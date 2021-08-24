package com.potato369.find.portal.controller;

import com.potato369.find.common.api.CommonResult;
import com.potato369.find.common.vo.MessageVO;
import com.potato369.find.common.vo.MessageVO2;
import com.potato369.find.common.vo.MessageVO3;
import com.potato369.find.portal.feign.MessageService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Api(value = "message-controller", tags = "消息模块Restful API")
@RestController
@RequestMapping("/message")
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
     * @apiSuccess (200) {Object} [likes] 最新点赞消息
     * @apiSuccess (200) {String} [content1] 最新一条未读点赞消息内容
     * @apiSuccess (200) {Number} [count1] 未读点赞消息数量
     * @apiSuccess (200){Number} [totalCount] 申请加微信消息总条数
     * @apiSuccess (200) {Number} [totalPage] 申请加微信消息总页数
     * @apiSuccess (200) {Object[]} [list] 申请加微信消息数据
     * @apiSuccess (200){Number} [messageId] 申请加微信消息记录id
     * @apiSuccess (200){Number} [userId] 申请加微信发送者用户id
     * @apiSuccess (200) {String} [head] 申请加微信发送者用户头像
     * @apiSuccess (200) {String} [nickname] 申请加微信发送者用户昵称
     * @apiSuccess (200) {String} [content2] 申请加微信发送消息内容
     * @apiSuccess (200){Number} [count2] 申请加微信未读消息数量
     * @apiSuccess (200) {String} [createTime] 消息发送时间
     * @apiSuccess (200) {String} [type] 消息类型，0->普通消息，1->申请加微信消息
     * @apiSuccess (200) {Number} [flag] 是否展示复制微信，0->否，1->是
     * @apiSuccess (200) {String} [weixinId] 微信号
     * @apiSuccess (200) {Boolean} [isOrNotApplication] 是否是申请者，true->是，false->否
     * @apiSuccessExample {json} 200响应示例
     * HTTP/1.1 200 OK
     * {
     * "status":200,
     * "code":0,
     * "msg":"返回数据成功",
     * "data":{
     * "likes":{
     * "content1":"阿萌赞了你的动态差点就掉下去了！",
     * "count1":5
     * },
     * "totalCount":5,
     * "totalPage":1,
     * "list":[
     * {
     * "messageId": 7,
     * "userId":60,
     * "head":"http://192.168.31.31:9000/find/img/head/60/01.png",
     * "nickname":"尘埃",
     * "content2":"需要加您的微信?",
     * "count2":5,
     * "createTime": "2021年04月22日 16:40:40",
     * "type":1,
     * "flag":0,
     * "isOrNotApplication": false
     * },
     * {
     * "messageId": 2,
     * "userId":62,
     * "head":"http://192.168.31.31:9000/find/img/head/62/02.png",
     * "nickname":"蓝梧桐",
     * "content2":"需要加您的微信?",
     * "count2":5,
     * "createTime": "2021年04月22日 16:40:40",
     * "type":1,
     * "flag":0,
     * "isOrNotApplication": false
     * },
     * {
     * "messageId": 3,
     * "userId":61,
     * "head":"http://192.168.31.31:9000/find/img/head/61/01.png",
     * "nickname":"长安",
     * "content2":"需要加您的微信?",
     * "count2":6
     * "createTime": "2021年04月22日 16:40:40",
     * "type":1,
     * "flag":0,
     * "isOrNotApplication": false
     * },
     * {
     * "messageId": 4,
     * "userId":71,
     * "head":"http://192.168.31.31:9000/find/img/head/71/07.png",
     * "nickname":"弦雨晴",
     * "content2":"需要加您的微信?",
     * "count2":6
     * "createTime": "2021年04月22日 16:40:40",
     * "type":1,
     * "flag":0,
     * "isOrNotApplication": false
     * },
     * {
     * "messageId": 5,
     * "userId":70,
     * "head":"http://192.168.31.31:9000/find/img/head/70/03.png",
     * "nickname":"阿萌",
     * "content2":"需要加您的微信?",
     * "createTime": "2021年04月22日 16:40:40",
     * "count2":1
     * "type":1,
     * "flag":0,
     * "isOrNotApplication": false
     * },
     * {
     * "messageId": 86,
     * "userId": 137,
     * "head": "http://192.168.31.31:9000/find/img/head/137/34ca77aa-b3e2-4358-b7cf-0acb172121db.jpeg",
     * "nickname": "jack",
     * "content2": "已同意添加微信，我的微信号是：",
     * "createTime": "2021年04月22日 16:40:40",
     * "count2": 2,
     * "type": "1",
     * "flag": 1,
     * "weixinId": "wxnaza12345681",
     * "isOrNotApplication": false
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
    @GetMapping(value = "/{id}/all")
    @ApiOperation(value = "分页获取消息界面点赞和申请加微信消息列表接口", notes = "用于用户分页获取消息界面点赞和申请加微信消息列表。")
    @ApiResponses(@ApiResponse(code = 200, message = "分页获取消息界面用户最近一条点赞和申请加微信消息列表成功", response = CommonResult.class))
    CommonResult<MessageVO> findAll(@PathVariable(name = "id") @ApiParam(name = "id", value = "用户id", required = true, example = "1") Long userId,
                                    @RequestParam(name = "pageNum", required = false, defaultValue = "1") @ApiParam(name = "pageNum", value = "当前页码，默认：当前第1页", example = "1") Integer pageNum, // 当前页码，默认：当前第1页
                                    @RequestParam(name = "pageSize", required = false, defaultValue = "20") @ApiParam(name = "pageSize", value = "每页数量，默认：每页20条", example = "20") Integer pageSize) {// 每页数量，默认：每页20条
        return this.messageFeignClient.all(userId, pageNum, pageSize);
    }

    /**
     * @api {get} /find/message/{id}/likes 分页获取点赞消息列表接口
     * @apiVersion 1.0.0
     * @apiGroup 消息模块API
     * @apiName 分页获取点赞消息列表
     * @apiParam (接口请求参数){Number} id 消息接收者用户id
     * @apiParam (接口请求参数) {Number} [pageNum=1] 当前页码
     * @apiParam (接口请求参数) {Number} [pageSize=20] 每页数量
     * @apiParamExample 请求示例
     * curl -v -X GET "http://w168428j19.51mypc.cn/find/message/29/likes?pageNum=1&pageSize=20" -H "accept: application/json"
     * @apiSuccess (200) {Number} code 信息码
     * @apiSuccess (200) {String} msg 说明
     * @apiSuccess (200) {Number} status 响应状态码
     * @apiSuccess (200) {Object} [data] 消息数据
     * @apiSuccess (200){Number} [totalCount] 点赞消息总条数
     * @apiSuccess (200) {Number} [totalPage] 点赞消息总页数
     * @apiSuccess (200) {Object[]} [list] 点赞消息数据
     * @apiSuccess (200){Number} [userId] 点赞者用户id
     * @apiSuccess (200){Number} [messageId] 点赞消息记录id
     * @apiSuccess (200){Number} [dynamicInfoId] 点赞的动态内容id
     * @apiSuccess (200) {String} [head] 点赞者用户头像
     * @apiSuccess (200) {String} [content] 点赞者发送消息内容
     * @apiSuccess (200) {String} [attacheType] 点赞的动态内容类型，0->图片，1->语音
     * @apiSuccess (200) {String[]} [filenameList] 点赞的动态文件名称列表
     * @apiSuccessExample {json} 200响应示例
     * HTTP/1.1 200 OK
     * {
     * "status":200,
     * "code":0,
     * "msg":"返回数据成功",
     * "data":{
     * "totalCount":30,
     * "totalPage":2,
     * "list":[
     * {
     * "messageId":90,
     * "dynamicInfoId":87,
     * "userId":70,
     * "head":"http://192.168.31.31:9000/find/img/head/70/03.png",
     * "content":"阿萌赞了你的动态差点就掉下去了！",
     * "attacheType":"0",
     * "filenameList":[
     * "http://192.168.31.31:9000/find/res/images/28/20200611/03.png"
     * ]
     * },
     * {
     * "messageId":89,
     * "dynamicInfoId":187,
     * "userId":70,
     * "head":"http://192.168.31.31:9000/find/img/head/70/03.png",
     * "content":"阿萌赞了你的动态差点就掉下去了！",
     * "attacheType":"0",
     * "filenameList":[
     * "http://192.168.31.31:9000/find/res/images/29/20200427/014.png"
     * ]
     * },
     * {
     * "messageId":88,
     * "dynamicInfoId":37,
     * "userId":70,
     * "head":"http://192.168.31.31:9000/find/img/head/70/03.png",
     * "content":"阿萌赞了你的动态差点就掉下去了！",
     * "attacheType":"0",
     * "filenameList":[
     * "http://192.168.31.31:9000/find/res/images/29/20200502/07.png",
     * "http://192.168.31.31:9000/find/res/images/29/20200502/09.png"
     * ]
     * },
     * {
     * "messageId":87,
     * "dynamicInfoId":57,
     * "userId":70,
     * "head":"http://192.168.31.31:9000/find/img/head/70/03.png",
     * "content":"阿萌赞了你的动态差点就掉下去了！",
     * "attacheType":"0",
     * "filenameList":[
     * "http://192.168.31.31:9000/find/res/images/29/20200503/03.png",
     * "http://192.168.31.31:9000/find/res/images/29/20200503/05.png",
     * "http://192.168.31.31:9000/find/res/images/29/20200503/08.png"
     * ]
     * },
     * {
     * "messageId":86,
     * "dynamicInfoId":287,
     * "userId":70,
     * "head":"http://192.168.31.31:9000/find/img/head/70/03.png",
     * "content":"阿萌赞了你的动态差点就掉下去了！",
     * "attacheType":"0",
     * "filenameList":[
     * "http://192.168.31.31:9000/find/res/images/29/20200505/12.png",
     * "http://192.168.31.31:9000/find/res/images/29/20200505/13.png",
     * "http://192.168.31.31:9000/find/res/images/29/20200505/15.png"
     * ]
     * },
     * {
     * "messageId":85,
     * "dynamicInfoId":88,
     * "userId":70,
     * "head":"http://192.168.31.31:9000/find/img/head/70/03.png",
     * "content":"阿萌赞了你的动态差点就掉下去了！",
     * "attacheType":"0",
     * "filenameList":[
     * "http://192.168.31.31:9000/find/res/images/29/20200507/04.png"
     * ]
     * },
     * {
     * "messageId":84,
     * "dynamicInfoId":37,
     * "userId":70,
     * "head":"http://192.168.31.31:9000/find/img/head/70/03.png",
     * "content":"阿萌赞了你的动态这组我比较喜欢",
     * "attacheType":"0",
     * "filenameList":[
     * "http://192.168.31.31:9000/find/res/images/28/20200611/03.png"
     * ]
     * },
     * {
     * "messageId":83,
     * "dynamicInfoId":47,
     * "userId":70,
     * "head":"http://192.168.31.31:9000/find/img/head/70/03.png",
     * "content":"阿萌赞了你的动态这组我比较喜欢",
     * "attacheType":"0",
     * "filenameList":[
     * "http://192.168.31.31:9000/find/res/images/29/20200427/014.png"
     * ]
     * },
     * {
     * "messageId":82,
     * "dynamicInfoId":67,
     * "userId":70,
     * "head":"http://192.168.31.31:9000/find/img/head/70/03.png",
     * "content":"阿萌赞了你的动态这组我比较喜欢",
     * "attacheType":"0",
     * "filenameList":[
     * "http://192.168.31.31:9000/find/res/images/29/20200502/07.png",
     * "http://192.168.31.31:9000/find/res/images/29/20200502/09.png"
     * ]
     * },
     * {
     * "messageId":81,
     * "dynamicInfoId":77,
     * "userId":70,
     * "head":"http://192.168.31.31:9000/find/img/head/70/03.png",
     * "content":"阿萌赞了你的动态这组我比较喜欢",
     * "attacheType":"0",
     * "filenameList":[
     * "http://192.168.31.31:9000/find/res/images/29/20200503/03.png",
     * "http://192.168.31.31:9000/find/res/images/29/20200503/05.png",
     * "http://192.168.31.31:9000/find/res/images/29/20200503/08.png"
     * ]
     * },
     * {
     * "messageId":80,
     * "dynamicInfoId":67,
     * "userId":70,
     * "head":"http://192.168.31.31:9000/find/img/head/70/03.png",
     * "content":"阿萌赞了你的动态这组我比较喜欢",
     * "attacheType":"0",
     * "filenameList":[
     * "http://192.168.31.31:9000/find/res/images/29/20200505/12.png",
     * "http://192.168.31.31:9000/find/res/images/29/20200505/13.png",
     * "http://192.168.31.31:9000/find/res/images/29/20200505/15.png"
     * ]
     * },
     * {
     * "messageId":79,
     * "dynamicInfoId":57,
     * "userId":70,
     * "head":"http://192.168.31.31:9000/find/img/head/70/03.png",
     * "content":"阿萌赞了你的动态这组我比较喜欢",
     * "attacheType":"0",
     * "filenameList":[
     * "http://192.168.31.31:9000/find/res/images/29/20200507/04.png"
     * ]
     * },
     * {
     * "messageId":78,
     * "dynamicInfoId":17,
     * "userId":70,
     * "head":"http://192.168.31.31:9000/find/img/head/70/03.png",
     * "content":"阿萌赞了你的动态51出门熏人",
     * "attacheType":"0",
     * "filenameList":[
     * "http://192.168.31.31:9000/find/res/images/28/20200611/03.png"
     * ]
     * },
     * {
     * "messageId":77,
     * "dynamicInfoId":87,
     * "userId":70,
     * "head":"http://192.168.31.31:9000/find/img/head/70/03.png",
     * "content":"阿萌赞了你的动态51出门熏人",
     * "attacheType":"0",
     * "filenameList":[
     * "http://192.168.31.31:9000/find/res/images/29/20200427/014.png"
     * ]
     * },
     * {
     * "messageId":76,
     * "dynamicInfoId":87,
     * "userId":70,
     * "head":"http://192.168.31.31:9000/find/img/head/70/03.png",
     * "content":"阿萌赞了你的动态51出门熏人",
     * "attacheType":"0",
     * "filenameList":[
     * "http://192.168.31.31:9000/find/res/images/29/20200502/07.png",
     * "http://192.168.31.31:9000/find/res/images/29/20200502/09.png"
     * ]
     * },
     * {
     * "messageId":75,
     * "dynamicInfoId":87,
     * "userId":70,
     * "head":"http://192.168.31.31:9000/find/img/head/70/03.png",
     * "content":"阿萌赞了你的动态51出门熏人",
     * "attacheType":"0",
     * "filenameList":[
     * "http://192.168.31.31:9000/find/res/images/29/20200503/03.png",
     * "http://192.168.31.31:9000/find/res/images/29/20200503/05.png",
     * "http://192.168.31.31:9000/find/res/images/29/20200503/08.png"
     * ]
     * },
     * {
     * "messageId":74,
     * "dynamicInfoId":87,
     * "userId":70,
     * "head":"http://192.168.31.31:9000/find/img/head/70/03.png",
     * "content":"阿萌赞了你的动态51出门熏人",
     * "attacheType":"0",
     * "filenameList":[
     * "http://192.168.31.31:9000/find/res/images/29/20200505/12.png",
     * "http://192.168.31.31:9000/find/res/images/29/20200505/13.png",
     * "http://192.168.31.31:9000/find/res/images/29/20200505/15.png"
     * ]
     * },
     * {
     * "messageId":73,
     * "dynamicInfoId":87,
     * "userId":70,
     * "head":"http://192.168.31.31:9000/find/img/head/70/03.png",
     * "content":"阿萌赞了你的动态51出门熏人",
     * "attacheType":"0",
     * "filenameList":[
     * "http://192.168.31.31:9000/find/res/images/29/20200507/04.png"
     * ]
     * },
     * {
     * "messageId":72,
     * "dynamicInfoId":87,
     * "userId":70,
     * "head":"http://192.168.31.31:9000/find/img/head/70/03.png",
     * "content":"阿萌赞了你的动态摩天轮旋转",
     * "attacheType":"0",
     * "filenameList":[
     * "http://192.168.31.31:9000/find/res/images/28/20200611/03.png"
     * ]
     * },
     * {
     * "messageId":71,
     * "dynamicInfoId":87,
     * "userId":70,
     * "head":"http://192.168.31.31:9000/find/img/head/70/03.png",
     * "content":"阿萌赞了你的动态摩天轮旋转",
     * "attacheType":"0",
     * "filenameList":[
     * "http://192.168.31.31:9000/find/res/images/29/20200427/014.png"
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
}
