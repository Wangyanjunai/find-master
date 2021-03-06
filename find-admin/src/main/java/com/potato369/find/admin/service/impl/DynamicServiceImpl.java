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
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
@Transactional(isolation = Isolation.SERIALIZABLE, rollbackFor = Exception.class, timeout = 30)
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
                    BeanUtils.copyProperties(user, user2);
                    user2.setUpdateTime(new Date());
                    userResult = this.userMapperWrite.updateByPrimaryKeySelective(user2);
                }
            }
            if (dynamicIdLong != null) {
                Dynamic dynamic2 = this.dynamicDaoUseJdbcTemplate.getById(dynamicIdLong);
                if (dynamic2 == null) {
                    dynamicResult = this.dynamicMapperWrite.insertImport(dynamic);
                } else {
                    BeanUtils.copyProperties(dynamic, dynamic2);
                    dynamic2.setUpdateTime(new Date());
                    dynamicResult = this.dynamicMapperWrite.updateByPrimaryKeySelective(dynamic2);
                }
            }
            if (dynamicInfoIdLong != null) {
                DynamicInfo dynamicInfo2 = this.dynamicInfoDaoUseJdbcTemplate.getById(dynamicInfoIdLong);
                if (dynamicInfo2 == null) {
                    dynamicInfoResult = this.dynamicInfoMapperWrite.insertImport(dynamicInfo);
                } else {
                    BeanUtils.copyProperties(dynamicInfo, dynamicInfo2);
                    dynamicInfo2.setUpdateTime(new Date());
                    dynamicInfoResult = this.dynamicInfoMapperWrite.updateByPrimaryKeySelective(dynamicInfo2);
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
    public String ajaxUploadExcel(HttpServletRequest request, HttpServletResponse response) throws Exception {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        MultipartFile file = multipartRequest.getFile("upfile");
        assert file != null;
        if (file.isEmpty()) {
            log.error("??????????????????");
            return "??????????????????";
        }
        List<List<Object>> listObject;
        try {
            listObject = new ExcelUtil().getBankListByExcel(file.getInputStream(), file.getOriginalFilename());
        } catch (Exception e) {
            log.error("??????????????????", e);
            return "??????????????????";
        }
        // ???????????????service?????????????????????????????????????????????????????????????????????
        for (List<Object> lo : listObject) {
            if (!lo.isEmpty() && StrUtil.isNotEmpty(lo.get(0).toString()) && lo.size() == 24) {
                String dir = String.valueOf(lo.get(22));
                Long userIdLong;
                String datetimeString;
                String phoneString = String.valueOf(lo.get(0)); // ????????????
                String nicknameString = String.valueOf(lo.get(1)); // ??????
                String welkinIdString = String.valueOf(lo.get(2)); // ?????????
                String genderString = String.valueOf(lo.get(3)); // ??????
                String yearString = String.valueOf(lo.get(4)); // ????????????
                String monthString = String.valueOf(lo.get(5)); // ????????????
                String dateString = String.valueOf(lo.get(6)); // ?????????????????????
                String constellationString = String.valueOf(lo.get(7)); // ??????
                String headIconString = String.valueOf(lo.get(8)); // ????????????
                String provinceString = String.valueOf(lo.get(9)); // ????????????????????????
                String cityString = String.valueOf(lo.get(10)); // ????????????????????????
                User user;
                Dynamic dynamic;
                DynamicInfo dynamicInfo;
                if (StrUtil.isNotEmpty(dir)) {
                    String[] dirArray = StrUtil.split(dir, "/");
                    userIdLong = Long.parseLong(dirArray[0]);
                    datetimeString = dirArray[1];
                    user = this.userDaoUseJdbcTemplate.getById(userIdLong);
                    if (user == null && StrUtil.isNotEmpty(phoneString)) {
                        user = this.userDaoUseJdbcTemplate.getByPhone(phoneString);
                    }
                    if (user == null) {
                        user = new User();
                    }
                    user.setId(userIdLong);// ??????id
                    user.setPhone(phoneString);// ????????????
                    if (StrUtil.isEmpty(nicknameString)) {
                        nicknameString = new RandomNickNameUtil().randomName();
                    }
                    user.setStatus(UserStatusEnum.Normal.getCode().toString());
                    user.setIsTest(UserIsTestEnum.Yes.getCode().toString());
                    user.setNickName(nicknameString);// ??????
                    if (StrUtil.isNotEmpty(welkinIdString)) {
                        user.setWeixinId(welkinIdString);// ?????????
                    }
                    if (StrUtil.isNotEmpty(genderString)) {
                        user.setGender(genderString);// ??????
                    }
                    if (StrUtil.isNotEmpty(yearString)) {
                        user.setYear(yearString);// ????????????
                    }
                    if (StrUtil.isNotEmpty(monthString)) {
                        user.setMonth(monthString);// ????????????
                    }
                    if (StrUtil.isNotEmpty(dateString)) {
                        user.setDate(dateString);// ?????????????????????
                    }
                    if (StrUtil.isNotEmpty(constellationString)) {
                        user.setConstellation(constellationString);// ??????
                    }
                    if (StrUtil.isNotEmpty(headIconString)) {
                        user.setHeadIcon(headIconString);// ????????????
                    }
                    user.setCountry("??????");// ?????????????????????
                    if (StrUtil.isNotEmpty(provinceString)) {
                        user.setProvince(provinceString);// ????????????????????????
                    }
                    if (StrUtil.isNotEmpty(cityString)) {
                        user.setCity(cityString); // ????????????????????????
                    }
                    user.setGrade(UserGradeEnum.VIP0.getCode().toString());
                    user.setImei(UUIDUtil.genIMEI());
                    user.setModel("?????? P40");
                    user.setSysName("Android");
                    user.setSysCode("11");
                    user.setNetworkMode("WIFI");
                    user.setPlatform("HuaWei");
                    user.setIp("183.14.29.243");
                    Date userCreateTimeDate = DateUtil.randomDateTime(datetimeString);
                    Date userUpdateTimeDate = DateUtil.getTimeByHourAndDate(2, userCreateTimeDate);
                    user.setCreateTime(userCreateTimeDate);
                    user.setUpdateTime(userUpdateTimeDate);
                    //Long dynamicIdLong = Long.parseLong(String.valueOf(lo.get(11)));// ????????????id
                    String dynamicProvinceString = String.valueOf(lo.get(12)); // ??????????????????????????????
                    log.info("dynamicProvinceString={}", dynamicProvinceString);
                    String dynamicCityString = String.valueOf(lo.get(13)); // ??????????????????????????????
                    log.info("dynamicCityString={}", dynamicCityString);
                    dynamic = this.dynamicDaoUseJdbcTemplate.getById(userIdLong);
                    if (dynamic == null) {
                        dynamic = new Dynamic();
                    }
                    dynamic.setId(userIdLong);
                    dynamic.setUserId(user.getId());
                    dynamic.setNickName(user.getNickName());
                    dynamic.setImei(user.getImei());
                    dynamic.setModel(user.getModel());
                    dynamic.setSysName(user.getSysName());
                    dynamic.setSysCode(user.getSysCode());
                    dynamic.setNetworkMode(user.getNetworkMode());
                    dynamic.setCountry("??????");
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
                    Long dynamicInfoIdLong = Long.parseLong(String.valueOf(lo.get(14)));// ????????????id

                    String contentString = String.valueOf(lo.get(15));// ????????????
                    String contentStatusString = String.valueOf(lo.get(16));// ??????????????????
                    String publishStatusString = String.valueOf(lo.get(17));// ????????????????????????
                    Integer likesInteger = Integer.parseInt(String.valueOf(lo.get(18)));// ?????????
                    Integer applicationsInteger = Integer.parseInt(String.valueOf(lo.get(19)));// ??????????????????
                    String attacheTypeString = String.valueOf(lo.get(20));// ????????????
                    log.info("attacheTypeString={}", attacheTypeString);
                    Integer attacheNumberInteger = Integer.parseInt(String.valueOf(lo.get(21)));// ????????????
                    dynamicInfo = this.dynamicInfoDaoUseJdbcTemplate.getById(dynamicInfoIdLong);
                    if (dynamicInfo == null) {
                        dynamicInfo = new DynamicInfo();
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
                    dynamicInfo.setCountry("??????");
                    if (StrUtil.isNotEmpty(dynamicProvinceString)) {
                        dynamicInfo.setProvince(dynamicProvinceString);
                    } else {
                        dynamicInfo.setProvince(dynamic.getProvince());
                    }
                    if (StrUtil.isNotEmpty(dynamicCityString)) {
                        dynamicInfo.setCity(dynamicCityString);
                    } else {
                        dynamicInfo.setCity(dynamic.getCity());
                    }
                    String attacheNameStrings = String.valueOf(lo.get(23));// ??????????????????
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
        log.info("?????????????????????");
        return "?????????????????????";
    }
}
