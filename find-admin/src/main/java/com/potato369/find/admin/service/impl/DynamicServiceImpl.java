package com.potato369.find.admin.service.impl;

import cn.hutool.core.util.StrUtil;
import com.potato369.find.admin.dao.DynamicDaoUseJdbcTemplate;
import com.potato369.find.admin.dao.DynamicInfoDaoUseJdbcTemplate;
import com.potato369.find.admin.dao.UserDaoUseJdbcTemplate;
import com.potato369.find.admin.service.DynamicService;
import com.potato369.find.admin.utils.ExcelUtil;
import com.potato369.find.admin.utils.RandomNickNameUtil;
import com.potato369.find.common.enums.AttacheInfoStatusEnum;
import com.potato369.find.common.enums.UserGradeEnum;
import com.potato369.find.common.enums.UserIsTestEnum;
import com.potato369.find.common.enums.UserStatusEnum;
import com.potato369.find.common.utils.DateUtil;
import com.potato369.find.common.utils.UUIDUtil;
import com.potato369.find.mbg.mapper.AttacheInfoMapper;
import com.potato369.find.mbg.mapper.DynamicInfoMapper;
import com.potato369.find.mbg.mapper.DynamicMapper;
import com.potato369.find.mbg.mapper.UserMapper;
import com.potato369.find.mbg.model.AttacheInfo;
import com.potato369.find.mbg.model.Dynamic;
import com.potato369.find.mbg.model.DynamicInfo;
import com.potato369.find.mbg.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
@Transactional(readOnly = true)
public class DynamicServiceImpl implements DynamicService {

    private UserMapper userMapperWrite;

    private DynamicMapper dynamicMapperWrite;

    private DynamicInfoMapper dynamicInfoMapperWrite;

    private AttacheInfoMapper attacheInfoMapperWrite;

    private UserDaoUseJdbcTemplate userDaoUseJdbcTemplate;

    private DynamicDaoUseJdbcTemplate dynamicDaoUseJdbcTemplate;

    private DynamicInfoDaoUseJdbcTemplate dynamicInfoDaoUseJdbcTemplate;

    @Autowired
    public void setUserMapperWrite(UserMapper userMapperWrite) {
        this.userMapperWrite = userMapperWrite;
    }

    @Autowired
    public void setDynamicMapperWrite(DynamicMapper dynamicMapperWrite) {
        this.dynamicMapperWrite = dynamicMapperWrite;
    }

    @Autowired
    public void setDynamicInfoMapperWrite(DynamicInfoMapper dynamicInfoMapperWrite) {
        this.dynamicInfoMapperWrite = dynamicInfoMapperWrite;
    }

    @Autowired
    public void setAttacheInfoMapperWrite(AttacheInfoMapper attacheInfoMapperWrite) {
        this.attacheInfoMapperWrite = attacheInfoMapperWrite;
    }

    @Autowired
    public void setUserDaoUseJdbcTemplate(UserDaoUseJdbcTemplate userDaoUseJdbcTemplate) {
        this.userDaoUseJdbcTemplate = userDaoUseJdbcTemplate;
    }

    @Autowired
    public void setDynamicDaoUseJdbcTemplate(DynamicDaoUseJdbcTemplate dynamicDaoUseJdbcTemplate) {
        this.dynamicDaoUseJdbcTemplate = dynamicDaoUseJdbcTemplate;
    }

    @Autowired
    public void setDynamicInfoDaoUseJdbcTemplate(DynamicInfoDaoUseJdbcTemplate dynamicInfoDaoUseJdbcTemplate) {
        this.dynamicInfoDaoUseJdbcTemplate = dynamicInfoDaoUseJdbcTemplate;
    }

    @Override
    @Transactional(readOnly = false)
    public int insert(User user, Dynamic dynamic, DynamicInfo dynamicInfo, AttacheInfo attacheInfo) throws Exception {
        int userResult = 0;
        int dynamicResult = 0;
        int dynamicInfoResult = 0;
        int attacheInfoResult = 0;
        if (user != null && dynamic != null && dynamicInfo != null && attacheInfo != null) {
            Long userIdLong = user.getId();
            Long dynamicIdLong = dynamic.getId();
            Long dynamicInfoIdLong = dynamicInfo.getId();
            if (userIdLong != null) {
                User user2 = this.userDaoUseJdbcTemplate.getById(userIdLong);
                if (user2 == null) {
                    userResult = this.userMapperWrite.insertImport(user);
                } else {
                    userResult = this.userMapperWrite.updateByPrimaryKey(user);
                }
            }
            if (dynamicIdLong != null) {
                Dynamic dynamic2 = this.dynamicDaoUseJdbcTemplate.getById(dynamicIdLong);
                if (dynamic2 == null) {
                    dynamicResult = this.dynamicMapperWrite.insertImport(dynamic);
                } else {
                    dynamicResult = this.dynamicMapperWrite.updateByPrimaryKey(dynamic);
                }
            }
            if (dynamicInfoIdLong != null) {
                DynamicInfo dynamicInfo2 = this.dynamicInfoDaoUseJdbcTemplate.getById(dynamicInfoIdLong);
                if (dynamicInfo2 == null) {
                    dynamicInfoResult = this.dynamicInfoMapperWrite.insertImport(dynamicInfo);
                } else {
                    dynamicInfoResult = this.dynamicInfoMapperWrite.updateByPrimaryKey(dynamicInfo);
                }
            }
            attacheInfoResult = this.attacheInfoMapperWrite.insertSelective(attacheInfo);
        }
        if (log.isDebugEnabled()) {
            log.debug("user={}, dynamic={}, dynamicInfo={},attacheInfo={}", user, dynamic, dynamicInfo, attacheInfo);
        }
        return userResult + dynamicResult + dynamicInfoResult + attacheInfoResult;
    }

    @Override
    @Transactional(readOnly = false)
    public String ajaxUploadExcel(HttpServletRequest request, HttpServletResponse response) throws Exception {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        MultipartFile file = multipartRequest.getFile("upfile");
        assert file != null;
        if (file.isEmpty()) {
            log.error("文件不存在！");
            return "文件不存在！";
        }
        InputStream in = null;
        try {
            in = file.getInputStream();
        } catch (IOException e) {
            log.error("文件不存在！", e);
            return "文件不存在！";
        }
        List<List<Object>> listob = null;
        try {
            listob = new ExcelUtil().getBankListByExcel(in, file.getOriginalFilename());
        } catch (Exception e) {
            log.error("文件不存在！", e);
            return "文件不存在！";
        }
        // 该处可调用service相应方法进行数据保存到数据库中，现只对数据输出
        for (int i = 0; i < listob.size(); i++) {
            List<Object> lo = listob.get(i);
            log.info("lo.size={}", lo.size());
            if (!lo.isEmpty() && StrUtil.isNotEmpty(lo.get(0).toString()) && lo.size() == 24) {
                String dir = String.valueOf(lo.get(22));
                Long userIdLong;// id
                String datetimeString;
                String phoneString = String.valueOf(lo.get(0)); // 手机号码
                String nicknameString = String.valueOf(lo.get(1)); // 昵称
                String welkinIdString = String.valueOf(lo.get(2)); // 微信号
                String genderString = String.valueOf(lo.get(3)); // 性别
                String yearString = String.valueOf(lo.get(4)); // 出生年代
                String monthString = String.valueOf(lo.get(5)); // 出生月份
                String dateString = String.valueOf(lo.get(6)); // 出生日期（天）
                String constellationString = String.valueOf(lo.get(7)); // 星座
                String headIconString = String.valueOf(lo.get(8)); // 头像小图
                String provinceString = String.valueOf(lo.get(9)); // 注册定位（省份）
                String cityString = String.valueOf(lo.get(10)); // 注册定位（城市）
                User user = null;
                Dynamic dynamic = null;
                DynamicInfo dynamicInfo = null;
                if (StrUtil.isNotEmpty(dir)) {
                    String[] dirArray = StrUtil.split(dir, "/");
                    userIdLong = Long.parseLong(dirArray[0]);
                    datetimeString = dirArray[1];
                    log.info("userIdLong={}", userIdLong);
                    log.info("datetimeString={}", datetimeString);
                    user = this.userDaoUseJdbcTemplate.getById(userIdLong);
                    if (user == null && StrUtil.isNotEmpty(phoneString)) {
                        user = this.userDaoUseJdbcTemplate.getByPhone(phoneString);
                    }
                    if (user == null) {
                        user = new User();
                    }
                    user.setId(userIdLong);// 用户id
                    user.setPhone(phoneString);// 手机号码
                    if (StrUtil.isEmpty(nicknameString)) {
                        nicknameString = new RandomNickNameUtil().randomName();
                    }
                    user.setStatus(UserStatusEnum.Normal.getCode().toString());
                    user.setIsTest(UserIsTestEnum.Yes.getCode().toString());
                    user.setNickName(nicknameString);// 昵称
                    if (StrUtil.isNotEmpty(welkinIdString)) {
                        user.setWeixinId(welkinIdString);// 微信号
                    } else {
                        user.setWeixinId(UUIDUtil.genWeChat());
                    }
                    if (StrUtil.isNotEmpty(genderString)) {
                        user.setGender(genderString);// 性别
                    }
                    if (StrUtil.isNotEmpty(yearString)) {
                        user.setYear(yearString);// 出生年代
                    }
                    if (StrUtil.isNotEmpty(monthString)) {
                        user.setMonth(monthString);// 出生月份
                    }
                    if (StrUtil.isNotEmpty(dateString)) {
                        user.setDate(dateString);// 出生日期（天）
                    }
                    if (StrUtil.isNotEmpty(constellationString)) {
                        user.setConstellation(constellationString);// 星座
                    }
                    if (StrUtil.isNotEmpty(headIconString)) {
                        user.setHeadIcon(headIconString);// 头像小图
                    }
                    user.setCountry("中国");// 国家，默认中国
                    if (StrUtil.isNotEmpty(provinceString)) {
                        user.setProvince(provinceString);// 注册定位（省份）
                    }
                    if (StrUtil.isNotEmpty(cityString)) {
                        user.setCity(cityString); // 注册定位（城市）
                    }
                    user.setGrade(UserGradeEnum.VIP0.getCode().toString());
                    user.setImei(UUIDUtil.genIMEI());
                    user.setModel("华为 P40");
                    user.setSysName("Android");
                    user.setSysCode("10.0");
                    user.setNetworkMode("WIFI");
                    user.setPlatform("HuaWei");
                    user.setIp("183.14.29.243");
                    Date userCreateTimeDate = DateUtil.randomDateTime(datetimeString);
                    Date userUpdateTimeDate = DateUtil.getTimeByHourAndDate(2, userCreateTimeDate);
                    user.setCreateTime(userCreateTimeDate);
                    user.setUpdateTime(userUpdateTimeDate);

                    Long dynamicIdLong = Long.parseLong(String.valueOf(lo.get(11)));// 动态信息id
                    String dynamicProvinceString = String.valueOf(lo.get(12)); // 动态定位地址（省份）
                    String dynamicCityString = String.valueOf(lo.get(13)); // 动态定位地址（城市）
                    dynamic = this.dynamicDaoUseJdbcTemplate.getById(dynamicIdLong);
                    if (dynamic == null) {
                        dynamic = new Dynamic();
                    }
                    dynamic.setId(dynamicIdLong);
                    dynamic.setUserId(user.getId());
                    dynamic.setNickName(user.getNickName());
                    dynamic.setImei(user.getImei());
                    dynamic.setModel(user.getModel());
                    dynamic.setSysName(user.getSysName());
                    dynamic.setSysCode(user.getSysCode());
                    dynamic.setNetworkMode(user.getNetworkMode());
                    dynamic.setCountry("中国");
                    if (StrUtil.isNotEmpty(dynamicProvinceString)) {
                        dynamic.setProvince(dynamicProvinceString);
                    } else {
                        dynamic.setProvince(user.getProvince());
                    }
                    if (StrUtil.isNotEmpty(dynamicCityString)) {
                        dynamic.setCity(dynamicCityString);
                    } else {
                        dynamic.setCity(user.getCity());
                    }
                    dynamic.setCreateTime(DateUtil.getTimeByHourAndDate(3, userCreateTimeDate));
                    dynamic.setUpdateTime(DateUtil.getTimeByHourAndDate(5, userCreateTimeDate));
                    Long dynamicInfoIdLong = Long.parseLong(String.valueOf(lo.get(14)));// 动态内容id
                    dynamicInfo = this.dynamicInfoDaoUseJdbcTemplate.getById(dynamicInfoIdLong);
                    String contentString = String.valueOf(lo.get(15));// 动态内容
                    String contentStatusString = String.valueOf(lo.get(16));// 动态内容状态
                    String publishStatusString = String.valueOf(lo.get(17));// 是否公开定位状态
                    Integer likesInteger = Integer.parseInt(String.valueOf(lo.get(18)));// 点赞数
                    Integer applicationsInteger = Integer.parseInt(String.valueOf(lo.get(19)));// 申请加微信数
                    String attacheTypeString = String.valueOf(lo.get(20));// 附件类型
                    Integer attacheNumberInteger = Integer.parseInt(String.valueOf(lo.get(21)));// 附件数量
                    if (dynamicInfo == null) {
                        dynamicInfo = new DynamicInfo();
                    }
                    if ("1".equals(attacheTypeString)) {
                        attacheTypeString = "0";
                    }
                    if ("2".equals(attacheTypeString)) {
                        attacheTypeString = "1";
                    }
                    dynamicInfo.setId(dynamicInfoIdLong);
                    dynamicInfo.setUserId(user.getId());
                    dynamicInfo.setDynamicId(dynamic.getId());
                    dynamicInfo.setContent(contentString);
                    dynamicInfo.setDynamicStatus(contentStatusString);
                    dynamicInfo.setPublicStatus(publishStatusString);
                    dynamicInfo.setLikes(likesInteger);
                    dynamicInfo.setApplications(applicationsInteger);
                    dynamicInfo.setShares(0);
                    dynamicInfo.setAttacheType(attacheTypeString);
                    dynamicInfo.setAttacheNumber(attacheNumberInteger);

                    dynamicInfo.setCreateTime(DateUtil.getTimeByHourAndDate(6, userCreateTimeDate));
                    dynamicInfo.setUpdateTime(DateUtil.getTimeByHourAndDate(7, userCreateTimeDate));

                    String attacheNameStrings = String.valueOf(lo.get(23));// 附件名称集合
                    String[] attacheNameArray = StrUtil.split(attacheNameStrings, "||");
                    AttacheInfo attacheInfo = new AttacheInfo();
                    StringBuilder stringBuilder = new StringBuilder();
                    attacheInfo.setStatus(AttacheInfoStatusEnum.Show.getCode().toString());
                    attacheInfo.setDynamicInfoBy(dynamicInfoIdLong);
                    attacheInfo.setDataType(attacheTypeString);
                    if (attacheNameArray != null && attacheNameArray.length > 0) {
                        for (int j = 0; j < attacheNameArray.length; j++) {
                            StringBuilder stringBuilderFN = new StringBuilder();
                            stringBuilderFN.append(dir).append("/").append(attacheNameArray[j]);
                            if (attacheNameArray.length == 1) {
                            } else {
                                if (j == attacheNameArray.length - 1) {
                                } else {
                                    stringBuilderFN.append("||");
                                }
                            }
                            stringBuilder.append(stringBuilderFN);
                        }
                    }
                    attacheInfo.setFileName(stringBuilder.toString());
                    attacheInfo.setCreateTime(DateUtil.getTimeByHourAndDate(6, userCreateTimeDate));
                    attacheInfo.setUpdateTime(DateUtil.getTimeByHourAndDate(7, userCreateTimeDate));
                    insert(user, dynamic, dynamicInfo, attacheInfo);
                }
            }
        }
        log.info("文件导入成功！");
        return "文件导入成功！";
    }
}
