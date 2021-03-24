package com.potato369.find.message.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.potato369.find.common.api.CommonResult;
import com.potato369.find.common.vo.MessageVO;
import com.potato369.find.common.vo.MessageVO2;
import com.potato369.find.common.vo.MessageVO3;
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
     * @param userId 用户id
     * @param pageNum 当前页码，默认：1
     * @param pageSize 每页数量，默认：20
     */
    @ApiOperation(value = "消息主界面获取点赞和申请加微信", notes = "消息主界面获取点赞和申请加微信")
	@GetMapping(value = "/{id}/all.do")
	public CommonResult<MessageVO> all(
			@PathVariable(name = "id", required = true) @ApiParam(name = "id", value = "用户id", required = true, example = "1") Long userId,
			@RequestParam(name = "pageNum", defaultValue = "1") @ApiParam(name = "pageNum", value = "当前页码", example = "1") Integer pageNum,
			@RequestParam(name = "pageSize", defaultValue = "20") @ApiParam(name = "pageSize", value = "每页数量", example = "20") Integer pageSize) {
		return CommonResult.success(this.messageService.selectApplicationsMessage(userId, pageNum, pageSize));
	}
    
    /**
     * 被点赞的动态内容消息记录
     * @param userId 用户id
     * @param pageNum 当前页码，默认：1
     * @param pageSize 每页数量，默认：20
     */
    @ApiOperation(value = "被点赞的动态内容消息记录", notes = "被点赞的动态内容消息记录")
    @GetMapping(value = "/{id}/likes.do")
    public CommonResult<MessageVO2> likes(
    		@PathVariable(name = "id") @ApiParam(name = "id", value = "用户id", required = true, example = "1") Long userId,
            @RequestParam(name = "pageNum", required = false, defaultValue = "1") @ApiParam(name = "pageNum", value = "当前页码", example = "1") Integer pageNum,
            @RequestParam(name = "pageSize", required = false, defaultValue = "20") @ApiParam(name = "pageSize", value = "每页数量", example = "20") Integer pageSize) {
		return CommonResult.success(this.messageService.selectLikesMessage(userId, pageNum, pageSize));
	}
    
    /**
     * 发送的消息记录
     * @param sendUserId 消息发送者用户id
     * @param recipientUserId 消息收者用户id
     * @param pageNum 当前页码，默认：1
     * @param pageSize 每页数量，默认：20
     */
    @ApiOperation(value = "发送的消息记录接口", notes = "发送的消息记录接口")
    @GetMapping(value = "/{id1}/{id2}/messages.do")
    public CommonResult<MessageVO3> messages(
    		@PathVariable(name = "id1") @ApiParam(name = "id1", value = "消息发送者用户id", required = true, example = "1") Long sendUserId,
    		@PathVariable(name = "id2") @ApiParam(name = "id2", value = "消息接收者用户id", required = true, example = "2") Long recipientUserId,
            @RequestParam(name = "pageNum", required = false, defaultValue = "1") @ApiParam(name = "pageNum", value = "当前页码", example = "1") Integer pageNum,
            @RequestParam(name = "pageSize", required = false, defaultValue = "20") @ApiParam(name = "pageSize", value = "每页数量", example = "20") Integer pageSize) {
		return CommonResult.success(this.messageService.selectMessageRecord(sendUserId, recipientUserId, pageNum, pageSize));
	}
    
    /**
     * 发送消息
     * @param sendUserId 消息发送者用户id
     * @param recipientUserId 消息收者用户id
     * @param pageNum 当前页码，默认：1
     * @param pageSize 每页数量，默认：20
     */
    @ApiOperation(value = "发送消息接口", notes = "发送消息接口")
    @PostMapping(value = "/{id1}/{id2}/send.do")
    public CommonResult<Map<String, Object>> send(
    		@PathVariable(name = "id1") @ApiParam(name = "id1", value = "消息发送者用户id", required = true, example = "1") Long sendUserId,
    		@PathVariable(name = "id2") @ApiParam(name = "id2", value = "消息接收者用户id", required = true, example = "2") Long recipientUserId,
    		@RequestParam(name = "content") @ApiParam(name = "content", value = "消息内容", required = true, example = "可以申请加你的微信吗？") String content) {
    	return this.messageService.sendMessageAndPush(sendUserId, recipientUserId, content);
    }
}
