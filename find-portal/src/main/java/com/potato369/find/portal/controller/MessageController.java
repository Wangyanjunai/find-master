package com.potato369.find.portal.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.potato369.find.common.api.CommonResult;
import com.potato369.find.common.vo.MessageVO;
import com.potato369.find.common.vo.MessageVO2;
import com.potato369.find.common.vo.MessageVO3;
import com.potato369.find.portal.feign.MessageService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

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
     * @api {get} http://8.135.36.45:8084/find/message/{id}/all 分页获取消息界面点赞和申请加微信消息列表接口
     * @apiVersion 1.0.0
     * @apiGroup 消息模块API
     * @apiName 分页获取消息界面点赞和申请加微信消息列表
     * @apiParam (接口请求参数) {long} id 消息接收者用户id
     * @apiParam (接口请求参数) {int} [pageNum] 当前页码，默认：1
     * @apiParam (接口请求参数) {int} [pageSize] 每页数量，默认：20
     * @apiParamExample {json} 请求示例
     * HTTP/1.1 OK
     * curl -v -X GET "http://8.135.36.45:8084/find/message/29/all?pageNum=1&pageSize=20" -H "accept: application/json"
     * @apiSuccess (200) {long{0-500}} code 信息码
     * @apiSuccess (200) {string{..255}} msg 说明
     * @apiSuccess (200) {int{0-65535}} status 响应状态码
     * @apiSuccess (200) {object} [data] 消息数据
     * @apiSuccess (200) {object} [likes] 点赞消息数据
     * @apiSuccess (200) {string} [head] 最新点赞者用户头像
     * @apiSuccess (200) {string} [content] 最新点赞消息内容
     * @apiSuccess (200) {int} [count] 未读点赞消息总条数
     * @apiSuccess (200) {long} [totalCount] 申请加微信消息总条数
     * @apiSuccess (200) {int} [totalPage] 申请加微信消息总页数
     * @apiSuccess (200) {object[]} [list] 申请加微信消息数据
     * @apiSuccess (200) {long} [messageId] 申请加微信消息记录id
     * @apiSuccess (200) {long} [userId] 申请加微信发送者用户id
     * @apiSuccess (200) {string} [head] 申请加微信发送者用户头像
     * @apiSuccess (200) {string} [nickname] 申请加微信发送者用户昵称
     * @apiSuccess (200) {string} [content] 申请加微信发送消息内容
     * @apiSuccess (200) {long} [count] 申请加微信未读消息数量
     * @apiSuccessExample {json} 200响应示例
     * HTTP/1.1 200 OK
		{
		    "status":200,
		    "code":0,
		    "msg":"返回数据成功",
		    "data":{
		        "likes":{
		            "content":"阿萌赞了你的动态差点就掉下去了！",
		            "count":5
		        },
		        "totalCount":5,
		        "totalPage":1,
		        "list":[
		            {
		                "messageId": 7,
		                "userId":60,
		                "head":"http://8.135.36.45:8000/find/img/head/60/01.png",
		                "nickname":"尘埃",
		                "content":"需要加您的微信?",
		                "count":5
		            },
		            {
		                "messageId": 2,
		                "userId":62,
		                "head":"http://8.135.36.45:8000/find/img/head/62/02.png",
		                "nickname":"蓝梧桐",
		                "content":"需要加您的微信?",
		                "count":5
		            },
		            {
		                "messageId": 3,
		                "userId":61,
		                "head":"http://8.135.36.45:8000/find/img/head/61/01.png",
		                "nickname":"长安",
		                "content":"需要加您的微信?",
		                "count":6
		            },
		            {
		                "messageId": 4,
		                "userId":71,
		                "head":"http://8.135.36.45:8000/find/img/head/71/07.png",
		                "nickname":"弦雨晴",
		                "content":"需要加您的微信?",
		                "count":6
		            },
		            {
		                "messageId": 5,
		                "userId":70,
		                "head":"http://8.135.36.45:8000/find/img/head/70/03.png",
		                "nickname":"阿萌",
		                "content":"需要加您的微信?",
		                "count":1
		            }
		        ]
		    }
		}
     * @apiError (403) {int{0-65535}} status 响应状态码
     * @apiError (403) {long{0-500}} code 消息码
     * @apiError (403) {String} msg 说明
     * @apiErrorExample {json} 403错误
     * HTTP/1.1 403 403响应
      {
      	"status": 403,
      	"code": 199,
      	"msg": "未找到用户信息！"
      }
     * @apiError (404) {int{0-65535}} status 响应状态码
     * @apiError (404) {long{0-500}} code 消息码
     * @apiError (404) {String} msg 说明
     * @apiErrorExample {json} 404错误
     * HTTP/1.1 404 404响应
      { 
      	"status": 404,
      	"code": 200,
      	"msg": "接口未注册！"
      }
     * @apiError (500) {int{0-65535}} status 响应状态码
     * @apiError (500) {long{0-500}} code 消息码
     * @apiError (500) {String} msg 说明
     * @apiErrorExample {json} 500错误
     * HTTP/1.1 500 500响应
      {
      	"status": 500,
      	"code": 205,
      	"msg": "服务器未响应！"
      }
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
     * @api {get} http://8.135.36.45:8084/find/message/{id}/likes 分页获取点赞消息列表接口
     * @apiVersion 1.0.0
     * @apiGroup 消息模块API
     * @apiName 分页获取点赞消息列表
     * @apiParam (接口请求参数) {long} id 消息接收者用户id
     * @apiParam (接口请求参数) {int} [pageNum] 当前页码，默认：1
     * @apiParam (接口请求参数) {int} [pageSize] 每页数量，默认：20
     * @apiParamExample {json} 请求示例
     * HTTP/1.1 OK
     * curl -v -X GET "http://8.135.36.45:8084/find/message/29/likes?pageNum=1&pageSize=20" -H "accept: application/json"
     * @apiSuccess (200) {long{0-500}} code 信息码
     * @apiSuccess (200) {string{..255}} msg 说明
     * @apiSuccess (200) {int{0-65535}} status 响应状态码
     * @apiSuccess (200) {object} [data] 消息数据
     * @apiSuccess (200) {long} [totalCount] 未读点赞消息总条数
     * @apiSuccess (200) {int} [totalPage] 点赞消息总页数
     * @apiSuccess (200) {object[]} [list] 点赞消息数据
     * @apiSuccess (200) {long} [userId] 点赞者用户id
     * @apiSuccess (200) {string} [head] 点赞者用户头像
     * @apiSuccess (200) {string} [content] 点赞者发送消息内容
     * @apiSuccess (200) {string} [attacheType] 点赞的动态内容类型，0->图片，1->语音
     * @apiSuccess (200) {string[]} [filenameList] 点赞的动态文件名称列表
     * @apiSuccessExample {json} 200响应示例
     * HTTP/1.1 200 OK
		{
		    "status":200,
		    "code":0,
		    "msg":"返回数据成功",
		    "data":{
		        "totalCount":30,
		        "totalPage":2,
		        "list":[
		            {
		                "userId":70,
		                "head":"http://8.135.36.45:8000/find/img/head/70/03.png",
		                "content":"阿萌赞了你的动态差点就掉下去了！",
		                "attacheType":"0",
		                "filenameList":[
		                    "http://8.135.36.45:8000/find/res/images/28/20200611/03.png"
		                ]
		            },
		            {
		                "userId":70,
		                "head":"http://8.135.36.45:8000/find/img/head/70/03.png",
		                "content":"阿萌赞了你的动态差点就掉下去了！",
		                "attacheType":"0",
		                "filenameList":[
		                    "http://8.135.36.45:8000/find/res/images/29/20200427/014.png"
		                ]
		            },
		            {
		                "userId":70,
		                "head":"http://8.135.36.45:8000/find/img/head/70/03.png",
		                "content":"阿萌赞了你的动态差点就掉下去了！",
		                "attacheType":"0",
		                "filenameList":[
		                    "http://8.135.36.45:8000/find/res/images/29/20200502/07.png",
		                    "http://8.135.36.45:8000/find/res/images/29/20200502/09.png"
		                ]
		            },
		            {
		                "userId":70,
		                "head":"http://8.135.36.45:8000/find/img/head/70/03.png",
		                "content":"阿萌赞了你的动态差点就掉下去了！",
		                "attacheType":"0",
		                "filenameList":[
		                    "http://8.135.36.45:8000/find/res/images/29/20200503/03.png",
		                    "http://8.135.36.45:8000/find/res/images/29/20200503/05.png",
		                    "http://8.135.36.45:8000/find/res/images/29/20200503/08.png"
		                ]
		            },
		            {
		                "userId":70,
		                "head":"http://8.135.36.45:8000/find/img/head/70/03.png",
		                "content":"阿萌赞了你的动态差点就掉下去了！",
		                "attacheType":"0",
		                "filenameList":[
		                    "http://8.135.36.45:8000/find/res/images/29/20200505/12.png",
		                    "http://8.135.36.45:8000/find/res/images/29/20200505/13.png",
		                    "http://8.135.36.45:8000/find/res/images/29/20200505/15.png"
		                ]
		            },
		            {
		                "userId":70,
		                "head":"http://8.135.36.45:8000/find/img/head/70/03.png",
		                "content":"阿萌赞了你的动态差点就掉下去了！",
		                "attacheType":"0",
		                "filenameList":[
		                    "http://8.135.36.45:8000/find/res/images/29/20200507/04.png"
		                ]
		            },
		            {
		                "userId":70,
		                "head":"http://8.135.36.45:8000/find/img/head/70/03.png",
		                "content":"阿萌赞了你的动态这组我比较喜欢",
		                "attacheType":"0",
		                "filenameList":[
		                    "http://8.135.36.45:8000/find/res/images/28/20200611/03.png"
		                ]
		            },
		            {
		                "userId":70,
		                "head":"http://8.135.36.45:8000/find/img/head/70/03.png",
		                "content":"阿萌赞了你的动态这组我比较喜欢",
		                "attacheType":"0",
		                "filenameList":[
		                    "http://8.135.36.45:8000/find/res/images/29/20200427/014.png"
		                ]
		            },
		            {
		                "userId":70,
		                "head":"http://8.135.36.45:8000/find/img/head/70/03.png",
		                "content":"阿萌赞了你的动态这组我比较喜欢",
		                "attacheType":"0",
		                "filenameList":[
		                    "http://8.135.36.45:8000/find/res/images/29/20200502/07.png",
		                    "http://8.135.36.45:8000/find/res/images/29/20200502/09.png"
		                ]
		            },
		            {
		                "userId":70,
		                "head":"http://8.135.36.45:8000/find/img/head/70/03.png",
		                "content":"阿萌赞了你的动态这组我比较喜欢",
		                "attacheType":"0",
		                "filenameList":[
		                    "http://8.135.36.45:8000/find/res/images/29/20200503/03.png",
		                    "http://8.135.36.45:8000/find/res/images/29/20200503/05.png",
		                    "http://8.135.36.45:8000/find/res/images/29/20200503/08.png"
		                ]
		            },
		            {
		                "userId":70,
		                "head":"http://8.135.36.45:8000/find/img/head/70/03.png",
		                "content":"阿萌赞了你的动态这组我比较喜欢",
		                "attacheType":"0",
		                "filenameList":[
		                    "http://8.135.36.45:8000/find/res/images/29/20200505/12.png",
		                    "http://8.135.36.45:8000/find/res/images/29/20200505/13.png",
		                    "http://8.135.36.45:8000/find/res/images/29/20200505/15.png"
		                ]
		            },
		            {
		                "userId":70,
		                "head":"http://8.135.36.45:8000/find/img/head/70/03.png",
		                "content":"阿萌赞了你的动态这组我比较喜欢",
		                "attacheType":"0",
		                "filenameList":[
		                    "http://8.135.36.45:8000/find/res/images/29/20200507/04.png"
		                ]
		            },
		            {
		                "userId":70,
		                "head":"http://8.135.36.45:8000/find/img/head/70/03.png",
		                "content":"阿萌赞了你的动态51出门熏人",
		                "attacheType":"0",
		                "filenameList":[
		                    "http://8.135.36.45:8000/find/res/images/28/20200611/03.png"
		                ]
		            },
		            {
		                "userId":70,
		                "head":"http://8.135.36.45:8000/find/img/head/70/03.png",
		                "content":"阿萌赞了你的动态51出门熏人",
		                "attacheType":"0",
		                "filenameList":[
		                    "http://8.135.36.45:8000/find/res/images/29/20200427/014.png"
		                ]
		            },
		            {
		                "userId":70,
		                "head":"http://8.135.36.45:8000/find/img/head/70/03.png",
		                "content":"阿萌赞了你的动态51出门熏人",
		                "attacheType":"0",
		                "filenameList":[
		                    "http://8.135.36.45:8000/find/res/images/29/20200502/07.png",
		                    "http://8.135.36.45:8000/find/res/images/29/20200502/09.png"
		                ]
		            },
		            {
		                "userId":70,
		                "head":"http://8.135.36.45:8000/find/img/head/70/03.png",
		                "content":"阿萌赞了你的动态51出门熏人",
		                "attacheType":"0",
		                "filenameList":[
		                    "http://8.135.36.45:8000/find/res/images/29/20200503/03.png",
		                    "http://8.135.36.45:8000/find/res/images/29/20200503/05.png",
		                    "http://8.135.36.45:8000/find/res/images/29/20200503/08.png"
		                ]
		            },
		            {
		                "userId":70,
		                "head":"http://8.135.36.45:8000/find/img/head/70/03.png",
		                "content":"阿萌赞了你的动态51出门熏人",
		                "attacheType":"0",
		                "filenameList":[
		                    "http://8.135.36.45:8000/find/res/images/29/20200505/12.png",
		                    "http://8.135.36.45:8000/find/res/images/29/20200505/13.png",
		                    "http://8.135.36.45:8000/find/res/images/29/20200505/15.png"
		                ]
		            },
		            {
		                "userId":70,
		                "head":"http://8.135.36.45:8000/find/img/head/70/03.png",
		                "content":"阿萌赞了你的动态51出门熏人",
		                "attacheType":"0",
		                "filenameList":[
		                    "http://8.135.36.45:8000/find/res/images/29/20200507/04.png"
		                ]
		            },
		            {
		                "userId":70,
		                "head":"http://8.135.36.45:8000/find/img/head/70/03.png",
		                "content":"阿萌赞了你的动态摩天轮旋转",
		                "attacheType":"0",
		                "filenameList":[
		                    "http://8.135.36.45:8000/find/res/images/28/20200611/03.png"
		                ]
		            },
		            {
		                "userId":70,
		                "head":"http://8.135.36.45:8000/find/img/head/70/03.png",
		                "content":"阿萌赞了你的动态摩天轮旋转",
		                "attacheType":"0",
		                "filenameList":[
		                    "http://8.135.36.45:8000/find/res/images/29/20200427/014.png"
		                ]
		            }
		        ]
		    }
		}
     * @apiError (403) {int{0-65535}} status 响应状态码
     * @apiError (403) {long{0-500}} code 消息码
     * @apiError (403) {String} msg 说明
     * @apiErrorExample {json} 403错误
     * HTTP/1.1 403 403响应
      {
      	"status": 403,
      	"code": 199,
      	"msg": "未找到用户信息！"
      }
     * @apiError (404) {int{0-65535}} status 响应状态码
     * @apiError (404) {long{0-500}} code 消息码
     * @apiError (404) {String} msg 说明
     * @apiErrorExample {json} 404错误
     * HTTP/1.1 404 404响应
      {
      	"status": 404,
      	"code": 200,
      	"msg": "接口未注册！"
      }
     * @apiError (500) {int{0-65535}} status 响应状态码
     * @apiError (500) {long{0-500}} code 消息码
     * @apiError (500) {String} msg 说明
     * @apiErrorExample {json} 500错误
     * HTTP/1.1 500 500响应
      {
      	"status": 500,
      	"code": 205,
      	"msg": "服务器未响应！"
      }
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
     * @api {get} http://8.135.36.45:8084/find/message/{id1}/{id2}/messages 分页获取消息历史记录列表接口
     * @apiVersion 1.0.0
     * @apiGroup 消息模块API
     * @apiName 分页获取消息历史记录列表
     * @apiParam (接口请求参数) {long} id1 消息发送者用户id
     * @apiParam (接口请求参数) {long} id2 消息接收者用户id
     * @apiParam (接口请求参数) {int} [pageNum] 当前页码，默认：1
     * @apiParam (接口请求参数) {int} [pageSize] 每页数量，默认：20
     * @apiParamExample {json} 请求示例
     * HTTP/1.1 OK
     * curl -v -X GET "http://8.135.36.45:8084/find/message/60/29/messages?pageNum=1&pageSize=20" -H "accept: application/json"
     * @apiSuccess (200) {long{0-500}} code 信息码
     * @apiSuccess (200) {string{..255}} msg 说明
     * @apiSuccess (200) {int{0-65535}} status 响应状态码
     * @apiSuccess (200) {object} [data] 消息历史记录数据
     * @apiSuccess (200) {long} [totalCount] 消息历史记录总条数
     * @apiSuccess (200) {int} [totalPage] 消息历史记录总页数
     * @apiSuccess (200) {object[]} [list] 消息历史记录数据列表
     * @apiSuccess (200) {long} [sendUserId] 消息发送者用户id
     * @apiSuccess (200) {string} [sendUserHead] 消息发送者用户头像
     * @apiSuccess (200) {string} [sendUserNickname] 消息发送者用户昵称
     * @apiSuccess (200) {long} [recipientUserId] 消息发送者用户id
     * @apiSuccess (200) {string} [recipientUserHead] 消息发送者用户头像
     * @apiSuccess (200) {string} [recipientUserNickname] 消息发送者用户昵称
     * @apiSuccess (200) {string} [sendDateTime] 消息发送时间
     * @apiSuccess (200) {string} [content] 消息内容
     * @apiSuccessExample {json} 200响应示例
     * HTTP/1.1 200 OK
		{
		"status": 200,
		"code": 0,
		"msg": "返回数据成功",
		"data": {
			"totalCount": 5,
			"totalPage": 1,
			"list": [
				{
					"sendUserId": 60,
					"sendUserHead": "http://8.135.36.45:8000/find/img/head/60/01.png",
					"sendUserNickname": "尘埃",
					"recipientUserId": 29,
					"recipientUserHead": "http://8.135.36.45:8000/find/img/head/29/014.png",
					"recipientUserNickname": "深兰",
					"sendDateTime": "2021年03月16日 11:13:04",
					"content": "需要加您的微信?"
				},
				{
					"sendUserId": 60,
					"sendUserHead": "http://8.135.36.45:8000/find/img/head/60/01.png",
					"sendUserNickname": "尘埃",
					"recipientUserId": 29,
					"recipientUserHead": "http://8.135.36.45:8000/find/img/head/29/014.png",
					"recipientUserNickname": "深兰",
					"sendDateTime": "2021年03月16日 11:12:55",
					"content": "需要加您的微信?"
				},
				{
					"sendUserId": 60,
					"sendUserHead": "http://8.135.36.45:8000/find/img/head/60/01.png",
					"sendUserNickname": "尘埃",
					"recipientUserId": 29,
					"recipientUserHead": "http://8.135.36.45:8000/find/img/head/29/014.png",
					"recipientUserNickname": "深兰",
					"sendDateTime": "2021年03月16日 11:12:48",
					"content": "需要加您的微信?"
				},
				{
					"sendUserId": 60,
					"sendUserHead": "http://8.135.36.45:8000/find/img/head/60/01.png",
					"sendUserNickname": "尘埃",
					"recipientUserId": 29,
					"recipientUserHead": "http://8.135.36.45:8000/find/img/head/29/014.png",
					"recipientUserNickname": "深兰",
					"sendDateTime": "2021年03月16日 11:12:29",
					"content": "需要加您的微信?"
				},
				{
					"sendUserId": 60,
					"sendUserHead": "http://8.135.36.45:8000/find/img/head/60/01.png",
					"sendUserNickname": "尘埃",
					"recipientUserId": 29,
					"recipientUserHead": "http://8.135.36.45:8000/find/img/head/29/014.png",
					"recipientUserNickname": "深兰",
					"sendDateTime": "2021年03月16日 10:55:39",
					"content": "需要加您的微信?"
				}
			  ]
			}
		}
     * @apiError (403) {int{0-65535}} status 响应状态码
     * @apiError (403) {long{0-500}} code 消息码
     * @apiError (403) {String} msg 说明
     * @apiErrorExample {json} 403错误
     * HTTP/1.1 403 403响应
      {
      	"status": 403,
      	"code": 199,
      	"msg": "未找到用户信息！"
      }
     * @apiError (404) {int{0-65535}} status 响应状态码
     * @apiError (404) {long{0-500}} code 消息码
     * @apiError (404) {String} msg 说明
     * @apiErrorExample {json} 404错误
     * HTTP/1.1 404 404响应
      {
      	"status": 404,
      	"code": 200,
      	"msg": "接口未注册！"
      }
     * @apiError (500) {int{0-65535}} status 响应状态码
     * @apiError (500) {long{0-500}} code 消息码
     * @apiError (500) {String} msg 说明
     * @apiErrorExample {json} 500错误
     * HTTP/1.1 500 500响应
      {
      	"status": 500,
      	"code": 205,
      	"msg": "服务器未响应！"
      }
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
     * @api {post} http://8.135.36.45:8084/find/message/{id1}/{id2}/send 发送消息接口
     * @apiVersion 1.0.0
     * @apiGroup 消息模块API
     * @apiName 发送消息
     * @apiParam (接口请求参数) {long} id1 消息发送者用户id
     * @apiParam (接口请求参数) {long} id2 消息接收者用户id
     * @apiParam (接口请求参数) {string} content 消息内容
     * @apiParamExample {json} 请求示例
     * HTTP/1.1 OK
     * curl -v -X POST "http://8.135.36.45:8084/find/message/60/29/send?content=可以申请加你的微信吗？" -H "accept: application/json"
     * @apiSuccess (200) {long{0-500}} code 信息码
     * @apiSuccess (200) {string{..255}} msg 说明
     * @apiSuccess (200) {int{0-65535}} status 响应状态码
     * @apiSuccess (200) {object} [data] 发送状态数据
     * @apiSuccess (200) {string} [SEND] OK->发送成功，ERROR->发送失败
     * @apiSuccessExample {json} 200响应示例
     * HTTP/1.1 200 OK
		{
		"status": 200,
		"code": 0,
		"msg": "发送消息成功。",
		"data": {
				"SEND": "OK"
			}
		}
     * @apiError (403) {int{0-65535}} status 响应状态码
     * @apiError (403) {long{0-500}} code 消息码
     * @apiError (403) {String} msg 说明
     * @apiErrorExample {json} 403错误
     * HTTP/1.1 403 403响应
      {
      	"status": 403,
      	"code": 199,
      	"msg": "未找到用户信息！"
      }
     * @apiError (404) {int{0-65535}} status 响应状态码
     * @apiError (404) {long{0-500}} code 消息码
     * @apiError (404) {String} msg 说明
     * @apiErrorExample {json} 404错误
     * HTTP/1.1 404 404响应
      {
      	"status": 404,
      	"code": 200,
      	"msg": "接口未注册！"
      }
     * @apiError (500) {int{0-65535}} status 响应状态码
     * @apiError (500) {long{0-500}} code 消息码
     * @apiError (500) {String} msg 说明
     * @apiErrorExample {json} 500错误
     * HTTP/1.1 500 500响应
      {
      	"status": 500,
      	"code": 205,
      	"msg": "服务器未响应！"
      }
     */    
    @GetMapping(value = "/{id1}/{id2}/send")
    @ApiOperation(value = "发送消息接口", notes = "发送消息接口")
    public CommonResult<Map<String, Object>> send(
    		@PathVariable(name = "id1") @ApiParam(name = "id1", value = "消息发送者用户id", required = true, example = "1") Long sendUserId,
    		@PathVariable(name = "id2") @ApiParam(name = "id2", value = "消息接收者用户id", required = true, example = "2") Long recipientUserId,
    		@RequestParam(name = "content", defaultValue = "可以申请加你的微信吗？") @ApiParam(name = "content", value = "消息内容", required = true, example = "可以申请加你的微信吗？") String content) {
    	return this.messageFeignClient.send(sendUserId, recipientUserId, content);
    }
    
    /**
     * @api {put} http://8.135.36.45:8084/find/message/{id}/updateAll 全部消息已读接口
     * @apiVersion 1.0.0
     * @apiGroup 消息模块API
     * @apiName 全部消息已读
     * @apiParam (接口请求参数) {long} id 消息接收者用户id
     * @apiParamExample {json} 请求示例
     * HTTP/1.1 OK
     * curl -v -X PUT "http://8.135.36.45:8084/find/message/60/updateAll" -H "accept: application/json"
     * @apiSuccess (200) {long{0-500}} code 信息码
     * @apiSuccess (200) {string{..255}} msg 说明
     * @apiSuccess (200) {int{0-65535}} status 响应状态码
     * @apiSuccess (200) {object} [data] 标记已读状态数据
     * @apiSuccess (200) {string} [UPDATE] OK->标记已读成功，ERROR->标记已读失败
     * @apiSuccessExample {json} 200响应示例
     * HTTP/1.1 200 OK
		{
		"status": 200,
		"code": 0,
		"msg": "标记已读成功。",
		"data": {
				"UPDATE": "OK"
			}
		}
     * @apiError (403) {int{0-65535}} status 响应状态码
     * @apiError (403) {long{0-500}} code 消息码
     * @apiError (403) {String} msg 说明
     * @apiErrorExample {json} 403错误
     * HTTP/1.1 403 403响应
      {
      	"status": 403,
      	"code": 199,
      	"msg": "未找到用户信息！"
      }
     * @apiError (404) {int{0-65535}} status 响应状态码
     * @apiError (404) {long{0-500}} code 消息码
     * @apiError (404) {String} msg 说明
     * @apiErrorExample {json} 404错误
     * HTTP/1.1 404 404响应
      {
      	"status": 404,
      	"code": 200,
      	"msg": "接口未注册！"
      }
     * @apiError (500) {int{0-65535}} status 响应状态码
     * @apiError (500) {long{0-500}} code 消息码
     * @apiError (500) {String} msg 说明
     * @apiErrorExample {json} 500错误
     * HTTP/1.1 500 500响应
      {
      	"status": 500,
      	"code": 205,
      	"msg": "服务器未响应！"
      }
     */    
    @PutMapping(value = "/{id}/updateAll")
    @ApiOperation(value = "全部消息已读接口", notes = "全部消息已读接口")
    public CommonResult<Map<String, Object>> updateAll(
    		@PathVariable(name = "id") @ApiParam(name = "id", value = "消息接收者用户id", required = true, example = "1") Long recipientUserId) {
    	return this.messageFeignClient.allRead(recipientUserId);
    }
    
    
    /**
     * @api {delete} http://8.135.36.45:8084/find/message/{id}/delete 删除消息记录接口
     * @apiVersion 1.0.0
     * @apiGroup 消息模块API
     * @apiName 删除消息记录
     * @apiParam (接口请求参数) {long} id 消息接收者用户id
     * @apiParam (接口请求参数) {long} messageId 消息记录id
     * @apiParamExample {json} 请求示例
     * HTTP/1.1 OK
     * curl -v -X PUT "http://8.135.36.45:8084/find/message/60/delete?messageId=28" -H "accept: application/json"
     * @apiSuccess (200) {long{0-500}} code 信息码
     * @apiSuccess (200) {string{..255}} msg 说明
     * @apiSuccess (200) {int{0-65535}} status 响应状态码
     * @apiSuccess (200) {object} [data] 标记已读状态数据
     * @apiSuccess (200) {string} [DELETE] OK->删除消息记录成功，ERROR->删除消息记录失败
     * @apiSuccessExample {json} 200响应示例
     * HTTP/1.1 200 OK
        {
        "status": 200,
        "code": 0,
        "msg": "标记已读成功。",
        "data": {
                "DELETE": "OK"
            }
        }
     * @apiError (403) {int{0-65535}} status 响应状态码
     * @apiError (403) {long{0-500}} code 消息码
     * @apiError (403) {String} msg 说明
     * @apiErrorExample {json} 403错误
     * HTTP/1.1 403 403响应
      {
        "status": 403,
        "code": 199,
        "msg": "未找到用户信息！"
      }
     * @apiError (404) {int{0-65535}} status 响应状态码
     * @apiError (404) {long{0-500}} code 消息码
     * @apiError (404) {String} msg 说明
     * @apiErrorExample {json} 404错误
     * HTTP/1.1 404 404响应
      {
        "status": 404,
        "code": 200,
        "msg": "接口未注册！"
      }
     * @apiError (500) {int{0-65535}} status 响应状态码
     * @apiError (500) {long{0-500}} code 消息码
     * @apiError (500) {String} msg 说明
     * @apiErrorExample {json} 500错误
     * HTTP/1.1 500 500响应
      {
        "status": 500,
        "code": 205,
        "msg": "服务器未响应！"
      }
     */    
    @PutMapping(value = "/{id}/delete")
    @ApiOperation(value = "删除消息记录接口", notes = "删除消息记录接口")
    public CommonResult<Map<String, Object>> delete(
            @PathVariable(name = "id") @ApiParam(name = "id", value = "消息接收者用户id", required = true, example = "1") Long recipientUserId,
            @RequestParam(name = "messageId") @ApiParam(name = "messageId", value = "消息记录id", required = true, example = "2") Long messageId) {
        return this.messageFeignClient.allRead(recipientUserId);
    }
}
