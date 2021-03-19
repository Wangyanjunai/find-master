package com.potato369.find.common.enums;

//用户操作记录类型枚举
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum OperateRecordTypeEnum implements CodeEnum<Integer>{
	
	ReleaseDynamic(0, "0", "发布动态"),
	
	DeleteDynamic(1, "1", "删除动态"),
	
	LikeDynamic(2, "2", "点赞动态"),
	
	ApplyForWechat(3, "3", "申请加微信"),
	
	shareDynamic(4, "4", "分享动态"),
	
	PushBlackList(5, "5", "拉入黑名单"),
	
	PullBlackList(6, "6", "推出黑名单"),
	
	ReportDynamic(7, "7", "举报动态"),
	
	ReportUser(8, "8", "举报用户"),
	
	CreateUser(9, "9", "用户注册"),
	
	UpdateUser(10, "10", "修改资料"),
	
	UploadHeadIcon(11, "11", "上传头像小图"),
	
	UpdateHeadIcon(12, "12", "修改头像小图"),
	
	UpdateBackgroundIcon(13, "13", "修改背景图片"),
	
	LoginUser(14, "14", "用户登录"),
	
	UpdateLocation(15, "15", "更新动态定位"),
	
	;
	private Integer code;

    private String type;
    
    private String message;
}
