package com.potato369.find.dynamic.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import cn.hutool.core.util.StrUtil;
import com.potato369.find.common.enums.LikeRecordTypeEnum;
import com.potato369.find.common.enums.LikeStatusEnum;
import com.potato369.find.common.vo.CommentVO;
import com.potato369.find.common.vo.PageCommentVOs;
import com.potato369.find.dynamic.config.props.ProjectUrlProps;
import com.potato369.find.mbg.mapper.LikeRecordMapper;
import com.potato369.find.mbg.mapper.UserMapper;
import com.potato369.find.mbg.model.*;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.potato369.find.common.enums.DeleteStatusEnum;
import com.potato369.find.dynamic.service.CommentService;
import com.potato369.find.mbg.mapper.CommentMapper;

@Service
@Transactional
public class CommentServiceImpl implements CommentService {

    private CommentMapper commentMapperReader;

    private CommentMapper commentMapperWriter;

    private UserMapper userMapperReader;

    private LikeRecordMapper likeRecordMapperReader;

    private ProjectUrlProps projectUrlProps;

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

    /**
     * 新增评论
     *
     * @param comment
     * @return
     */
    @Override
    @Transactional(readOnly = false)
    public int save(Comment comment) {
        return this.commentMapperWriter.insertSelective(comment);
    }

    /**
     * 修改评论
     *
     * @param comment
     * @return
     */
    @Override
    @Transactional(readOnly = false)
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
        commentExample.createCriteria()
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
    public PageCommentVOs pageCommentsByDynamicId(Long userId, Long dynamicId, int pageNum, int pageSize) {
        CommentExample example = new CommentExample();
        example.setDistinct(false);
        example.setOrderByClause("created_time DESC, updated_time DESC, likes DESC");
        example.createCriteria().andDynamicInfoIdEqualTo(dynamicId).andDeleteStatusEqualTo(DeleteStatusEnum.NO.getStatus());
        final Page<Comment> commentsPage = PageHelper.startPage(pageNum, pageSize).doSelectPage(() -> this.commentMapperReader.selectByExampleWithBLOBs(example));
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
                        commentVO.setHead(StrUtil.trimToNull(this.projectUrlProps.getResDomain()) +
                                StrUtil.trimToNull(this.projectUrlProps.getProjectName()) +
                                StrUtil.trimToNull(this.projectUrlProps.getResHeadIcon()) +
                                user.getId() +
                                "/" +
                                user.getHeadIcon());
                    }
                    commentVO.setComment(comment.getContent());
                    commentVO.setCommentDateTime(comment.getCreatedTime());
                    commentVO.setLikes(comment.getLikes());
                    LikeRecordExample likeRecordExample = new LikeRecordExample();
                    likeRecordExample.createCriteria()
                            .andDynamicInfoIdEqualTo(comment.getId())
                            .andUserIdEqualTo(userId)
                            .andTypeEqualTo(LikeRecordTypeEnum.Comment.getType())
                            .andStatusEqualTo(LikeStatusEnum.YES.getStatus());
                    List<LikeRecord> likeRecordList = this.likeRecordMapperReader.selectByExample(likeRecordExample);
                    System.out.println("likeRecordList="+likeRecordList);
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
}
