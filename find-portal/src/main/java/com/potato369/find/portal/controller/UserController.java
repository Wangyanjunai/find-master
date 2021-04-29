package com.potato369.find.portal.controller;

import com.potato369.find.common.api.CommonResult;
import com.potato369.find.common.dto.BlacklistDTO;
import com.potato369.find.common.dto.ReportInfoDTO;
import com.potato369.find.common.dto.UpdateUserDTO;
import com.potato369.find.common.vo.ReportCategoryVO;
import com.potato369.find.common.vo.UserVO2;
import com.potato369.find.portal.feign.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@Api(value = "user-controller", tags = "用户信息模块Restful API")
@Slf4j
@RestController
@RequestMapping("/user")
@Profile({"dev", "dev2", "test"})
public class UserController {
	
	private UserService userFeignClient;

	@Autowired
	public void setUserFeignClient(UserService userFeignClient) {
		this.userFeignClient = userFeignClient;
	}
	
	//上报或者更新极光推送唯一设备的标识接口
	/**
     * @api {put} http://8.135.36.45:8084/find/user/{id}/uploadRegId 上报极光推送设备标识接口
     * @apiVersion 1.0.0
     * @apiGroup 用户模块API
     * @apiName 上报极光推送设备标识
     * @apiParam (接口请求参数) {long} id 用户id
     * @apiParam (接口请求参数) {string} regId 极光推送唯一设备标识
     * @apiParamExample {json} 请求示例
     * HTTP/1.1 OK
     * curl -v -X PUT http://8.135.36.45:8084/find/user/70/uploadRegId?regId=1507bfd3f76139cd43a
     * @apiSuccess (200) {int{0-65535}} status 响应状态码
     * @apiSuccess (200) {long{0-500}} code 信息码
     * @apiSuccess (200) {string{..255}} msg 说明
     * @apiSuccess (200) {object} [data] 数据
     * @apiSuccess (200) {string={"OK", "FAILED"}} [UPLOADREGID] 上报状态，OK->“成功”，FAILED->“失败”
     * @apiSuccessExample {json} 200响应示例
     * HTTP/1.1 200 OK
		{
		    "status": 200,
		    "code": 0,
		    "msg": "返回数据成功",
		    "data": {
		        "UPLOADREGID": "OK",
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
      		"msg": "未找到用户信息",
      }
     * @apiError (404) {int{0-65535}} status 响应状态码
     * @apiError (404) {long{0-500}} code 消息码
     * @apiError (404) {String} msg 说明
     * @apiErrorExample {json} 404错误
     * HTTP/1.1 404 404响应
     * {
     * 		"status": 404,
     * 		"code": 200,
     * 		"msg": "接口未注册",
     * }
     * @apiError (500) {int{0-65535}} status 响应状态码
     * @apiError (500) {long{0-500}} code 消息码
     * @apiError (500) {String} msg 说明
     * @apiErrorExample {json} 500 错误
     * {
     * 		"status": 500,
     * 		"code": 205,
     * 		"msg": "服务器未响应"
     * }
     */
	@ApiOperation(value = "上报极光推送设备标识接口", notes = "用于用户进行上报极光推送设备标识提交信息。")
	@PutMapping(value = "/{id}/uploadRegId")
	public CommonResult<Map<String, Object>> uploadRegId(
			@PathVariable(name = "id") @ApiParam(name = "id", value = "用户id", example = "1", required = true) Long userId, 
			@RequestParam(name = "regId") @ApiParam("极光推送唯一设备标识") String regId) {
		return this.userFeignClient.uploadRegId(userId, regId);
	}
	
	//修改头像接口
	/**
     * @api {put} http://8.135.36.45:8084/find/user/{id}/head 修改头像接口
     * @apiVersion 1.0.0
     * @apiGroup 用户模块API
     * @apiName 修改头像
     * @apiParam (接口请求参数) {long} id 用户id
     * @apiParam (接口请求参数) {file} headIconFile 头像图片文件
     * @apiParamExample {json} 请求示例
     * HTTP/1.1 OK
      {
      		"headIconFile":"D:\Program\Resources\find\img\head\head01.png"
      }
     * @apiSuccess (200) {int{0-65535}} status 响应状态码
     * @apiSuccess (200) {long{0-500}} code 信息码
     * @apiSuccess (200) {string{..255}} msg 说明
     * @apiSuccess (200) {object} [data] 数据
     * @apiSuccess (200) {file} [head] 头像图片地址
     * @apiSuccess (200) {long} [id] 用户id
     * @apiSuccessExample {json} 200响应示例
     * HTTP/1.1 200 OK
		{
		    "status": 200,
		    "code": 0,
		    "msg": "返回数据成功",
		    "data": {
		        "head": "http://8.135.36.45:8000/find/img/head/2b9c022d-ec00-497c-9626-813add17b877_admin069.jpg",
		        "id": 1
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
      		"msg": "未找到用户信息",
      }
     * @apiError (404) {int{0-65535}} status 响应状态码
     * @apiError (404) {long{0-500}} code 消息码
     * @apiError (404) {String} msg 说明
     * @apiErrorExample {json} 404错误
     * HTTP/1.1 404 404响应
     * {
     * 		"status": 404,
     * 		"code": 200,
     * 		"msg": "接口未注册",
     * }
     * @apiError (500) {int{0-65535}} status 响应状态码
     * @apiError (500) {long{0-500}} code 消息码
     * @apiError (500) {String} msg 说明
     * @apiErrorExample {json} 500 错误
     * {
     * 		"status": 500,
     * 		"code": 205,
     * 		"msg": "服务器未响应"
     * }
     */
	@ApiOperation(value = "修改头像接口", notes = "用于用户进行提交修改头像图片信息。")
	@PutMapping(value = "/{id}/head", consumes = {"multipart/form-data;charset=utf-8"}, produces = {"application/json;charset=utf-8"})
	public CommonResult<Map<String, Object>> head(
			@PathVariable(name = "id") @ApiParam(name = "id", value = "用户id", example = "1", required = true) Long userId, 
			@RequestPart(name = "headIconFile") @ApiParam("头像图片文件") MultipartFile headFile) {
		return this.userFeignClient.head(userId, headFile);
	}
	
	//修改背景图接口
	/**
     * @api {put} http://8.135.36.45:8084/find/user/{id}/background 修改背景图接口
     * @apiVersion 1.0.0
     * @apiGroup 用户模块API
     * @apiName 修改背景图
     * @apiParam (接口请求参数) {long} id 用户id
     * @apiParam (接口请求参数) {file} backgroundIconFile 背景图片文件
     * @apiParamExample {json} 请求示例
     * HTTP/1.1 OK
      {
      		"backgroundIconFile":"D:\Program\Resources\find\img\head\bg02.png"
      }
     * @apiSuccess (200) {int{0-65535}} status 响应状态码
     * @apiSuccess (200) {long{0-500}} code 消息码
     * @apiSuccess (200) {string{..255}} msg 说明
     * @apiSuccess (200) {object} [data] 数据
     * @apiSuccess (200) {string} [head] 背景图片地址
     * @apiSuccess (200) {long} [id] 用户id
     * @apiSuccessExample {json} 200响应示例
     * HTTP/1.1 200 OK
		{
		    "status": 200,
		    "code": 0,
		    "msg": "返回数据成功",
		    "data": {
		        "bg": "http://8.135.36.45:8000/find/img/head/2b9c022d-ec00-497c-9626-813add17b877_admin069.jpg",
		        "id": 1
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
      		"msg": "未找到用户信息",
      }
     * @apiError (404) {int{0-65535}} status 响应状态码
     * @apiError (404) {long{0-500}} code 消息码
     * @apiError (404) {String} msg 说明
     * @apiErrorExample {json} 404错误
     * HTTP/1.1 404 404响应
     * {
     * 		"status": 404,
     * 		"code": 200,
     * 		"msg": "接口未注册",
     * }
     * @apiError (500) {int{0-65535}} status 响应状态码
     * @apiError (500) {long{0-500}} code 消息码
     * @apiError (500) {String} msg 说明
     * @apiErrorExample {json} 500 错误
     * HTTP/1.1 500 500响应
     * {
     * 		"status": 500,
     * 		"code": 205,
     * 		"msg": "服务器未响应"
     * }
     */
	@ApiOperation(value = "修改背景图接口", notes = "用于用户进行提交修改背景图片信息。")
	@PutMapping(value = "/{id}/background", consumes = {"multipart/form-data;charset=utf-8"}, produces = {"application/json;charset=utf-8"})
	public CommonResult<Map<String, Object>> background(
			@PathVariable(name = "id") @ApiParam(name = "id", value = "用户id", example = "1", required = true) Long userId, 
			@RequestPart(name = "backgroundIconFile") @ApiParam("背景图片文件") MultipartFile headFile) {
		return this.userFeignClient.background(userId, headFile);
	}
	
	//用户注册接口
	/**
     * @api {post} http://8.135.36.45:8084/find/user/reg 用户注册接口
     * @apiVersion 1.0.0
     * @apiGroup 用户模块API
     * @apiName 用户注册
     * @apiParam (接口请求参数) {string {11}} phone 手机号码
     * @apiParam (接口请求参数) {string {16}} [ip] 客户端IP不能与定位（国家、省份，城市）同时为空
     * @apiParam (接口请求参数) {string {1} ="0", "1"}} gender 性别，0->女生；1->男生
     * @apiParam (接口请求参数) {string {1..16}} [platform] 平台
     * @apiParam (接口请求参数) {string {0..32}} nickname 昵称
     * @apiParam (接口请求参数) {string {0..128}} weixinId 微信号
     * @apiParam (接口请求参数) {string {0..64}} [imei] 设备串码
     * @apiParam (接口请求参数) {string {0..64}} [model] 设备型号
     * @apiParam (接口请求参数) {string {0..16}} [sysName] 系统名称
     * @apiParam (接口请求参数) {string {0..16}} [sysCode] 系统版本
     * @apiParam (接口请求参数) {string {0..4}} [networkMode] 网络方式
     * @apiParam (接口请求参数) {string {0..4}} year 出生年份
     * @apiParam (接口请求参数) {string {0..2}} month 出生月份
     * @apiParam (接口请求参数) {string {0..2}} date 出生日期
     * @apiParam (接口请求参数) {string={"水瓶座","双鱼座","白羊座","金牛座","双子座","巨蟹座","狮子座","处女座","天秤座","天蝎座","射手座","摩羯座"}} constellation 星座
     * @apiParam (接口请求参数) {string {0..16}} [country] 定位（国家）
     * @apiParam (接口请求参数) {string {0..32}} [province] 定位（省份）
     * @apiParam (接口请求参数) {string {0..32}} [city] 定位（城市）
     * @apiParam (接口请求参数) {string {0..255}} [autograph] 签名/发布动态内容
     * @apiParam (接口请求参数) {file} head 头像图片文件
     * @apiParamExample {json} 请求示例01（注册01）
     * HTTP/1.1 OK 封装表单数据格式01 注：form表单提交，需要在请求头加：“Content-Type=multipart/form-data;charset=utf-8”
  		"phone" : "18138812110",
  		"ip" : "183.14.29.70",
 		"gender" : "1",
  		"platform" : "Baidu",
  		"nickname" : "张三",
  		"weixinId" : "wx123456788",
  		"imei" : "123456788222",
  		"model" : "vivo Y55",
  		"sysName" : "Android",
  		"sysCode" : "6.0.1",
 		"networkMode" : "WIFI",
  		"year" : "1995",
  		"month" : "05",
  		"date" : "05",
  		"constellation" : "巨蟹座",
 		"autograph" : "新人来到，多多关照，谢谢！",
 		"head":"D:\Program\Resources\find\img\head\02.png"
     * @apiParamExample {json} 请求示例02（注册02）
     * HTTP/1.1 OK 封装表单数据格式02 注：form表单提交，需要在请求头加：“Content-Type=multipart/form-data;charset=utf-8”
  		"phone" : "18138812310",
  		"ip" : "123.84.129.170",
  		"gender" : "0",
  		"platform" : "HUAWEI",
  		"nickname" : "李四",
  		"weixinId" : "wx123456700",
  		"imei" : "123456788111",
  		"model" : "vivo Z5",
  		"sysName" : "Android",
  		"sysCode" : "9.0.1",
  		"networkMode" : "WIFI",
  		"year" : "1993",
  		"month" : "05",
  		"date" : "05",
  		"constellation" : "巨蟹座",
  		"autograph" : "新人来到，多多关照，谢谢！",
  		"country" : "中国",
  		"province" : "广东",
  		"city" : "广州",
  		"head":"D:\Program\Resources\find\img\head\02.png"
     * @apiSuccess (200) {int{0-65535}} status 响应状态码
     * @apiSuccess (200) {long{0-500}} code 消息码
     * @apiSuccess (200) {string{..255}} msg 说明
     * @apiSuccess (200) {object} [data] 数据
     * @apiSuccess (200) {object} [user] 用户数据
     * @apiSuccess (200) {long} [id] 用户id
     * @apiSuccess (200) {string} [gender] 性别
     * @apiSuccess (200) {string} [nickname] 昵称
     * @apiSuccess (200) {string} [head] 头像图片地址
     * @apiSuccess (200) {string} [bg] 背景图片地址
     * @apiSuccess (200) {string} [autograph] 签名
     * @apiSuccessExample {json} 200响应示例
     * HTTP/1.1 200 OK
		{
		    "status": 200,
		    "code": 0,
		    "msg": "登录或者注册成功。",
		    "data": {
		        "user": {
		            "id": 21,
		            "gender": "1",
		            "nickname": "张三",
		            "head": "http://8.135.36.45:8000/find/img/head/head.png",
		            "bg": "http://8.135.36.45:8000/find/img/background/bg.png",
		            "autograph": "新人小生，初来乍到，请多关照。"
		        }
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
      		"msg": "未找到用户信息！",
      }
     * @apiError (404) {int{0-65535}} status 响应状态码
     * @apiError (404) {long{0-500}} code 消息码
     * @apiError (404) {String} msg 说明
     * @apiErrorExample {json} 404错误
     * HTTP/1.1 404 404响应
      {
      		"status": 404,
     		"code": 200,
      		"msg": "接口未注册！",
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
	@ApiOperation(value = "用户注册接口", notes = "用于用户进行注册提交信息。")
	@PostMapping(value = "/reg",consumes = {"multipart/form-data;charset=utf-8"}, produces = {"application/json;charset=utf-8"})
	public CommonResult<Map<String, Object>> reg(
			@RequestParam(name = "phone") @ApiParam("手机号码") String phone, // phone：手机号码
            @RequestParam(name = "ip", required = false) @ApiParam("客户端IP") String ip, // ip：客户端IP
            @RequestParam(name = "gender", required = false) @ApiParam("性别") String gender, // gender：性别
            @RequestParam(name = "platform", required = false) @ApiParam("平台") String platform, // platform：平台
            @RequestParam(name = "nickname", required = false) @ApiParam("昵称") String nickname, // nickname：昵称
            @RequestParam(name = "weixinId", required = false) @ApiParam("微信号") String weixinId, // weixinId：微信号
            @RequestParam(name = "imei", required = false) @ApiParam("设备串码") String imei, // imei：设备串码
            @RequestParam(name = "model", required = false) @ApiParam("设备型号") String model, // model：设备型号 
            @RequestParam(name = "sysName", required = false) @ApiParam("系统名称") String sysName, // sysName：系统名称
            @RequestParam(name = "sysCode", required = false) @ApiParam("系统版本") String sysCode, // sysCode：系统版本
            @RequestParam(name = "networkMode", required = false) @ApiParam("上网方式") String networkMode, // networkMode：上网方式
            @RequestParam(name = "year", required = false) @ApiParam("出生年份") String year, // year：出生年份
            @RequestParam(name = "month", required = false) @ApiParam("出生月份") String month, // month：出生月份
            @RequestParam(name = "date", required = false) @ApiParam("出生日期") String date, // date：出生日期
            @RequestParam(name = "constellation", required = false) @ApiParam("星座") String constellation, // constellation：星座
            @RequestParam(name = "country", required = false) @ApiParam("国家") String country, // country：国家
            @RequestParam(name = "province", required = false) @ApiParam("省份") String province, // province：省份
            @RequestParam(name = "city", required = false) @ApiParam("城市") String city, // city：城市
            @RequestParam(name = "autograph", required = false) @ApiParam("签名/动态内容") String autograph, // autograph：签名/动态内容
            @RequestPart(value = "head", required = false) @ApiParam("头像图片文件") MultipartFile head) { // head：头像图片文件
		log.info("phone={}, ip={}, gender={}, platform={}, nickname={}, weixinId={}, imei={}, model={}, sysName={}, sysCode={}, networkMode={}, year={}, month={}, date={}, constellation={}, country={}, province={}, city={}, autograph={}, head={}", phone, ip, gender, platform, nickname, weixinId, imei, model, sysName, sysCode, networkMode, year, month, date, constellation, country, province, city, autograph, head);
		return this.userFeignClient.register(phone, ip, gender, platform, nickname, weixinId, imei, model, sysName, sysCode, networkMode, year, month, date, constellation, country, province, city, autograph, head);
	}
	
	//用户登录接口
	/**
     * @api {put} http://8.135.36.45:8084/find/user/login 用户登录接口
     * @apiVersion 1.0.0
     * @apiGroup 用户模块API
     * @apiName 用户登录
     * @apiParam (接口请求参数) {string {11}} phone 手机号码
     * @apiParam (接口请求参数) {string {16}} [ip] 客户端IP不能与定位（国家、省份，城市）同时为空
     * @apiParam (接口请求参数) {string {0..16}} [country] 定位（国家）
     * @apiParam (接口请求参数) {string {0..32}} [province] 定位（省份）
     * @apiParam (接口请求参数) {string {0..32}} [city] 定位（城市）
     * @apiParamExample {json} 请求示例01（手机号码和客户端IP登录）
     * HTTP/1.1 OK
     * curl --insecure -X PUT -v http://8.135.36.45:8084/find/user/login?phone=18138812310&ip=183.14.29.70
     * @apiParamExample {json} 请求示例02（手机号码和定位地址登录）
     * HTTP/1.1 OK
     * curl --insecure -X PUT -v http://8.135.36.45:8084/find/user/login?phone=18138812236&country=中国&province=广东省&city=深圳市		
     * @apiSuccess (200) {int{0-65535}} status 响应状态码
     * @apiSuccess (200) {long{0-500}} code 消息码
     * @apiSuccess (200) {string{..255}} msg 说明
     * @apiSuccess (200) {object} [data] 数据
     * @apiSuccess (200) {object} [user] 用户数据
     * @apiSuccess (200) {long} [id] 用户id
     * @apiSuccess (200) {string} [gender] 性别
     * @apiSuccess (200) {string} [nickname] 昵称
     * @apiSuccess (200) {string} [head] 头像图片地址
     * @apiSuccess (200) {string} [bg] 背景图片地址
     * @apiSuccess (200) {string} [autograph] 签名
     * @apiSuccessExample {json} 200响应示例
     * HTTP/1.1 200 OK
		{
		    "status": 200,
		    "code": 0,
		    "msg": "登录成功。",
		    "data": {
		        "user": {
		            "id": 21,
		            "gender": "1",
		            "nickname": "张三",
		            "head": "http://8.135.36.45:8000/find/img/head/head.png",
		            "bg": "http://8.135.36.45:8000/find/img/background/bg.png",
		            "autograph": "新人小生，初来乍到，请多关照。"
		        }
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
      		"msg": "未找到用户信息！",
      }
     * @apiError (404) {int{0-65535}} status 响应状态码
     * @apiError (404) {long{0-500}} code 消息码
     * @apiError (404) {String} msg 说明
     * @apiErrorExample {json} 404错误
     * HTTP/1.1 404 404响应
      {
      		"status": 404,
     		"code": 200,
      		"msg": "接口未注册！",
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
	@ApiOperation(value = "用户登录接口", notes = "用于用户进行登录提交信息。")
	@PutMapping(value = "/login")
	public CommonResult<Map<String, UserVO2>> login(
            @RequestParam(name = "phone") @ApiParam("手机号码") String phone, // phone：手机号码
            @RequestParam(name = "ip", required = false) @ApiParam("客户端IP") String ip, // ip：客户端IP
            @RequestParam(name = "country", required = false) @ApiParam("国家") String country, // country：国家
            @RequestParam(name = "province", required = false) @ApiParam("省份") String province, // province：省份
            @RequestParam(name = "city", required = false) @ApiParam("城市") String city) { //city：城市
		return this.userFeignClient.login(phone, ip, country, province, city);
	}
	
	/**
     * @api {get} http://8.135.36.45:8084/find/user/isreg?phone={手机号码} 判断手机号是否注册接口
     * @apiVersion 1.0.0
     * @apiGroup 用户模块API
     * @apiName 判断手机号是否注册
     * @apiParam (接口请求参数) {string {11}} phone 手机号码，必须是11位数字符合要求的手机号码
     * @apiParamExample {json} 请求示例（判断手机号是否注册）
     * HTTP/1.1 OK
     * curl -v -X GET http://8.135.36.45:8084/find/user/isreg?phone=18138802541
     * @apiSuccess (200) {int{0-65535}} status 响应状态码
     * @apiSuccess (200) {long{0-500}} code 消息码
     * @apiSuccess (200) {string{..255}} msg 说明
     * @apiSuccess (200) {object} [data] 数据
     * @apiSuccess (200) {boolean="true", "false"} [isReg] 用户是否已经注册，true->已经注册，false->还未注册
     * @apiSuccessExample {json} 200响应示例
     * HTTP/1.1 200 OK
		{
		    "status": 200,
		    "code": 0,
		    "msg": "SUCCESS，还未注册。",
		    "data": {
		        "isReg": false
		    }
		}
     * @apiSuccessExample {json} 200响应示例
     * HTTP/1.1 200 OK
		{
		    "status": 200,
		    "code": 0,
		    "msg": "SUCCESS，已经注册。",
		    "data": {
		        "isReg": true
		    }
		}		
     * @apiError (404) {int{0-65535}} status 响应状态码
     * @apiError (404) {long{0-500}} code 消息码
     * @apiError (404) {String} msg 说明
     * @apiErrorExample {json} 404错误
     * HTTP/1.1 404 404响应
     * {
     * 		"status": 404,
     * 		"code": 200,
     * 		"msg": "接口未注册",
     * }
     * @apiError (500) {int{0-65535}} status 响应状态码
     * @apiError (500) {long{0-500}} code 消息码
     * @apiError (500) {String} msg 说明
     * @apiErrorExample {json} 500错误
     * HTTP/1.1 500 500响应
     * {
     * 		"status": 500,
     * 		"code": 205,
     * 		"msg": "服务器未响应"
     * }
     */
	@ApiOperation(value = "是否已经注册接口", notes = "用于进行判断用户手机号码是否已经注册提交信息。")
	@GetMapping(value = "/isreg")
	public CommonResult<Map<String, Boolean>> isReg(
			@RequestParam(name = "phone") @ApiParam("手机号码") String phone) {
		return this.userFeignClient.isRegister(phone);
	}
	
	/**
     * @api {put} http://8.135.36.45:8084/find/user/{id}/update 更新用户资料接口
     * @apiVersion 1.0.0
     * @apiGroup 用户模块API
     * @apiName 更新用户资料
     * @apiParam (接口请求参数) {long} id 用户id
     * @apiParam (接口请求参数) {string} [nickname] 昵称
     * @apiParam (接口请求参数) {string} [weixinId] 微信号
     * @apiParam (接口请求参数) {string {0..4}} [year] 出生年代
     * @apiParam (接口请求参数) {string {0..2}} [month] 出生月份
     * @apiParam (接口请求参数) {string {0..2}} [date] 出生日期
     * @apiParam (接口请求参数) {string {0..4}} [constellation={"水瓶座","双鱼座","白羊座","金牛座","双子座","巨蟹座","狮子座","处女座","天秤座","天蝎座","射手座","摩羯座"}] 星座
     * @apiParam (接口请求参数) {string} [autograph] 签名
     * @apiParamExample {json} 请求示例 修改昵称
     * HTTP/1.1 OK
     * curl --insecure -X PUT -v http://8.135.36.45:8084/find/user/{id}/update -H "Content-Type: application/json;;charset=UTF-8" -d '{"nickname":"王6"}'
     * @apiParamExample {json} 请求示例 修改微信号
     * HTTP/1.1 OK
     * curl --insecure -X PUT -v http://8.135.36.45:8084/find/user/{id}/update -H "Content-Type: application/json;charset=UTF-8" -d '{"weixinId":"12622"}'
     * @apiParamExample {json} 请求示例 修改出生年月日和星座
     * HTTP/1.1 OK
     * curl --insecure -X PUT -v http://8.135.36.45:8084/find/user/{id}/update -H "Content-Type: application/json;charset=UTF-8" -d '{"year":"1996", "month":"08", "date":"03", "constellation":"双子座"}'
     * @apiParamExample {json} 请求示例 修改签名
     * HTTP/1.1 OK
     * curl --insecure -X PUT -v http://8.135.36.45:8084/find/user/{id}/update -H "Content-Type: application/json;charset=UTF-8" -d '{"autograph":"12622"}'
     * @apiParamExample {json} 请求示例 修改签名和出生年月日，星座
     * HTTP/1.1 OK
     * curl --insecure -X PUT -v http://8.135.36.45:8084/find/user/{id}/update -H "Content-Type: application/json;charset=UTF-8" -d '{"autograph":"126我的2", "year":"1996", "month":"08", "date":"03", "constellation":"水平座"}'
     * @apiParamExample {json} 请求示例 修改签名，出生年月日，星座，昵称，微信号
     * HTTP/1.1 OK
     * curl --insecure -X PUT -v http://8.135.36.45:8084/find/user/{id}/update -H "Content-Type: application/json;charset=UTF-8" -d '{"nickname":"王666", "weixinId":"12622www", "autograph":"126我的ss2", "year":"1996", "month":"08", "date":"03", "constellation":"射手座"}'
     * @apiSuccess (200) {int{0-65535}} status 响应状态码
     * @apiSuccess (200) {long{0-500}} code 消息码
     * @apiSuccess (200) {string{..255}} msg 说明
     * @apiSuccess (200) {object} [data] 数据
     * @apiSuccessExample {json} 200响应示例
     * HTTP/1.1 200 OK
		{
		    "status": 200,
		    "code": 0,
		    "msg": "修改或者更新用户资料成功",
		    "data": {
		        "UPDATE": "OK"
		    }
		}		
     * @apiError (404) {int{0-65535}} status 响应状态码
     * @apiError (404) {long{0-500}} code 消息码
     * @apiError (404) {String} msg 说明
     * @apiErrorExample {json} 404错误
     * HTTP/1.1 404 404响应
     * {
     * 		"status": 404,
     * 		"code": 200,
     * 		"msg": "接口未注册",
     * }
     * @apiError (500) {int{0-65535}} status 响应状态码
     * @apiError (500) {long{0-500}} code 消息码
     * @apiError (500) {String} msg 说明
     * @apiErrorExample {json} 500错误
     * HTTP/1.1 500 500响应
     * {
     * 		"status": 500,
     * 		"code": 205,
     * 		"msg": "服务器未响应"
     * }
     */
	@ApiOperation(value = "更新用户资料接口", notes = "用于提交更新用户资料信息。")
	@PutMapping(value = "/{id}/update")
	public CommonResult<Map<String, Object>> update(
			@PathVariable(name = "id") @ApiParam(name = "id", value = "用户id", example = "1", required = true) Long userId,
			@RequestBody UpdateUserDTO updateUserDTO) {
		return this.userFeignClient.update(userId, updateUserDTO);
	}
	
	/**
     * @api {get} http://8.135.36.45:8084/find/user/{id}/query 查看用户资料接口
     * @apiVersion 1.0.0
     * @apiGroup 用户模块API
     * @apiName 查看用户资料
     * @apiParam (接口请求参数) {long} id 用户id
     * @apiParamExample {json} 请求示例 获取用户资料
     * HTTP/1.1 OK
     * curl --insecure -X GET -v http://8.135.36.45:8084/find/user/1/query
     * @apiSuccess (200) {int{0-65535}} status 响应状态码
     * @apiSuccess (200) {long{0-500}} code 消息码
     * @apiSuccess (200) {string{..255}} msg 说明
     * @apiSuccess (200) {object} [data] 数据
     * @apiSuccess (200) {object} [user] 用户数据
     * @apiSuccess (200) {long} [id] 用户id
     * @apiSuccess (200) {string} [nickname] 昵称
     * @apiSuccess (200) {int={"0", "1", "2"}} [grade] VIP等级，0->普通用户，1->VIP1等级用户，2->VIP2等级用户
     * @apiSuccess (200) {int} [age] 年龄
     * @apiSuccess (200) {string={"0", "1"}} [gender] 性别，"0"->女生，"1"->男生
     * @apiSuccess (200) {string} [head] 头像
     * @apiSuccess (200) {string} [bg] 背景图片
     * @apiSuccess (200) {string} [autograph] 签名
     * @apiSuccessExample {json} 200响应示例
     * HTTP/1.1 200 OK
		{
		    "status": 200,
		    "code": 0,
		    "msg": "查看用户个人资料成功",
		    "data": {
		        "user": {
		            "id": 1,
		            "nickname": "王666",
		            "head": "http://8.135.36.45:8000/find/img/head/2b9c022d-ec00-497c-9626-813add17b877_admin069.jpg",
		            "bg": "http://8.135.36.45:8000/find/img/background/a1bf6181-ebd0-43b4-8e91-761ec8fc83ab_admin055.jpg",
		            "grade": 0,
		            "age": 24,
		            "gender": "1",
		            "autograph": "126我的ss2"
		        }
		    }
		}		
     * @apiError (404) {int{0-65535}} status 响应状态码
     * @apiError (404) {long{0-500}} code 消息码
     * @apiError (404) {String} msg 说明
     * @apiErrorExample {json} 404错误
     * HTTP/1.1 404 404响应
     * {
     * 		"status": 404,
     * 		"code": 200,
     * 		"msg": "接口未注册",
     * }
     * @apiError (500) {int{0-65535}} status 响应状态码
     * @apiError (500) {long{0-500}} code 消息码
     * @apiError (500) {String} msg 说明
     * @apiErrorExample {json} 500错误
     * HTTP/1.1 500 500响应
     * {
     * 		"status": 500,
     * 		"code": 205,
     * 		"msg": "服务器未响应"
     * }
     */
	@ApiOperation(value = "查看用户资料接口", notes = "用户用于查看个人资料信息。")
	@GetMapping(value = "/{id}/query")
	public CommonResult<Map<String, Object>> query(@PathVariable(name = "id") @ApiParam(name = "id", value = "用户id", example = "1", required = true) Long userId) {
		return this.userFeignClient.query(userId);
	}
	
	/**
     * @api {get} http://8.135.36.45:8084/find/user/{id}/queryWeixin 查看用户微信号接口
     * @apiVersion 1.0.0
     * @apiGroup 用户模块API
     * @apiName 查看用户微信号
     * @apiParam (接口请求参数) {long} id 用户id
     * @apiParamExample {json} 请求示例 修改昵称
     * HTTP/1.1 OK
     * curl --insecure -X GET -v http://8.135.36.45:8084/find/user/1/queryWeixin
     * @apiSuccess (200) {int{0-65535}} status 响应状态码
     * @apiSuccess (200) {long{0-500}} code 消息码
     * @apiSuccess (200) {string{..255}} msg 说明
     * @apiSuccess (200) {object} [data] 数据
     * @apiSuccess (200) {object} [user] 数据
     * @apiSuccess (200) {string} [weixinId] 微信号
     * @apiSuccessExample {json} 200响应示例
     * HTTP/1.1 200 OK
		{
		    "status": 200,
		    "code": 0,
		    "msg": "查看用户个人资料成功",
		    "data": {
		        "user": {
		            "weixinId": 11111111
		        }
		    }
		}		
     * @apiError (404) {int{0-65535}} status 响应状态码
     * @apiError (404) {long{0-500}} code 消息码
     * @apiError (404) {String} msg 说明
     * @apiErrorExample {json} 404错误
     * HTTP/1.1 404 404响应
     * {
     * 		"status": 404,
     * 		"code": 200,
     * 		"msg": "接口未注册",
     * }
     * @apiError (500) {int{0-65535}} status 响应状态码
     * @apiError (500) {long{0-500}} code 消息码
     * @apiError (500) {String} msg 说明
     * @apiErrorExample {json} 500错误
     * HTTP/1.1 500 500响应
     * {
     * 		"status": 500,
     * 		"code": 205,
     * 		"msg": "服务器未响应"
     * }
     */
	@ApiOperation(value = "查看用户微信号接口", notes = "用于用户查看微信号信息。")
	@GetMapping(value = "/{id}/queryWeixin")
	public CommonResult<Map<String, Object>> queryWeixin(@PathVariable(name = "id") @ApiParam(name = "id", value = "用户id", example = "1", required = true) Long userId) {
		return this.userFeignClient.queryWeixin(userId);
	}
	
	/**
     * @api {get} http://8.135.36.45:8084/find/user/{id}/report-categories 获取用户举报类型接口
     * @apiVersion 1.0.0
     * @apiGroup 用户模块API
     * @apiName 获取用户举报类型
     * @apiParam (接口请求参数) {long} id 用户id
     * @apiParamExample {json} 请求示例 查看用户举报类型
     * HTTP/1.1 OK
     * curl --insecure -X GET -v http://8.135.36.45:8084/find/user/1/report-categories
     * @apiSuccess (200) {int{0-65535}} status 响应状态码
     * @apiSuccess (200) {long{0-500}} code 消息码
     * @apiSuccess (200) {string{..255}} msg 说明
     * @apiSuccess (200) {object} [data] 数据
     * @apiSuccess (200) {object[]} [list] 举报类型数据列表
     * @apiSuccess (200) {long} [id] 类型id
     * @apiSuccess (200) {string} [name] 类型名称
     * @apiSuccessExample {json} 200响应示例
     * HTTP/1.1 200 OK
		{
		    "status": 200,
		    "code": 0,
		    "msg": "获取用户举报类型列表成功",
		    "data": {
		        "list": [
		            {
		                "id": 1,
		                "name": "垃圾广告"
		            },
		            {
		                "id": 2,
		                "name": "色情低俗"
		            },
		            {
		                "id": 3,
		                "name": "政治敏感"
		            },
		            {
		                "id": 4,
		                "name": "恐怖暴力"
		            },
		            {
		                "id": 5,
		                "name": "违法暴力"
		            },
		            {
		                "id": 6,
		                "name": "自杀自残"
		            },
		            {
		                "id": 7,
		                "name": "侵犯个人信息"
		            },
		            {
		                "id": 8,
		                "name": "侵犯未成年人权益"
		            }
		        ]
		    }
		}	
     * @apiError (404) {int{0-65535}} status 响应状态码
     * @apiError (404) {long{0-500}} code 消息码
     * @apiError (404) {String} msg 说明
     * @apiErrorExample {json} 404错误
     * HTTP/1.1 404 404响应
     * {
     * 		"status": 404,
     * 		"code": 200,
     * 		"msg": "接口未注册",
     * }
     * @apiError (500) {int{0-65535}} status 响应状态码
     * @apiError (500) {long{0-500}} code 消息码
     * @apiError (500) {String} msg 说明
     * @apiErrorExample {json} 500错误
     * HTTP/1.1 500 500响应
     * {
     * 		"status": 500,
     * 		"code": 205,
     * 		"msg": "服务器未响应"
     * }
     */
	@ApiOperation(value = "获取用户举报类型接口", notes = "用户用于获取举报类型信息数据列表。")
	@GetMapping(value = "/{id}/report-categories")
	public CommonResult<Map<String, List<ReportCategoryVO>>> reportCategoryList(@PathVariable(name = "id") @ApiParam(name = "id", value = "用户id", example = "1", required = true) Long userId) {
		return this.userFeignClient.reportCategoryList(userId);
	}
	
	/**
     * @api {post} http://8.135.36.45:8084/find/user/{id}/report 用户举报接口
     * @apiVersion 1.0.0
     * @apiGroup 用户模块API
     * @apiName 用户举报
     * @apiParam (接口请求参数) {long} id 举报用户id
     * @apiParam (接口请求参数) {long} categoryId 举报类目id
     * @apiParam (接口请求参数) {int="1", "2"} reportType 举报类型，1->动态，2->用户
     * @apiParam (接口请求参数) {long} beingReportId 被举报用户id或者动态id
     * @apiParam (接口请求参数) {string} reportContent 举报内容
     * @apiParamExample {json} 请求示例 查看用户举报类型
     * HTTP/1.1 OK
     * curl --insecure -X POST -v http://8.135.36.45:8084/find/user/{id}/report -H "Content-Type: application/json;charset=UTF-8" -d '{"categoryId":1, "reportType":1, "beingReportId":3, "reportContent":"老是打广告dddddd+++++++！！！！！"}'
     * @apiSuccess (200) {int{0-65535}} status 响应状态码
     * @apiSuccess (200) {long{0-500}} code 消息码
     * @apiSuccess (200) {string{..255}} msg 说明
     * @apiSuccess (200) {object} [data] 数据
     * @apiSuccess (200) {string} [REPORTED] 举报消息
     * @apiSuccessExample {json} 200响应示例
     * HTTP/1.1 200 OK
		{
		    "status": 200,
		    "code": 0,
		    "msg": "记录用户举报内容成功。",
		    "data": {
		        "REPORTED": "OK"
		    }
		}	
     * @apiError (404) {int{0-65535}} status 响应状态码
     * @apiError (404) {long{0-500}} code 消息码
     * @apiError (404) {String} msg 说明
     * @apiErrorExample {json} 404错误
     * HTTP/1.1 404 404响应
     * {
     * 		"status": 404,
     * 		"code": 200,
     * 		"msg": "接口未注册",
     * }
     * @apiError (500) {int{0-65535}} status 响应状态码
     * @apiError (500) {long{0-500}} code 消息码
     * @apiError (500) {String} msg 说明
     * @apiErrorExample {json} 500错误
     * HTTP/1.1 500 500响应
     * {
     * 		"status": 500,
     * 		"code": 205,
     * 		"msg": "服务器未响应"
     * }
     */
	@ApiOperation(value = "用户举报接口", notes = "用于用户进行举报不良信息。")
	@PostMapping(value = "/{id}/report")
	public CommonResult<Map<String, Object>> report(
			@PathVariable(name = "id") @ApiParam(name = "id", value = "用户id", example = "1", required = true) Long userId, 
			@RequestBody @Valid ReportInfoDTO reportInfoDTO) {
		return this.userFeignClient.reportDynamic(userId, reportInfoDTO);
	}
	
	/**
     * @api {get} http://8.135.36.45:8084/find/user/{id}/blacklist?pageNum={pageNum}&pageSize={pageSize} 获取用户黑名单列表接口
     * @apiVersion 1.0.0
     * @apiGroup 用户模块API
     * @apiName 获取用户黑名单列表接口
     * @apiParam (接口请求参数) {long} id 用户id
     * @apiParam (接口请求参数) {int} [pageNum] 当前页数，默认值：1
     * @apiParam (接口请求参数) {int} [pageSize] 每页条数，默认值：10
     * @apiParamExample {json} 请求示例 查看用户举报类型
     * HTTP/1.1 OK
     * curl --insecure -X GET -v http://8.135.36.45:8084/find/user/1/blacklist?pageNum=1&pageSize=10
     * @apiSuccess (200) {int{0-65535}} status 响应状态码
     * @apiSuccess (200) {long{0-500}} code 消息码
     * @apiSuccess (200) {string{..255}} msg 说明
     * @apiSuccess (200) {object} [data] 数据
     * @apiSuccess (200) {int} [totalPage] 总页数
     * @apiSuccess (200) {object[]} [list] 黑名单数据列表
     * @apiSuccess (200) {long} [id] 用户id
     * @apiSuccess (200) {string} [nickname] 昵称
     * @apiSuccess (200) {string} [head] 头像图片地址
     * @apiSuccess (200) {string} [time] 加入时间
     * @apiSuccessExample {json} 200响应示例
     * HTTP/1.1 200 OK
		{
		    "status": 200,
		    "code": 0,
		    "msg": "获取用户黑名单列表成功。",
		    "data": {
		        "totalPage": 2,
		        "list": [
		            {
		                "id": 12,
		                "nickname": "陆蕙兰",
		                "head": "http://8.135.36.45:8000/find/img/head/12.jpg",
		                "time": "2021年01月22日 15:25:27"
		            },
		            {
		                "id": 11,
		                "nickname": "商梓珊",
		                "head": "http://8.135.36.45:8000/find/img/head/11.jpg",
		                "time": "2021年01月22日 15:24:51"
		            },
		            {
		                "id": 10,
		                "nickname": "赫飞雨",
		                "head": "http://8.135.36.45:8000/find/img/head/10.jpg",
		                "time": "2021年01月22日 15:24:47"
		            },
		            {
		                "id": 9,
		                "nickname": "寒烟",
		                "head": "http://8.135.36.45:8000/find/img/head/09.jpg",
		                "time": "2021年01月22日 15:24:42"
		            },
		            {
		                "id": 7,
		                "nickname": "勾高朗",
		                "head": "http://8.135.36.45:8000/find/img/head/07.jpg",
		                "time": "2021年01月22日 15:24:30"
		            },
		            {
		                "id": 6,
		                "nickname": "慕蕊",
		                "head": "http://8.135.36.45:8000/find/img/head/06.jpg",
		                "time": "2021年01月22日 15:24:24"
		            },
		            {
		                "id": 5,
		                "nickname": "庾音韵",
		                "head": "http://8.135.36.45:8000/find/img/head/05.jpg",
		                "time": "2021年01月22日 15:24:19"
		            },
		            {
		                "id": 4,
		                "nickname": "覃星宇",
		                "head": "http://8.135.36.45:8000/find/img/head/04.jpg",
		                "time": "2021年01月22日 15:24:14"
		            },
		            {
		                "id": 3,
		                "nickname": "致命德毒药",
		                "head": "http://8.135.36.45:8000/find/img/head/03.jpg",
		                "time": "2021年01月22日 15:24:08"
		            },
		            {
		                "id": 2,
		                "nickname": "千柳",
		                "head": "http://8.135.36.45:8000/find/img/head/02.jpg",
		                "time": "2021年01月22日 15:24:02"
		            }
		        ]
		    }
		}	
     * @apiError (404) {int{0-65535}} status 响应状态码
     * @apiError (404) {long{0-500}} code 消息码
     * @apiError (404) {String} msg 说明
     * @apiErrorExample {json} 404错误
     * HTTP/1.1 404 404响应
     * {
     * 		"status": 404,
     * 		"code": 200,
     * 		"msg": "接口未注册",
     * }
     * @apiError (500) {int{0-65535}} status 响应状态码
     * @apiError (500) {long{0-500}} code 消息码
     * @apiError (500) {String} msg 说明
     * @apiErrorExample {json} 500错误
     * HTTP/1.1 500 500响应
     * {
     * 		"status": 500,
     * 		"code": 205,
     * 		"msg": "服务器未响应"
     * }
     */
	@ApiOperation(value = "获取用户黑名单列表接口", notes = "用于用户获取黑名单列表信息。")
	@GetMapping(value = "/{id}/blacklist")
	public CommonResult<Map<String, Object>> blackList(@PathVariable(name = "id") @ApiParam(name = "id", value = "用户id", example = "1", required = true) Long userId) {
		return this.userFeignClient.blackList(userId);
	}
	
	
	/**
     * @api {post} http://8.135.36.45:8084/find/user/{id}/pushOrPull 拉入推出黑名单接口
     * @apiVersion 1.0.0
     * @apiGroup 用户模块API
     * @apiName 拉入推出黑名单接口
     * @apiParam (接口请求参数) {long} id 用户id
     * @apiParam (接口请求参数) {long} blackUserId 黑名单用户id
     * @apiParam (接口请求参数) {int} type 奇数->拉入，偶数->推出
     * @apiParamExample {json} 请求示例
     * HTTP/1.1 OK
     * curl --insecure -X POST -v http://8.135.36.45:8084/find/user/1/pushOrPull -H "Content-Type: application/json;charset=UTF-8" -d '{"blackUserId":2, "type":0}'
     * @apiSuccess (200) {int{0-65535}} status 响应状态码
     * @apiSuccess (200) {long{0-500}} code 消息码
     * @apiSuccess (200) {string{..255}} msg 说明
     * @apiSuccess (200) {object} [data] 数据
     * @apiSuccess (200) {object} [PULL] 推出状态
     * @apiSuccess (200) {object} [PUSH] 拉入状态
     * @apiSuccessExample {json} 200响应示例 推出黑名单列表
     * HTTP/1.1 200 OK
		{
		    "status": 200,
		    "code": 0,
		    "msg": "将用户千柳推出黑名单列表成功。",
		    "data": {
		        "PULL": "OK"
		    }
		}
     * @apiSuccessExample {json} 200响应示例 拉入黑名单列表
     * HTTP/1.1 200 OK
		{
		    "status": 200,
		    "code": 0,
		    "msg": "将用户千柳拉入黑名单列表成功。",
		    "data": {
		        "PUSH": "OK"
		    }
		}			
     * @apiError (404) {int{0-65535}} status 响应状态码
     * @apiError (404) {long{0-500}} code 消息码
     * @apiError (404) {String} msg 说明
     * @apiErrorExample {json} 404错误
     * HTTP/1.1 404 404响应
     * {
     * 		"status": 404,
     * 		"code": 200,
     * 		"msg": "接口未注册",
     * }
     * @apiError (500) {int{0-65535}} status 响应状态码
     * @apiError (500) {long{0-500}} code 消息码
     * @apiError (500) {String} msg 说明
     * @apiErrorExample {json} 500错误
     * HTTP/1.1 500 500响应
     * {
     * 		"status": 500,
     * 		"code": 205,
     * 		"msg": "服务器未响应"
     * }
     */
	@ApiOperation(value = "拉入推出黑名单接口", notes = "用于用户拉入推出黑名单列表。")
	@PostMapping(value = "/{id}/pushOrPull")
	public CommonResult<Map<String, String>> pushOrPullToBlacklist(
			@PathVariable(name = "id") @ApiParam(name = "id", value = "用户id", example = "1", required = true) Long userId,
			@RequestBody @Valid BlacklistDTO blacklistDTO) {
		return this.userFeignClient.pushBlackList(userId, blacklistDTO);
	}
}
