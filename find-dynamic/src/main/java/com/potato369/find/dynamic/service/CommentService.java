package com.potato369.find.dynamic.service;

import com.potato369.find.common.vo.PageCommentVOs;
import com.potato369.find.mbg.model.Comment;
import com.potato369.find.mbg.model.DynamicInfo;

public interface CommentService {

  /**
   * 新增评论
   *
   * @param comment
   * @param dynamicInfo
   * @return
   */
  int save(Comment comment, DynamicInfo dynamicInfo);

  /**
   * 查询评论
   *
   * @param id
   * @return
   */
  Comment findById(Long id);

  /**
   * 修改评论
   *
   * @param comment
   * @return
   */
  int update(Comment comment);

  /**
   * 根据动态内容id分页查询评论列表
   *
   * @param dynamicId
   * @param pageNum
   * @param pageSize
   * @return
   */
  PageCommentVOs pageCommentsByDynamicId(Long userId, Long dynamicId, int pageNum, int pageSize);
}
