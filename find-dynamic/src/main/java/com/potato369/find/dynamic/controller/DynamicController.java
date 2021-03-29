package com.potato369.find.dynamic.controller;

import cn.hutool.core.util.StrUtil;
import com.potato369.find.common.api.CommonResult;
import com.potato369.find.common.api.ResultCode;
import com.potato369.find.common.constants.ConstellationConstant;
import com.potato369.find.common.dto.DynamicDTO;
import com.potato369.find.common.dto.DynamicLocationDTO;
import com.potato369.find.common.dto.LocationDTO;
import com.potato369.find.common.enums.*;
import com.potato369.find.common.exception.UserAuthorizeException;
import com.potato369.find.common.utils.CopyUtil;
import com.potato369.find.common.utils.DateUtil;
import com.potato369.find.common.utils.FileTypeUtil;
import com.potato369.find.dynamic.config.bean.PushBean;
import com.potato369.find.dynamic.config.props.DynamicDefaultAgeProps;
import com.potato369.find.dynamic.service.*;
import com.potato369.find.mbg.mapper.*;
import com.potato369.find.mbg.model.*;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Api(value = "动态模块用户管理控制器类")
@Slf4j
@RestController
@RequestMapping("/v1/dynamic")
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

	// 用户发布动态附件（包括图片和语音）
    @PostMapping(value = "/{id}/release.do", consumes = {"multipart/form-data;charset=utf-8"}, produces = {"application/json;charset=utf-8"})
    public CommonResult<Map<String, Object>> releaseDynamicFiles(
            @PathVariable(name = "id", required = true) Long userIdLong,// 用户id
            @RequestParam(name = "attacheInfoDataType", required = false) String attacheInfoDataType,// 附件类型，0->图片，1->语音
            @RequestParam(name = "imei", required = false) String imei,// 设备串号
            @RequestParam(name = "model", required = false) String model,// 设备型号
            @RequestParam(name = "sysName", required = false) String sysName,// 设备系统名称
            @RequestParam(name = "sysCode", required = false) String sysCode,// 设备系统版本
            @RequestParam(name = "networkMode", required = false, defaultValue = "4G") String networkMode,// 网络方式
            @RequestParam(name = "ip", required = false) String ip,// 客户端IP
            @RequestParam(name = "country", required = false, defaultValue = "中国") String country,// 发布定位（国）
            @RequestParam(name = "province", required = false) String province,// 发布定位（省份）
            @RequestParam(name = "city", required = false) String city,// 发布定位（城市）
            @RequestParam(name = "publicStatus", required = false, defaultValue = "0") String publicStatus,// 是否公开定位状态，0->不公开，1->公开
            @RequestPart(value = "files", required = false) MultipartFile[] files,// 附件列表
            @RequestParam(name = "content", required = false) String content) { // 动态内容
        try {
            if (log.isDebugEnabled()) {
                log.debug("开始发布动态内容");
            }
            DynamicDTO dynamicDTO = new DynamicDTO();
            dynamicDTO.setUserId(userIdLong);
            dynamicDTO.setImei(imei);
            dynamicDTO.setAttacheInfoDataType(attacheInfoDataType);
            dynamicDTO.setModel(model);
            dynamicDTO.setSysName(sysName);
            dynamicDTO.setSysCode(sysCode);
            dynamicDTO.setNetworkMode(networkMode);
            dynamicDTO.setIp(ip);
            dynamicDTO.setCountry(country);
            dynamicDTO.setProvince(province);
            dynamicDTO.setCity(city);
            dynamicDTO.setPublicStatus(publicStatus);
            dynamicDTO.setContent(content);
            User user = this.userMapperReader.selectByPrimaryKey(userIdLong);
            if (user == null) {
                return CommonResult.validateFailed("发布动态内容，用户信息不存在。");
            }
            if (files == null) {
                if (StrUtil.isEmpty(content)) {
                    return CommonResult.validateFailed("发布动态内容不能为空。");
                }
                this.dynamicService.save(dynamicDTO);
                Map<String, Object> result = new HashMap<>();
                result.put("RELEASED", "OK");
                return CommonResult.success(result, "发布动态内容成功。");
            }
            if (AttacheInfoDataTypeEnum.Image.getCode().toString().equals(attacheInfoDataType)) {
                if (files.length == 0) {
                    return CommonResult.failed("发布动态内容图片资源文件不能为空。");
                }
                if (files.length > 4) {
                    return CommonResult.failed("一次发布动态内容图片资源文件不能大于4张包括4张。");
                }
                //判断图片资源文件类型是否正确
                for (MultipartFile multipartFile : files) {
                    if (!FileTypeUtil.isImageType(multipartFile.getContentType(), multipartFile.getOriginalFilename())) {
                        return CommonResult.failed("发布动态内容图片资源文件类型不正确。");
                    }
                }
            }
            if (AttacheInfoDataTypeEnum.Audio.getCode().toString().equals(attacheInfoDataType)) {
                if (files.length == 0) {
                    return CommonResult.failed("发布动态内容语音资源文件不能为空。");
                }
                if (files.length > 1) {
                    return CommonResult.failed("一次发布动态内容语音资源文件不能大于1个包括1个。");
                }
                //判断图片资源文件类型是否正确
                for (MultipartFile multipartFile : files) {
                    if (!FileTypeUtil.isAudioType(multipartFile.getContentType(), multipartFile.getOriginalFilename())) {
                        return CommonResult.failed("发布动态内容语音资源文件类型不正确。");
                    }
                }
            }
            if (StrUtil.isEmpty(imei)) {
                dynamicDTO.setImei(user.getImei());
            }
            if (StrUtil.isEmpty(model)) {
                dynamicDTO.setModel(user.getModel());
            }
            if (StrUtil.isEmpty(sysName)) {
                dynamicDTO.setSysName(sysName);
            }
            if (StrUtil.isEmpty(sysCode)) {
                dynamicDTO.setSysCode(sysCode);
            }
            if (StrUtil.isEmpty(ip)) {
                if (StrUtil.isAllEmpty(province, city)) {
                    if (StrUtil.isNotEmpty(user.getIp())) {
                        dynamicDTO.setIp(user.getIp());
                    } else {
                        dynamicDTO.setIp("127.0.0.1");
                    }
                    if (StrUtil.isNotEmpty(user.getCountry())) {
                        dynamicDTO.setCountry(user.getCountry());
                    } else {
                        dynamicDTO.setCountry("中国");
                    }
                    if (StrUtil.isNotEmpty(user.getProvince())) {
                        dynamicDTO.setProvince(user.getProvince());
                    } else {
                        dynamicDTO.setProvince("广东省");
                    }
                    if (StrUtil.isNotEmpty(user.getCity())) {
                        dynamicDTO.setCity(user.getCity());
                    } else {
                        dynamicDTO.setCity("深圳市");
                    }
                }
            }
            return this.dynamicService.update(dynamicDTO, files, "发布动态内容成功。");
        } catch (Exception e) {
            log.error("发布动态内容出错", e);
            return CommonResult.failed("发布动态内容出错。");
        } finally {
            if (log.isDebugEnabled()) {
                log.debug("结束发布动态内容");
            }
        }
    }

	//检测用户发布动态定位是否发生改变
    @PostMapping(value = "/{id}/checkLocation.do")
    public CommonResult<Map<String, Object>> checkLocation(@PathVariable(name = "id") Long userIdLong, @RequestBody LocationDTO locationDTO) {
        Map<String, Object> data = new HashMap<>();
        try {
            if (log.isDebugEnabled()) {
                log.debug("开始检查");
            }
            boolean b = false;
            User user = this.userMapperReader.selectByPrimaryKey(userIdLong);
            if (user == null) {
                return CommonResult.validateFailed("检查失败，用户信息不存在。");
            }
            if (StrUtil.isAllEmpty(locationDTO.getIp(), locationDTO.getCountry(), locationDTO.getProvince(), locationDTO.getCity())) {
                return CommonResult.validateFailed("检查失败，客户端IP，发布动态定位（国）、（省）、（市）不能同时不传或者为空。");
            }
            b = this.dynamicService.checkLocationIsUpdate(locationDTO, user);
            data.put("CHANGED", b);
            return CommonResult.success(data, "检查成功。");
        } catch (UserAuthorizeException e) {
            log.error("检查出错，用户信息不存在或者用户状态异常", e);
            return CommonResult.failed(ResultCode.USER_IS_NOT_EXIST);
        } catch (Exception e) {
            log.error("检查出错", e);
            return CommonResult.failed("检查失败。");
        } finally {
            if (log.isDebugEnabled()) {
                log.debug("结束检查");
            }
        }
    }

    //更新用户发布动态定位
    @PostMapping(value = "/{id}/updateLocation.do")
    public CommonResult<Map<String, Object>> updateLocation(@PathVariable(name = "id") Long userIdLong, @RequestBody LocationDTO locationDTO) {
        Map<String, Object> data = new HashMap<>();
        String b = "ERROR";
        try {
            if (log.isDebugEnabled()) {
                log.debug("开始更新用户发布动态定位");
            }
            User user = this.userMapperReader.selectByPrimaryKey(userIdLong);
            if (user == null) {
                return CommonResult.validateFailed("更新失败，用户信息不存在");
            }
            String ipString = locationDTO.getIp();
            String countryString = locationDTO.getCountry();
            String provinceString = locationDTO.getProvince();
            String cityString = locationDTO.getCity();
            if (StrUtil.isAllEmpty(ipString, countryString, provinceString, cityString)) {
                return CommonResult.validateFailed("检查失败，客户端IP，发布动态定位（国）、（省）、（市）不能同时不传或者为空。");
            }
            if (StrUtil.isAllEmpty(countryString, provinceString, cityString)) {
                if (StrUtil.isNotEmpty(ipString)) {
                    LocationDTO locationDTO2 = new LocationDTO();
                    locationDTO2.setIp(ipString);
                    DynamicDTO dynamicDTO = this.dynamicService.getLocationByIp(locationDTO2);
                    if (dynamicDTO != null) {
                        countryString = dynamicDTO.getCountry();
                        provinceString = dynamicDTO.getProvince();
                        cityString = dynamicDTO.getCity();
                    }
                }
            }
            Dynamic record = null;
            DynamicExample example = new DynamicExample();
            example.setDistinct(true);
            example.createCriteria().andUserIdEqualTo(userIdLong);
            List<Dynamic> dynamicList = this.dynamicMapperReader.selectByExample(example);
            if (!dynamicList.isEmpty()) {
                record = dynamicList.get(0);
            }
            if (record != null) {
                if ((StrUtil.isNotEmpty(countryString) && StrUtil.isNotEmpty(record.getCountry()) || !countryString.equals(record.getCountry()))
                        || StrUtil.isNotEmpty(provinceString) && StrUtil.isNotEmpty(record.getProvince()) || !provinceString.equals(record.getProvince())
                        || StrUtil.isNotEmpty(cityString) && StrUtil.isNotEmpty(record.getCity()) || !countryString.equals(record.getCity())) {
                    record.setCountry(countryString);
                    record.setProvince(provinceString);
                    record.setCity(cityString);
                    record.setUpdateTime(new Date());
                    int result = dynamicMapperWriter.updateByPrimaryKeySelective(record);
                    if (result > 0) {
                        b = "OK";
                    }
                }
            }
            data.put("UPDATE", b);
            return CommonResult.success(data, "更新用户发布动态定位成功。");
        } catch (Exception e) {
            log.error("更新用户发布动态定位出错", e);
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
            @PathVariable(name = "id", required = true) Long userId, // 用户id
            @RequestParam(name = "pageNum", required = false, defaultValue = "1") int pageNum, // 当前页数，默认：当前第1页
            @RequestParam(name = "pageSize", required = false, defaultValue = "20") int pageSize) {// 每页条数，默认：每页20条)
        try {
            if (log.isDebugEnabled()) {
                log.debug("开始获取觅鹿界面发布的动态内容信息列表");
            }
            Map<String, Object> data;
            User user = this.userMapperReader.selectByPrimaryKey(userId);
            if (user == null) {
                return CommonResult.validateFailed("用户id参数校验不通过，用户信息不存在。");
            }
            String ip = user.getIp();
            String country = user.getCountry();
            String province = user.getProvince();
            String city = user.getCity();
            if (StrUtil.isNotEmpty(ip) && StrUtil.isAllEmpty(country, province, city)) {
                LocationDTO locationDTO = new LocationDTO();
                locationDTO.setIp(ip);
                DynamicDTO dynamicDTO = dynamicService.getLocationByIp(locationDTO);
                if (dynamicDTO != null) {
                    country = dynamicDTO.getCountry(); // 国家
                    province = dynamicDTO.getProvince();// 省份
                    city = dynamicDTO.getCity(); // 城市
                }
            }

            DynamicLocationDTO dynamicLocationDTO = new DynamicLocationDTO();
            String gender = user.getGender();
            //3、将“性别”加入筛选条件
            if (UserGenderEnum.Female.getCode().toString().equals(gender)) {
                dynamicLocationDTO.setGender(UserGenderEnum.Male.getCode().toString());
            }
            if (UserGenderEnum.Male.getCode().toString().equals(gender)) {
                dynamicLocationDTO.setGender(UserGenderEnum.Female.getCode().toString());
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
            // 1、将“黑名单列表”加入筛选条件
//            dynamicLocationDTO.setList(blackUserIdList);
//            // 第一步：筛选出不在黑名单列表，符合性别，年龄，或者星座的用户信息列表
//            List<User> userList = this.userMapperReader.selectByUserIdList(dynamicLocationDTO);
//            List<Long> userIdList = userList.stream().map(User::getId).collect(Collectors.toList());
//            userIdList.add(userId);
//            userIdList = CopyUtil.removeLongDuplicate(userIdList);
//            log.info("白名单用户列表userIdList={}", userIdList);
            DynamicInfoParam dynamicInfoParam = new DynamicInfoParam();
            dynamicInfoParam.setBlackRecordUserIdLongList(blackUserIdList);
            dynamicInfoParam.setCountryLocations(countryList);
            dynamicInfoParam.setProvinceLocations(provinceList);
            dynamicInfoParam.setCityLocations(cityList);
//          dynamicInfoParam.setAfterTimeHour(this.dynamicDefaultAgeProps.getTimeRangeAfterHour());
//            log.info("筛选条件dynamicInfoParam={}", dynamicInfoParam);
            data = this.dynamicService.getDynamicInfoData(userId, dynamicInfoParam, pageNum, pageSize);
            return CommonResult.success(data, "获取觅鹿界面发布的动态内容信息列表成功");
        } catch (Exception e) {
            log.error("获取觅鹿界面发布的动态内容信息列表出错", e);
        } finally {
            if (log.isDebugEnabled()) {
                log.debug("结束获取觅鹿界面发布的动态内容信息列表");
            }
        }
        return null;
    }

    // 筛选发布动态内容信息列表
    @GetMapping(value = "/{id}/screen.do")
    public CommonResult<Map<String, Object>> screen (
            @PathVariable(name = "id", required = true) Long userId, // 用户id
            @RequestParam(name = "pageNum", required = false, defaultValue = "1") int pageNum, // 当前页数，默认：当前第1页
            @RequestParam(name = "pageSize", required = false, defaultValue = "20") int pageSize, // 每页条数，默认：每页20条
            @RequestParam(name = "gender", required = false) String gender, // 性别，0->女生，1->男生，2->全部
            @RequestParam(name = "minAge", required = false, defaultValue = "16") Integer minAge, // 年龄范围（最小值），默认：16岁
            @RequestParam(name = "maxAge", required = false, defaultValue = "35") Integer maxAge, // 年龄范围（最大值），默认：35岁
            @RequestParam(name = "constellation", required = false) List<String> constellations, // 星座
            @RequestParam(name = "dataType", required = false, defaultValue = "0") String dataType, // 附件类型，默认：0，全部；0->全部，1->图片或者图片+文字，2->语音或者语音+文字
            @RequestParam(name = "provinceList", required = false) List<String> provinceList, // 发布动态定位（省份列表）
            @RequestParam(name = "cityList", required = false) List<String> cityList) { // 发布动态定位（城市列表）
        try {
            if (log.isDebugEnabled()) {
                log.debug("开始筛选发布动态内容信息列表");
            }
            
            // 1、校验用户id参数，根据用户id查询用户信息是否存在
            User user = this.userMapperReader.selectByPrimaryKey(userId);
            if (user == null) {
                return CommonResult.validateFailed("用户id参数校验不通过，用户信息不存在。");
            }
          
            // 2、校验性别参数，性别参数可选值，0->女生，1->男生
            if (StrUtil.isNotEmpty(gender)) {
                if (!(UserGenderEnum.Female.getCode().toString().equals(gender) || UserGenderEnum.Male.getCode().toString().equals(gender) || UserGenderEnum.ALL.getCode().toString().equals(gender))) {
                    return CommonResult.validateFailed("性别参数校验不通过，性别参数值非法。");
                }
            }
            
            // 3、 校验年龄最小值范围参数，是否大于等于16岁
            if (minAge < 0 || minAge < this.dynamicDefaultAgeProps.getMinRangeAge()) {
            	return CommonResult.validateFailed("年龄最小值范围参数校验不通过，最小值为16岁。");
			}
            
            // 4、 校验年龄最大值范围参数，是否小于等于150岁
            if (maxAge > this.dynamicDefaultAgeProps.getMaxRangeAge()) {
                return CommonResult.validateFailed("年龄最大值范围参数校验不通过，最大值为150岁。");
            }
            
            // 5、 校验星座数组参数，星座值是否在十二星座中选择
            ConstellationConstant constellationConstant = new ConstellationConstant();
            if (constellations != null && constellations.size() > 0) {
            	for (int i = 0; i < constellations.size(); i++) {
            		if (!constellationConstant.getConstellationList().contains(constellations.get(i))) {
                		return CommonResult.validateFailed("星座参数校验不通过，星座值非法。，星座值：{}" + constellations.get(i));
                	}
				}
			}
            // 6、校验附件类型参数，附件文件类型，0->全部，1->图片或图片+文字，2->语音或语音+文字
            if (StrUtil.isNotEmpty(dataType)) {
            	if ("0".equals(dataType)) {
            		dataType = null; //null 全部
				}
            	if ("1".equals(dataType)) {
            		dataType = "0";	// 0->只有图片
				}
            	if ("2".equals(dataType)) {
            		dataType = "1";	// 1->只有语音
				}
			}
            DynamicInfoParam dynamicInfoParam = new DynamicInfoParam();

            // 如果前端传过来的筛选条件为空，则按照默认筛选条件筛选，
            //（普通用户+VIP用户）默认条件一：性别，如果用户是男生，则筛选女生，反之，如果用户是女生，则筛选男生；
            if (StrUtil.isEmpty(gender)) {
                gender = user.getGender();
                if (UserGenderEnum.Female.getCode().toString().equals(gender)) {
                    gender = UserGenderEnum.Male.getGender();
                } else if (UserGenderEnum.Male.getCode().toString().equals(gender)) {
                    gender = UserGenderEnum.Female.getGender();
                }
            } else if (StrUtil.isNotEmpty(gender) && UserGenderEnum.ALL.getCode().toString().equals(gender)) {
                gender= null;
            }
            
            // 设置性别筛选条件
            dynamicInfoParam.setGender(gender);
            // dynamicLocationDTO.setGender(gender);

            // 如果前端传过来的年龄范围最小值为空或等于0
            //（普通用户+VIP用户）默认条件二：设置默认最小年龄范围->16岁
            // 设置年龄范围最小值筛选条件
            // dynamicLocationDTO.setMinAge(DateUtil.fomatDate(DateUtil.getBeforeYearByAge(minAge)));
            dynamicInfoParam.setMinAge(DateUtil.fomatDate(DateUtil.getBeforeYearByAge(minAge)));

            // 如果前端传过来的年龄范围最大值为空或等于0
            //（普通用户+VIP用户）默认条件二：设置默认最大年龄范围->35岁
            // 设置年龄范围最大值筛选条件
            // dynamicLocationDTO.setMaxAge(DateUtil.fomatDate(DateUtil.getBeforeYearByAge(maxAge)));
            // 7、 年龄最大值范围参数，是否大于等于50岁，是否是50+，则查询条件查询所有的大于最小年龄范围的用户
            if (maxAge >= 50) {
                dynamicInfoParam.setMaxAge(null);
            }
            dynamicInfoParam.setMaxAge(DateUtil.fomatDate(DateUtil.getBeforeYearByAge(maxAge)));
            
//            log.info("minAge={}, maxAge={}", minAge, maxAge);

            //（普通用户+VIP用户）默认条件四：设置发布时间配置
            // int timeAfterHour = this.dynamicDefaultAgeProps.getTimeRangeAfterHour(); // 发布时间配置（小时）
            // dynamicInfoParam.setAfterTimeHour(timeAfterHour);
            
            // 态发布时定位（国家、省份，城市），如果为空，则按照用户注册时的定位筛选
            List<String> countryList = new ArrayList<>();
        	countryList.add("中国");

            if (provinceList == null) {
            	provinceList = new ArrayList<>();
			}
            
            if (cityList == null) {
            	cityList = new ArrayList<>();
			}
            
            //默认条件三：按照定位地址列表进行筛选， 如果前端传输过来的参数或者省份，城市地址定位列表为空，则获取用户注册时候填写的信息
            String ip = user.getIp(); // 用户注册时获取的客户端IP
            String country = user.getCountry(); // 国家
            String province = user.getProvince(); // 省份
            String city = user.getCity(); // 城市
            if (StrUtil.isNotEmpty(ip)) {
                if (StrUtil.isAllEmpty(country, province, city)) {
                    LocationDTO locationDTOTmp = new LocationDTO();
                    locationDTOTmp.setIp(ip);
                    DynamicDTO dynamicDTO = this.dynamicService.getLocationByIp(locationDTOTmp);
                    country = dynamicDTO.getCountry();
                    province = dynamicDTO.getProvince();
                    city = dynamicDTO.getCity();
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
            if (constellations != null && constellations.size() > 0) {
            	dynamicInfoParam.setConstellations(constellations);
			}
            // 获取当前用户的黑名单用户列表
            BlacklistRecordExample example = new BlacklistRecordExample();
            example.setDistinct(true);
            example.setOrderByClause("id DESC, update_time DESC, create_time DESC");
            example.createCriteria().andOwnerUserIdEqualTo(userId);
            List<BlacklistRecord> blacklistRecordList = this.blacklistRecordMapperReader.selectByExample(example).stream().filter((BlacklistRecord b) -> (b.getStatus() % 2) != 0).collect(Collectors.toList());
            
            List<Long> blackUserIdList = blacklistRecordList.stream().map(BlacklistRecord::getBlackUserId).collect(Collectors.toList());
            // 1、将“黑名单列表”加入筛选条件
//            dynamicLocationDTO.setList(blackUserIdList);
            // 第一步：筛选出不在黑名单列表，符合性别，年龄，或者星座的用户信息列表
//            List<User> userList = this.userMapperReader.selectByUserIdList(dynamicLocationDTO);
//            List<Long> userIdList = new ArrayList<>();
//            for(User user2 : userList) {
//            	userIdList.add(user2.getId());
//            }
//            log.info("blackUserIdList={}", blackUserIdList);
            // 将自己加入到白名单或者筛选的用户列表中
            // userIdList.add(userId);
            blackUserIdList = CopyUtil.removeLongDuplicate(blackUserIdList);
//            log.info("userIdList={}", userIdList);
            dynamicInfoParam.setBlackRecordUserIdLongList(blackUserIdList);
            Map<String, Object> data = this.dynamicService.getDynamicInfoData(userId, dynamicInfoParam, pageNum, pageSize);
            return CommonResult.success(data, "筛选发布动态内容信息列表成功");
        } catch (Exception e) {
            log.error("筛选发布动态内容信息列表出错", e);
            return CommonResult.failed("筛选发布动态内容信息列表出错");
        } finally {
            if (log.isDebugEnabled()) {
                log.debug("结束筛选发布动态内容信息列表");
            }
        }
    }

    // 用户分享动态
    @PutMapping(value = "/{id}/share.do")
    public CommonResult<Map<String, Object>> shareDynamic(@PathVariable(name = "id", required = true) Long userId, 
    													  @RequestParam(name = "dynamicInfoId", required = true) Long dynamicInfoId,
    													  @RequestParam(name = "mode", required = true) String shareMode) {
        Map<String, Object> data = new ConcurrentHashMap<>();
        try {
            if (log.isDebugEnabled()) {
                log.debug("开始用户分享动态内容");
            }
            DynamicInfo dynamicInfo = this.dynamicInfoService.findDynamicInfoByPrimaryKey(dynamicInfoId);
            if (dynamicInfo == null) {
                return CommonResult.validateFailed("分享动态内容出错，动态内容不存在。");
            }
            ShareRecord shareRecord = new ShareRecord();
            shareRecord.setDynamicInfoId(dynamicInfoId);
            shareRecord.setMode(shareMode);
            shareRecord.setUserId(userId);
            int shareResult = shareRecordMapperWriter.insertSelective(shareRecord);
            
            OperateRecord operateRecord = new OperateRecord();
            operateRecord.setType(OperateRecordTypeEnum.shareDynamic.getCode());
            operateRecord.setUserId(userId);
            
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
                operateRecord.setStatus(OperateRecordStatusEnum.Fail.getCode().toString());
                this.operateRecordMapperWriter.insertSelective(operateRecord);
                return CommonResult.success(data, "分享动态内容失败。");
            }
        } catch (Exception e) {
            log.error("用户分享动态内容出错", e);
            data.put("SHARED", "ERROR");
            return CommonResult.failed(data, ResultCode.FAILED);
        } finally {
            if (log.isDebugEnabled()) {
                log.debug("结束用户分享动态内容");
            }
        }
    }

    // 用户点赞/取消点赞动态内容
    @PutMapping(value = "/{id}/likes.do")
    public CommonResult<Map<String, Object>> likes(@PathVariable(name = "id", required = true) Long userId,
                                                   @RequestParam(name = "dynamicInfoId", required = true) Long dynamicInfoId,
                                                   @RequestParam(name = "type", required = true) String type) {
        Map<String, Object> data = new ConcurrentHashMap<>();
        data.put("LIKED", "ERROR");
        try {
            if (log.isDebugEnabled()) {
                log.debug("开始点赞当前动态内容");
            }
            User user = this.userMapperReader.selectByPrimaryKey(userId);
            if (user == null) {
                return CommonResult.success(data, "点赞当前动态内容出错，用户信息不存在。");
            }
            DynamicInfo dynamicInfo = this.dynamicInfoService.findDynamicInfoByPrimaryKey(dynamicInfoId);
            if (dynamicInfo == null) {
                return CommonResult.success(data, "点赞当前动态内容出错，动态内容不存在。");
            }
            Long publishUserId = dynamicInfo.getUserId();
            User publishUser = this.userMapperReader.selectByPrimaryKey(publishUserId);
            if (publishUser == null) {
            	return CommonResult.success(data, "点赞当前动态内容出错，发布者用户信息不存在。");
			}
            LikeRecord likeRecord = this.likeRecordService.findByUserIdAndDynamicInfoId(userId, dynamicInfoId);
            // 取消点赞
            if (LikeStatusEnum.NO.getType().equals(type)) {
                if (likeRecord == null) {
                    return CommonResult.success(data, "取消点赞，点赞记录信息不存在。");
                }
                int result = this.likeRecordService.deleteByUserIdAndDynamicInfoId(userId, dynamicInfo);
                if (result > 0) {
                    data.put("LIKED", "OK");
                    return CommonResult.success(data, "取消点赞，取消成功。");
                }
            }
            // 开始点赞
            if (LikeStatusEnum.YES.getType().equals(type)) {
                if (likeRecord == null) {
                	String content = user.getNickName() + "赞了你的动态" + dynamicInfo.getContent();//消息内容
                    int result = this.likeRecordService.createByUserIdAndDynamicInfoId(content, userId, dynamicInfo);
                    if (result > 0) {
                        data.put("LIKED", "OK");
                        String title = "互动消息";//消息标题
                        Map<String, String> extras = new HashMap<>();
                        PushBean pushBean = new PushBean();
                		pushBean.setAlert(content);
                		pushBean.setTitle(title);
                		pushBean.setExtras(extras);
                		this.jiGuangPushService.pushAndroid(pushBean, publishUser.getReserveColumn03());
                        return CommonResult.success(data, "创建点赞，点赞成功。");
                    }
                } else {
                    return CommonResult.success(data, "创建点赞，点赞失败，点赞记录不存在。");
                }
            }
        } catch (Exception e) {
            log.error("点赞当前动态内容出现错误", e);
            return CommonResult.failed("点赞当前动态内容出现错误。");
        } finally {
            if (log.isDebugEnabled()) {
                log.debug("结束点赞当前动态内容");
            }
        }
        return null;
    }

    // 用户申请加微信
    @PutMapping(value = "/{id}/application.do")
    public CommonResult<Map<String, Object>> application(@PathVariable(name = "id", required = true) Long userId,
                                                         @RequestParam(name = "dynamicInfoId", required = true) Long dynamicInfoId,
                                                         @RequestParam(name = "message", required = false) String message) {
        Map<String, Object> data = new ConcurrentHashMap<>();
        data.put("APPLICATION", "ERROR");
        try {
            if (log.isDebugEnabled()) {
                log.debug("开始申请加微信");
            }
            DynamicInfo dynamicInfo = this.dynamicInfoService.findDynamicInfoByPrimaryKey(dynamicInfoId);
            if (dynamicInfo == null) {
            	return CommonResult.failed(data, ResultCode.DYNAMIC_IS_NOT_EXIST);
            }
            Long publishUserId = dynamicInfo.getUserId();//动态内容拥有者或者发动态的用户id
            User publishUser = this.userMapperReader.selectByPrimaryKey(publishUserId);
            if (publishUser == null) {
            	return CommonResult.failed(data, ResultCode.PUBLISH_USER_IS_NOT_EXIST);
			}
            if (publishUserId.equals(userId)) {
            	return CommonResult.failed(data, ResultCode.PUBLISH_USER_IS_VALID);
			}
            ApplicationSetting applicationSetting = this.applicationSettingService.findApplication();
            int times = 0;
            if (applicationSetting != null) {
                times = applicationSetting.getTimes();
            }
            User recipientUser = this.userMapperReader.selectByPrimaryKey(userId);
            if (recipientUser == null) {
                return CommonResult.failed(data, ResultCode.RECIPIENT_USER_IS_NOT_EXIST);
            }
            //TODO 是否需要判断一天只能申请加同一个人的微信一次，加不同的人的微信按照设置，对方没有回复是不能继续发送的。
            DynamicInfo dynamicInfo2 = this.dynamicInfoService.findDynamicInfoByPrimaryKey(dynamicInfoId);
            dynamicInfo2.getUserId();
            if (UserGradeEnum.VIP0.getGrade().equals(publishUser.getGrade())) {
                int timesResult = this.applicationRecordService.findApplicationRecordCountByUserId(userId);
                if (timesResult >= times) {
                    return CommonResult.failed(data, ResultCode.TIMES_OVERRUN);
                }
            }
            ApplicationRecord applicationRecord = new ApplicationRecord();
            applicationRecord.setDynamicInfoId(dynamicInfoId);
            applicationRecord.setUserId(userId);
            if (StrUtil.isEmpty(message)) {
                message = "申请加微信，麻烦通过下。";
            }
            int rowResult = this.applicationRecordService.saveApplicationRecord(dynamicInfo, applicationRecord, message);
            String msg = "";
            if (rowResult > 0) {
                data.put("APPLICATION", "OK");
                msg = "申请加微信成功。";
                Long sendUserId = applicationRecord.getUserId();
                User user2 = this.userMapperReader.selectByPrimaryKey(sendUserId);
                assert user2 != null;
                String title = user2.getNickName();//消息标题
                Map<String, String> extras = new HashMap<>();
                PushBean pushBean = new PushBean();
        		pushBean.setAlert(message);
        		pushBean.setTitle(title);
        		pushBean.setExtras(extras);
        		this.jiGuangPushService.pushAndroid(pushBean, publishUser.getReserveColumn03());
            } else {
                data.put("APPLICATION", "ERROR");
                msg = "申请加微信失败。";
            }
            return CommonResult.success(data, msg);
        } catch (Exception e) {
            log.error("申请加微信出现错误", e);
            return CommonResult.failed("申请加微信出现错误。");
        } finally {
            if (log.isDebugEnabled()) {
                log.debug("结束申请加微信");
            }
        }
    }

    // 用户删除动态内容
    @PutMapping(value = "/{id}/delete.do")
    public CommonResult<Map<String, Object>> delete(@PathVariable(name = "id", required = true) Long userId,
                                                    @RequestParam(name = "dynamicInfoId", required = true) Long dynamicInfoId) {
        Map<String, Object> data = new ConcurrentHashMap<>();
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
            String message = "";
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
            return CommonResult.success(data, message);
        } catch (Exception e) {
            log.error("删除动态内容出现错误", e);
            return CommonResult.failed("删除动态内容出现错误。");
        } finally {
            if (log.isDebugEnabled()) {
                log.debug("结束删除动态内容");
            }
        }
    }
    
    // 分页获取用户自己发布的所有动态内容列表
    @GetMapping(value = "/{id}/mylist.do")
    public CommonResult<Map<String, Object>> mylist(@PathVariable(name = "id", required = true) Long userId,
                                                    @RequestParam(name = "pageNum", required = false, defaultValue = "1") int pageNum,
                                                    @RequestParam(name = "pageSize", required = false, defaultValue = "20") int pageSize) {
        try {
            if (log.isDebugEnabled()) {
                log.debug("开始分页获取用户自己发布的所有动态内容列表");
            }
            User user = this.userMapperReader.selectByPrimaryKey(userId);
            if (user == null) {
                return CommonResult.validateFailed("获取自己发布的所有动态内容列表出错，用户信息不存在。");
            }
            return CommonResult.success(this.dynamicInfoService.getMyDynamicInfoDataList(userId, pageNum, pageSize), "分页获取用户自己发布的所有动态内容列表成功。");
        } catch (Exception e) {
            log.error("分页获取用户自己发布的所有动态内容列表出现错误", e);
            return CommonResult.failed("分页获取用户自己发布的所有动态内容列表出现错误。");
        } finally {
            if (log.isDebugEnabled()) {
                log.debug("结束分页获取用户自己发布的所有动态内容列表");
            }
        }
    }
}