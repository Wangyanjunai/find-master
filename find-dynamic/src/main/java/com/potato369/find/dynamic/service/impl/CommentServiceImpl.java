package com.potato369.find.dynamic.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.potato369.find.common.enums.DeleteStatusEnum;
import com.potato369.find.dynamic.service.CommentService;
import com.potato369.find.mbg.mapper.CommentMapper;
import com.potato369.find.mbg.model.Comment;
import com.potato369.find.mbg.model.CommentExample;

@Service
@Transactional
public class CommentServiceImpl implements CommentService{

	private CommentMapper commentMapperReader;
	
	private CommentMapper commentMapperWriter;

	@Autowired
	public void setCommentMapperReader(CommentMapper commentMapperReader) {
		this.commentMapperReader = commentMapperReader;
	}

	@Autowired
	public void setCommentMapperWriter(CommentMapper commentMapperWriter) {
		this.commentMapperWriter = commentMapperWriter;
	}

	/**
	 * 新增评论
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
	 * @param dynamicId
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	@Override
	@Transactional(readOnly = true)
	public Page<Comment> pageCommentsByDynamicId(Long dynamicId, int pageNum, int pageSize) {
		CommentExample example = new CommentExample();
		example.createCriteria().andDynamicInfoIdEqualTo(dynamicId).andDeleteStatusEqualTo(DeleteStatusEnum.NO.getStatus());
		return PageHelper.startPage(pageNum, pageSize).doSelectPage(() -> this.commentMapperReader.selectByExampleWithBLOBs(example));
	}
}
