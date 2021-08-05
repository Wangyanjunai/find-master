package com.potato369.find.portal.controller;

import com.potato369.find.common.api.CommonResult;
import com.potato369.find.portal.feign.DynamicService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <pre>
 * @PackageName com.potato369.find.portal.controller
 * @ClassName CommentController
 * @Desc 动态评论信息模块Restful API
 * @WebSite https://www.potato369.com
 * @Author admin
 * @Date 2021/07/21 11:36
 * @CreateBy Intellij IDEA 2020.3 (Ultimate Edition)
 * @Copyright Copyright (c) 2016 ~ 2028 版权所有 (C) 土豆互联科技(深圳)有限公司 https://www.potato369.com All Rights Reserved。
 * </pre>
 */
@Api(value = "comment-controller", tags = "动态评论信息模块Restful API")
@RestController
@RequestMapping("/comment")
public class CommentController {

    private DynamicService dynamicFeignClient;

    @Autowired
    public void setDynamicFeignClient(DynamicService dynamicFeignClient) {
        this.dynamicFeignClient = dynamicFeignClient;
    }

    /**
     * @api {post} /find/comment/{id}/release 发表评论接口
     * @apiVersion 1.2.0
     * @apiGroup 动态模块API
     * @apiName 发表评论
     * @apiParam (接口请求参数) {Number} id 用户id
     * @apiParam (接口请求参数) {Number} dynamicInfoId 动态内容id
     * @apiParam (接口请求参数) {String} content 评论内容
     * @apiParamExample 请求示例
     * curl -v -X POST http://w168428j19.51mypc.cn/find/user/70/uploadRegId?regId=1507bfd3f76139cd43a
     * @apiSuccess (200) {Number} status 响应状态码
     * @apiSuccess (200) {Number} code 信息码
     * @apiSuccess (200) {String} msg 说明
     * @apiSuccess (200) {Object} [data] 数据
     * @apiSuccess (200) {String={"OK", "FAILED"}} [RELEASE] 发布状态，OK->“成功”，FAILED->“失败”
     * @apiSuccessExample {json} 200响应示例
     * {
     * "status": 200,
     * "code": 0,
     * "msg": "发布评论成功",
     * "data": {
     * "RELEASE": "OK"
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
     * "msg": "未找到用户信息",
     * }
     * @apiError (404) {Number} status 响应状态码
     * @apiError (404) {Number} code 消息码
     * @apiError (404) {String} msg 说明
     * @apiErrorExample {json} 404错误
     * HTTP/1.1 404 404响应
     * {
     * "status": 404,
     * "code": 200,
     * "msg": "接口未注册",
     * }
     * @apiError (500) {Number} status 响应状态码
     * @apiError (500) {Number} code 消息码
     * @apiError (500) {String} msg 说明
     * @apiErrorExample {json} 500 错误
     * {
     * "status": 500,
     * "code": 205,
     * "msg": "服务器未响应"
     * }
     */
    @PostMapping("/{id}/release")
    public CommonResult<Map<String, Object>> release(@PathVariable(name = "id") Long userId,
                                                     @RequestParam(name = "dynamicInfoId") Long dynamicInfoId,
                                                     @RequestParam(name = "content") String content) {
        return this.dynamicFeignClient.releaseComment(userId, dynamicInfoId, content);
    }

    /**
     * @api {get} /find/comment/{id}/query 分页查询评论接口
     * @apiVersion 1.0.0
     * @apiGroup 动态模块API
     * @apiName 分页查询评论接口
     * @apiParam (接口请求参数) {Number} id 用户id
     * @apiParam (接口请求参数) {Number} dynamicInfoId 动态内容id
     * @apiParam (接口请求参数) {Number} [pageNum=1] 当前页数
     * @apiParam (接口请求参数) {Number} [pageSize=20] 每页条数
     * @apiParamExample 请求示例
     * curl -v -X GET http://w168428j19.51mypc.cn/find/comment/35/query?dynamicInfoId=1&pageNum=1&pageSize=20
     * @apiSuccess (200) {Number} status 响应状态码
     * @apiSuccess (200) {Number} code 信息码
     * @apiSuccess (200) {String} msg 说明
     * @apiSuccess (200) {Object} [data] 数据
     * @apiSuccess (200) {Number} [totalSize] 总条数
     * @apiSuccess (200) {Number} [totalPage] 总页数
     * @apiSuccess (200) {Object[]} [list] 评论数据列表
     * @apiSuccess (200) {Number} [userId] 评论的用户id
     * @apiSuccess (200) {Number} [commentId] 评论id
     * @apiSuccess (200) {String} [nickname] 评论的用户昵称
     * @apiSuccess (200) {String} [head] 评论的用户头像URL
     * @apiSuccess (200) {String} [content] 评论内容
     * @apiSuccess (200) {String} [dateTime] 评论时间
     * @apiSuccess (200) {String} [isOrNotLikes] 当前用户是否点赞，0->点赞，1->未点赞
     * @apiSuccess (200) {Number} [likes] 当前评论点赞数
     * @apiSuccessExample {json} 200响应示例
     * {
     * "status": 200,
     * "code": 0,
     * "msg": "分页查询某条动态内容的所有评论详情数据成功。",
     * "data": {
     * "totalSize": 15,
     * "totalPage": 1,
     * "list": [
     * {
     * "userId": 35,
     * "commentId": 30,
     * "nickname": "思思",
     * "head": "http://192.168.31.31:9000/find/img/head/35/02.png",
     * "content": "好的，会的",
     * "dateTime": "2021-07-21 12:04:39",
     * "isOrNotLikes": "0",
     * "likes": 0
     * },
     * {
     * "userId": 70,
     * "commentId": 16,
     * "nickname": "阿萌",
     * "head": "http://192.168.31.31:9000/find/img/head/70/03.png",
     * "content": "我的加油，加油。",
     * "dateTime": "2021-07-14 15:31:56",
     * "isOrNotLikes": "0",
     * "likes": 3
     * },
     * {
     * "userId": 70,
     * "commentId": 15,
     * "nickname": "阿萌",
     * "head": "http://192.168.31.31:9000/find/img/head/70/03.png",
     * "content": "我的加油。",
     * "dateTime": "2021-07-14 15:17:51",
     * "isOrNotLikes": "0",
     * "likes": 0
     * },
     * {
     * "userId": 122,
     * "commentId": 14,
     * "nickname": "丸子",
     * "head": "http://192.168.31.31:9000/find/img/head/122/01.png",
     * "content": "好的，",
     * "dateTime": "2021-07-13 21:04:58",
     * "isOrNotLikes": "0",
     * "likes": 0
     * },
     * {
     * "userId": 121,
     * "commentId": 13,
     * "nickname": "夹心小憨宝",
     * "head": "http://192.168.31.31:9000/find/img/head/121/03.png",
     * "content": "好的，",
     * "dateTime": "2021-07-13 21:04:54",
     * "isOrNotLikes": "0",
     * "likes": 0
     * },
     * {
     * "userId": 138,
     * "commentId": 12,
     * "nickname": "阿妩",
     * "head": "http://192.168.31.31:9000/find/img/head/138/31b0b00e-f8c3-4e23-ba77-d7e50eafe17e.jpg",
     * "content": "好的，",
     * "dateTime": "2021-07-13 21:04:48",
     * "isOrNotLikes": "0",
     * "likes": 0
     * },
     * {
     * "userId": 141,
     * "commentId": 11,
     * "nickname": "good",
     * "head": "http://192.168.31.31:9000/find/img/head/141/cd118c01-db49-43f9-a857-07bf53ee2918.png",
     * "content": "好的，",
     * "dateTime": "2021-07-13 21:04:43",
     * "isOrNotLikes": "0",
     * "likes": 0
     * },
     * {
     * "userId": 23,
     * "commentId": 10,
     * "nickname": "北柠陌寒",
     * "head": "http://192.168.31.31:9000/find/img/head/23/06.png",
     * "content": "好的，",
     * "dateTime": "2021-07-13 21:04:36",
     * "isOrNotLikes": "0",
     * "likes": 0
     * },
     * {
     * "userId": 42,
     * "commentId": 9,
     * "nickname": "轻吟",
     * "head": "http://192.168.31.31:9000/find/img/head/42/01.png",
     * "content": "好的，",
     * "dateTime": "2021-07-13 21:04:31",
     * "isOrNotLikes": "0",
     * "likes": 0
     * },
     * {
     * "userId": 41,
     * "commentId": 8,
     * "nickname": "卖萌迪",
     * "head": "http://192.168.31.31:9000/find/img/head/41/02.png",
     * "content": "好的，",
     * "dateTime": "2021-07-13 21:04:26",
     * "isOrNotLikes": "0",
     * "likes": 0
     * },
     * {
     * "userId": 51,
     * "commentId": 7,
     * "nickname": "暮夏",
     * "head": "http://192.168.31.31:9000/find/img/head/51/02.png",
     * "content": "好的，",
     * "dateTime": "2021-07-13 21:04:20",
     * "isOrNotLikes": "0",
     * "likes": 0
     * },
     * {
     * "userId": 50,
     * "commentId": 6,
     * "nickname": "来愿",
     * "head": "http://192.168.31.31:9000/find/img/head/50/02.png",
     * "content": "好的，我会的，欢迎！",
     * "dateTime": "2021-07-13 20:58:59",
     * "isOrNotLikes": "0",
     * "likes": 0
     * },
     * {
     * "userId": 60,
     * "commentId": 5,
     * "nickname": "尘埃",
     * "head": "http://192.168.31.31:9000/find/img/head/60/01.png",
     * "content": "好的，我会的，欢迎！",
     * "dateTime": "2021-07-13 20:58:52",
     * "isOrNotLikes": "0",
     * "likes": 0
     * },
     * {
     * "userId": 63,
     * "commentId": 4,
     * "nickname": "浮生",
     * "head": "http://192.168.31.31:9000/find/img/head/63/07.png",
     * "content": "好的，我会的，欢迎！",
     * "dateTime": "2021-07-13 20:58:30",
     * "isOrNotLikes": "0",
     * "likes": 0
     * },
     * {
     * "userId": 73,
     * "commentId": 3,
     * "nickname": "如风",
     * "head": "http://192.168.31.31:9000/find/img/head/73/05.png",
     * "content": "好的，我会的，欢迎！",
     * "dateTime": "2021-07-13 20:58:22",
     * "isOrNotLikes": "0",
     * "likes": 0
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
     * "msg": "未找到用户信息",
     * }
     * @apiError (404) {Number} status 响应状态码
     * @apiError (404) {Number} code 消息码
     * @apiError (404) {String} msg 说明
     * @apiErrorExample {json} 404错误
     * HTTP/1.1 404 404响应
     * {
     * "status": 404,
     * "code": 200,
     * "msg": "接口未注册",
     * }
     * @apiError (500) {Number} status 响应状态码
     * @apiError (500) {Number} code 消息码
     * @apiError (500) {String} msg 说明
     * @apiErrorExample {json} 500 错误
     * {
     * "status": 500,
     * "code": 205,
     * "msg": "服务器未响应"
     * }
     */
    @GetMapping("/{id}/query")
    CommonResult<Map<String, Object>> query(
            @PathVariable(name = "id") Long userId,
            @RequestParam(name = "dynamicInfoId") Long dynamicInfoId,
            @RequestParam(name = "pageNum", required = false, defaultValue = "1") int pageNum,
            @RequestParam(name = "pageSize", required = false, defaultValue = "20") int pageSize) {
        return dynamicFeignClient.queryComment(userId, dynamicInfoId, pageNum, pageSize);
    }

    /**
     * @api {put} /find/comment/{id}/likes 点赞/取消评论接口
     * @apiVersion 1.0.0
     * @apiGroup 动态模块API
     * @apiName 点赞/取消评论接口
     * @apiParam (接口请求参数) {Number} id 用户id
     * @apiParam (接口请求参数) {Number} commentId 评论id
     * @apiParam (接口请求参数) {String} type 类型，0->取消，1->点赞
     * @apiParamExample 请求示例（点赞）
     * curl -v -X PUT http://w168428j19.51mypc.cn/find/comment/35/likes?commentId=16&type=1
     * @apiSuccess (200) {Number} status 响应状态码
     * @apiSuccess (200) {Number} code 信息码
     * @apiSuccess (200) {String} msg 说明
     * @apiSuccess (200) {Object} [data] 数据
     * @apiSuccess (200) {String={"OK", "FAILED"}} [LIKES] 点赞状态，OK->“成功”，FAILED->“失败”
     * @apiSuccessExample {json} 200响应示例（点赞）
     * {
     * "status": 200,
     * "code": 0,
     * "msg": "点赞成功。",
     * "data": {
     * "LIKES": "OK"
     * }
     * }
     * @apiParamExample 请求示例（取消）
     * curl -v -X PUT http://w168428j19.51mypc.cn/find/comment/35/likes?commentId=16&type=0
     * @apiSuccess (200) {Number} status 响应状态码
     * @apiSuccess (200) {Number} code 信息码
     * @apiSuccess (200) {String} msg 说明
     * @apiSuccess (200) {Object} [data] 数据
     * @apiSuccess (200) {String={"OK", "FAILED"}} [LIKES] 点赞状态，OK->“成功”，FAILED->“失败”
     * @apiSuccessExample {json} 200响应示例（取消）
     * {
     * "status": 200,
     * "code": 0,
     * "msg": "取消点赞成功。",
     * "data": {
     * "LIKES": "OK"
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
     * "msg": "未找到用户信息",
     * }
     * @apiError (404) {Number} status 响应状态码
     * @apiError (404) {Number} code 消息码
     * @apiError (404) {String} msg 说明
     * @apiErrorExample {json} 404错误
     * HTTP/1.1 404 404响应
     * {
     * "status": 404,
     * "code": 200,
     * "msg": "接口未注册",
     * }
     * @apiError (500) {Number} status 响应状态码
     * @apiError (500) {Number} code 消息码
     * @apiError (500) {String} msg 说明
     * @apiErrorExample {json} 500 错误
     * {
     * "status": 500,
     * "code": 205,
     * "msg": "服务器未响应"
     * }
     */
    @PutMapping("/{id}/likes")
    CommonResult<Map<String, Object>> likes(@PathVariable(name = "id") Long userId,
                                            @RequestParam(name = "commentId") Long commentId,
                                            @RequestParam(name = "type") String type) {
        return dynamicFeignClient.likesComment(userId, commentId, type);
    }
}
