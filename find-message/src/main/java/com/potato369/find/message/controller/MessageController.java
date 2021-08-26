package com.potato369.find.message.controller;

import com.potato369.find.common.api.CommonResult;
import com.potato369.find.common.vo.MessageVO;
import com.potato369.find.common.vo.MessageVO2;
import com.potato369.find.common.vo.MessageVO3;
import com.potato369.find.message.service.MessageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

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
     * 获取消息界面点赞和申请加微信消息记录
     *
     * @param userId   用户id
     * @param pageNum  当前页码，默认：1
     * @param pageSize 每页数量，默认：20
     */
    @ApiOperation(value = "获取最新（动态或评论内容）点赞，评论和申请加微信消息记录", notes = "获取最新（动态或评论内容）点赞，评论和申请加微信消息记录")
    @GetMapping(value = "/{id}/all.do")
    public CommonResult<MessageVO> all(
            @PathVariable(name = "id") @ApiParam(name = "id", value = "用户id", required = true, example = "1") Long userId,
            @RequestParam(name = "pageNum", required = false, defaultValue = "1") @ApiParam(name = "pageNum", value = "当前页码", example = "1") int pageNum,
            @RequestParam(name = "pageSize", required = false, defaultValue = "20") @ApiParam(name = "pageSize", value = "每页数量", example = "20") int pageSize) {
        return CommonResult.success(this.messageService.selectApplicationsMessage(userId, pageNum, pageSize));
    }

    /**
     * 被点赞的动态内容消息记录
     *
     * @param userId   被点赞的动态所属的用户id
     * @param pageNum  当前页码，默认：1
     * @param pageSize 每页数量，默认：20
     */
    @ApiOperation(value = "被点赞的动态、评论内容消息记录", notes = "被点赞的动态、评论内容消息记录")
    @GetMapping(value = "/{id}/likes.do")
    public CommonResult<MessageVO2> likes(
            @PathVariable(name = "id") @ApiParam(name = "id", value = "用户id", required = true, example = "1") Long userId,
            @RequestParam(name = "pageNum", required = false, defaultValue = "1") @ApiParam(name = "pageNum", value = "当前页码", example = "1") Integer pageNum,
            @RequestParam(name = "pageSize", required = false, defaultValue = "20") @ApiParam(name = "pageSize", value = "每页数量", example = "20") Integer pageSize) {
        return CommonResult.success(this.messageService.selectLikesMessage(userId, pageNum, pageSize));
    }

    /**
     * 被评论的动态内容消息记录
     *
     * @param userId   被评论的动态所属的用户id
     * @param pageNum  当前页码，默认：1
     * @param pageSize 每页数量，默认：20
     */
    @ApiOperation(value = "被评论动态内容消息记录", notes = "被评论动态内容消息记录")
    @GetMapping(value = "/{id}/comments.do")
    public CommonResult<MessageVO2> comments(
            @PathVariable(name = "id") @ApiParam(name = "id", value = "用户id", required = true, example = "1") Long userId,
            @RequestParam(name = "pageNum", required = false, defaultValue = "1") @ApiParam(name = "pageNum", value = "当前页码", example = "1") Integer pageNum,
            @RequestParam(name = "pageSize", required = false, defaultValue = "20") @ApiParam(name = "pageSize", value = "每页数量", example = "20") Integer pageSize) {
        return CommonResult.success(this.messageService.selectLikesMessage(userId, pageNum, pageSize));
    }

    /**
     * 消息记录
     *
     * @param sendUserId      发送者用户id
     * @param recipientUserId 接收者用户id
     * @param pageNum         当前页码，默认：1
     * @param pageSize        每页数量，默认：20
     */
    @ApiOperation(value = "消息记录接口", notes = "消息记录接口")
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
     *
     * @param sendUserId 发送者用户id
     * @param messageId  消息id
     * @param content    消息内容
     */
    @ApiOperation(value = "发送消息接口", notes = "发送消息接口")
    @PostMapping(value = "/{id}/send.do")
    public CommonResult<Map<String, Object>> send(
            @PathVariable(name = "id") @ApiParam(name = "id", value = "发送者用户id", required = true, example = "1") Long sendUserId,
            @RequestParam(name = "messageId") @ApiParam(name = "messageId", value = "消息id", required = true, example = "3") Long messageId,
            @RequestParam(name = "content") @ApiParam(name = "content", value = "消息内容", required = true, example = "可以聊聊吗？") String content) {
        return this.messageService.sendMessageAndPush(sendUserId, messageId, content);
    }

    /**
     * 标记全部消息已读
     *
     * @param recipientUserId 收者用户id
     */
    @ApiOperation(value = "标记全部消息已读接口", notes = "标记全部消息已读接口")
    @PutMapping(value = "/{id}/updateAll.do")
    public CommonResult<Map<String, Object>> updateAll(
            @PathVariable(name = "id") @ApiParam(name = "id", value = "消息收者用户id", required = true, example = "1") Long recipientUserId) {
        return this.messageService.allRead(recipientUserId);
    }

    /**
     * 删除申请加微信消息记录
     *
     * @param recipientUserId 消息收者用户id
     * @param messageId       消息记录id
     */
    @ApiOperation(value = "删除申请加微信消息记录接口", notes = "删除申请加微信消息记录接口")
    @DeleteMapping(value = "/{id1}/delete.do")
    public CommonResult<Map<String, Object>> deleteApplications(
            @PathVariable(name = "id1") @ApiParam(name = "id1", value = "消息收者用户id", required = true, example = "1") Long recipientUserId,
            @RequestParam(name = "id2") @ApiParam(name = "id2", value = "消息记录id", required = true, example = "2") Long messageId) {
        return this.messageService.delete(recipientUserId, messageId);
    }

    /**
     * 删除点赞消息记录接口
     *
     * @param recipientUserId 消息收者用户id
     * @param messageId       消息记录id
     */
    @ApiOperation(value = "删除点赞消息记录接口", notes = "删除点赞消息记录接口")
    @DeleteMapping(value = "/{id}/deleteLikes.do")
    public CommonResult<Map<String, Object>> deleteLikes(
            @PathVariable(name = "id") @ApiParam(name = "id", value = "消息收者用户id", required = true, example = "1") Long recipientUserId,
            @RequestParam(name = "messageId") @ApiParam(name = "messageId", value = "消息记录id", required = true, example = "2") Long messageId) {
        return this.messageService.deleteLikes(recipientUserId, messageId);
    }

    /**
     * 回复申请加微信消息记录接口
     *
     * @param userId    被申请者用户id
     * @param messageId 消息id
     * @param type      类型，0->拒绝，1->同意
     * @param content   消息内容
     */
    @ApiOperation(value = "回复申请加微信消息记录接口", notes = "回复申请加微信消息记录接口")
    @PutMapping(value = "/{id}/reply.do")
    public CommonResult<Map<String, Object>> reply(@PathVariable(name = "id") @ApiParam(name = "id", value = "被申请者用户id", required = true, example = "1") Long userId,
                                                   @RequestParam(name = "messageId") @ApiParam(name = "messageId", value = "消息id", required = true, example = "0") Long messageId,
                                                   @RequestParam(name = "type") @ApiParam(name = "type", value = "类型，0->拒绝，1->同意", required = true, example = "0") String type,
                                                   @RequestParam(name = "content", required = false) @ApiParam(name = "content", value = "消息内容", example = "非常抱歉，我不想加你！") String content,
                                                   @RequestParam(name = "weChatId", required = false) @ApiParam(name = "weChatId", value = "微信号", example = "wx123456789") String weChatId) {
        return this.messageService.replyApplications(userId, messageId, type, content, weChatId);
    }
}
