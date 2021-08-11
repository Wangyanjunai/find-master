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
import com.potato369.find.user.service.SensitiveWordsService;
import com.potato369.find.user.service.TagService;
import com.potato369.find.user.service.UserService;
import com.potato369.find.user.utils.RandomNickNameUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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

    private UserMapper userMapperWriter;

    private UserMapper userMapperReader;

    private UserDaoUseJdbcTemplate userDaoUseJdbcTemplate;

    private DynamicDaoUseJdbcTemplate dynamicDaoUseJdbcTemplate;

    private DynamicInfoDaoUseJdbcTemplate dynamicInfoDaoUseJdbcTemplate;

    private ReportCategoryDaoUseJdbcTemplate reportCategoryDaoUseJdbcTemplate;

    private ReportCategoryMapper reportCategoryMapper;

    private ReportInfoMapper reportInfoMapper;

    private DynamicInfoMapper dynamicInfoMapperReader;

    private BlacklistRecordMapper blacklistRecordMapperReader;

    private BlacklistRecordMapper blacklistRecordMapperWriter;

    private AliyunProps aliyunProps;

    private UserService userService;

    private ProjectUrlProps projectUrlProps;

    private TagService tagService;

    private ProfessionsMapper professionsMapperReader;

    private IndustrysMapper industrysMapperReader;

    private ScreenSettingMapper screenSettingMapperReader;

    private AttacheInfoMapper attacheInfoMapperReader;

    private TagMapper tagMapperWriter;

    private TagMapper tagMapperReader;

    private SensitiveWordsService sensitiveWordsService;

    private OperateRecordMapper operateRecordMapperWriter;

    @Autowired
    public void setUserMapperWriter(UserMapper userMapperWriter) {
        this.userMapperWriter = userMapperWriter;
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
    public void setReportCategoryMapper(ReportCategoryMapper reportCategoryMapper) {
        this.reportCategoryMapper = reportCategoryMapper;
    }

    @Autowired
    public void setReportInfoMapper(ReportInfoMapper reportInfoMapper) {
        this.reportInfoMapper = reportInfoMapper;
    }

    @Autowired
    public void setDynamicInfoMapperReader(DynamicInfoMapper dynamicInfoMapperReader) {
        this.dynamicInfoMapperReader = dynamicInfoMapperReader;
    }

    @Autowired
    public void setBlacklistRecordMapperReader(BlacklistRecordMapper blacklistRecordMapperReader) {
        this.blacklistRecordMapperReader = blacklistRecordMapperReader;
    }

    @Autowired
    public void setBlacklistRecordMapperWriter(BlacklistRecordMapper blacklistRecordMapperWriter) {
        this.blacklistRecordMapperWriter = blacklistRecordMapperWriter;
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

    @Autowired
    public void setAttacheInfoMapperReader(AttacheInfoMapper attacheInfoMapperReader) {
        this.attacheInfoMapperReader = attacheInfoMapperReader;
    }

    @Autowired
    public void setTagMapperWriter(TagMapper tagMapperWriter) {
        this.tagMapperWriter = tagMapperWriter;
    }

    @Autowired
    public void setTagMapperReader(TagMapper tagMapperReader) {
        this.tagMapperReader = tagMapperReader;
    }

    @Autowired
    public void setSensitiveWordsService(SensitiveWordsService sensitiveWordsService) {
        this.sensitiveWordsService = sensitiveWordsService;
    }

    @Autowired
    public void setOperateRecordMapperWriter(OperateRecordMapper operateRecordMapperWriter) {
        this.operateRecordMapperWriter = operateRecordMapperWriter;
    }

    //上报或者更新极光推送唯一设备的标识接口
    @ApiOperation(value = "上报或者更新极光推送唯一设备的标识接口", notes = "上报或者更新极光推送唯一设备的标识接口", response = CommonResult.class)
    @PutMapping(value = "/{id}/uploadRegId.do")
    public CommonResult<Map<String, Object>> uploadRegId(@PathVariable(name = "id") Long id,//id：用户id
                                                         @RequestParam(name = "regId") String regId) {//regId：极光推送唯一设备的标识
        Map<String, Object> result = new ConcurrentHashMap<>();
        String data = "UPLOADREGID";
        String message = "FAILED";
        OperateRecord operateRecordResult = new OperateRecord();
        operateRecordResult.setUserId(id);
        operateRecordResult.setStatus(OperateRecordStatusEnum.Fail.getStatus());
        operateRecordResult.setType(OperateRecordTypeEnum.UploadUserRegId.getCode());
        try {
            if (log.isDebugEnabled()) {
                log.debug("开始上报或者更新极光推送唯一设备的标识");
            }
            User user = this.userDaoUseJdbcTemplate.getById(id);
            if (Objects.isNull(user)) {
                return CommonResult.validateFailed("参数校验不通过，用户信息不存在。");
            }
            String regIdOld = user.getReserveColumn03();
            if (StrUtil.isNotEmpty(regId) && !regId.equals(regIdOld)) {
                user.setReserveColumn03(regId);
                user.setUpdateTime(new Date());
                int row = this.userMapperWriter.updateByPrimaryKeySelective(user);
                if (row > 0) {
                    operateRecordResult.setStatus(OperateRecordStatusEnum.Success.getStatus());
                    message = "OK";
                }
            }
            result.put(data, message);
            this.operateRecordMapperWriter.insertSelective(operateRecordResult);
            return CommonResult.success(result);
        } catch (Exception e) {
            log.error("上报或者更新极光推送唯一设备的标识出现错误", e);
            this.operateRecordMapperWriter.insertSelective(operateRecordResult);
            return CommonResult.failed("上报或者更新极光推送唯一设备的标识失败。");
        } finally {
            if (log.isDebugEnabled()) {
                log.debug("结束上报或者更新极光推送唯一设备的标识");
            }
        }
    }

    //上传或者修改头像小图接口
    @ApiOperation(value = "上传或者修改头像小图接口", notes = "上传或者修改头像小图接口", response = CommonResult.class)
    @PutMapping(value = "/{id}/head.do", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public CommonResult<Map<String, Object>> handleHeadIconUpload(@PathVariable(name = "id") Long id,
                                                                  @RequestPart(value = "headIconFile") MultipartFile headIconFile01) {
        if (log.isDebugEnabled()) {
            log.debug("开始上传头像小图片");
            log.debug("前端传输过来的用户id={}", id);
        }
        Map<String, Object> data = new ConcurrentHashMap<>();
        data.put("id", id);
        User user1 = this.userMapperReader.selectByPrimaryKey(id);
        if (Objects.isNull(user1)) {
            return CommonResult.validateFailed("用户信息不存在");
        }
        OperateRecord operateRecordResult = new OperateRecord();
        operateRecordResult.setUserId(id);
        operateRecordResult.setStatus(OperateRecordStatusEnum.Fail.getStatus());
        operateRecordResult.setType(OperateRecordTypeEnum.UpdateHeadIcon.getCode());
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
            if (!Objects.isNull(headIconFile01) && !headIconFile01.isEmpty()) {
                if (!FileTypeUtil.isImageType(headIconFile01.getContentType(), Objects.requireNonNull(headIconFile01.getOriginalFilename()))) {
                    return CommonResult.validateFailed("上传头像文件类型不符合要求。");
                }
                String oldFileName = headIconFile01.getOriginalFilename();
                String newFileName = null;
                if (!Objects.isNull(oldFileName)) {
                    newFileName = UUID.randomUUID() + oldFileName.substring(oldFileName.lastIndexOf("."));
                }
                assert newFileName != null;
                File headIconFile = new File(headIconFilePath, newFileName);
                headIconFileName = headIconFile.getName();
                try {
                    if (!Objects.equals(headIconFileName, user1.getHeadIcon())) {
                        if (!Objects.isNull(user1.getHeadIcon())) {
                            File oldHeadIconFile = new File(headIconFilePath, user1.getHeadIcon());
                            //删除之前服务器的头像
                            if (oldHeadIconFile.exists()) {
                                oldHeadIconFile.delete();
                            }
                        }
                        if (!headIconFile.getParentFile().exists()) {
                            headIconFile.getParentFile().mkdirs();
                        }
                        //重新上传新的头像到服务器，并更新用户数据库头像信息
                        headIconFile01.transferTo(headIconFile);
                        user1.setHeadIcon(headIconFileName);
                        user1.setUpdateTime(new Date());
                        this.userMapperWriter.updateByPrimaryKeySelective(user1);
                        operateRecordResult.setStatus(OperateRecordStatusEnum.Success.getStatus());
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
            this.operateRecordMapperWriter.insertSelective(operateRecordResult);
            return CommonResult.success(data);
        } catch (Exception e) {
            log.error("上传头像小图到Nginx服务器失败", e);
            this.operateRecordMapperWriter.insertSelective(operateRecordResult);
            return CommonResult.failed("上传头像小图到Nginx服务器失败");
        } finally {
            if (log.isDebugEnabled()) {
                log.debug("结束上传头像小图片");
            }
        }
    }

    //上传或者修改背景图片接口
    @ApiOperation(value = "上传或者修改背景图片接口", notes = "上传或者修改背景图片接口", response = CommonResult.class)
    @PutMapping(value = "/{id}/background.do", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public CommonResult<Map<String, Object>> handleBackgroundIconUpload(
            @PathVariable(name = "id") Long id,
            @RequestPart(name = "backgroundIconFile") MultipartFile backgroundIconFile02) {
        if (log.isDebugEnabled()) {
            log.debug("开始上传背景图片");
            log.debug("前端传输过来的用户id={}", id);
        }
        Map<String, Object> data = new ConcurrentHashMap<>();
        data.put("id", id);
        User user1 = this.userMapperReader.selectByPrimaryKey(id);
        OperateRecord operateRecord = new OperateRecord();
        operateRecord.setUserId(id);
        operateRecord.setStatus(OperateRecordStatusEnum.Fail.getStatus());
        operateRecord.setType(OperateRecordTypeEnum.UpdateBackgroundIcon.getCode());
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
            if (!Objects.isNull(backgroundIconFile02) && !backgroundIconFile02.isEmpty()) {
                if (!FileTypeUtil.isImageType(backgroundIconFile02.getContentType(), Objects.requireNonNull(backgroundIconFile02.getOriginalFilename()))) {
                    return CommonResult.validateFailed("上传背景图片文件类型不符合要求。");
                }
                String oldFileName = backgroundIconFile02.getOriginalFilename();
                String newFileName = null;
                if (!Objects.isNull(oldFileName)) {
                    newFileName = UUID.randomUUID() + oldFileName.substring(oldFileName.lastIndexOf("."));
                }
                assert newFileName != null;
                File bgIconFile = new File(bgIconFilePath, newFileName);
                bgIconFileName = bgIconFile.getName();
                try {
                    if (!Objects.equals(bgIconFileName, user1.getBackgroundIcon())) {
                        if (!Objects.isNull(user1.getBackgroundIcon())) {
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
                        this.userMapperWriter.updateByPrimaryKeySelective(user1);
                        operateRecord.setStatus(OperateRecordStatusEnum.Success.getStatus());
                    }
                    bgIconFileUrlBf.append(bgIconFileName);
                } catch (Exception e) {
                    log.error("上传用户头像小图到Nginx服务器出现错误", e);
                    bgIconFileUrlBf.append(user1.getBackgroundIcon());
                }
                if (log.isDebugEnabled()) {
                    log.debug("上传用户头像小图到Nginx服务器成功，file={}", bgIconFile.getAbsolutePath() + bgIconFile.getName());
                }
            }
            data.put("bg", bgIconFileUrlBf);
            this.operateRecordMapperWriter.insertSelective(operateRecord);
            return CommonResult.success(data);
        } catch (Exception e) {
            log.error("上传背景图片到Nginx服务器失败", e);
            this.operateRecordMapperWriter.insertSelective(operateRecord);
            return CommonResult.failed("上传背景图片到Nginx服务器失败");
        } finally {
            if (log.isDebugEnabled()) {
                log.debug("结束上传背景图片");
            }
        }
    }

    //注册接口
    @ApiOperation(value = "注册接口", notes = "注册接口", response = CommonResult.class)
    @PostMapping(value = "/reg.do", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public CommonResult<Map<String, UserVO2>> register(
            UserDTO userDTO,
            @RequestPart(value = "head") MultipartFile head) { // head：头像图片文件
        Map<String, UserVO2> map = new ConcurrentHashMap<>();
        UserVO2 userVO2 = UserVO2.builder().build();
        String message = "注册失败。";
        Long userId = 0L;
        OperateRecord operateRecord = new OperateRecord();
        operateRecord.setStatus(OperateRecordStatusEnum.Fail.getStatus());
        operateRecord.setType(OperateRecordTypeEnum.CreateUser.getCode());
        operateRecord.setUserId(userId);
        try {
            if (log.isDebugEnabled()) {
                log.debug("开始注册");
                log.debug("前端传输过来的用户信息userDTO={}", userDTO);
            }
            //校验手机号码是否为空
            String phone = userDTO.getPhone();
            if (Objects.isNull(phone)) {
                return CommonResult.validateFailed("参数校验不通过，手机号码不能为空。");
            }
            //校验手机号码格式是否正确
            if (!RegexUtil.isMathPhone(phone)) {
                return CommonResult.validateFailed("参数校验不通过，手机号码格式不正确。");
            }
            Double longitude = userDTO.getLongitude();
            String longitudeStr = "";
            if (!Objects.isNull(longitude)) {
                longitudeStr = String.valueOf(longitude);
            }
            Double latitude = userDTO.getLatitude();
            String latitudeStr = "";
            if (!Objects.isNull(latitude)) {
                latitudeStr = String.valueOf(latitude);
            }
            //校验客户端IP，定位（国家、省份、城市、区/县、其它、经纬度）是否同时为空。
            if (StrUtil.isAllEmpty(userDTO.getIp(), userDTO.getCountry(), userDTO.getProvince(), userDTO.getCity(), userDTO.getDistrict(), userDTO.getOther(), longitudeStr, latitudeStr)) {
                return CommonResult.validateFailed("参数校验不通过，客户端IP，定位（国家、省份、城市、区/县、其它、经纬度）不能同时为空。");
            }
            if (StrUtil.isNotEmpty(userDTO.getIp()) && !RegexUtil.isMathIp(userDTO.getIp())) {
                return CommonResult.validateFailed("参数校验不通过，客户端IP格式不正确。");
            }
            //校验星座是否符合要求
            String constellation = userDTO.getConstellation();
            if (!Objects.isNull(constellation)) {
                ConstellationConstant ConstellationConstant = new ConstellationConstant();
                if (!ConstellationConstant.getConstellationList().contains(constellation)) {
                    return CommonResult.validateFailed("参数校验不通过，星座不符合要求。");
                }
            }
            //校验头像图片文件是否为空
            if (Objects.isNull(head) || head.isEmpty()) {
                return CommonResult.validateFailed("参数校验不通过，头像图片文件不能为空。");
            }
            //校验头像图片文件格式是否符合要求
            if (!FileTypeUtil.isImageType(head.getContentType(), Objects.requireNonNull(head.getOriginalFilename()))) {
                return CommonResult.validateFailed("参数校验不通过，头像图片文件类型不符合要求。");
            }
            String nickname = userDTO.getNickname();
            if (StrUtil.isEmpty(nickname)) {
                nickname = new RandomNickNameUtil().randomName();
            }
            if (!Objects.isNull(nickname)) {
                //校验昵称是否包含敏感词汇
                SensitiveWords sensitiveWords = this.sensitiveWordsService.checkHasSensitiveWords(nickname);
                if (!Objects.isNull(sensitiveWords)) {
                    return CommonResult.validateFailed("参数校验不通过，昵称包含" + sensitiveWords.getTypeName() + "类型敏感词汇，禁止添加。");
                }
            }
            String autograph = userDTO.getAutograph();
            //校验签名是否包含敏感词汇
            if (!Objects.isNull(autograph)) {
                SensitiveWords sensitiveWords = this.sensitiveWordsService.checkHasSensitiveWords(autograph);
                if (!Objects.isNull(sensitiveWords)) {
                    return CommonResult.validateFailed("参数校验不通过，签名包含" + sensitiveWords.getTypeName() + "类型敏感词汇，禁止发布。");
                }
            }
            User user = new User();
            BeanUtils.copyProperties(userDTO, user);
            UpdateUserDTO updateUserDTO = UpdateUserDTO.builder().build();
            BeanUtils.copyProperties(userDTO, updateUserDTO);
            this.copy(updateUserDTO, user);
            user.setNickName(nickname);
            if (StrUtil.isAllEmpty(userDTO.getCountry(), userDTO.getProvince(), userDTO.getCity(), userDTO.getDistrict(), userDTO.getOther(), longitudeStr, latitudeStr)) {
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
            if (StrUtil.isEmpty(autograph)) {
                if (Objects.equals(UserGenderEnum.Female.getGender(), user.getGender())) {
                    autograph = StrUtil.trimToNull(this.projectUrlProps.getDefaultFemaleContent());
                }
                if (Objects.equals(UserGenderEnum.Male.getGender(), user.getGender())) {
                    autograph = StrUtil.trimToNull(this.projectUrlProps.getDefaultMaleContent());
                }
            }
            user.setAutograph(autograph);
            int result02 = this.userService.register(user, autograph, userDTO, head);
            if (Objects.equals(result02, 5)) {
                message = "注册成功。";
                operateRecord.setStatus(OperateRecordStatusEnum.Success.getStatus());
                operateRecord.setUserId(user.getId());
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
                map.put("user", userVO2);
                this.operateRecordMapperWriter.insertSelective(operateRecord);
                return CommonResult.success(map, message);
            } else {
                this.operateRecordMapperWriter.insertSelective(operateRecord);
                return CommonResult.failed(message);
            }
        } catch (Exception e) {
            log.error("注册出现错误", e);
            this.operateRecordMapperWriter.insertSelective(operateRecord);
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
        }
        Map<String, UserVO2> map = new ConcurrentHashMap<>();
        UserVO2 userVO2 = UserVO2.builder().build();
        String message = "登录失败。";
        Long userId = 0L;
        OperateRecord operateRecord = new OperateRecord();
        operateRecord.setStatus(OperateRecordStatusEnum.Fail.getCode().toString());
        operateRecord.setType(OperateRecordTypeEnum.LoginUser.getCode());
        operateRecord.setUserId(userId);
        try {
            if (bindingResult.hasErrors()) {
                return CommonResult.validateFailed(Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage());
            }
            if (!RegexUtil.isMathPhone(userDTO.getPhone())) {
                return CommonResult.validateFailed("参数校验不通过，手机号码格式不正确。");
            }
            Double longitude = userDTO.getLongitude();//经度
            String longitudeString = "";
            if (!Objects.isNull(longitude)) {
                longitudeString = String.valueOf(longitude);
            }
            Double latitude = userDTO.getLatitude();//纬度
            String latitudeString = "";
            if (!Objects.isNull(latitude)) {
                latitudeString = String.valueOf(latitude);
            }
            if (StrUtil.isAllEmpty(userDTO.getIp(), userDTO.getCountry(), userDTO.getProvince(), userDTO.getCity(), userDTO.getDistrict(), userDTO.getOther(), longitudeString, latitudeString)) {
                return CommonResult.validateFailed("参数校验失败，客户端IP，定位（国家、省份、城市、区/县、其它、经度、纬度）不能同时为空。");
            }
            if (StrUtil.isNotEmpty(userDTO.getIp()) && !RegexUtil.isMathIp(userDTO.getIp())) {
                return CommonResult.validateFailed("客户端IP参数校验失败，客户端IP格式不正确。");
            }
            User user = this.userDaoUseJdbcTemplate.getByPhone(userDTO.getPhone());
            if (Objects.isNull(user)) {
                return CommonResult.failed("该手机号码未注册用户，请注册后再试。");
            }
            if (StrUtil.isAllEmpty(userDTO.getCountry(), userDTO.getProvince(), userDTO.getCity(), userDTO.getDistrict(), userDTO.getOther(), longitudeString, latitudeString)) {
                if (StrUtil.isNotEmpty(userDTO.getIp())) {
                    //更新用户定位或者ip
                    LocationDTO locationDTO = IPLocationUtil.getLocationByAliyunIP(StrUtil.trimToEmpty(this.aliyunProps.getAppcode()), StrUtil.trimToEmpty(this.aliyunProps.getUrl()), userDTO.getIp());
                    if (!Objects.equals(user.getIp(), locationDTO.getIp())) {
                        user.setIp(locationDTO.getIp());
                    }
                    if (!Objects.equals(user.getCountry(), locationDTO.getCountry())) {
                        user.setCountry(locationDTO.getCountry());
                    }
                    if (!Objects.equals(user.getProvince(), locationDTO.getProvince())) {
                        user.setProvince(locationDTO.getProvince());
                    }
                    if (!Objects.equals(user.getCity(), locationDTO.getCity())) {
                        user.setCity(locationDTO.getCity());
                    }
                    if (!Objects.equals(user.getDistrict(), locationDTO.getDistrict())) {
                        user.setDistrict(locationDTO.getDistrict());
                    }
                    if (!Objects.equals(user.getOther(), locationDTO.getOther())) {
                        user.setOther(locationDTO.getOther());
                    }
                    if (!Objects.equals(user.getLongitude(), locationDTO.getLongitude())) {
                        user.setLongitude(locationDTO.getLongitude());
                    }
                    if (!Objects.equals(user.getLatitude(), locationDTO.getLatitude())) {
                        user.setLatitude(locationDTO.getLatitude());
                    }
                }
            } else {
                if (!Objects.equals(user.getIp(), userDTO.getIp())) {
                    user.setIp(userDTO.getIp());
                }
                if (!Objects.equals(user.getCountry(), userDTO.getCountry())) {
                    user.setCountry(userDTO.getCountry());
                }
                if (!Objects.equals(user.getProvince(), userDTO.getProvince())) {
                    user.setProvince(userDTO.getProvince());
                }
                if (!Objects.equals(user.getCity(), userDTO.getCity())) {
                    user.setCity(userDTO.getCity());
                }
                if (!Objects.equals(user.getDistrict(), userDTO.getDistrict())) {
                    user.setDistrict(userDTO.getDistrict());
                }
                if (!Objects.equals(user.getOther(), userDTO.getOther())) {
                    user.setOther(userDTO.getOther());
                }
                if (!Objects.equals(user.getLongitude(), userDTO.getLongitude())) {
                    user.setLongitude(userDTO.getLongitude());
                }
                if (!Objects.equals(user.getLatitude(), userDTO.getLatitude())) {
                    user.setLatitude(userDTO.getLatitude());
                }
            }
            user.setUpdateTime(new Date());
            Dynamic dynamic = this.dynamicDaoUseJdbcTemplate.getByUserId(user.getId());
            if (Objects.isNull(dynamic)) {
                return CommonResult.failed("该注册用户动态不存在，请注册后再试。");
            }
            if (!Objects.equals(dynamic.getCountry(), user.getCountry())) {
                dynamic.setCountry(user.getCountry());
            }
            if (!Objects.equals(dynamic.getProvince(), user.getProvince())) {
                dynamic.setProvince(user.getProvince());
            }
            if (!Objects.equals(dynamic.getCity(), user.getCity())) {
                dynamic.setCity(user.getCity());
            }
            if (!Objects.equals(dynamic.getDistrict(), user.getDistrict())) {
                dynamic.setDistrict(user.getDistrict());
            }
            if (!Objects.equals(dynamic.getOther(), user.getOther())) {
                dynamic.setOther(user.getOther());
            }
            if (!Objects.equals(dynamic.getLongitude(), user.getLongitude())) {
                dynamic.setLongitude(user.getLongitude());
            }
            if (!Objects.equals(dynamic.getLatitude(), user.getLatitude())) {
                dynamic.setLatitude(user.getLatitude());
            }
            dynamic.setUpdateTime(new Date());
            int a = this.userService.update(user, dynamic);
            if (a > 0) {
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
                map.put("user", userVO2);
                userId = user.getId();
                message = "登录成功。";
                operateRecord.setUserId(userId);
                operateRecord.setStatus(OperateRecordStatusEnum.Success.getStatus());
            }
            this.operateRecordMapperWriter.insertSelective(operateRecord);
            return CommonResult.success(map, message);
        } catch (Exception e) {
            log.error("登录出现错误", e);
            this.operateRecordMapperWriter.insertSelective(operateRecord);
            return CommonResult.failed(message);
        } finally {
            if (log.isDebugEnabled()) {
                log.debug("结束登录");
            }
        }
    }

    //判断用户是否已经注册接口
    @GetMapping(value = "/is-reg.do")
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
            Map<String, Boolean> map = new ConcurrentHashMap<>();
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
    @PutMapping(value = "/{id}/update.do", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public CommonResult<Map<String, Object>> update(@PathVariable(name = "id") Long id,
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
            //校验昵称是否包含敏感词汇
            SensitiveWords sensitiveWords01 = this.sensitiveWordsService.checkHasSensitiveWords(updateUserDTO.getNickname());
            if (!Objects.isNull(sensitiveWords01)) {
                return CommonResult.validateFailed("用户昵称包含" + sensitiveWords01.getTypeName() + "类型敏感词汇，禁止添加。");
            }
            //校验签名内容是否包含敏感词汇
            SensitiveWords sensitiveWords02 = this.sensitiveWordsService.checkHasSensitiveWords(updateUserDTO.getAutograph());
            if (!Objects.isNull(sensitiveWords02)) {
                return CommonResult.validateFailed("签名包含" + sensitiveWords02.getTypeName() + "类型敏感词汇，禁止发布。");
            }
            //校验星座值是否符合要求
            if (StrUtil.isNotEmpty(updateUserDTO.getConstellation())) {
                ConstellationConstant ConstellationConstant = new ConstellationConstant();
                if (!ConstellationConstant.getConstellationList().contains(updateUserDTO.getConstellation())) {
                    return CommonResult.validateFailed("参数校验不通过，星座值非法。");
                }
            }
            if (!Objects.isNull(user)) {
                this.copy(updateUserDTO, user);
                user.setUpdateTime(new Date());
                int a = this.userMapperWriter.updateByPrimaryKeySelective(user);
                if (a > 0) {
                    data.put("UPDATE", "OK");
                    operateRecord.setStatus(OperateRecordStatusEnum.Success.getStatus());
                }
            }
            this.operateRecordMapperWriter.insertSelective(operateRecord);
            return CommonResult.success(data, "修改或者更新用户资料成功");
        } catch (Exception e) {
            log.error("修改或者更新用户资料出现错误", e);
            this.operateRecordMapperWriter.insertSelective(operateRecord);
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
        OperateRecord operateRecord = new OperateRecord();
        operateRecord.setStatus(OperateRecordStatusEnum.Fail.getStatus());
        operateRecord.setType(OperateRecordTypeEnum.QueryUser.getCode());
        operateRecord.setUserId(id);
        Map<String, Object> data = new ConcurrentHashMap<>();
        try {
            User user = this.userMapperReader.selectByPrimaryKey(id);
            if (Objects.isNull(user)) {
                return CommonResult.validateFailed("参数校验不通过，用户信息不存在。");
            }
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
            operateRecord.setStatus(OperateRecordStatusEnum.Success.getStatus());
            this.operateRecordMapperWriter.insertSelective(operateRecord);
            return CommonResult.success(data, "查看用户个人资料成功");
        } catch (Exception e) {
            log.error("查看用户个人资料出现错误", e);
            this.operateRecordMapperWriter.insertSelective(operateRecord);
            return CommonResult.failed("查看用户个人资料失败");
        } finally {
            if (log.isDebugEnabled()) {
                log.debug("结束查看用户个人资料");
            }
        }
    }

    //查看用户微信接口
    @GetMapping(value = "/{id}/query-weChat.do")
    public CommonResult<Map<String, Object>> queryWeixin(@PathVariable(name = "id") Long id) {
        if (log.isDebugEnabled()) {
            log.debug("开始查看用户微信号");
        }
        if (log.isDebugEnabled()) {
            log.debug("前端传输过来的用户信息id={}", id);
        }
        OperateRecord operateRecord = new OperateRecord();
        operateRecord.setStatus(OperateRecordStatusEnum.Fail.getStatus());
        operateRecord.setType(OperateRecordTypeEnum.QueryUserWeChat.getCode());
        operateRecord.setUserId(id);
        Map<String, Object> data = new ConcurrentHashMap<>();
        try {
            User user = this.userDaoUseJdbcTemplate.getweixinIdById(id);
            if (!Objects.isNull(user)) {
                data.put("user", user);
            }
            operateRecord.setStatus(OperateRecordStatusEnum.Success.getStatus());
            this.operateRecordMapperWriter.insertSelective(operateRecord);
            return CommonResult.success(data, "查看用户微信号资料成功");
        } catch (Exception e) {
            log.error("查看用户微信号资料出现错误", e);
            this.operateRecordMapperWriter.insertSelective(operateRecord);
            return CommonResult.failed("查看用户微信号资料失败");
        } finally {
            if (log.isDebugEnabled()) {
                log.debug("结束查看用户微信号资料");
            }
        }
    }

    //获取用户举报类型列表接口
    @GetMapping(value = "/{id}/report-categories.do")
    public CommonResult<Map<String, List<ReportCategoryVO>>> reportCategoryList(@PathVariable(name = "id") Long userId) {
        try {
            if (log.isDebugEnabled()) {
                log.debug("开始获取用户举报类型列表");
            }
            User user = this.userMapperReader.selectByPrimaryKey(userId);
            if (Objects.isNull(user)) {
                return CommonResult.validateFailed("参数校验不通过，用户信息不存在。");
            }
            Map<String, List<ReportCategoryVO>> list = new ConcurrentHashMap<>();
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
    @PostMapping(value = "/{id}/report.do", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public CommonResult<Map<String, Object>> report(@PathVariable(name = "id") Long userId, @RequestBody @Valid ReportInfoDTO reportInfoDTO, BindingResult bindingResult) {
        OperateRecord operateRecord = new OperateRecord();
        operateRecord.setStatus(OperateRecordStatusEnum.Fail.getStatus());
        operateRecord.setType(OperateRecordTypeEnum.ReportDynamic.getCode());
        operateRecord.setUserId(userId);
        Map<String, Object> data = new ConcurrentHashMap<>();
        try {
            if (log.isDebugEnabled()) {
                log.debug("开始记录用户举报内容。");
            }

            if (bindingResult.hasErrors()) {
                return CommonResult.failed(HttpStatus.NOT_ACCEPTABLE.value(), ErrorMessageUtil.messageBuild(bindingResult.getAllErrors()));
            }
            Long categoryId = reportInfoDTO.getCategoryId();                //举报类目id
            ReportCategory reportCategory = this.reportCategoryDaoUseJdbcTemplate.getById(categoryId);
            if (Objects.isNull(reportCategory)) {
                return CommonResult.failed("参数校验不通过，举报类目信息不存在。");
            }
            String reportType = reportInfoDTO.getReportType();          //举报类型，1->动态，2->用户，默认：1-动态
            Long reportUserId = reportInfoDTO.getBeingReportId();       //被举报用户id或者动态id
            String reportContent = reportInfoDTO.getReportContent();    //举报填写的内容
            if (StrUtil.isNotEmpty(reportContent)) {
                //校验举报填写的内容是否包含敏感词汇
                SensitiveWords sensitiveWords = this.sensitiveWordsService.checkHasSensitiveWords(reportContent);
                if (!Objects.isNull(sensitiveWords)) {
                    return CommonResult.validateFailed("举报填写的内容包含" + sensitiveWords.getTypeName() + "类型敏感词汇，禁止发布。");
                }
            }
            User reportUser = this.userDaoUseJdbcTemplate.getById(userId);
            if (Objects.isNull(reportUser)) {
                return CommonResult.validateFailed("参数校验不通过，举报用户不存在。");
            }
            ReportInfo reportInfo = new ReportInfo();
            if (ReportTypeEnum.User.getCode().toString().equals(reportType)) {
                reportInfo.setReportType(ReportTypeEnum.User.getCode().toString());
                operateRecord.setType(OperateRecordTypeEnum.ReportUser.getCode());
                //根据举报的用户id，判断用户信息是否存在
                User beingReportUser = this.userDaoUseJdbcTemplate.getById(reportUserId);
                if (Objects.isNull(beingReportUser)) {
                    return CommonResult.validateFailed("参数校验不通过，被举报用户不存在。");
                }
            } else {
                DynamicInfoExample dynamicInfoExample = new DynamicInfoExample();
                dynamicInfoExample.setOrderByClause("id DESC, create_time DESC, update_time DESC");
                dynamicInfoExample.createCriteria().andUserIdEqualTo(userId);
                List<DynamicInfo> dynamicInfoList = this.dynamicInfoMapperReader.selectByExample(dynamicInfoExample);
                if (dynamicInfoList != null && !dynamicInfoList.isEmpty()) {
                    List<Long> dynamicInfoIdList = dynamicInfoList.stream().map(DynamicInfo::getId).collect(Collectors.toList());
                    if (dynamicInfoIdList.contains(reportUserId)) {
                        return CommonResult.validateFailed("参数校验不通过，自己不能举报自己发布的动态内容。");
                    }
                }
                //根据被举报的动态内容id，判断举报内容是否存在
                DynamicInfo dynamicInfo = this.dynamicInfoDaoUseJdbcTemplate.getById(reportUserId);
                if (Objects.isNull(dynamicInfo)) {
                    return CommonResult.validateFailed("参数校验不通过，被举报动态内容不存在。");
                }
            }
            reportInfo.setReportUserId(userId);
            reportInfo.setBeingReportUserId(reportUserId);
            reportInfo.setReportContent(reportContent);
            reportInfo.setReportType(reportType);
            reportInfo.setCategoryType(categoryId);
            int result01 = this.reportInfoMapper.insertSelective(reportInfo);
            if (result01 > 0) {
                operateRecord.setStatus(OperateRecordStatusEnum.Success.getCode().toString());
                data.put("REPORTED", "OK");
            }
            this.operateRecordMapperWriter.insertSelective(operateRecord);
            return CommonResult.success(data, "记录用户举报内容成功。");
        } catch (Exception e) {
            log.error("记录用户举报内容出错。", e);
            this.operateRecordMapperWriter.insertSelective(operateRecord);
            return CommonResult.failed("记录用户举报内容失败。");
        } finally {
            if (log.isDebugEnabled()) {
                log.debug("结束记录用户举报内容。");
            }
        }
    }

    //获取用户黑名单列表接口
    @GetMapping(value = "/{id}/black-list.do")
    public CommonResult<Map<String, Object>> blackList(
            @PathVariable(name = "id") Long userId,
            @RequestParam(name = "pageNum", required = false, defaultValue = "1") int pageNum,
            @RequestParam(name = "pageSize", required = false, defaultValue = "10") int pageSize) {
        try {
            if (log.isDebugEnabled()) {
                log.debug("开始根据用户id={}，查询用户黑名单列表。", userId);
            }
            User user = this.userDaoUseJdbcTemplate.getUserInfoById(userId);
            if (Objects.isNull(user)) {
                return CommonResult.validateFailed("参数校验不通过，用户信息不存在。");
            }
            Map<String, Object> data = new ConcurrentHashMap<>();
            BlacklistRecordExample example = new BlacklistRecordExample();
            example.setDistinct(true);
            example.setOrderByClause("id DESC, update_time DESC, create_time DESC");
            example.createCriteria().andOwnerUserIdEqualTo(userId).andStatusEqualTo(BlacklistRecordStatusEnum.PUSH.getCode());
            final PageInfo<BlacklistRecord> listPageInfo = PageHelper.startPage(pageNum, pageSize).setOrderBy("create_time DESC, update_time DESC")
                    .doSelectPageInfo(() -> this.blacklistRecordMapperReader.selectByExample(example).stream().collect(Collectors.toList()));
            int totalPage = 0;
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
                    userVO.setCreateTime(DateUtil.fomateDate(blacklistRecord.getUpdateTime(), DateUtil.sdfTimeCNFmt));
                }
                userVOList.add(userVO);
            }
            data.put("totalPage", totalPage);
            data.put("list", userVOList);
            return CommonResult.success(data, "获取用户黑名单列表成功。");
        } catch (Exception e) {
            log.error("根据用户查询用户黑名单列表出错", e);
            return CommonResult.failed("获取用户黑名单列表失败。");
        } finally {
            if (log.isDebugEnabled()) {
                log.debug("结束根据用户id={}，查询用户黑名单列表。", userId);
            }
        }
    }

    //拉入拉出用户黑名单列表接口
    @PostMapping(value = "/{id}/pushOrPull-blacklist.do", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public CommonResult<Map<String, String>> pushBlackList(@PathVariable(name = "id") Long userId,
                                                           @RequestBody @Valid BlacklistDTO blacklistDTO, BindingResult bindingResult) {
        OperateRecord operateRecord = new OperateRecord();
        operateRecord.setUserId(userId);
        operateRecord.setStatus(OperateRecordStatusEnum.Fail.getStatus());
        operateRecord.setType(OperateRecordTypeEnum.PullBlackList.getCode());
        Map<String, String> data = new ConcurrentHashMap<>();
        String status = "PUSH";
        String statusStr = "ERROR";
        try {
            if (log.isDebugEnabled()) {
                log.debug("开始将用户拉入推出黑名单列表");
            }
            if (bindingResult.hasErrors()) {
                return CommonResult.validateFailed(ErrorMessageUtil.messageBuild(bindingResult.getAllErrors()));
            }
            Long blackUserId = blacklistDTO.getBlackUserId();//黑名单列表用户id
            if (Objects.equals(blackUserId, userId)) {//不能把自己添加到自己的黑名单列表中
                return CommonResult.validateFailed(ResultCode.NOT_PULL_OR_PUSH_OWNER_BLACKLIST_ERROR.getMessage());
            }
            User blackUser = this.userDaoUseJdbcTemplate.getById(blackUserId);//黑名单列表被拥有者
            if (Objects.isNull(blackUser)) {
                return CommonResult.validateFailed(ResultCode.USER_IS_NOT_EXIST.getMessage());
            }
            User ownerUser = this.userDaoUseJdbcTemplate.getById(userId);//黑名单列表拥有者
            if (Objects.isNull(ownerUser)) {
                return CommonResult.validateFailed(ResultCode.USER_IS_NOT_EXIST.getMessage());
            }
            //查询黑名单列表记录是否存在
            BlacklistRecordExample example = new BlacklistRecordExample();
            example.createCriteria().andOwnerUserIdEqualTo(userId).andBlackUserIdEqualTo(blackUserId);
            List<BlacklistRecord> blacklistRecords = this.blacklistRecordMapperReader.selectByExample(example);
            Integer type = blacklistDTO.getType();
            String blackUserNickName = blackUser.getNickName();
            if (!Objects.isNull(blacklistRecords) && blacklistRecords.isEmpty()) {
                if (type % 2 != 0) {
                    operateRecord.setType(OperateRecordTypeEnum.PushBlackList.getCode());
                    BlacklistRecord blacklistRecord = new BlacklistRecord();
                    blacklistRecord.setOwnerUserId(userId);
                    blacklistRecord.setBlackUserId(blackUserId);
                    blacklistRecord.setStatus(BlacklistRecordStatusEnum.PUSH.getCode());
                    int result = this.blacklistRecordMapperWriter.insertSelective(blacklistRecord);
                    if (result > 0) {
                        operateRecord.setStatus(OperateRecordStatusEnum.Success.getStatus());
                    }
                    operateRecord.setType(OperateRecordTypeEnum.PushBlackList.getCode());
                    this.operateRecordMapperWriter.insertSelective(operateRecord);
                    statusStr = "OK";
                    data.put(status, statusStr);
                    return CommonResult.success(data, "将用户“" + blackUserNickName + "”拉入黑名单列表成功");
                }
                status = "PULL";
                operateRecord.setType(OperateRecordTypeEnum.PullBlackList.getCode());
                this.operateRecordMapperWriter.insertSelective(operateRecord);
                data.put(status, statusStr);
                return CommonResult.failed(data, ResultCode.USER_NOT_EXIST_BLACKLIST_ERROR);
            }
            if (!Objects.isNull(blacklistRecords) && !blacklistRecords.isEmpty()) {
                BlacklistRecord blacklistRecord = blacklistRecords.get(0);
                if (!Objects.isNull(blacklistRecord)) {
                    Integer typeTemp = blacklistRecord.getStatus();
                    if (Objects.equals(BlacklistRecordStatusEnum.PUSH.getCode(), typeTemp)) {
                        if (type % 2 != 0) {
                            operateRecord.setType(OperateRecordTypeEnum.PushBlackList.getCode());
                            this.operateRecordMapperWriter.insertSelective(operateRecord);
                            data.put(status, statusStr);
                            return CommonResult.failed(data, ResultCode.USER_EXIST_BLACKLIST_ERROR);
                        }
                        status = "PULL";
                        blacklistRecord.setStatus(BlacklistRecordStatusEnum.PULL.getCode());
                        blacklistRecord.setUpdateTime(new Date());
                        int result = this.blacklistRecordMapperWriter.updateByPrimaryKeySelective(blacklistRecord);
                        if (result > 0) {
                            operateRecord.setStatus(OperateRecordStatusEnum.Success.getStatus());
                            operateRecord.setType(OperateRecordTypeEnum.PullBlackList.getCode());
                            this.operateRecordMapperWriter.insertSelective(operateRecord);
                            statusStr = "OK";
                            data.put(status, statusStr);
                            return CommonResult.success(data, "将用户“" + blackUserNickName + "”推出黑名单列表成功");
                        }
                    }
                    if (Objects.equals(BlacklistRecordStatusEnum.PULL.getCode(), typeTemp)) {
                        if (type % 2 != 0) {
                            blacklistRecord.setStatus(BlacklistRecordStatusEnum.PUSH.getCode());
                            blacklistRecord.setUpdateTime(new Date());
                            int result = this.blacklistRecordMapperWriter.updateByPrimaryKeySelective(blacklistRecord);
                            if (result > 0) {
                                operateRecord.setStatus(OperateRecordStatusEnum.Success.getStatus());
                                operateRecord.setType(OperateRecordTypeEnum.PushBlackList.getCode());
                                this.operateRecordMapperWriter.insertSelective(operateRecord);
                                statusStr = "OK";
                                data.put(status, statusStr);
                                return CommonResult.success(data, "将用户“" + blackUserNickName + "”拉入黑名单列表成功");
                            }
                        }
                        status = "PULL";
                        operateRecord.setType(OperateRecordTypeEnum.PullBlackList.getCode());
                        this.operateRecordMapperWriter.insertSelective(operateRecord);
                        data.put(status, statusStr);
                        return CommonResult.failed(data, ResultCode.USER_NOT_EXIST_BLACKLIST_ERROR);
                    }
                }
            }
            data.put(status, statusStr);
            return CommonResult.success(data);
        } catch (Exception e) {
            log.error("将用户拉入推出黑名单列表出错", e);
            data.put(status, statusStr);
            this.operateRecordMapperWriter.insertSelective(operateRecord);
            return CommonResult.failed(data, ResultCode.FAILED);
        } finally {
            if (log.isDebugEnabled()) {
                if (log.isDebugEnabled()) {
                    log.debug("结束将用户拉入推出黑名单列表");
                }
            }
        }
    }

    //鹿可模块推荐用户数据接口
    @GetMapping("/{id}/look.do")
    public CommonResult<Map<String, List<UserVO3>>> look(
            @PathVariable(name = "id") Long id,
            @Valid UserDTO3 userDTO, BindingResult bindingResult,
            @RequestParam(name = "count", required = false, defaultValue = "10") Integer count) {
        Map<String, List<UserVO3>> data = new ConcurrentHashMap<>();
        try {
            if (log.isDebugEnabled()) {
                log.debug("开始获取鹿可模块用户数据");
            }
            if (bindingResult.hasErrors()) {
                return CommonResult.validateFailed("参数校验不通过，" + Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage());
            }
            User user = this.userMapperReader.selectByPrimaryKey(id);
            if (Objects.isNull(user)) {
                return CommonResult.validateFailed("参数校验不通过，用户信息不存在。");
            }
            String screenGender = user.getGender();//鹿可性别筛选条件一
            ScreenSettingExample example = new ScreenSettingExample();
            example.setDistinct(true);
            ScreenSetting screenSetting = null;
            Integer age = 0;
            if (UserGenderEnum.Male.getGender().equals(screenGender)) {//男生
                example.createCriteria().andDeleteStatusEqualTo(DeleteStatusEnum.NO.getStatus()).andTypeEqualTo(ScreenSettingTypeEnum.LOOK_AGE_MALE.getType());
            }
            if (UserGenderEnum.Female.getGender().equals(screenGender)) {//女生
                example.createCriteria().andDeleteStatusEqualTo(DeleteStatusEnum.NO.getStatus()).andTypeEqualTo(ScreenSettingTypeEnum.LOOK_AGE_FEMALE.getType());
            }
            List<ScreenSetting> screenSettings = this.screenSettingMapperReader.selectByExample(example);
            if (!Objects.isNull(screenSettings) && !screenSettings.isEmpty()) {
                screenSetting = screenSettings.get(0);
            }
            if (!Objects.isNull(screenSetting)) {
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
            lookInfoParam.setUserId(id);
            log.info("lookInfoParam={}", lookInfoParam);
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
                    List<AttacheInfo> attacheInfoList = this.dynamicInfoMapperReader.getFileNameByUserId(userTmp.getId());
                    AttacheInfo attacheInfo = null;
                    if (!Objects.isNull(attacheInfoList) && !attacheInfoList.isEmpty()) {
                        attacheInfo = attacheInfoList.get(0);
                    }
                    String[] filenameTemps = new String[0];
                    if (!Objects.isNull(attacheInfo)) {
                        filenameTemps = StrUtil.split(attacheInfo.getFileName(), "||");
                        userVO3.setDynamicInfoId(attacheInfo.getDynamicInfoBy());
                    }
                    String filenameTemp = null;
                    if (filenameTemps != null && filenameTemps.length > 0) {
                        filenameTemp = filenameTemps[0];
                    }
                    String filename = StrUtil.trimToNull(this.projectUrlProps.getResDomain()) + StrUtil.trimToNull(this.projectUrlProps.getProjectName()) + StrUtil.trimToNull(this.projectUrlProps.getResDynamicImageFile()) + filenameTemp;
                    userVO3.setImg(filename);
                    userVO3.setDistance(DistanceUtil.getDistance(userDTO.getLongitude(), userDTO.getLatitude(), userTmp.getLongitude(), userTmp.getLatitude()));
                    userVO3List.add(userVO3);
                }
            }
            data.put("list", userVO3List);
            return CommonResult.success(data);
        } catch (Exception e) {
            log.error("获取鹿可模块用户数据出现错误", e);
            return CommonResult.failed("获取鹿可模块用户数据失败");
        } finally {
            if (log.isDebugEnabled()) {
                log.debug("结束获取鹿可模块用户数据");
            }
        }
    }

    //鹿可模块推荐用户详情数据接口
    @GetMapping("/{id}/look-details.do")
    public CommonResult<UserVO4> lookDetails(
            @PathVariable(name = "id") Long id,
            @RequestParam(name = "detailsUserId") Long detailsUserId) {
        try {
            if (log.isDebugEnabled()) {
                log.debug("开始获取鹿可模块用户详情数据");
            }
            //log.info("前端提交过来的请求参数：id={}, detailsUserId={}", id, detailsUserId);
            User user = this.userMapperReader.selectByPrimaryKey(id);
            if (Objects.isNull(user)) {
                return CommonResult.validateFailed("参数校验不通过，当前用户信息不存在。");
            }
            User userDetails = this.userMapperReader.selectByPrimaryKey(detailsUserId);
            if (Objects.isNull(userDetails)) {
                return CommonResult.validateFailed("参数校验不通过，鹿可用户信息不存在。");
            }
            UserVO4 userVO4 = UserVO4.builder().build();
            BeanUtils.copyProperties(userDetails, userVO4);
            List<AttacheInfo> attacheInfoList = this.attacheInfoMapperReader.getAttacheInfoByUserId(userDetails.getId());
            List<String> attacheList = new ArrayList<>();
            List<String> attacheListTmp = new ArrayList<>();
            List<String> attacheListTmp1 = new ArrayList<>();
            for (AttacheInfo attacheInfo : attacheInfoList) {
                String filenameString = attacheInfo.getFileName();
                String[] filenameArr = StrUtil.split(filenameString, "||");
                if (filenameArr.length > 0) {
                    attacheListTmp.addAll(Arrays.asList(filenameArr));
                } else {
                    attacheListTmp.add(filenameString);
                }
            }
            if (!attacheListTmp.isEmpty()) {
                if (attacheListTmp.size() <= 6) {
                    attacheListTmp1 = attacheListTmp;
                } else {
                    attacheListTmp1 = attacheListTmp.subList(0, 6);
                }
            }
            for (String filename : attacheListTmp1) {
                attacheList.add(StrUtil.trimToNull(this.projectUrlProps.getResDomain()) + StrUtil.trimToNull(this.projectUrlProps.getProjectName()) + StrUtil.trimToNull(this.projectUrlProps.getResDynamicImageFile()) + filename);
            }
            userVO4.setAttacheList(attacheList);
            String birthDate = userDetails.getYear() + "-" + userDetails.getMonth() + "-" + userDetails.getDate();
            Date birthDay = DateUtil.fomatDate(birthDate);
            userVO4.setAge(AgeUtil.getAge(birthDay));
            setUserVO4(userVO4, userDetails);
            return CommonResult.success(userVO4, "获取鹿可模块用户详情数据成功。");
        } catch (Exception e) {
            log.error("获取鹿可模块用户详情数据出现错误", e);
            return CommonResult.failed("获取鹿可模块用户详情数据失败。");
        } finally {
            if (log.isDebugEnabled()) {
                log.debug("结束获取鹿可模块用户详情数据");
            }
        }
    }

    //意见反馈接口
    @PostMapping(value = "{id}/feedback.do", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public CommonResult<Map<String, Object>> feedback(@PathVariable(name = "id") Long userId,
                                                      @RequestParam(name = "dataType") String attacheInfoDataType,
                                                      @RequestParam(name = "content", required = false) String opinion,
                                                      @RequestPart(value = "files", required = false) MultipartFile[] files) {
        Map<String, Object> data = new ConcurrentHashMap<>();
        data.put("FEEDBACK", "ERROR");
        OperateRecord operateRecord = new OperateRecord();
        operateRecord.setUserId(userId);
        operateRecord.setStatus(OperateRecordStatusEnum.Fail.getStatus());
        operateRecord.setType(OperateRecordTypeEnum.Feedback.getCode());
        try {
            if (log.isDebugEnabled()) {
                log.debug("开始意见反馈");
            }
            User user = this.userMapperReader.selectByPrimaryKey(userId);
            if (Objects.isNull(user)) {
                return CommonResult.validateFailed("参数校验不通过，用户信息不存在。");
            }
            //校验附件文件类型是否正确
            if (!Objects.equals(attacheInfoDataType, AttacheInfoDataTypeEnum.Image.getCodeStr())
                    && !Objects.equals(attacheInfoDataType, AttacheInfoDataTypeEnum.Audio.getCodeStr())
                    && !Objects.equals(attacheInfoDataType, AttacheInfoDataTypeEnum.Text.getCodeStr())) {
                return CommonResult.validateFailed("参数校验不通过，不允许附件文件类型。");
            }
            FeedbackRecord feedbackRecord = new FeedbackRecord();
            feedbackRecord.setUserId(userId);
            feedbackRecord.setContent(opinion);
            feedbackRecord.setDataType(attacheInfoDataType);
            int result = this.userService.feedback(feedbackRecord, files);
            if (result > 0) {
                operateRecord.setStatus(OperateRecordStatusEnum.Success.getStatus());
                data.put("FEEDBACK", "OK");
            }
            this.operateRecordMapperWriter.insertSelective(operateRecord);
            return CommonResult.success(data);
        } catch (Exception e) {
            log.error("意见反馈出现错误", e);
            this.operateRecordMapperWriter.insertSelective(operateRecord);
            return CommonResult.failed("意见反馈失败。");
        } finally {
            if (log.isDebugEnabled()) {
                log.debug("结束意见反馈");
            }
        }
    }


    //复制需要更新的用户信息
    private void copy(UpdateUserDTO userDTO, User user) {
        if (!Objects.equals(userDTO.getYear(), user.getYear())) {
            user.setYear(userDTO.getYear());//出生年代
        }
        if (!Objects.equals(userDTO.getMonth(), user.getMonth())) {
            user.setMonth(userDTO.getMonth());//出生月份
        }
        if (!Objects.equals(userDTO.getDate(), user.getDate())) {
            user.setDate(userDTO.getDate());//出生日期
        }
        if (!Objects.equals(userDTO.getConstellation(), user.getConstellation())) {
            user.setConstellation(userDTO.getConstellation());//星座
        }
        if (!Objects.equals(userDTO.getNickname(), user.getNickName())) {
            user.setNickName(userDTO.getNickname());//昵称
        }
        if (!Objects.equals(userDTO.getWeixinId(), user.getWeixinId())) {
            user.setWeixinId(userDTO.getWeixinId());//昵称
        }
        if (!Objects.equals(userDTO.getAutograph(), user.getAutograph())) {
            user.setAutograph(userDTO.getAutograph());//签名
        }
        if (!Objects.equals(userDTO.getProfessionId(), user.getProfessionId())) {
            user.setProfessionId(userDTO.getProfessionId());//职业id
        }
        this.setTags(user, userDTO);//标签
    }

    /**
     * <pre>
     * 根据标签的名称获取标签Id
     * @param name 标签名称
     * @return 标签信息Id
     * </pre>
     */
    private Long getTagIdByName(String name) {
        Long tagIdLong = null;
        if (!Objects.isNull(name)) {
            Tag tag = this.tagService.findTagByName(name);
            if (!Objects.isNull(tag)) {
                tagIdLong = tag.getId();
            } else {
                tag = new Tag();
                tag.setName(name);
                tag.setReserveColumn01(TagTypeEnum.USER.getType());
                tagIdLong = this.tagService.saveTag(tag);
            }
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
            if (!Objects.equals(tagIdLong, user.getTag1())) {
                user.setTag1(tagIdLong);
                this.updateByTagId(tagIdLong);
            }
        }
        if (StrUtil.isNotEmpty(userDTO.getTag2())) {
            Long tagIdLong = this.getTagIdByName(userDTO.getTag2());
            if (!Objects.equals(tagIdLong, user.getTag2())) {
                user.setTag2(tagIdLong);
                this.updateByTagId(tagIdLong);
            }
        }
        if (StrUtil.isNotEmpty(userDTO.getTag3())) {
            Long tagIdLong = this.getTagIdByName(userDTO.getTag3());
            if (!Objects.equals(tagIdLong, user.getTag3())) {
                user.setTag3(tagIdLong);
                this.updateByTagId(tagIdLong);
            }
        }
        if (StrUtil.isNotEmpty(userDTO.getTag4())) {
            Long tagIdLong = this.getTagIdByName(userDTO.getTag4());
            if (!Objects.equals(tagIdLong, user.getTag4())) {
                user.setTag4(tagIdLong);
                this.updateByTagId(tagIdLong);
            }
        }
        if (StrUtil.isNotEmpty(userDTO.getTag5())) {
            Long tagIdLong = this.getTagIdByName(userDTO.getTag5());
            if (!Objects.equals(tagIdLong, user.getTag5())) {
                user.setTag5(tagIdLong);
                this.updateByTagId(tagIdLong);
            }
        }
    }

    private void setUserVO(UserVO userVO3, User user) {
        if (!Objects.isNull(userVO3) && !Objects.isNull(user)) {
            Professions professions = this.professionsMapperReader.selectByPrimaryKey(user.getProfessionId());
            if (!Objects.isNull(professions)) {
                userVO3.setProfession(professions.getName());
                Industrys industrys = this.industrysMapperReader.selectByPrimaryKey(professions.getIndustryId());
                if (!Objects.isNull(industrys)) {
                    userVO3.setIndustry(industrys.getName());
                }
            }
            userVO3.setTag1(this.getTagNameById(user.getTag1()));
            userVO3.setTag2(this.getTagNameById(user.getTag2()));
            userVO3.setTag3(this.getTagNameById(user.getTag3()));
            userVO3.setTag4(this.getTagNameById(user.getTag4()));
            userVO3.setTag5(this.getTagNameById(user.getTag5()));
        }
    }

    private void setUserVO4(UserVO4 userVO4, User user) {
        if (!Objects.isNull(userVO4) && !Objects.isNull(user)) {
            Professions professions = this.professionsMapperReader.selectByPrimaryKey(user.getProfessionId());
            if (!Objects.isNull(professions)) {
                userVO4.setProfession(professions.getName());
                Industrys industrys = this.industrysMapperReader.selectByPrimaryKey(professions.getIndustryId());
                if (!Objects.isNull(industrys)) {
                    userVO4.setIndustry(industrys.getName());
                }
            }
            userVO4.setTag1(this.getTagNameById(user.getTag1()));
            userVO4.setTag2(this.getTagNameById(user.getTag2()));
            userVO4.setTag3(this.getTagNameById(user.getTag3()));
            userVO4.setTag4(this.getTagNameById(user.getTag4()));
            userVO4.setTag5(this.getTagNameById(user.getTag5()));
        }
    }

    private void updateByTagId(Long tagId) {
        if (!Objects.isNull(tagId)) {
            Tag tag = this.tagMapperReader.selectByPrimaryKey(tagId);
            if (!Objects.isNull(tag)) {
                tag.setHotValue(tag.getHotValue() + 1);
                tag.setUpdatedTime(new Date());
                this.tagMapperWriter.updateByPrimaryKeySelective(tag);
            }
        }
    }
}
