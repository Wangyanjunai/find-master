package com.potato369.find.dynamic.service.impl;

import cn.hutool.core.util.StrUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.potato369.find.common.enums.*;
import com.potato369.find.common.vo.CommentVO;
import com.potato369.find.common.vo.PageCommentVOs;
import com.potato369.find.dynamic.config.props.ProjectUrlProps;
import com.potato369.find.dynamic.service.CommentService;
import com.potato369.find.mbg.mapper.*;
import com.potato369.find.mbg.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@Transactional(isolation = Isolation.SERIALIZABLE, rollbackFor = Exception.class, timeout = 30)
public class CommentServiceImpl implements CommentService {

    private CommentMapper commentMapperReader;

    private CommentMapper commentMapperWriter;

    private UserMapper userMapperReader;

    private LikeRecordMapper likeRecordMapperReader;

    private ProjectUrlProps projectUrlProps;

    private DynamicInfoMapper dynamicInfoMapperReader;

    private MessageMapper messageMapperWriter;

    private CommentRecordMapper commentRecordMapperWriter;

    @Autowired
    public void setCommentMapperReader(CommentMapper commentMapperReader) {
        this.commentMapperReader = commentMapperReader;
    }

    @Autowired
    public void setCommentMapperWriter(CommentMapper commentMapperWriter) {
        this.commentMapperWriter = commentMapperWriter;
    }

    @Autowired
    public void setUserMapperReader(UserMapper userMapperReader) {
        this.userMapperReader = userMapperReader;
    }

    @Autowired
    public void setProjectUrlProps(ProjectUrlProps projectUrlProps) {
        this.projectUrlProps = projectUrlProps;
    }

    @Autowired
    public void setLikeRecordMapperReader(LikeRecordMapper likeRecordMapperReader) {
        this.likeRecordMapperReader = likeRecordMapperReader;
    }

    @Autowired
    public void setDynamicInfoMapperReader(DynamicInfoMapper dynamicInfoMapperReader) {
        this.dynamicInfoMapperReader = dynamicInfoMapperReader;
    }

    @Autowired
    public void setMessageMapperWriter(MessageMapper messageMapperWriter) {
        this.messageMapperWriter = messageMapperWriter;
    }

    @Autowired
    public void setCommentRecordMapperWriter(CommentRecordMapper commentRecordMapperWriter) {
        this.commentRecordMapperWriter = commentRecordMapperWriter;
    }

    /**
     * ????????????
     *
     * @param content
     * @param comment
     * @param commentRecord
     * @param dynamicInfo
     */
    @Override
    public int save(String content, Comment comment, CommentRecord commentRecord, DynamicInfo dynamicInfo) {
        int a = this.dynamicInfoMapperReader.updateByPrimaryKeySelective(dynamicInfo);
        int b = this.commentMapperWriter.insertSelective(comment);
        int c = this.commentRecordMapperWriter.insertSelective(commentRecord);
        //????????????
        Message messageRecord = new Message();
        messageRecord.setContent(content);//????????????
        messageRecord.setSendMode(MessageSendModeEnum.PASSIVE.getStatus());//????????????
        messageRecord.setRecipientUserId(dynamicInfo.getUserId());//???????????????id
        messageRecord.setSendUserId(comment.getUserId());//???????????????id
        messageRecord.setStatus(MessageStatusEnum.UNREAD.getStatus());//??????
        messageRecord.setReserveColumn01(MessageTypeEnum.Comments.getMessage());//?????????????????????->comments
        messageRecord.setReserveColumn02(MessageType2Enum.SEND.getCodeStr());//??????
        messageRecord.setReserveColumn03(MessageStatus2Enum.NO.getStatus());//????????????
        messageRecord.setReserveColumn04(String.valueOf(commentRecord.getId()));//????????????id
        int d = this.messageMapperWriter.insertSelective(messageRecord);
        return a + b + c + d;
    }

    /**
     * ????????????
     *
     * @param comment
     * @return
     */
    @Override
    public int update(Comment comment) {
        return this.commentMapperWriter.updateByPrimaryKeySelective(comment);
    }

    /*
     * ????????????
     * @param comment
     * @return
     */
    @Override
    public Comment findById(Long id) {
        CommentExample commentExample = new CommentExample();
        commentExample.createCriteria().andDeleteStatusEqualTo(DeleteStatusEnum.NO.getStatus()).andIdEqualTo(id);
        List<Comment> comments = this.commentMapperReader.selectByExampleWithBLOBs(commentExample);
        if (!Objects.isNull(comments) && !comments.isEmpty()) {
            return comments.get(0);
        }
        return null;
    }

    /**
     * ??????????????????id????????????????????????
     *
     * @param dynamicId
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public PageCommentVOs pageCommentsByDynamicId(
            Long userId, Long dynamicId, int pageNum, int pageSize) {
        CommentExample example = new CommentExample();
        example.setDistinct(false);
        example.setOrderByClause("created_time DESC, updated_time DESC, likes DESC");
        example
                .createCriteria()
                .andDynamicInfoIdEqualTo(dynamicId)
                .andDeleteStatusEqualTo(DeleteStatusEnum.NO.getStatus());
        final Page<Comment> commentsPage =
                PageHelper.startPage(pageNum, pageSize)
                        .doSelectPage(() -> this.commentMapperReader.selectByExampleWithBLOBs(example));
        PageCommentVOs pageCommentVOs = PageCommentVOs.builder().build();
        if (!Objects.isNull(commentsPage) && commentsPage.getTotal() > 0) {
            pageCommentVOs.setTotalSize(commentsPage.getTotal());
            pageCommentVOs.setTotalPage(commentsPage.getPages());
            List<Comment> commentList = commentsPage.getResult();
            List<CommentVO> CommentVOList = new ArrayList<>();
            if (!Objects.isNull(commentList) && !commentList.isEmpty()) {
                for (Comment comment : commentList) {
                    CommentVO commentVO = CommentVO.builder().build();
                    Long userId1 = comment.getUserId();
                    commentVO.setUserId(userId1);
                    commentVO.setCommentId(comment.getId());
                    User user = this.userMapperReader.selectByPrimaryKey(userId1);
                    if (!Objects.isNull(user)) {
                        commentVO.setNickname(user.getNickName());
                        commentVO.setHead(
                                StrUtil.trimToNull(this.projectUrlProps.getResDomain())
                                        + StrUtil.trimToNull(this.projectUrlProps.getProjectName())
                                        + StrUtil.trimToNull(this.projectUrlProps.getResHeadIcon())
                                        + user.getId()
                                        + "/"
                                        + user.getHeadIcon());
                    }
                    commentVO.setComment(comment.getContent());
                    commentVO.setCommentDateTime(comment.getCreatedTime());
                    commentVO.setLikes(comment.getLikes());
                    LikeRecordExample likeRecordExample = new LikeRecordExample();
                    likeRecordExample
                            .createCriteria()
                            .andDynamicInfoIdEqualTo(comment.getId())
                            .andUserIdEqualTo(userId)
                            .andTypeEqualTo(LikeRecordTypeEnum.Comment.getType())
                            .andStatusEqualTo(LikeStatusEnum.YES.getStatus());
                    List<LikeRecord> likeRecordList =
                            this.likeRecordMapperReader.selectByExample(likeRecordExample);
                    if (!Objects.isNull(likeRecordList) && likeRecordList.size() > 0) {
                        commentVO.setIsOrNotLikes(LikeStatusEnum.YES.getStatus());
                    } else {
                        commentVO.setIsOrNotLikes(LikeStatusEnum.NO.getStatus());
                    }
                    CommentVOList.add(commentVO);
                }
            }
            pageCommentVOs.setList(CommentVOList);
        }
        return pageCommentVOs;
    }

    /**
     * ????????????id?????????id????????????
     *
     * @param dynamicId ????????????id
     * @param userId    ??????id
     */
    @Override
    public List<Comment> findByDynamicIdAndUserId(Long dynamicId, Long userId) {
        CommentExample commentExample = new CommentExample();
        commentExample.setOrderByClause("created_time DESC");
        commentExample.createCriteria().andUserIdEqualTo(userId).andDynamicInfoIdEqualTo(dynamicId).andDeleteStatusEqualTo(DeleteStatusEnum.NO.getStatus());
        return this.commentMapperReader.selectByExample(commentExample);
    }
}
