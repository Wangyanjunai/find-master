package com.potato369.find.portal.controller;

import com.potato369.find.common.api.CommonResult;
import com.potato369.find.common.dto.OrderDTO;
import com.potato369.find.portal.feign.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.WebApplicationContext;

import javax.validation.Valid;
import java.util.Map;

@Api(value = "order-controller", tags = "订单信息模块Restful API")
@RestController
@RequestMapping("/order")
//@Profile({"dev", "dev2", "test", "prod"})
@Scope(value = WebApplicationContext.SCOPE_REQUEST)
public class OrderController {

    private OrderService orderFeignClient;

    @Autowired
    public void setOrderFeignClient(OrderService orderFeignClient) {
        this.orderFeignClient = orderFeignClient;
    }

    /**
     * @api {get} /find/order/{id}/product/list 获取充值商品列表接口
     * @apiVersion 1.0.0
     * @apiGroup 订单模块API
     * @apiName 获取充值商品列表
     * @apiParam (接口请求参数) {Number} id 用户id
     * @apiParamExample 请求示例03（手机号码和客户端IP登录）
     * curl -v -X GET http://w168428j19.51mypc.cn/find/order/1/product/list
     * @apiSuccess (200) {Number} code 信息码
     * @apiSuccess (200) {String} msg 说明
     * @apiSuccess (200) {Number} status 响应状态码
     * @apiSuccess (200) {Object} [data] 数据
     * @apiSuccess (200) {Object[]} [list] 商品信息列表数据
     * @apiSuccess (200) {String} [desc] 描述
     * @apiSuccess (200) {String} [icon] 商品小图地址
     * @apiSuccess (200) {String} [name] 商品名称
     * @apiSuccess (200) {Number}[pid] 商品id
     * @apiSuccess (200) {Double} [price] 商品价格
     * @apiSuccessExample {json} 200响应示例
     * HTTPS/1.1 200 OK
     * {
     * "code": 0,
     * "msg": "查询充值会员时长套餐产品列表成功",
     * "status": 200,
     * "data": {
     * "list": [
     * {
     * "desc": "60元/月",
     * "icon": "http://192.168.31.31:9000/find/img/app/product/01.png",
     * "name": "2个月",
     * "pid": 1,
     * "price": 120.00
     * },
     * {
     * "desc": "50元/月",
     * "icon": "http://192.168.31.31:9000/find/img/app/product/02.png",
     * "name": "3个月",
     * "pid": 2,
     * "price": 180.00
     * },
     * {
     * "desc": "41.3元/月",
     * "icon": "http://192.168.31.31:9000/find/img/app/product/03.png",
     * "name": "6个月",
     * "pid": 3,
     * "price": 248.00
     * },
     * {
     * "desc": "33元/月",
     * "icon": "http://192.168.31.31:9000/find/img/app/product/04.png",
     * "name": "12个月",
     * "pid": 4,
     * "price": 369.00
     * }
     * ]
     * }
     * }
     * @apiError (403) {Number} status 响应状态码
     * @apiError (403) {Number} code 消息码
     * @apiError (403){String} msg 说明
     * @apiErrorExample {json} 403错误
     * HTTP/1.1 403 403响应
     * {
     * "status": 403,
     * "code": 199,
     * "msg": "未找到用户信息！",
     * }
     * @apiError (404) {Number} status 响应状态码
     * @apiError (404) {Number} code 消息码
     * @apiError (404){String} msg 说明
     * @apiErrorExample {json} 404错误
     * HTTP/1.1 404 404响应
     * {
     * "status": 404,
     * "code": 200,
     * "msg": "接口未注册！",
     * }
     * @apiError (500) {Number} status 响应状态码
     * @apiError (500) {Number} code 消息码
     * @apiError (500){String} msg 说明
     * @apiErrorExample {json} 500错误
     * HTTP/1.1 500 500响应
     * {
     * "status": 500,
     * "code": 205,
     * "msg": "服务器未响应！"
     * }
     */
    @ApiOperation(value = "获取充值商品列表接口", notes = "用于获取充值商品列表。")
    @GetMapping(value = "/{id}/product/list")
    public CommonResult<Map<String, Object>> list(
            @PathVariable(name = "id") @ApiParam(name = "id", value = "用户id", required = true, example = "1") Long userId) {
        return this.orderFeignClient.list(userId);
    }

    /**
     * @api {post} /find/order/{id}/mobile/create 创建预支付订单接口
     * @apiVersion 1.0.0
     * @apiGroup 订单模块API
     * @apiName 创建预支付订单
     * @apiParam (请求参数) {Number} id 用户id
     * @apiParam (请求参数) {Number} pid 商品id
     * @apiParam (请求参数) {Number=0, 1} mode 支付方式：0->微信，1->支付宝
     * @apiParam (请求参数){String} [ip] 客户端IP
     * @apiParamExample {json} 请求示例01（微信支付）
     * HTTP/1.1 OK
     * curl -v -X POST http://w168428j19.51mypc.cn/find/order/1/mobile/create -H "Content-Type:application/json;charset=utf-8" -d '{"pid":1, "mode":0, "ip":"192.168.31.31"}'
     * @apiSuccess (200) {Number} code 信息码
     * @apiSuccess (200) {String} msg 说明
     * @apiSuccess (200) {Number} status 响应状态码
     * @apiSuccess (200) {Object} [data] 数据
     * @apiSuccess (200) {Object} [trade_info] 商品预支付订单信息
     * @apiSuccess (200) {String} [appid] 应用id
     * @apiSuccess (200) {String} [err_code] 错误码
     * @apiSuccess (200) {String} [err_code_des] 错误说明
     * @apiSuccess (200) {String} [out_trade_no] 订单号
     * @apiSuccess (200) {String} [package] 支付标识
     * @apiSuccess (200) {String} [partnerid] 商户id
     * @apiSuccess (200) {String} [prepayid] 预支付请求id
     * @apiSuccess (200) {String} [result_code] 结果状态
     * @apiSuccess (200) {String} [return_code] 返回状态
     * @apiSuccess (200) {String} [return_msg] 返回消息说明
     * @apiSuccess (200) {String} [sign] 签名
     * @apiSuccess (200) {String} [timestamp] 时间戳
     * @apiSuccessExample {json} 200（微信支付）响应示例01
     * HTTP/1.1 200 OK
     * {
     * "code": 0,
     * "data": {
     * "trade_info": {
     * "appid": "wx394471ab93938b34",
     * "err_code": "Success",
     * "err_code_des": "请求微信支付统一下单接口生成APP支付预付单信息成功。",
     * "noncestr": "1610960641587",
     * "out_trade_no": "2021011817040003581135ba8bfa742a",
     * "package": "Sign=WXPay",
     * "partnerid": "1539515591",
     * "prepayid": "wx1817040283097360129d35d33dfc5a0000",
     * "result_code": "Success",
     * "return_code": "Success",
     * "return_msg": "生成微信APP支付预支付订单信息成功。",
     * "sign": "0F6E2CB474B2A6B675D35D3F9215086D",
     * "timestamp": "1610960641"
     * }
     * },
     * "msg": "返回数据成功",
     * "status": 200
     * }
     * @apiParamExample {json} 请求示例02（支付宝支付）
     * HTTP/1.1 OK
     * curl -v -X POST http://w168428j19.51mypc.cn/find/order/1/mobile/create -H "Content-Type:application/json;charset=utf-8" -d '{"pid":1, "mode":1, "ip":"192.168.31.31"}'
     * @apiSuccess (200) {Number} code 信息码
     * @apiSuccess (200){String} msg 说明
     * @apiSuccess (200) {Number} status 响应状态码
     * @apiSuccess (200) {Object} [data] 数据
     * @apiSuccess (200) {Object} [trade_info] 商品预支付订单信息
     * @apiSuccess (200) {String} [appid] 应用id
     * @apiSuccess (200) {String} [err_code] 错误码
     * @apiSuccess (200) {String} [err_code_des] 错误说明
     * @apiSuccess (200) {String} [orderString] 支付订单信息
     * @apiSuccess (200) {String} [out_trade_no] 订单号
     * @apiSuccess (200) {String} [result_code] 结果状态
     * @apiSuccess (200) {String} [return_code] 返回状态
     * @apiSuccess (200) {String} [return_msg] 返回消息说明
     * @apiSuccessExample {json} 200（支付宝支付）响应示例01
     * HTTP/1.1 200 OK
     * {
     * "code": 0,
     * "data": {
     * "trade_info": {
     * "appid": "2021001183634710",
     * "err_code": "Success",
     * "err_code_des": "生成支付宝APP预支付订单信息返回数据成功。",
     * "orderString": "alipay_sdk=alipay-sdk-java-3.7.110.ALL&app_id=2021001183634710&biz_content=%7B%22goods_type%22%3A%220%22%2C%22out_trade_no%22%3A%22202101181714518933f2c2fe0f3fa423%22%2C%22subject%22%3A%22%E5%85%85%E5%80%BC2%E4%B8%AA%E6%9C%88VIP%22%2C%22timeout_express%22%3A%2230%22%2C%22total_amount%22%3A%221%22%7D&charset=UTF-8&format=JSON&method=alipay.trade.app.pay&notify_url=http%3A%2F%2Fw168428j19.51mypc.cn%2Ffind%2Fv1%2Forder%2Fpay%2Falipay-notify.do&sign=Hi4DY84hHqM%2F3SrBCCtUYfDd9i8ZKrf8QF0O3nRRO0bgvS7GTyuLOhaJQ9Td%2FLMfsvU7G0OPh7PABaslzRLqRKdVrMe0LvrVtBQQJ2%2BKh0w0YGOSoIV7tGq%2Bkz2hs4%2FmH%2FLfLH2XX2tSDOi3HM6CdhhF7SkX7DFEbgowLGR3VtRVpbKVBtpHKHVk%2BQbxlPnkiDNb9u6bnefb2kBYJB6AGLL7E7PrwQOx61yezFg8HBAd7Ic%2FNstMEZ%2BX5ZcCSQaiRbaAQ5iZTTiFiyF66bHtTTYrlT2M37JxO6VQ2o5Rn4EOKS4d1NwqaqUnGg7upZH4ygqbZFkaAvDGo3bbS%2FnZ0Q%3D%3D&sign_type=RSA2&timestamp=2021-01-18+17%3A14%3A51&version=2.0",
     * "out_trade_no": "202101181714518933f2c2fe0f3fa423",
     * "result_code": "Success",
     * "return_code": "Success",
     * "return_msg": "生成支付宝APP预支付订单信息返回数据成功。"
     * }
     * },
     * "msg": "返回数据成功",
     * "status": 200
     * }
     * @apiError (403) {Number} status 响应状态码
     * @apiError (403) {Number} code 消息码
     * @apiError (403){String} msg 说明
     * @apiErrorExample {json} 403错误
     * HTTP/1.1 403 403响应
     * {
     * "status": 403,
     * "code": 199,
     * "msg": "未找到用户信息！",
     * }
     * @apiError (404) {Number} status 响应状态码
     * @apiError (404) {Number} code 消息码
     * @apiError (404){String} msg 说明
     * @apiErrorExample {json} 404错误
     * HTTP/1.1 404 404响应
     * {
     * "status": 404,
     * "code": 200,
     * "msg": "接口未注册！",
     * }
     * @apiError (500) {Number} status 响应状态码
     * @apiError (500) {Number} code 消息码
     * @apiError (500){String} msg 说明
     * @apiErrorExample {json} 500错误
     * HTTP/1.1 500 500响应
     * {
     * "status": 500,
     * "code": 205,
     * "msg": "服务器未响应！"
     * }
     */
    @ApiOperation(value = "创建预支付订单接口", notes = "用于创建预支付订单信息。")
    @PostMapping(value = "/{id}/mobile/create")
    public CommonResult<Map<String, Object>> create(
            @PathVariable("id") @ApiParam(name = "id", value = "用户id", required = true, example = "1") Long userId,
            @RequestBody @Valid @ApiParam(value = "订单实体对象") OrderDTO orderDTO) {
        return this.orderFeignClient.createOrder(userId, orderDTO);
    }
}
