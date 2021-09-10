package com.potato369.find.dynamic.controller;

import cn.hutool.core.util.StrUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.potato369.find.common.api.CommonResult;
import com.potato369.find.common.api.ResultCode;
import com.potato369.find.common.constants.ConstellationConstant;
import com.potato369.find.common.dto.DynamicDTO;
import com.potato369.find.common.dto.DynamicLocationDTO;
import com.potato369.find.common.dto.LocationDTO;
import com.potato369.find.common.enums.*;
import com.potato369.find.common.utils.CopyUtil;
import com.potato369.find.common.utils.DateUtil;
import com.potato369.find.common.utils.DistanceUtil;
import com.potato369.find.common.utils.FileTypeUtil;
import com.potato369.find.common.vo.DynamicInfoVO;
import com.potato369.find.common.vo.HotTopicInfoVO;
import com.potato369.find.common.vo.HotTopicVO;
import com.potato369.find.common.vo.TagVO;
import com.potato369.find.dynamic.config.bean.PushBean;
import com.potato369.find.dynamic.config.props.DynamicDefaultAgeProps;
import com.potato369.find.dynamic.config.props.ProjectUrlProps;
import com.potato369.find.dynamic.service.*;
import com.potato369.find.mbg.mapper.*;
import com.potato369.find.mbg.model.*;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Api(value = "动态模块用户管理控制器类")
@Slf4j
@RestController
@RequestMapping("/v1/dynamic")
@Scope("request")
public class DynamicController {

    private DynamicService dynamicService;

    private DynamicInfoService dynamicInfoService;

    private LikeRecordService likeRecordService;

    private ApplicationSettingService applicationSettingService;

    private ApplicationRecordService applicationRecordService;

    private DynamicDefaultAgeProps dynamicDefaultAgeProps;

    private UserMapper userMapperReader;

    private DynamicMapper dynamicMapperReader;

    private DynamicMapper dynamicMapperWriter;

    private BlacklistRecordMapper blacklistRecordMapperReader;

    private ShareRecordMapper shareRecordMapperWriter;

    private OperateRecordMapper operateRecordMapperWriter;

    private JiGuangPushService jiGuangPushService;

    private ApplicationRecordMapper applicationRecordMapperReader;

    private MessageMapper messageMapperReader;

    private SensitiveWordsService sensitiveWordsService;

    private ProfessionsMapper professionsMapperReader;

    private TagMapper tagMapperReader;

    private DynamicInfoMapper dynamicInfoMapperReader;

    private ProjectUrlProps projectUrlProps;

    private LikeRecordMapper likeRecordMapperReader;

    private AttacheInfoMapper attacheInfoMapperReader;

    private CommentRecordMapper commentRecordMapperReader;

    @Autowired
    public void setDynamicService(DynamicService dynamicService) {
        this.dynamicService = dynamicService;
    }

    @Autowired
    public void setDynamicInfoService(DynamicInfoService dynamicInfoService) {
        this.dynamicInfoService = dynamicInfoService;
    }

    @Autowired
    public void setLikeRecordService(LikeRecordService likeRecordService) {
        this.likeRecordService = likeRecordService;
    }

    @Autowired
    public void setApplicationSettingService(ApplicationSettingService applicationSettingService) {
        this.applicationSettingService = applicationSettingService;
    }

    @Autowired
    public void setApplicationRecordService(ApplicationRecordService applicationRecordService) {
        this.applicationRecordService = applicationRecordService;
    }

    @Autowired
    public void setDynamicDefaultAgeProps(DynamicDefaultAgeProps dynamicDefaultAgeProps) {
        this.dynamicDefaultAgeProps = dynamicDefaultAgeProps;
    }

    @Autowired
    public void setUserMapperReader(UserMapper userMapperReader) {
        this.userMapperReader = userMapperReader;
    }

    @Autowired
    public void setDynamicMapperReader(DynamicMapper dynamicMapperReader) {
        this.dynamicMapperReader = dynamicMapperReader;
    }

    @Autowired
    public void setDynamicMapperWriter(DynamicMapper dynamicMapperWriter) {
        this.dynamicMapperWriter = dynamicMapperWriter;
    }

    @Autowired
    public void setBlacklistRecordMapperReader(BlacklistRecordMapper blacklistRecordMapperReader) {
        this.blacklistRecordMapperReader = blacklistRecordMapperReader;
    }

    @Autowired
    public void setShareRecordMapperWriter(ShareRecordMapper shareRecordMapperWriter) {
        this.shareRecordMapperWriter = shareRecordMapperWriter;
    }

    @Autowired
    public void setOperateRecordMapperWriter(OperateRecordMapper operateRecordMapperWriter) {
        this.operateRecordMapperWriter = operateRecordMapperWriter;
    }

    @Autowired
    public void setJiGuangPushService(JiGuangPushService jiGuangPushService) {
        this.jiGuangPushService = jiGuangPushService;
    }

    @Autowired
    public void setApplicationRecordMapperReader(ApplicationRecordMapper applicationRecordMapperReader) {
        this.applicationRecordMapperReader = applicationRecordMapperReader;
    }

    @Autowired
    public void setMessageMapperReader(MessageMapper messageMapperReader) {
        this.messageMapperReader = messageMapperReader;
    }

    @Autowired
    public void setSensitiveWordsService(SensitiveWordsService sensitiveWordsService) {
        this.sensitiveWordsService = sensitiveWordsService;
    }

    @Autowired
    public void setProfessionsMapperReader(ProfessionsMapper professionsMapperReader) {
        this.professionsMapperReader = professionsMapperReader;
    }

    @Autowired
    public void setTagMapperReader(TagMapper tagMapperReader) {
        this.tagMapperReader = tagMapperReader;
    }

    @Autowired
    public void setDynamicInfoMapperReader(DynamicInfoMapper dynamicInfoMapperReader) {
        this.dynamicInfoMapperReader = dynamicInfoMapperReader;
    }

    @Autowired
    public void setProjectUrlProps(ProjectUrlProps projectUrlProps) {
        this.projectUrlProps = projectUrlProps;
    }

    @Autowired
    public void setLikeRecordMapperReader(LikeRecordMapper likeRecordMapperReader) {
        this.likeRecordMapperReader = likeRecordMapperReader;
    }

    @Autowired
    public void setAttacheInfoMapperReader(AttacheInfoMapper attacheInfoMapperReader) {
        this.attacheInfoMapperReader = attacheInfoMapperReader;
    }

    @Autowired
    public void setCommentRecordMapperReader(CommentRecordMapper commentRecordMapperReader) {
        this.commentRecordMapperReader = commentRecordMapperReader;
    }

    // 用户发布动态附件（包括图片和语音）
    @PostMapping(value = "/{id}/release.do", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public CommonResult<Map<String, Object>> release(
            @PathVariable(name = "id") Long userId,
            DynamicDTO dynamicDTO,
            @RequestPart(value = "files", required = false) MultipartFile[] files) {// 附件列表
        Map<String, Object> result = new ConcurrentHashMap<>();
        result.put("RELEASED", "FAILED");
        try {
            if (log.isDebugEnabled()) {
                log.debug("开始发布动态内容");
            }
            //校验用户信息是否存在
            User user = this.userMapperReader.selectByPrimaryKey(userId);
            if (Objects.isNull(user)) {
                return CommonResult.validateFailed("参数校验不通过，用户信息不存在。");
            }
            dynamicDTO.setUserId(userId);
            String attacheInfoDataType = dynamicDTO.getAttacheInfoDataType();
            //校验动态内容类型是否正确
            if (!Objects.equals(attacheInfoDataType, AttacheInfoDataTypeEnum.Image.getCodeStr())
                    && !Objects.equals(attacheInfoDataType, AttacheInfoDataTypeEnum.Audio.getCodeStr())
                    && !Objects.equals(attacheInfoDataType, AttacheInfoDataTypeEnum.Text.getCodeStr())) {
                return CommonResult.validateFailed("参数校验不通过，不允许此动态内容类型。");
            }
            //校验发布的内容是否包含敏感词汇
            String content = dynamicDTO.getContent();
            if (StrUtil.isNotEmpty(content)) {
                SensitiveWords sensitiveWords01 = this.sensitiveWordsService.checkHasSensitiveWords(content);
                if (!Objects.isNull(sensitiveWords01)) {
                    return CommonResult.validateFailed("参数校验不通过，动态内容包含" + sensitiveWords01.getTypeName() + "类型敏感词汇，禁止发布。");
                }
            }
            //校验发布话题的话题标题是否为空
            if (Objects.equals(IsTopicEnum.Yes.getType(), dynamicDTO.getIsTopic())) {
                String topicTitle = dynamicDTO.getTopicTitle();
                if (StrUtil.isEmpty(topicTitle)) {
                    return CommonResult.validateFailed("参数校验不通过，发布话题时，话题标题不能为空。");
                }
                //校验发布的内容是否包含敏感词汇
                SensitiveWords sensitiveWords02 = this.sensitiveWordsService.checkHasSensitiveWords(topicTitle);
                if (!Objects.isNull(sensitiveWords02)) {
                    return CommonResult.validateFailed("参数校验不通过，话题标题包含" + sensitiveWords02.getTypeName() + "类型敏感词汇，禁止发布。");
                }
            }
            //校验客户端IP，定位省份，城市，区/县，其它地址，经纬度是否全部为空
            String longitudeString = "";
            if (!Objects.isNull(dynamicDTO.getLongitude())) {
                longitudeString = String.valueOf(dynamicDTO.getLongitude());
            }
            String latitudeString = "";
            if (!Objects.isNull(dynamicDTO.getLatitude())) {
                latitudeString = String.valueOf(dynamicDTO.getLatitude());
            }
            if (StrUtil.isAllEmpty(dynamicDTO.getIp(), dynamicDTO.getProvince(), dynamicDTO.getCity(), dynamicDTO.getDistrict(), dynamicDTO.getOther(), longitudeString, latitudeString)) {
                return CommonResult.validateFailed("参数校验不通过，动态定位客户端IP和动态定位地址不能同时为空。");
            }
            if (Objects.isNull(files) || files.length <= 0) {
                //发布不带附件的动态内容
                if (Objects.equals(AttacheInfoDataTypeEnum.Text.getCodeStr(), attacheInfoDataType)) {
                    //校验发布的内容是否为空
                    if (StrUtil.isEmpty(dynamicDTO.getContent())) {
                        return CommonResult.validateFailed("参数校验不通过，发布动态内容不能为空。");
                    }
                }
            } else {
                if (Objects.equals(AttacheInfoDataTypeEnum.Image.getCodeStr(), attacheInfoDataType)) {
                    if (files.length > 4) {
                        return CommonResult.validateFailed("参数校验不通过，一次发布动态内容图片资源文件不能大于4张包括4张。");
                    }
                    //判断图片资源文件类型是否正确
                    for (MultipartFile multipartFile : files) {
                        if (!FileTypeUtil.isImageType(multipartFile.getContentType(), Objects.requireNonNull(multipartFile.getOriginalFilename()))) {
                            return CommonResult.validateFailed("参数校验不通过，发布动态内容图片资源文件类型不正确。");
                        }
                    }
                }
                if (Objects.equals(AttacheInfoDataTypeEnum.Audio.getCodeStr(), attacheInfoDataType)) {
                    if (files.length > 1) {
                        return CommonResult.validateFailed("参数校验不通过，一次发布动态内容语音资源文件不能大于1个包括1个。");
                    }
                    //判断图片资源文件类型是否正确
                    for (MultipartFile multipartFile : files) {
                        if (!FileTypeUtil.isAudioType(multipartFile.getContentType(), Objects.requireNonNull(multipartFile.getOriginalFilename()))) {
                            return CommonResult.validateFailed("参数校验不通过，发布动态内容语音资源文件类型不正确。");
                        }
                    }
                }
            }
            int result2 = this.dynamicService.save(user, dynamicDTO, files);
            if (result2 >= 2) {
                result.put("RELEASED", "OK");
            }
            return CommonResult.success(result, "发布动态内容成功。");
        } catch (Exception e) {
            log.error("发布动态内容出错", e);
            return CommonResult.failed("发布动态内容失败。");
        } finally {
            if (log.isDebugEnabled()) {
                log.debug("结束发布动态内容");
            }
        }
    }

    //检测用户发布动态定位是否发生改变
    @PostMapping(value = "/{id}/checkLocation.do")
    public CommonResult<Map<String, Object>> checkLocation(
            @PathVariable(name = "id") Long userIdLong,
            @RequestBody LocationDTO locationDTO) {
        Map<String, Object> data = new ConcurrentHashMap<>();
        try {
            if (log.isDebugEnabled()) {
                log.debug("开始检测用户发布动态定位");
            }
            User user = this.userMapperReader.selectByPrimaryKey(userIdLong);
            if (Objects.isNull(user)) {
                return CommonResult.validateFailed("参数校验不通过，用户信息不存在。");
            }
            String longitudeString = null;
            Double longitude = locationDTO.getLongitude();
            if (!Objects.isNull(longitude)) {
                longitudeString = String.valueOf(longitude);
            }
            String latitudeString = null;
            Double latitude = locationDTO.getLatitude();
            if (!Objects.isNull(latitude)) {
                latitudeString = String.valueOf(latitude);
            }
            if (StrUtil.isAllEmpty(locationDTO.getIp(), locationDTO.getCountry(), locationDTO.getProvince(), locationDTO.getCity(), locationDTO.getDistrict(), locationDTO.getOther(), longitudeString, latitudeString)) {
                return CommonResult.validateFailed("参数校验不通过，客户端IP，发布动态定位（国家）、（省份）、（城市）、（区/县）、（其它）、（经纬度）不能同时为空。");
            }
            data.put("CHANGED", this.dynamicService.checkLocationIsUpdate(locationDTO, user));
            return CommonResult.success(data, "检测用户发布动态定位成功。");
        } catch (Exception e) {
            log.error("检测用户发布动态定位出错", e);
            return CommonResult.failed("检测用户发布动态定位失败。");
        } finally {
            if (log.isDebugEnabled()) {
                log.debug("结束检测用户发布动态定位");
            }
        }
    }

    //更新用户发布动态定位
    @PostMapping(value = "/{id}/updateLocation.do")
    public CommonResult<Map<String, Object>> updateLocation(
            @PathVariable(name = "id") Long userIdLong,
            @RequestBody LocationDTO locationDTO) {
        Map<String, Object> data = new ConcurrentHashMap<>();
        String b = "ERROR";
        OperateRecord operateRecord = new OperateRecord();
        operateRecord.setStatus(OperateRecordStatusEnum.Fail.getStatus());
        operateRecord.setUserId(userIdLong);
        operateRecord.setType(OperateRecordTypeEnum.UpdateLocation.getCode());
        try {
            if (log.isDebugEnabled()) {
                log.debug("开始更新用户发布动态定位");
            }
            User user = this.userMapperReader.selectByPrimaryKey(userIdLong);
            if (Objects.isNull(user)) {
                return CommonResult.validateFailed("参数校验不通过，用户信息不存在。");
            }
            String ipString = locationDTO.getIp();
            String countryString = locationDTO.getCountry();
            String provinceString = locationDTO.getProvince();
            String cityString = locationDTO.getCity();
            String districtString = locationDTO.getDistrict();
            String otherString = locationDTO.getOther();
            String longitudeString = null;
            Double longitude = locationDTO.getLongitude();
            if (!Objects.isNull(longitude)) {
                longitudeString = String.valueOf(longitude);
            }
            String latitudeString = null;
            Double latitude = locationDTO.getLatitude();
            if (!Objects.isNull(longitude)) {
                latitudeString = String.valueOf(latitude);
            }
            if (StrUtil.isAllEmpty(ipString, countryString, provinceString, cityString, districtString, otherString, longitudeString, latitudeString)) {
                return CommonResult.validateFailed("参数校验不通过，客户端IP，定位（国家）、（省份）、（城市）、（区/县）、（经纬度）不能同时为空。");
            }
            if (StrUtil.isAllEmpty(countryString, provinceString, cityString, longitudeString, latitudeString)) {
                if (StrUtil.isNotEmpty(ipString)) {
                    LocationDTO locationDTO1 = this.dynamicService.getLocationByAliyunIP(ipString);
                    if (!Objects.isNull(locationDTO1)) {
                        countryString = locationDTO1.getCountry();
                        provinceString = locationDTO1.getProvince();
                        cityString = locationDTO1.getCity();
                        districtString = locationDTO1.getDistrict();
                        otherString = locationDTO1.getOther();
                        longitude = locationDTO1.getLongitude();
                        latitude = locationDTO1.getLatitude();
                    }
                }
            }
            Dynamic record = null;
            DynamicExample example = new DynamicExample();
            example.createCriteria().andUserIdEqualTo(userIdLong);
            List<Dynamic> dynamicList = this.dynamicMapperReader.selectByExample(example);
            if (!Objects.isNull(dynamicList) && !dynamicList.isEmpty()) {
                record = dynamicList.get(0);
            }
            LocationDTO locationDTO1 = LocationDTO.builder().build();
            locationDTO1.setIp(ipString);
            locationDTO1.setCountry(countryString);
            locationDTO1.setProvince(provinceString);
            locationDTO1.setCity(cityString);
            locationDTO1.setDistrict(districtString);
            locationDTO1.setOther(otherString);
            locationDTO1.setLongitude(longitude);
            locationDTO1.setLatitude(latitude);
            LocationDTO locationDTO2 = LocationDTO.builder().build();
            if (!Objects.isNull(record)) {
                BeanUtils.copyProperties(record, locationDTO2);
                if (!Objects.deepEquals(locationDTO1, locationDTO2)) {
                    BeanUtils.copyProperties(locationDTO1, record);
                    record.setUpdateTime(new Date());
                    int result = dynamicMapperWriter.updateByPrimaryKeySelective(record);
                    if (result > 0) {
                        b = "OK";
                        operateRecord.setStatus(OperateRecordStatusEnum.Success.getStatus());
                    }
                }
            }
            data.put("UPDATE", b);
            this.operateRecordMapperWriter.insertSelective(operateRecord);
            return CommonResult.success(data, "更新用户发布动态定位成功。");
        } catch (Exception e) {
            log.error("更新用户发布动态定位出错", e);
            this.operateRecordMapperWriter.insertSelective(operateRecord);
            return CommonResult.failed("更新用户发布动态定位失败。");
        } finally {
            if (log.isDebugEnabled()) {
                log.debug("结束更新用户发布动态定位");
            }
        }
    }

    // 注册进入觅鹿界面获取发布动态内容信息列表
    @GetMapping(value = "/{id}/list.do")
    public CommonResult<Map<String, Object>> list(
            @PathVariable(name = "id") Long userId, // 用户id
            @RequestParam(name = "ip", required = false) String ip,//客户端IP
            @RequestParam(name = "longitude", required = false) Double longitude,//经度
            @RequestParam(name = "latitude", required = false) Double latitude,//纬度
            @RequestParam(name = "pageNum", required = false, defaultValue = "1") int pageNum, // 当前页数，默认：当前第1页
            @RequestParam(name = "pageSize", required = false, defaultValue = "20") int pageSize) {// 每页条数，默认：每页20条)
        try {
            if (log.isDebugEnabled()) {
                log.debug("开始获取觅鹿界面发布的动态内容信息列表");
            }
            User user = this.userMapperReader.selectByPrimaryKey(userId);
            if (Objects.isNull(user)) {
                return CommonResult.validateFailed("参数校验不通过，用户信息不存在。");
            }
            String longitudeString = null;
            if (!Objects.isNull(longitude)) {
                longitudeString = Double.toString(longitude);
            }
            String latitudeString = null;
            if (!Objects.isNull(latitude)) {
                latitudeString = Double.toString(latitude);
            }
            if (StrUtil.isAllEmpty(ip, longitudeString, latitudeString)) {
                return CommonResult.validateFailed("参数校验不通过，客户端IP，定位地址经纬度不能同时为空。");
            }
            if (!Objects.isNull(ip)) {
                LocationDTO locationDTO = dynamicService.getLocationByAliyunIP(ip);
                longitude = locationDTO.getLongitude();//经度
                latitude = locationDTO.getLatitude();//纬度
            }
            String country = user.getCountry();
            String province = user.getProvince();
            String city = user.getCity();
            if (StrUtil.isNotEmpty(ip) && StrUtil.isAllEmpty(country, province, city, longitudeString, latitudeString)) {
                LocationDTO locationDTO = dynamicService.getLocationByAliyunIP(ip);
                if (!Objects.isNull(locationDTO)) {
                    country = locationDTO.getCountry(); // 国家
                    province = locationDTO.getProvince();// 省份
                    city = locationDTO.getCity(); // 城市
                    longitude = locationDTO.getLongitude();//经度
                    latitude = locationDTO.getLatitude();//纬度
                }
            }
            DynamicLocationDTO dynamicLocationDTO = new DynamicLocationDTO();
            String gender = user.getGender();
            //3、将“性别”加入筛选条件
            if (Objects.equals(UserGenderEnum.Female.getGender(), gender)) {
                dynamicLocationDTO.setGender(UserGenderEnum.Male.getGender());
            }
            if (Objects.equals(UserGenderEnum.Male.getGender(), gender)) {
                dynamicLocationDTO.setGender(UserGenderEnum.Female.getGender());
            }
            Date minDate = DateUtil.fomatDate(DateUtil.getBeforeYearByAge(this.dynamicDefaultAgeProps.getMinDefaultAge()));
            Date maxDate = DateUtil.fomatDate(DateUtil.getBeforeYearByAge(this.dynamicDefaultAgeProps.getMaxDefaultAge()));
            //4、将“年龄范围”加入筛选条件
            dynamicLocationDTO.setMinAge(minDate);
            dynamicLocationDTO.setMaxAge(maxDate);
            String grade = user.getGrade();
            String constellation = user.getConstellation();
            //5、将“发布定位地址”加入筛选条件
            List<String> countryList = new ArrayList<>();
            List<String> provinceList = new ArrayList<>();
            List<String> cityList = new ArrayList<>();
            if (UserGradeEnum.VIP0.getCode().toString().equals(grade)) { // 普通用户 level 0
                countryList.add(country);
                provinceList.add(province);
            } else {// VIP用户 level 1
                //2、将“星座”加入到筛选条件
                dynamicLocationDTO.setConstellation(constellation);
                countryList.add(country);
                provinceList.add(province);
                cityList.add(city);
            }
            // 获取当前用户的黑名单用户列表
            BlacklistRecordExample example = new BlacklistRecordExample();
            example.setDistinct(true);
            example.setOrderByClause("id DESC, update_time DESC, create_time DESC");
            example.createCriteria().andOwnerUserIdEqualTo(userId);
            List<BlacklistRecord> blacklistRecordList = this.blacklistRecordMapperReader.selectByExample(example).stream().filter((BlacklistRecord b) -> (b.getStatus() % 2) != 0).collect(Collectors.toList());
            List<Long> blackUserIdList = blacklistRecordList.stream().map(BlacklistRecord::getBlackUserId).collect(Collectors.toList());
            DynamicInfoParam dynamicInfoParam = new DynamicInfoParam();
            dynamicInfoParam.setBlackRecordUserIdLongList(blackUserIdList);
            dynamicInfoParam.setCountryLocations(countryList);
            dynamicInfoParam.setProvinceLocations(provinceList);
            dynamicInfoParam.setCityLocations(cityList);
            dynamicInfoParam.setLongitude(longitude);
            dynamicInfoParam.setLatitude(latitude);
            Map<String, Object> data = this.dynamicService.getDynamicInfoData(userId, dynamicInfoParam, pageNum, pageSize);
            return CommonResult.success(data, "获取觅鹿界面发布的动态内容信息列表成功。");
        } catch (Exception e) {
            log.error("获取觅鹿界面发布的动态内容信息列表出错", e);
            return CommonResult.failed("获取觅鹿界面发布的动态内容信息列表失败。");
        } finally {
            if (log.isDebugEnabled()) {
                log.debug("结束获取觅鹿界面发布的动态内容信息列表");
            }
        }
    }

    // 筛选发布动态内容信息列表
    @GetMapping(value = "/{id}/screen.do")
    public CommonResult<Map<String, Object>> screen(
            @PathVariable(name = "id") Long userId, // 用户id
            @RequestParam(name = "ip", required = false) String ip,//客户端ip
            @RequestParam(name = "longitude", required = false) Double longitude,//经度
            @RequestParam(name = "latitude", required = false) Double latitude,//纬度
            @RequestParam(name = "gender", required = false) String gender, // 性别，0->女生，1->男生，2->全部
            @RequestParam(name = "minAge", required = false, defaultValue = "16") Integer minAge, // 年龄范围（最小值），默认：16岁
            @RequestParam(name = "maxAge", required = false, defaultValue = "35") Integer maxAge, // 年龄范围（最大值），默认：35岁
            @RequestParam(name = "constellation", required = false) List<String> constellations, // 星座
            @RequestParam(name = "dataType", required = false, defaultValue = "0") String dataType, // 附件类型，默认：0，全部；0->全部，1->图片或者图片+文字，2->语音或者语音+文字
            @RequestParam(name = "provinceList", required = false) List<String> provinceList, // 发布动态定位（省份列表）
            @RequestParam(name = "cityList", required = false) List<String> cityList,// 发布动态定位（城市列表）
            @RequestParam(name = "industryId", required = false) Long industryId, //行业Id
            @RequestParam(name = "tags", required = false) List<String> tagsList,//标签列表
            @RequestParam(name = "professionId", required = false) Long professionId, //职业Id
            @RequestParam(name = "pageNum", required = false, defaultValue = "1") int pageNum, // 当前页数，默认：当前第1页
            @RequestParam(name = "pageSize", required = false, defaultValue = "20") int pageSize) { // 每页条数，默认：每页20条
        OperateRecord operateRecord = new OperateRecord();
        operateRecord.setStatus(OperateRecordStatusEnum.Fail.getStatus());
        operateRecord.setType(OperateRecordTypeEnum.ScreenDynamic.getCode());
        operateRecord.setUserId(userId);
        try {
            if (log.isDebugEnabled()) {
                log.debug("开始筛选发布动态内容信息列表");
            }
            String longitudeString = null;
            if (!Objects.isNull(longitude)) {
                longitudeString = Double.toString(longitude);
            }
            String latitudeString = null;
            if (!Objects.isNull(latitude)) {
                latitudeString = Double.toString(latitude);
            }
            if (StrUtil.isAllEmpty(ip, longitudeString, latitudeString)) {
                return CommonResult.validateFailed("参数校验不通过，客户端IP，定位地址经纬度不能同时为空。");
            }
            //根据客户端ip获取经纬度，计算距离
            if (Objects.isNull(longitude) && Objects.isNull(latitude)) {
                if (!Objects.isNull(ip)) {
                    LocationDTO locationDTO = dynamicService.getLocationByAliyunIP(ip);
                    longitude = locationDTO.getLongitude();//经度
                    latitude = locationDTO.getLatitude();//纬度
                }
            }

            // 1、校验用户id参数，根据用户id查询用户信息是否存在
            User user = this.userMapperReader.selectByPrimaryKey(userId);
            if (Objects.isNull(user)) {
                return CommonResult.validateFailed("参数校验不通过，用户信息不存在。");
            }

            // 2、校验性别参数，性别参数可选值，0->女生，1->男生,2->男生+女生
            if (!Objects.isNull(gender)) {
                if (!Objects.equals(UserGenderEnum.Female.getGender(), gender) && !Objects.equals(UserGenderEnum.Male.getGender(), gender) && !Objects.equals(UserGenderEnum.ALL.getGender(), gender)) {
                    return CommonResult.validateFailed("参数校验不通过，性别参数值不符合要求。");
                }
            }

            // 3、 校验年龄最小值范围参数，是否大于等于16岁
            if (minAge < 0 || minAge < this.dynamicDefaultAgeProps.getMinRangeAge()) {
                return CommonResult.validateFailed("参数校验不通过，年龄范围最小值为16岁。");
            }

            // 4、 校验年龄最大值范围参数，是否小于等于150岁
            if (maxAge > this.dynamicDefaultAgeProps.getMaxRangeAge()) {
                return CommonResult.validateFailed("参数校验不通过，年龄范围最大值为150岁。");
            }

            // 5、 校验星座数组参数，星座值是否在十二个星座中选择
            ConstellationConstant constellationConstant = new ConstellationConstant();
            if (!Objects.isNull(constellations) && constellations.size() > 0) {
                for (String constellation : constellations) {
                    if (!constellationConstant.getConstellationList().contains(constellation)) {
                        return CommonResult.validateFailed("参数校验不通过，星座值“" + constellation + "”不符合要求。");
                    }
                }
            }
            // 6、校验附件类型参数，附件文件类型，0->全部，1->图片或图片+文字，2->语音或语音+文字
            if (StrUtil.isNotEmpty(dataType)) {
                if (Objects.equals(AttacheInfoDataTypeEnum.Text.getCodeStr(), dataType)) {
                    dataType = null; //null 全部
                }
            }

            DynamicInfoParam dynamicInfoParam = new DynamicInfoParam();
            if (Objects.isNull(gender)) {
                String userGender = user.getGender();
                if (Objects.equals(UserGenderEnum.Female.getGender(), userGender)) {
                    gender = UserGenderEnum.Male.getGender();
                }
                if (Objects.equals(UserGenderEnum.Male.getGender(), userGender)) {
                    gender = UserGenderEnum.Female.getGender();
                }
            } else {
                // 如果前端传过来的筛选条件值为2
                if (Objects.equals(UserGenderEnum.ALL.getGender(), gender)) {
                    gender = null;
                }
            }
            // 设置性别筛选条件
            dynamicInfoParam.setGender(gender);

            // 如果前端传过来的年龄范围最小值为空或等于0
            //（普通用户+VIP用户）默认条件二：设置默认最小年龄范围->16岁
            // 设置年龄范围最小值筛选条件
            dynamicInfoParam.setMinAge(DateUtil.fomatDate(DateUtil.getBeforeYearByAge(minAge)));


            // 如果前端传过来的年龄范围最大值为空或等于0
            //（普通用户+VIP用户）默认条件二：设置默认最大年龄范围->35岁
            // 设置年龄范围最大值筛选条件
            // 年龄最大值范围参数，是否大于等于50岁，是否是50+，则查询条件查询所有的大于最小年龄范围的用户
            dynamicInfoParam.setMaxAge(DateUtil.fomatDate(DateUtil.getBeforeYearByAge(maxAge)));
            if (maxAge >= 50) {
                dynamicInfoParam.setMaxAge(null);
            }
            // 态发布时定位（国家、省份，城市），如果为空，则按照用户注册时的定位筛选
            List<String> countryList = new ArrayList<>();
            countryList.add("中国");

            if (Objects.isNull(provinceList)) {
                provinceList = new ArrayList<>();
            }

            if (Objects.isNull(cityList)) {
                cityList = new ArrayList<>();
            }

            //默认条件三：按照定位地址列表进行筛选， 如果前端传输过来的参数或者省份，城市地址定位列表为空，则获取用户注册时候填写的信息
            String ip2 = user.getIp(); // 用户注册时获取的客户端IP
            String country = user.getCountry(); // 国家
            String province = user.getProvince(); // 省份
            String city = user.getCity(); // 城市
            if (StrUtil.isNotEmpty(ip2)) {
                if (StrUtil.isAllEmpty(country, province, city)) {
                    LocationDTO location = this.dynamicService.getLocationByAliyunIP(ip2);
                    country = location.getCountry();
                    province = location.getProvince();
                    city = location.getCity();
                }
            }
            if (provinceList.isEmpty() && cityList.isEmpty()) {
                countryList.add(country);
                provinceList.add(province);
                cityList.add(city);
            }

            // 设置默认筛选条件国家
            dynamicInfoParam.setCountryLocations(CopyUtil.removeStringDuplicate(countryList));
            // 设置默认筛选条件省份
            dynamicInfoParam.setProvinceLocations(CopyUtil.removeStringDuplicate(provinceList));
            //设置默认筛选条件城市
            dynamicInfoParam.setCityLocations(CopyUtil.removeStringDuplicate(cityList));

            // 判断用户是普通用户还是VIP用户
            // String grade = user.getGrade();
            //设置筛选条件：附件类型
            dynamicInfoParam.setDataType(dataType);
            //设置筛选条件：星座列表
            if (!Objects.isNull(constellations) && constellations.size() > 0) {
                dynamicInfoParam.setConstellations(constellations);
            }
            List<Long> professionIdList = new ArrayList<>();
            if (!Objects.isNull(industryId) && !Objects.isNull(professionId)) {
                professionIdList.add(professionId);
            }
            if (!Objects.isNull(industryId) && Objects.isNull(professionId)) {
                ProfessionsExample example = new ProfessionsExample();
                example.createCriteria().andIndustryIdEqualTo(industryId).andDeleteStatusEqualTo(DeleteStatusEnum.NO.getStatus());
                List<Professions> professionsList = this.professionsMapperReader.selectByExample(example);
                if (!Objects.isNull(professionsList) && !professionsList.isEmpty()) {
                    professionIdList = professionsList.stream().map(Professions::getId).collect(Collectors.toList());
                }
            }
            if (Objects.isNull(industryId) && !Objects.isNull(professionId)) {
                professionIdList.add(professionId);
            }
            dynamicInfoParam.setProfessionIds(professionIdList);

            List<Long> tagsIdList = new ArrayList<>();
            if (!Objects.isNull(tagsList) && !tagsList.isEmpty()) {
                for (String tag : tagsList) {
                    List<Tag> tagRecord = this.tagMapperReader.selectByTagNameLike(tag);
                    if (!Objects.isNull(tagRecord) && !tagRecord.isEmpty()) {
                        List<Long> longList = tagRecord.stream().map(Tag::getId).collect(Collectors.toList());
                        tagsIdList.addAll(longList);
                    }
                }
            }
            dynamicInfoParam.setTagsList(CopyUtil.removeLongDuplicate(tagsIdList));

            // 获取当前用户的黑名单用户列表
            BlacklistRecordExample example = new BlacklistRecordExample();
            example.setDistinct(true);
            example.setOrderByClause("id DESC, update_time DESC, create_time DESC");
            example.createCriteria().andOwnerUserIdEqualTo(userId).andStatusEqualTo(BlacklistRecordStatusEnum.PUSH.getCode());
            List<BlacklistRecord> blacklistRecordList = new ArrayList<>(this.blacklistRecordMapperReader.selectByExample(example));
            List<Long> blackUserIdList = blacklistRecordList.stream().map(BlacklistRecord::getBlackUserId).collect(Collectors.toList());
            blackUserIdList.add(userId);
            CopyUtil.removeLongDuplicate(blackUserIdList);
            dynamicInfoParam.setBlackRecordUserIdLongList(blackUserIdList);
            dynamicInfoParam.setLongitude(longitude);
            dynamicInfoParam.setLatitude(latitude);
            Map<String, Object> data = this.dynamicService.getDynamicInfoData(userId, dynamicInfoParam, pageNum, pageSize);
            operateRecord.setStatus(OperateRecordStatusEnum.Success.getStatus());
            this.operateRecordMapperWriter.insertSelective(operateRecord);
            return CommonResult.success(data, "筛选发布动态内容信息列表成功。");
        } catch (Exception e) {
            log.error("筛选发布动态内容信息列表出错", e);
            this.operateRecordMapperWriter.insertSelective(operateRecord);
            return CommonResult.failed("筛选发布动态内容信息列表出错");
        } finally {
            if (log.isDebugEnabled()) {
                log.debug("结束筛选发布动态内容信息列表");
            }
        }
    }

    // 用户分享动态
    @PutMapping(value = "/{id}/share.do")
    public CommonResult<Map<String, Object>> shareDynamic(@PathVariable(name = "id") Long userId,
                                                          @RequestParam(name = "dynamicInfoId") Long dynamicInfoId,
                                                          @RequestParam(name = "mode") String shareMode) {
        Map<String, Object> data = new ConcurrentHashMap<>();
        OperateRecord operateRecord = new OperateRecord();
        operateRecord.setType(OperateRecordTypeEnum.shareDynamic.getCode());
        operateRecord.setStatus(OperateRecordStatusEnum.Fail.getCode().toString());
        operateRecord.setUserId(userId);
        try {
            if (log.isDebugEnabled()) {
                log.debug("开始用户分享动态内容");
            }
            DynamicInfo dynamicInfo = this.dynamicInfoService.findDynamicInfoByPrimaryKey(dynamicInfoId);
            if (Objects.isNull(dynamicInfo)) {
                return CommonResult.validateFailed("分享动态内容出错，动态内容不存在。");
            }
            ShareRecord shareRecord = new ShareRecord();
            shareRecord.setDynamicInfoId(dynamicInfoId);
            shareRecord.setMode(shareMode);
            shareRecord.setUserId(userId);
            int shareResult = shareRecordMapperWriter.insertSelective(shareRecord);
            int shares = dynamicInfo.getShares() + 1;
            dynamicInfo.setShares(shares);
            dynamicInfo.setUpdateTime(new Date());
            int result = this.dynamicInfoService.updateDynamicInfoByPrimaryKey(dynamicInfo);
            if (result > 0 && shareResult > 0) {
                data.put("SHARED", "OK");
                operateRecord.setStatus(OperateRecordStatusEnum.Success.getCode().toString());
                this.operateRecordMapperWriter.insertSelective(operateRecord);
                return CommonResult.success(data, "分享动态内容成功。");
            } else {
                data.put("SHARED", "ERROR");
                this.operateRecordMapperWriter.insertSelective(operateRecord);
                return CommonResult.success(data, "分享动态内容失败。");
            }
        } catch (Exception e) {
            log.error("用户分享动态内容出错", e);
            data.put("SHARED", "ERROR");
            this.operateRecordMapperWriter.insertSelective(operateRecord);
            return CommonResult.failed(data, ResultCode.FAILED);
        } finally {
            if (log.isDebugEnabled()) {
                log.debug("结束用户分享动态内容");
            }
        }
    }

    // 用户点赞/取消点赞动态内容
    @PutMapping(value = "/{id}/likes.do")
    public CommonResult<Map<String, Object>> likes(@PathVariable(name = "id") Long userId,
                                                   @RequestParam(name = "dynamicInfoId") Long dynamicInfoId,
                                                   @RequestParam(name = "type") String type) {
        Map<String, Object> data = new ConcurrentHashMap<>();
        OperateRecord operateRecord = new OperateRecord();
        operateRecord.setUserId(userId);
        operateRecord.setStatus(OperateRecordStatusEnum.Fail.getStatus());
        operateRecord.setType(OperateRecordTypeEnum.LikesDynamic.getCode());
        data.put("LIKED", "ERROR");
        try {
            if (log.isDebugEnabled()) {
                log.debug("开始点赞当前动态内容");
            }
            //点赞者用户信息
            User user = this.userMapperReader.selectByPrimaryKey(userId);
            if (Objects.isNull(user)) {
                return CommonResult.failed(data, ResultCode.LIKES_USER_IS_NOT_EXIST);
            }
            //被点赞的动态内容信息
            DynamicInfo dynamicInfo = this.dynamicInfoService.findDynamicInfoByPrimaryKey(dynamicInfoId);
            if (Objects.isNull(dynamicInfo)) {
                return CommonResult.failed(data, ResultCode.LIKES_DYNAMIC_INFO_IS_NOT_EXIST);
            }
            //被点赞者用户信息
            Long publishUserId = dynamicInfo.getUserId();
            User publishUser = this.userMapperReader.selectByPrimaryKey(publishUserId);
            if (Objects.isNull(publishUser)) {
                return CommonResult.failed(data, ResultCode.LIKES_USER_IS_NOT_EXIST);
            }
            //点赞记录信息
            LikeRecord likeRecord = this.likeRecordService.findByUserIdAndDynamicInfoId(userId, dynamicInfoId, LikeRecordTypeEnum.Dynamic.getType());
            //取消点赞
            if (LikeStatusEnum.NO.getStatus().equals(type)) {
                if (Objects.isNull(likeRecord)) {
                    return CommonResult.failed(data, ResultCode.LIKES_RECORD_IS_NOT_EXIST);
                }
//                likeRecord.setStatus(LikeStatusEnum.NO.getStatus());
                likeRecord.setUpdateTime(new Date());
//                dynamicInfo.setLikes(dynamicInfo.getLikes() - 1);
                dynamicInfo.setUpdateTime(new Date());
                int result = this.likeRecordService.update(likeRecord, dynamicInfo);
                if (result > 0) {
                    data.put("LIKED", "OK");
                    operateRecord.setStatus(OperateRecordStatusEnum.Success.getStatus());
                    this.operateRecordMapperWriter.insertSelective(operateRecord);
                    return CommonResult.success(data, "无法取消点赞。");
                }
            }
            //点赞
            if (LikeStatusEnum.YES.getStatus().equals(type)) {
                String content = user.getNickName() + " 点赞您的动态 " + dynamicInfo.getContent();//消息内容
                int result = this.likeRecordService.createByUserIdAndDynamicInfoId(content, userId, dynamicInfo, likeRecord);
                if (result > 0) {
                    data.put("LIKED", "OK");
                    String title = "互动消息";//消息标题
                    PushBean pushBean = new PushBean();
                    pushBean.setAlert(content);
                    pushBean.setTitle(title);
                    String regId = publishUser.getReserveColumn03();
                    if (!Objects.isNull(regId) && !Objects.equals(userId, publishUserId)) {
                        this.jiGuangPushService.pushAndroid(pushBean, regId);
                    }
                    operateRecord.setStatus(OperateRecordStatusEnum.Success.getStatus());
                    this.operateRecordMapperWriter.insertSelective(operateRecord);
                    return CommonResult.success(data, "点赞成功。");
                }
            }
        } catch (Exception e) {
            log.error("点赞当前动态内容出现错误", e);
            this.operateRecordMapperWriter.insertSelective(operateRecord);
            return CommonResult.failed("点赞当前动态内容出现错误。");
        } finally {
            if (log.isDebugEnabled()) {
                log.debug("结束点赞当前动态内容");
            }
        }
        return null;
    }

    /**
     * 根据动态内容id申请加微信
     *
     * @param applicantUserId 申请者的用户id
     * @param dynamicInfoId   动态内容信息id
     * @param message         发送的消息
     */
    @PutMapping(value = "/{id}/application.do")
    public CommonResult<Map<String, Object>> applyToAddWechatByDynamicId(@PathVariable(name = "id") Long applicantUserId,
                                                                         @RequestParam(name = "dynamicInfoId") Long dynamicInfoId,
                                                                         @RequestParam(name = "message", required = false) String message) {
        Map<String, Object> data = new ConcurrentHashMap<>();
        OperateRecord operateRecord = new OperateRecord();
        operateRecord.setUserId(applicantUserId);
        operateRecord.setStatus(OperateRecordStatusEnum.Fail.getStatus());
        operateRecord.setType(OperateRecordTypeEnum.ApplyForWechat.getCode());
        data.put("APPLICATION", "ERROR");
        try {
            if (log.isDebugEnabled()) {
                log.debug("开始申请加微信");
            }
            //申请加微信者
            User applicantUser = this.userMapperReader.selectByPrimaryKey(applicantUserId);
            if (Objects.isNull(applicantUser)) {
                return CommonResult.failed(data, ResultCode.APPLICANT_USER_IS_NOT_EXIST);
            }
            //被申请加微信者动态内容信息
            DynamicInfo dynamicInfo = this.dynamicInfoService.findDynamicInfoByPrimaryKey(dynamicInfoId);
            if (Objects.isNull(dynamicInfo)) {
                return CommonResult.failed(data, ResultCode.DYNAMIC_IS_NOT_EXIST);
            }
            //被申请者用户id
            Long applicantsUserId = dynamicInfo.getUserId();
            //需要判断是否是在申请加自己微信
            if (Objects.equals(applicantsUserId, applicantUserId)) {
                return CommonResult.failed(data, ResultCode.APPLICANTS_USER_IS_VALID);
            }
            //被申请者用户信息
            User applicantsUser = this.userMapperReader.selectByPrimaryKey(applicantsUserId);
            if (Objects.isNull(applicantsUser)) {
                return CommonResult.failed(data, ResultCode.APPLICANTS_USER_IS_NOT_EXIST);
            }
            // 获取申请加微信者申请加被申请加微信者微信记录条数，查询当天该用户申请加被申请者微信次数
            int count = this.applicationRecordMapperReader.countByUserId(applicantUserId, applicantsUserId);
            if (count > 0) {
                // 如果次数大于0，则判断被申请加微信者未回复申请加微信者发送的消息，则不允许继续申请加被申请人微信
                MessageExample messageExample = new MessageExample();
                messageExample.setOrderByClause("create_time DESC");
                messageExample.createCriteria()
                        .andRecipientUserIdEqualTo(applicantsUserId)
                        .andSendUserIdEqualTo(applicantUserId)
                        .andReserveColumn01EqualTo(MessageTypeEnum.Applications.getMessage())
                        .andReserveColumn02EqualTo(MessageType2Enum.SEND.getCodeStr());
                List<Message> messageList = this.messageMapperReader.selectByExample(messageExample);
                if (!Objects.isNull(messageList) && !messageList.isEmpty()) {
                    Message messageTemp = messageList.get(0);
                    if (!Objects.isNull(messageTemp)) {
                        int count2 = this.messageMapperReader.countByUserId(applicantsUserId, applicantUserId, messageTemp.getId());
                        if (count2 <= 0) {
                            return CommonResult.failed(data, ResultCode.NO_REPLY_OVERRUN);
                        }
                    }
                }
            }
            // 非VIP用户加不同人微信次数看配置，目前配置是每人每天只能申请添加微信5次，VIP用户没有限制
            ApplicationSetting applicationSetting = this.applicationSettingService.findApplication();
            int times = 0;
            if (!Objects.isNull(applicationSetting)) {
                times = applicationSetting.getTimes();
            }
            if (UserGradeEnum.VIP0.getGrade().equals(applicantUser.getGrade())) {
                int timesResult = this.applicationRecordService.findApplicationRecordCountByUserId(applicantUserId);
                if (timesResult >= times) {
                    return CommonResult.failed(data, ResultCode.TIMES_OVERRUN);
                }
            }
            ApplicationRecord applicationRecord = new ApplicationRecord();
            applicationRecord.setDynamicInfoId(dynamicInfoId);//被申请加微信者动态内容信息id
            applicationRecord.setUserId(applicantUserId);//申请加微信者用户id
            applicationRecord.setReserveColumn01(String.valueOf(applicantsUserId));//被申请加微信者用户id
            if (StrUtil.isNotEmpty(message)) {
                //校验发布的内容是否包含敏感词汇
                SensitiveWords sensitiveWords = this.sensitiveWordsService.checkHasSensitiveWords(message);
                if (!Objects.isNull(sensitiveWords)) {
                    return CommonResult.validateFailed("发送消息，消息内容包含" + sensitiveWords.getTypeName() + "类型敏感词汇，禁止发送。");
                }
            }
            if (StrUtil.isEmpty(message)) {
                message = "申请加您的微信，麻烦通过一下，谢谢！";
            }
            int rowResult = this.applicationRecordService.saveApplicationRecord(dynamicInfo, applicationRecord, message);
            String msg;
            if (rowResult > 0) {
                data.put("APPLICATION", "OK");
                msg = "申请加微信成功。";
                String title = applicantUser.getNickName();//消息标题
                Map<String, String> extras = new ConcurrentHashMap<>();
                PushBean pushBean = new PushBean();
                pushBean.setAlert(message);
                pushBean.setTitle(title);
                pushBean.setExtras(extras);
                String regId = applicantsUser.getReserveColumn03();
                if (!Objects.isNull(regId) && !Objects.equals(applicantsUserId, applicantUserId)) {
                    this.jiGuangPushService.pushAndroid(pushBean, regId);
                }
            } else {
                data.put("APPLICATION", "ERROR");
                msg = "申请加微信失败。";
            }
            operateRecord.setStatus(OperateRecordStatusEnum.Success.getStatus());
            this.operateRecordMapperWriter.insertSelective(operateRecord);
            return CommonResult.success(data, msg);
        } catch (Exception e) {
            log.error("申请加微信出现错误", e);
            this.operateRecordMapperWriter.insertSelective(operateRecord);
            return CommonResult.failed("申请加微信失败。");
        } finally {
            if (log.isDebugEnabled()) {
                log.debug("结束申请加微信");
            }
        }
    }

    // 用户删除动态内容
    @PutMapping(value = "/{id}/delete.do")
    public CommonResult<Map<String, Object>> delete(@PathVariable(name = "id") Long userId,
                                                    @RequestParam(name = "dynamicInfoId") Long dynamicInfoId) {
        Map<String, Object> data = new ConcurrentHashMap<>();
        OperateRecord operateRecord = new OperateRecord();
        operateRecord.setUserId(userId);
        operateRecord.setStatus(OperateRecordStatusEnum.Fail.getStatus());
        operateRecord.setType(OperateRecordTypeEnum.DeleteDynamic.getCode());
        data.put("DELETED", "ERROR");
        try {
            if (log.isDebugEnabled()) {
                log.debug("开始删除动态内容");
            }
            DynamicInfo dynamicInfo = this.dynamicInfoService.findDynamicInfoByPrimaryKey(dynamicInfoId);
            if (dynamicInfo == null) {
                return CommonResult.success(data, "删除动态内容出错，动态内容不存在。");
            }
            long dynamicInfoUserId = dynamicInfo.getUserId();
            String message;
            if (dynamicInfoUserId == userId) {
                dynamicInfo.setDynamicStatus(DynamicInfoStatusEnum.HIDE.getCode().toString());
                dynamicInfo.setUpdateTime(new Date());
                int rowResult = this.dynamicInfoService.updateDynamicInfoByPrimaryKey(dynamicInfo);
                if (rowResult > 0) {
                    message = "删除动态内容成功。";
                    data.put("DELETED", "OK");
                } else {
                    message = "删除动态内容失败。";
                }
            } else {
                message = "删除动态内容失败。";
            }
            operateRecord.setStatus(OperateRecordStatusEnum.Success.getStatus());
            this.operateRecordMapperWriter.insertSelective(operateRecord);
            return CommonResult.success(data, message);
        } catch (Exception e) {
            log.error("删除动态内容出现错误", e);
            this.operateRecordMapperWriter.insertSelective(operateRecord);
            return CommonResult.failed("删除动态内容出现错误。");
        } finally {
            if (log.isDebugEnabled()) {
                log.debug("结束删除动态内容");
            }
        }
    }

    // 分页获取用户自己发布的所有动态内容列表
    @GetMapping(value = "/{id}/mylist.do")
    public CommonResult<Map<String, Object>> myList(@PathVariable(name = "id") Long userId,
                                                    @RequestParam(name = "pageNum", required = false, defaultValue = "1") int pageNum,
                                                    @RequestParam(name = "pageSize", required = false, defaultValue = "20") int pageSize) {
        OperateRecord operateRecord = new OperateRecord();
        operateRecord.setUserId(userId);
        operateRecord.setStatus(OperateRecordStatusEnum.Fail.getStatus());
        operateRecord.setType(OperateRecordTypeEnum.QueryOwnerDynamic.getCode());
        try {
            if (log.isDebugEnabled()) {
                log.debug("开始分页获取用户自己发布的所有动态内容列表");
            }
            User user = this.userMapperReader.selectByPrimaryKey(userId);
            if (Objects.isNull(user)) {
                return CommonResult.validateFailed("参数校验不通过，用户信息不存在。");
            }
            operateRecord.setStatus(OperateRecordStatusEnum.Success.getStatus());
            this.operateRecordMapperWriter.insertSelective(operateRecord);
            return CommonResult.success(this.dynamicInfoService.getMyDynamicInfoDataList(userId, pageNum, pageSize), "分页获取用户自己发布的所有动态内容列表成功。");
        } catch (Exception e) {
            log.error("分页获取用户自己发布的所有动态内容列表出现错误", e);
            this.operateRecordMapperWriter.insertSelective(operateRecord);
            return CommonResult.failed("分页获取用户自己发布的所有动态内容列表出现错误。");
        } finally {
            if (log.isDebugEnabled()) {
                log.debug("结束分页获取用户自己发布的所有动态内容列表");
            }
        }
    }

    //分页获取热门话题
    @GetMapping(value = "/{id}/hotTopics.do")
    public CommonResult<HotTopicVO> hotTopics(@PathVariable(name = "id") Long userId,
                                              @RequestParam(name = "pageNum", required = false, defaultValue = "1") int pageNum,
                                              @RequestParam(name = "pageSize", required = false, defaultValue = "10") int pageSize) {

        try {
            if (log.isDebugEnabled()) {
                log.debug("开始分页获取热门话题列表");
            }
            return CommonResult.success(this.dynamicInfoService.findHotTopicList(userId, pageNum, pageSize), "分页获取热门话题列表成功。");
        } catch (Exception e) {
            log.error("分页获取热门话题列表出错", e);
            return CommonResult.failed("分页获取热门话题列表出现错误。");
        } finally {
            if (log.isDebugEnabled()) {
                log.debug("结束分页获取热门话题列表");
            }
        }
    }

    //获取三个热门话题
    @GetMapping(value = "/{id}/hots.do")
    public CommonResult<Map<String, Object>> hots(@PathVariable(name = "id") Long userId) {
        Map<String, Object> data = new ConcurrentHashMap<>();
        try {
            if (log.isDebugEnabled()) {
                log.debug("开始获取三个热门话题");
            }
            User user = this.userMapperReader.selectByPrimaryKey(userId);
            if (Objects.isNull(user)) {
                return CommonResult.validateFailed("参数校验失败，用户信息不存在。");
            }
            List<HotTopic> hotTopicList = this.dynamicInfoMapperReader.selectHotTopicTitle();
            data.put("hots", hotTopicList);
            return CommonResult.success(data, "获取三个热门话题成功。");
        } catch (Exception e) {
            log.error("获取三个热门话题出错", e);
            return CommonResult.failed("获取三个热门话题出现错误。");
        } finally {
            if (log.isDebugEnabled()) {
                log.debug("结束获取三个热门话题");
            }
        }
    }

    //模糊搜索话题
    @GetMapping(value = "/{id}/search-title.do")
    public CommonResult<Map<String, Object>> searchLikeByTitle(@PathVariable(name = "id") Long userId, @RequestParam(name = "keywords") String keywords) {
        Map<String, Object> data = new ConcurrentHashMap<>();
        try {
            if (log.isDebugEnabled()) {
                log.debug("开始模糊搜索话题");
            }
            User user = this.userMapperReader.selectByPrimaryKey(userId);
            if (Objects.isNull(user)) {
                return CommonResult.validateFailed("参数校验失败，用户信息不存在。");
            }
            List<TagVO> tagVOList = new ArrayList<>();
            this.dynamicInfoMapperReader.selectByKeywords(keywords).forEach((tag) -> {
                TagVO tagVO = new TagVO();
                tagVO.setId(tag.getId());
                tagVO.setName("#" + tag.getTopicTitle());
                tagVOList.add(tagVO);
            });
            data.put("list", tagVOList);
            return CommonResult.success(data, "模糊搜索话题成功。");
        } catch (Exception e) {
            log.error("模糊搜索话题出错", e);
            return CommonResult.failed("模糊搜索话题失败。");
        } finally {
            if (log.isDebugEnabled()) {
                log.debug("结束模糊搜索话题");
            }
        }
    }

    //根据话题名称查询所有话题列表
    @GetMapping(value = "/{id}/find-title.do")
    public CommonResult<Map<String, Object>> findDynamicInfoByTitle(@PathVariable(name = "id") Long userId,
                                                                    @RequestParam(name = "topicTitle") String topicTitle,
                                                                    @RequestParam(name = "ip", required = false) String ip,
                                                                    @RequestParam(name = "longitude", required = false) Double longitude,
                                                                    @RequestParam(name = "latitude", required = false) Double latitude,
                                                                    @RequestParam(name = "pageNum", required = false, defaultValue = "1") int pageNum,
                                                                    @RequestParam(name = "pageSize", required = false, defaultValue = "10") int pageSize) {
        Map<String, Object> data = new ConcurrentHashMap<>();
        try {
            if (log.isDebugEnabled()) {
                log.debug("开始根据话题名称查询所有话题列表");
            }
            User user = this.userMapperReader.selectByPrimaryKey(userId);
            if (Objects.isNull(user)) {
                return CommonResult.validateFailed("参数校验不通过，用户信息不存在。");
            }
            String longitudeString = null;
            if (!Objects.isNull(longitude)) {
                longitudeString = Double.toString(longitude);
            }
            String latitudeString = null;
            if (!Objects.isNull(latitude)) {
                latitudeString = Double.toString(latitude);
            }
            if (StrUtil.isAllEmpty(ip, longitudeString, latitudeString)) {
                return CommonResult.validateFailed("参数校验不通过，客户端IP，定位地址经纬度不能同时为空。");
            }
            if (Objects.isNull(longitude) && Objects.isNull(latitude)) {
                if (!Objects.isNull(ip)) {
                    LocationDTO locationDTO = this.dynamicService.getLocationByAliyunIP(ip);
                    longitude = locationDTO.getLongitude();//经度
                    latitude = locationDTO.getLatitude();//纬度
                }
            }
            final PageInfo<DynamicInfoData> listPageInfo = PageHelper.startPage(pageNum, pageSize).doSelectPageInfo(() -> this.dynamicInfoMapperReader.selectByTopicTitle(topicTitle));
            List<DynamicInfoData> list = new ArrayList<>();
            List<DynamicInfoVO> list2 = new ArrayList<>();
            if (!Objects.isNull(listPageInfo) && listPageInfo.getTotal() > 0) {
                list = listPageInfo.getList();
                data.put("totalPage", listPageInfo.getPages());
            } else {
                data.put("totalPage", 0);
            }
            if (!Objects.isNull(list) && !list.isEmpty()) {
                for (DynamicInfoData dynamicInfoData : list) {
                    DynamicInfoVO dynamicInfoVO = DynamicInfoVO.builder().build();
                    dynamicInfoData.setHeadIcon(StrUtil.trimToNull(this.projectUrlProps.getResDomain()
                            + StrUtil.trimToNull(this.projectUrlProps.getProjectName()))
                            + StrUtil.trimToNull(this.projectUrlProps.getResHeadIcon())
                            + dynamicInfoData.getUserId()
                            + "/"
                            + dynamicInfoData.getHeadIcon());
                    dynamicInfoData.setPublishTime(DateUtil.fomateDate(dynamicInfoData.getCreateTime(), DateUtil.sdfTimeFmt));
                    BeanUtils.copyProperties(dynamicInfoData, dynamicInfoVO);

                    Long dynamicInfoId = dynamicInfoData.getDynamicInfoId(); // 动态内容id

                    // 查询用户对该条动态是否申请加微信
                    ApplicationRecordExample applicationRecordExample = new ApplicationRecordExample();
                    applicationRecordExample.setDistinct(true);
                    applicationRecordExample.setOrderByClause("create_time DESC, id DESC");
                    applicationRecordExample.createCriteria().andUserIdEqualTo(userId).andDynamicInfoIdEqualTo(dynamicInfoId);
                    List<ApplicationRecord> applicationRecordList = this.applicationRecordMapperReader.selectByExample(applicationRecordExample);
                    dynamicInfoVO.setApplicationStatus(applicationRecordList != null && !applicationRecordList.isEmpty());

                    // 查询用户对该条动态是否点赞
                    LikeRecordExample likeRecordExample = new LikeRecordExample();
                    likeRecordExample.setDistinct(true);
                    likeRecordExample.setOrderByClause("create_time DESC, id DESC");
                    likeRecordExample.createCriteria().andUserIdEqualTo(userId).andDynamicInfoIdEqualTo(dynamicInfoId);
                    List<LikeRecord> likeRecordList = this.likeRecordMapperReader.selectByExample(likeRecordExample);
                    if (!Objects.isNull(likeRecordList) && !likeRecordList.isEmpty()) {
                        LikeRecord likeRecord = likeRecordList.get(0);
                        if (!Objects.isNull(likeRecord)) {
                            if (likeRecord.getStatus().equals(LikeStatusEnum.YES.getStatus())) {
                                dynamicInfoVO.setLikeStatus(true);
                            }
                            if (likeRecord.getStatus().equals(LikeStatusEnum.NO.getStatus())) {
                                dynamicInfoVO.setLikeStatus(false);
                            }
                        }
                    } else {
                        dynamicInfoVO.setLikeStatus(false);
                    }
                    if (PublicStatusEnum.NOPublic.getType().equals(dynamicInfoData.getPublishStatus())) {
                        dynamicInfoVO.setAddress(null);
                    }
                    if (PublicStatusEnum.Public.getType().equals(dynamicInfoData.getPublishStatus())) {
                        StringBuilder stringBuilder = new StringBuilder();
                        if (!"省".equals(dynamicInfoData.getProvince())) {
                            stringBuilder.append(dynamicInfoData.getProvince());
                        }
                        if (!Objects.isNull(dynamicInfoData.getCity())) {
                            stringBuilder.append(dynamicInfoData.getCity());
                        }
                        if (!Objects.isNull(dynamicInfoData.getDistrict())) {
                            stringBuilder.append(dynamicInfoData.getDistrict());
                        }
                        dynamicInfoVO.setAddress(stringBuilder.toString());
                    }
                    AttacheInfo attacheInfo = this.attacheInfoMapperReader.selectByDynamicInfoId(dynamicInfoId);
                    if (!Objects.isNull(attacheInfo)) {
                        String[] fileNameList01 = StrUtil.split(attacheInfo.getFileName(), "||");
                        List<String> fileNameList02 = new ArrayList<>(Arrays.asList(fileNameList01));
                        List<String> fileNameList03 = new ArrayList<>();
                        for (String fileName : fileNameList02) {
                            StringBuilder stringBuilder = new StringBuilder();
                            stringBuilder.append(StrUtil.trimToNull(this.projectUrlProps.getResDomain())).append(StrUtil.trimToNull(this.projectUrlProps.getProjectName()));
                            if (Objects.equals(AttacheInfoDataTypeEnum.Image.getCodeStr(), attacheInfo.getDataType())) {
                                stringBuilder.append(StrUtil.trimToNull(this.projectUrlProps.getResDynamicImageFile()));
                            }
                            if (Objects.equals(AttacheInfoDataTypeEnum.Audio.getCodeStr(), attacheInfo.getDataType())) {
                                stringBuilder.append(StrUtil.trimToNull(this.projectUrlProps.getResDynamicVoiceFile()));
                            }
                            stringBuilder.append(fileName);
                            fileNameList03.add(stringBuilder.toString());
                            dynamicInfoVO.setFileName(fileNameList03);
                        }
                    }
                    if (Objects.equals(IsAnonymousEnum.No.getType(), dynamicInfoData.getIsAnonymous())) {
                        dynamicInfoVO.setAnonymous(false);
                    }
                    if (Objects.equals(IsAnonymousEnum.Yes.getType(), dynamicInfoData.getIsAnonymous())) {
                        dynamicInfoVO.setAnonymous(true);
                        dynamicInfoVO.setHeadIcon(StrUtil.trimToNull(this.projectUrlProps.getResDomain()
                                + StrUtil.trimToNull(this.projectUrlProps.getProjectName()))
                                + StrUtil.trimToNull(this.projectUrlProps.getResHeadIcon() + "default.png"));
                        dynamicInfoVO.setNickName("匿名用户");
                    }
                    if (Objects.equals(IsTopicEnum.No.getType(), dynamicInfoData.getIsTopic())) {
                        dynamicInfoVO.setTopic(false);
                    }
                    if (Objects.equals(IsTopicEnum.Yes.getType(), dynamicInfoData.getIsTopic())) {
                        dynamicInfoVO.setTopic(true);
                        dynamicInfoVO.setTopicTitle("#" + dynamicInfoData.getTopicTitle());
                    }
                    dynamicInfoVO.setComments(dynamicInfoData.getComments());
                    if (!Objects.isNull(dynamicInfoData.getLongitude()) && !Objects.isNull(dynamicInfoData.getLatitude())) {
                        dynamicInfoVO.setDistance(DistanceUtil.getDistance(dynamicInfoData.getLongitude(), dynamicInfoData.getLatitude(), longitude, latitude));
                    }
                    list2.add(dynamicInfoVO);
                }
            }
            data.put("list", list2);
            return CommonResult.success(data, "根据话题名称查询所有话题列表成功。");
        } catch (Exception e) {
            log.error("根据话题名称查询所有话题列表出错", e);
            return CommonResult.failed("根据话题名称查询所有话题列表失败。");
        } finally {
            if (log.isDebugEnabled()) {
                log.debug("结束根据话题名称查询所有话题列表");
            }
        }
    }

    //热门推荐话题
    @GetMapping(value = "/{id}/hot-topic.do")
    public CommonResult<Map<String, Object>> findHotByDynamicInfoCount(@PathVariable(name = "id") Long userId) {
        Map<String, Object> commonResult = new ConcurrentHashMap<>();
        try {
            if (log.isDebugEnabled()) {
                log.debug("开始查询热门推荐话题列表");
            }
            User user = this.userMapperReader.selectByPrimaryKey(userId);
            if (Objects.isNull(user)) {
                return CommonResult.validateFailed("参数校验不通过，用户信息不存在。");
            }
            List<HotTopicInfoVO> hotTopicInfoVOList = new ArrayList<>();
            List<HotTopic> hotTopicList = this.dynamicInfoMapperReader.findHotByDynamicInfoCount();
            if (!Objects.isNull(hotTopicList) && !hotTopicList.isEmpty()) {
                hotTopicInfoVOList = hotTopicList.stream().map(hotTopic -> {
                    HotTopicInfoVO hotTopicInfoVO = HotTopicInfoVO.builder().build();
                    BeanUtils.copyProperties(hotTopic, hotTopicInfoVO);
                    return hotTopicInfoVO;
                }).collect(Collectors.toList());
            }
            commonResult.put("list", hotTopicInfoVOList);
            return CommonResult.success(commonResult, "查询热门推荐话题成功。");
        } catch (Exception e) {
            log.error("查询热门推荐话题出现错误", e);
            return CommonResult.failed("查询热门推荐话题失败。");
        } finally {
            if (log.isDebugEnabled()) {
                log.debug("结束查询热门推荐话题列表");
            }
        }
    }

    /**
     * 根据用户id申请加微信
     *
     * @param applicantUserId  申请加微信者用户id
     * @param applicantsUserId 被申请加微信着用户id
     * @param message          申请加微信发送的消息
     */
    @PutMapping(value = "/{id}/apply-to-add-wechat.do")
    public CommonResult<Map<String, Object>> applyToAddWechatByUserId(@PathVariable(name = "id") Long applicantUserId,
                                                                      @RequestParam(name = "userId") Long applicantsUserId,
                                                                      @RequestParam(name = "message", required = false) String message) {
        Map<String, Object> data = new ConcurrentHashMap<>();
        OperateRecord operateRecord = new OperateRecord();
        operateRecord.setUserId(applicantUserId);
        operateRecord.setStatus(OperateRecordStatusEnum.Fail.getStatus());
        operateRecord.setType(OperateRecordTypeEnum.ApplyForWechat.getCode());
        data.put("APPLICATION", "ERROR");
        try {
            if (log.isDebugEnabled()) {
                log.debug("开始申请加微信");
            }
            //申请加微信者
            User applicantUser = this.userMapperReader.selectByPrimaryKey(applicantUserId);
            if (Objects.isNull(applicantUser)) {
                return CommonResult.failed(data, ResultCode.APPLICANT_USER_IS_NOT_EXIST);
            }
            //需要判断是否是在申请加自己微信
            if (Objects.equals(applicantsUserId, applicantUserId)) {
                return CommonResult.failed(data, ResultCode.APPLICANTS_USER_IS_VALID);
            }
            //被申请者用户信息
            User applicantsUser = this.userMapperReader.selectByPrimaryKey(applicantsUserId);
            if (Objects.isNull(applicantsUser)) {
                return CommonResult.failed(data, ResultCode.APPLICANTS_USER_IS_NOT_EXIST);
            }
            if (StrUtil.isNotEmpty(message)) {
                //校验发布的内容是否包含敏感词汇
                SensitiveWords sensitiveWords = this.sensitiveWordsService.checkHasSensitiveWords(message);
                if (!Objects.isNull(sensitiveWords)) {
                    return CommonResult.validateFailed("发送消息，消息内容包含" + sensitiveWords.getTypeName() + "类型敏感词汇，禁止发送。");
                }
            }
            if (StrUtil.isEmpty(message)) {
                message = "申请加您的微信，麻烦通过一下，谢谢！";
            }
            // 获取申请加微信者申请加被申请加微信者微信记录条数，查询当天用户申请加微信次数
            int count = this.applicationRecordMapperReader.countByUserId(applicantUserId, applicantsUserId);
            if (count > 0) {
                // 如果次数大于0，则判断被申请加微信者未回复申请加微信者发送的消息，则不允许继续申请加被申请人微信
                MessageExample messageExample = new MessageExample();
                messageExample.setOrderByClause("create_time DESC");
                messageExample.createCriteria()
                        .andRecipientUserIdEqualTo(applicantsUserId)
                        .andSendUserIdEqualTo(applicantUserId)
                        .andReserveColumn01EqualTo(MessageTypeEnum.Applications.getMessage())
                        .andReserveColumn02EqualTo(MessageType2Enum.SEND.getCodeStr());
                List<Message> messageList = this.messageMapperReader.selectByExample(messageExample);
                if (!Objects.isNull(messageList) && !messageList.isEmpty()) {
                    Message messageTemp = messageList.get(0);
                    if (!Objects.isNull(messageTemp)) {
                        int count2 = this.messageMapperReader.countByUserId(applicantsUserId, applicantUserId, messageTemp.getId());
                        if (count2 <= 0) {
                            return CommonResult.failed(data, ResultCode.NO_REPLY_OVERRUN);
                        }
                    }
                }
            }
            // 非VIP用户加不同人微信次数看配置，目前配置是每人每天只能申请添加微信5次，VIP用户没有限制
            ApplicationSetting applicationSetting = this.applicationSettingService.findApplication();
            int times = 0;
            if (!Objects.isNull(applicationSetting)) {
                times = applicationSetting.getTimes();
            }
            if (UserGradeEnum.VIP0.getGrade().equals(applicantUser.getGrade())) {
                int timesResult = this.applicationRecordService.findApplicationRecordCountByUserId(applicantUserId);
                if (timesResult >= times) {
                    return CommonResult.failed(data, ResultCode.TIMES_OVERRUN);
                }
            }
            DynamicInfo dynamicInfo = null;
            DynamicInfoExample dynamicInfoExample = new DynamicInfoExample();
            dynamicInfoExample.setOrderByClause("create_time DESC, update_time DESC");
            dynamicInfoExample.createCriteria().andDynamicStatusNotEqualTo(DynamicInfoStatusEnum.HIDE.getStatus()).andUserIdEqualTo(applicantsUserId);
            List<DynamicInfo> dynamicInfoList = this.dynamicInfoMapperReader.selectByExample(dynamicInfoExample);
            if (!Objects.isNull(dynamicInfoList) && dynamicInfoList.size() > 0) {
                dynamicInfo = dynamicInfoList.get(0);
            }
            ApplicationRecord applicationRecord = new ApplicationRecord();
            if (!Objects.isNull(dynamicInfo)) {
                applicationRecord.setDynamicInfoId(dynamicInfo.getId());//被申请加微信者动态内容信息id
            } else {
                applicationRecord.setDynamicInfoId(applicantsUserId);//被申请加微信者动态内容信息id
            }
            applicationRecord.setUserId(applicantUserId);//申请加微信者用户id
            applicationRecord.setReserveColumn01(String.valueOf(applicantsUserId));//被申请加微信者用户id
            int rowResult = this.applicationRecordService.saveApplicationRecord(dynamicInfo, applicationRecord, message);
            String msg;
            if (rowResult > 0) {
                data.put("APPLICATION", "OK");
                msg = "申请加微信成功。";
                String title = applicantUser.getNickName();//消息标题
                Map<String, String> extras = new ConcurrentHashMap<>();
                PushBean pushBean = new PushBean();
                pushBean.setAlert(message);
                pushBean.setTitle(title);
                pushBean.setExtras(extras);
                String regId = applicantsUser.getReserveColumn03();
                if (!Objects.isNull(regId) && !Objects.equals(applicantsUserId, applicantUserId)) {
                    this.jiGuangPushService.pushAndroid(pushBean, regId);
                }
            } else {
                data.put("APPLICATION", "ERROR");
                msg = "申请加微信失败。";
            }
            operateRecord.setStatus(OperateRecordStatusEnum.Success.getStatus());
            this.operateRecordMapperWriter.insertSelective(operateRecord);
            return CommonResult.success(data, msg);
        } catch (Exception e) {
            log.error("申请加微信出现错误", e);
            this.operateRecordMapperWriter.insertSelective(operateRecord);
            return CommonResult.failed("申请加微信出现错误。");
        } finally {
            if (log.isDebugEnabled()) {
                log.debug("结束申请加微信");
            }
        }
    }

    //校验内容是否包含敏感词汇接口
    @GetMapping("/check.do")
    public CommonResult<Map<String, Object>> checkResult(@RequestParam(name = "content") String content) {
        Map<String, Object> data = new ConcurrentHashMap<>();
        data.put("HAS", false);
        try {
            if (log.isDebugEnabled()) {
                log.debug("开始校验内容是否含有敏感词汇");
            }
            SensitiveWords sensitiveWords = this.check(content);
            if (!Objects.isNull(sensitiveWords)) {
                data.put("HAS", true);
                return CommonResult.success(data, "内容校验不通过，内容包含" + sensitiveWords.getTypeName() + "类型敏感词汇。");
            }
            return CommonResult.success(data, "内容校验通过，内容不包含敏感词汇。");
        } catch (Exception e) {
            log.error("校验内容是否含有敏感词汇出错", e);
            return CommonResult.failed("校验内容是否含有敏感词汇失败");
        } finally {
            if (log.isDebugEnabled()) {
                log.debug("结束校验内容是否含有敏感词汇");
            }
        }
    }

    /**
     * 根据别人的用户id查询他的动态信息
     *
     * @param id  当前用户id
     * @param id2 别人用户id
     */
    @GetMapping("/{id}/{id2}/otherList.do")
    public CommonResult<Map<String, Object>> getDynamicByUserId(@PathVariable(name = "id") Long id,
                                                                @PathVariable(name = "id2") Long id2,
                                                                @RequestParam(name = "pageNum", required = false, defaultValue = "1") int pageNum,
                                                                @RequestParam(name = "pageSize", required = false, defaultValue = "20") int pageSize) {
        OperateRecord operateRecord = new OperateRecord();
        operateRecord.setUserId(id2);
        operateRecord.setStatus(OperateRecordStatusEnum.Fail.getStatus());
        operateRecord.setType(OperateRecordTypeEnum.QueryOwnerDynamic.getCode());
        try {
            if (log.isDebugEnabled()) {
                log.debug("开始分页获取别人发布的所有动态内容列表");
            }
            User user1 = this.userMapperReader.selectByPrimaryKey(id);
            if (Objects.isNull(user1)) {
                return CommonResult.validateFailed("参数校验不通过，用户信息不存在。");
            }
            User user2 = this.userMapperReader.selectByPrimaryKey(id2);
            if (Objects.isNull(user2)) {
                return CommonResult.validateFailed("参数校验不通过，用户信息不存在。");
            }
            operateRecord.setStatus(OperateRecordStatusEnum.Success.getStatus());
            this.operateRecordMapperWriter.insertSelective(operateRecord);
            return CommonResult.success(this.dynamicInfoService.getOtherDynamicInfoDataList(id, id2, pageNum, pageSize), "分页获取别人发布的所有动态内容列表成功。");
        } catch (Exception e) {
            log.error("分页获取别人发布的所有动态内容列表出现错误", e);
            this.operateRecordMapperWriter.insertSelective(operateRecord);
            return CommonResult.failed("分页获取别人发布的所有动态内容列表出现错误。");
        } finally {
            if (log.isDebugEnabled()) {
                log.debug("结束分页获取别人发布的所有动态内容列表");
            }
        }
    }

    // 用户置顶/取消置顶动态内容
    @PutMapping(value = "/{id}/topping.do")
    public CommonResult<Map<String, Object>> topping(@PathVariable(name = "id") Long userId,
                                                     @RequestParam(name = "dynamicInfoId") Long dynamicInfoId) {
        Map<String, Object> data = new ConcurrentHashMap<>();
        OperateRecord operateRecord = new OperateRecord();
        operateRecord.setUserId(userId);
        operateRecord.setStatus(OperateRecordStatusEnum.Fail.getStatus());
        operateRecord.setType(OperateRecordTypeEnum.ToppingDynamic.getCode());
        data.put("TOPPING", "ERROR");
        try {
            if (log.isDebugEnabled()) {
                log.debug("开始置顶/取消置顶动态内容");
            }
            DynamicInfo dynamicInfo = this.dynamicInfoService.findDynamicInfoByPrimaryKey(dynamicInfoId);
            if (Objects.isNull(dynamicInfo)) {
                return CommonResult.success(data, "置顶动态内容出错，动态内容不存在。");
            }
            long dynamicInfoUserId = dynamicInfo.getUserId();
            String message;
            String message1 = null;
            if (Objects.equals(userId, dynamicInfoUserId)) {
                //置顶
                String type = dynamicInfo.getIsTop();
                if (Objects.equals(type, DynamicInfoToppingEnum.NO.getStatus())) {
                    int count = this.dynamicInfoMapperReader.selectMyIsTopCount(userId);
                    if (count > 0) {
                        return CommonResult.success(data, "置顶动态内容出错，只能置顶一条动态。");
                    }
                    dynamicInfo.setIsTop(DynamicInfoToppingEnum.YES.getStatus());
                    message1 = "置顶";
                }
                //取消置顶
                if (Objects.equals(type, DynamicInfoToppingEnum.YES.getStatus())) {
                    dynamicInfo.setIsTop(DynamicInfoToppingEnum.NO.getStatus());
                    message1 = "取消置顶";
                }
                dynamicInfo.setUpdateTime(new Date());
                int rowResult = this.dynamicInfoService.updateDynamicInfoByPrimaryKey(dynamicInfo);
                if (rowResult > 0) {
                    message = message1 + "动态内容成功。";
                    data.put("TOPPING", "OK");
                } else {
                    message = message1 + "动态内容失败。";
                }
            } else {
                message = "置顶/取消置顶动态内容失败。";
            }
            operateRecord.setStatus(OperateRecordStatusEnum.Success.getStatus());
            this.operateRecordMapperWriter.insertSelective(operateRecord);
            return CommonResult.success(data, message);
        } catch (Exception e) {
            log.error("置顶/取消置顶动态内容出现错误", e);
            this.operateRecordMapperWriter.insertSelective(operateRecord);
            return CommonResult.failed("置顶/取消置顶动态内容出现错误。");
        } finally {
            if (log.isDebugEnabled()) {
                log.debug("结束置顶/取消置顶动态内容");
            }
        }
    }

    //查询动态内容与当前用户的关系接口
    @GetMapping("/{id}/info.do")
    public CommonResult<DynamicInfoVO> getDynamicInfoById(@PathVariable(name = "id") Long userId,
                                                          @RequestParam(name = "dynamicInfoId") Long dynamicInfoId,
                                                          @RequestParam(name = "ip", required = false) String clientIP,
                                                          @RequestParam(name = "longitude", required = false) Double longitude,
                                                          @RequestParam(name = "latitude", required = false) Double latitude) {
        try {
            if (log.isDebugEnabled()) {
                log.debug("开始查询动态内容与当前用户的关系");
            }
            String longitudeString = null;
            if (!Objects.isNull(longitude)) {
                longitudeString = Double.toString(longitude);
            }
            String latitudeString = null;
            if (!Objects.isNull(latitude)) {
                latitudeString = Double.toString(latitude);
            }
            if (StrUtil.isAllEmpty(clientIP, longitudeString, latitudeString)) {
                return CommonResult.validateFailed("参数校验不通过，客户端IP，定位地址经纬度不能同时为空。");
            }
            if (Objects.isNull(longitude) && Objects.isNull(latitude)) {
                if (!Objects.isNull(clientIP)) {
                    LocationDTO locationDTO = this.dynamicService.getLocationByAliyunIP(clientIP);
                    longitude = locationDTO.getLongitude(); //经度
                    latitude = locationDTO.getLatitude();   //纬度
                }
            }
            User user = this.userMapperReader.selectByPrimaryKey(userId);
            if (Objects.isNull(user)) {
                return CommonResult.validateFailed("参数校验不通过，当前用户信息不存在");
            }
            DynamicInfo dynamicInfo = this.dynamicInfoMapperReader.selectByPrimaryKey(dynamicInfoId);
            if (Objects.isNull(dynamicInfo) || Objects.equals(DynamicInfoStatusEnum.HIDE.getStatus(), dynamicInfo.getDynamicStatus())) {
                return CommonResult.validateFailed("参数校验不通过，当前动态内容信息不存在");
            }
            //动态内容发布者id
            Long dynamicInfoUserId = dynamicInfo.getUserId();
            User dynamicInfoUser = this.userMapperReader.selectByPrimaryKey(dynamicInfoUserId);
            if (Objects.isNull(dynamicInfoUser) || Objects.equals(UserStatusEnum.Abnormal.getStatus(), dynamicInfoUser.getStatus())) {
                return CommonResult.validateFailed("参数校验不通过，当前动态内容用户信息不存在");
            }
            //动态id
            Long dynamicId = dynamicInfo.getDynamicId();
            Dynamic dynamic = this.dynamicMapperReader.selectByPrimaryKey(dynamicId);
            if (Objects.isNull(dynamic)) {
                return CommonResult.validateFailed("参数校验不通过，当前动态信息不存在");
            }
            DynamicInfoVO dynamicInfoVO = DynamicInfoVO.builder().build();
            BeanUtils.copyProperties(dynamicInfo, dynamicInfoVO);
            dynamicInfoVO.setNickName(dynamicInfoUser.getNickName());
            dynamicInfoVO.setHeadIcon(StrUtil.trimToNull(this.projectUrlProps.getResDomain()
                    + StrUtil.trimToNull(this.projectUrlProps.getProjectName()))
                    + StrUtil.trimToNull(this.projectUrlProps.getResHeadIcon())
                    + dynamicInfoUserId
                    + "/"
                    + dynamicInfoUser.getHeadIcon());
            dynamicInfoVO.setPublishTime(DateUtil.fomateDate(dynamicInfo.getCreateTime(), DateUtil.sdfTimeFmt));
            // 查询用户对该条动态是否申请加微信
            ApplicationRecordExample applicationRecordExample = new ApplicationRecordExample();
            applicationRecordExample.createCriteria().andUserIdEqualTo(userId).andDynamicInfoIdEqualTo(dynamicInfoId).andReserveColumn01EqualTo(String.valueOf(dynamicInfoUserId));
            List<ApplicationRecord> applicationRecordList = this.applicationRecordMapperReader.selectByExample(applicationRecordExample);
            dynamicInfoVO.setApplicationStatus(applicationRecordList != null && !applicationRecordList.isEmpty());

            // 查询用户对该条动态是否点赞
            LikeRecordExample likeRecordExample = new LikeRecordExample();
            likeRecordExample.setDistinct(true);
            likeRecordExample.setOrderByClause("create_time DESC, id DESC");
            likeRecordExample.createCriteria().andUserIdEqualTo(userId).andDynamicInfoIdEqualTo(dynamicInfoId);
            List<LikeRecord> likeRecordList = this.likeRecordMapperReader.selectByExample(likeRecordExample);
            if (!Objects.isNull(likeRecordList) && !likeRecordList.isEmpty()) {
                LikeRecord likeRecord = likeRecordList.get(0);
                if (!Objects.isNull(likeRecord)) {
                    if (likeRecord.getStatus().equals(LikeStatusEnum.YES.getStatus())) {
                        dynamicInfoVO.setLikeStatus(true);
                    }
                    if (likeRecord.getStatus().equals(LikeStatusEnum.NO.getStatus())) {
                        dynamicInfoVO.setLikeStatus(false);
                    }
                }
            } else {
                dynamicInfoVO.setLikeStatus(false);
            }
            if (PublicStatusEnum.NOPublic.getType().equals(dynamicInfo.getPublicStatus())) {
                dynamicInfoVO.setAddress(null);
            }
            if (PublicStatusEnum.Public.getType().equals(dynamicInfo.getPublicStatus())) {
                StringBuilder stringBuilder = new StringBuilder();
                if (!"省".equals(dynamicInfo.getProvince())) {
                    stringBuilder.append(dynamicInfo.getProvince());
                }
                if (!Objects.isNull(dynamicInfo.getCity())) {
                    stringBuilder.append(dynamicInfo.getCity());
                }
                if (!Objects.isNull(dynamicInfo.getDistrict())) {
                    stringBuilder.append(dynamicInfo.getDistrict());
                }
                dynamicInfoVO.setAddress(stringBuilder.toString());
            }
            AttacheInfo attacheInfo = this.attacheInfoMapperReader.selectByDynamicInfoId(dynamicInfoId);
            if (!Objects.isNull(attacheInfo)) {
                String[] fileNameList01 = StrUtil.split(attacheInfo.getFileName(), "||");
                List<String> fileNameList02 = new ArrayList<>(Arrays.asList(fileNameList01));
                List<String> fileNameList03 = new ArrayList<>();
                for (String fileName : fileNameList02) {
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append(StrUtil.trimToNull(this.projectUrlProps.getResDomain())).append(StrUtil.trimToNull(this.projectUrlProps.getProjectName()));
                    if (Objects.equals(AttacheInfoDataTypeEnum.Image.getCodeStr(), attacheInfo.getDataType())) {
                        stringBuilder.append(StrUtil.trimToNull(this.projectUrlProps.getResDynamicImageFile()));
                    }
                    if (Objects.equals(AttacheInfoDataTypeEnum.Audio.getCodeStr(), attacheInfo.getDataType())) {
                        stringBuilder.append(StrUtil.trimToNull(this.projectUrlProps.getResDynamicVoiceFile()));
                    }
                    stringBuilder.append(fileName);
                    fileNameList03.add(stringBuilder.toString());
                    dynamicInfoVO.setFileName(fileNameList03);
                }
                if (Objects.equals(AttacheInfoDataTypeEnum.Image.getCodeStr(), attacheInfo.getDataType())) {
                    dynamicInfoVO.setAttacheFileDataType(AttacheInfoDataTypeEnum.Image.getCodeStr());
                }
                if (Objects.equals(AttacheInfoDataTypeEnum.Audio.getCodeStr(), attacheInfo.getDataType())) {
                    dynamicInfoVO.setAttacheFileDataType(AttacheInfoDataTypeEnum.Audio.getCodeStr());
                }
            } else {
                dynamicInfoVO.setAttacheFileDataType(AttacheInfoDataTypeEnum.Text.getCodeStr());
            }
            if (Objects.equals(IsAnonymousEnum.No.getType(), dynamicInfo.getIsAnonymous())) {
                dynamicInfoVO.setAnonymous(false);
            }

            if (Objects.equals(IsAnonymousEnum.Yes.getType(), dynamicInfo.getIsAnonymous())) {
                dynamicInfoVO.setAnonymous(true);
                dynamicInfoVO.setHeadIcon(StrUtil.trimToNull(this.projectUrlProps.getResDomain()
                        + StrUtil.trimToNull(this.projectUrlProps.getProjectName()))
                        + StrUtil.trimToNull(this.projectUrlProps.getResHeadIcon() + "default.png"));
                dynamicInfoVO.setNickName("匿名用户");
            }
            if (Objects.equals(IsTopicEnum.No.getType(), dynamicInfo.getIsTopic())) {
                dynamicInfoVO.setTopic(false);
            }
            if (Objects.equals(IsTopicEnum.Yes.getType(), dynamicInfo.getIsTopic())) {
                dynamicInfoVO.setTopic(true);
                dynamicInfoVO.setTopicTitle("#" + dynamicInfo.getTopicTitle());
            }
            dynamicInfoVO.setComments(dynamicInfo.getComments());
            CommentRecordExample commentRecordExample = new CommentRecordExample();
            commentRecordExample.createCriteria().andDynamicInfoIdEqualTo(dynamicInfoId).andBeUserIdEqualTo(dynamicInfoUserId).andUserIdEqualTo(userId);
            List<CommentRecord> commentRecordList = this.commentRecordMapperReader.selectByExample(commentRecordExample);
            dynamicInfoVO.setIsComment(!Objects.isNull(commentRecordList) && !commentRecordList.isEmpty());
            if (!Objects.isNull(dynamicInfo.getLongitude()) && !Objects.isNull(dynamicInfo.getLatitude())) {
                dynamicInfoVO.setDistance(DistanceUtil.getDistance(dynamicInfo.getLongitude(), dynamicInfo.getLatitude(), longitude, latitude));
            }
            dynamicInfoVO.setDynamicInfoId(dynamicInfoId);
            return CommonResult.success(dynamicInfoVO);
        } catch (Exception e) {
            log.error("查询动态内容与当前用户的关系出现错误", e);
            return CommonResult.failed("查询动态内容与当前用户的关系失败");
        } finally {
            if (log.isDebugEnabled()) {
                log.debug("结束查询动态内容与当前用户的关系");
            }
        }
    }

    private SensitiveWords check(String content) {
        if (StrUtil.isNotEmpty(content)) {
            return this.sensitiveWordsService.checkHasSensitiveWords(content);
        }
        return null;
    }
}