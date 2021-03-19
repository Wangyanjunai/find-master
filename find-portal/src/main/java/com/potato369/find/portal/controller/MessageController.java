package com.potato369.find.portal.controller;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.potato369.find.common.api.CommonResult;
import com.potato369.find.common.vo.MessageVO;
import com.potato369.find.common.vo.MessageVO2;
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
     * @api {post} http://124.71.38.2:8084/find/message/{id}/all 分页获取消息列表接口
     * @apiVersion 1.0.0
     * @apiGroup 消息模块API
     * @apiName 分页获取消息列表
     * @apiParam (接口请求参数) {long} id 用户id
     * @apiParam (接口请求参数) {int} [pageNum] 当前页码
     * @apiParam (接口请求参数) {int} [pageSize] 每页数量
     * @apiParamExample {json} 请求示例01（发布图片有具体发布定位地址）
     * HTTP/1.1 OK
     * curl -X GET "http://124.71.38.2:8084/find/message/29/all?pageNum=1&pageSize=20" -H "accept: application/json"
     * @apiSuccess (200) {long{0-500}} code 信息码
     * @apiSuccess (200) {string{..255}} msg 说明
     * @apiSuccess (200) {int{0-65535}} status 响应状态码
     * @apiSuccess (200) {object} [data] 数据
     * @apiSuccess (200) {string} [RELEASED] 发布状态
     * @apiSuccessExample {json} 200响应示例01（发布图片有具体发布定位地址）
     * HTTP/1.1 200 OK
     * {
     * "status": 200,
     * "code": 0,
     * "msg": "发布动态内容成功。",
     * "data": {
     * "RELEASED": "OK"
     * }
     * }
     * @apiParamExample {json} 请求示例02（发布图片有客户端IP）
     * HTTP/1.1 OK 注：form表单提交，需要在请求头加：“Content-Type=multipart/form-data;charset=utf-8”
     * curl -v -X POST -H 'multipart/form-data;charset=utf-8' http://124.71.38.2:8084/find/dynamic/3/release
     * -d '{
     * "imei": "895568564457954422",
     * "attacheInfoDataType": "1",
     * "files": "C:\Users\Administrator\Pictures\images\01.jpg,
     * C:\Users\Administrator\Pictures\images\02.jpg,
     * C:\Users\Administrator\Pictures\images\03.jpg,
     * C:\Users\Administrator\Pictures\images\04.jpg",
     * "model": "vivo x7 plus",
     * "sysName": "Android",
     * "sysCode": "9.0",
     * "networkMode": "WIFI",
     * "ip": "183.14.31.54",
     * "publicStatus": "0",
     * "content": "发布照片。"
     * }'
     * @apiSuccess (200) {long{0-500}} code 信息码
     * @apiSuccess (200) {string{..255}} msg 说明
     * @apiSuccess (200) {int{0-65535}} status 响应状态码
     * @apiSuccess (200) {object} [data] 数据
     * @apiSuccess (200) {string} [RELEASED] 发布状态
     * @apiSuccessExample {json} 200响应示例02（发布图片有客户端IP）
     * HTTP/1.1 200 OK
     * {
     * "status": 200,
     * "code": 0,
     * "msg": "发布动态内容成功。",
     * "data": {
     * "RELEASED": "OK"
     * }
     * }
     * @apiParamExample {json} 请求示例03（发布语音有客户端IP）
     * HTTP/1.1 OK 注：form表单提交，需要在请求头加：“Content-Type=multipart/form-data;charset=utf-8”
     * curl -v -X POST -H 'multipart/form-data;charset=utf-8' http://124.71.38.2:8084/find/dynamic/3/release
     * -d '{
     * "imei": "895568564457954422",
     * "attacheInfoDataType": "2",
     * "files": "F:\文件\各种音乐\(DJ)中文DJ\7姨、高梦瑶、妖姬 - 威震八方.mp3",
     * "model": "vivo x7 plus",
     * "sysName": "Android",
     * "sysCode": "9.0",
     * "networkMode": "WIFI",
     * "ip": "183.14.31.54",
     * "publicStatus": "0",
     * "content": "发布语音。"
     * }'
     * @apiSuccess (200) {long{0-500}} code 信息码
     * @apiSuccess (200) {string{..255}} msg 说明
     * @apiSuccess (200) {int{0-65535}} status 响应状态码
     * @apiSuccess (200) {object} [data] 数据
     * @apiSuccess (200) {string} [RELEASED] 发布状态
     * @apiSuccessExample {json} 200响应示例03（发布语音有客户端IP）
     * HTTP/1.1 200 OK
     * {
     * "status": 200,
     * "code": 0,
     * "msg": "发布动态内容成功。",
     * "data": {
     * "RELEASED": "OK"
     * }
     * }
     * @apiParamExample {json} 请求示例04（发布纯文字有客户端IP）
     * HTTP/1.1 OK 注：form表单提交，需要在请求头加：“Content-Type=multipart/form-data;charset=utf-8”
     * curl -v -X POST -H 'multipart/form-data;charset=utf-8' http://124.71.38.2:8084/find/dynamic/3/release
     * -d '{
     * "imei": "895568564457954422",
     * "attacheInfoDataType": "0",
     * "model": "vivo x7 plus",
     * "sysName": "Android",
     * "sysCode": "9.0",
     * "networkMode": "WIFI",
     * "ip": "183.14.31.54",
     * "publicStatus": "0",
     * "content": "发布语音。"
     * }'
     * @apiSuccess (200) {long{0-500}} code 信息码
     * @apiSuccess (200) {string{..255}} msg 说明
     * @apiSuccess (200) {int{0-65535}} status 响应状态码
     * @apiSuccess (200) {object} [data] 数据
     * @apiSuccess (200) {string} [RELEASED] 发布状态
     * @apiSuccessExample {json} 200响应示例04（发布纯文字有客户端IP）
     * HTTP/1.1 200 OK
     * {
     * "status": 200,
     * "code": 0,
     * "msg": "发布动态内容成功。",
     * "data": {
     * "RELEASED": "OK"
     * }
     * }
     * @apiError (403) {int{0-65535}} status 响应状态码
     * @apiError (403) {long{0-500}} code 消息码
     * @apiError (403) {String} msg 说明
     * @apiErrorExample {json} 403错误
     * HTTP/1.1 403 403响应
     * {
     * "status": 403,
     * "code": 199,
     * "msg": "未找到用户信息！"
     * }
     * @apiError (404) {int{0-65535}} status 响应状态码
     * @apiError (404) {long{0-500}} code 消息码
     * @apiError (404) {String} msg 说明
     * @apiErrorExample {json} 404错误
     * HTTP/1.1 404 404响应
     * {
     * "status": 404,
     * "code": 200,
     * "msg": "接口未注册！"
     * }
     * @apiError (500) {int{0-65535}} status 响应状态码
     * @apiError (500) {long{0-500}} code 消息码
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
    @ApiOperation(value = "分页获取消息界面用户最近一条点赞和申请加微信消息列表接口", notes = "用于分页获取消息界面用户最近一条点赞和申请加微信消息列表。")
    @ApiResponses(@ApiResponse(code = 200, message = "分页获取消息界面用户最近一条点赞和申请加微信消息列表成功", response = CommonResult.class))
    CommonResult<MessageVO> findAll(@PathVariable(name = "id") @ApiParam(name = "id", value = "用户id", required = true, example = "1") Long userId,
    		@RequestParam(name = "pageNum", required = false, defaultValue = "1") @ApiParam(name = "pageNum", value = "当前页码，默认：当前第1页", example = "1") Integer pageNum, // 当前页码，默认：当前第1页
            @RequestParam(name = "pageSize", required = false, defaultValue = "20") @ApiParam(name = "pageSize", value = "每页数量，默认：每页20条", example = "20") Integer pageSize) {// 每页数量，默认：每页20条
        return this.messageFeignClient.findAll(userId, pageNum, pageSize);
    }

    @GetMapping(value = "{id}/likes")
    @ApiOperation(value = "分页获取点赞消息列表接口", notes = "分页获取点赞消息列表。")
    @ApiResponses(@ApiResponse(code = 200, message = "分页获取点赞消息列表成功", response = CommonResult.class))
    CommonResult<MessageVO2> findLikes(@PathVariable(name = "id") @ApiParam(name = "id", value = "用户id", required = true, example = "1") Long userId,
    		@RequestParam(name = "pageNum", required = false, defaultValue = "1") @ApiParam(name = "pageNum", value = "当前页码，默认：当前第1页", example = "1") Integer pageNum, // 当前页码，默认：当前第1页
            @RequestParam(name = "pageSize", required = false, defaultValue = "20") @ApiParam(name = "pageSize", value = "每页数量，默认：每页20条", example = "20") Integer pageSize) {// 每页数量，默认：每页20条
        return this.messageFeignClient.findLikes(userId, pageNum, pageSize);
    }
}
