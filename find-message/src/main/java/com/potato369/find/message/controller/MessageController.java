package com.potato369.find.message.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.potato369.find.common.api.CommonResult;
import com.potato369.find.common.vo.MessageVO;
import com.potato369.find.common.vo.MessageVO2;
import com.potato369.find.message.service.MessageService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(value = "消息模块消息管理控制器类", consumes = "消息模块消息管理控制器类")
@RestController
@RequestMapping("/v1/message")
public class MessageController {

    private MessageService messageService;
    
    @Autowired
    public void setMessageService(MessageService messageService) {
        this.messageService = messageService;
    }

	/**
	 * 消息主界面获取点赞和申请加微信
     * @param userId   用户id
     * @param pageNum  当前页码，默认：1
     * @param pageSize 每页数量，默认：20
     */
    @ApiOperation(value = "消息主界面获取点赞和申请加微信", notes = "消息主界面获取点赞和申请加微信")
	@GetMapping(value = "/{id}/all.do")
	public CommonResult<MessageVO> findAll(
			@PathVariable(name = "id", required = true) @ApiParam(name = "id", value = "用户id", required = true, example = "1") Long userId,
			@RequestParam(name = "pageNum", defaultValue = "1") @ApiParam(name = "pageNum", value = "当前页码", example = "1") Integer pageNum,
			@RequestParam(name = "pageSize", defaultValue = "20") @ApiParam(name = "pageSize", value = "每页数量", example = "20") Integer pageSize) {
		return CommonResult.success(this.messageService.selectNotLikesMessage(userId, pageNum, pageSize));
	}
    
    /**
     * 被点赞的动态内容消息记录
     * @param userId   用户id
     * @param pageNum  当前页码，默认：1
     * @param pageSize 每页数量，默认：20
     */
    @ApiOperation(value = "被点赞的动态内容消息记录", notes = "被点赞的动态内容消息记录")
    @GetMapping(value = "/{id}/likes.do")
    public CommonResult<MessageVO2> findLikes(
    		@PathVariable(name = "id") @ApiParam(name = "id", value = "用户id", required = true, example = "1") Long userId,
            @RequestParam(name = "pageNum", required = false, defaultValue = "1") @ApiParam(name = "pageNum", value = "页码", example = "1") Integer pageNum,
            @RequestParam(name = "pageSize", required = false, defaultValue = "20") @ApiParam(name = "pageSize", value = "每页数量", example = "20") Integer pageSize) {
		return CommonResult.success(this.messageService.selectLikesMessage(userId, pageNum, pageSize));
	}
}
