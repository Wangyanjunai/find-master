package com.potato369.find.portal.controller;

import com.potato369.find.common.api.CommonResult;
import com.potato369.find.common.dto.LocationDTO;
import com.potato369.find.portal.feign.DynamicService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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

    @Autowired
    public void setDynamicFeignClient(DynamicService dynamicFeignClient) {
        this.dynamicFeignClient = dynamicFeignClient;
    }

    // 用户发布动态内容包括文字，图片，语音

    /**
     * @api {post} http://127.0.0.1:8084/find/dynamic/{id}/release 发布动态内容接口
     * @apiVersion 1.0.0
     * @apiGroup 动态模块API
     * @apiName 发布动态内容
     * @apiParam (接口请求参数) {long} id 用户id
     * @apiParam (接口请求参数) {string} [imei] 设备串码
     * @apiParam (接口请求参数) {string={"0", "1", "2"}} attacheInfoDataType 动态包含附件类型：0->文字（不包含图片，语音的纯文字），1->图片，2->语音
     * @apiParam (接口请求参数) {file[]={1..4}} [files] 附件数组，说明：图片文件不能超过4张包括4张，语音文件不能超过1个包括1个
     * @apiParam (接口请求参数) {string} [model] 设备型号
     * @apiParam (接口请求参数) {string} [sysName] 系统名称
     * @apiParam (接口请求参数) {string} [sysCode] 系统版本
     * @apiParam (接口请求参数) {string={"2G","3G","4G","5G","WIFI"} [networkMode] 上网方式
     * @apiParam (接口请求参数) {string} [ip] 客户端IP
     * @apiParam (接口请求参数) {double} [longitude] 定位（经度）
     * @apiParam (接口请求参数) {double} [latitude] 定位（纬度）
     * @apiParam (接口请求参数) {string} [country] 定位（国家）
     * @apiParam (接口请求参数) {string} [province] 定位（省份）
     * @apiParam (接口请求参数) {string} [city] 定位（城市）
     * @apiParam (接口请求参数) {string} [district] 定位（区/县）
     * @apiParam (接口请求参数) {string} [other] 定位（其它）
     * @apiParam (接口请求参数) {string={"0", "1"}} [publicStatus] 是否公开定位，0->否，1->是，默认：0
     * @apiParam (接口请求参数) {string={"0", "1"}} [isTopic] 是否话题，0->否，1->是，默认：0
     * @apiParam (接口请求参数) {string} [topicTitle] 话题标题
     * @apiParam (接口请求参数) {string={"0", "1"}} [isAnonymous] 是否匿名，0->否，1->是，默认：0
     * @apiParam (接口请求参数) {string} [content] 动态内容
     * @apiParamExample {json} 请求示例01（发布图片有具体发布定位地址的动态）
     * HTTP/1.1 OK 注：form表单提交，需要在请求头加：“Content-Type=multipart/form-data;charset=utf-8”
     * curl -v -X POST -H 'multipart/form-data;charset=utf-8' http://127.0.0.1:8084/find/dynamic/3/release
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
     * "district": "荔湾区",
     * "other": "荔湾酒店",
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
     * curl -v -X POST -H 'multipart/form-data;charset=utf-8' http://127.0.0.1:8084/find/dynamic/3/release
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
     * curl -v -X POST -H 'multipart/form-data;charset=utf-8' http://127.0.0.1:8084/find/dynamic/3/release
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
     * curl -v -X POST -H 'multipart/form-data;charset=utf-8' http://127.0.0.1:8084/find/dynamic/3/release
     * -d '{
     * "imei": "895568564457954422",
     * "attacheInfoDataType": "0",
     * "model": "vivo x7 plus",
     * "sysName": "Android",
     * "sysCode": "9.0",
     * "networkMode": "WIFI",
     * "ip": "183.14.31.54",
     * "publicStatus": "0",
     * "content": "今天天气很好。"
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
     * @apiParamExample {json} 请求示例05（发布纯文字有客户端IP，是话题，匿名发布，不公开定位）
     * HTTP/1.1 OK 注：form表单提交，需要在请求头加：“Content-Type=multipart/form-data;charset=utf-8”
     * curl -v -X POST -H 'multipart/form-data;charset=utf-8' http://127.0.0.1:8084/find/dynamic/3/release
     * -d '{
     * "imei": "895568564457954422",
     * "attacheInfoDataType": "0",
     * "model": "vivo x7 plus",
     * "sysName": "Android",
     * "sysCode": "9.0",
     * "networkMode": "WIFI",
     * "ip": "183.14.31.54",
     * "publicStatus": "0",
     * "content": "今天天气很好。",
     * "isTopic": "1",
     * "topicTitle": "天气",
     * "isAnonymous": "1",
     * "publicStatus":"0"
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
    @PostMapping(value = "/{id}/release", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public CommonResult<Map<String, Object>> release(
            @PathVariable(name = "id") @ApiParam(name = "id", value = "用户id", example = "1", required = true) Long userId,
            @RequestParam(name = "imei", required = false) @ApiParam(name = "imei", value = "设备串码") String imei,
            @RequestParam(name = "attacheInfoDataType", required = false) @ApiParam(name = "attacheInfoDataType", value = "动态包含附件类型：0->文字（不包含图片，语音的纯文字），1->图片，2->语音", allowableValues = "0, 1, 2", example = "0") String attacheInfoDataType,
            @RequestPart(value = "files", required = false) @ApiParam(name = "files", value = "附件数组") MultipartFile[] files,
            @RequestParam(name = "model", required = false) @ApiParam(name = "model", value = "设备型号") String model,
            @RequestParam(name = "sysName", required = false) @ApiParam(name = "sysName", value = "系统名称") String sysName,
            @RequestParam(name = "sysCode", required = false) @ApiParam(name = "sysCode", value = "系统版本") String sysCode,
            @RequestParam(name = "networkMode", required = false) @ApiParam(name = "networkMode", value = "上网方式", allowableValues = "2G,3G,4G,5G,WIFI", example = "4G") String networkMode,
            @RequestParam(name = "ip", required = false) @ApiParam(name = "ip", value = "客户端IP") String ip,
            @RequestParam(name = "longitude", required = false) @ApiParam(name = "longitude", value = "定位（经度）") Double longitude,
            @RequestParam(name = "latitude", required = false) @ApiParam(name = "latitude", value = "定位（纬度）") Double latitude,
            @RequestParam(name = "country", required = false) @ApiParam(name = "country", value = "定位（国家）") String country,
            @RequestParam(name = "province", required = false) @ApiParam(name = "province", value = "定位（省份）") String province,
            @RequestParam(name = "city", required = false) @ApiParam(name = "city", value = "定位（城市）") String city,
            @RequestParam(name = "district", required = false) @ApiParam(name = "district", value = "定位（区/县）") String district,
            @RequestParam(name = "other", required = false) @ApiParam(name = "other", value = "定位（其它）") String other,
            @RequestParam(name = "publicStatus", required = false) @ApiParam(name = "publicStatus", value = "是否公开定位，0->否，1->是，默认：0", allowableValues = "0, 1", example = "0") String publicStatus,
            @RequestParam(name = "isTopic", required = false) @ApiParam(name = "isTopic", value = "是否话题，0->否，1->是，默认：0", allowableValues = "0, 1", example = "0") String isTopic,
            @RequestParam(name = "topicTitle", required = false) @ApiParam(name = "topicTitle", value = "话题标题") String topicTitle,
            @RequestParam(name = "isAnonymous", required = false) @ApiParam(name = "isAnonymous", value = "是否匿名，0->否，1->是，默认：0", allowableValues = "0, 1", example = "0") String isAnonymous,
            @RequestParam(name = "content", required = false) @ApiParam(name = "content", value = "动态内容") String content) {
        return this.dynamicFeignClient.release(userId, imei, attacheInfoDataType, files, model, sysName, sysCode, networkMode, ip, longitude, latitude, country, province, city, district, other, publicStatus, isTopic, topicTitle, isAnonymous, content);
    }

    /**
     * @api {post} http://127.0.0.1:8084/find/dynamic/{id}/updateLocation 更新动态地址定位接口
     * @apiVersion 1.0.0
     * @apiGroup 动态模块API
     * @apiName 更新动态地址定位
     * @apiParam (接口请求参数) {long} id 用户id
     * @apiParam (接口请求参数) {string} [ip] 客户端IP，说明：不能与定位（国家）、（省份）、（城市）、（区/县）、（其它）、（经纬度）同时为空，如果同时都不为空，以定位（国家）、（省份）、（城市）、（区/县）、（其它）、（经纬度）为准
     * @apiParam (接口请求参数) {string} [country] 定位（国家）
     * @apiParam (接口请求参数) {string} [province] 定位（省份）
     * @apiParam (接口请求参数) {string} [city] 定位（城市）
     * @apiParam (接口请求参数) {string} [district] 定位（区/县）
     * @apiParam (接口请求参数) {string} [other] 定位（其它）
     * @apiParam (接口请求参数) {double} [longitude] 定位（经度）
     * @apiParam (接口请求参数) {double} [latitude] 定位（纬度）
     * @apiParamExample {json} 请求示例01（有客户端IP）
     * HTTP/1.1 OK
     * curl -v -X POST -H 'application/json;charset=utf-8' http://127.0.0.1:8084/find/dynamic/1/updateLocation -d '{"ip":"183.14.133.239"}'
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
     * curl -v -X POST -H 'application/json;charset=utf-8' http://127.0.0.1:8084/find/dynamic/1/updateLocation -d '
     * {
     * "ip": "14.150.175.209",
     * "country": "中国",
     * "province": "广东省",
     * "city": "广州市",
     * "district": "白云区",
     * "other": "机场T3航站楼三楼",
     * "longitude": 113.201737,
     * "latitude": 22.832123
     * }'
     * @apiSuccess (200) {long{0-500}} code 信息码
     * @apiSuccess (200) {string{..255}} msg 说明
     * @apiSuccess (200) {int{0-65535}} status 响应状态码
     * @apiSuccess (200) {object} [data] 数据
     * @apiSuccess (200) {string} [UPDATE] 更新状态，OK->成功，ERROR->失败
     * @apiSuccessExample {json} 200 响应示例02（有客户端IP，具体定位地址（国家）、（省份）、（城市）、（区/县）、（其它）、（经纬度））
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
     * curl -v -X POST -H 'application/json;charset=utf-8' http://127.0.0.1:8084/find/dynamic/1/updateLocation
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
     * curl -v -X POST -H 'application/json;charset=utf-8' http://127.0.0.1:8084/find/dynamic/1/updateLocation
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
            @PathVariable(name = "id") @ApiParam(name = "id", value = "用户id", required = true, example = "1") Long userId,
            @RequestBody @ApiParam("定位实体对象") LocationDTO locationDTO) {
        return this.dynamicFeignClient.updateLocation(userId, locationDTO);
    }

    /**
     * @api {post} http://127.0.0.1:8084/find/dynamic/{id}/checkLocation 检查定位地址是否更改接口
     * @apiVersion 1.0.0
     * @apiGroup 动态模块API
     * @apiName 检查定位地址是否更改
     * @apiParam (接口请求参数) {long} id 用户id
     * @apiParam (接口请求参数) {string} [ip] 客户端IP，不能与发布动态定位（国家）、（省份）、（城市）、（区/县）、（其它）、（经纬度）同时为空，如果同时都不为空，以传的发布动态定位（国家）、（省份）、（城市）、（区/县）、（其它）、（经纬度）为准
     * @apiParam (接口请求参数) {string} [country] 发布动态定位（国家）
     * @apiParam (接口请求参数) {string} [province] 发布动态定位（省份）
     * @apiParam (接口请求参数) {string} [city] 发布动态定位（城市）
     * @apiParam (接口请求参数) {string} [district] 发布动态定位（区/县）
     * @apiParam (接口请求参数) {string} [other] 发布动态定位（其它）
     * @apiParam (接口请求参数) {double} [longitude] 发布动态定位（经度）
     * @apiParam (接口请求参数) {double} [latitude] 发布动态定位（纬度）
     * @apiParamExample {json} 请求示例01（有客户端IP）
     * HTTP/1.1 OK
     * curl -v -X POST -H 'application/json;charset=utf-8' http://127.0.0.1:8084/find/dynamic/1/checkLocation -d '{"ip":"183.14.133.239"}'
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
     * curl -v -X POST -H 'application/json;charset=utf-8' http://127.0.0.1:8084/find/dynamic/1/checkLocation -d '{"country": "中国", "province": "广东省", "city": "深圳市"}'
     * @apiSuccess (200) {long{0-500}} code 信息码
     * @apiSuccess (200) {string{..255}} msg 说明
     * @apiSuccess (200) {int{0-65535}} status 响应状态码
     * @apiSuccess (200) {object} [data] 数据
     * @apiSuccess (200) {boolean} [CHANGED] 是否发生更改，true->是，false->否
     * @apiSuccessExample {json} 200 响应示例02（有发布定位地址（国家）、（省份）、（城市））
     * HTTP/1.1 200 OK
     * {
     * "status": 200,
     * "code": 0,
     * "msg": "检查成功。",
     * "data": {
     * "CHANGED": true
     * }
     * }
     * @apiParamExample {json} 请求示例03（有客户端IP，发布定位地址（国家）、（省份）、（城市））
     * HTTP/1.1 OK
     * curl -v -X POST -H 'application/json;charset=utf-8' http://127.0.0.1:8084/find/dynamic/1/checkLocation
     * -d '{
     * "ip": "183.14.133.239",
     * "country": "中国",
     * "province": "广东省",
     * "city": "深圳市"
     * }'
     * @apiSuccess (200) {long{0-500}} code 信息码
     * @apiSuccess (200) {string{..255}} msg 说明
     * @apiSuccess (200) {int{0-65535}} status 响应状态码
     * @apiSuccess (200) {object} [data] 数据
     * @apiSuccess (200) {boolean} [CHANGED] 是否发生更改，true->是，false->否
     * @apiSuccessExample {json} 200响应示例03（有客户端IP，发布定位地址（国家）、（省份）、（城市））
     * HTTP/1.1 200 OK
     * curl -v -X POST -H 'application/json;charset=utf-8' http://127.0.0.1:8084/find/dynamic/1/checkLocation -d '{}'
     * @apiErrorExample {json} 403错误 （客户端IP，发布定位地址（国家）、（省份）、（城市）都不传）
     * HTTP/1.1 400 400响应
     * {
     * "status": 400,
     * "code": 500,
     * "msg": "检查失败，客户端IP，发布动态定位（国家）、（省份）、（城市）不能同时不传或者为空。",
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
     * "path": "/find/v1/dynamic/1/checkLocation1"
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
     * @api {put} http://127.0.0.1:8084/find/dynamic/{id}/delete 删除动态内容接口
     * @apiVersion 1.0.0
     * @apiGroup 动态模块API
     * @apiName 删除动态内容
     * @apiParam (接口请求参数) {long} id 用户id
     * @apiParam (接口请求参数) {long} dynamicInfoId 动态内容id
     * @apiParamExample {json} 请求示例01（是自己发布的动态内容， 删除成功）
     * HTTP/1.1 OK
     * curl -v -X PUT http://127.0.0.1:8084/find/dynamic/70/delete?dynamicInfoId=85
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
     * curl -v -X PUT http://127.0.0.1:8084/find/dynamic/70/delete?dynamicInfoId=86
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
     * "path": "/find/dynamic/1/delete1"
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
        return this.dynamicFeignClient.delete(userId, dynamicInfoId);
    }


    /**
     * @api {put} http://127.0.0.1:8084/find/dynamic/{id}/application 申请加微信接口
     * @apiVersion 1.0.0
     * @apiGroup 动态模块API
     * @apiName 申请加微信
     * @apiParam (接口请求参数) {long} id 申请者用户id，说明：普通用户每天只允许申请最多5次添加微信，VIP用户申请加微信次数没有限制
     * @apiParam (接口请求参数) {long} dynamicInfoId 动态内容id
     * @apiParam (接口请求参数) {string{..255}} [message] 发送的消息
     * @apiParamExample {json} 请求示例01（第1次申请加微信）
     * HTTP/1.1 OK
     * curl -v -X PUT http://127.0.0.1:8084/find/dynamic/70/application?dynamicInfoId=86&message=需要加您的微信，请发送微信号码过来
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
     * curl -v -X PUT http://127.0.0.1:8084/find/dynamic/70/application?dynamicInfoId=86&message=需要加您的微信16
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
     * "path": "find/dynamic/70/application1"
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
     * @api {put} http://127.0.0.1:8084/find/dynamic/{id}/likes 点赞或取消点赞接口
     * @apiVersion 1.0.0
     * @apiGroup 动态模块API
     * @apiName 点赞或取消点赞
     * @apiParam (接口请求参数) {long} id 用户id
     * @apiParam (接口请求参数) {long} dynamicInfoId 动态内容id
     * @apiParam (接口请求参数) {string{"0", "1"}} type 类型，0->取消，1->点赞
     * @apiParamExample {json} 请求示例01（取消，点赞记录不存在）
     * HTTP/1.1 OK
     * curl -v -X PUT http://127.0.0.1:8084/find/dynamic/70/likes?dynamicInfoId=86&type=0
     * @apiSuccess (200) {int{0-65535}} status 响应状态码
     * @apiSuccess (200) {long{0-500}} code 信息码
     * @apiSuccess (200) {string{..255}} msg 说明
     * @apiSuccess (200) {object} [data] 数据
     * @apiSuccess (200) {string} [LIKED] 点赞或取消点赞状态，OK->成功，ERROR->失败
     * @apiSuccessExample {json} 200响应示例01（取消，点赞记录不存在）
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
     * curl -v -X PUT http://127.0.0.1:8084/find/dynamic/70/likes?dynamicInfoId=86&type=0
     * @apiSuccess (200) {int{0-65535}} status 响应状态码
     * @apiSuccess (200) {long{0-500}} code 信息码
     * @apiSuccess (200) {string{..255}} msg 说明
     * @apiSuccess (200) {object} [data] 数据
     * @apiSuccess (200) {string} [LIKED] 点赞或取消点赞状态，OK->成功，ERROR->失败
     * @apiSuccessExample {json} 200 响应示例02（取消，点赞记录存在）
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
     * curl -v -X PUT http://127.0.0.1:8084/find/dynamic/70/likes?dynamicInfoId=86&type=1
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
     * @api {put} http://127.0.0.1:8084/find/dynamic/{id}/share 分享动态内容接口
     * @apiVersion 1.0.0
     * @apiGroup 动态模块API
     * @apiName 分享内容动态
     * @apiParam (接口请求参数) {long} id 用户id
     * @apiParam (接口请求参数) {long} dynamicInfoId 动态内容id
     * @apiParam (接口请求参数) {string={"0", "1", "2", "3", "4"}} mode 分享方式：0->微信好友，1->QQ好友，2->微信朋友圈，3->QQ空间，4->微信收藏
     * @apiParamExample {json} 请求示例
     * HTTP/1.1 OK
     * curl -v -X PUT http://127.0.0.1:8084/find/dynamic/70/share?dynamicInfoId=86&mode=0
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
     * "path": "/find/dynamic/70/share1"
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
     * @api {get} http://127.0.0.1:8084/find/dynamic/{id}/list 觅鹿主界面动态内容列表接口
     * @apiVersion 1.0.0
     * @apiGroup 动态模块API
     * @apiName 觅鹿主界面动态内容列表
     * @apiParam (接口请求参数) {long} id 用户id
     * @apiParam (接口请求参数) {string} ip 客户端IP，不能与定位（经纬度）同时为空，否则获取不到距离
     * @apiParam (接口请求参数) {double} longitude 定位（经度）
     * @apiParam (接口请求参数) {double} latitude 定位（纬度）
     * @apiParam (接口请求参数) {int} [pageNum] 当前页数，默认：第1页
     * @apiParam (接口请求参数) {int} [pageSize] 每页条数，默认：每页20条
     * @apiParamExample {json} 请求示例01（有客户端IP）
     * curl -v -X GET http://127.0.0.1:8084/find/dynamic/156/list?ip=183.14.135.215&pageNum=1&pageSize=20
     * @apiSuccess (200) {int{0-65535}} status 响应状态码
     * @apiSuccess (200) {long{0-500}} code 信息码
     * @apiSuccess (200) {string{..255}} msg 说明
     * @apiSuccess (200) {object} [data] 数据
     * @apiSuccess (200) {int} [totalPage] 总页数
     * @apiSuccess (200) {object[]} [hots] 三条热门话题列表
     * @apiSuccess (200) {int} [totalCount] 参与话题的动态数量
     * @apiSuccess (200) {string} [topicTitle] 话题标题
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
     * @apiSuccess (200) {boolean} [isTopic] 是否话题，true->是，false->否
     * @apiSuccess (200) {string} [topicTitle] 话题标题
     * @apiSuccess (200) {int} [comments] 评论数
     * @apiSuccess (200) {boolean} [isAnonymous] 是否匿名发布，true->是，false->否
     * @apiSuccess (200) {double} [distance] 当前位置距发布动态定位的距离（单位（米））
     * @apiSuccess (200) {string} [dataTye] 附件文件类型，1->图片，2->语音
     * @apiSuccess (200) {string[]} [attacheFileUrlList] 附件文件地址列表
     * @apiSuccessExample {json} 200响应示例01（有客户端IP）
     * {
     * "status": 200,
     * "code": 0,
     * "msg": "获取觅鹿界面发布的动态内容信息列表成功。",
     * "data": {
     * "totalPage": 11,
     * "hots": [
     * {
     * "totalCount": 5,
     * "topicTitle": "#电动车交规"
     * },
     * {
     * "totalCount": 4,
     * "topicTitle": "#球长防骗课堂"
     * },
     * {
     * "totalCount": 2,
     * "topicTitle": "#懒癌生存守则"
     * }
     * ],
     * "list": [
     * {
     * "userId": 70,
     * "headUrl": "http://127.0.0.1:9000/find/img/head/70/b150c5e3-bf2d-4c2f-b6cd-f586cd65183b.jpg",
     * "nickname": "阿萌",
     * "publishTime": "2021-07-22 14:10:37",
     * "dynamicInfoId": 768,
     * "content": "今天心情很烦躁的，不知道为什么？",
     * "likes": 0,
     * "likeStatus": false,
     * "applications": 0,
     * "applicationStatus": false,
     * "isTopic": false,
     * "comments": 0,
     * "isAnonymous": false,
     * "distance": 0.030315003677945207,
     * "dataType": "1",
     * "attacheFileUrlList": [
     * "http://127.0.0.1:9000/find/res/images/70/20210722/1626934237048/308f48ad-ce12-49ab-92f2-eb4ef7dcd649.jpg",
     * "http://127.0.0.1:9000/find/res/images/70/20210722/1626934237048/72517b7c-a808-445e-9a3d-3f15ee66e341.jpg"
     * ]
     * },
     * {
     * "userId": 70,
     * "headUrl": "http://127.0.0.1:9000/find/img/head/70/b150c5e3-bf2d-4c2f-b6cd-f586cd65183b.jpg",
     * "nickname": "阿萌",
     * "publishTime": "2021-07-22 14:10:20",
     * "dynamicInfoId": 767,
     * "content": "今天心情很烦躁的，不知道为什么？",
     * "likes": 0,
     * "likeStatus": false,
     * "applications": 0,
     * "applicationStatus": false,
     * "isTopic": false,
     * "comments": 0,
     * "isAnonymous": false,
     * "distance": 0.030315003677945207,
     * "dataType": "2",
     * "attacheFileUrlList": [
     * "http://127.0.0.1:9000/find/res/voices/70/20210722/1626934220235/7d3079b3-19fe-4ec1-93df-3d31cdceb643.mp3"
     * ]
     * },
     * {
     * "userId": 70,
     * "headUrl": "http://127.0.0.1:9000/find/img/head/70/b150c5e3-bf2d-4c2f-b6cd-f586cd65183b.jpg",
     * "nickname": "阿萌",
     * "publishTime": "2021-07-22 13:59:17",
     * "dynamicInfoId": 766,
     * "content": "今天心情很烦躁的，不知道为什么？",
     * "likes": 0,
     * "likeStatus": false,
     * "applications": 0,
     * "applicationStatus": false,
     * "isTopic": false,
     * "comments": 0,
     * "isAnonymous": false,
     * "distance": 0.030315003677945207,
     * "dataType": "2",
     * "attacheFileUrlList": [
     * "http://127.0.0.1:9000/find/res/voices/70/20210722/1626933557403/b32516d1-4231-4514-9712-b6fe3e13a554.mp3"
     * ]
     * },
     * {
     * "userId": 70,
     * "headUrl": "http://127.0.0.1:9000/find/img/head/70/b150c5e3-bf2d-4c2f-b6cd-f586cd65183b.jpg",
     * "nickname": "阿萌",
     * "publishTime": "2021-07-22 13:58:31",
     * "dynamicInfoId": 765,
     * "content": "今天心情很烦躁的，不知道为什么？",
     * "likes": 0,
     * "likeStatus": false,
     * "applications": 0,
     * "applicationStatus": false,
     * "isTopic": false,
     * "comments": 0,
     * "isAnonymous": false,
     * "distance": 0.030315003677945207,
     * "dataType": "1",
     * "attacheFileUrlList": [
     * "http://127.0.0.1:9000/find/res/images/70/20210722/1626933512005/2d5f85de-e2b1-4b96-a8c5-f3a6814c3c50.jpg",
     * "http://127.0.0.1:9000/find/res/images/70/20210722/1626933512005/fb92d519-e29c-4c80-b7cc-acfa2f6eaf47.jpg"
     * ]
     * },
     * {
     * "userId": 70,
     * "headUrl": "http://127.0.0.1:9000/find/img/head/70/b150c5e3-bf2d-4c2f-b6cd-f586cd65183b.jpg",
     * "nickname": "阿萌",
     * "publishTime": "2021-07-22 13:55:26",
     * "dynamicInfoId": 764,
     * "content": "今天心情很烦躁的，不知道为什么？",
     * "likes": 0,
     * "likeStatus": false,
     * "applications": 0,
     * "applicationStatus": false,
     * "isTopic": false,
     * "comments": 0,
     * "isAnonymous": false,
     * "distance": 0.030315003677945207,
     * "dataType": "1",
     * "attacheFileUrlList": [
     * "http://127.0.0.1:9000/find/res/images/70/20210722/1626933326165/4872593b-e7d2-496a-a52c-897cafda2bb5.jpg",
     * "http://127.0.0.1:9000/find/res/images/70/20210722/1626933326165/d7319235-dfee-42e3-a089-50cec9ae5a1f.jpg",
     * "http://127.0.0.1:9000/find/res/images/70/20210722/1626933326165/52eb9137-4a4f-43fd-98c9-bedd3f963d7a.jpg"
     * ]
     * },
     * {
     * "userId": 70,
     * "headUrl": "http://127.0.0.1:9000/find/img/head/70/b150c5e3-bf2d-4c2f-b6cd-f586cd65183b.jpg",
     * "nickname": "阿萌",
     * "publishTime": "2021-07-22 13:47:55",
     * "dynamicInfoId": 763,
     * "content": "今天心情很烦躁的，不知道为什么？",
     * "likes": 0,
     * "likeStatus": false,
     * "applications": 0,
     * "applicationStatus": false,
     * "isTopic": false,
     * "comments": 0,
     * "isAnonymous": false,
     * "distance": 0.030315003677945207,
     * "dataType": "1",
     * "attacheFileUrlList": [
     * "http://127.0.0.1:9000/find/res/images/70/20210722/1626932875293/5a0c731a-835f-4a3a-804e-624615858dad.jpg",
     * "http://127.0.0.1:9000/find/res/images/70/20210722/1626932875293/4ff713b7-cf87-4f58-97f3-b3311107b95c.jpg",
     * "http://127.0.0.1:9000/find/res/images/70/20210722/1626932875293/194b386a-107c-4782-84c2-c5ed563e38b1.jpg",
     * "http://127.0.0.1:9000/find/res/images/70/20210722/1626932875293/06f27bf2-b11a-4e42-8eac-0152d9aa4dbe.jpg"
     * ]
     * },
     * {
     * "userId": 70,
     * "headUrl": "http://127.0.0.1:9000/find/img/head/70/b150c5e3-bf2d-4c2f-b6cd-f586cd65183b.jpg",
     * "nickname": "阿萌",
     * "publishTime": "2021-07-22 13:47:21",
     * "dynamicInfoId": 762,
     * "content": "今天心情很烦躁的，不知道为什么？",
     * "likes": 0,
     * "likeStatus": false,
     * "applications": 0,
     * "applicationStatus": false,
     * "isTopic": false,
     * "comments": 0,
     * "isAnonymous": false,
     * "distance": 0.030315003677945207,
     * "dataType": "2",
     * "attacheFileUrlList": [
     * "http://127.0.0.1:9000/find/res/voices/70/20210722/1626932841063/c76d91fe-6f26-42b7-b5c5-340e23a35cbf.mp3"
     * ]
     * },
     * {
     * "userId": 73,
     * "headUrl": "http://127.0.0.1:9000/find/img/head/73/05.png",
     * "nickname": "如风",
     * "publishTime": "2021-07-22 11:52:05",
     * "dynamicInfoId": 757,
     * "content": "今天心情很烦躁的，不知道为什么？",
     * "likes": 0,
     * "likeStatus": false,
     * "applications": 0,
     * "applicationStatus": false,
     * "isTopic": false,
     * "comments": 0,
     * "isAnonymous": false,
     * "distance": 0.030315003677945207,
     * "dataType": "2",
     * "attacheFileUrlList": [
     * "http://127.0.0.1:9000/find/res/voices/73/20210722/1626925925504/8377a2cc-e6c5-416f-957a-46292d096650.mp3"
     * ]
     * },
     * {
     * "userId": 73,
     * "headUrl": "http://127.0.0.1:9000/find/img/head/73/05.png",
     * "nickname": "如风",
     * "publishTime": "2021-07-22 11:22:37",
     * "dynamicInfoId": 756,
     * "content": "今天心情很烦躁的，不知道为什么？",
     * "likes": 0,
     * "likeStatus": false,
     * "applications": 0,
     * "applicationStatus": false,
     * "isTopic": false,
     * "comments": 0,
     * "isAnonymous": false,
     * "distance": 0.030315003677945207,
     * "dataType": "2",
     * "attacheFileUrlList": [
     * "http://127.0.0.1:9000/find/res/voices/73/20210722/1626924157741/b7d2c5cf-15b9-49e2-b31a-789ffdc7ad5d.mp3"
     * ]
     * },
     * {
     * "userId": 72,
     * "headUrl": "http://127.0.0.1:9000/find/img/head/72/02.png",
     * "nickname": "竹語嫣",
     * "publishTime": "2021-07-22 11:16:04",
     * "dynamicInfoId": 755,
     * "content": "今天心情很烦躁的，不知道为什么？",
     * "likes": 0,
     * "likeStatus": false,
     * "applications": 0,
     * "applicationStatus": false,
     * "isTopic": false,
     * "comments": 0,
     * "isAnonymous": false,
     * "distance": 0.030315003677945207,
     * "dataType": "1",
     * "attacheFileUrlList": [
     * "http://127.0.0.1:9000/find/res/images/72/20210722/1626923764727/e118d01d-eaaa-4ebc-a6cb-2ed37103eb0f.jpg",
     * "http://127.0.0.1:9000/find/res/images/72/20210722/1626923764727/a02dee00-1554-457b-8f7f-2fb7fcb17bc2.jpg",
     * "http://127.0.0.1:9000/find/res/images/72/20210722/1626923764727/aee8c0e3-0ef7-414a-a20c-1c1261ec59e7.jpg",
     * "http://127.0.0.1:9000/find/res/images/72/20210722/1626923764727/f75d4dcb-52aa-4ef3-868e-655d8202177c.jpg"
     * ]
     * },
     * {
     * "userId": 72,
     * "headUrl": "http://127.0.0.1:9000/find/img/head/72/02.png",
     * "nickname": "竹語嫣",
     * "publishTime": "2021-07-22 11:03:07",
     * "dynamicInfoId": 754,
     * "content": "今天心情很烦躁的，不知道为什么？",
     * "likes": 0,
     * "likeStatus": false,
     * "applications": 0,
     * "applicationStatus": false,
     * "isTopic": false,
     * "comments": 0,
     * "isAnonymous": false,
     * "distance": 0.030315003677945207,
     * "dataType": "1",
     * "attacheFileUrlList": [
     * "http://127.0.0.1:9000/find/res/images/72/20210722/1626922987983/02a8fc1a-eaf8-45c8-a128-f3f7c5514853.jpg",
     * "http://127.0.0.1:9000/find/res/images/72/20210722/1626922987983/dc64a856-a5f2-4450-a3ad-75716e4e0852.jpg",
     * "http://127.0.0.1:9000/find/res/images/72/20210722/1626922987983/66df045b-bd62-4680-95d5-19d56c66ce28.jpg",
     * "http://127.0.0.1:9000/find/res/images/72/20210722/1626922987983/07d37a73-4fa5-45c7-a5e6-aa08f1ad9273.jpg"
     * ]
     * },
     * {
     * "userId": 72,
     * "headUrl": "http://127.0.0.1:9000/find/img/head/72/02.png",
     * "nickname": "竹語嫣",
     * "publishTime": "2021-07-22 11:02:27",
     * "dynamicInfoId": 753,
     * "content": "今天心情很烦躁的，不知道为什么？",
     * "likes": 0,
     * "likeStatus": false,
     * "applications": 0,
     * "applicationStatus": false,
     * "isTopic": false,
     * "comments": 0,
     * "isAnonymous": false,
     * "distance": 0.030315003677945207,
     * "dataType": "1",
     * "attacheFileUrlList": [
     * "http://127.0.0.1:9000/find/res/images/72/20210722/1626922947495/3ca511fb-478b-46ea-961d-59bd42fbaa2a.jpg",
     * "http://127.0.0.1:9000/find/res/images/72/20210722/1626922947495/b1779c43-70c0-4691-8a3a-b9cb702f7712.jpg"
     * ]
     * },
     * {
     * "userId": 72,
     * "headUrl": "http://127.0.0.1:9000/find/img/head/72/02.png",
     * "nickname": "竹語嫣",
     * "publishTime": "2021-07-22 09:41:05",
     * "dynamicInfoId": 752,
     * "content": "今天心情很烦躁的，不知道为什么？",
     * "likes": 0,
     * "likeStatus": false,
     * "applications": 0,
     * "applicationStatus": false,
     * "isTopic": false,
     * "comments": 0,
     * "isAnonymous": false,
     * "distance": 0.030315003677945207,
     * "dataType": "1",
     * "attacheFileUrlList": [
     * "http://127.0.0.1:9000/find/res/images/72/20210722/1626918065310/09faf385-81c3-4f31-ac8a-00fb2ae890b7.jpg"
     * ]
     * },
     * {
     * "userId": 72,
     * "headUrl": "http://127.0.0.1:9000/find/img/head/72/02.png",
     * "nickname": "竹語嫣",
     * "publishTime": "2021-07-22 09:24:33",
     * "dynamicInfoId": 751,
     * "content": "今天心情很烦躁的，不知道为什么？",
     * "likes": 0,
     * "likeStatus": false,
     * "applications": 0,
     * "applicationStatus": false,
     * "isTopic": false,
     * "comments": 0,
     * "isAnonymous": false,
     * "distance": 0.030315003677945207,
     * "dataType": "1",
     * "attacheFileUrlList": [
     * "http://127.0.0.1:9000/find/res/images/72/20210722/1626917073859/dd1821af-5e4c-4c57-9328-cb48349b0bbe.jpg"
     * ]
     * },
     * {
     * "userId": 73,
     * "headUrl": "http://127.0.0.1:9000/find/img/head/73/05.png",
     * "nickname": "如风",
     * "publishTime": "2021-07-21 18:04:57",
     * "dynamicInfoId": 750,
     * "content": "大家好，今天发布几张违法抓拍图片。",
     * "address": "广东省·深圳市·龙华区·",
     * "likes": 0,
     * "likeStatus": false,
     * "applications": 0,
     * "applicationStatus": false,
     * "isTopic": false,
     * "comments": 0,
     * "isAnonymous": true,
     * "distance": 26084.37755145445,
     * "dataType": "1",
     * "attacheFileUrlList": [
     * "http://127.0.0.1:9000/find/res/images/73/20210721/1626861897466/dcf127e2-5417-4e9a-bd9d-5a6d54897f3c.jpg",
     * "http://127.0.0.1:9000/find/res/images/73/20210721/1626861897466/23cfe24d-5daf-475e-af0f-1cdc49b3ac99.jpg",
     * "http://127.0.0.1:9000/find/res/images/73/20210721/1626861897466/1f638073-f95d-4509-9b69-9b89c3413924.jpg",
     * "http://127.0.0.1:9000/find/res/images/73/20210721/1626861897466/0ed716bb-9245-4128-b0f3-bdb4cd1ecf1e.jpg"
     * ]
     * },
     * {
     * "userId": 73,
     * "headUrl": "http://127.0.0.1:9000/find/img/head/73/05.png",
     * "nickname": "如风",
     * "publishTime": "2021-07-21 17:51:11",
     * "dynamicInfoId": 749,
     * "content": "大家好，今天发布几张违法抓拍图片。",
     * "address": "广东省·深圳市·龙华区·",
     * "likes": 0,
     * "likeStatus": false,
     * "applications": 0,
     * "applicationStatus": false,
     * "isTopic": false,
     * "comments": 0,
     * "isAnonymous": true,
     * "distance": 26084.37755145445,
     * "dataType": "1",
     * "attacheFileUrlList": [
     * "http://127.0.0.1:9000/find/res/images/73/20210721/1626861071731/0224b827-48a7-43b4-ad26-333ea10a4c92.jpg",
     * "http://127.0.0.1:9000/find/res/images/73/20210721/1626861071731/f8a66c9d-3aa1-4f30-af94-6b1d6f43dc08.jpg",
     * "http://127.0.0.1:9000/find/res/images/73/20210721/1626861071731/6cd8e532-a4f8-4621-89f3-fb4e83091500.jpg",
     * "http://127.0.0.1:9000/find/res/images/73/20210721/1626861071731/7fe9e0ee-9a68-4ead-be76-415b836a0c58.jpg"
     * ]
     * },
     * {
     * "userId": 73,
     * "headUrl": "http://127.0.0.1:9000/find/img/head/73/05.png",
     * "nickname": "如风",
     * "publishTime": "2021-07-21 17:50:06",
     * "dynamicInfoId": 748,
     * "content": "今天心情很烦躁的，不知道为什么？",
     * "likes": 0,
     * "likeStatus": false,
     * "applications": 0,
     * "applicationStatus": false,
     * "isTopic": false,
     * "comments": 0,
     * "isAnonymous": false,
     * "distance": 0.030315003677945207,
     * "dataType": "1",
     * "attacheFileUrlList": [
     * "http://127.0.0.1:9000/find/res/images/73/20210721/1626861006826/7dcbfbba-3790-46af-a228-c43aa5fc2721.jpg"
     * ]
     * },
     * {
     * "userId": 144,
     * "headUrl": "http://127.0.0.1:9000/find/img/head/144/fc3fe05b-6ca8-49fe-863c-31593879e124.jpg",
     * "nickname": "季婉",
     * "publishTime": "2021-07-21 17:46:37",
     * "dynamicInfoId": 747,
     * "content": "大家好，今天发布几张违法抓拍图片。",
     * "address": "广东省·深圳市·龙华区·",
     * "likes": 0,
     * "likeStatus": false,
     * "applications": 0,
     * "applicationStatus": false,
     * "isTopic": false,
     * "comments": 0,
     * "isAnonymous": true,
     * "distance": 26084.37755145445,
     * "dataType": "1",
     * "attacheFileUrlList": [
     * "http://127.0.0.1:9000/find/res/images/144/20210721/1626860797870/021c6104-a1e8-4811-a3d9-dcf0bcc2ab3a.jpg",
     * "http://127.0.0.1:9000/find/res/images/144/20210721/1626860797870/1279cab3-5bc1-4719-a199-fe66a6b8ae39.jpg",
     * "http://127.0.0.1:9000/find/res/images/144/20210721/1626860797870/c8077e24-ffb2-42ec-bdbb-7dee206152bc.jpg",
     * "http://127.0.0.1:9000/find/res/images/144/20210721/1626860797870/3ba9a02c-71bc-49ce-8ffb-46667fa14af8.jpg"
     * ]
     * },
     * {
     * "userId": 144,
     * "headUrl": "http://127.0.0.1:9000/find/img/head/144/fc3fe05b-6ca8-49fe-863c-31593879e124.jpg",
     * "nickname": "季婉",
     * "publishTime": "2021-07-21 17:43:58",
     * "dynamicInfoId": 746,
     * "content": "大家好，今天发布几张违法抓拍图片。",
     * "address": "广东省·深圳市·龙华区·",
     * "likes": 0,
     * "likeStatus": false,
     * "applications": 0,
     * "applicationStatus": false,
     * "isTopic": false,
     * "comments": 0,
     * "isAnonymous": true,
     * "distance": 26084.37755145445,
     * "dataType": "1",
     * "attacheFileUrlList": [
     * "http://127.0.0.1:9000/find/res/images/144/20210721/1626860638180/6877cde6-c4db-4ca9-8134-5c26334c3446.jpg",
     * "http://127.0.0.1:9000/find/res/images/144/20210721/1626860638180/4923990b-3c0b-4a57-9784-52ba6edc4527.jpg",
     * "http://127.0.0.1:9000/find/res/images/144/20210721/1626860638180/068d982b-5e37-447f-8134-4a154ded53de.jpg",
     * "http://127.0.0.1:9000/find/res/images/144/20210721/1626860638180/b659c879-26cf-49a9-b419-9e17437e9c75.jpg"
     * ]
     * },
     * {
     * "userId": 144,
     * "headUrl": "http://127.0.0.1:9000/find/img/head/144/fc3fe05b-6ca8-49fe-863c-31593879e124.jpg",
     * "nickname": "季婉",
     * "publishTime": "2021-07-21 17:33:09",
     * "dynamicInfoId": 744,
     * "content": "大家好，今天发布几张违法抓拍图片。",
     * "address": "广东省·深圳市·龙华区·",
     * "likes": 0,
     * "likeStatus": false,
     * "applications": 0,
     * "applicationStatus": false,
     * "isTopic": false,
     * "comments": 0,
     * "isAnonymous": true,
     * "distance": 26084.37755145445,
     * "dataType": "1",
     * "attacheFileUrlList": [
     * "http://127.0.0.1:9000/find/res/images/144/20210721/1626859989359/f6445ce4-829c-45f9-912b-299c2065576c.jpg",
     * "http://127.0.0.1:9000/find/res/images/144/20210721/1626859989359/4dba0a11-27fd-493e-8b59-a83c3251a0ac.jpg",
     * "http://127.0.0.1:9000/find/res/images/144/20210721/1626859989359/c04b4347-400d-4be9-9dd7-5f30953d0f8f.jpg",
     * "http://127.0.0.1:9000/find/res/images/144/20210721/1626859989359/37b9835b-b527-40fa-851b-f07254e80a22.jpg"
     * ]
     * }
     * ]
     * }
     * }
     * @apiParamExample {json} 请求示例02（有经纬度）
     * curl -v -X GET http://127.0.0.1:8084/find/dynamic/156/list?longitude=113.24421&latitude=23.12592&pageNum=1&pageSize=20
     * @apiSuccessExample {json} 200响应示例02（有经纬度）
     * {
     * "status": 200,
     * "code": 0,
     * "msg": "获取觅鹿界面发布的动态内容信息列表成功。",
     * "data": {
     * "totalPage": 11,
     * "hots": [
     * {
     * "totalCount": 5,
     * "topicTitle": "#电动车交规"
     * },
     * {
     * "totalCount": 4,
     * "topicTitle": "#球长防骗课堂"
     * },
     * {
     * "totalCount": 2,
     * "topicTitle": "#懒癌生存守则"
     * }
     * ],
     * "list": [
     * {
     * "userId": 70,
     * "headUrl": "http://127.0.0.1:9000/find/img/head/70/b150c5e3-bf2d-4c2f-b6cd-f586cd65183b.jpg",
     * "nickname": "阿萌",
     * "publishTime": "2021-07-22 14:10:37",
     * "dynamicInfoId": 768,
     * "content": "今天心情很烦躁的，不知道为什么？",
     * "likes": 0,
     * "likeStatus": false,
     * "applications": 0,
     * "applicationStatus": false,
     * "isTopic": false,
     * "comments": 0,
     * "isAnonymous": false,
     * "distance": 104359.96525414106,
     * "dataType": "1",
     * "attacheFileUrlList": [
     * "http://127.0.0.1:9000/find/res/images/70/20210722/1626934237048/308f48ad-ce12-49ab-92f2-eb4ef7dcd649.jpg",
     * "http://127.0.0.1:9000/find/res/images/70/20210722/1626934237048/72517b7c-a808-445e-9a3d-3f15ee66e341.jpg"
     * ]
     * },
     * {
     * "userId": 70,
     * "headUrl": "http://127.0.0.1:9000/find/img/head/70/b150c5e3-bf2d-4c2f-b6cd-f586cd65183b.jpg",
     * "nickname": "阿萌",
     * "publishTime": "2021-07-22 14:10:20",
     * "dynamicInfoId": 767,
     * "content": "今天心情很烦躁的，不知道为什么？",
     * "likes": 0,
     * "likeStatus": false,
     * "applications": 0,
     * "applicationStatus": false,
     * "isTopic": false,
     * "comments": 0,
     * "isAnonymous": false,
     * "distance": 104359.96525414106,
     * "dataType": "2",
     * "attacheFileUrlList": [
     * "http://127.0.0.1:9000/find/res/voices/70/20210722/1626934220235/7d3079b3-19fe-4ec1-93df-3d31cdceb643.mp3"
     * ]
     * },
     * {
     * "userId": 70,
     * "headUrl": "http://127.0.0.1:9000/find/img/head/70/b150c5e3-bf2d-4c2f-b6cd-f586cd65183b.jpg",
     * "nickname": "阿萌",
     * "publishTime": "2021-07-22 13:59:17",
     * "dynamicInfoId": 766,
     * "content": "今天心情很烦躁的，不知道为什么？",
     * "likes": 0,
     * "likeStatus": false,
     * "applications": 0,
     * "applicationStatus": false,
     * "isTopic": false,
     * "comments": 0,
     * "isAnonymous": false,
     * "distance": 104359.96525414106,
     * "dataType": "2",
     * "attacheFileUrlList": [
     * "http://127.0.0.1:9000/find/res/voices/70/20210722/1626933557403/b32516d1-4231-4514-9712-b6fe3e13a554.mp3"
     * ]
     * },
     * {
     * "userId": 70,
     * "headUrl": "http://127.0.0.1:9000/find/img/head/70/b150c5e3-bf2d-4c2f-b6cd-f586cd65183b.jpg",
     * "nickname": "阿萌",
     * "publishTime": "2021-07-22 13:58:31",
     * "dynamicInfoId": 765,
     * "content": "今天心情很烦躁的，不知道为什么？",
     * "likes": 0,
     * "likeStatus": false,
     * "applications": 0,
     * "applicationStatus": false,
     * "isTopic": false,
     * "comments": 0,
     * "isAnonymous": false,
     * "distance": 104359.96525414106,
     * "dataType": "1",
     * "attacheFileUrlList": [
     * "http://127.0.0.1:9000/find/res/images/70/20210722/1626933512005/2d5f85de-e2b1-4b96-a8c5-f3a6814c3c50.jpg",
     * "http://127.0.0.1:9000/find/res/images/70/20210722/1626933512005/fb92d519-e29c-4c80-b7cc-acfa2f6eaf47.jpg"
     * ]
     * },
     * {
     * "userId": 70,
     * "headUrl": "http://127.0.0.1:9000/find/img/head/70/b150c5e3-bf2d-4c2f-b6cd-f586cd65183b.jpg",
     * "nickname": "阿萌",
     * "publishTime": "2021-07-22 13:55:26",
     * "dynamicInfoId": 764,
     * "content": "今天心情很烦躁的，不知道为什么？",
     * "likes": 0,
     * "likeStatus": false,
     * "applications": 0,
     * "applicationStatus": false,
     * "isTopic": false,
     * "comments": 0,
     * "isAnonymous": false,
     * "distance": 104359.96525414106,
     * "dataType": "1",
     * "attacheFileUrlList": [
     * "http://127.0.0.1:9000/find/res/images/70/20210722/1626933326165/4872593b-e7d2-496a-a52c-897cafda2bb5.jpg",
     * "http://127.0.0.1:9000/find/res/images/70/20210722/1626933326165/d7319235-dfee-42e3-a089-50cec9ae5a1f.jpg",
     * "http://127.0.0.1:9000/find/res/images/70/20210722/1626933326165/52eb9137-4a4f-43fd-98c9-bedd3f963d7a.jpg"
     * ]
     * },
     * {
     * "userId": 70,
     * "headUrl": "http://127.0.0.1:9000/find/img/head/70/b150c5e3-bf2d-4c2f-b6cd-f586cd65183b.jpg",
     * "nickname": "阿萌",
     * "publishTime": "2021-07-22 13:47:55",
     * "dynamicInfoId": 763,
     * "content": "今天心情很烦躁的，不知道为什么？",
     * "likes": 0,
     * "likeStatus": false,
     * "applications": 0,
     * "applicationStatus": false,
     * "isTopic": false,
     * "comments": 0,
     * "isAnonymous": false,
     * "distance": 104359.96525414106,
     * "dataType": "1",
     * "attacheFileUrlList": [
     * "http://127.0.0.1:9000/find/res/images/70/20210722/1626932875293/5a0c731a-835f-4a3a-804e-624615858dad.jpg",
     * "http://127.0.0.1:9000/find/res/images/70/20210722/1626932875293/4ff713b7-cf87-4f58-97f3-b3311107b95c.jpg",
     * "http://127.0.0.1:9000/find/res/images/70/20210722/1626932875293/194b386a-107c-4782-84c2-c5ed563e38b1.jpg",
     * "http://127.0.0.1:9000/find/res/images/70/20210722/1626932875293/06f27bf2-b11a-4e42-8eac-0152d9aa4dbe.jpg"
     * ]
     * },
     * {
     * "userId": 70,
     * "headUrl": "http://127.0.0.1:9000/find/img/head/70/b150c5e3-bf2d-4c2f-b6cd-f586cd65183b.jpg",
     * "nickname": "阿萌",
     * "publishTime": "2021-07-22 13:47:21",
     * "dynamicInfoId": 762,
     * "content": "今天心情很烦躁的，不知道为什么？",
     * "likes": 0,
     * "likeStatus": false,
     * "applications": 0,
     * "applicationStatus": false,
     * "isTopic": false,
     * "comments": 0,
     * "isAnonymous": false,
     * "distance": 104359.96525414106,
     * "dataType": "2",
     * "attacheFileUrlList": [
     * "http://127.0.0.1:9000/find/res/voices/70/20210722/1626932841063/c76d91fe-6f26-42b7-b5c5-340e23a35cbf.mp3"
     * ]
     * },
     * {
     * "userId": 73,
     * "headUrl": "http://127.0.0.1:9000/find/img/head/73/05.png",
     * "nickname": "如风",
     * "publishTime": "2021-07-22 11:52:05",
     * "dynamicInfoId": 757,
     * "content": "今天心情很烦躁的，不知道为什么？",
     * "likes": 0,
     * "likeStatus": false,
     * "applications": 0,
     * "applicationStatus": false,
     * "isTopic": false,
     * "comments": 0,
     * "isAnonymous": false,
     * "distance": 104359.96525414106,
     * "dataType": "2",
     * "attacheFileUrlList": [
     * "http://127.0.0.1:9000/find/res/voices/73/20210722/1626925925504/8377a2cc-e6c5-416f-957a-46292d096650.mp3"
     * ]
     * },
     * {
     * "userId": 73,
     * "headUrl": "http://127.0.0.1:9000/find/img/head/73/05.png",
     * "nickname": "如风",
     * "publishTime": "2021-07-22 11:22:37",
     * "dynamicInfoId": 756,
     * "content": "今天心情很烦躁的，不知道为什么？",
     * "likes": 0,
     * "likeStatus": false,
     * "applications": 0,
     * "applicationStatus": false,
     * "isTopic": false,
     * "comments": 0,
     * "isAnonymous": false,
     * "distance": 104359.96525414106,
     * "dataType": "2",
     * "attacheFileUrlList": [
     * "http://127.0.0.1:9000/find/res/voices/73/20210722/1626924157741/b7d2c5cf-15b9-49e2-b31a-789ffdc7ad5d.mp3"
     * ]
     * },
     * {
     * "userId": 72,
     * "headUrl": "http://127.0.0.1:9000/find/img/head/72/02.png",
     * "nickname": "竹語嫣",
     * "publishTime": "2021-07-22 11:16:04",
     * "dynamicInfoId": 755,
     * "content": "今天心情很烦躁的，不知道为什么？",
     * "likes": 0,
     * "likeStatus": false,
     * "applications": 0,
     * "applicationStatus": false,
     * "isTopic": false,
     * "comments": 0,
     * "isAnonymous": false,
     * "distance": 104359.96525414106,
     * "dataType": "1",
     * "attacheFileUrlList": [
     * "http://127.0.0.1:9000/find/res/images/72/20210722/1626923764727/e118d01d-eaaa-4ebc-a6cb-2ed37103eb0f.jpg",
     * "http://127.0.0.1:9000/find/res/images/72/20210722/1626923764727/a02dee00-1554-457b-8f7f-2fb7fcb17bc2.jpg",
     * "http://127.0.0.1:9000/find/res/images/72/20210722/1626923764727/aee8c0e3-0ef7-414a-a20c-1c1261ec59e7.jpg",
     * "http://127.0.0.1:9000/find/res/images/72/20210722/1626923764727/f75d4dcb-52aa-4ef3-868e-655d8202177c.jpg"
     * ]
     * },
     * {
     * "userId": 72,
     * "headUrl": "http://127.0.0.1:9000/find/img/head/72/02.png",
     * "nickname": "竹語嫣",
     * "publishTime": "2021-07-22 11:03:07",
     * "dynamicInfoId": 754,
     * "content": "今天心情很烦躁的，不知道为什么？",
     * "likes": 0,
     * "likeStatus": false,
     * "applications": 0,
     * "applicationStatus": false,
     * "isTopic": false,
     * "comments": 0,
     * "isAnonymous": false,
     * "distance": 104359.96525414106,
     * "dataType": "1",
     * "attacheFileUrlList": [
     * "http://127.0.0.1:9000/find/res/images/72/20210722/1626922987983/02a8fc1a-eaf8-45c8-a128-f3f7c5514853.jpg",
     * "http://127.0.0.1:9000/find/res/images/72/20210722/1626922987983/dc64a856-a5f2-4450-a3ad-75716e4e0852.jpg",
     * "http://127.0.0.1:9000/find/res/images/72/20210722/1626922987983/66df045b-bd62-4680-95d5-19d56c66ce28.jpg",
     * "http://127.0.0.1:9000/find/res/images/72/20210722/1626922987983/07d37a73-4fa5-45c7-a5e6-aa08f1ad9273.jpg"
     * ]
     * },
     * {
     * "userId": 72,
     * "headUrl": "http://127.0.0.1:9000/find/img/head/72/02.png",
     * "nickname": "竹語嫣",
     * "publishTime": "2021-07-22 11:02:27",
     * "dynamicInfoId": 753,
     * "content": "今天心情很烦躁的，不知道为什么？",
     * "likes": 0,
     * "likeStatus": false,
     * "applications": 0,
     * "applicationStatus": false,
     * "isTopic": false,
     * "comments": 0,
     * "isAnonymous": false,
     * "distance": 104359.96525414106,
     * "dataType": "1",
     * "attacheFileUrlList": [
     * "http://127.0.0.1:9000/find/res/images/72/20210722/1626922947495/3ca511fb-478b-46ea-961d-59bd42fbaa2a.jpg",
     * "http://127.0.0.1:9000/find/res/images/72/20210722/1626922947495/b1779c43-70c0-4691-8a3a-b9cb702f7712.jpg"
     * ]
     * },
     * {
     * "userId": 72,
     * "headUrl": "http://127.0.0.1:9000/find/img/head/72/02.png",
     * "nickname": "竹語嫣",
     * "publishTime": "2021-07-22 09:41:05",
     * "dynamicInfoId": 752,
     * "content": "今天心情很烦躁的，不知道为什么？",
     * "likes": 0,
     * "likeStatus": false,
     * "applications": 0,
     * "applicationStatus": false,
     * "isTopic": false,
     * "comments": 0,
     * "isAnonymous": false,
     * "distance": 104359.96525414106,
     * "dataType": "1",
     * "attacheFileUrlList": [
     * "http://127.0.0.1:9000/find/res/images/72/20210722/1626918065310/09faf385-81c3-4f31-ac8a-00fb2ae890b7.jpg"
     * ]
     * },
     * {
     * "userId": 72,
     * "headUrl": "http://127.0.0.1:9000/find/img/head/72/02.png",
     * "nickname": "竹語嫣",
     * "publishTime": "2021-07-22 09:24:33",
     * "dynamicInfoId": 751,
     * "content": "今天心情很烦躁的，不知道为什么？",
     * "likes": 0,
     * "likeStatus": false,
     * "applications": 0,
     * "applicationStatus": false,
     * "isTopic": false,
     * "comments": 0,
     * "isAnonymous": false,
     * "distance": 104359.96525414106,
     * "dataType": "1",
     * "attacheFileUrlList": [
     * "http://127.0.0.1:9000/find/res/images/72/20210722/1626917073859/dd1821af-5e4c-4c57-9328-cb48349b0bbe.jpg"
     * ]
     * },
     * {
     * "userId": 73,
     * "headUrl": "http://127.0.0.1:9000/find/img/head/73/05.png",
     * "nickname": "如风",
     * "publishTime": "2021-07-21 18:04:57",
     * "dynamicInfoId": 750,
     * "content": "大家好，今天发布几张违法抓拍图片。",
     * "address": "广东省·深圳市·龙华区·",
     * "likes": 0,
     * "likeStatus": false,
     * "applications": 0,
     * "applicationStatus": false,
     * "isTopic": false,
     * "comments": 0,
     * "isAnonymous": true,
     * "distance": 94813.12283768077,
     * "dataType": "1",
     * "attacheFileUrlList": [
     * "http://127.0.0.1:9000/find/res/images/73/20210721/1626861897466/dcf127e2-5417-4e9a-bd9d-5a6d54897f3c.jpg",
     * "http://127.0.0.1:9000/find/res/images/73/20210721/1626861897466/23cfe24d-5daf-475e-af0f-1cdc49b3ac99.jpg",
     * "http://127.0.0.1:9000/find/res/images/73/20210721/1626861897466/1f638073-f95d-4509-9b69-9b89c3413924.jpg",
     * "http://127.0.0.1:9000/find/res/images/73/20210721/1626861897466/0ed716bb-9245-4128-b0f3-bdb4cd1ecf1e.jpg"
     * ]
     * },
     * {
     * "userId": 73,
     * "headUrl": "http://127.0.0.1:9000/find/img/head/73/05.png",
     * "nickname": "如风",
     * "publishTime": "2021-07-21 17:51:11",
     * "dynamicInfoId": 749,
     * "content": "大家好，今天发布几张违法抓拍图片。",
     * "address": "广东省·深圳市·龙华区·",
     * "likes": 0,
     * "likeStatus": false,
     * "applications": 0,
     * "applicationStatus": false,
     * "isTopic": false,
     * "comments": 0,
     * "isAnonymous": true,
     * "distance": 94813.12283768077,
     * "dataType": "1",
     * "attacheFileUrlList": [
     * "http://127.0.0.1:9000/find/res/images/73/20210721/1626861071731/0224b827-48a7-43b4-ad26-333ea10a4c92.jpg",
     * "http://127.0.0.1:9000/find/res/images/73/20210721/1626861071731/f8a66c9d-3aa1-4f30-af94-6b1d6f43dc08.jpg",
     * "http://127.0.0.1:9000/find/res/images/73/20210721/1626861071731/6cd8e532-a4f8-4621-89f3-fb4e83091500.jpg",
     * "http://127.0.0.1:9000/find/res/images/73/20210721/1626861071731/7fe9e0ee-9a68-4ead-be76-415b836a0c58.jpg"
     * ]
     * },
     * {
     * "userId": 73,
     * "headUrl": "http://127.0.0.1:9000/find/img/head/73/05.png",
     * "nickname": "如风",
     * "publishTime": "2021-07-21 17:50:06",
     * "dynamicInfoId": 748,
     * "content": "今天心情很烦躁的，不知道为什么？",
     * "likes": 0,
     * "likeStatus": false,
     * "applications": 0,
     * "applicationStatus": false,
     * "isTopic": false,
     * "comments": 0,
     * "isAnonymous": false,
     * "distance": 104359.96525414106,
     * "dataType": "1",
     * "attacheFileUrlList": [
     * "http://127.0.0.1:9000/find/res/images/73/20210721/1626861006826/7dcbfbba-3790-46af-a228-c43aa5fc2721.jpg"
     * ]
     * },
     * {
     * "userId": 144,
     * "headUrl": "http://127.0.0.1:9000/find/img/head/144/fc3fe05b-6ca8-49fe-863c-31593879e124.jpg",
     * "nickname": "季婉",
     * "publishTime": "2021-07-21 17:46:37",
     * "dynamicInfoId": 747,
     * "content": "大家好，今天发布几张违法抓拍图片。",
     * "address": "广东省·深圳市·龙华区·",
     * "likes": 0,
     * "likeStatus": false,
     * "applications": 0,
     * "applicationStatus": false,
     * "isTopic": false,
     * "comments": 0,
     * "isAnonymous": true,
     * "distance": 94813.12283768077,
     * "dataType": "1",
     * "attacheFileUrlList": [
     * "http://127.0.0.1:9000/find/res/images/144/20210721/1626860797870/021c6104-a1e8-4811-a3d9-dcf0bcc2ab3a.jpg",
     * "http://127.0.0.1:9000/find/res/images/144/20210721/1626860797870/1279cab3-5bc1-4719-a199-fe66a6b8ae39.jpg",
     * "http://127.0.0.1:9000/find/res/images/144/20210721/1626860797870/c8077e24-ffb2-42ec-bdbb-7dee206152bc.jpg",
     * "http://127.0.0.1:9000/find/res/images/144/20210721/1626860797870/3ba9a02c-71bc-49ce-8ffb-46667fa14af8.jpg"
     * ]
     * },
     * {
     * "userId": 144,
     * "headUrl": "http://127.0.0.1:9000/find/img/head/144/fc3fe05b-6ca8-49fe-863c-31593879e124.jpg",
     * "nickname": "季婉",
     * "publishTime": "2021-07-21 17:43:58",
     * "dynamicInfoId": 746,
     * "content": "大家好，今天发布几张违法抓拍图片。",
     * "address": "广东省·深圳市·龙华区·",
     * "likes": 0,
     * "likeStatus": false,
     * "applications": 0,
     * "applicationStatus": false,
     * "isTopic": false,
     * "comments": 0,
     * "isAnonymous": true,
     * "distance": 94813.12283768077,
     * "dataType": "1",
     * "attacheFileUrlList": [
     * "http://127.0.0.1:9000/find/res/images/144/20210721/1626860638180/6877cde6-c4db-4ca9-8134-5c26334c3446.jpg",
     * "http://127.0.0.1:9000/find/res/images/144/20210721/1626860638180/4923990b-3c0b-4a57-9784-52ba6edc4527.jpg",
     * "http://127.0.0.1:9000/find/res/images/144/20210721/1626860638180/068d982b-5e37-447f-8134-4a154ded53de.jpg",
     * "http://127.0.0.1:9000/find/res/images/144/20210721/1626860638180/b659c879-26cf-49a9-b419-9e17437e9c75.jpg"
     * ]
     * },
     * {
     * "userId": 144,
     * "headUrl": "http://127.0.0.1:9000/find/img/head/144/fc3fe05b-6ca8-49fe-863c-31593879e124.jpg",
     * "nickname": "季婉",
     * "publishTime": "2021-07-21 17:33:09",
     * "dynamicInfoId": 744,
     * "content": "大家好，今天发布几张违法抓拍图片。",
     * "address": "广东省·深圳市·龙华区·",
     * "likes": 0,
     * "likeStatus": false,
     * "applications": 0,
     * "applicationStatus": false,
     * "isTopic": false,
     * "comments": 0,
     * "isAnonymous": true,
     * "distance": 94813.12283768077,
     * "dataType": "1",
     * "attacheFileUrlList": [
     * "http://127.0.0.1:9000/find/res/images/144/20210721/1626859989359/f6445ce4-829c-45f9-912b-299c2065576c.jpg",
     * "http://127.0.0.1:9000/find/res/images/144/20210721/1626859989359/4dba0a11-27fd-493e-8b59-a83c3251a0ac.jpg",
     * "http://127.0.0.1:9000/find/res/images/144/20210721/1626859989359/c04b4347-400d-4be9-9dd7-5f30953d0f8f.jpg",
     * "http://127.0.0.1:9000/find/res/images/144/20210721/1626859989359/37b9835b-b527-40fa-851b-f07254e80a22.jpg"
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
            @RequestParam(name = "ip", required = false) @ApiParam(name = "ip", value = "客户端IP", example = "183.14.135.215") String ip,//客户端IP
            @RequestParam(name = "longitude", required = false) @ApiParam(name = "longitude", value = "经度", example = "113.962941") Double longitude,//经度
            @RequestParam(name = "latitude", required = false) @ApiParam(name = "latitude", value = "纬度", example = "22.462714") Double latitude,//纬度
            @RequestParam(name = "pageNum", required = false, defaultValue = "1") @ApiParam(name = "pageNum", value = "当前页码", example = "1") Integer pageNum,
            @RequestParam(name = "pageSize", required = false, defaultValue = "20") @ApiParam(name = "pageSize", value = "每页数量", example = "20") Integer pageSize) {
        return this.dynamicFeignClient.list(userId, ip, longitude, latitude, pageNum, pageSize);
    }

    /**
     * @api {get} http://127.0.0.1:8084/find/dynamic/{id}/screen 筛选动态内容列表接口
     * @apiVersion 1.0.0
     * @apiGroup 动态模块API
     * @apiName 筛选动态内容列表
     * @apiParam (接口请求参数) {long} id 用户id
     * @apiParam (接口请求参数) {string} [ip] 客户端IP，不能与定位（经纬度）同时为空，否则获取不到距离
     * @apiParam (接口请求参数) {double} [longitude] 定位（经度）
     * @apiParam (接口请求参数) {double} [latitude] 定位（纬度）
     * @apiParam (接口请求参数) {int} [pageNum] 当前页数，默认：第1页
     * @apiParam (接口请求参数) {int} [pageSize] 每页条数，默认：每页20条
     * @apiParam (接口请求参数) {string={"0", "1", "2"}} [gender] 性别，0->女生，1->男生，2->全部，默认：如果用户注册选择是0->男生，则筛选值：1->女生，反之，如果用户注册选择是1->女生，则筛选值为：0->男生
     * @apiParam (接口请求参数) {int} [minAge] 年龄范围（最小值），默认：16
     * @apiParam (接口请求参数) {int} [maxAge] 年龄范围（最大值），默认：35
     * @apiParam (接口请求参数) {string[]={"水瓶座","双鱼座","白羊座","金牛座","双子座","巨蟹座","狮子座","处女座","天秤座","天蝎座","射手座","摩羯座"}} [constellation] 星座列表，默认：null，不限->不传此参数，此参数为：null，全部->不传此参数，此参数为：null
     * @apiParam (接口请求参数) {string={"0","1","2"}} [dataType] 附件类型，默认：0->全部，1->图片或者图片+文字，2->语音或者语音+文字
     * @apiParam (接口请求参数) {string[]} [provinceList] 省份列表，例如：广东省, 四川省
     * @apiParam (接口请求参数) {string[]} [cityList] 城市列表，例如：深圳市, 广州市, 成都市, 攀枝花市
     * @apiParam (接口请求参数) {long} industryId 行业id
     * @apiParam (接口请求参数) {long} [professionId] 职业id
     * @apiParam (接口请求参数) {string[]} [tags] 标签列表，例如：音乐, 篮球, 二次元
     * @apiParamExample {json} 请求示例01
     * HTTP/1.1 OK
     * curl -v -X GET http://127.0.0.1:8084/find/dynamic/71/screen?industryId=1&ip=183.14.134.172&pageNum=1&pageSize=20&gender=0&minAge=16&maxAge=39&constellation=巨蟹座,水瓶座&dataType=0&provinceList=广东省,广西省,湖南省&cityList=深圳市,广州市,南宁市,长沙市
     * @apiSuccess (200) {int{0-65535}} status 响应状态码
     * @apiSuccess (200) {long{0-500}} code 信息码
     * @apiSuccess (200) {string{..255}} msg 说明
     * @apiSuccess (200) {object} [data] 数据
     * @apiSuccess (200) {int} [totalPage] 总页数
     * @apiSuccess (200) {object[]} [hots] 三条热门话题列表
     * @apiSuccess (200) {int} [totalCount] 参与话题的动态数量
     * @apiSuccess (200) {string} [topicTitle] 话题标题
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
     * @apiSuccess (200) {boolean} [isTopic] 是否话题，true->是，false->否
     * @apiSuccess (200) {string} [topicTitle] 话题标题
     * @apiSuccess (200) {int} [comments] 评论数
     * @apiSuccess (200) {boolean} [isAnonymous] 是否匿名发布，true->是，false->否
     * @apiSuccess (200) {double} [distance] 当前位置距发布动态定位的距离（单位（米））
     * @apiSuccess (200) {string} [dataTye] 附件文件类型，1->图片，2->语音
     * @apiSuccess (200) {string[]} [attacheFileUrlList] 附件文件地址列表
     * @apiSuccessExample {json} 200响应示例01
     * HTTP/1.1 200 OK
     * {
     * "status": 200,
     * "code": 0,
     * "msg": "筛选发布动态内容信息列表成功。",
     * "data": {
     * "totalPage": 5,
     * "hots": [
     * {
     * "totalCount": 5,
     * "topicTitle": "#电动车交规"
     * },
     * {
     * "totalCount": 4,
     * "topicTitle": "#球长防骗课堂"
     * },
     * {
     * "totalCount": 2,
     * "topicTitle": "#懒癌生存守则"
     * }
     * ],
     * "list": [
     * {
     * "userId": 73,
     * "headUrl": "http://127.0.0.1:9000/find/img/head/73/05.png",
     * "nickname": "如风",
     * "publishTime": "2021-07-22 11:52:05",
     * "dynamicInfoId": 757,
     * "content": "今天心情很烦躁的，不知道为什么？",
     * "likes": 0,
     * "likeStatus": false,
     * "applications": 0,
     * "applicationStatus": false,
     * "isTopic": false,
     * "comments": 0,
     * "isAnonymous": false,
     * "distance": 0.030315003677945207,
     * "dataType": "2",
     * "attacheFileUrlList": [
     * "http://127.0.0.1:9000/find/res/voices/73/20210722/1626925925504/8377a2cc-e6c5-416f-957a-46292d096650.mp3"
     * ]
     * },
     * {
     * "userId": 73,
     * "headUrl": "http://127.0.0.1:9000/find/img/head/73/05.png",
     * "nickname": "如风",
     * "publishTime": "2021-07-22 11:22:37",
     * "dynamicInfoId": 756,
     * "content": "今天心情很烦躁的，不知道为什么？",
     * "likes": 0,
     * "likeStatus": false,
     * "applications": 0,
     * "applicationStatus": false,
     * "isTopic": false,
     * "comments": 0,
     * "isAnonymous": false,
     * "distance": 0.030315003677945207,
     * "dataType": "2",
     * "attacheFileUrlList": [
     * "http://127.0.0.1:9000/find/res/voices/73/20210722/1626924157741/b7d2c5cf-15b9-49e2-b31a-789ffdc7ad5d.mp3"
     * ]
     * },
     * {
     * "userId": 73,
     * "headUrl": "http://127.0.0.1:9000/find/img/head/default.png",
     * "nickname": "匿名",
     * "publishTime": "2021-07-21 18:04:57",
     * "dynamicInfoId": 750,
     * "content": "大家好，今天发布几张违法抓拍图片。",
     * "address": "广东省·深圳市·龙华区·",
     * "likes": 0,
     * "likeStatus": false,
     * "applications": 0,
     * "applicationStatus": false,
     * "isTopic": false,
     * "comments": 0,
     * "isAnonymous": true,
     * "distance": 26084.37755145445,
     * "dataType": "1",
     * "attacheFileUrlList": [
     * "http://127.0.0.1:9000/find/res/images/73/20210721/1626861897466/dcf127e2-5417-4e9a-bd9d-5a6d54897f3c.jpg",
     * "http://127.0.0.1:9000/find/res/images/73/20210721/1626861897466/23cfe24d-5daf-475e-af0f-1cdc49b3ac99.jpg",
     * "http://127.0.0.1:9000/find/res/images/73/20210721/1626861897466/1f638073-f95d-4509-9b69-9b89c3413924.jpg",
     * "http://127.0.0.1:9000/find/res/images/73/20210721/1626861897466/0ed716bb-9245-4128-b0f3-bdb4cd1ecf1e.jpg"
     * ]
     * },
     * {
     * "userId": 73,
     * "headUrl": "http://127.0.0.1:9000/find/img/head/default.png",
     * "nickname": "匿名",
     * "publishTime": "2021-07-21 17:51:11",
     * "dynamicInfoId": 749,
     * "content": "大家好，今天发布几张违法抓拍图片。",
     * "address": "广东省·深圳市·龙华区·",
     * "likes": 0,
     * "likeStatus": false,
     * "applications": 0,
     * "applicationStatus": false,
     * "isTopic": false,
     * "comments": 0,
     * "isAnonymous": true,
     * "distance": 26084.37755145445,
     * "dataType": "1",
     * "attacheFileUrlList": [
     * "http://127.0.0.1:9000/find/res/images/73/20210721/1626861071731/0224b827-48a7-43b4-ad26-333ea10a4c92.jpg",
     * "http://127.0.0.1:9000/find/res/images/73/20210721/1626861071731/f8a66c9d-3aa1-4f30-af94-6b1d6f43dc08.jpg",
     * "http://127.0.0.1:9000/find/res/images/73/20210721/1626861071731/6cd8e532-a4f8-4621-89f3-fb4e83091500.jpg",
     * "http://127.0.0.1:9000/find/res/images/73/20210721/1626861071731/7fe9e0ee-9a68-4ead-be76-415b836a0c58.jpg"
     * ]
     * },
     * {
     * "userId": 73,
     * "headUrl": "http://127.0.0.1:9000/find/img/head/73/05.png",
     * "nickname": "如风",
     * "publishTime": "2021-07-21 17:50:06",
     * "dynamicInfoId": 748,
     * "content": "今天心情很烦躁的，不知道为什么？",
     * "likes": 0,
     * "likeStatus": false,
     * "applications": 0,
     * "applicationStatus": false,
     * "isTopic": false,
     * "comments": 0,
     * "isAnonymous": false,
     * "distance": 0.030315003677945207,
     * "dataType": "1",
     * "attacheFileUrlList": [
     * "http://127.0.0.1:9000/find/res/images/73/20210721/1626861006826/7dcbfbba-3790-46af-a228-c43aa5fc2721.jpg"
     * ]
     * },
     * {
     * "userId": 73,
     * "headUrl": "http://127.0.0.1:9000/find/img/head/73/05.png",
     * "nickname": "如风",
     * "publishTime": "2021-07-21 16:57:03",
     * "dynamicInfoId": 740,
     * "content": "今天心情很烦躁的，不知道为什么？",
     * "likes": 0,
     * "likeStatus": false,
     * "applications": 0,
     * "applicationStatus": false,
     * "isTopic": false,
     * "comments": 0,
     * "isAnonymous": false,
     * "distance": 0.030315003677945207,
     * "dataType": "1",
     * "attacheFileUrlList": [
     * "http://127.0.0.1:9000/find/res/images/73\\20210721\\1626857823349\\c7b2399a-8dd0-4bac-935c-a2b74c53eae3.jpg"
     * ]
     * },
     * {
     * "userId": 73,
     * "headUrl": "http://127.0.0.1:9000/find/img/head/73/05.png",
     * "nickname": "如风",
     * "publishTime": "2021-07-21 16:51:42",
     * "dynamicInfoId": 739,
     * "content": "今天心情很烦躁的，不知道为什么？",
     * "likes": 0,
     * "likeStatus": false,
     * "applications": 0,
     * "applicationStatus": false,
     * "isTopic": false,
     * "comments": 0,
     * "isAnonymous": false,
     * "distance": 0.030315003677945207,
     * "dataType": "1",
     * "attacheFileUrlList": [
     * "http://127.0.0.1:9000/find/res/images/73\\20210721\\1626857502401\\d67870b3-4af6-4833-9093-1dd2ef757e44.jpg"
     * ]
     * },
     * {
     * "userId": 73,
     * "headUrl": "http://127.0.0.1:9000/find/img/head/73/05.png",
     * "nickname": "如风",
     * "publishTime": "2021-07-21 16:45:28",
     * "dynamicInfoId": 738,
     * "content": "今天心情很烦躁的，不知道为什么？",
     * "likes": 0,
     * "likeStatus": false,
     * "applications": 0,
     * "applicationStatus": false,
     * "isTopic": false,
     * "comments": 0,
     * "isAnonymous": false,
     * "distance": 0.030315003677945207,
     * "dataType": "1",
     * "attacheFileUrlList": [
     * "http://127.0.0.1:9000/find/res/images/73\\20210721\\1626857128615\\1626857128615e5e8e46a-4031-45dd-a49c-219eb8830a85.jpg"
     * ]
     * },
     * {
     * "userId": 73,
     * "headUrl": "http://127.0.0.1:9000/find/img/head/73/05.png",
     * "nickname": "如风",
     * "publishTime": "2021-07-21 16:38:41",
     * "dynamicInfoId": 737,
     * "content": "今天心情很烦躁的，不知道为什么？",
     * "likes": 0,
     * "likeStatus": false,
     * "applications": 0,
     * "applicationStatus": false,
     * "isTopic": false,
     * "comments": 0,
     * "isAnonymous": false,
     * "distance": 0.030315003677945207,
     * "dataType": "1",
     * "attacheFileUrlList": [
     * "http://127.0.0.1:9000/find/res/images/73\\20210721\\1626856721660\\9bcbd543-1784-4fa6-a9a0-462ecd240660.jpg"
     * ]
     * },
     * {
     * "userId": 73,
     * "headUrl": "http://127.0.0.1:9000/find/img/head/73/05.png",
     * "nickname": "如风",
     * "publishTime": "2021-07-21 16:25:41",
     * "dynamicInfoId": 736,
     * "content": "今天心情很烦躁的，不知道为什么？",
     * "likes": 0,
     * "likeStatus": false,
     * "applications": 0,
     * "applicationStatus": false,
     * "isTopic": false,
     * "comments": 0,
     * "isAnonymous": false,
     * "distance": 0.030315003677945207,
     * "dataType": "1",
     * "attacheFileUrlList": [
     * "http://127.0.0.1:9000/find/res/images/73\\20210721\\1626855941783\\70d90e8f-4ae1-4410-ab35-4e196713faf0.jpg"
     * ]
     * },
     * {
     * "userId": 138,
     * "headUrl": "http://127.0.0.1:9000/find/img/head/138/31b0b00e-f8c3-4e23-ba77-d7e50eafe17e.jpg",
     * "nickname": "阿妩",
     * "publishTime": "2021-04-29 10:27:24",
     * "dynamicInfoId": 707,
     * "content": "41",
     * "address": "广东省·深圳市·",
     * "likes": 0,
     * "likeStatus": false,
     * "applications": 3,
     * "applicationStatus": false,
     * "isTopic": true,
     * "topicTitle": "#健身",
     * "comments": 110,
     * "isAnonymous": false,
     * "dataType": "1",
     * "attacheFileUrlList": [
     * "http://127.0.0.1:9000/find/res/images/138/20210429/1619663244476/4d789742-acb9-4298-b74e-8618f9c9d2e4.jpg"
     * ]
     * },
     * {
     * "userId": 138,
     * "headUrl": "http://127.0.0.1:9000/find/img/head/138/31b0b00e-f8c3-4e23-ba77-d7e50eafe17e.jpg",
     * "nickname": "阿妩",
     * "publishTime": "2021-04-28 17:47:41",
     * "dynamicInfoId": 706,
     * "content": "40",
     * "address": "广东省·深圳市·",
     * "likes": 0,
     * "likeStatus": false,
     * "applications": 4,
     * "applicationStatus": false,
     * "isTopic": true,
     * "topicTitle": "#新疆棉",
     * "comments": 100,
     * "isAnonymous": false,
     * "dataType": "1",
     * "attacheFileUrlList": [
     * "http://127.0.0.1:9000/find/res/images/138/20210428/1619603261418/624f6488-d143-4847-8ef4-e41083624cdf.jpg"
     * ]
     * },
     * {
     * "userId": 138,
     * "headUrl": "http://127.0.0.1:9000/find/img/head/138/31b0b00e-f8c3-4e23-ba77-d7e50eafe17e.jpg",
     * "nickname": "阿妩",
     * "publishTime": "2021-04-27 15:26:10",
     * "dynamicInfoId": 705,
     * "content": "39",
     * "address": "广东省·深圳市·",
     * "likes": 0,
     * "likeStatus": false,
     * "applications": 2,
     * "applicationStatus": false,
     * "isTopic": true,
     * "topicTitle": "#新疆棉",
     * "comments": 20,
     * "isAnonymous": false,
     * "dataType": "1",
     * "attacheFileUrlList": [
     * "http://127.0.0.1:9000/find/res/images/138/20210427/1619508370907/c7996c45-5e47-4ee1-9a4c-e919bca57a0a.jpg"
     * ]
     * },
     * {
     * "userId": 138,
     * "headUrl": "http://127.0.0.1:9000/find/img/head/138/31b0b00e-f8c3-4e23-ba77-d7e50eafe17e.jpg",
     * "nickname": "阿妩",
     * "publishTime": "2021-04-26 16:04:17",
     * "dynamicInfoId": 704,
     * "content": "38",
     * "address": "广东省·深圳市·",
     * "likes": 0,
     * "likeStatus": false,
     * "applications": 0,
     * "applicationStatus": false,
     * "isTopic": true,
     * "topicTitle": "#国庆节",
     * "comments": 150,
     * "isAnonymous": false,
     * "dataType": "1",
     * "attacheFileUrlList": [
     * "http://127.0.0.1:9000/find/res/images/138/20210426/1619424257938/da8c46d6-7f86-4925-9505-83226836c951.jpg"
     * ]
     * },
     * {
     * "userId": 138,
     * "headUrl": "http://127.0.0.1:9000/find/img/head/138/31b0b00e-f8c3-4e23-ba77-d7e50eafe17e.jpg",
     * "nickname": "阿妩",
     * "publishTime": "2021-04-21 10:27:17",
     * "dynamicInfoId": 703,
     * "content": "33",
     * "address": "广东省·深圳市·",
     * "likes": 1,
     * "likeStatus": false,
     * "applications": 1,
     * "applicationStatus": false,
     * "isTopic": true,
     * "topicTitle": "#祖国100华诞",
     * "comments": 200,
     * "isAnonymous": false,
     * "dataType": "1",
     * "attacheFileUrlList": [
     * "http://127.0.0.1:9000/find/res/images/138/20210421/1618972037495/ff21b2ca-1409-4bb0-bcb2-5946efb685a4.jpg"
     * ]
     * },
     * {
     * "userId": 138,
     * "headUrl": "http://127.0.0.1:9000/find/img/head/138/31b0b00e-f8c3-4e23-ba77-d7e50eafe17e.jpg",
     * "nickname": "阿妩",
     * "publishTime": "2021-04-20 11:11:49",
     * "dynamicInfoId": 702,
     * "content": "34",
     * "address": "广东省·深圳市·",
     * "likes": 2,
     * "likeStatus": false,
     * "applications": 1,
     * "applicationStatus": false,
     * "isTopic": true,
     * "topicTitle": "#祖国100华诞",
     * "comments": 30,
     * "isAnonymous": false,
     * "dataType": "1",
     * "attacheFileUrlList": [
     * "http://127.0.0.1:9000/find/res/images/138/20210420/1618888309356/715cc6f1-f245-4a88-a363-db0a427b8620.jpg"
     * ]
     * },
     * {
     * "userId": 138,
     * "headUrl": "http://127.0.0.1:9000/find/img/head/138/31b0b00e-f8c3-4e23-ba77-d7e50eafe17e.jpg",
     * "nickname": "阿妩",
     * "publishTime": "2021-04-19 09:58:47",
     * "dynamicInfoId": 701,
     * "content": "33",
     * "address": "广东省·深圳市·",
     * "likes": 0,
     * "likeStatus": false,
     * "applications": 1,
     * "applicationStatus": false,
     * "isTopic": true,
     * "topicTitle": "#千古玦尘",
     * "comments": 200,
     * "isAnonymous": false,
     * "dataType": "1",
     * "attacheFileUrlList": [
     * "http://127.0.0.1:9000/find/res/images/138/20210419/1618797527797/4c23b018-e826-412a-b482-a069b341cdb4.jpg"
     * ]
     * },
     * {
     * "userId": 138,
     * "headUrl": "http://127.0.0.1:9000/find/img/head/138/31b0b00e-f8c3-4e23-ba77-d7e50eafe17e.jpg",
     * "nickname": "阿妩",
     * "publishTime": "2021-04-17 17:41:44",
     * "dynamicInfoId": 700,
     * "content": "32",
     * "address": "广东省·深圳市·",
     * "likes": 1,
     * "likeStatus": false,
     * "applications": 0,
     * "applicationStatus": false,
     * "isTopic": true,
     * "topicTitle": "#千古玦尘",
     * "comments": 20,
     * "isAnonymous": false,
     * "dataType": "1",
     * "attacheFileUrlList": [
     * "http://127.0.0.1:9000/find/res/images/138/20210417/1618652504495/48c4b6d3-47d3-4230-b487-9e6c0016c4bc.jpg"
     * ]
     * },
     * {
     * "userId": 138,
     * "headUrl": "http://127.0.0.1:9000/find/img/head/138/31b0b00e-f8c3-4e23-ba77-d7e50eafe17e.jpg",
     * "nickname": "阿妩",
     * "publishTime": "2021-04-16 16:16:15",
     * "dynamicInfoId": 699,
     * "content": "31",
     * "address": "广东省·深圳市·",
     * "likes": 1,
     * "likeStatus": false,
     * "applications": 2,
     * "applicationStatus": false,
     * "isTopic": true,
     * "topicTitle": "#找男朋友",
     * "comments": 109,
     * "isAnonymous": false,
     * "dataType": "1",
     * "attacheFileUrlList": [
     * "http://127.0.0.1:9000/find/res/images/138/20210416/1618560975204/bd9463ce-2e85-4e1e-9244-8301ac2b5c8f.jpg"
     * ]
     * },
     * {
     * "userId": 138,
     * "headUrl": "http://127.0.0.1:9000/find/img/head/138/31b0b00e-f8c3-4e23-ba77-d7e50eafe17e.jpg",
     * "nickname": "阿妩",
     * "publishTime": "2021-04-16 16:15:55",
     * "dynamicInfoId": 698,
     * "content": "30",
     * "address": "广东省·深圳市·",
     * "likes": 2,
     * "likeStatus": false,
     * "applications": 0,
     * "applicationStatus": false,
     * "isTopic": true,
     * "topicTitle": "#找男朋友",
     * "comments": 109,
     * "isAnonymous": false,
     * "dataType": "1",
     * "attacheFileUrlList": [
     * "http://127.0.0.1:9000/find/res/images/138/20210416/1618560955446/df93f78e-7305-427d-9245-03242e92dd72.jpg"
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
     * "path": "/find/dynamic/70/screen1"
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
            @RequestParam(name = "ip", required = false) @ApiParam(name = "ip", value = "客户端IP", example = "183.14.134.172") String ip,//客户端ip
            @RequestParam(name = "longitude", required = false) @ApiParam(name = "longitude", value = "定位（经度）", example = "113.24421") Double longitude,//经度
            @RequestParam(name = "latitude", required = false) @ApiParam(name = "latitude", value = "定位（纬度）", example = "23.12592") Double latitude,//纬度
            @RequestParam(name = "pageNum", required = false, defaultValue = "1") @ApiParam(name = "pageNum", value = "当前页码，默认：当前第1页", example = "1") Integer pageNum,    // 当前页码，默认：当前第1页
            @RequestParam(name = "pageSize", required = false, defaultValue = "20") @ApiParam(name = "pageSize", value = "每页数量，默认：每页20条", example = "20") Integer pageSize,// 每页数量，默认：每页20条
            @RequestParam(name = "gender", required = false) @ApiParam(name = "gender", value = "性别，0->女生，1->男生，2->全部", allowableValues = "0,1,2", example = "0") String gender, // 性别，0->女生，1->男生
            @RequestParam(name = "minAge", required = false, defaultValue = "16") @ApiParam(name = "minAge", value = "年龄范围（最小值），默认：16", example = "16") Integer minAge, // 年龄范围（最小值），默认：16岁
            @RequestParam(name = "maxAge", required = false, defaultValue = "35") @ApiParam(name = "maxAge", value = "年龄范围（最大值），默认：35", example = "35") Integer maxAge, // 年龄范围（最大值），默认：35岁
            @RequestParam(name = "constellation", required = false) @ApiParam(name = "constellation", value = "星座列表") List<String> constellations, // 星座
            @RequestParam(name = "dataType", required = false) @ApiParam(name = "dataType", value = "附件类型，0->全部，1->图片或者图片+文字，2->语音或者语音+文字，默认：0", allowableValues = "0,1,2", example = "0") String dataType, // 0->全部，1->图片或者图片+文字，2->语音或者语音+文字，默认：0
            @RequestParam(name = "provinceList", required = false) @ApiParam(name = "provinceList", value = "定位（省份）列表") List<String> provinceList, // 发布动态定位（省份）列表
            @RequestParam(name = "cityList", required = false) @ApiParam(name = "cityList", value = "定位（城市）列表") List<String> cityList,// 发布动态定位（城市）列表
            @RequestParam(name = "industryId") @ApiParam(name = "industryId", value = "行业Id") Long industryId, //行业Id
            @RequestParam(name = "professionId", required = false) @ApiParam(name = "professionId", value = "职业Id") Long professionId, //职业Id
            @RequestParam(name = "tags", required = false) @ApiParam(name = "tags", value = "标签列表") List<String> tagsList) { //标签列表
        return this.dynamicFeignClient.screen(userId, ip, longitude, latitude, pageNum, pageSize, gender, minAge, maxAge, constellations, dataType, provinceList, cityList, industryId, professionId, tagsList);
    }

    /**
     * @api {get} http://127.0.0.1:8084/find/dynamic/{id}/mylist 获取自己的动态内容列表接口
     * @apiVersion 1.0.0
     * @apiGroup 动态模块API
     * @apiName 获取自己的动态内容列表
     * @apiParam (接口请求参数) {long} id 用户id
     * @apiParam (接口请求参数) {int} [pageNum] 当前页数，默认：第1页
     * @apiParam (接口请求参数) {int} [pageSize] 每页条数，默认：每页20条
     * @apiParamExample {json} 请求示例
     * HTTP/1.1 OK
     * curl -v -X GET http://127.0.0.1:8084/find/dynamic/144/mylist?pageNum=1&pageSize=20
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
     * @apiSuccess (200) {boolean} [isTopic] 是否话题，true->是，false->否
     * @apiSuccess (200) {string} [topicTitle] 话题标题
     * @apiSuccess (200) {int} [comments] 评论数
     * @apiSuccess (200) {boolean} [isAnonymous] 是否匿名发布，true->是，false->否
     * @apiSuccess (200) {string} [dataTye] 附件文件类型，1->图片，2->语音
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
     * "userId": 144,
     * "headUrl": "http://127.0.0.1:9000/find/img/head/144/fc3fe05b-6ca8-49fe-863c-31593879e124.jpg",
     * "nickname": "季婉",
     * "publishTime": "2021-07-21 17:46:37",
     * "dynamicInfoId": 747,
     * "content": "大家好，今天发布几张违法抓拍图片。",
     * "address": "广东省·深圳市",
     * "likes": 0,
     * "likeStatus": false,
     * "applications": 0,
     * "applicationStatus": false,
     * "isTopic": false,
     * "comments": 0,
     * "isAnonymous": true,
     * "dataType": "1",
     * "attacheFileUrlList": [
     * "http://127.0.0.1:9000/find/res/images/144/20210721/1626860797870/021c6104-a1e8-4811-a3d9-dcf0bcc2ab3a.jpg",
     * "http://127.0.0.1:9000/find/res/images/144/20210721/1626860797870/1279cab3-5bc1-4719-a199-fe66a6b8ae39.jpg",
     * "http://127.0.0.1:9000/find/res/images/144/20210721/1626860797870/c8077e24-ffb2-42ec-bdbb-7dee206152bc.jpg",
     * "http://127.0.0.1:9000/find/res/images/144/20210721/1626860797870/3ba9a02c-71bc-49ce-8ffb-46667fa14af8.jpg"
     * ]
     * },
     * {
     * "userId": 144,
     * "headUrl": "http://127.0.0.1:9000/find/img/head/144/fc3fe05b-6ca8-49fe-863c-31593879e124.jpg",
     * "nickname": "季婉",
     * "publishTime": "2021-07-21 17:43:58",
     * "dynamicInfoId": 746,
     * "content": "大家好，今天发布几张违法抓拍图片。",
     * "address": "广东省·深圳市",
     * "likes": 0,
     * "likeStatus": false,
     * "applications": 0,
     * "applicationStatus": false,
     * "isTopic": false,
     * "comments": 0,
     * "isAnonymous": true,
     * "dataType": "1",
     * "attacheFileUrlList": [
     * "http://127.0.0.1:9000/find/res/images/144/20210721/1626860638180/6877cde6-c4db-4ca9-8134-5c26334c3446.jpg",
     * "http://127.0.0.1:9000/find/res/images/144/20210721/1626860638180/4923990b-3c0b-4a57-9784-52ba6edc4527.jpg",
     * "http://127.0.0.1:9000/find/res/images/144/20210721/1626860638180/068d982b-5e37-447f-8134-4a154ded53de.jpg",
     * "http://127.0.0.1:9000/find/res/images/144/20210721/1626860638180/b659c879-26cf-49a9-b419-9e17437e9c75.jpg"
     * ]
     * },
     * {
     * "userId": 144,
     * "headUrl": "http://127.0.0.1:9000/find/img/head/144/fc3fe05b-6ca8-49fe-863c-31593879e124.jpg",
     * "nickname": "季婉",
     * "publishTime": "2021-07-21 17:33:09",
     * "dynamicInfoId": 744,
     * "content": "大家好，今天发布几张违法抓拍图片。",
     * "address": "广东省·深圳市",
     * "likes": 0,
     * "likeStatus": false,
     * "applications": 0,
     * "applicationStatus": false,
     * "isTopic": false,
     * "comments": 0,
     * "isAnonymous": true,
     * "dataType": "1",
     * "attacheFileUrlList": [
     * "http://127.0.0.1:9000/find/res/images/144/20210721/1626859989359/f6445ce4-829c-45f9-912b-299c2065576c.jpg",
     * "http://127.0.0.1:9000/find/res/images/144/20210721/1626859989359/4dba0a11-27fd-493e-8b59-a83c3251a0ac.jpg",
     * "http://127.0.0.1:9000/find/res/images/144/20210721/1626859989359/c04b4347-400d-4be9-9dd7-5f30953d0f8f.jpg",
     * "http://127.0.0.1:9000/find/res/images/144/20210721/1626859989359/37b9835b-b527-40fa-851b-f07254e80a22.jpg"
     * ]
     * },
     * {
     * "userId": 144,
     * "headUrl": "http://127.0.0.1:9000/find/img/head/144/fc3fe05b-6ca8-49fe-863c-31593879e124.jpg",
     * "nickname": "季婉",
     * "publishTime": "2021-07-21 17:09:42",
     * "dynamicInfoId": 742,
     * "content": "发几张美女照片看看。",
     * "likes": 0,
     * "likeStatus": false,
     * "applications": 0,
     * "applicationStatus": false,
     * "isTopic": false,
     * "comments": 0,
     * "isAnonymous": false,
     * "dataType": "1",
     * "attacheFileUrlList": [
     * "http://127.0.0.1:9000/find/res/images/144/20210721/1626858582879/7e632d43-1f3a-4e0c-b531-22847a46304f.jpg"
     * ]
     * },
     * {
     * "userId": 144,
     * "headUrl": "http://127.0.0.1:9000/find/img/head/144/fc3fe05b-6ca8-49fe-863c-31593879e124.jpg",
     * "nickname": "季婉",
     * "publishTime": "2021-07-21 17:01:57",
     * "dynamicInfoId": 741,
     * "content": "发几张美女照片看看。",
     * "likes": 0,
     * "likeStatus": false,
     * "applications": 0,
     * "applicationStatus": false,
     * "isTopic": false,
     * "comments": 0,
     * "isAnonymous": false,
     * "dataType": "1",
     * "attacheFileUrlList": [
     * "http://127.0.0.1:9000/find/res/images/144/20210721/1626858117646/9d06c224-47fa-4915-9351-4ed8c872db82.jpg"
     * ]
     * },
     * {
     * "userId": 144,
     * "headUrl": "http://127.0.0.1:9000/find/img/head/144/fc3fe05b-6ca8-49fe-863c-31593879e124.jpg",
     * "nickname": "季婉",
     * "publishTime": "2021-04-30 17:14:10",
     * "dynamicInfoId": 708,
     * "content": "测试",
     * "address": "广东省·深圳市",
     * "likes": 0,
     * "likeStatus": false,
     * "applications": 0,
     * "applicationStatus": false,
     * "isTopic": true,
     * "topicTitle": "#找女朋友",
     * "comments": 120,
     * "isAnonymous": false,
     * "dataType": "1",
     * "attacheFileUrlList": [
     * "http://127.0.0.1:9000/find/res/images/144/20210430/1619774050374/a1259f31-3131-4f8c-a99b-3f8120ec2bfa.jpg"
     * ]
     * },
     * {
     * "userId": 144,
     * "headUrl": "http://127.0.0.1:9000/find/img/head/144/fc3fe05b-6ca8-49fe-863c-31593879e124.jpg",
     * "nickname": "季婉",
     * "publishTime": "2021-03-31 15:36:44",
     * "dynamicInfoId": 658,
     * "content": "测试",
     * "address": "广东省·深圳市",
     * "likes": 1,
     * "likeStatus": false,
     * "applications": 0,
     * "applicationStatus": false,
     * "isTopic": true,
     * "topicTitle": "#找男朋友",
     * "comments": 109,
     * "isAnonymous": false,
     * "dataType": "1",
     * "attacheFileUrlList": [
     * "http://127.0.0.1:9000/find/res/images/144/20210331/1617176204339/a76d12a2-d430-4155-9a79-a5f6485b0544.jpeg"
     * ]
     * },
     * {
     * "userId": 144,
     * "headUrl": "http://127.0.0.1:9000/find/img/head/144/fc3fe05b-6ca8-49fe-863c-31593879e124.jpg",
     * "nickname": "季婉",
     * "publishTime": "2021-03-31 15:36:44",
     * "dynamicInfoId": 659,
     * "content": "测试",
     * "address": "广东省·深圳市",
     * "likes": 2,
     * "likeStatus": false,
     * "applications": 0,
     * "applicationStatus": false,
     * "isTopic": true,
     * "topicTitle": "#找男朋友",
     * "comments": 109,
     * "isAnonymous": false,
     * "dataType": "1",
     * "attacheFileUrlList": [
     * "http://127.0.0.1:9000/find/res/images/144/20210331/1617176204466/bbdfdece-081d-4eaf-81d1-79855d6d39bc.jpeg"
     * ]
     * },
     * {
     * "userId": 144,
     * "headUrl": "http://127.0.0.1:9000/find/img/head/144/fc3fe05b-6ca8-49fe-863c-31593879e124.jpg",
     * "nickname": "季婉",
     * "publishTime": "2021-03-31 15:35:07",
     * "dynamicInfoId": 656,
     * "content": "二哥",
     * "address": "广东省·深圳市",
     * "likes": 1,
     * "likeStatus": false,
     * "applications": 1,
     * "applicationStatus": false,
     * "isTopic": true,
     * "topicTitle": "#找男朋友",
     * "comments": 109,
     * "isAnonymous": false,
     * "dataType": "1",
     * "attacheFileUrlList": [
     * "http://127.0.0.1:9000/find/res/images/144/20210331/1617176107913/b7eb7189-346c-44e1-8fb4-41d84b44b2c4.jpeg"
     * ]
     * },
     * {
     * "userId": 144,
     * "headUrl": "http://127.0.0.1:9000/find/img/head/144/fc3fe05b-6ca8-49fe-863c-31593879e124.jpg",
     * "nickname": "季婉",
     * "publishTime": "2021-03-31 15:35:07",
     * "dynamicInfoId": 657,
     * "content": "二哥",
     * "address": "广东省·深圳市",
     * "likes": 0,
     * "likeStatus": false,
     * "applications": 0,
     * "applicationStatus": false,
     * "isTopic": true,
     * "topicTitle": "#找男朋友",
     * "comments": 109,
     * "isAnonymous": false,
     * "dataType": "1",
     * "attacheFileUrlList": [
     * "http://127.0.0.1:9000/find/res/images/144/20210331/1617176108009/9212a7c2-535e-44cc-9f4b-bd53e44b01e3.jpeg"
     * ]
     * },
     * {
     * "userId": 144,
     * "headUrl": "http://127.0.0.1:9000/find/img/head/144/fc3fe05b-6ca8-49fe-863c-31593879e124.jpg",
     * "nickname": "季婉",
     * "publishTime": "2021-03-31 15:34:01",
     * "dynamicInfoId": 652,
     * "content": "呃呃呃",
     * "address": "广东省·深圳市",
     * "likes": 0,
     * "likeStatus": false,
     * "applications": 0,
     * "applicationStatus": false,
     * "isTopic": true,
     * "topicTitle": "#找男朋友",
     * "comments": 109,
     * "isAnonymous": false,
     * "dataType": "1",
     * "attacheFileUrlList": [
     * "http://127.0.0.1:9000/find/res/images/144/20210331/1617176041174/f53b5070-ba5e-4d8d-8b15-b6d6f73a50c8.jpeg"
     * ]
     * },
     * {
     * "userId": 144,
     * "headUrl": "http://127.0.0.1:9000/find/img/head/144/fc3fe05b-6ca8-49fe-863c-31593879e124.jpg",
     * "nickname": "季婉",
     * "publishTime": "2021-03-31 15:34:01",
     * "dynamicInfoId": 653,
     * "content": "呃呃呃",
     * "address": "广东省·深圳市",
     * "likes": 1,
     * "likeStatus": false,
     * "applications": 0,
     * "applicationStatus": false,
     * "isTopic": true,
     * "topicTitle": "#找男朋友",
     * "comments": 109,
     * "isAnonymous": false,
     * "dataType": "1",
     * "attacheFileUrlList": [
     * "http://127.0.0.1:9000/find/res/images/144/20210331/1617176041285/0956f1b1-201b-4288-9c4d-c898814b0533.jpeg"
     * ]
     * },
     * {
     * "userId": 144,
     * "headUrl": "http://127.0.0.1:9000/find/img/head/144/fc3fe05b-6ca8-49fe-863c-31593879e124.jpg",
     * "nickname": "季婉",
     * "publishTime": "2021-03-31 15:33:35",
     * "dynamicInfoId": 650,
     * "content": "打算",
     * "address": "广东省·深圳市",
     * "likes": 0,
     * "likeStatus": false,
     * "applications": 0,
     * "applicationStatus": false,
     * "isTopic": true,
     * "topicTitle": "#找男朋友",
     * "comments": 109,
     * "isAnonymous": false,
     * "dataType": "1",
     * "attacheFileUrlList": [
     * "http://127.0.0.1:9000/find/res/images/144/20210331/1617176015267/1b0909a3-697a-4c53-91cf-9b7146360181.jpeg"
     * ]
     * },
     * {
     * "userId": 144,
     * "headUrl": "http://127.0.0.1:9000/find/img/head/144/fc3fe05b-6ca8-49fe-863c-31593879e124.jpg",
     * "nickname": "季婉",
     * "publishTime": "2021-03-31 15:33:35",
     * "dynamicInfoId": 651,
     * "content": "打算",
     * "address": "广东省·深圳市",
     * "likes": 0,
     * "likeStatus": false,
     * "applications": 0,
     * "applicationStatus": false,
     * "isTopic": true,
     * "topicTitle": "#找男朋友",
     * "comments": 109,
     * "isAnonymous": false,
     * "dataType": "1",
     * "attacheFileUrlList": [
     * "http://127.0.0.1:9000/find/res/images/144/20210331/1617176015395/71b9fdaf-dd61-413c-b6d3-e7cea0b7d905.jpeg"
     * ]
     * },
     * {
     * "userId": 144,
     * "headUrl": "http://127.0.0.1:9000/find/img/head/144/fc3fe05b-6ca8-49fe-863c-31593879e124.jpg",
     * "nickname": "季婉",
     * "publishTime": "2021-03-31 15:32:38",
     * "dynamicInfoId": 649,
     * "content": "测试",
     * "address": "广东省·深圳市",
     * "likes": 0,
     * "likeStatus": false,
     * "applications": 0,
     * "applicationStatus": false,
     * "isTopic": true,
     * "topicTitle": "#找男朋友",
     * "comments": 109,
     * "isAnonymous": false,
     * "dataType": "1",
     * "attacheFileUrlList": [
     * "http://127.0.0.1:9000/find/res/images/144/20210331/1617175958506/88afb745-172f-487b-9712-f9f33747119d.jpeg"
     * ]
     * },
     * {
     * "userId": 144,
     * "headUrl": "http://127.0.0.1:9000/find/img/head/144/fc3fe05b-6ca8-49fe-863c-31593879e124.jpg",
     * "nickname": "季婉",
     * "publishTime": "2021-03-31 15:30:59",
     * "dynamicInfoId": 648,
     * "content": "测试",
     * "likes": 0,
     * "likeStatus": false,
     * "applications": 0,
     * "applicationStatus": false,
     * "isTopic": true,
     * "topicTitle": "#找男朋友",
     * "comments": 109,
     * "isAnonymous": false,
     * "dataType": "1",
     * "attacheFileUrlList": [
     * "http://127.0.0.1:9000/find/res/images/144/20210331/1617175859511/b99c7a95-59d6-4780-8310-3462181b4c4f.jpeg"
     * ]
     * },
     * {
     * "userId": 144,
     * "headUrl": "http://127.0.0.1:9000/find/img/head/144/fc3fe05b-6ca8-49fe-863c-31593879e124.jpg",
     * "nickname": "季婉",
     * "publishTime": "2021-03-30 20:22:25",
     * "dynamicInfoId": 644,
     * "content": "测试",
     * "address": "广东省·深圳市",
     * "likes": 4,
     * "likeStatus": false,
     * "applications": 0,
     * "applicationStatus": false,
     * "isTopic": true,
     * "topicTitle": "#找男朋友",
     * "comments": 109,
     * "isAnonymous": false,
     * "dataType": "1",
     * "attacheFileUrlList": [
     * "http://127.0.0.1:9000/find/res/images/144/20210330/1617106945829/aee822d2-ee9c-4fff-8597-464e15906c2c.jpg"
     * ]
     * },
     * {
     * "userId": 144,
     * "headUrl": "http://127.0.0.1:9000/find/img/head/144/fc3fe05b-6ca8-49fe-863c-31593879e124.jpg",
     * "nickname": "季婉",
     * "publishTime": "2021-03-10 20:09:10",
     * "dynamicInfoId": 620,
     * "content": "测试",
     * "address": "广东省·深圳市",
     * "likes": 1,
     * "likeStatus": false,
     * "applications": 2,
     * "applicationStatus": false,
     * "isTopic": true,
     * "topicTitle": "#找男朋友",
     * "comments": 109,
     * "isAnonymous": false,
     * "dataType": "1",
     * "attacheFileUrlList": [
     * "http://127.0.0.1:9000/find/res/images/144/20210310/1615378150059/40ab5a01-de81-4e88-8f80-ff4d110142ec.jpg",
     * "http://127.0.0.1:9000/find/res/images/144/20210310/1615378150059/77acf396-1fcf-48a6-aed3-5f7b335568d3.jpg"
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
