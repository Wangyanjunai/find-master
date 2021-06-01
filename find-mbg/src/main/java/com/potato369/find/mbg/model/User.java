package com.potato369.find.mbg.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class User implements Serializable {
    @ApiModelProperty(value = "用户id，主键")
    private Long id;

    @ApiModelProperty(value = "手机号码")
    private String phone;

    @ApiModelProperty(value = "账号状态，0->正常；1->异常，默认：0->正常")
    private String status;

    @ApiModelProperty(value = "是否测试用户，0->否；1->是，默认：0->否")
    private String isTest;

    @ApiModelProperty(value = "昵称")
    private String nickName;

    @ApiModelProperty(value = "微信号码")
    private String weixinId;

    @ApiModelProperty(value = "设备串码")
    private String imei;

    @ApiModelProperty(value = "设备型号")
    private String model;

    @ApiModelProperty(value = "系统名称")
    private String sysName;

    @ApiModelProperty(value = "系统版本")
    private String sysCode;

    @ApiModelProperty(value = "网络方式")
    private String networkMode;

    @ApiModelProperty(value = "性别，0->女生；1->男生，默认：0->女生")
    private String gender;

    @ApiModelProperty(value = "出生年代")
    private String year;

    @ApiModelProperty(value = "出生月份")
    private String month;

    @ApiModelProperty(value = "出生日期")
    private String date;

    @ApiModelProperty(value = "星座")
    private String constellation;

    @ApiModelProperty(value = "头像小图")
    private String headIcon;

    @ApiModelProperty(value = "国")
    private String country;

    @ApiModelProperty(value = "省")
    private String province;

    @ApiModelProperty(value = "市")
    private String city;

    @ApiModelProperty(value = "区/县")
    private String district;

    @ApiModelProperty(value = "其它地址")
    private String other;

    @ApiModelProperty(value = "经度")
    private Double longitude;

    @ApiModelProperty(value = "纬度")
    private Double latitude;

    @ApiModelProperty(value = "平台")
    private String platform;

    @ApiModelProperty(value = "IP")
    private String ip;

    @ApiModelProperty(value = "VIP等级，0->VIP0，普通用户；1->VIP1，VIP用户；2->SVIP，超级VIP；默认：0->VIP0，普通用户")
    private String grade;

    @ApiModelProperty(value = "签名，默认：这位靓妹/靓仔还未填写签名。")
    private String autograph;

    @ApiModelProperty(value = "VIP开始时间")
    private Date vipStartTime;

    @ApiModelProperty(value = "VIP结束时间")
    private Date vipEndTime;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

    @ApiModelProperty(value = "背景图片")
    private String backgroundIcon;

    @ApiModelProperty(value = "极光推送唯一设备的标识")
    private String reserveColumn03;

    @ApiModelProperty(value = "预留字段04")
    private String reserveColumn04;

    @ApiModelProperty(value = "职业编号")
    private Long professionId;

    @ApiModelProperty(value = "标签1编号，对应标签表id")
    private Long tag1;

    @ApiModelProperty(value = "标签2编号，对应标签表id")
    private Long tag2;

    @ApiModelProperty(value = "标签3编号，对应标签表id")
    private Long tag3;

    @ApiModelProperty(value = "标签4编号，对应标签表id")
    private Long tag4;

    @ApiModelProperty(value = "标签5编号，对应标签表id")
    private Long tag5;

    @ApiModelProperty(value = "预留字段05")
    private String reserveColumn05;

    @ApiModelProperty(value = "预留字段06")
    private String reserveColumn06;

    @ApiModelProperty(value = "预留字段07")
    private String reserveColumn07;

    @ApiModelProperty(value = "预留字段08")
    private String reserveColumn08;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getIsTest() {
        return isTest;
    }

    public void setIsTest(String isTest) {
        this.isTest = isTest;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getWeixinId() {
        return weixinId;
    }

    public void setWeixinId(String weixinId) {
        this.weixinId = weixinId;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getSysName() {
        return sysName;
    }

    public void setSysName(String sysName) {
        this.sysName = sysName;
    }

    public String getSysCode() {
        return sysCode;
    }

    public void setSysCode(String sysCode) {
        this.sysCode = sysCode;
    }

    public String getNetworkMode() {
        return networkMode;
    }

    public void setNetworkMode(String networkMode) {
        this.networkMode = networkMode;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getConstellation() {
        return constellation;
    }

    public void setConstellation(String constellation) {
        this.constellation = constellation;
    }

    public String getHeadIcon() {
        return headIcon;
    }

    public void setHeadIcon(String headIcon) {
        this.headIcon = headIcon;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getAutograph() {
        return autograph;
    }

    public void setAutograph(String autograph) {
        this.autograph = autograph;
    }

    public Date getVipStartTime() {
        return vipStartTime;
    }

    public void setVipStartTime(Date vipStartTime) {
        this.vipStartTime = vipStartTime;
    }

    public Date getVipEndTime() {
        return vipEndTime;
    }

    public void setVipEndTime(Date vipEndTime) {
        this.vipEndTime = vipEndTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getBackgroundIcon() {
        return backgroundIcon;
    }

    public void setBackgroundIcon(String backgroundIcon) {
        this.backgroundIcon = backgroundIcon;
    }

    public String getReserveColumn03() {
        return reserveColumn03;
    }

    public void setReserveColumn03(String reserveColumn03) {
        this.reserveColumn03 = reserveColumn03;
    }

    public String getReserveColumn04() {
        return reserveColumn04;
    }

    public void setReserveColumn04(String reserveColumn04) {
        this.reserveColumn04 = reserveColumn04;
    }

    public Long getProfessionId() {
        return professionId;
    }

    public void setProfessionId(Long professionId) {
        this.professionId = professionId;
    }

    public Long getTag1() {
        return tag1;
    }

    public void setTag1(Long tag1) {
        this.tag1 = tag1;
    }

    public Long getTag2() {
        return tag2;
    }

    public void setTag2(Long tag2) {
        this.tag2 = tag2;
    }

    public Long getTag3() {
        return tag3;
    }

    public void setTag3(Long tag3) {
        this.tag3 = tag3;
    }

    public Long getTag4() {
        return tag4;
    }

    public void setTag4(Long tag4) {
        this.tag4 = tag4;
    }

    public Long getTag5() {
        return tag5;
    }

    public void setTag5(Long tag5) {
        this.tag5 = tag5;
    }

    public String getReserveColumn05() {
        return reserveColumn05;
    }

    public void setReserveColumn05(String reserveColumn05) {
        this.reserveColumn05 = reserveColumn05;
    }

    public String getReserveColumn06() {
        return reserveColumn06;
    }

    public void setReserveColumn06(String reserveColumn06) {
        this.reserveColumn06 = reserveColumn06;
    }

    public String getReserveColumn07() {
        return reserveColumn07;
    }

    public void setReserveColumn07(String reserveColumn07) {
        this.reserveColumn07 = reserveColumn07;
    }

    public String getReserveColumn08() {
        return reserveColumn08;
    }

    public void setReserveColumn08(String reserveColumn08) {
        this.reserveColumn08 = reserveColumn08;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", phone=").append(phone);
        sb.append(", status=").append(status);
        sb.append(", isTest=").append(isTest);
        sb.append(", nickName=").append(nickName);
        sb.append(", weixinId=").append(weixinId);
        sb.append(", imei=").append(imei);
        sb.append(", model=").append(model);
        sb.append(", sysName=").append(sysName);
        sb.append(", sysCode=").append(sysCode);
        sb.append(", networkMode=").append(networkMode);
        sb.append(", gender=").append(gender);
        sb.append(", year=").append(year);
        sb.append(", month=").append(month);
        sb.append(", date=").append(date);
        sb.append(", constellation=").append(constellation);
        sb.append(", headIcon=").append(headIcon);
        sb.append(", country=").append(country);
        sb.append(", province=").append(province);
        sb.append(", city=").append(city);
        sb.append(", district=").append(district);
        sb.append(", other=").append(other);
        sb.append(", longitude=").append(longitude);
        sb.append(", latitude=").append(latitude);
        sb.append(", platform=").append(platform);
        sb.append(", ip=").append(ip);
        sb.append(", grade=").append(grade);
        sb.append(", autograph=").append(autograph);
        sb.append(", vipStartTime=").append(vipStartTime);
        sb.append(", vipEndTime=").append(vipEndTime);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", backgroundIcon=").append(backgroundIcon);
        sb.append(", reserveColumn03=").append(reserveColumn03);
        sb.append(", reserveColumn04=").append(reserveColumn04);
        sb.append(", professionId=").append(professionId);
        sb.append(", tag1=").append(tag1);
        sb.append(", tag2=").append(tag2);
        sb.append(", tag3=").append(tag3);
        sb.append(", tag4=").append(tag4);
        sb.append(", tag5=").append(tag5);
        sb.append(", reserveColumn05=").append(reserveColumn05);
        sb.append(", reserveColumn06=").append(reserveColumn06);
        sb.append(", reserveColumn07=").append(reserveColumn07);
        sb.append(", reserveColumn08=").append(reserveColumn08);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}