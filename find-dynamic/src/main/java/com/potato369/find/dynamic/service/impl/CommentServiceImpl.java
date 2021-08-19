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
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@Transactional
public class CommentServiceImpl implements CommentService {

    private CommentMapper commentMapperReader;

    private CommentMapper commentMapperWriter;

    private UserMapper userMapperReader;

    private LikeRecordMapper likeRecordMapperReader;

    private ProjectUrlProps projectUrlProps;

    private DynamicInfoMapper dynamicInfoMapperReader;

    private MessageMapper messageMapperWriter;

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

    /**
     * 新增评论
     *
     * @param content
     * @param comment
     * @param dynamicInfo
     */
    @Override
    @Transactional
    public int save(String content, Comment comment, DynamicInfo dynamicInfo) {
        int a = this.dynamicInfoMapperReader.updateByPrimaryKeySelective(dynamicInfo);
        int b = this.commentMapperWriter.insertSelective(comment);
        //消息记录
        Message messageRecord = new Message();
        messageRecord.setContent(content);//消息内容
        messageRecord.setSendMode(MessageSendModeEnum.PASSIVE.getStatus());//发送方式
        messageRecord.setRecipientUserId(dynamicInfo.getUserId());//接收者用户id
        messageRecord.setSendUserId(comment.getUserId());//发送者用户id
        messageRecord.setStatus(MessageStatusEnum.UNREAD.getStatus());//未读
        messageRecord.setReserveColumn01(MessageTypeEnum.Comments.getMessage());//消息类型，评论->comments
        messageRecord.setReserveColumn02(MessageType2Enum.SEND.getCodeStr());//发送
        messageRecord.setReserveColumn03(MessageStatus2Enum.NO.getStatus());//是否删除
        int c = this.messageMapperWriter.insertSelective(messageRecord);
        return a + b + c;
    }

    /**
     * 修改评论
     *
     * @param comment
     * @return
     */
    @Override
    @Transactional
    public int update(Comment comment) {
        return this.commentMapperWriter.updateByPrimaryKeySelective(comment);
    }

    /*
     * 查询评论
     * @param comment
     * @return
     */
    @Override
    @Transactional(readOnly = true)
    public Comment findById(Long id) {
        CommentExample commentExample = new CommentExample();
        commentExample
                .createCriteria()
                .andDeleteStatusEqualTo(DeleteStatusEnum.NO.getStatus())
                .andIdEqualTo(id);
        List<Comment> comments = this.commentMapperReader.selectByExample(commentExample);
        if (comments != null && !comments.isEmpty()) {
            return comments.get(0);
        }
        return null;
    }

    /**
     * 根据动态内容id分页查询评论列表
     *
     * @param dynamicId
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    @Transactional(readOnly = true)
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
     * 根据用户id和动态id查询评论
     *
     * @param dynamicId 动态内容id
     * @param userId    用户id
     */
    @Override
    @Transactional(readOnly = true)
    public List<Comment> findByDynamicIdAndUserId(Long dynamicId, Long userId) {
        CommentExample commentExample = new CommentExample();
        commentExample.setOrderByClause("created_time DESC");
        commentExample.createCriteria().andUserIdEqualTo(userId).andDynamicInfoIdEqualTo(dynamicId).andDeleteStatusEqualTo(DeleteStatusEnum.NO.getStatus());
        return this.commentMapperReader.selectByExample(commentExample);
    }
}
