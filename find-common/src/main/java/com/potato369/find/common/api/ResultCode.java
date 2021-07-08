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

    SUCCESS(0, "返回数据成功。"),


    USER_EXIST_BLACKLIST_ERROR(300, "将用户拉入黑名单列表失败，用户已经存在黑名单列表中。"),

    USER_NOT_EXIST_BLACKLIST_ERROR(301, "将用户推出黑名单列表失败，用户不存在黑名单列表中。"),

    NOT_PULL_OR_PUSH_OWNER_BLACKLIST_ERROR(302, "不能将自己推入拉出黑名单列表。"),

    VALIDATE_FAILED(400, "参数校验失败。"),

    UNAUTHORIZED(401, "暂未登录或token已经过期。"),

    FORBIDDEN(403, "没有相关权限。"),

    FILE_EXCEEDS_LIMIT(420, "上传的单个文件大小超过10M，服务器不允许上传。"),

    USER_IS_NOT_EXIST(421, "用户信息不存在。"),

    USER_ACCOUNT_IS_ABNORMAL(422, "用户账号异常。"),

    PRODUCT_IS_NOT_EXIST(423, "充值会员套餐时长信息不存在。"),


    FAILED(500, "返回数据失败"),


    APPLICANT_USER_IS_NOT_EXIST(600, "申请加微信者用户信息不存在。"),

    DYNAMIC_IS_NOT_EXIST(601, "被申请加微信者动态内容信息不存在。"),

    APPLICANTS_USER_IS_NOT_EXIST(602, "被申请加微信者用户信息不存在。"),

    APPLICANTS_USER_IS_VALID(603, "申请加微信者不能申请加自己微信。"),

    TIMES_OVERRUN(604, "今天申请加微信次数超限，请明天再试。"),

    COUNT_OVERRUN(605, "今天已经申请加过微信，请明天再试。"),

    NO_REPLY_OVERRUN(606, "对方未回复，请等待对方回复再试。"),


    LIKES_USER_IS_NOT_EXIST(630, "点赞当前动态内容出错，用户信息不存在。"),

    LIKES_DYNAMIC_INFO_IS_NOT_EXIST(631, "点赞当前动态内容出错，动态内容不存在。"),

    LIKES_RECORD_IS_NOT_EXIST(632, "取消/点赞，点赞记录不存在。"),


    REPLY_MESSAGE_IS_NOT_EXIST(650, "回复的消息不存在。"),

    REPLY_MESSAGE_STATUS2_IS_VALID(651, "回复的消息已被删除。"),

    REPLY_MESSAGE_IS_VALID(652, "消息已被回复。"),

    REPLY_MESSAGE_WEIXIN_ID_IS_EMPTY(653, "回复同意消息微信Id为空。"),

    REPLY_MESSAGE_USER_IS_NOT_EXIST(654, "申请加微信者用户信息不存在。"),

    REPLY_MESSAGE_USER_IS_NOT_VALID(655, "回复申请加微信者用户信息非法。"),

    REPLY_MESSAGE_IS_OWNER(656, "不能给自己回复消息。"),

    REPLY_APPLICATIONS_MESSAGE_IS_VALID(657, "不能自己回复自己加别人的微信的申请。"),
    ;

    private int code;

    private String message;
}
