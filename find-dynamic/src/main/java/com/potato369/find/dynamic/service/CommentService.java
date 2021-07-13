package com.potato369.find.dynamic.service;


import com.github.pagehelper.Page;
import com.potato369.find.mbg.model.Comment;

public interface CommentService {

	/**
	 * 新增评论
	 * @param comment
	 * @return
	 */
	int save(Comment comment);
	
	/**
	 * 查询评论
	 * @param comment
	 * @return
	 */
	Comment findById(Long id);
	
	/**
	 * 修改评论
	 * @param comment
	 * @return
	 */
	int update(Comment comment);
	
	/**
	 * 根据动态内容id分页查询评论列表
	 * @param dynamicId
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	Page<Comment> pageCommentsByDynamicId(Long dynamicId, int pageNum, int pageSize);
}
