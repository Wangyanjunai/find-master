package com.potato369.find.portal.controller;

import com.potato369.find.common.api.CommonResult;
import com.potato369.find.common.vo.MessageVO;
import com.potato369.find.common.vo.MessageVO2;
import com.potato369.find.common.vo.MessageVO3;
import com.potato369.find.portal.feign.MessageService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.WebApplicationContext;

import java.util.Map;

@Api(value = "message-controller", tags = "消息模块Restful API")
@RestController
@RequestMapping("/message")
@Scope(value = WebApplicationContext.SCOPE_REQUEST)
//@Profile({"dev", "dev2", "test", "prod"})
public class MessageController {

    private MessageService messageFeignClient;

    @Autowired
    public void setMessageFeignClient(MessageService messageFeignClient) {
        this.messageFeignClient = messageFeignClient;
    }

    /**
     * @api {get} /find/message/{id}/all 分页获取消息界面点赞和申请加微信消息列表接口
     * @apiVersion 1.0.0
     * @apiGroup 消息模块API
     * @apiName 分页获取消息界面点赞和申请加微信消息列表
     * @apiParam (接口请求参数){Number} id 接收者用户id
     * @apiParam (接口请求参数) {Number} [pageNum=1] 当前页码
     * @apiParam (接口请求参数) {Number} [pageSize=20] 每页数量
     * @apiParamExample 请求示例
     * curl -v -X GET "http://w168428j19.51mypc.cn/find/message/29/all?pageNum=1&pageSize=20" -H "accept: application/json"
     * @apiSuccess (200) {Number} code 信息码
     * @apiSuccess (200) {String} msg 说明
     * @apiSuccess (200) {Number} status 响应状态码
     * @apiSuccess (200) {Object} [data] 消息数据
     * @apiSuccess (200) {Object} [likes] 最新点赞或者评论消息
     * @apiSuccess (200) {String} [content1] 最新一条点赞或者评论消息内容
     * @apiSuccess (200) {Number} [count1] 未读点赞或者评论消息数量
     * @apiSuccess (200){Number} [totalCount] 申请加微信消息总条数
     * @apiSuccess (200) {Number} [totalPage] 申请加微信消息总页数
     * @apiSuccess (200) {Number} [unReadCount] 未读（点赞，评论，申请加微信）消息总条数
     * @apiSuccess (200) {Object[]} [list] 申请加微信消息数据
     * @apiSuccess (200){Number} [messageId] 申请加微信消息记录id
     * @apiSuccess (200){Number} [userId] 申请加微信发送者用户id
     * @apiSuccess (200) {String} [head] 申请加微信发送者用户头像
     * @apiSuccess (200) {String} [nickname] 申请加微信发送者用户昵称
     * @apiSuccess (200) {String} [content2] 申请加微信发送消息内容
     * @apiSuccess (200){Number} [count2] 申请加微信未读消息数量
     * @apiSuccess (200) {String} [createTime] 消息发送时间
     * @apiSuccess (200) {String} [type] 消息类型，0->普通消息，1->申请加微信消息
     * @apiSuccess (200) {Number} [flag] 是否展示复制微信按钮，0->否，1->是
     * @apiSuccess (200) {Number} [flag2] 是否展示“同意”，“拒绝”按钮，0->否，1->是
     * @apiSuccess (200) {String} [weixinId] 微信号
     * @apiSuccess (200) {Boolean} [isOrNotApplication] 是否是申请者，true->是，false->否
     * @apiSuccessExample {json} 200响应示例
     * HTTP/1.1 200 OK
     * {
     * "status": 200,
     * "code": 0,
     * "msg": "返回数据成功。",
     * "data": {
     * "likes": {
     * "content1": "测试11 点赞您的动态 测试是否推送",
     * "count1": 0
     * },
     * "totalCount": 6,
     * "totalPage": 1,
     * "list": [
     * {
     * "messageId": 865,
     * "userId": 229,
     * "head": "http://8.135.36.45:8000/find02/img/head/229/341bbd52-135b-46ab-9996-9698ba98a919.jpg",
     * "nickname": "测试11",
     * "content2": "你好，可以加微信吗",
     * "createTime": "2021年09月17日 16:41:16",
     * "count2": 1,
     * "type": "1",
     * "flag": 0,
     * "flag2": 1,
     * "isOrNotApplication": false
     * },
     * {
     * "messageId": 852,
     * "userId": 224,
     * "head": "http://8.135.36.45:8000/find02/img/head/224/d9a25d51-c90e-4df6-9910-5e20dbe565f1.jpg",
     * "nickname": "tt",
     * "content2": "你好美女",
     * "createTime": "2021年09月16日 14:52:12",
     * "count2": 1,
     * "type": "1",
     * "flag": 0,
     * "flag2": 1,
     * "isOrNotApplication": false
     * },
     * {
     * "messageId": 724,
     * "userId": 210,
     * "head": "http://8.135.36.45:8000/find02/img/head/210/b6d8f544-a1b1-45a3-92da-72618a90df95.png",
     * "nickname": "关键",
     * "content2": "qqq",
     * "createTime": "2021年09月13日 14:07:11",
     * "count2": 0,
     * "type": "1",
     * "flag": 0,
     * "flag2": 1,
     * "isOrNotApplication": false
     * },
     * {
     * "messageId": 714,
     * "userId": 142,
     * "head": "http://8.135.36.45:8000/find02/img/head/142/331e380a-73da-4592-9bd6-6c79f539c4e4.jpeg",
     * "nickname": "qqq",
     * "content2": "我的微信号是wx406181651",
     * "createTime": "2021年09月13日 13:52:35",
     * "count2": 0,
     * "type": "0",
     * "flag": 1,
     * "flag2": 0,
     * "isOrNotApplication": false
     * },
     * {
     * "messageId": 710,
     * "userId": 139,
     * "head": "http://8.135.36.45:8000/find02/img/head/139/76489d30-d996-46dd-81c9-fdb3ed6197ca.jpg",
     * "nickname": "哦哦哦哦",
     * "content2": "我的微信号是wx406181651",
     * "createTime": "2021年09月13日 13:46:21",
     * "count2": 0,
     * "type": "0",
     * "flag": 1,
     * "flag2": 0,
     * "weixinId": "qqq",
     * "isOrNotApplication": false
     * },
     * {
     * "messageId": 658,
     * "userId": 44,
     * "head": "http://8.135.36.45:8000/find02/img/head/44/10.png",
     * "nickname": "如雪",
     * "content2": "你好",
     * "createTime": "2021年09月09日 14:08:43",
     * "count2": 0,
     * "type": "0",
     * "flag": 0,
     * "flag2": 0,
     * "isOrNotApplication": true
     * }
     * ],
     * "unReadCount": 2
     * }
     * }
     * @apiError (403) {Number} status 响应状态码
     * @apiError (403) {Number} code 消息码
     * @apiError (403) {String} msg 说明
     * @apiErrorExample {json} 403错误
     * HTTP/1.1 403 403响应
     * {
     * "status": 403,
     * "code": 199,
     * "msg": "未找到用户信息！"
     * }
     * @apiError (404) {Number} status 响应状态码
     * @apiError (404) {Number} code 消息码
     * @apiError (404) {String} msg 说明
     * @apiErrorExample {json} 404错误
     * HTTP/1.1 404 404响应
     * {
     * "status": 404,
     * "code": 200,
     * "msg": "接口未注册！"
     * }
     * @apiError (500) {Number} status 响应状态码
     * @apiError (500) {Number} code 消息码
     * @apiError (500) {String} msg 说明
     * @apiErrorExample {json} 500错误
     * HTTP/1.1 500 500响应
     * {
     * "status": 500,
     * "code": 205,
     * "msg": "服务器未响应！"
     * }
     */
    @GetMapping(value = "/{id}/all")
    @ApiOperation(value = "分页获取消息界面点赞和申请加微信消息列表接口", notes = "用于用户分页获取消息界面点赞和申请加微信消息列表。")
    @ApiResponses(@ApiResponse(code = 200, message = "分页获取消息界面用户最近一条点赞和申请加微信消息列表成功", response = CommonResult.class))
    CommonResult<MessageVO> findAll(@PathVariable(name = "id") @ApiParam(name = "id", value = "用户id", required = true, example = "1") Long userId,
                                    @RequestParam(name = "pageNum", required = false, defaultValue = "1") @ApiParam(name = "pageNum", value = "当前页码，默认：当前第1页", example = "1") Integer pageNum, // 当前页码，默认：当前第1页
                                    @RequestParam(name = "pageSize", required = false, defaultValue = "20") @ApiParam(name = "pageSize", value = "每页数量，默认：每页20条", example = "20") Integer pageSize) {// 每页数量，默认：每页20条
        return this.messageFeignClient.all(userId, pageNum, pageSize);
    }

    /**
     * @api {get} /find/message/{id}/likes 分页获取点赞，评论消息列表接口
     * @apiVersion 1.0.0
     * @apiGroup 消息模块API
     * @apiName 分页获取点赞，评论消息列表
     * @apiParam (接口请求参数){Number} id 消息接收者用户id
     * @apiParam (接口请求参数) {Number} [pageNum=1] 当前页码
     * @apiParam (接口请求参数) {Number} [pageSize=20] 每页数量
     * @apiParamExample 请求示例
     * curl -v -X GET "http://w168428j19.51mypc.cn/find/message/138/likes?pageNum=1&pageSize=20" -H "accept: application/json"
     * @apiSuccess (200) {Number} code 信息码
     * @apiSuccess (200) {String} msg 说明
     * @apiSuccess (200) {Number} status 响应状态码
     * @apiSuccess (200) {Object} [data] 消息数据
     * @apiSuccess (200) {Number} [totalCount] 消息总条数
     * @apiSuccess (200) {Number} [totalPage] 消息总页数
     * @apiSuccess (200) {Object[]} [list] 消息数据
     * @apiSuccess (200) {Number} [messageId] 消息记录id
     * @apiSuccess (200) {Number} [userId] 消息发送者用户id
     * @apiSuccess (200) {String="0", "1", "3"} [type] 消息类型，"0"->点赞动态内容消息，"1"->点赞评论内容消息，"3"->评论动态内容消息
     * @apiSuccess (200) {Number} [dynamicInfoId] 点赞的或者评论的动态内容id
     * @apiSuccess (200) {String} [head] 消息发送者用户头像
     * @apiSuccess (200) {String} [content] 消息发送者发送消息内容
     * @apiSuccess (200) {String} [attacheType] 消息类型type="0"或者type="3"，为动态内容，则表示点赞，或者评论的动态内容类型，1->图片，2->语音
     * @apiSuccess (200) {String[]} [filenameList] 消息类型type="0"或者type="3"，则为动态内容的动态文件名称列表
     * @apiSuccessExample {json} 200响应示例
     * HTTP/1.1 200 OK
     * {
     * "status": 200,
     * "code": 0,
     * "msg": "返回数据成功。",
     * "data": {
     * "totalCount": 6,
     * "totalPage": 1,
     * "list": [
     * {
     * "messageId": 503,
     * "userId": 146,
     * "type": "1",
     * "dynamicInfoId": 24,
     * "head": "http://192.168.31.38:9000/find/img/head/146/68d93b51-fee5-413a-b600-3597f1a1197c.jpg",
     * "content": "尹明艳 点赞您的评论 是的，今天天气很糟糕的。"
     * },
     * {
     * "messageId": 501,
     * "userId": 146,
     * "type": "0",
     * "dynamicInfoId": 642,
     * "head": "http://192.168.31.38:9000/find/img/head/146/68d93b51-fee5-413a-b600-3597f1a1197c.jpg",
     * "content": "尹明艳 点赞您的动态 测试一下",
     * "attacheType": "1",
     * "filenameList": [
     * "http://192.168.31.38:9000/find/res/images/138/20210328/1616931465570/bfbe75bc-4ab5-4984-ab08-a8f492b718f1.jpeg"
     * ]
     * },
     * {
     * "messageId": 500,
     * "userId": 144,
     * "type": "3",
     * "dynamicInfoId": 642,
     * "head": "http://192.168.31.38:9000/find/img/head/144/fc3fe05b-6ca8-49fe-863c-31593879e124.jpg",
     * "content": "季婉 评论您的动态 测试一下",
     * "attacheType": "1",
     * "filenameList": [
     * "http://192.168.31.38:9000/find/res/images/138/20210328/1616931465570/bfbe75bc-4ab5-4984-ab08-a8f492b718f1.jpeg"
     * ]
     * },
     * {
     * "messageId": 497,
     * "userId": 144,
     * "type": "0",
     * "dynamicInfoId": 642,
     * "head": "http://192.168.31.38:9000/find/img/head/144/fc3fe05b-6ca8-49fe-863c-31593879e124.jpg",
     * "content": "季婉 点赞您的动态 测试一下",
     * "attacheType": "1",
     * "filenameList": [
     * "http://192.168.31.38:9000/find/res/images/138/20210328/1616931465570/bfbe75bc-4ab5-4984-ab08-a8f492b718f1.jpeg"
     * ]
     * },
     * {
     * "messageId": 494,
     * "userId": 144,
     * "type": "3",
     * "dynamicInfoId": 628,
     * "head": "http://192.168.31.38:9000/find/img/head/144/fc3fe05b-6ca8-49fe-863c-31593879e124.jpg",
     * "content": "季婉 评论您的动态 测试正常",
     * "attacheType": "1",
     * "filenameList": [
     * "http://192.168.31.38:9000/find/res/images/138/20210317/1615981448911/d338074b-d7a9-4963-9ccf-3cc4de3a9485.jpeg"
     * ]
     * },
     * {
     * "messageId": 493,
     * "userId": 144,
     * "type": "0",
     * "dynamicInfoId": 628,
     * "head": "http://192.168.31.38:9000/find/img/head/144/fc3fe05b-6ca8-49fe-863c-31593879e124.jpg",
     * "content": "季婉 点赞您的动态 测试正常",
     * "attacheType": "1",
     * "filenameList": [
     * "http://192.168.31.38:9000/find/res/images/138/20210317/1615981448911/d338074b-d7a9-4963-9ccf-3cc4de3a9485.jpeg"
     * ]
     * }
     * ]
     * }
     * }
     * @apiError (403) {Number} status 响应状态码
     * @apiError (403) {Number} code 消息码
     * @apiError (403) {String} msg 说明
     * @apiErrorExample {json} 403错误
     * HTTP/1.1 403 403响应
     * {
     * "status": 403,
     * "code": 199,
     * "msg": "未找到用户信息！"
     * }
     * @apiError (404) {Number} status 响应状态码
     * @apiError (404) {Number} code 消息码
     * @apiError (404) {String} msg 说明
     * @apiErrorExample {json} 404错误
     * HTTP/1.1 404 404响应
     * {
     * "status": 404,
     * "code": 200,
     * "msg": "接口未注册！"
     * }
     * @apiError (500) {Number} status 响应状态码
     * @apiError (500) {Number} code 消息码
     * @apiError (500) {String} msg 说明
     * @apiErrorExample {json} 500错误
     * HTTP/1.1 500 500响应
     * {
     * "status": 500,
     * "code": 205,
     * "msg": "服务器未响应！"
     * }
     */
    @GetMapping(value = "{id}/likes")
    @ApiOperation(value = "分页获取点赞消息列表接口", notes = "分页获取点赞消息列表。")
    @ApiResponses(@ApiResponse(code = 200, message = "分页获取点赞消息列表成功", response = CommonResult.class))
    CommonResult<MessageVO2> findLikes(@PathVariable(name = "id") @ApiParam(name = "id", value = "用户id", required = true, example = "1") Long userId,
                                       @RequestParam(name = "pageNum", required = false, defaultValue = "1") @ApiParam(name = "pageNum", value = "当前页码，默认：当前第1页", example = "1") Integer pageNum, // 当前页码，默认：当前第1页
                                       @RequestParam(name = "pageSize", required = false, defaultValue = "20") @ApiParam(name = "pageSize", value = "每页数量，默认：每页20条", example = "20") Integer pageSize) {// 每页数量，默认：每页20条
        return this.messageFeignClient.likes(userId, pageNum, pageSize);
    }

//    @GetMapping(value = "{id}/comments")
//    @ApiOperation(value = "分页获取评论消息列表接口", notes = "分页获取评论消息列表。")
//    @ApiResponses(@ApiResponse(code = 200, message = "分页获取点赞消息列表成功", response = CommonResult.class))
//    CommonResult<CommentsVO2> findComments(@PathVariable(name = "id") @ApiParam(name = "id", value = "用户id", required = true, example = "1") Long userId,
//                                           @RequestParam(name = "pageNum", required = false, defaultValue = "1") @ApiParam(name = "pageNum", value = "当前页码，默认：当前第1页", example = "1") Integer pageNum, // 当前页码，默认：当前第1页
//                                           @RequestParam(name = "pageSize", required = false, defaultValue = "20") @ApiParam(name = "pageSize", value = "每页数量，默认：每页20条", example = "20") Integer pageSize) {// 每页数量，默认：每页20条
//        return this.messageFeignClient.comments(userId, pageNum, pageSize);
//    }

    /**
     * @api {get} /find/message/{id1}/{id2}/messages 分页获取消息历史记录列表接口
     * @apiVersion 1.0.0
     * @apiGroup 消息模块API
     * @apiName 分页获取消息历史记录列表
     * @apiParam (接口请求参数){Number} id1 消息发送者用户id
     * @apiParam (接口请求参数){Number} id2 消息接收者用户id
     * @apiParam (接口请求参数) {Number} [pageNum=1] 当前页码
     * @apiParam (接口请求参数) {Number} [pageSize=20] 每页数量
     * @apiParamExample 请求示例
     * HTTP/1.1 OK
     * curl -v -X GET "http://w168428j19.51mypc.cn/find/message/138/139/messages?pageNum=1&pageSize=20" -H "accept: application/json"
     * @apiSuccess (200) {Number} code 信息码
     * @apiSuccess (200) {String} msg 说明
     * @apiSuccess (200) {Number} status 响应状态码
     * @apiSuccess (200) {Object} [data] 消息历史记录数据
     * @apiSuccess (200){Number} [totalCount] 消息记录总条数
     * @apiSuccess (200) {Number} [totalPage] 消息记录总页数
     * @apiSuccess (200) {Object[]} [list] 消息记录数据列表
     * @apiSuccess (200){Number} [messageId] 消息记录id
     * @apiSuccess (200){Number} [sendUserId] 消息发送者用户id
     * @apiSuccess (200) {String} [sendUserHead] 消息发送者用户头像
     * @apiSuccess (200) {String} [sendUserNickname] 消息发送者用户昵称
     * @apiSuccess (200) {String} [sendDateTime] 消息发送时间
     * @apiSuccess (200) {String} [content] 消息内容
     * @apiSuccessExample {json} 200响应示例
     * HTTP/1.1 200 OK
     * {
     * "status": 200,
     * "code": 0,
     * "msg": "返回数据成功。",
     * "data": {
     * "totalCount": 4,
     * "totalPage": 1,
     * "list": [
     * {
     * "messageId": 8,
     * "sendUserId": 139,
     * "sendUserHead": "http://192.168.31.31:9000/find/img/head/139/e2a31a97-c64d-467e-9df8-b0ed5b1cc09b.jpeg",
     * "sendUserNickname": "9527",
     * "sendDateTime": "2021年04月06日 11:50:30",
     * "content": "申请加您的微信，麻烦通过一下，谢谢！"
     * },
     * {
     * "messageId": 9,
     * "sendUserId": 138,
     * "sendUserHead": "http://192.168.31.31:9000/find/img/head/138/644406af-ebc4-4c85-b793-33e6f563d847.jpg",
     * "sendUserNickname": "阿珂",
     * "sendDateTime": "2021年04月06日 11:52:00",
     * "content": "我同意。好的，我的微信号是：wx123123212"
     * },
     * {
     * "messageId": 18,
     * "sendUserId": 139,
     * "sendUserHead": "http://192.168.31.31:9000/find/img/head/139/e2a31a97-c64d-467e-9df8-b0ed5b1cc09b.jpeg",
     * "sendUserNickname": "9527",
     * "sendDateTime": "2021年04月06日 15:24:43",
     * "content": "添加微信聊聊"
     * },
     * {
     * "messageId": 32,
     * "sendUserId": 138,
     * "sendUserHead": "http://192.168.31.31:9000/find/img/head/138/644406af-ebc4-4c85-b793-33e6f563d847.jpg",
     * "sendUserNickname": "阿珂",
     * "sendDateTime": "2021年04月06日 18:16:09",
     * "content": "好啊"
     * }
     * ]
     * }
     * }
     * @apiError (403) {Number} status 响应状态码
     * @apiError (403) {Number} code 消息码
     * @apiError (403) {String} msg 说明
     * @apiErrorExample {json} 403错误
     * HTTP/1.1 403 403响应
     * {
     * "status": 403,
     * "code": 199,
     * "msg": "未找到用户信息！"
     * }
     * @apiError (404) {Number} status 响应状态码
     * @apiError (404) {Number} code 消息码
     * @apiError (404) {String} msg 说明
     * @apiErrorExample {json} 404错误
     * HTTP/1.1 404 404响应
     * {
     * "status": 404,
     * "code": 200,
     * "msg": "接口未注册！"
     * }
     * @apiError (500) {Number} status 响应状态码
     * @apiError (500) {Number} code 消息码
     * @apiError (500) {String} msg 说明
     * @apiErrorExample {json} 500错误
     * HTTP/1.1 500 500响应
     * {
     * "status": 500,
     * "code": 205,
     * "msg": "服务器未响应！"
     * }
     */
    @GetMapping(value = "/{id1}/{id2}/messages")
    @ApiOperation(value = "分页获取消息历史记录列表接口", notes = "分页获取消息历史记录列表接口")
    public CommonResult<MessageVO3> messages(
            @PathVariable(name = "id1") @ApiParam(name = "id1", value = "消息发送者用户id", required = true, example = "1") Long sendUserId,
            @PathVariable(name = "id2") @ApiParam(name = "id2", value = "消息接收者用户id", required = true, example = "2") Long recipientUserId,
            @RequestParam(name = "pageNum", required = false, defaultValue = "1") @ApiParam(name = "pageNum", value = "当前页码", example = "1") Integer pageNum,
            @RequestParam(name = "pageSize", required = false, defaultValue = "20") @ApiParam(name = "pageSize", value = "每页数量", example = "20") Integer pageSize) {
        return this.messageFeignClient.messages(sendUserId, recipientUserId, pageNum, pageSize);
    }

    /**
     * @api {post} /find/message/{id}/send 发送消息接口
     * @apiVersion 1.0.0
     * @apiGroup 消息模块API
     * @apiName 发送消息
     * @apiParam (接口请求参数) {Number} id 发送者用户id
     * @apiParam (接口请求参数) {Number} messageId 回复的消息id
     * @apiParam (接口请求参数) {String} content 消息内容
     * @apiParamExample 请求示例 发送消息
     * curl -v -X POST "http://w168428j19.51mypc.cn/find/message/60/send?messageId=25&content=可以申请加你的微信吗？" -H "accept: application/json"
     * @apiSuccess (200) {Number} code 信息码
     * @apiSuccess (200) {String} msg 说明
     * @apiSuccess (200) {Number} status 响应状态码
     * @apiSuccess (200) {Object} [data] 发送状态数据
     * @apiSuccess (200) {String} [SEND] OK->发送成功，ERROR->发送失败
     * @apiSuccessExample {json} 200响应示例
     * HTTP/1.1 200 OK
     * {
     * "status": 200,
     * "code": 0,
     * "msg": "发送消息成功。",
     * "data": {
     * "SEND": "OK"
     * }
     * }
     * @apiParamExample 请求示例 回复消息
     * curl -v -X POST "http://w168428j19.51mypc.cn/find/message/29/send?messageId=2&content=可以申请加你的微信吗？" -H "accept: application/json"
     * @apiSuccess (200) {Number} code 信息码
     * @apiSuccess (200) {String} msg 说明
     * @apiSuccess (200) {Number} status 响应状态码
     * @apiSuccess (200) {Object} [data] 发送状态数据
     * @apiSuccess (200) {String} [SEND] OK->发送成功，ERROR->发送失败
     * @apiSuccessExample {json} 200响应示例
     * HTTP/1.1 200 OK
     * {
     * "status": 200,
     * "code": 0,
     * "msg": "发送消息成功。",
     * "data": {
     * "SEND": "OK"
     * }
     * }
     * @apiError (403) {Number} status 响应状态码
     * @apiError (403) {Number} code 消息码
     * @apiError (403) {String} msg 说明
     * @apiErrorExample {json} 403错误
     * HTTP/1.1 403 403响应
     * {
     * "status": 403,
     * "code": 199,
     * "msg": "未找到用户信息！"
     * }
     * @apiError (404) {Number} status 响应状态码
     * @apiError (404) {Number} code 消息码
     * @apiError (404) {String} msg 说明
     * @apiErrorExample {json} 404错误
     * HTTP/1.1 404 404响应
     * {
     * "status": 404,
     * "code": 200,
     * "msg": "接口未注册！"
     * }
     * @apiError (500) {Number} status 响应状态码
     * @apiError (500) {Number} code 消息码
     * @apiError (500) {String} msg 说明
     * @apiErrorExample {json} 500错误
     * HTTP/1.1 500 500响应
     * {
     * "status": 500,
     * "code": 205,
     * "msg": "服务器未响应！"
     * }
     */
    @PostMapping(value = "/{id}/send")
    @ApiOperation(value = "发送消息接口", notes = "发送消息接口")
    public CommonResult<Map<String, Object>> send(
            @PathVariable(name = "id") @ApiParam(name = "id", value = "消息发送者用户id", required = true, example = "1") Long sendUserId,
            @RequestParam(name = "messageId") @ApiParam(name = "messageId", value = "消息id", required = true, example = "3") Long messageId,
            @RequestParam(name = "content") @ApiParam(name = "content", value = "消息内容", required = true, example = "可以聊聊吗？") String content) {
        return this.messageFeignClient.send(sendUserId, messageId, content);
    }

    /**
     * @api {put} /find/message/{id}/updateAll 全部消息已读接口
     * @apiVersion 1.0.0
     * @apiGroup 消息模块API
     * @apiName 全部消息已读
     * @apiParam (接口请求参数){Number} id 消息接收者用户id
     * @apiParamExample 请求示例
     * HTTP/1.1 OK
     * curl -v -X PUT "http://w168428j19.51mypc.cn/find/message/60/updateAll" -H "accept: application/json"
     * @apiSuccess (200) {Number} code 信息码
     * @apiSuccess (200) {String} msg 说明
     * @apiSuccess (200) {Number} status 响应状态码
     * @apiSuccess (200) {Object} [data] 标记已读状态数据
     * @apiSuccess (200) {String} [UPDATE] OK->标记已读成功，ERROR->标记已读失败
     * @apiSuccessExample {json} 200响应示例
     * HTTP/1.1 200 OK
     * {
     * "status": 200,
     * "code": 0,
     * "msg": "标记已读成功。",
     * "data": {
     * "UPDATE": "OK"
     * }
     * }
     * @apiError (403) {Number} status 响应状态码
     * @apiError (403) {Number} code 消息码
     * @apiError (403) {String} msg 说明
     * @apiErrorExample {json} 403错误
     * HTTP/1.1 403 403响应
     * {
     * "status": 403,
     * "code": 199,
     * "msg": "未找到用户信息！"
     * }
     * @apiError (404) {Number} status 响应状态码
     * @apiError (404) {Number} code 消息码
     * @apiError (404) {String} msg 说明
     * @apiErrorExample {json} 404错误
     * HTTP/1.1 404 404响应
     * {
     * "status": 404,
     * "code": 200,
     * "msg": "接口未注册！"
     * }
     * @apiError (500) {Number} status 响应状态码
     * @apiError (500) {Number} code 消息码
     * @apiError (500) {String} msg 说明
     * @apiErrorExample {json} 500错误
     * HTTP/1.1 500 500响应
     * {
     * "status": 500,
     * "code": 205,
     * "msg": "服务器未响应！"
     * }
     */
    @PutMapping(value = "/{id}/updateAll")
    @ApiOperation(value = "全部消息已读接口", notes = "全部消息已读接口")
    public CommonResult<Map<String, Object>> updateAll(
            @PathVariable(name = "id") @ApiParam(name = "id", value = "消息接收者用户id", required = true, example = "1") Long recipientUserId) {
        return this.messageFeignClient.allRead(recipientUserId);
    }


    /**
     * @api {delete} /find/message/{id1}/delete 删除申请加微信消息记录接口
     * @apiVersion 1.0.0
     * @apiGroup 消息模块API
     * @apiName 删除申请加微信消息记录
     * @apiParam (接口请求参数){Number} id1 消息接收者用户id
     * @apiParam (接口请求参数){Number} id2 消息id
     * @apiParamExample 请求示例
     * curl -v -X PUT "http://w168428j19.51mypc.cn/find/message/60/delete?id2=28" -H "accept: application/json"
     * @apiSuccess (200) {Number} code 信息码
     * @apiSuccess (200) {String} msg 说明
     * @apiSuccess (200) {Number} status 响应状态码
     * @apiSuccess (200) {Object} [data] 删除消息状态数据
     * @apiSuccess (200) {String} [DELETE] OK->删除消息记录成功，ERROR->删除消息记录失败
     * @apiSuccessExample {json} 200响应示例
     * HTTP/1.1 200 OK
     * {
     * "status": 200,
     * "code": 0,
     * "msg": "删除消息记录成功。",
     * "data": {
     * "DELETE": "OK"
     * }
     * }
     * @apiError (403) {Number} status 响应状态码
     * @apiError (403) {Number} code 消息码
     * @apiError (403) {String} msg 说明
     * @apiErrorExample {json} 403错误
     * HTTP/1.1 403 403响应
     * {
     * "status": 403,
     * "code": 199,
     * "msg": "未找到用户信息！"
     * }
     * @apiError (404) {Number} status 响应状态码
     * @apiError (404) {Number} code 消息码
     * @apiError (404) {String} msg 说明
     * @apiErrorExample {json} 404错误
     * HTTP/1.1 404 404响应
     * {
     * "status": 404,
     * "code": 200,
     * "msg": "接口未注册！"
     * }
     * @apiError (500) {Number} status 响应状态码
     * @apiError (500) {Number} code 消息码
     * @apiError (500) {String} msg 说明
     * @apiErrorExample {json} 500错误
     * HTTP/1.1 500 500响应
     * {
     * "status": 500,
     * "code": 205,
     * "msg": "服务器未响应！"
     * }
     */
    @DeleteMapping(value = "/{id1}/delete")
    @ApiOperation(value = "删除申请加微信消息记录接口", notes = "删除申请加微信消息记录接口")
    public CommonResult<Map<String, Object>> deleteApplications(
            @PathVariable(name = "id1") @ApiParam(name = "id1", value = "消息接收者用户id", required = true, example = "1") Long recipientUserId,
            @RequestParam(name = "id2") @ApiParam(name = "id2", value = "消息发送者用户id", required = true, example = "2") Long sendUserId) {
        return this.messageFeignClient.delete(recipientUserId, sendUserId);
    }

    /**
     * @api {delete} /find/message/{id}/deleteLikes 删除点赞消息记录接口
     * @apiVersion 1.0.0
     * @apiGroup 消息模块API
     * @apiName 删除点赞消息记录
     * @apiParam (接口请求参数){Number} id 消息接收者用户id
     * @apiParam (接口请求参数){Number} messageId 消息记录id
     * @apiParamExample 请求示例
     * curl -v -X PUT "http://w168428j19.51mypc.cn/find/message/60/deleteLikes?messageId=28" -H "accept: application/json"
     * @apiSuccess (200) {Number} code 信息码
     * @apiSuccess (200) {String} msg 说明
     * @apiSuccess (200) {Number} status 响应状态码
     * @apiSuccess (200) {Object} [data] 删除消息状态数据
     * @apiSuccess (200) {String} [DELETE] OK->删除消息记录成功，ERROR->删除消息记录失败
     * @apiSuccessExample {json} 200响应示例
     * HTTP/1.1 200 OK
     * {
     * "status": 200,
     * "code": 0,
     * "msg": "标记已读成功。",
     * "data": {
     * "DELETE": "OK"
     * }
     * }
     * @apiError (403) {Number} status 响应状态码
     * @apiError (403) {Number} code 消息码
     * @apiError (403) {String} msg 说明
     * @apiErrorExample {json} 403错误
     * HTTP/1.1 403 403响应
     * {
     * "status": 403,
     * "code": 199,
     * "msg": "未找到用户信息！"
     * }
     * @apiError (404) {Number} status 响应状态码
     * @apiError (404) {Number} code 消息码
     * @apiError (404) {String} msg 说明
     * @apiErrorExample {json} 404错误
     * HTTP/1.1 404 404响应
     * {
     * "status": 404,
     * "code": 200,
     * "msg": "接口未注册！"
     * }
     * @apiError (500) {Number} status 响应状态码
     * @apiError (500) {Number} code 消息码
     * @apiError (500) {String} msg 说明
     * @apiErrorExample {json} 500错误
     * HTTP/1.1 500 500响应
     * {
     * "status": 500,
     * "code": 205,
     * "msg": "服务器未响应！"
     * }
     */
    @DeleteMapping(value = "/{id}/deleteLikes")
    @ApiOperation(value = "删除点赞消息记录接口", notes = "删除点赞消息记录接口")
    public CommonResult<Map<String, Object>> deleteLikes(
            @PathVariable(name = "id") @ApiParam(name = "id", value = "消息接收者用户id", required = true, example = "1") Long recipientUserId,
            @RequestParam(name = "messageId") @ApiParam(name = "messageId", value = "消息记录id", required = true, example = "2") Long messageId) {
        return this.messageFeignClient.deleteLikes(recipientUserId, messageId);
    }

    /**
     * @api {delete} /find/message/{id}/deleteComments 删除评论消息记录接口
     * @apiVersion 1.0.0
     * @apiGroup 消息模块API
     * @apiName 删除评论消息记录
     * @apiParam (接口请求参数){Number} id 消息接收者用户id
     * @apiParam (接口请求参数){Number} messageId 消息记录id
     * @apiParamExample 请求示例
     * curl -v -X PUT "http://w168428j19.51mypc.cn/find/message/60/deleteComments?messageId=28" -H "accept: application/json"
     * @apiSuccess (200) {Number} code 信息码
     * @apiSuccess (200) {String} msg 说明
     * @apiSuccess (200) {Number} status 响应状态码
     * @apiSuccess (200) {Object} [data] 删除消息状态数据
     * @apiSuccess (200) {String} [DELETE] OK->删除消息记录成功，ERROR->删除消息记录失败
     * @apiSuccessExample {json} 200响应示例
     * HTTP/1.1 200 OK
     * {
     * "status": 200,
     * "code": 0,
     * "msg": "删除消息记录成功。",
     * "data": {
     * "DELETE": "OK"
     * }
     * }
     * @apiError (403) {Number} status 响应状态码
     * @apiError (403) {Number} code 消息码
     * @apiError (403) {String} msg 说明
     * @apiErrorExample {json} 403错误
     * HTTP/1.1 403 403响应
     * {
     * "status": 403,
     * "code": 199,
     * "msg": "未找到用户信息！"
     * }
     * @apiError (404) {Number} status 响应状态码
     * @apiError (404) {Number} code 消息码
     * @apiError (404) {String} msg 说明
     * @apiErrorExample {json} 404错误
     * HTTP/1.1 404 404响应
     * {
     * "status": 404,
     * "code": 200,
     * "msg": "接口未注册！"
     * }
     * @apiError (500) {Number} status 响应状态码
     * @apiError (500) {Number} code 消息码
     * @apiError (500) {String} msg 说明
     * @apiErrorExample {json} 500错误
     * HTTP/1.1 500 500响应
     * {
     * "status": 500,
     * "code": 205,
     * "msg": "服务器未响应！"
     * }
     */
    @DeleteMapping(value = "/{id}/deleteComments")
    @ApiOperation(value = "删除评论消息记录接口", notes = "删除评论消息记录接口")
    public CommonResult<Map<String, Object>> deleteComments(
            @PathVariable(name = "id") @ApiParam(name = "id", value = "消息接收者用户id", required = true, example = "1") Long recipientUserId,
            @RequestParam(name = "messageId") @ApiParam(name = "messageId", value = "消息记录id", required = true, example = "2") Long messageId) {
        return this.messageFeignClient.deleteComments(recipientUserId, messageId);
    }

    /**
     * @api {put} /find/message/{id}/reply 回复申请加微信消息记录接口
     * @apiVersion 1.0.0
     * @apiGroup 消息模块API
     * @apiName 回复申请加微信消息记录
     * @apiParam (接口请求参数){Number} id 用户id
     * @apiParam (接口请求参数){Number} messageId 消息id
     * @apiParam (接口请求参数) {string={"0", "1"}} type 类型，0->拒绝，1->同意
     * @apiParam (接口请求参数) {String} [content] 消息内容
     * @apiParam (接口请求参数) {String} [weChatId] 微信号
     * @apiParamExample {json} 请求示例 回复申请加微信消息（拒绝）
     * HTTP/1.1 OK
     * curl -v -X PUT "http://w168428j19.51mypc.cn/find/message/138/reply?messageId=37&type=0&content=非常抱歉，我不想加你！" -H "accept: application/json"
     * @apiSuccess (200) {Number} code 信息码
     * @apiSuccess (200) {String} msg 说明
     * @apiSuccess (200) {Number} status 响应状态码
     * @apiSuccess (200) {Object} [data] 回复消息状态数据
     * @apiSuccess (200) {String} [REPLY] OK->回复成功，ERROR->回复失败
     * @apiSuccessExample {json} 200响应示例
     * HTTP/1.1 200 OK
     * {
     * "status": 200,
     * "code": 0,
     * "msg": "返回数据成功。",
     * "data": {
     * "REPLY": "OK"
     * }
     * }
     * @apiParamExample {json} 请求示例 回复申请加微信消息（同意）
     * HTTP/1.1 OK
     * curl -v -X PUT "http://w168428j19.51mypc.cn/find/144/reply?messageId=42&type=1&content=我乐意&weChatId=wx406151651a" -H "accept: application/json"
     * @apiSuccess (200) {Number} code 信息码
     * @apiSuccess (200) {String} msg 说明
     * @apiSuccess (200) {Number} status 响应状态码
     * @apiSuccess (200) {Object} [data] 回复消息状态数据
     * @apiSuccess (200) {String} [REPLY] OK->回复成功，ERROR->回复失败
     * @apiSuccessExample {json} 200响应示例
     * HTTP/1.1 200 OK
     * {
     * "status": 200,
     * "code": 0,
     * "msg": "返回数据成功。",
     * "data": {
     * "REPLY": "OK"
     * }
     * }
     * @apiError (403) {Number} status 响应状态码
     * @apiError (403) {Number} code 消息码
     * @apiError (403) {String} msg 说明
     * @apiErrorExample {json} 403错误
     * HTTP/1.1 403 403响应
     * {
     * "status": 403,
     * "code": 199,
     * "msg": "未找到用户信息！"
     * }
     * @apiError (404) {Number} status 响应状态码
     * @apiError (404) {Number} code 消息码
     * @apiError (404) {String} msg 说明
     * @apiErrorExample {json} 404错误
     * HTTP/1.1 404 404响应
     * {
     * "status": 404,
     * "code": 200,
     * "msg": "接口未注册！"
     * }
     * @apiError (500) {Number} status 响应状态码
     * @apiError (500) {Number} code 消息码
     * @apiError (500) {String} msg 说明
     * @apiErrorExample {json} 500错误
     * HTTP/1.1 500 500响应
     * {
     * "status": 500,
     * "code": 205,
     * "msg": "服务器未响应！"
     * }
     */
    @ApiOperation(value = "回复申请加微信消息记录接口", notes = "回复申请加微信消息记录接口")
    @PutMapping(value = "/{id}/reply")
    public CommonResult<Map<String, Object>> reply(@PathVariable(name = "id") @ApiParam(name = "id", value = "用户id", required = true, example = "1") Long userId,
                                                   @RequestParam(name = "messageId") @ApiParam(name = "messageId", value = "消息id", required = true, example = "2") Long messageId,
                                                   @RequestParam(name = "type") @ApiParam(name = "type", value = "类型，0->拒绝，1->同意", required = true, example = "0") String type,
                                                   @RequestParam(name = "content", required = false) @ApiParam(name = "content", value = "消息内容", example = "非常抱歉，我不想加你！") String content,
                                                   @RequestParam(name = "weChatId", required = false) @ApiParam(name = "weChatId", value = "微信号", example = "wx123456789") String weChatId) {
        return this.messageFeignClient.reply(userId, messageId, type, content, weChatId);
    }

    /**
     * @api {get} /find/message/{id}/getUnReadCount 获取未读（点赞，评论，申请加微信）消息总条数接口
     * @apiVersion 1.0.0
     * @apiGroup 消息模块API
     * @apiName 获取未读（点赞，评论，申请加微信）消息总条数
     * @apiParam (接口请求参数){Number} id 当前（消息接收者）用户id
     * @apiParamExample {json} 请求示例 回复申请加微信消息（拒绝）
     * HTTP/1.1 OK
     * curl -v -X GET "http://w168428j19.51mypc.cn/find/message/138/getUnReadCount
     * @apiSuccess (200) {Number} code 信息码
     * @apiSuccess (200) {String} msg 说明
     * @apiSuccess (200) {Number} status 响应状态码
     * @apiSuccess (200) {Object} [data] 回复消息状态数据
     * @apiSuccess (200) {Number} [unReadCount] 未读（点赞，评论，申请加微信）消息总条数
     * @apiSuccessExample {json} 200响应示例
     * HTTP/1.1 200 OK
     * {
     * "status": 200,
     * "code": 0,
     * "msg": "返回数据成功。",
     * "data": {
     * "unReadCount": 6
     * }
     * }
     * @apiError (403) {Number} status 响应状态码
     * @apiError (403) {Number} code 消息码
     * @apiError (403) {String} msg 说明
     * @apiErrorExample {json} 403错误
     * HTTP/1.1 403 403响应
     * {
     * "status": 403,
     * "code": 199,
     * "msg": "未找到用户信息！"
     * }
     * @apiError (404) {Number} status 响应状态码
     * @apiError (404) {Number} code 消息码
     * @apiError (404) {String} msg 说明
     * @apiErrorExample {json} 404错误
     * HTTP/1.1 404 404响应
     * {
     * "status": 404,
     * "code": 200,
     * "msg": "接口未注册！"
     * }
     * @apiError (500) {Number} status 响应状态码
     * @apiError (500) {Number} code 消息码
     * @apiError (500) {String} msg 说明
     * @apiErrorExample {json} 500错误
     * HTTP/1.1 500 500响应
     * {
     * "status": 500,
     * "code": 205,
     * "msg": "服务器未响应！"
     * }
     */
    @GetMapping(value = "/{id}/getUnReadCount")
    public CommonResult<Map<String, Object>> getUnReadCount(@PathVariable(name = "id") @ApiParam(name = "id", value = "消息接收者用户id", required = true, example = "1") Long userId) {
        return this.messageFeignClient.getUnReadCount(userId);
    }
}
