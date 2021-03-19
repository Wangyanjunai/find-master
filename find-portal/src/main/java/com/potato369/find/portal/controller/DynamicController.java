package com.potato369.find.portal.controller;

import com.potato369.find.common.api.CommonResult;
import com.potato369.find.common.dto.LocationDTO;
import com.potato369.find.common.enums.OperateRecordStatusEnum;
import com.potato369.find.common.enums.OperateRecordTypeEnum;
import com.potato369.find.mbg.model.OperateRecord;
import com.potato369.find.portal.feign.DynamicService;
import com.potato369.find.portal.feign.UserLogService;

import io.swagger.annotations.*;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@Api(value = "dynamic-controller", tags = "动态信息模块Restful API")
@RestController
@RequestMapping("/dynamic")
//@Profile({"dev", "dev2", "test", "prod"})
public class DynamicController {

    private DynamicService dynamicFeignClient;

    private UserLogService userLogFeignClient;

    @Autowired
    public void setDynamicFeignClient(DynamicService dynamicFeignClient) {
        this.dynamicFeignClient = dynamicFeignClient;
    }

    @Autowired
    public void setUserLogFeignClient(UserLogService userLogFeignClient) {
        this.userLogFeignClient = userLogFeignClient;
    }

    // 用户发布动态内容包括文字，图片，语音

    /**
     * @api {post} http://124.71.38.2:8084/find/dynamic/{id}/release 发布动态内容接口
     * @apiVersion 1.0.0
     * @apiGroup 动态模块API
     * @apiName 发布动态内容
     * @apiParam (接口请求参数) {long} id 用户id
     * @apiParam (接口请求参数) {string} [imei] 设备串码
     * @apiParam (接口请求参数) {string={"0", "1"}} attacheInfoDataType 附件类型：0->图片；1->语音
     * @apiParam (接口请求参数) {file[]={1..4}} [files] 附件数组，说明：图片文件不能超过4张包括4张，语音文件不能超过1个包括1个
     * @apiParam (接口请求参数) {string} [model] 设备型号
     * @apiParam (接口请求参数) {string} [sysName] 系统名称
     * @apiParam (接口请求参数) {string} [sysCode] 系统版本
     * @apiParam (接口请求参数) {string={"2G","3G","4G","5G","WIFI"} [networkMode] 上网方式
     * @apiParam (接口请求参数) {string} [ip] 客户端IP
     * @apiParam (接口请求参数) {string} [country] 定位（国家）
     * @apiParam (接口请求参数) {string} [province] 定位（省份）
     * @apiParam (接口请求参数) {string} [city] 定位（城市）
     * @apiParam (接口请求参数) {string={"0", "1"}} [publicStatus] 是否公开定位，0->未公开；1->公开，默认0
     * @apiParam (接口请求参数) {string} [content] 动态内容
     * @apiParamExample {json} 请求示例01（发布图片有具体发布定位地址）
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
     * "country": "中国",
     * "province": "广东",
     * "city": "广州",
     * "publicStatus": "0",
     * "content": "发布照片。"
     * }'
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
    @ApiOperation(value = "发布动态内容接口", notes = "用于用户发布动态内容包括文字，图片，语音。")
    @ApiResponses(@ApiResponse(code = 200, message = "发布动态内容成功", response = CommonResult.class))
    @PostMapping(value = "/{id}/release", consumes = {"multipart/form-data;charset=utf-8"}, produces = {"application/json;charset=utf-8"})
    public CommonResult<Map<String, Object>> release(
            @PathVariable(name = "id") @ApiParam(name = "id", value = "用户id", example = "1", required = true) Long userId,
            @RequestParam(name = "imei", required = false) @ApiParam(name = "imei", value = "设备串码") String imei,
            @RequestParam(name = "attacheInfoDataType", required = false) @ApiParam(name = "attacheInfoDataType", value = "附件类型") String attacheInfoDataType,
            @RequestPart(value = "files", required = false) @ApiParam(name = "files", value = "附件数组") MultipartFile[] files,
            @RequestParam(name = "model", required = false) @ApiParam(name = "model", value = "设备型号") String model,
            @RequestParam(name = "sysName", required = false) @ApiParam(name = "sysName", value = "系统名称") String sysName,
            @RequestParam(name = "sysCode", required = false) @ApiParam(name = "sysCode", value = "系统版本") String sysCode,
            @RequestParam(name = "networkMode", required = false) @ApiParam(name = "networkMode", value = "上网方式", allowableValues = "2G,3G,4G,5G,WIFI", example = "4G") String networkMode,
            @RequestParam(name = "ip", required = false) @ApiParam(name = "ip", value = "客户端IP") String ip,
            @RequestParam(name = "country", required = false) @ApiParam(name = "country", value = "定位（国家）") String country,
            @RequestParam(name = "province", required = false) @ApiParam(name = "province", value = "定位（省份）") String province,
            @RequestParam(name = "city", required = false) @ApiParam(name = "city", value = "定位（城市）") String city,
            @RequestParam(name = "publicStatus", required = false) @ApiParam(name = "publicStatus", value = "是否公开定位", allowableValues = "0, 1", example = "0") String publicStatus,
            @RequestParam(name = "content", required = false) @ApiParam(name = "content", value = "动态内容") String content) {
        OperateRecord operateRecord = new OperateRecord();
        operateRecord.setStatus(OperateRecordStatusEnum.Success.getStatus());
        operateRecord.setUserId(userId);
        operateRecord.setType(OperateRecordTypeEnum.ReleaseDynamic.getCode());
        this.userLogFeignClient.record(userId, operateRecord);
        return this.dynamicFeignClient.release(userId, imei, attacheInfoDataType, files, model, sysName, sysCode, networkMode, ip, country, province, city, publicStatus, content);
    }

    /**
     * @api {post} http://124.71.38.2:8084/find/dynamic/{id}/updateLocation 更新动态地址定位接口
     * @apiVersion 1.0.0
     * @apiGroup 动态模块API
     * @apiName 更新动态地址定位
     * @apiParam (接口请求参数) {long} id 用户id
     * @apiParam (接口请求参数) {string} [ip] 客户端IP，说明：不能与定位（国家）、（省份）、（城市）同时为空，如果同时都不为空，以定位（国家）、（省份）、（城市）为准
     * @apiParam (接口请求参数) {string} [country] 定位（国家）
     * @apiParam (接口请求参数) {string} [province] 定位（省份）
     * @apiParam (接口请求参数) {string} [city] 定位（城市）
     * @apiParamExample {json} 请求示例01（有客户端IP）
     * HTTP/1.1 OK
     * curl -v -X POST -H 'application/json;charset=utf-8' http://124.71.38.2:8084/find/dynamic/1/updateLocation -d '{"ip":"183.14.133.239"}'
     * @apiSuccess (200) {long{0-500}} code 信息码
     * @apiSuccess (200) {string{..255}} msg 说明
     * @apiSuccess (200) {int{0-65535}} status 响应状态码
     * @apiSuccess (200) {string} [UPDATE] 更新状态，OK->成功，ERROR->失败
     * @apiSuccessExample {json} 200响应示例01（有客户端IP）
     * HTTP/1.1 200 OK
     * {
     * "status": 200,
     * "code": 0,
     * "msg": "更新用户发布动态定位成功。",
     * "data": {
     * "UPDATE": "OK"
     * }
     * }
     * @apiParamExample {json} 请求示例02（有定位（国家）、（省份）、（城市））
     * HTTP/1.1 OK
     * curl -v -X POST -H 'application/json;charset=utf-8' http://124.71.38.2:8084/find/dynamic/1/updateLocation -d '{"country": "中国", "province": "广东", "city": "深圳"}'
     * @apiSuccess (200) {long{0-500}} code 信息码
     * @apiSuccess (200) {string{..255}} msg 说明
     * @apiSuccess (200) {int{0-65535}} status 响应状态码
     * @apiSuccess (200) {object} [data] 数据
     * @apiSuccess (200) {string} [UPDATE] 更新状态，OK->成功，ERROR->失败
     * @apiSuccessExample {json} 200 响应示例02（有有定位（国家）、（省份）、（城市））
     * HTTP/1.1 200 OK
     * {
     * "status": 200,
     * "code": 0,
     * "msg": "更新用户发布动态定位成功。",
     * "data": {
     * "UPDATE": "OK"
     * }
     * }
     * @apiParamExample {json} 请求示例03（有客户端IP，定位（国家）、（省份）、（城市））
     * HTTP/1.1 OK
     * curl -v -X POST -H 'application/json;charset=utf-8' http://124.71.38.2:8084/find/dynamic/1/updateLocation
     * -d '{
     * "ip": "183.14.133.239",
     * "country": "中国",
     * "province": "广东",
     * "city": "深圳"
     * }'
     * @apiSuccess (200) {long{0-500}} code 信息码
     * @apiSuccess (200) {string{..255}} msg 说明
     * @apiSuccess (200) {int{0-65535}} status 响应状态码
     * @apiSuccess (200) {object} [data] 数据
     * @apiSuccess (200) {boolean} [CHANGED] 是否发生更改，true->是，false->否
     * @apiSuccessExample {json} 200响应示例03（有客户端IP，发布定位地址（国）、（省）、（市））
     * HTTP/1.1 200 OK
     * curl -v -X POST -H 'application/json;charset=utf-8' http://124.71.38.2:8084/find/dynamic/1/updateLocation
     * @apiErrorExample {json} 403错误 （客户端IP，定位（国家）、（省份）、（城市）都为空）
     * HTTP/1.1 400 400响应
     * {
     * "status": 400,
     * "code": 500,
     * "msg": "检查失败，客户端IP，发布动态定位（国）、（省）、（市）不能同时不传或者为空。",
     * "data": null
     * }
     * @apiError (404) {int{0-65535}} timestamp 响应时间戳
     * @apiError (404) {long{0-500}} status 消息码
     * @apiError (404) {String} error 错误说明
     * @apiError (404) {String} message 返回说明
     * @apiError (404) {String} path 路径
     * @apiErrorExample {json} 404错误
     * HTTP/1.1 404 404响应 接口未注册
     * {
     * "timestamp": 1611558682334,
     * "status": 404,
     * "error": "Not Found",
     * "message": "No message available",
     * "path": "/find/dynamic/1/updateLocation1"
     * }
     * @apiError (500) {int{0-65535}} status 响应状态码
     * @apiError (500) {long{0-500}} code 消息码
     * @apiError (500) {String} msg 说明
     * @apiErrorExample {json} 500错误
     * HTTP/1.1 500 500响应
     * {
     * "status": 500,
     * "code": 205,
     * "msg": "服务器未响应！",
     * "data": null
     * }
     */
    @ApiOperation(value = "更新动态地址定位接口", notes = "用于用户更新动态地址定位。")
    @ApiResponses(@ApiResponse(code = 200, message = "更新动态定位地址成功", response = CommonResult.class))
    @PostMapping(value = "/{id}/updateLocation")
    public CommonResult<Map<String, Object>> updateLocation(
            @PathVariable(name = "id") @ApiParam(name = "id", value = "用户id", required = true, example = "1")Long userId,
            @RequestBody @ApiParam("定位实体对象") LocationDTO locationDTO) {
        OperateRecord operateRecord = new OperateRecord();
        operateRecord.setStatus(OperateRecordStatusEnum.Success.getStatus());
        operateRecord.setUserId(userId);
        operateRecord.setType(OperateRecordTypeEnum.UpdateLocation.getCode());
        this.userLogFeignClient.record(userId, operateRecord);
        return this.dynamicFeignClient.updateLocation(userId, locationDTO);
    }

    /**
     * @api {post} http://124.71.38.2:8084/find/dynamic/{id}/checkLocation 检查定位地址是否更改接口
     * @apiVersion 1.0.0
     * @apiGroup 动态模块API
     * @apiName 检查定位地址是否更改
     * @apiParam (接口请求参数) {long} id 用户id
     * @apiParam (接口请求参数) {string} [ip] 客户端IP，不能与发布动态定位（国）、（省）、（市）同时不传或者为空，如果同时都传或者都不为空，以传的发布动态定位（国）、（省）、（市）为准
     * @apiParam (接口请求参数) {string} [country] 发布动态定位（国）
     * @apiParam (接口请求参数) {string} [province] 发布动态定位（省）
     * @apiParam (接口请求参数) {string} [city] 发布动态定位（市）
     * @apiParamExample {json} 请求示例01（有客户端IP）
     * HTTP/1.1 OK
     * curl -v -X POST -H 'application/json;charset=utf-8' http://124.71.38.2:8084/find/dynamic/1/checkLocation -d '{"ip":"183.14.133.239"}'
     * @apiSuccess (200) {long{0-500}} code 信息码
     * @apiSuccess (200) {string{..255}} msg 说明
     * @apiSuccess (200) {int{0-65535}} status 响应状态码
     * @apiSuccess (200) {object} [data] 数据
     * @apiSuccess (200) {boolean} [CHANGED] 是否发生更改，true->是，false->否
     * @apiSuccessExample {json} 200响应示例01（有客户端IP）
     * HTTP/1.1 200 OK
     * {
     * "status": 200,
     * "code": 0,
     * "msg": "检查成功。",
     * "data": {
     * "CHANGED": true
     * }
     * }
     * @apiParamExample {json} 请求示例02（有发布定位地址（国）、省、市）
     * HTTP/1.1 OK
     * curl -v -X POST -H 'application/json;charset=utf-8' http://124.71.38.2:8084/find/dynamic/1/checkLocation -d '{"country": "中国", "province": "广东", "city": "深圳"}'
     * @apiSuccess (200) {long{0-500}} code 信息码
     * @apiSuccess (200) {string{..255}} msg 说明
     * @apiSuccess (200) {int{0-65535}} status 响应状态码
     * @apiSuccess (200) {object} [data] 数据
     * @apiSuccess (200) {boolean} [CHANGED] 是否发生更改，true->是，false->否
     * @apiSuccessExample {json} 200 响应示例02（有发布定位地址（国）、（省）、（市））
     * HTTP/1.1 200 OK
     * {
     * "status": 200,
     * "code": 0,
     * "msg": "检查成功。",
     * "data": {
     * "CHANGED": true
     * }
     * }
     * @apiParamExample {json} 请求示例03（有客户端IP，发布定位地址（国）、（省）、（市））
     * HTTP/1.1 OK
     * curl -v -X POST -H 'application/json;charset=utf-8' http://124.71.38.2:8084/find/dynamic/1/checkLocation
     * -d '{
     * "ip": "183.14.133.239",
     * "country": "中国",
     * "province": "广东",
     * "city": "深圳"
     * }'
     * @apiSuccess (200) {long{0-500}} code 信息码
     * @apiSuccess (200) {string{..255}} msg 说明
     * @apiSuccess (200) {int{0-65535}} status 响应状态码
     * @apiSuccess (200) {object} [data] 数据
     * @apiSuccess (200) {boolean} [CHANGED] 是否发生更改，true->是，false->否
     * @apiSuccessExample {json} 200响应示例03（有客户端IP，发布定位地址（国）、（省）、（市））
     * HTTP/1.1 200 OK
     * curl -v -X POST -H 'application/json;charset=utf-8' http://124.71.38.2:8084/find/dynamic/1/checkLocation -d '{}'
     * @apiErrorExample {json} 403错误 （客户端IP，发布定位地址（国）、（省）、（市）都不传）
     * HTTP/1.1 400 400响应
     * {
     * "status": 400,
     * "code": 500,
     * "msg": "检查失败，客户端IP，发布动态定位（国）、（省）、（市）不能同时不传或者为空。",
     * "data": null
     * }
     * @apiError (404) {int{0-65535}} timestamp 响应时间戳
     * @apiError (404) {long{0-500}} status 消息码
     * @apiError (404) {String} error 错误说明
     * @apiError (404) {String} message 返回说明
     * @apiError (404) {String} path 路径
     * @apiErrorExample {json} 404错误
     * HTTP/1.1 404 404响应 接口未注册
     * {
     * "timestamp": 1611558682334,
     * "status": 404,
     * "error": "Not Found",
     * "message": "No message available",
     * "path": "/find/v1/dynamic/1/checkLocation1.do"
     * }
     * @apiError (500) {int{0-65535}} status 响应状态码
     * @apiError (500) {long{0-500}} code 消息码
     * @apiError (500) {String} msg 说明
     * @apiErrorExample {json} 500错误
     * HTTP/1.1 500 500响应
     * {
     * "status": 500,
     * "code": 205,
     * "msg": "服务器未响应！",
     * "data": null
     * }
     */
    @ApiOperation(value = "检查动态定位是否改变接口", notes = "用于检查用户动态定位是否改变。")
    @ApiResponses(@ApiResponse(code = 200, message = "检查动态定位是否改变成功", response = CommonResult.class))
    @PostMapping(value = "/{id}/checkLocation")
    public CommonResult<Map<String, Object>> checkLocation(
            @PathVariable(name = "id") @ApiParam(name = "id", value = "用户id", required = true, example = "1") Long userId,
            @RequestBody @ApiParam("定位实体对象") LocationDTO locationDTO) {
        return this.dynamicFeignClient.checkLocation(userId, locationDTO);
    }

    /**
     * @api {put} http://124.71.38.2:8084/find/dynamic/{id}/delete 删除动态内容接口
     * @apiVersion 1.0.0
     * @apiGroup 动态模块API
     * @apiName 删除动态内容
     * @apiParam (接口请求参数) {long} id 用户id
     * @apiParam (接口请求参数) {long} dynamicInfoId 动态内容id
     * @apiParamExample {json} 请求示例01（是自己发布的动态内容， 删除成功）
     * HTTP/1.1 OK
     * curl -v -X PUT http://124.71.38.2:8084/find/dynamic/70/delete?dynamicInfoId=85
     * @apiSuccess (200) {long{0-500}} code 信息码
     * @apiSuccess (200) {int{0-65535}} status 响应状态码
     * @apiSuccess (200) {string{..255}} msg 说明
     * @apiSuccess (200) {object} [data] 数据
     * @apiSuccess (200) {string} [DELETED] 删除状态，OK->成功，ERROR->失败
     * @apiSuccessExample {json} 200响应示例01（是自己发布的动态内容， 删除成功）
     * HTTP/1.1 200 OK
     * {
     * "status": 200,
     * "code": 0,
     * "msg": "删除动态内容成功。",
     * "data": {
     * "DELETED": "OK"
     * }
     * }
     * @apiParamExample {json} 请求示例02（非自己发布的动态内容， 删除失败）
     * HTTP/1.1 OK
     * curl -v -X PUT http://124.71.38.2:8084/find/dynamic/70/delete?dynamicInfoId=86
     * @apiSuccess (200) {int{0-65535}} status 响应状态码
     * @apiSuccess (200) {long{0-500}} code 信息码
     * @apiSuccess (200) {string{..255}} msg 说明
     * @apiSuccess (200) {object} [data] 数据
     * @apiSuccess (200) {string} [DELETED] 删除状态，OK->成功，ERROR->失败
     * @apiSuccessExample {json} 200 响应示例02（非自己发布的动态内容， 删除失败）
     * HTTP/1.1 200 OK
     * {
     * "status": 200,
     * "code": 0,
     * "msg": "删除动态内容失败。",
     * "data": {
     * "DELETED": "ERROR"
     * }
     * }
     * @apiError (404) {int{0-65535}} timestamp 响应时间戳
     * @apiError (404) {long{0-500}} status 消息码
     * @apiError (404) {String} error 错误说明
     * @apiError (404) {String} message 返回说明
     * @apiError (404) {String} path 路径
     * @apiErrorExample {json} 404错误
     * HTTP/1.1 404 404响应 接口未注册
     * {
     * "timestamp": 1611558682334,
     * "status": 404,
     * "error": "Not Found",
     * "message": "No message available",
     * "path": "/find/dynamic/1/delete1.do"
     * }
     * @apiError (500) {int{0-65535}} status 响应状态码
     * @apiError (500) {long{0-500}} code 消息码
     * @apiError (500) {String} msg 说明
     * @apiErrorExample {json} 500错误
     * HTTP/1.1 500 500响应
     * {
     * "status": 500,
     * "code": 205,
     * "msg": "服务器未响应！",
     * "data": null
     * }
     */
    @ApiOperation(value = "删除动态内容接口", notes = "用户用于删除动态内容。")
    @ApiResponses(@ApiResponse(code = 200, message = "删除动态内容成功", response = CommonResult.class))
    @PutMapping(value = "/{id}/delete")
    public CommonResult<Map<String, Object>> delete(
            @PathVariable(name = "id") @ApiParam(name = "id", value = "用户id", required = true, example = "1") Long userId,
            @RequestParam(name = "dynamicInfoId") @ApiParam(name = "dynamicInfoId", value = "动态信息id", required = true, example = "1") Long dynamicInfoId) {
        OperateRecord operateRecord = new OperateRecord();
        operateRecord.setStatus(OperateRecordStatusEnum.Success.getStatus());
        operateRecord.setUserId(userId);
        operateRecord.setType(OperateRecordTypeEnum.DeleteDynamic.getCode());
        this.userLogFeignClient.record(userId, operateRecord);
        return this.dynamicFeignClient.delete(userId, dynamicInfoId);
    }


    /**
     * @api {put} http://124.71.38.2:8084/find/dynamic/{id}/application 申请加微信接口
     * @apiVersion 1.0.0
     * @apiGroup 动态模块API
     * @apiName 申请加微信
     * @apiParam (接口请求参数) {long} id 用户id，说明：普通用户每天只允许申请最多5次添加微信，VIP用户申请加微信次数没有限制
     * @apiParam (接口请求参数) {long} dynamicInfoId 动态内容id
     * @apiParam (接口请求参数) {string{..255}} [message] 发送的消息
     * @apiParamExample {json} 请求示例01（第1次申请加微信）
     * HTTP/1.1 OK
     * curl -v -X PUT http://124.71.38.2:8084/find/dynamic/70/application?dynamicInfoId=86&message=需要加您的微信，请发送微信号码过来
     * @apiSuccess (200) {int{0-65535}} status 响应状态码
     * @apiSuccess (200) {long{0-500}} code 信息码
     * @apiSuccess (200) {string{..255}} msg 说明
     * @apiSuccess (200) {object} [data] 数据
     * @apiSuccess (200) {string} [APPLICATION] 申请加微信状态，OK->成功，ERROR->失败
     * @apiSuccessExample {json} 200响应示例01（第1次申请加微信）
     * HTTP/1.1 200 OK
     * {
     * "status": 200,
     * "code": 0,
     * "msg": "申请加微信成功。",
     * "data": {
     * "APPLICATION": "OK"
     * }
     * }
     * @apiParamExample {json} 请求示例02（第6次申请加微信）
     * HTTP/1.1 OK
     * curl -v -X PUT http://124.71.38.2:8084/find/dynamic/70/application?dynamicInfoId=86&message=需要加您的微信16
     * @apiSuccess (200) {int{0-65535}} status 响应状态码
     * @apiSuccess (200) {long{0-500}} code 信息码
     * @apiSuccess (200) {string{..255}} msg 说明
     * @apiSuccess (200) {object} [data] 数据
     * @apiSuccess (200) {string} [APPLICATION] 申请加微信状态，OK->成功，ERROR->失败
     * @apiSuccessExample {json} 200 响应示例02（第6次申请加微信）
     * HTTP/1.1 200 OK
     * {
     * "status": 200,
     * "code": 0,
     * "msg": "申请加微信出错，当天申请加微信次数超限。",
     * "data": {
     * "APPLICATION": "ERROR"
     * }
     * }
     * @apiError (404) {int{0-65535}} timestamp 响应时间戳
     * @apiError (404) {long{0-500}} status 消息码
     * @apiError (404) {String} error 错误说明
     * @apiError (404) {String} message 返回说明
     * @apiError (404) {String} path 路径
     * @apiErrorExample {json} 404错误
     * HTTP/1.1 404 404响应 接口未注册
     * {
     * "timestamp": 1611558682334,
     * "status": 404,
     * "error": "Not Found",
     * "message": "No message available",
     * "path": "find/dynamic/70/application1.do"
     * }
     * @apiError (500) {int{0-65535}} status 响应状态码
     * @apiError (500) {long{0-500}} code 消息码
     * @apiError (500) {String} msg 说明
     * @apiErrorExample {json} 500错误
     * HTTP/1.1 500 500响应
     * {
     * "status": 500,
     * "code": 205,
     * "msg": "服务器未响应！",
     * "data": null
     * }
     */
    @ApiOperation(value = "申请加微信接口", notes = "用户用于申请加微信，发送消息给对应的用户。")
    @PutMapping(value = "/{id}/application")
    public CommonResult<Map<String, Object>> application(
            @PathVariable(name = "id") @ApiParam(name = "id", value = "用户id", required = true, example = "1") Long userId,
            @RequestParam(name = "dynamicInfoId") @ApiParam(name = "dynamicInfoId", value = "动态信息id", required = true, example = "1") Long dynamicInfoId,
            @RequestParam(name = "message", required = false) @ApiParam(name = "message", value = "消息内容") String message) {
        return this.dynamicFeignClient.application(userId, dynamicInfoId, message);
    }

    /**
     * @api {put} http://124.71.38.2:8084/find/dynamic/{id}/likes 点赞或取消点赞接口
     * @apiVersion 1.0.0
     * @apiGroup 动态模块API
     * @apiName 点赞或取消点赞
     * @apiParam (接口请求参数) {long} id 用户id
     * @apiParam (接口请求参数) {long} dynamicInfoId 动态内容id
     * @apiParam (接口请求参数) {string{"0", "1"}} type 类型，0->取消点赞，1->点赞
     * @apiParamExample {json} 请求示例01（取消点赞，点赞记录不存在）
     * HTTP/1.1 OK
     * curl -v -X PUT http://124.71.38.2:8084/find/dynamic/70/likes?dynamicInfoId=86&type=0
     * @apiSuccess (200) {int{0-65535}} status 响应状态码
     * @apiSuccess (200) {long{0-500}} code 信息码
     * @apiSuccess (200) {string{..255}} msg 说明
     * @apiSuccess (200) {object} [data] 数据
     * @apiSuccess (200) {string} [LIKED] 点赞或取消点赞状态，OK->成功，ERROR->失败
     * @apiSuccessExample {json} 200响应示例01（取消点赞，点赞记录不存在）
     * HTTP/1.1 200 OK
     * {
     * "status": 200,
     * "code": 0,
     * "msg": "取消点赞，点赞记录信息不存在。",
     * "data": {
     * "LIKED": "ERROR"
     * }
     * }
     * @apiParamExample {json} 请求示例02（取消点赞，点赞记录存在）
     * HTTP/1.1 OK
     * curl -v -X PUT http://124.71.38.2:8084/find/dynamic/70/likes?dynamicInfoId=86&type=0
     * @apiSuccess (200) {int{0-65535}} status 响应状态码
     * @apiSuccess (200) {long{0-500}} code 信息码
     * @apiSuccess (200) {string{..255}} msg 说明
     * @apiSuccess (200) {object} [data] 数据
     * @apiSuccess (200) {string} [LIKED] 点赞或取消点赞状态，OK->成功，ERROR->失败
     * @apiSuccessExample {json} 200 响应示例02（取消点赞，点赞记录存在）
     * HTTP/1.1 200 OK
     * {
     * "status": 200,
     * "code": 0,
     * "msg": "取消点赞，取消成功。",
     * "data": {
     * "LIKED": "OK"
     * }
     * }
     * @apiParamExample {json} 请求示例03（点赞，点赞记录不存在）
     * HTTP/1.1 OK
     * curl -v -X PUT http://124.71.38.2:8084/find/dynamic/70/likes?dynamicInfoId=86&type=1
     * @apiSuccess (200) {int{0-65535}} status 响应状态码
     * @apiSuccess (200) {long{0-500}} code 信息码
     * @apiSuccess (200) {string{..255}} msg 说明
     * @apiSuccess (200) {object} [data] 数据
     * @apiSuccess (200) {string} [LIKED] 点赞或取消点赞状态，OK->成功，ERROR->失败
     * @apiSuccessExample {json} 200 响应示例03（点赞，点赞记录不存在）
     * HTTP/1.1 200 OK
     * {
     * "status": 200,
     * "code": 0,
     * "msg": "创建点赞，点赞成功。",
     * "data": {
     * "LIKED": "OK"
     * }
     * }
     * @apiError (404) {int{0-65535}} timestamp 响应时间戳
     * @apiError (404) {long{0-500}} status 消息码
     * @apiError (404) {String} error 错误说明
     * @apiError (404) {String} message 返回说明
     * @apiError (404) {String} path 路径
     * @apiErrorExample {json} 404错误
     * HTTP/1.1 404 404响应 接口未注册
     * {
     * "timestamp": 1611558682334,
     * "status": 404,
     * "error": "Not Found",
     * "message": "No message available",
     * "path": "/find/dynamic/70/likes1"
     * }
     * @apiError (500) {int{0-65535}} status 响应状态码
     * @apiError (500) {long{0-500}} code 消息码
     * @apiError (500) {String} msg 说明
     * @apiErrorExample {json} 500错误
     * HTTP/1.1 500 500响应
     * {
     * "status": 500,
     * "code": 205,
     * "msg": "服务器未响应！",
     * "data": null
     * }
     */
    @ApiOperation(value = "点赞或取消点赞接口", notes = "用户用于点赞或取消点赞动态内容。")
    @PutMapping(value = "/{id}/likes")
    public CommonResult<Map<String, Object>> likes(
            @PathVariable(name = "id") @ApiParam(name = "id", value = "用户id", required = true, example = "1") Long userId,
            @RequestParam(name = "dynamicInfoId") @ApiParam(name = "dynamicInfoId", value = "动态信息id", required = true, example = "1") Long dynamicInfoId,
            @RequestParam(name = "type") @ApiParam(name = "type", value = "类型", required = true, allowableValues = "0, 1", example = "0") String type) {
        return this.dynamicFeignClient.likes(userId, dynamicInfoId, type);
    }

    /**
     * @api {put} http://124.71.38.2:8084/find/dynamic/{id}/share 分享动态内容接口
     * @apiVersion 1.0.0
     * @apiGroup 动态模块API
     * @apiName 分享内容动态
     * @apiParam (接口请求参数) {long} id 用户id
     * @apiParam (接口请求参数) {long} dynamicInfoId 动态内容id
     * @apiParam (接口请求参数) {string={"0", "1", "2", "3", "4"}} mode 分享方式：0->微信好友，1->QQ好友，2->微信朋友圈，3->QQ空间，4->微信收藏
     * @apiParamExample {json} 请求示例
     * HTTP/1.1 OK
     * curl -v -X PUT http://124.71.38.2:8084/find/dynamic/70/share?dynamicInfoId=86&mode=0
     * @apiSuccess (200) {int{0-65535}} status 响应状态码
     * @apiSuccess (200) {long{0-500}} code 信息码
     * @apiSuccess (200) {string{..255}} msg 说明
     * @apiSuccess (200) {object} [data] 数据
     * @apiSuccess (200) {string} [SHARED] 分享状态，OK->成功，ERROR->失败，说明：成功，分享数+1，失败，不处理
     * @apiSuccessExample {json} 200响应示例
     * HTTP/1.1 200 OK
     * {
     * "status": 200,
     * "code": 0,
     * "msg": "分享动态内容成功。",
     * "data": {
     * "SHARED": "OK"
     * }
     * }
     * @apiError (404) {int{0-65535}} timestamp 响应时间戳
     * @apiError (404) {long{0-500}} status 消息码
     * @apiError (404) {String} error 错误说明
     * @apiError (404) {String} message 返回说明
     * @apiError (404) {String} path 路径
     * @apiErrorExample {json} 404错误
     * HTTP/1.1 404 404响应 接口未注册
     * {
     * "timestamp": 1611558682334,
     * "status": 404,
     * "error": "Not Found",
     * "message": "No message available",
     * "path": "/find/dynamic/70/share1.do"
     * }
     * @apiError (500) {int{0-65535}} status 响应状态码
     * @apiError (500) {long{0-500}} code 消息码
     * @apiError (500) {String} msg 说明
     * @apiErrorExample {json} 500错误
     * HTTP/1.1 500 500响应
     * {
     * "status": 500,
     * "code": 205,
     * "msg": "服务器未响应！",
     * "data": null
     * }
     */
    @ApiOperation(value = "分享动态内容接口", notes = "用户用于分享动态内容。")
    @PutMapping(value = "/{id}/share")
    public CommonResult<Map<String, Object>> shares(
            @PathVariable(name = "id") @ApiParam(name = "id", value = "用户id", required = true, example = "1") Long userId,
            @RequestParam(name = "dynamicInfoId") @ApiParam(name = "dynamicInfoId", value = "动态信息id", required = true, example = "1") Long dynamicInfoId,
            @RequestParam(name = "mode") @ApiParam(name = "mode", value = "分享方式", required = true, allowableValues = "0,1,2,3,4", example = "0") String mode) {
        return this.dynamicFeignClient.share(userId, dynamicInfoId, mode);
    }

    /**
     * @api {get} http://124.71.38.2:8084/find/dynamic/{id}/list 觅鹿主界面动态内容列表接口
     * @apiVersion 1.0.0
     * @apiGroup 动态模块API
     * @apiName 觅鹿主界面动态内容列表
     * @apiParam (接口请求参数) {long} id 用户id
     * @apiParam (接口请求参数) {int} [pageNum] 当前页数，默认：第1页
     * @apiParam (接口请求参数) {int} [pageSize] 每页条数，默认：每页20条
     * @apiParamExample {json} 请求示例01
     * HTTP/1.1 OK
     * curl -v -X PUT http://124.71.38.2:8084/find/dynamic/73/list?pageNum=1&pageSize=20
     * @apiSuccess (200) {int{0-65535}} status 响应状态码
     * @apiSuccess (200) {long{0-500}} code 信息码
     * @apiSuccess (200) {string{..255}} msg 说明
     * @apiSuccess (200) {object} [data] 数据
     * @apiSuccess (200) {int} [totalPage] 总页数
     * @apiSuccess (200) {object} [list] 动态内容数据列表
     * @apiSuccess (200) {string} [userId] 用户id
     * @apiSuccess (200) {string} [headUrl] 头像图片地址
     * @apiSuccess (200) {string} [nickname] 昵称
     * @apiSuccess (200) {string} [publishTime] 发布时间
     * @apiSuccess (200) {long} [dynamicInfoId] 动态内容id
     * @apiSuccess (200) {string} [content] 动态内容
     * @apiSuccess (200) {string} [address] 定位地址，如果发布动态时，公开定位，则会返回这条动态发布时的定位，否则不返回
     * @apiSuccess (200) {int} [likes] 点赞数
     * @apiSuccess (200) {boolean} [likeStatus] 点赞状态，true->已点赞，false->未点赞
     * @apiSuccess (200) {int} [applications] 申请加微信数
     * @apiSuccess (200) {boolean} [applicationStatus] 申请加微信状态，true->已申请，false->未申请
     * @apiSuccess (200) {string} [dataTye] 附件文件类型，0->图片，1->语音
     * @apiSuccess (200) {string[]} [attacheFileUrlList] 附件文件地址列表
     * @apiSuccessExample {json} 200响应示例01
     * HTTP/1.1 200 OK
     * {
     * "status": 200,
     * "code": 0,
     * "msg": "获取觅鹿界面发布的动态内容信息列表成功",
     * "data": {
     * "totalPage": 1,
     * "list": [
     * {
     * "userId": 71,
     * "headUrl": "http://124.71.38.2:9000/find/img/head/71/3cc0c052-e6ab-4b9a-b904-b4a577bd3413.jpg",
     * "nickname": "杨贵妃",
     * "publishTime": "2021-02-01 14:35:23",
     * "dynamicInfoId": 86,
     * "content": "刚刚注册，请多关照小妹子！！",
     * "address": "广西省南宁市",
     * "likes": 0,
     * "likeStatus": false,
     * "applications": 5,
     * "applicationStatus": true,
     * "dataType": "1",
     * "attacheFileUrlList": [
     * "http://124.71.38.2:9000/find/res/images/71/20210201/1612161322850/3cc0c052-e6ab-4b9a-b904-b4a577bd3413.jpg"
     * ]
     * }
     * ]
     * }
     * }
     * @apiError (404) {int{0-65535}} timestamp 响应时间戳
     * @apiError (404) {long{0-500}} status 消息码
     * @apiError (404) {String} error 错误说明
     * @apiError (404) {String} message 返回说明
     * @apiError (404) {String} path 路径
     * @apiErrorExample {json} 404错误
     * HTTP/1.1 404 404响应 接口未注册
     * {
     * "timestamp": 1611558682334,
     * "status": 404,
     * "error": "Not Found",
     * "message": "No message available",
     * "path": "/find/dynamic/70/list"
     * }
     * @apiError (500) {int{0-65535}} status 响应状态码
     * @apiError (500) {long{0-500}} code 消息码
     * @apiError (500) {String} msg 说明
     * @apiErrorExample {json} 500错误
     * HTTP/1.1 500 500响应
     * {
     * "status": 500,
     * "code": 205,
     * "msg": "服务器未响应！",
     * "data": null
     * }
     */
    @ApiOperation(value = "觅鹿主界面动态内容列表接口", notes = "用于分页获取觅鹿主界面动态内容列表。")
    @GetMapping(value = "/{id}/list")
    public CommonResult<Map<String, Object>> list(
            @PathVariable(name = "id") @ApiParam(name = "id", value = "用户id", required = true, example = "1") Long userId,
            @RequestParam(name = "pageNum", required = false, defaultValue = "1") @ApiParam(name = "pageNum", value = "当前页码", example = "1") Integer pageNum,
            @RequestParam(name = "pageSize", required = false, defaultValue = "20") @ApiParam(name = "pageSize", value = "每页数量", example = "20") Integer pageSize) {
        return this.dynamicFeignClient.list(userId, pageNum, pageSize);
    }

    /**
     * @api {get} http://124.71.38.2:8084/find/dynamic/{id}/screen 筛选动态内容列表接口
     * @apiVersion 1.0.0
     * @apiGroup 动态模块API
     * @apiName 筛选动态内容列表
     * @apiParam (接口请求参数) {long} id 用户id
     * @apiParam (接口请求参数) {int} [pageNum] 当前页数，默认：第1页
     * @apiParam (接口请求参数) {int} [pageSize] 每页条数，默认：每页20条
     * @apiParam (接口请求参数) {string={"0", "1", "2"}} [gender] 性别，0->女生，1->男生，2->全部，默认：如果用户注册选择是0->男生，则筛选值：1->女生，反之，如果用户注册选择是1->女生，则筛选值为：0->男生
     * @apiParam (接口请求参数) {int} [minAge] 年龄范围（最小值），默认：16
     * @apiParam (接口请求参数) {int} [maxAge] 年龄范围（最大值），默认：35
     * @apiParam (接口请求参数) {list={"水瓶座","双鱼座","白羊座","金牛座","双子座","巨蟹座","狮子座","处女座","天秤座","天蝎座","射手座","摩羯座"}} [constellation] 星座列表，默认：不限
     * @apiParam (接口请求参数) {string={"0","1","2"}} [dataType] 附件类型，默认：0->全部，1->图片或者图片+文字，2->语音或者语音+文字
     * @apiParam (接口请求参数) {list} [provinceList] 省份列表，例如：广东省, 四川省
     * @apiParam (接口请求参数) {list} [cityList] 城市列表，例如：深圳市, 广州市, 成都市, 攀枝花市
     * @apiParamExample {json} 请求示例01
     * HTTP/1.1 OK
     * curl -v -X GET http://124.71.38.2:8084/find/dynamic/71/screen?pageNum=1&pageSize=20&gender=0&minAge=16&maxAge=39&constellation=巨蟹座&dataType=0&provinceList=广东省,广西省,湖南省&cityList=深圳市,广州市,南宁市,长沙市
     * @apiSuccess (200) {int{0-65535}} status 响应状态码
     * @apiSuccess (200) {long{0-500}} code 信息码
     * @apiSuccess (200) {string{..255}} msg 说明
     * @apiSuccess (200) {object} [data] 数据
     * @apiSuccess (200) {int} [totalPage] 总页数
     * @apiSuccess (200) {object} [list] 动态内容数据列表
     * @apiSuccess (200) {string} [userId] 用户id
     * @apiSuccess (200) {string} [headUrl] 头像图片地址
     * @apiSuccess (200) {string} [nickname] 昵称
     * @apiSuccess (200) {string} [publishTime] 发布时间
     * @apiSuccess (200) {long} [dynamicInfoId] 动态内容id
     * @apiSuccess (200) {string} [content] 动态内容
     * @apiSuccess (200) {string} [address] 定位地址，如果发布动态时，公开定位，则会返回这条动态发布时的定位，否则不返回
     * @apiSuccess (200) {int} [likes] 点赞数
     * @apiSuccess (200) {boolean} [likeStatus] 点赞状态，true->已点赞，false->未点赞
     * @apiSuccess (200) {int} [applications] 申请加微信数
     * @apiSuccess (200) {boolean} [applicationStatus] 申请加微信状态，true->已申请，false->未申请
     * @apiSuccess (200) {string} [dataTye] 附件文件类型，0->图片，1->语音
     * @apiSuccess (200) {list} [attacheFileUrlList] 附件文件地址列表
     * @apiSuccessExample {json} 200响应示例01
     * HTTP/1.1 200 OK
     * {
     * "status": 200,
     * "code": 0,
     * "msg": "获取觅鹿界面发布的动态内容信息列表成功",
     * "data": {
     * "totalPage": 1,
     * "list": [
     * {
     * "userId": 71,
     * "headUrl": "http://124.71.38.2:9000/find/img/head/71/3cc0c052-e6ab-4b9a-b904-b4a577bd3413.jpg",
     * "nickname": "杨贵妃",
     * "publishTime": "2021-02-01 14:35:23",
     * "dynamicInfoId": 86,
     * "content": "刚刚注册，请多关照小妹子！！",
     * "address": "广西省南宁市",
     * "likes": 0,
     * "likeStatus": false,
     * "applications": 5,
     * "applicationStatus": true,
     * "dataType": "1",
     * "attacheFileUrlList": [
     * "http://124.71.38.2:9000/find/res/images/71/20210201/1612161322850/3cc0c052-e6ab-4b9a-b904-b4a577bd3413.jpg"
     * ]
     * }
     * ]
     * }
     * }
     * @apiError (404) {int{0-65535}} timestamp 响应时间戳
     * @apiError (404) {long{0-500}} status 消息码
     * @apiError (404) {String} error 错误说明
     * @apiError (404) {String} message 返回说明
     * @apiError (404) {String} path 路径
     * @apiErrorExample {json} 404错误
     * HTTP/1.1 404 404响应 接口未注册
     * {
     * "timestamp": 1611558682334,
     * "status": 404,
     * "error": "Not Found",
     * "message": "No message available",
     * "path": "/find/dynamic/70/screen1.do"
     * }
     * @apiError (500) {int{0-65535}} status 响应状态码
     * @apiError (500) {long{0-500}} code 消息码
     * @apiError (500) {String} msg 说明
     * @apiErrorExample {json} 500错误
     * HTTP/1.1 500 500响应
     * {
     * "status": 500,
     * "code": 205,
     * "msg": "服务器未响应！",
     * "data": null
     * }
     */
    @ApiOperation(value = "筛选动态内容列表接口", notes = "用于分页获取筛选动态内容列表。")
    @GetMapping(value = "/{id}/screen")
    public CommonResult<Map<String, Object>> screen(
            @PathVariable(name = "id") @ApiParam(name = "id", value = "用户id", required = true, example = "1") Long userId,// 用户id
            @RequestParam(name = "pageNum", required = false, defaultValue = "1") @ApiParam(name = "pageNum", value = "当前页码，默认：当前第1页", example = "1") Integer pageNum,    // 当前页码，默认：当前第1页
            @RequestParam(name = "pageSize", required = false, defaultValue = "20") @ApiParam(name = "pageSize", value = "每页数量，默认：每页20条", example = "20") Integer pageSize,// 每页数量，默认：每页20条
            @RequestParam(name = "gender", required = false) @ApiParam(name = "gender", value = "性别，0->女生，1->男生，2->全部", allowableValues = "0,1,2", example = "0") String gender, // 性别，0->女生，1->男生
            @RequestParam(name = "minAge", required = false, defaultValue = "16") @ApiParam(name = "minAge", value = "年龄范围（最小值），默认：16", example = "16") Integer minAge, // 年龄范围（最小值），默认：16岁
            @RequestParam(name = "maxAge", required = false, defaultValue = "35") @ApiParam(name = "maxAge", value = "年龄范围（最大值），默认：35", example = "35") Integer maxAge, // 年龄范围（最大值），默认：35岁
            @RequestParam(name = "constellation", required = false) @ApiParam(name = "constellation", value = "星座列表") List<String> constellations, // 星座
            @RequestParam(name = "dataType", required = false) @ApiParam(name = "dataType", value = "附件类型，0->全部，1->图片或者图片+文字，2->语音或者语音+文字，默认：0", allowableValues = "0,1,2", example = "0") String dataType, // 0->全部，1->图片或者图片+文字，2->语音或者语音+文字，默认：0
            @RequestParam(name = "provinceList", required = false) @ApiParam(name = "provinceList", value = "定位（省份）列表") List<String> provinceList, // 发布动态定位（省份）列表
            @RequestParam(name = "cityList", required = false) @ApiParam(name = "cityList", value = "定位（城市）列表") List<String> cityList) {// 发布动态定位（城市）列表
        return this.dynamicFeignClient.screen(userId, pageNum, pageSize, gender, minAge, maxAge, constellations, dataType, provinceList, cityList);
    }

    /**
     * @api {get} http://124.71.38.2:8084/find/dynamic/{id}/mylist 获取自己的动态内容列表接口
     * @apiVersion 1.0.0
     * @apiGroup 动态模块API
     * @apiName 获取自己的动态内容列表
     * @apiParam (接口请求参数) {long} id 用户id
     * @apiParam (接口请求参数) {int} [pageNum] 当前页数，默认：第1页
     * @apiParam (接口请求参数) {int} [pageSize] 每页条数，默认：每页20条
     * @apiParamExample {json} 请求示例
     * HTTP/1.1 OK
     * curl -v -X PUT http://124.71.38.2:8084/find/dynamic/71/mylist?pageNum=1&pageSize=20
     * @apiSuccess (200) {int{0-65535}} status 响应状态码
     * @apiSuccess (200) {long{0-500}} code 信息码
     * @apiSuccess (200) {string{..255}} msg 说明
     * @apiSuccess (200) {object} [data] 数据
     * @apiSuccess (200) {int} [totalPage] 总页数
     * @apiSuccess (200) {object} [list] 动态内容数据列表
     * @apiSuccess (200) {string} [userId] 用户id
     * @apiSuccess (200) {string} [headUrl] 头像图片地址
     * @apiSuccess (200) {string} [nickname] 昵称
     * @apiSuccess (200) {string} [publishTime] 发布时间
     * @apiSuccess (200) {long} [dynamicInfoId] 动态内容id
     * @apiSuccess (200) {string} [content] 动态内容
     * @apiSuccess (200) {string} [address] 定位地址，如果发布动态时，公开定位，则会返回这条动态发布时的定位，否则不返回
     * @apiSuccess (200) {int} [likes] 点赞数
     * @apiSuccess (200) {boolean} [likeStatus] 点赞状态，true->已点赞，false->未点赞
     * @apiSuccess (200) {int} [applications] 申请加微信数
     * @apiSuccess (200) {boolean} [applicationStatus] 申请加微信状态，true->已申请，false->未申请
     * @apiSuccess (200) {string} [dataTye] 附件文件类型，0->图片，1->语音
     * @apiSuccess (200) {list} [attacheFileUrlList] 附件文件地址列表
     * @apiSuccessExample {json} 200响应示例
     * HTTP/1.1 200 OK
     * {
     * "status": 200,
     * "code": 0,
     * "msg": "分页获取用户自己发布的所有动态内容列表成功。",
     * "data": {
     * "totalPage": 1,
     * "list": [
     * {
     * "userId": 71,
     * "headUrl": "http://124.71.38.2:9000/find/img/head/71/3cc0c052-e6ab-4b9a-b904-b4a577bd3413.jpg",
     * "nickname": "杨贵妃",
     * "publishTime": "2021-02-01 14:35:23",
     * "dynamicInfoId": 86,
     * "content": "刚刚注册，请多关照小妹子！！",
     * "address": "广西省南宁市",
     * "likes": 0,
     * "likeStatus": false,
     * "applications": 5,
     * "applicationStatus": false,
     * "dataType": "0",
     * "attacheFileUrlList": [
     * "http://124.71.38.2:9000/find/res/images/71/20210201/1612161322850/3cc0c052-e6ab-4b9a-b904-b4a577bd3413.jpg"
     * ]
     * }
     * ]
     * }
     * }
     * @apiError (404) {int{0-65535}} timestamp 响应时间戳
     * @apiError (404) {long{0-500}} status 消息码
     * @apiError (404) {String} error 错误说明
     * @apiError (404) {String} message 返回说明
     * @apiError (404) {String} path 路径
     * @apiErrorExample {json} 404错误
     * HTTP/1.1 404 404响应 接口未注册
     * {
     * "timestamp": 1611558682334,
     * "status": 404,
     * "error": "Not Found",
     * "message": "No message available",
     * "path": "find/dynamic/71/mylist1"
     * }
     * @apiError (500) {int{0-65535}} status 响应状态码
     * @apiError (500) {long{0-500}} code 消息码
     * @apiError (500) {String} msg 说明
     * @apiErrorExample {json} 500错误
     * HTTP/1.1 500 500响应
     * {
     * "status": 500,
     * "code": 205,
     * "msg": "服务器未响应！",
     * "data": null
     * }
     */
    @ApiOperation(value = "获取自己的动态内容列表接口", notes = "用于分页获取自己的动态内容列表。")
    @GetMapping(value = "/{id}/mylist")
    public CommonResult<Map<String, Object>> mylist(
            @PathVariable(name = "id") @ApiParam(name = "id", value = "用户id", required = true, example = "1") Long userId,
            @RequestParam(name = "pageNum", required = false, defaultValue = "1") @ApiParam(name = "pageNum", value = "当前页码，默认：当前第1页", example = "1") Integer pageNum,    // 当前页码，默认：当前第1页
            @RequestParam(name = "pageSize", required = false, defaultValue = "20") @ApiParam(name = "pageSize", value = "每页数量，默认：每页20条", example = "20") Integer pageSize) {// 每页数量，默认：每页20条
        return this.dynamicFeignClient.mylist(userId, pageNum, pageSize);
    }
}
