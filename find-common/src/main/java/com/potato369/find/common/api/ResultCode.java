package com.potato369.find.common.api;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 枚举了一些常用API操作码
 * Created by macro on 2019/4/19.
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum ResultCode implements IErrorCode {

    SUCCESS(0, "返回数据成功"),

    USER_EXIST_BLACKLIST_ERROR(300, "将用户拉入黑名单列表失败，用户已经存在黑名单列表中"),

    USER_NOT_EXIST_BLACKLIST_ERROR(301, "将用户推出黑名单列表失败，用户不存在黑名单列表中"),

    NOT_PULL_OR_PUSH_OWNER_BLACKLIST_ERROR(302, "不能将自己推入拉出黑名单列表"),

    UNAUTHORIZED(401, "暂未登录或token已经过期"),

    FORBIDDEN(403, "没有相关权限"),

    VALIDATE_FAILED(404, "参数检验失败"),

    FILE_EXCEEDS_LIMIT(420, "上传的单个文件大小超过10M，服务器不允许上传"),

    USER_IS_NOT_EXIST(421, "用户信息不存在"),

    USER_ACCOUNT_IS_ABNORMAL(422, "用户账号异常"),

    PRODUCT_IS_NOT_EXIST(423, "充值会员套餐时长信息不存在"),

    FAILED(500, "返回数据失败"),

    DYNAMIC_IS_NOT_EXIST(600, "申请加微信出错，动态内容不存在。"),

    PUBLISH_USER_IS_NOT_EXIST(601, "申请加微信出错，发布者用户信息不存在。"),

    PUBLISH_USER_IS_VALID(602, "申请加微信出错，不能申请加自己微信。"),

    RECIPIENT_USER_IS_NOT_EXIST(603, "申请加微信出错，接收者用户信息不存在。"),

    TIMES_OVERRUN(604, "申请加微信出错，今天申请加微信次数超限，请明天再试。"),

    COUNT_OVERRUN(605, "申请加微信出错，今天已经申请加过微信，请明天再试。"),

    NO_REPLY_OVERRUN(606, "申请加微信出错，对方未回复，请等待对方回复再试。"),

    LIKES_USER_IS_NOT_EXIST(630, "点赞当前动态内容出错，用户信息不存在。"),

    LIKES_DYNAMIC_INFO_IS_NOT_EXIST(631, "点赞当前动态内容出错，动态内容不存在。"),

    LIKES_RECORD_IS_NOT_EXIST(632, "取消/点赞，点赞记录不存在。"),

    ;
    private int code;

    private String message;
}
