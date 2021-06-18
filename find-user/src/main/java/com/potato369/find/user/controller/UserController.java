package com.potato369.find.user.controller;

import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.StrUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.potato369.find.common.api.CommonResult;
import com.potato369.find.common.api.ResultCode;
import com.potato369.find.common.constants.ConstellationConstant;
import com.potato369.find.common.dto.*;
import com.potato369.find.common.enums.*;
import com.potato369.find.common.utils.*;
import com.potato369.find.common.vo.*;
import com.potato369.find.mbg.mapper.*;
import com.potato369.find.mbg.model.*;
import com.potato369.find.user.config.props.AliyunProps;
import com.potato369.find.user.config.props.ProjectUrlProps;
import com.potato369.find.user.dao.DynamicDaoUseJdbcTemplate;
import com.potato369.find.user.dao.DynamicInfoDaoUseJdbcTemplate;
import com.potato369.find.user.dao.ReportCategoryDaoUseJdbcTemplate;
import com.potato369.find.user.dao.UserDaoUseJdbcTemplate;
import com.potato369.find.user.feign.UserLogService;
import com.potato369.find.user.service.TagService;
import com.potato369.find.user.service.UserService;
import com.potato369.find.user.utils.RandomNickNameUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Api(value = "用户模块用户管理控制器类")
@Slf4j
@RestController
@RequestMapping("/v1/user")
public class UserController {

    private UserMapper userMapperWrite;

    private UserMapper userMapperReader;

    private UserDaoUseJdbcTemplate userDaoUseJdbcTemplate;

    private DynamicMapper dynamicMapperWriter;

    private DynamicDaoUseJdbcTemplate dynamicDaoUseJdbcTemplate;

    private DynamicInfoDaoUseJdbcTemplate dynamicInfoDaoUseJdbcTemplate;

    private ReportCategoryDaoUseJdbcTemplate reportCategoryDaoUseJdbcTemplate;

    private OperateRecordMapper operateRecordMapperWrite;

    private ReportCategoryMapper reportCategoryMapper;

    private ReportInfoMapper reportInfoMapper;

    private DynamicInfoMapper dynamicInfoMapperRead;

    private BlacklistRecordMapper blacklistRecordMapperRead;

    private BlacklistRecordMapper blacklistRecordMapperWrite;

    private AliyunProps aliyunProps;

    private UserLogService userLogOpenFeign;

    private UserService userService;

    private ProjectUrlProps projectUrlProps;

    private TagService tagService;

    private ProfessionsMapper professionsMapperReader;

    private IndustrysMapper industrysMapperReader;

    private ScreenSettingMapper screenSettingMapperReader;

    @Autowired
    public void setUserMapperWrite(UserMapper userMapperWrite) {
        this.userMapperWrite = userMapperWrite;
    }

    @Autowired
    public void setUserMapperReader(UserMapper userMapperReader) {
        this.userMapperReader = userMapperReader;
    }

    @Autowired
    public void setUserDaoUseJdbcTemplate(UserDaoUseJdbcTemplate userDaoUseJdbcTemplate) {
        this.userDaoUseJdbcTemplate = userDaoUseJdbcTemplate;
    }

    @Autowired
    public void setDynamicMapperWriter(DynamicMapper dynamicMapperWriter) {
        this.dynamicMapperWriter = dynamicMapperWriter;
    }

    @Autowired
    public void setDynamicDaoUseJdbcTemplate(DynamicDaoUseJdbcTemplate dynamicDaoUseJdbcTemplate) {
        this.dynamicDaoUseJdbcTemplate = dynamicDaoUseJdbcTemplate;
    }

    @Autowired
    public void setDynamicInfoDaoUseJdbcTemplate(DynamicInfoDaoUseJdbcTemplate dynamicInfoDaoUseJdbcTemplate) {
        this.dynamicInfoDaoUseJdbcTemplate = dynamicInfoDaoUseJdbcTemplate;
    }

    @Autowired
    public void setReportCategoryDaoUseJdbcTemplate(ReportCategoryDaoUseJdbcTemplate reportCategoryDaoUseJdbcTemplate) {
        this.reportCategoryDaoUseJdbcTemplate = reportCategoryDaoUseJdbcTemplate;
    }

    @Autowired
    public void setOperateRecordMapperWrite(OperateRecordMapper operateRecordMapperWrite) {
        this.operateRecordMapperWrite = operateRecordMapperWrite;
    }

    @Autowired
    public void setReportCategoryMapper(ReportCategoryMapper reportCategoryMapper) {
        this.reportCategoryMapper = reportCategoryMapper;
    }

    @Autowired
    public void setReportInfoMapper(ReportInfoMapper reportInfoMapper) {
        this.reportInfoMapper = reportInfoMapper;
    }

    @Autowired
    public void setDynamicInfoMapperRead(DynamicInfoMapper dynamicInfoMapperRead) {
        this.dynamicInfoMapperRead = dynamicInfoMapperRead;
    }

    @Autowired
    public void setBlacklistRecordMapperRead(BlacklistRecordMapper blacklistRecordMapperRead) {
        this.blacklistRecordMapperRead = blacklistRecordMapperRead;
    }

    @Autowired
    public void setBlacklistRecordMapperWrite(BlacklistRecordMapper blacklistRecordMapperWrite) {
        this.blacklistRecordMapperWrite = blacklistRecordMapperWrite;
    }

    @Autowired
    public void setProfessionsMapperReader(ProfessionsMapper professionsMapperReader) {
        this.professionsMapperReader = professionsMapperReader;
    }

    @Autowired
    public void setAliyunProps(AliyunProps aliyunProps) {
        this.aliyunProps = aliyunProps;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setUserLogOpenFeign(UserLogService userLogOpenFeign) {
        this.userLogOpenFeign = userLogOpenFeign;
    }

    @Autowired
    public void setProjectUrlProps(ProjectUrlProps projectUrlProps) {
        this.projectUrlProps = projectUrlProps;
    }

    @Autowired
    public void setTagService(TagService tagService) {
        this.tagService = tagService;
    }

    @Autowired
    public void setIndustrysMapperReader(IndustrysMapper industrysMapperReader) {
        this.industrysMapperReader = industrysMapperReader;
    }

    @Autowired
    public void setScreenSettingMapperReader(ScreenSettingMapper screenSettingMapperReader) {
        this.screenSettingMapperReader = screenSettingMapperReader;
    }

    //上报或者更新极光推送唯一设备的标识接口
    @ApiOperation(value = "上报或者更新极光推送唯一设备的标识接口", notes = "上报或者更新极光推送唯一设备的标识接口", response = CommonResult.class)
    @PutMapping(value = "/{id}/uploadRegId.do")
    public CommonResult<Map<String, Object>> uploadRegId(@PathVariable(name = "id", required = true) Long id,//id：用户id
                                                         @RequestParam(name = "regId", required = true) String regId) {//regId：极光推送唯一设备的标识
        Map<String, Object> result = new ConcurrentHashMap<>();
        String data = "UPLOADREGID";
        String message = "FAILED";
        try {
            if (log.isDebugEnabled()) {
                log.debug("开始上报或者更新极光推送唯一设备的标识");
            }
            User user = this.userDaoUseJdbcTemplate.getById(id);
            if (user == null) {
                result.put(data, message);
                return CommonResult.failed(result, ResultCode.USER_IS_NOT_EXIST);
            }
            String regIdOld = user.getReserveColumn03();
            if (StrUtil.isNotEmpty(regId) && !regId.equals(regIdOld)) {
                user.setReserveColumn03(regId);
                user.setUpdateTime(new Date());
                int row = this.userMapperWrite.updateByPrimaryKeySelective(user);
                if (row > 0) {
                    message = "OK";
                }
            }
        } catch (Exception e) {
            log.error("上报或者更新极光推送唯一设备的标识出现错误", e);
        } finally {
            if (log.isDebugEnabled()) {
                log.debug("结束上报或者更新极光推送唯一设备的标识");
            }
        }
        result.put(data, message);
        return CommonResult.success(result);
    }

    //上传或者修改头像小图接口
    @ApiOperation(value = "上传或者修改头像小图接口", notes = "上传或者修改头像小图接口", response = CommonResult.class)
    @PutMapping(value = "/{id}/head.do", consumes = {"multipart/form-data;charset=utf-8"}, produces = {"application/json;charset=utf-8"})
    public CommonResult<Map<String, Object>> handleHeadIconUpload(@PathVariable(name = "id", required = true) Long id,
                                                                  @RequestPart(name = "headIconFile", required = true) MultipartFile headIconFile01) {
        if (log.isDebugEnabled()) {
            log.debug("开始上传头像小图片");
            log.debug("前端传输过来的用户id={}", id);
        }
        Map<String, Object> data = new ConcurrentHashMap<>();
        data.put("id", id);
        User user1 = this.userMapperReader.selectByPrimaryKey(id);
        OperateRecord operateRecord = new OperateRecord();
        operateRecord.setUserId(id);
        operateRecord.setStatus(OperateRecordStatusEnum.Fail.getCode().toString());
        operateRecord.setType(OperateRecordTypeEnum.UploadHeadIcon.getCode());
        try {
            // 头像图片上传服务器
            String headIconFileName;

            // 头像图片nginx服务器URL
            StringBuilder headIconFileUrlBf = new StringBuilder()
                    .append(StrUtil.trimToNull(this.projectUrlProps.getResDomain()))
                    .append(StrUtil.trimToNull(this.projectUrlProps.getProjectName()))
                    .append(StrUtil.trimToNull(this.projectUrlProps.getResHeadIcon()))
                    .append(user1.getId())
                    .append("/");
            // 头像图片存储本地路径
            String headIconFilePath = StrUtil.trimToNull(this.projectUrlProps.getUploadRes())
                    + StrUtil.trimToNull(this.projectUrlProps.getProjectName())
                    + StrUtil.trimToNull(this.projectUrlProps.getResHeadIcon())
                    + user1.getId();
            if (headIconFile01 != null && !headIconFile01.isEmpty()) {
                if (!FileTypeUtil.isImageType(headIconFile01.getContentType(), headIconFile01.getOriginalFilename())) {
                    try {
                        this.userLogOpenFeign.record(id, operateRecord);
                    } catch (Exception e) {
                        log.error("记录用户操作记录失败", e);
                    }
                    return CommonResult.validateFailed("上传头像文件类型不符合要求。");
                }
                String oldFileName = headIconFile01.getOriginalFilename();
                String newFileName = null;
                if (oldFileName != null) {
                    newFileName = UUID.randomUUID() + oldFileName.substring(oldFileName.lastIndexOf("."));
                }
                assert newFileName != null;
                File headIconFile = new File(headIconFilePath, newFileName);
                headIconFileName = headIconFile.getName();
                try {
                    if (StrUtil.isNotEmpty(user1.getHeadIcon()) && !headIconFileName.equals(user1.getHeadIcon())) {
                        File oldHeadIconFile = new File(headIconFilePath, user1.getHeadIcon());
                        //删除之前服务器的头像
                        if (oldHeadIconFile.exists()) {
                            oldHeadIconFile.delete();
                        }
                        if (!headIconFile.getParentFile().exists()) {
                            headIconFile.getParentFile().mkdirs();
                        }
                        //重新上传新的头像到服务器，并更新用户数据库头像信息
                        headIconFile01.transferTo(headIconFile);
                        user1.setHeadIcon(headIconFileName);
                        user1.setUpdateTime(new Date());
                        operateRecord.setType(OperateRecordTypeEnum.UpdateHeadIcon.getCode());
                        this.userMapperWrite.updateByPrimaryKeySelective(user1);
                        operateRecord.setStatus(OperateRecordStatusEnum.Success.getCode().toString());
                    }
                    headIconFileUrlBf.append(headIconFileName);
                } catch (Exception e) {
                    log.error("上传用户头像小图到Nginx服务器出现错误", e);
                    headIconFileUrlBf.append(user1.getHeadIcon());
                }
                if (log.isDebugEnabled()) {
                    log.debug("上传用户头像小图到Nginx服务器成功，file={}", headIconFile.getAbsolutePath() + headIconFile.getName());
                }
            }
            data.put("head", headIconFileUrlBf);
            try {
                this.userLogOpenFeign.record(id, operateRecord);
            } catch (Exception e) {
                log.error("记录用户操作记录失败", e);
            }
        } catch (Exception e) {
            log.error("上传头像小图到Nginx服务器失败", e);
            return CommonResult.failed("上传头像小图到Nginx服务器失败");
        } finally {
            if (log.isDebugEnabled()) {
                log.debug("结束上传头像小图片");
            }
        }
        return CommonResult.success(data);
    }

    //上传或者修改背景图片接口
    @ApiOperation(value = "上传或者修改背景图片接口", notes = "上传或者修改背景图片接口", response = CommonResult.class)
    @PutMapping(value = "/{id}/background.do", consumes = {"multipart/form-data;charset=utf-8"}, produces = {"application/json;charset=utf-8"})
    public CommonResult<Map<String, Object>> handleBackgroundIconUpload(
            @PathVariable(name = "id", required = true) Long id,
            @RequestPart(name = "backgroundIconFile", required = true) MultipartFile backgroundIconFile02) {
        if (log.isDebugEnabled()) {
            log.debug("开始上传背景图片");
            log.debug("前端传输过来的用户id={}", id);
        }
        Map<String, Object> data = new ConcurrentHashMap<>();
        data.put("id", id);
        User user1 = this.userMapperReader.selectByPrimaryKey(id);
        OperateRecord operateRecord = new OperateRecord();
        operateRecord.setUserId(id);
        operateRecord.setStatus(OperateRecordStatusEnum.Fail.getCode().toString());
        try {
            // 背景图片上传服务器
            String bgIconFileName;
            // 背景图片nginx服务器URL
            StringBuilder bgIconFileUrlBf = new StringBuilder()
                    .append(StrUtil.trimToNull(this.projectUrlProps.getResDomain()))
                    .append(StrUtil.trimToNull(this.projectUrlProps.getProjectName()))
                    .append(StrUtil.trimToNull(this.projectUrlProps.getResBackgroundIcon()))
                    .append(user1.getId())
                    .append("/");
            // 背景图片存储本地路径
            String bgIconFilePath = StrUtil.trimToNull(this.projectUrlProps.getUploadRes())
                    + StrUtil.trimToNull(this.projectUrlProps.getProjectName())
                    + StrUtil.trimToNull(this.projectUrlProps.getResBackgroundIcon())
                    + user1.getId();
            if (backgroundIconFile02 != null && !backgroundIconFile02.isEmpty()) {
                if (!FileTypeUtil.isImageType(backgroundIconFile02.getContentType(), backgroundIconFile02.getOriginalFilename())) {
                    try {
                        this.userLogOpenFeign.record(id, operateRecord);
                    } catch (Exception e) {
                        log.error("记录用户操作记录失败", e);
                    }
                    return CommonResult.validateFailed("上传背景图片文件类型不符合要求。");
                }
                String oldFileName = backgroundIconFile02.getOriginalFilename();
                String newFileName = null;
                if (oldFileName != null) {
                    newFileName = UUID.randomUUID() + oldFileName.substring(oldFileName.lastIndexOf("."));
                }
                assert newFileName != null;
                File bgIconFile = new File(bgIconFilePath, newFileName);
                bgIconFileName = bgIconFile.getName();
                try {
                    if (StrUtil.isNotEmpty(user1.getBackgroundIcon()) && !bgIconFileName.equals(user1.getBackgroundIcon())) {
                        // 删除之前服务器的背景
                        File oldBgIconFile = new File(bgIconFilePath, user1.getBackgroundIcon());
                        if (oldBgIconFile.exists()) {
                            oldBgIconFile.delete();
                        }
                    }
                    if (!bgIconFile.getParentFile().exists()) {
                        bgIconFile.getParentFile().mkdirs();
                    }
                    // 重新上传新的头像到服务器，并更新用户数据库头像信息
                    backgroundIconFile02.transferTo(bgIconFile);
                    user1.setBackgroundIcon(bgIconFileName);
                    user1.setUpdateTime(new Date());
                    operateRecord.setType(OperateRecordTypeEnum.UpdateBackgroundIcon.getCode());
                    this.userMapperWrite.updateByPrimaryKeySelective(user1);
                    operateRecord.setStatus(OperateRecordStatusEnum.Success.getCode().toString());
                    bgIconFileUrlBf.append(bgIconFileName);
                } catch (Exception e) {
                    log.error("上传用户头像小图到Nginx服务器出现错误", e);
                    bgIconFileUrlBf.append(user1.getBackgroundIcon());
                }
                if (log.isDebugEnabled()) {
                    log.debug("上传用户头像小图到Nginx服务器成功，file={}", bgIconFile.getAbsolutePath() + bgIconFile.getName());
                }
            }
            try {
                this.userLogOpenFeign.record(id, operateRecord);
            } catch (Exception e) {
                log.error("记录用户操作记录失败", e);
            }
            data.put("bg", bgIconFileUrlBf);
        } catch (Exception e) {
            log.error("上传背景图片到Nginx服务器失败", e);
            return CommonResult.failed("上传背景图片到Nginx服务器失败");
        } finally {
            if (log.isDebugEnabled()) {
                log.debug("结束上传背景图片");
            }
        }
        return CommonResult.success(data);
    }

    //注册接口
    @ApiOperation(value = "注册接口", notes = "注册接口", response = CommonResult.class)
    @PostMapping(value = "/reg.do", consumes = {"multipart/form-data;charset=utf-8"}, produces = {"application/json;charset=utf-8"})
    public CommonResult<Map<String, UserVO2>> register(
            @Valid UserDTO userDTO, BindingResult bindingResult,
            @RequestPart(value = "head", required = false) MultipartFile head) { // head：头像图片文件
        Map<String, UserVO2> map = new ConcurrentHashMap<>();
        UserVO2 userVO2 = UserVO2.builder().build();
        String message = "注册失败。";
        OperateRecord operateRecord = new OperateRecord();
        operateRecord.setStatus(OperateRecordStatusEnum.Fail.getCode().toString());
        operateRecord.setType(OperateRecordTypeEnum.CreateUser.getCode());
        operateRecord.setUserId(0L);
        try {
            if (log.isDebugEnabled()) {
                log.debug("开始注册");
                log.debug("前端传输过来的用户信息user={}", userDTO);
            }
            if (bindingResult.hasErrors()) {
                try {
                    this.userLogOpenFeign.record(0L, operateRecord);
                } catch (Exception e) {
                    log.error("记录用户操作记录失败", e);
                }
                return CommonResult.validateFailed(Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage());
            }
            if (!RegexUtil.isMathPhone(userDTO.getPhone())) {
                try {
                    this.userLogOpenFeign.record(0L, operateRecord);
                } catch (Exception e) {
                    log.error("记录用户操作记录失败", e);
                }
                return CommonResult.validateFailed("手机号码参数校验失败，手机号码格式不正确。");
            }
            Double longitude = userDTO.getLongitude();
            String longitudeStr = "";
            if (longitude != null) {
                longitudeStr = String.valueOf(longitude);
            }
            Double latitude = userDTO.getLatitude();
            String latitudeStr = "";
            if (latitude != null) {
                latitudeStr = String.valueOf(latitude);
            }
            if (StrUtil.isAllEmpty(userDTO.getIp(), userDTO.getCountry(), userDTO.getProvince(), userDTO.getCity(), longitudeStr, latitudeStr)) {
                try {
                    this.userLogOpenFeign.record(0L, operateRecord);
                } catch (Exception e) {
                    log.error("记录用户操作记录失败", e);
                }
                return CommonResult.validateFailed("客户端IP，定位（国家、省份、城市、经度、纬度）参数校验失败，客户端IP，定位（国家、省份、城市、经度，纬度）不能同时为空。");
            }
            if (StrUtil.isNotEmpty(userDTO.getIp()) && !RegexUtil.isMathIp(userDTO.getIp())) {
                try {
                    this.userLogOpenFeign.record(0L, operateRecord);
                } catch (Exception e) {
                    log.error("记录用户操作记录失败", e);
                }
                return CommonResult.validateFailed("客户端IP参数校验失败，客户端IP格式不正确。");
            }
            if (StrUtil.isNotEmpty(userDTO.getConstellation())) {
                ConstellationConstant ConstellationConstant = new ConstellationConstant();
                if (!ConstellationConstant.getConstellationList().contains(userDTO.getConstellation())) {
                    try {
                        this.userLogOpenFeign.record(0L, operateRecord);
                    } catch (Exception e) {
                        log.error("记录用户操作记录失败", e);
                    }
                    return CommonResult.validateFailed("星座参数校验不通过，星座值非法。");
                }
            }
            User user = this.userDaoUseJdbcTemplate.getByPhone(userDTO.getPhone());
            String fileString1 = "";
            if (user == null) {
                user = new User();
                BeanUtils.copyProperties(userDTO, user);
                String nickname = userDTO.getNickname();
                if (StrUtil.isEmpty(nickname)) {
                    user.setNickName(new RandomNickNameUtil().randomName());
                } else {
                    user.setNickName(nickname);
                }
                if (StrUtil.isAllEmpty(userDTO.getCountry(), userDTO.getProvince(), userDTO.getCity(), longitudeStr, latitudeStr)) {
                    // 根据IP调用百度定位获取地址
                    if (StrUtil.isNotEmpty(userDTO.getIp())) {
                        LocationDTO locationDTO = IPLocationUtil.getLocationByAliyunIP(StrUtil.trimToEmpty(this.aliyunProps.getAppcode()), StrUtil.trimToEmpty(this.aliyunProps.getUrl()), userDTO.getIp());
                        user.setIp(locationDTO.getIp());
                        user.setCountry(locationDTO.getCountry());
                        user.setProvince(locationDTO.getProvince());
                        user.setCity(locationDTO.getCity());
                        user.setDistrict(locationDTO.getDistrict());
                        user.setOther(locationDTO.getOther());
                        user.setLongitude(locationDTO.getLongitude());
                        user.setLatitude(locationDTO.getLatitude());
                    }
                }
                String autograph = userDTO.getAutograph();
                if (StrUtil.isEmpty(autograph)) {
                    if (UserGenderEnum.Female.getCode().toString().equals(user.getGender())) {
                        user.setAutograph(StrUtil.trimToNull(this.projectUrlProps.getDefaultFemaleContent()));
                    }
                    if (UserGenderEnum.Male.getCode().toString().equals(user.getGender())) {
                        user.setAutograph(StrUtil.trimToNull(this.projectUrlProps.getDefaultMaleContent()));
                    }
                }
                // 头像图片上传服务器
                int result = this.userMapperWrite.insertSelective(user);
                // 头像图片存储本地路径
                String headIconFilePath = StrUtil.trimToNull(this.projectUrlProps.getUploadRes())
                        + StrUtil.trimToNull(this.projectUrlProps.getProjectName())
                        + StrUtil.trimToNull(this.projectUrlProps.getResHeadIcon())
                        + user.getId();
                // 头像图片上传服务器
                String newHeadIconFileName = null;
                if (head != null && !head.isEmpty()) {
                    if (!FileTypeUtil.isImageType(head.getContentType(), Objects.requireNonNull(head.getOriginalFilename()))) {
                        try {
                            this.userLogOpenFeign.record(0L, operateRecord);
                        } catch (Exception e) {
                            log.error("记录用户操作记录失败", e);
                        }
                        return CommonResult.validateFailed("上传头像文件类型不符合要求。");
                    }
                    String oldFileName = head.getOriginalFilename();
                    String newFileName = null;
                    if (oldFileName != null) {
                        newFileName = UUID.randomUUID() + oldFileName.substring(oldFileName.lastIndexOf("."));
                    }
                    assert newFileName != null;
                    File newHeadIconFile = new File(headIconFilePath, newFileName);
                    newHeadIconFileName = newHeadIconFile.getName();
                    StringBuilder filePath = new StringBuilder()
                            .append(StrUtil.trimToNull(this.projectUrlProps.getUploadRes()))
                            .append(StrUtil.trimToNull(this.projectUrlProps.getProjectName()))
                            .append(StrUtil.trimToNull(this.projectUrlProps.getResDynamicImageFile()));
                    fileString1 = user.getId() +
                            "/" +
                            DateUtil.getDays() +
                            "/" +
                            System.currentTimeMillis() +
                            "/";
                    // 附件文件存放路径
                    String fileString = filePath.append(fileString1).toString();
                    //发布一条动态
                    File newHeadIconFileDy = new File(fileString, newFileName);
                    try {
                        if (!newHeadIconFile.getParentFile().exists()) {
                            newHeadIconFile.getParentFile().mkdirs();
                        }
                        if (!newHeadIconFileDy.getParentFile().exists()) {
                            newHeadIconFileDy.getParentFile().mkdirs();
                        }
                        //重新上传新的头像到服务器，并更新用户数据库头像信息
                        head.transferTo(newHeadIconFile);
                        FileUtil.copyDir(newHeadIconFile.getParent(), newHeadIconFileDy.getParent());
                        operateRecord.setStatus(OperateRecordStatusEnum.Success.getCode().toString());
                        user.setHeadIcon(newHeadIconFileName);
                        user.setUpdateTime(new Date());
                        this.userMapperWrite.updateByPrimaryKeySelective(user);
                    } catch (Exception e) {
                        log.error("上传用户头像小图到Nginx服务器出现错误", e);
                    }
                    if (log.isDebugEnabled()) {
                        log.debug("上传用户头像小图到Nginx服务器成功，file={}", newHeadIconFile.getAbsolutePath() + newHeadIconFile.getName());
                    }
                }
                if (result > 0) {
                    operateRecord.setStatus(OperateRecordStatusEnum.Success.getCode().toString());
                    operateRecord.setUserId(user.getId());
                    if (head != null && !head.isEmpty()) {
                        if (!FileTypeUtil.isImageType(head.getContentType(), Objects.requireNonNull(head.getOriginalFilename()))) {
                            try {
                                this.userLogOpenFeign.record(0L, operateRecord);
                            } catch (Exception e) {
                                log.error("记录用户操作记录失败", e);
                            }
                            return CommonResult.validateFailed("上传发布动态图片文件类型不符合要求。");
                        }
                        this.userService.uploadHeadIcon1(user, autograph, fileString1 + newHeadIconFileName, operateRecord);
                    } else {
                        try {
                            this.userLogOpenFeign.record(0L, operateRecord);
                        } catch (Exception e) {
                            log.error("记录用户操作记录失败", e);
                        }
                        return CommonResult.validateFailed("上传发布动态图片文件不能为空。");
                    }
                    message = "注册成功。";
                }
            } else {
                //更新用户定位或者ip
                if (StrUtil.isAllEmpty(userDTO.getCountry(), userDTO.getProvince(), userDTO.getCity(), longitudeStr, latitudeStr)) {
                    if (StrUtil.isNotEmpty(userDTO.getIp())) {
                        LocationDTO locationDTO = IPLocationUtil.getLocationByAliyunIP(StrUtil.trimToEmpty(this.aliyunProps.getAppcode()), StrUtil.trimToNull(this.aliyunProps.getUrl()), userDTO.getIp());
                        if (StrUtil.isNotEmpty(user.getIp()) && !user.getIp().equals(locationDTO.getIp())) {
                            user.setIp(locationDTO.getIp());
                        }
                        if (StrUtil.isNotEmpty(user.getCountry()) && !user.getCountry().equals(locationDTO.getCountry())) {
                            user.setCountry(locationDTO.getCountry());
                        }
                        if (StrUtil.isNotEmpty(user.getProvince()) && !user.getProvince().equals(locationDTO.getProvince())) {
                            user.setProvince(locationDTO.getProvince());
                        }
                        if (StrUtil.isNotEmpty(user.getCity()) && !user.getCity().equals(locationDTO.getCity())) {
                            user.setCity(locationDTO.getCity());
                        }
                        if (user.getLongitude().equals(locationDTO.getLongitude())) {
                            user.setLongitude(locationDTO.getLongitude());
                        }
                        if (user.getLatitude().equals(locationDTO.getLatitude())) {
                            user.setLatitude(locationDTO.getLatitude());
                        }
                    }
                } else {
                    if (StrUtil.isNotEmpty(user.getIp()) && !user.getIp().equals(userDTO.getIp())) {
                        user.setIp(userDTO.getIp());
                    }
                    if (StrUtil.isNotEmpty(user.getCountry()) && !user.getCountry().equals(userDTO.getCountry())) {
                        user.setCountry(userDTO.getCountry());
                    }
                    if (StrUtil.isNotEmpty(user.getProvince()) && !user.getProvince().equals(userDTO.getProvince())) {
                        user.setProvince(userDTO.getProvince());
                    }
                    if (StrUtil.isNotEmpty(user.getCity()) && !user.getCity().equals(userDTO.getCity())) {
                        user.setCity(userDTO.getCity());
                    }
                    if (user.getLongitude().equals(Double.parseDouble(String.valueOf(userDTO.getLongitude())))) {
                        user.setLongitude(Double.parseDouble(String.valueOf(userDTO.getLongitude())));
                    }
                    if (user.getLatitude().equals(Double.parseDouble(String.valueOf(userDTO.getLatitude())))) {
                        user.setLatitude(Double.parseDouble(String.valueOf(userDTO.getLatitude())));
                    }
                }
                user.setUpdateTime(new Date());
                this.userMapperWrite.updateByPrimaryKeySelective(user);
            }
            userVO2.setId(user.getId());
            userVO2.setNickname(user.getNickName());
            userVO2.setAutograph(user.getAutograph());
            userVO2.setGender(user.getGender());
            userVO2.setHead(StrUtil.trimToNull(this.projectUrlProps.getResDomain()) +
                    StrUtil.trimToNull(this.projectUrlProps.getProjectName()) +
                    StrUtil.trimToNull(this.projectUrlProps.getResHeadIcon())
                    + user.getId()
                    + "/"
                    + user.getHeadIcon());
            try {
                this.userLogOpenFeign.record(user.getId(), operateRecord);
            } catch (Exception e) {
                log.error("记录用户操作记录失败", e);
            }
            map.put("userDTO", userVO2);
            return CommonResult.success(map, message);
        } catch (Exception e) {
            log.error("注册出现错误", e);
            try {
                this.operateRecordMapperWrite.insert(operateRecord);
            } catch (Exception e2) {
                log.error("记录用户操作记录失败", e);
            }
            return CommonResult.failed(message);
        } finally {
            if (log.isDebugEnabled()) {
                log.debug("结束注册");
            }
        }
    }

    //登录接口
    @ApiOperation(value = "登录接口", notes = "登录接口", response = CommonResult.class)
    @PutMapping(value = "/login.do")
    public CommonResult<Map<String, UserVO2>> login(@Valid UserDTO userDTO, BindingResult bindingResult) {
        if (log.isDebugEnabled()) {
            log.debug("开始登录");
            log.debug("前端传输过来的用户信息userDTO={}", userDTO);
        }
        Map<String, UserVO2> map = new ConcurrentHashMap<>();
        UserVO2 userVO2 = UserVO2.builder().build();
        String message = "登录失败。";
        OperateRecord operateRecord = new OperateRecord();
        operateRecord.setStatus(OperateRecordStatusEnum.Fail.getCode().toString());
        operateRecord.setType(OperateRecordTypeEnum.CreateUser.getCode());
        operateRecord.setUserId(0L);
        try {
            log.info("phone={}, ip={}, country={}, province={}, city={}, district={}, other={}, longitude={}, latitude={}", userDTO.getPhone(), userDTO.getIp(), userDTO.getCountry(), userDTO.getProvince(), userDTO.getCity(), userDTO.getDistrict(), userDTO.getOther(), userDTO.getLongitude(), userDTO.getLatitude());
            if (bindingResult.hasErrors()) {
                try {
                    this.userLogOpenFeign.record(0L, operateRecord);
                } catch (Exception e) {
                    log.error("记录用户操作记录失败", e);
                }
                return CommonResult.validateFailed(Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage());
            }
            if (!RegexUtil.isMathPhone(userDTO.getPhone())) {
                try {
                    this.userLogOpenFeign.record(0L, operateRecord);
                } catch (Exception e) {
                    log.error("记录用户操作记录失败", e);
                }
                return CommonResult.validateFailed("手机号码参数校验失败，手机号码格式不正确。");
            }

            Double longitude = userDTO.getLongitude();//经度
            String longitudeString = "";
            if (longitude != null) {
                longitudeString = String.valueOf(longitude);
            }

            Double latitude = userDTO.getLatitude();//纬度
            String latitudeString = "";
            if (latitude != null) {
                latitudeString = String.valueOf(latitude);
            }

            if (StrUtil.isAllEmpty(userDTO.getIp(), userDTO.getCountry(), userDTO.getProvince(), userDTO.getCity(), userDTO.getDistrict(), userDTO.getOther(), longitudeString, latitudeString)) {
                try {
                    this.userLogOpenFeign.record(0L, operateRecord);
                } catch (Exception e) {
                    log.error("记录用户操作记录失败", e);
                }
                return CommonResult.validateFailed("客户端IP，定位（国家、省份、城市、区/县、其它、经度、纬度）参数校验失败，客户端IP，定位（国家、省份、城市、区/县、其它、经度、纬度）不能同时为空。");
            }
            if (StrUtil.isNotEmpty(userDTO.getIp()) && !RegexUtil.isMathIp(userDTO.getIp())) {
                try {
                    this.userLogOpenFeign.record(0L, operateRecord);
                } catch (Exception e) {
                    log.error("记录用户操作记录失败", e);
                }
                return CommonResult.validateFailed("客户端IP参数校验失败，客户端IP格式不正确。");
            }
            User user = this.userDaoUseJdbcTemplate.getByPhone(userDTO.getPhone());
            if (user == null) {
                return CommonResult.failed("该手机号码未注册用户，请注册后再试。");
            }
            if (StrUtil.isAllEmpty(userDTO.getCountry(), userDTO.getProvince(), userDTO.getCity(), userDTO.getDistrict(), userDTO.getOther(), longitudeString, latitudeString)) {
                if (StrUtil.isNotEmpty(userDTO.getIp())) {
                    //更新用户定位或者ip
                    LocationDTO locationDTO = IPLocationUtil.getLocationByAliyunIP(StrUtil.trimToEmpty(this.aliyunProps.getAppcode()), StrUtil.trimToEmpty(this.aliyunProps.getUrl()), userDTO.getIp());
                    if (StrUtil.isNotEmpty(user.getIp()) && !user.getIp().equals(locationDTO.getIp())) {
                        user.setIp(locationDTO.getIp());
                    }
                    if (StrUtil.isNotEmpty(user.getCountry()) && !user.getCountry().equals(locationDTO.getCountry())) {
                        user.setCountry(locationDTO.getCountry());
                    }
                    if (StrUtil.isNotEmpty(user.getProvince()) && !user.getProvince().equals(locationDTO.getProvince())) {
                        user.setProvince(locationDTO.getProvince());
                    }
                    if (StrUtil.isNotEmpty(user.getCity()) && !user.getCity().equals(locationDTO.getCity())) {
                        user.setCity(locationDTO.getCity());
                    }
                    if (StrUtil.isNotEmpty(user.getDistrict()) && !user.getDistrict().equals(locationDTO.getDistrict())) {
                        user.setDistrict(locationDTO.getDistrict());
                    }
                    if (StrUtil.isNotEmpty(user.getOther()) && !user.getOther().equals(locationDTO.getOther())) {
                        user.setOther(locationDTO.getOther());
                    }
                    if (user.getLongitude() == null) {
                        user.setLongitude(locationDTO.getLongitude());
                    } else if (!user.getLongitude().equals(locationDTO.getLongitude())) {
                        user.setLongitude(locationDTO.getLongitude());
                    }
                    if (user.getLatitude() == null) {
                        user.setLatitude(locationDTO.getLatitude());
                    } else if (!user.getLatitude().equals(locationDTO.getLatitude())) {
                        user.setLatitude(locationDTO.getLatitude());
                    }
                }
            } else {
                if (StrUtil.isNotEmpty(user.getIp()) && !user.getIp().equals(userDTO.getIp())) {
                    user.setIp(userDTO.getIp());
                }
                if (StrUtil.isNotEmpty(user.getCountry()) && !user.getCountry().equals(userDTO.getCountry())) {
                    user.setCountry(userDTO.getCountry());
                }
                if (StrUtil.isNotEmpty(user.getProvince()) && !user.getProvince().equals(userDTO.getProvince())) {
                    user.setProvince(userDTO.getProvince());
                }
                if (StrUtil.isNotEmpty(user.getCity()) && !user.getCity().equals(userDTO.getCity())) {
                    user.setCity(userDTO.getCity());
                }
                if (StrUtil.isNotEmpty(user.getDistrict()) && !user.getDistrict().equals(userDTO.getDistrict())) {
                    user.setDistrict(userDTO.getDistrict());
                }
                if (StrUtil.isNotEmpty(user.getOther()) && !user.getOther().equals(userDTO.getOther())) {
                    user.setOther(userDTO.getOther());
                }
                if (!user.getLongitude().equals(userDTO.getLongitude())) {
                    user.setLongitude(userDTO.getLongitude());
                }
                if (!user.getLatitude().equals(userDTO.getLatitude())) {
                    user.setLatitude(userDTO.getLatitude());
                }
            }
            user.setUpdateTime(new Date());
            this.userMapperWrite.updateByPrimaryKeySelective(user);
            Dynamic dynamic = this.dynamicDaoUseJdbcTemplate.getByUserId(user.getId());
            if (dynamic != null) {
                if (StrUtil.isNotEmpty(dynamic.getCountry()) && !dynamic.getCountry().equals(user.getCountry())) {
                    dynamic.setCountry(user.getCountry());
                }
                if (StrUtil.isNotEmpty(dynamic.getProvince()) && !dynamic.getProvince().equals(user.getProvince())) {
                    dynamic.setProvince(user.getProvince());
                }
                if (StrUtil.isNotEmpty(dynamic.getCity()) && !dynamic.getCity().equals(user.getCity())) {
                    dynamic.setCity(user.getCity());
                }
                if (StrUtil.isNotEmpty(dynamic.getDistrict()) && !dynamic.getDistrict().equals(user.getDistrict())) {
                    dynamic.setDistrict(user.getDistrict());
                }
                if (StrUtil.isNotEmpty(dynamic.getOther()) && !dynamic.getOther().equals(user.getOther())) {
                    dynamic.setOther(user.getOther());
                }
                if (dynamic.getLongitude() == null) {
                    dynamic.setLongitude(user.getLongitude());
                } else if (!dynamic.getLongitude().equals(user.getLongitude())) {
                    dynamic.setLongitude(user.getLongitude());
                }
                if (dynamic.getLatitude() == null) {
                    dynamic.setLatitude(user.getLatitude());
                } else if (!dynamic.getLatitude().equals(user.getLatitude())) {
                    dynamic.setLatitude(user.getLatitude());
                }
                dynamic.setUpdateTime(new Date());
                this.dynamicMapperWriter.updateByPrimaryKey(dynamic);
            }
            message = "登录成功。";
            userVO2.setId(user.getId());
            userVO2.setNickname(user.getNickName());
            userVO2.setAutograph(user.getAutograph());
            userVO2.setGender(user.getGender());
            userVO2.setHead(StrUtil.trimToNull(this.projectUrlProps.getResDomain()) +
                    StrUtil.trimToNull(this.projectUrlProps.getProjectName()) +
                    StrUtil.trimToNull(this.projectUrlProps.getResHeadIcon())
                    + user.getId()
                    + "/"
                    + user.getHeadIcon());
            try {
                this.userLogOpenFeign.record(user.getId(), operateRecord);
            } catch (Exception e) {
                log.error("记录用户操作记录失败", e);
            }
            map.put("user", userVO2);
            return CommonResult.success(map, message);
        } catch (Exception e) {
            log.error("登录出现错误", e);
            try {
                this.operateRecordMapperWrite.insert(operateRecord);
            } catch (Exception e2) {
                log.error("记录用户操作记录失败", e);
            }
            return CommonResult.failed(message);
        } finally {
            if (log.isDebugEnabled()) {
                log.debug("结束登录");
            }
        }
    }

    //判断用户是否已经注册接口
    @GetMapping(value = "/isreg.do")
    public CommonResult<Map<String, Boolean>> isregister(@RequestParam(name = "phone") String phone) {
        if (log.isDebugEnabled()) {
            log.debug("前端传输过来的用户手机号码={}", phone);
        }
        try {
            if (log.isDebugEnabled()) {
                log.debug("开始判断用户是否已经注册");
            }
            if (!RegexUtil.isMathPhone(phone)) {
                return CommonResult.failed("手机号码格式不正确");
            }
            User user = this.userDaoUseJdbcTemplate.getByPhone(phone);
            Map<String, Boolean> map = new HashMap<>();
            if (user == null) {
                map.put("isReg", false);
                return CommonResult.success(map, "SUCCESS，还未注册。");
            } else {
                map.put("isReg", true);
                return CommonResult.success(map, "SUCCESS，已经注册。");
            }
        } catch (Exception e) {
            log.error("判断用户是否注册出现错误", e);
            return CommonResult.failed("判断用户是否注册出现错误");
        } finally {
            if (log.isDebugEnabled()) {
                log.debug("结束判断用户是否已经注册");
            }
        }
    }

    //修改或者更新用户资料接口
    @PutMapping(value = "/{id}/update.do", consumes = {"application/json;charset=utf-8"}, produces = {"application/json;charset=utf-8"})
    public CommonResult<Map<String, Object>> update(@PathVariable(name = "id", required = true) Long id,
                                                    @RequestBody UpdateUserDTO updateUserDTO) {
        if (log.isDebugEnabled()) {
            log.debug("开始修改或者更新用户资料");
        }
        if (log.isDebugEnabled()) {
            log.debug("前端传输过来的用户信息id={}，updateUserDTO={}", id, updateUserDTO);
        }
        Map<String, Object> data = new ConcurrentHashMap<>();
        OperateRecord operateRecord = new OperateRecord();
        operateRecord.setStatus(OperateRecordStatusEnum.Fail.getCode().toString());
        operateRecord.setType(OperateRecordTypeEnum.UpdateUser.getCode());
        operateRecord.setUserId(id);
        try {
            User user = this.userMapperReader.selectByPrimaryKey(id);
            if (user != null) {
                this.copy(updateUserDTO, user);
                user.setUpdateTime(new Date());
                this.userMapperWrite.updateByPrimaryKeySelective(user);
                try {
                    operateRecord.setStatus(OperateRecordStatusEnum.Success.getCode().toString());
                    this.userLogOpenFeign.record(id, operateRecord);
                } catch (Exception e2) {
                    log.error("记录用户操作记录失败", e2);
                }
            }
            data.put("UPDATE", "OK");
            return CommonResult.success(data, "修改或者更新用户资料成功");
        } catch (Exception e) {
            log.error("修改或者更新用户资料出现错误", e);
            try {
                this.userLogOpenFeign.record(id, operateRecord);
            } catch (Exception e2) {
                log.error("记录用户操作记录失败", e2);
            }
            return CommonResult.failed("修改或者更新用户资料失败");
        } finally {
            if (log.isDebugEnabled()) {
                log.debug("结束修改或者更新用户资料");
            }
        }
    }

    //查看用户个人资料接口
    @GetMapping(value = "/{id}/query.do")
    public CommonResult<Map<String, Object>> query(@PathVariable(name = "id") Long id) {
        if (log.isDebugEnabled()) {
            log.debug("开始查看用户个人资料");
        }
        if (log.isDebugEnabled()) {
            log.debug("前端传输过来的用户信息id={}", id);
        }
        Map<String, Object> data = new ConcurrentHashMap<>();
        try {
            User user = this.userMapperReader.selectByPrimaryKey(id);
            if (user != null) {
                UserVO userVO = UserVO.builder().build();
                BeanUtils.copyProperties(user, userVO);
                userVO.setGrade(user.getGrade());
                if (StrUtil.isNotEmpty(user.getHeadIcon())) {
                    userVO.setHeadIcon(StrUtil.trimToNull(this.projectUrlProps.getResDomain()) +
                            StrUtil.trimToNull(this.projectUrlProps.getProjectName()) +
                            StrUtil.trimToNull(this.projectUrlProps.getResHeadIcon()) +
                            user.getId() +
                            "/" +
                            user.getHeadIcon());
                }
                if (StrUtil.isNotEmpty(user.getBackgroundIcon())) {
                    userVO.setBgIcon(StrUtil.trimToNull(this.projectUrlProps.getResDomain()) +
                            StrUtil.trimToNull(this.projectUrlProps.getProjectName()) +
                            StrUtil.trimToNull(this.projectUrlProps.getResBackgroundIcon()) +
                            user.getId() +
                            "/" +
                            user.getBackgroundIcon());
                }
                userVO.setGender(user.getGender());
                userVO.setYear(user.getYear());
                userVO.setMonth(user.getMonth());
                userVO.setDate(user.getDate());
                String birthDate = user.getYear() + "-" + user.getMonth() + "-" + user.getDate();
                Date birthDay = DateUtil.fomatDate(birthDate);
                userVO.setAge(AgeUtil.getAge(birthDay));
                this.setUserVO(userVO, user);
                data.put("user", userVO);
                return CommonResult.success(data, "查看用户个人资料成功");
            } else {
                return CommonResult.validateFailed("用户信息不存在，查询用户个人资料失败。");
            }
        } catch (Exception e) {
            log.error("查看用户个人资料出现错误", e);
            return CommonResult.failed("查看用户个人资料失败");
        } finally {
            if (log.isDebugEnabled()) {
                log.debug("结束查看用户个人资料");
            }
        }
    }

    //查看用户微信接口
    @GetMapping(value = "/{id}/queryWeixin.do")
    public CommonResult<Map<String, Object>> queryWeixin(@PathVariable(name = "id", required = true) Long id) {
        if (log.isDebugEnabled()) {
            log.debug("开始查看用户微信号");
        }
        if (log.isDebugEnabled()) {
            log.debug("前端传输过来的用户信息id={}", id);
        }
        Map<String, Object> data = new HashMap<>();
        try {
            User user2 = this.userDaoUseJdbcTemplate.getweixinIdById(id);
            if (user2 != null) {
                data.put("user", user2);
            }
            return CommonResult.success(data, "查看用户微信号资料成功");
        } catch (Exception e) {
            log.error("查看用户微信号资料出现错误", e);
            return CommonResult.failed("查看用户微信号资料失败");
        } finally {
            if (log.isDebugEnabled()) {
                log.debug("结束查看用户微信号资料");
            }
        }
    }

    //获取用户举报类型列表接口
    @GetMapping(value = "/{id}/reportcategories.do")
    public CommonResult<Map<String, List<ReportCategoryVO>>> reportCategoryList(@PathVariable(name = "id", required = true) Long userId) {
        try {
            if (log.isDebugEnabled()) {
                log.debug("开始获取用户举报类型列表");
            }
            Map<String, List<ReportCategoryVO>> list = new HashMap<>();
            ReportCategoryExample example = new ReportCategoryExample();
            example.setDistinct(true);
            example.setOrderByClause("id ASC, create_time DESC, update_time DESC");
            example.createCriteria().andStatusEqualTo(StatusEnum.Enabled.getCode().toString());
            List<ReportCategory> reportCategories = this.reportCategoryMapper.selectByExample(example);
            List<ReportCategoryVO> reportCategoriesVO = new ArrayList<>();
            if (reportCategories != null && !reportCategories.isEmpty()) {
                for (ReportCategory reportCategory : reportCategories) {
                    ReportCategoryVO reportCategoryVO = ReportCategoryVO.builder().build();
                    reportCategoryVO.setId(reportCategory.getId());
                    reportCategoryVO.setName(reportCategory.getContent());
                    reportCategoriesVO.add(reportCategoryVO);
                }
            }
            list.put("list", reportCategoriesVO);
            return CommonResult.success(list, "获取用户举报类型列表成功");
        } catch (Exception e) {
            log.error("获取用户举报类型列表出错", e);
            return CommonResult.failed("获取用户举报类型列表失败");
        } finally {
            if (log.isDebugEnabled()) {
                log.debug("结束获取用户举报类型列表");
            }
        }
    }

    //记录用户举报内容接口
    @PostMapping(value = "/{id}/report.do", consumes = {"application/json;charset=utf-8"}, produces = {"application/json;charset=utf-8"})
    public CommonResult<Map<String, Object>> report(@PathVariable(name = "id") Long userId, @RequestBody @Valid ReportInfoDTO reportInfoDTO, BindingResult bindingResult) {
        try {
            if (log.isDebugEnabled()) {
                log.debug("开始记录用户举报内容。");
            }
            OperateRecord operateRecord = new OperateRecord();
            operateRecord.setStatus(OperateRecordStatusEnum.Fail.getCode().toString());
            operateRecord.setUserId(userId);
            if (bindingResult.hasErrors()) {
                try {
                    this.userLogOpenFeign.record(userId, operateRecord);
                } catch (Exception e) {
                    log.error("记录用户操作记录失败", e);
                }
                return CommonResult.failed(HttpStatus.NOT_ACCEPTABLE.value(), ErrorMessageUtil.messageBuild(bindingResult.getAllErrors()));
            }
            Map<String, Object> data = new HashMap<>();
            Long categoryId = reportInfoDTO.getCategoryId();                //举报类目id
            ReportCategory reportCategory = this.reportCategoryDaoUseJdbcTemplate.getById(categoryId);
            if (reportCategory == null) {
                try {
                    this.userLogOpenFeign.record(userId, operateRecord);
                } catch (Exception e) {
                    log.error("记录用户操作记录失败", e);
                }
                return CommonResult.failed("举报类目信息不存在，记录用户举报内容失败。");
            }
            String reportType = reportInfoDTO.getReportType();              //举报类型，1->动态，2->用户，默认：1-动态
            Long reportUserId = reportInfoDTO.getBeingReportId();       //被举报用户id或者动态id
            String reportContent = reportInfoDTO.getReportContent();        //举报填写的内容
            User reportUser = this.userDaoUseJdbcTemplate.getById(userId);
            if (reportUser == null) {
                try {
                    this.userLogOpenFeign.record(userId, operateRecord);
                } catch (Exception e) {
                    log.error("记录用户操作记录失败", e);
                }
                return CommonResult.failed("举报用户不存在，记录用户举报内容失败。");
            }

            ReportInfo reportInfo = new ReportInfo();
            if (ReportTypeEnum.User.getCode().toString().equals(reportType)) {
                //自己不能举报自己
                if (userId.equals(reportUserId)) {
                    try {
                        this.userLogOpenFeign.record(userId, operateRecord);
                    } catch (Exception e) {
                        log.error("记录用户操作记录失败", e);
                    }
                    return CommonResult.failed("你不能举报自己，记录用户举报内容失败。");
                }
                reportInfo.setReportType(ReportTypeEnum.User.getCode().toString());
                operateRecord.setType(OperateRecordTypeEnum.ReportUser.getCode());
                //根据举报的用户id，判断用户信息是否存在
                User beingReportUser = this.userDaoUseJdbcTemplate.getById(reportUserId);
                if (beingReportUser == null) {
                    try {
                        this.userLogOpenFeign.record(userId, operateRecord);
                    } catch (Exception e) {
                        log.error("记录用户操作记录失败", e);
                    }
                    return CommonResult.failed("被举报用户不存在，记录用户举报内容失败。");
                }
            } else {
                DynamicInfoExample dynamicInfoExample = new DynamicInfoExample();
                dynamicInfoExample.setOrderByClause("id DESC, create_time DESC, update_time DESC");
                dynamicInfoExample.createCriteria().andUserIdEqualTo(userId);
                List<DynamicInfo> dynamicInfoList = this.dynamicInfoMapperRead.selectByExample(dynamicInfoExample);
                if (dynamicInfoList != null && !dynamicInfoList.isEmpty() && dynamicInfoList.size() > 0) {
                    List<Long> dynamicInfoIdList = dynamicInfoList.stream().map(DynamicInfo::getId).collect(Collectors.toList());
                    if (dynamicInfoIdList.contains(reportUserId)) {
                        try {
                            this.userLogOpenFeign.record(userId, operateRecord);
                        } catch (Exception e) {
                            log.error("记录用户操作记录失败", e);
                        }
                        return CommonResult.failed("你不能举报自己发布的动态内容，记录用户举报内容失败。");
                    }
                }
                //根据被举报的动态内容id，判断举报内容是否存在
                DynamicInfo dynamicInfo = this.dynamicInfoDaoUseJdbcTemplate.getById(reportUserId);
                if (dynamicInfo == null) {
                    try {
                        this.userLogOpenFeign.record(userId, operateRecord);
                    } catch (Exception e) {
                        log.error("记录用户操作记录失败", e);
                    }
                    return CommonResult.failed("被举报动态内容不存在，记录用户举报内容失败。");
                }
                //根据被举报的动态信息id，判断举报信息是否存在
                Long dynamicId = dynamicInfo.getDynamicId();
                Dynamic dynamic = this.dynamicDaoUseJdbcTemplate.getById(dynamicId);
                if (dynamic == null) {
                    try {
                        this.userLogOpenFeign.record(userId, operateRecord);
                    } catch (Exception e) {
                        log.error("记录用户操作记录失败", e);
                    }
                    return CommonResult.failed("被举报动态信息不存在，记录用户举报内容失败。");
                }
                reportInfo.setReportType(ReportTypeEnum.Dynamic.getCode().toString());
                operateRecord.setType(OperateRecordTypeEnum.ReportDynamic.getCode());
            }
            reportInfo.setReportUserId(userId);
            reportInfo.setBeingReportUserId(reportUserId);
            reportInfo.setReportContent(reportContent);
            reportInfo.setReportType(reportType);
            reportInfo.setCategoryType(categoryId);
            int result01 = this.reportInfoMapper.insertSelective(reportInfo);
            if (result01 > 0) {
                operateRecord.setStatus(OperateRecordStatusEnum.Success.getCode().toString());
            }
            try {
                this.userLogOpenFeign.record(userId, operateRecord);
            } catch (Exception e2) {
                log.error("记录用户操作记录失败", e2);
            }
            data.put("REPORTED", "OK");
            return CommonResult.success(data, "记录用户举报内容成功。");
        } catch (Exception e) {
            log.error("记录用户举报内容出错。", e);
            return CommonResult.failed("记录用户举报内容失败。");
        } finally {
            if (log.isDebugEnabled()) {
                log.debug("结束记录用户举报内容。");
            }
        }
    }

    //获取用户黑名单列表接口
    @GetMapping(value = "/{id}/blacklist.do")
    public CommonResult<Map<String, Object>> blackList(
            @PathVariable(name = "id") Long userId,
            @RequestParam(name = "pageNum", required = false, defaultValue = "1") int pageNum,
            @RequestParam(name = "pageSize", required = false, defaultValue = "10") int pageSize) {
        try {
            if (log.isDebugEnabled()) {
                log.debug("开始根据用户id={}，查询用户黑名单列表。", userId);
            }
            User user = this.userDaoUseJdbcTemplate.getUserInfoById(userId);
            if (user == null) {
                return CommonResult.failed("用户信息不存在，获取用户黑名单列表失败。");
            }
            Map<String, Object> data = new HashMap<>();
            BlacklistRecordExample example = new BlacklistRecordExample();
            example.setDistinct(true);
            example.setOrderByClause("id DESC, update_time DESC, create_time DESC");
            example.createCriteria().andOwnerUserIdEqualTo(userId);
            final PageInfo<BlacklistRecord> listPageInfo = PageHelper.startPage(pageNum, pageSize)
                    .setOrderBy("create_time DESC, update_time DESC")
                    .doSelectPageInfo(() -> this.blacklistRecordMapperRead
                            .selectByExample(example).stream()
                            .filter((BlacklistRecord b) -> (b.getStatus() % 2) != 0).collect(Collectors.toList()));
            //log.info("listPageInfo={}", listPageInfo);
            int totalPage = 0;
//            List<BlacklistRecord> blacklistRecordList = this.blacklistRecordMapperRead.selectByExample(example).stream().filter((BlacklistRecord b) -> (b.getStatus() % 2) != 0).collect(Collectors.toList());
            List<BlacklistRecord> blacklistRecordList = new ArrayList<>();
            if (listPageInfo != null && listPageInfo.getList() != null && !listPageInfo.getList().isEmpty()) {
                blacklistRecordList = listPageInfo.getList();
                totalPage = listPageInfo.getPages();
            }
            List<BlackUserVO> userVOList = new ArrayList<>();
            for (BlacklistRecord blacklistRecord : blacklistRecordList) {
                BlackUserVO userVO = BlackUserVO.builder().build();
                User e = this.userDaoUseJdbcTemplate.getById(blacklistRecord.getBlackUserId());
                if (e != null) {
                    userVO.setId(e.getId());
                    userVO.setNickName(e.getNickName());
                    userVO.setHeadIcon(StrUtil.trimToNull(this.projectUrlProps.getResDomain()) +
                            StrUtil.trimToNull(this.projectUrlProps.getProjectName()) +
                            StrUtil.trimToNull(this.projectUrlProps.getResHeadIcon()) +
                            e.getId() + "/" +
                            e.getHeadIcon());
                    userVO.setCreateTime(DateUtil.fomateDate(blacklistRecord.getCreateTime(), DateUtil.sdfTimeCNFmt));
                }
                userVOList.add(userVO);
            }
            data.put("totalPage", totalPage);
            data.put("list", userVOList);
            return CommonResult.success(data, "获取用户黑名单列表成功。");
        } catch (Exception e) {
            log.error("根据用户id={}，查询用户黑名单列表出错。", userId, e);
            return CommonResult.failed("获取用户黑名单列表失败。");
        } finally {
            if (log.isDebugEnabled()) {
                log.debug("结束根据用户id={}，查询用户黑名单列表。", userId);
            }
        }
    }

    //拉入拉出用户黑名单列表接口
    @PostMapping(value = "/{id}/pushblacklist.do", consumes = {"application/json;charset=utf-8"}, produces = {"application/json;charset=utf-8"})
    public CommonResult<Map<String, String>> pushBlackList(@PathVariable(name = "id") Long userId,
                                                           @RequestBody(required = true) @Valid BlacklistDTO blacklistDTO, BindingResult bindingResult) {
        OperateRecord operateRecord = new OperateRecord();
        operateRecord.setStatus(OperateRecordStatusEnum.Fail.getCode().toString());
        Integer type = blacklistDTO.getType();
        Map<String, String> data = new HashMap<>();
        String status = "PUSH";
        String message = "拉入";
        try {
            if (type % 2 != 0) {
                message = "拉入";
                status = "PUSH";
            }
            if (type % 2 == 0) {
                message = "推出";
                status = "PULL";
            }
            if (bindingResult.hasErrors()) {
                return CommonResult.failed(HttpStatus.UNAUTHORIZED.value(), ErrorMessageUtil.messageBuild(bindingResult.getAllErrors()));
            }
            if (log.isDebugEnabled()) {
                log.debug("开始将用户{}黑名单列表。", message);
            }
            operateRecord.setUserId(userId);
            Long blackUserId = blacklistDTO.getBlackUserId();
            if (blackUserId.equals(userId)) {
                data.put(status, "ERROR");
                return CommonResult.failed(data, ResultCode.NOT_PULL_OR_PUSH_OWNER_BLACKLIST_ERROR);
            }
            User ownerUser = this.userDaoUseJdbcTemplate.getById(userId);
            if (ownerUser == null) {
                data.put(status, "ERROR");
                return CommonResult.failed(data, ResultCode.USER_IS_NOT_EXIST);
            }
            User blackUser = this.userDaoUseJdbcTemplate.getById(blackUserId);
            if (blackUser == null) {
                data.put(status, "ERROR");
                return CommonResult.failed(data, ResultCode.USER_IS_NOT_EXIST);
            }
            String blackUserNickName = blackUser.getNickName();
            BlacklistRecordExample example = new BlacklistRecordExample();
            example.setDistinct(true);
            example.setOrderByClause("id DESC, update_time DESC, create_time DESC");
            example.createCriteria()
                    .andOwnerUserIdEqualTo(userId)
                    .andBlackUserIdEqualTo(blackUserId);
            List<BlacklistRecord> blacklistRecords = this.blacklistRecordMapperRead.selectByExample(example);
            BlacklistRecord blacklistRecord = new BlacklistRecord();
            blacklistRecord.setOwnerUserId(userId);
            blacklistRecord.setBlackUserId(blackUserId);
            if (type % 2 != 0) {
                operateRecord.setType(OperateRecordTypeEnum.PushBlackList.getCode());
                try {
                    this.userLogOpenFeign.record(userId, operateRecord);
                } catch (Exception e2) {
                    log.error("记录用户操作记录失败", e2);
                }
                if (blacklistRecords != null && !blacklistRecords.isEmpty()) {
                    data.put(status, "ERROR");
                    return CommonResult.failed(data, ResultCode.USER_EXIST_BLACKLIST_ERROR);
                } else {
                    blacklistRecord.setStatus(OperateRecordTypeEnum.PushBlackList.getCode());
                    int result = this.blacklistRecordMapperWrite.insertSelective(blacklistRecord);
                    if (result > 0) {
                        operateRecord.setStatus(OperateRecordStatusEnum.Success.getCode().toString());
                    }
                    data.put(status, "OK");
                    return CommonResult.success(data, "将用户" + blackUserNickName + message + "黑名单列表成功。");
                }
            } else {
                operateRecord.setType(OperateRecordTypeEnum.PullBlackList.getCode());
                try {
                    this.userLogOpenFeign.record(userId, operateRecord);
                } catch (Exception e2) {
                    log.error("记录用户操作记录失败", e2);
                }
                if (blacklistRecords != null && !blacklistRecords.isEmpty()) {
                    BlacklistRecord blacklistRecord2 = blacklistRecords.get(0);
                    BlacklistRecordExample example2 = new BlacklistRecordExample();
                    example2.setDistinct(true);
                    example2.setOrderByClause("id DESC, update_time DESC, create_time DESC");
                    example2.createCriteria()
                            .andIdEqualTo(blacklistRecord2.getId())
                            .andOwnerUserIdEqualTo(blacklistRecord2.getOwnerUserId())
                            .andBlackUserIdEqualTo(blacklistRecord2.getBlackUserId());
                    int result = this.blacklistRecordMapperWrite.deleteByExample(example2);
                    if (result > 0) {
                        operateRecord.setStatus(OperateRecordStatusEnum.Success.getCode().toString());
                    }
                    data.put(status, "OK");
                    return CommonResult.success(data, "将用户" + blackUserNickName + message + "黑名单列表成功。");
                } else {
                    data.put(status, "ERROR");
                    return CommonResult.failed(data, ResultCode.USER_NOT_EXIST_BLACKLIST_ERROR);
                }
            }
        } catch (Exception e) {
            log.error("将用户拉入拉出黑名单列表出错。", e);
            data.put(status, "ERROR");
            return CommonResult.failed(data, ResultCode.FAILED);
        } finally {
            if (log.isDebugEnabled()) {
                if (log.isDebugEnabled()) {
                    log.debug("结束将用户{}黑名单列表。", message);
                }
            }
        }
    }

    //鹿可模块推荐用户列表接口
    @GetMapping("/{id}/look.do")
    public CommonResult<List<UserVO3>> look(
            @PathVariable(name = "id", required = true) Long id,
            @Valid UserDTO3 userDTO, BindingResult bindingResult,
            @RequestParam(name = "count", required = false, defaultValue = "10") Integer count) {
        try {
            if (log.isDebugEnabled()) {
                log.debug("开始获取鹿可模块用户数据列表");
            }
            log.info("前端提交过来的请求参数：id={}, userDTO={}, count={}", id, userDTO, count);
            if (bindingResult != null && bindingResult.hasErrors()) {
                return CommonResult.validateFailed(Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage());
            }
            User user = this.userMapperReader.selectByPrimaryKey(id);
            if (user == null) {
                return CommonResult.failed("用户信息不存在");
            }
            String screenGender = user.getGender();//鹿可性别筛选条件一
            ScreenSettingExample example = new ScreenSettingExample();
            example.setDistinct(true);
            ScreenSetting screenSetting = null;
            Integer age = 0;
            if (UserGenderEnum.Male.getGender().equals(screenGender)) {//男生
                example.createCriteria().andDeleteStatusEqualTo(DeleteStatusEnum.NO.getStatus()).andTypeEqualTo(ScreenSettingTypeEnum.LOOK_AGE_MALE.getType());
                age = 3;//默认+3
            }
            if (UserGenderEnum.Female.getGender().equals(screenGender)) {//女生
                example.createCriteria().andDeleteStatusEqualTo(DeleteStatusEnum.NO.getStatus()).andTypeEqualTo(ScreenSettingTypeEnum.LOOK_AGE_FEMALE.getType());
                age = 10;//默认+10
            }
            List<ScreenSetting> screenSettings = this.screenSettingMapperReader.selectByExample(example);
            if (screenSettings != null && !screenSettings.isEmpty()) {
                screenSetting = screenSettings.get(0);
            }
            if (screenSetting != null) {
                age = screenSetting.getValue();
            }
            String birthDate = user.getYear() + "-" + user.getMonth() + "-" + user.getDate();
            Date birthDay = DateUtil.fomatDate(birthDate);
            ScreenSettingExample example11 = new ScreenSettingExample();
            example11.createCriteria().andDeleteStatusEqualTo(DeleteStatusEnum.NO.getStatus()).andTypeEqualTo(ScreenSettingTypeEnum.DYNAMIC_AGE_MIN.getType());
            List<ScreenSetting> screenSettings11 = this.screenSettingMapperReader.selectByExample(example11);
            ScreenSetting screenSetting11 = null;
            if (screenSettings11 != null && !screenSettings11.isEmpty()) {
                screenSetting11 = screenSettings11.get(0);
            }
            Integer screenAgeMin = 16;//鹿可年龄最小值筛选条件
            if (screenSetting11 != null) {
                screenAgeMin = screenSetting11.getValue();
            }
            int screenAgeMax = age + AgeUtil.getAge(birthDay);//鹿可年龄最大值筛选条件
            Date min = DateUtil.fomatDate(DateUtil.getBeforeYearByAge(screenAgeMin));
            Date max = DateUtil.fomatDate(DateUtil.getBeforeYearByAge(screenAgeMax));
            LookInfoParam lookInfoParam = new LookInfoParam();
            lookInfoParam.setGender(screenGender);
            lookInfoParam.setMinAge(min);
            lookInfoParam.setMaxAge(max);
            log.info("screenGender={}, screenAgeMin={}, screenAgeMax={}", lookInfoParam.getGender(), DateUtil.strFormat(lookInfoParam.getMinAge(), DateUtil.sdfTimeFmt), DateUtil.strFormat(lookInfoParam.getMaxAge(), DateUtil.sdfTimeFmt));
            List<User> userList = this.userMapperReader.selectLookUserList(lookInfoParam);
            List<User> userList1 = new LinkedList<>();
            List<UserVO3> userVO3List = new LinkedList<>();
            if (userList != null && !userList.isEmpty() && userList.size() - 1 > count) {
                int[] list = MathUtil.getRandoms(0, userList.size() - 1, count);
                assert list != null;
                for (int i : list) {
                    User user1 = userList.get(i);
                    userList1.add(user1);
                }
                for (User userTmp : userList1) {
                    UserVO3 userVO3 = UserVO3.builder().build();
                    BeanUtils.copyProperties(userTmp, userVO3);
                    String birthDateTmp = userTmp.getYear() + "-" + userTmp.getMonth() + "-" + userTmp.getDate();
                    Date birthDayTmp = DateUtil.fomatDate(birthDateTmp);
                    userVO3.setAge(AgeUtil.getAge(birthDayTmp));
                    String[] filenameTemps = StrUtil.split(this.dynamicInfoMapperRead.getFileNameByUserId(userTmp.getId()), "||");
                    String filenameTemp;
                    if (filenameTemps != null && filenameTemps.length > 0) {
                        filenameTemp = filenameTemps[0];
                    } else {
                        filenameTemp = this.dynamicInfoMapperRead.getFileNameByUserId(userTmp.getId());
                    }
                    String filename = StrUtil.trimToNull(this.projectUrlProps.getResDomain()) + StrUtil.trimToNull(this.projectUrlProps.getProjectName()) + StrUtil.trimToNull(this.projectUrlProps.getResDynamicImageFile()) + filenameTemp;
                    userVO3.setImg(filename);
                    userVO3List.add(userVO3);
                }
            }
            return CommonResult.success(userVO3List);
        } catch (Exception e) {
            log.error("获取鹿可模块用户数据列表出现错误", e);
        } finally {
            if (log.isDebugEnabled()) {
                log.debug("结束获取鹿可模块用户数据列表");
            }
        }
        return null;
    }


    //复制需要更新的用户信息
    private void copy(UpdateUserDTO userDTO, User user) {
        if (userDTO != null && user != null) {
            if (StrUtil.isNotEmpty(userDTO.getYear()) && !userDTO.getYear().equals(user.getYear())) {//出生年代
                user.setYear(userDTO.getYear());
            }
            if (StrUtil.isNotEmpty(userDTO.getMonth()) && !userDTO.getMonth().equals(user.getMonth())) {//出生月份
                user.setMonth(userDTO.getMonth());
            }
            if (StrUtil.isNotEmpty(userDTO.getDate()) && !userDTO.getDate().equals(user.getDate())) {//出生日期
                user.setDate(userDTO.getDate());
            }
            if (StrUtil.isNotEmpty(userDTO.getConstellation()) && !userDTO.getConstellation().equals(user.getConstellation())) {//星座
                user.setConstellation(userDTO.getConstellation());
            }
            if (StrUtil.isNotEmpty(userDTO.getNickname()) && !userDTO.getNickname().equals(user.getNickName())) {//昵称
                user.setNickName(userDTO.getNickname());
            }
            if (StrUtil.isNotEmpty(userDTO.getWeixinId()) && !userDTO.getWeixinId().equals(user.getWeixinId())) {//微信号
                user.setWeixinId(userDTO.getWeixinId());
            }
            if (StrUtil.isNotEmpty(userDTO.getAutograph()) && !userDTO.getAutograph().equals(user.getAutograph())) {//签名
                user.setAutograph(userDTO.getAutograph());
            }
            if (userDTO.getProfessionId() != null && !userDTO.getProfessionId().equals(user.getProfessionId())) {//职业Id
                user.setProfessionId(userDTO.getProfessionId());
            }
            this.setTags(user, userDTO);
        }
    }

    /**
     * <pre>
     * 根据标签的名称获取标签Id
     * @param name 标签名称
     * @return 标签信息Id
     * </pre>
     */
    private Long getTagIdByName(String name) {
        Long tagIdLong;
        Tag tag = this.tagService.findTagByName(name);
        if (tag != null) {
            tagIdLong = tag.getId();
        } else {
            tag = new Tag();
            tag.setName(name);
            tag.setReserveColumn01(TagTypeEnum.USER.getType());
            tagIdLong = this.tagService.saveTag(tag);
        }
        return tagIdLong;
    }

    /**
     * <pre>
     * 根据标签Id获取标签名称
     * @param id 标签id
     * @return 标签名称
     * </pre>
     */
    private String getTagNameById(Long id) {
        return this.tagService.findTagById(id);
    }

    private void setTags(User user, UpdateUserDTO userDTO) {
        if (StrUtil.isNotEmpty(userDTO.getTag1())) {
            Long tagIdLong = this.getTagIdByName(userDTO.getTag1());
            if (!tagIdLong.equals(user.getTag1())) {
                user.setTag1(tagIdLong);
            }
        }
        if (StrUtil.isNotEmpty(userDTO.getTag2())) {
            Long tagIdLong = this.getTagIdByName(userDTO.getTag2());
            if (!tagIdLong.equals(user.getTag2())) {
                user.setTag2(tagIdLong);
            }
        }
        if (StrUtil.isNotEmpty(userDTO.getTag3())) {
            Long tagIdLong = this.getTagIdByName(userDTO.getTag3());
            if (!tagIdLong.equals(user.getTag3())) {
                user.setTag3(tagIdLong);
            }
        }
        if (StrUtil.isNotEmpty(userDTO.getTag4())) {
            Long tagIdLong = this.getTagIdByName(userDTO.getTag4());
            if (!tagIdLong.equals(user.getTag4())) {
                user.setTag4(tagIdLong);
            }
        }
        if (StrUtil.isNotEmpty(userDTO.getTag5())) {
            Long tagIdLong = this.getTagIdByName(userDTO.getTag5());
            if (!tagIdLong.equals(user.getTag5())) {
                user.setTag5(tagIdLong);
            }
        }
    }

    private void setUserVO(UserVO userVO, User user) {
        if (userVO != null && user != null) {
            Professions professions = this.professionsMapperReader.selectByPrimaryKey(user.getProfessionId());
            if (professions != null) {
                userVO.setProfession(professions.getName());
                Industrys industrys = this.industrysMapperReader.selectByPrimaryKey(professions.getIndustryId());
                if (industrys != null) {
                    userVO.setIndustry(industrys.getName());
                }
            }
            userVO.setTag1(this.getTagNameById(user.getTag1()));
            userVO.setTag2(this.getTagNameById(user.getTag2()));
            userVO.setTag3(this.getTagNameById(user.getTag3()));
            userVO.setTag4(this.getTagNameById(user.getTag4()));
            userVO.setTag5(this.getTagNameById(user.getTag5()));
        }
    }
}
