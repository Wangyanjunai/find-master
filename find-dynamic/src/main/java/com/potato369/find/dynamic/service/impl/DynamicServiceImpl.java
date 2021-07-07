package com.potato369.find.dynamic.service.impl;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.Header;
import cn.hutool.http.HttpRequest;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.potato369.find.common.api.CommonResult;
import com.potato369.find.common.constants.MunicipalityConstant;
import com.potato369.find.common.dto.DynamicDTO;
import com.potato369.find.common.dto.LocationDTO;
import com.potato369.find.common.enums.*;
import com.potato369.find.common.utils.CopyUtil;
import com.potato369.find.common.utils.DateUtil;
import com.potato369.find.common.utils.ErrorMessageUtil;
import com.potato369.find.common.utils.IPLocationUtil;
import com.potato369.find.common.vo.DynamicInfoVO;
import com.potato369.find.dynamic.config.props.AliyunProps;
import com.potato369.find.dynamic.config.props.BaiduProps;
import com.potato369.find.dynamic.config.props.ProjectUrlProps;
import com.potato369.find.dynamic.service.DynamicService;
import com.potato369.find.mbg.mapper.*;
import com.potato369.find.mbg.model.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Service
@Transactional
public class DynamicServiceImpl implements DynamicService {

    private DynamicMapper dynamicMapperReader;

    private DynamicMapper dynamicMapperWriter;

    private DynamicInfoMapper dynamicInfoMapperWriter;

    private DynamicInfoMapper dynamicInfoMapperReader;

    private AttacheInfoMapper attacheInfoMapperWriter;

    private ApplicationRecordMapper applicationRecordMapperReader;

    private LikeRecordMapper likeRecordMapperReader;

    private BaiduProps baiduProps;

    private ProjectUrlProps projectUrlProps;

    private AliyunProps aliyunProps;

    private OperateRecordMapper operateRecordMapperWriter;

    @Autowired
    public void setDynamicMapperReader(DynamicMapper dynamicMapperReader) {
        this.dynamicMapperReader = dynamicMapperReader;
    }

    @Autowired
    public void setDynamicMapperWriter(DynamicMapper dynamicMapperWriter) {
        this.dynamicMapperWriter = dynamicMapperWriter;
    }

    @Autowired
    public void setDynamicInfoMapperWriter(DynamicInfoMapper dynamicInfoMapperWriter) {
        this.dynamicInfoMapperWriter = dynamicInfoMapperWriter;
    }

    @Autowired
    public void setDynamicInfoMapperReader(DynamicInfoMapper dynamicInfoMapperReader) {
        this.dynamicInfoMapperReader = dynamicInfoMapperReader;
    }

    @Autowired
    public void setAttacheInfoMapperWriter(AttacheInfoMapper attacheInfoMapperWriter) {
        this.attacheInfoMapperWriter = attacheInfoMapperWriter;
    }

    @Autowired
    public void setApplicationRecordMapperReader(ApplicationRecordMapper applicationRecordMapperReader) {
        this.applicationRecordMapperReader = applicationRecordMapperReader;
    }

    @Autowired
    public void setLikeRecordMapperReader(LikeRecordMapper likeRecordMapperReader) {
        this.likeRecordMapperReader = likeRecordMapperReader;
    }

    @Autowired
    public void setBaiduProps(BaiduProps baiduProps) {
        this.baiduProps = baiduProps;
    }

    @Autowired
    public void setProjectUrlProps(ProjectUrlProps projectUrlProps) {
        this.projectUrlProps = projectUrlProps;
    }

    @Autowired
    public void setAliyunProps(AliyunProps aliyunProps) {
        this.aliyunProps = aliyunProps;
    }

    @Autowired
    public void setOperateRecordMapperWriter(OperateRecordMapper operateRecordMapperWriter) {
        this.operateRecordMapperWriter = operateRecordMapperWriter;
    }


    //保存动态内容信息
    @Override
    @Transactional(readOnly = false)
    public int save(User user, DynamicDTO dynamicDTO, MultipartFile[] files) throws Exception {
        OperateRecord operateRecord = new OperateRecord();
        operateRecord.setUserId(user.getId());
        operateRecord.setStatus(OperateRecordStatusEnum.Fail.getCode().toString());
        operateRecord.setType(OperateRecordTypeEnum.ReleaseDynamic.getCode());
        Long userIdLong = dynamicDTO.getUserId();
        String nickname1 = user.getNickName();
        String imei2 = dynamicDTO.getImei();
        String model2 = dynamicDTO.getModel();
        String sysName2 = dynamicDTO.getSysName();
        String sysCode2 = dynamicDTO.getSysCode();
        String networkMode2 = dynamicDTO.getNetworkMode();
        String ip2 = dynamicDTO.getIp();
        String province2 = dynamicDTO.getProvince();
        String city2 = dynamicDTO.getCity();
        String district2 = dynamicDTO.getDistrict();
        String other2 = dynamicDTO.getOther();
        //如果前端传输过来的动态信息这些字段都为空，则复制用户注册时填写的信息到动态信息表
        if (StrUtil.isAllEmpty(imei2, model2, sysName2, sysCode2, networkMode2)) {
            dynamicDTO.setImei(user.getImei());
            dynamicDTO.setModel(user.getModel());
            dynamicDTO.setSysName(user.getSysName());
            dynamicDTO.setSysCode(user.getSysCode());
            dynamicDTO.setNetworkMode(user.getNetworkMode());
        }
        if (StrUtil.isNotEmpty(ip2)) {
            if (StrUtil.isAllEmpty(province2, city2, district2, other2)) {
                LocationDTO locationDTO2 = IPLocationUtil.getLocationByAliyunIP(StrUtil.trimToEmpty(this.aliyunProps.getAppcode()), StrUtil.trimToEmpty(this.aliyunProps.getUrl()), ip2);
                dynamicDTO.setCountry(locationDTO2.getCountry());
                dynamicDTO.setProvince(locationDTO2.getProvince());
                dynamicDTO.setCity(locationDTO2.getCity());
                dynamicDTO.setDistrict(locationDTO2.getDistrict());
                dynamicDTO.setOther(locationDTO2.getOther());
                dynamicDTO.setLongitude(locationDTO2.getLongitude());
                dynamicDTO.setLatitude(locationDTO2.getLatitude());
            }
        }
        int result1;
        int result2 = 0;
        int result3 = 0;
        int result4;
        Dynamic dynamic = this.findDynamicByUserId(userIdLong, dynamicDTO.getCountry(), dynamicDTO.getProvince(), dynamicDTO.getCity());
        if (dynamic != null) {
            String[] nullPropertyNames = CopyUtil.getNullPropertyNames(dynamicDTO);
            BeanUtils.copyProperties(dynamicDTO, dynamic, nullPropertyNames);
            dynamic.setNickName(nickname1);
            dynamic.setUserId(userIdLong);
            dynamic.setUpdateTime(new Date());
            result1 = this.dynamicMapperWriter.updateByPrimaryKeySelective(dynamic);
        } else {
            dynamic = new Dynamic();
            String[] nullPropertyNames = CopyUtil.getNullPropertyNames(dynamicDTO);
            BeanUtils.copyProperties(dynamicDTO, dynamic, nullPropertyNames);
            dynamic.setNickName(nickname1);
            dynamic.setUserId(userIdLong);
            result1 = this.dynamicMapperWriter.insertSelective(dynamic);
        }
        if (result1 > 0) {
            DynamicInfo dynamicInfo = new DynamicInfo();
            String[] nullPropertyNames = CopyUtil.getNullPropertyNames(dynamicDTO);
            BeanUtils.copyProperties(dynamicDTO, dynamicInfo, nullPropertyNames);
            dynamicInfo.setDynamicId(dynamic.getId());
            dynamicInfo.setUserId(userIdLong);
            if (files != null && files.length > 0) {
                String attacheInfoDataType = dynamicDTO.getAttacheInfoDataType();
                StringBuffer filePath = new StringBuffer().append(StrUtil.trimToNull(this.projectUrlProps.getUploadRes())).append(StrUtil.trimToNull(this.projectUrlProps.getProjectName()));
                if (Objects.equals(AttacheInfoDataTypeEnum.Image.getCodeStr(), attacheInfoDataType)) {
                    filePath.append(StrUtil.trimToNull(this.projectUrlProps.getResDynamicImageFile()));
                }
                if (Objects.equals(AttacheInfoDataTypeEnum.Audio.getCodeStr(), attacheInfoDataType)) {
                    filePath.append(StrUtil.trimToNull(this.projectUrlProps.getResDynamicVoiceFile()));
                }
                dynamicInfo.setAttacheNumber(files.length);
                AttacheInfo attacheInfo = new AttacheInfo();
                attacheInfo.setDynamicInfoBy(dynamicInfo.getId());// 动态内容id
                attacheInfo.setDataType(attacheInfoDataType); // 附件类型
                List<File> files2 = new ArrayList<>();
                String fileString = new StringBuffer().append(userIdLong).append("/")
                        .append(DatePattern.PURE_DATE_FORMAT.format(new Date())).append("/")
                        .append(System.currentTimeMillis())
                        .append("/").toString();
                for (MultipartFile multipartFile : files) {
                    byte[] bytes = multipartFile.getBytes();
                    StringBuffer stringBuffer = new StringBuffer();
                    stringBuffer.append(filePath).append("/").append(fileString);
                    File path1 = new File(stringBuffer.toString());
                    if (!path1.exists()) {
                        path1.mkdirs();
                    }
                    String filename = multipartFile.getOriginalFilename();
                    String filenameSuffix = FileUtil.getSuffix(filename);
                    filename = new StringBuffer().append(UUID.randomUUID()).append(".").append(filenameSuffix).toString();
                    filename = new StringBuffer().append(filename).toString();
                    Path path = Paths.get(stringBuffer.append(filename).toString());
                    if (!multipartFile.isEmpty()) {
                        path.toFile();
                        File file = new File(path.toString());
                        files2.add(file);
                        Files.write(path, bytes);
                    }
                }
                String fileNames = ErrorMessageUtil.fileNameBuild(files2, fileString);
                attacheInfo.setFileName(fileNames);// 附件名称
                result2 = this.attacheInfoMapperWriter.insertSelective(attacheInfo);
            }
            result3 = this.dynamicInfoMapperWriter.insertSelective(dynamicInfo);
            if (result3 > 0) {
                operateRecord.setStatus(OperateRecordStatusEnum.Success.getCode().toString());
            }
        }
        result4 = this.operateRecordMapperWriter.insertSelective(operateRecord);
        return result1 + result2 + result3 + result4;
    }

    //筛选动态内容信息
    @Override
    @Transactional(readOnly = true)
    public void find(Long userId) {
    }

    @Override
    @Transactional
    public CommonResult<Map<String, Object>> updateDynamic(LocationDTO locationDTO, DynamicDTO dynamicDTO) throws Exception {
        int row = this.updateLocation(locationDTO, dynamicDTO);
        if (row > 0) {
            return CommonResult.success(null, "更新动态定位地址成功");
        }
        return CommonResult.failed("更新动态定位地址失败");
    }

    //更新动态内容信息
    @Override
    @Transactional(readOnly = false)
    public int update(User user, DynamicDTO dynamicDTO, MultipartFile[] files) throws Exception {
        String attacheInfoDataType = dynamicDTO.getAttacheInfoDataType();
        StringBuilder filePath = new StringBuilder().append(StrUtil.trimToNull(this.projectUrlProps.getUploadRes())).append(StrUtil.trimToNull(this.projectUrlProps.getProjectName()));
        if (Objects.equals(AttacheInfoDataTypeEnum.Image.getCodeStr(), attacheInfoDataType)) {
            filePath.append(StrUtil.trimToNull(this.projectUrlProps.getResDynamicImageFile()));
        }
        if (Objects.equals(AttacheInfoDataTypeEnum.Audio.getCodeStr(), attacheInfoDataType)) {
            filePath.append(StrUtil.trimToNull(this.projectUrlProps.getResDynamicVoiceFile()));
        }
        Long userIdLong = dynamicDTO.getUserId();
        Dynamic dynamic = this.findDynamicByUserId(userIdLong, dynamicDTO.getCountry(), dynamicDTO.getProvince(), dynamicDTO.getCity());
        if (user != null) {
            if (dynamic != null) {
                String[] nullPropertyNames = CopyUtil.getNullPropertyNames(dynamicDTO);
                BeanUtils.copyProperties(dynamicDTO, dynamic, nullPropertyNames);
                dynamic.setNickName(user.getNickName());
                dynamic.setUserId(user.getId());
                this.dynamicMapperWriter.updateByPrimaryKeySelective(dynamic);
            } else {
                dynamic = new Dynamic();
                String[] nullPropertyNames = CopyUtil.getNullPropertyNames(dynamicDTO);
                BeanUtils.copyProperties(dynamicDTO, dynamic, nullPropertyNames);
                dynamic.setNickName(user.getNickName());
                dynamic.setUserId(user.getId());
                this.dynamicMapperWriter.insertSelective(dynamic);
            }
        }
        DynamicInfo dynamicInfo = new DynamicInfo();
        dynamicInfo.setAttacheType(attacheInfoDataType);
        dynamicInfo.setAttacheNumber(files.length);
        dynamicInfo.setDynamicId(dynamic.getId());
        dynamicInfo.setContent(dynamicDTO.getContent());
        dynamicInfo.setPublicStatus(dynamicDTO.getPublicStatus());
        dynamicInfo.setUserId(userIdLong);
        this.dynamicInfoMapperWriter.insertSelective(dynamicInfo);
        AttacheInfo attacheInfo = new AttacheInfo();
        attacheInfo.setDynamicInfoBy(dynamicInfo.getId());// 动态内容id
        attacheInfo.setDataType(attacheInfoDataType); // 附件类型
        List<File> files2 = new ArrayList<>();
        String fileString = userIdLong + "/" +
                DatePattern.PURE_DATE_FORMAT.format(new Date()) + "/" +
                System.currentTimeMillis() +
                "/";
        for (MultipartFile multipartFile : files) {
            byte[] bytes = multipartFile.getBytes();
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(filePath).append("/").append(fileString);
            File path1 = new File(stringBuffer.toString());
            if (!path1.exists()) {
                path1.mkdirs();
            }
            String filename = UUID.randomUUID() + "." + FileUtil.getSuffix(multipartFile.getOriginalFilename());
            Path path = Paths.get(stringBuffer.append(filename).toString());
            if (!multipartFile.isEmpty()) {
                path.toFile();
                File file = new File(path.toString());
                files2.add(file);
                Files.write(path, bytes);
            }
        }
        String fileNames = ErrorMessageUtil.fileNameBuild(files2, fileString);
        attacheInfo.setFileName(fileNames);// 附件名称
        this.attacheInfoMapperWriter.insertSelective(attacheInfo);
        return 0;
    }

    //获取定位地址
    @Override
    @Transactional(readOnly = true)
    public DynamicDTO getLocation(LocationDTO locationDTO, User user) {
        DynamicDTO dynamicDTOTmp = this.getLocationByIp(locationDTO);
        String country2 = dynamicDTOTmp.getCountry();
        String province2 = dynamicDTOTmp.getProvince();
        String city2 = dynamicDTOTmp.getCity();
        if (!StrUtil.isAllEmpty(country2, province2, city2)) {
            dynamicDTOTmp.setCountry(country2);
            dynamicDTOTmp.setProvince(province2);
            dynamicDTOTmp.setCity(city2);
        } else {
            dynamicDTOTmp.setCountry(user.getCountry());
            dynamicDTOTmp.setProvince(user.getProvince());
            dynamicDTOTmp.setCity(user.getCity());
        }
        return dynamicDTOTmp;
    }

    //根据客户端ip获取定位地址
    @Override
    @Transactional(readOnly = true)
    public DynamicDTO getLocationByIp(LocationDTO locationDTO) {
        DynamicDTO dynamicDTOTmp = new DynamicDTO();
        String country2 = locationDTO.getCountry();
        String province2 = locationDTO.getProvince();
        String city2 = locationDTO.getCity();
        String district2 = locationDTO.getDistrict();
        String other2 = locationDTO.getOther();
        String ip2 = locationDTO.getIp();
        if (StrUtil.isAllEmpty(country2, province2, city2, district2, other2)) {
            if (StrUtil.isNotEmpty(ip2)) {
                Map<String, Object> params = new HashMap<>();
                String ak = StrUtil.trimToNull(this.baiduProps.getAk());
                params.put("ak", ak);
                params.put("ip", ip2);
                String urlString = StrUtil.trimToNull(this.baiduProps.getUrl());
                String result;
                try {
                    result = HttpRequest.get(urlString)
                            .header(Header.USER_AGENT, "HuTool http")
                            .charset(CharsetUtil.CHARSET_UTF_8)
                            .form(params)
                            .timeout(2000)
                            .execute()
                            .body();
                    JSONObject jsonbody = null;
                    if (StrUtil.isNotEmpty(result)) {
                        if (result != null) {
                            jsonbody = JSON.parseObject(result);
                        }
                    }
                    if (jsonbody != null) {
                        if (jsonbody.getIntValue("status") == 0) {
                            String address = jsonbody.getString("address");
                            String[] addr = StrUtil.split(address, "|");
                            if (addr != null && addr.length > 0) {
                                String countryString = addr[0];
                                if ("CN".equals(countryString)) {
                                    dynamicDTOTmp.setCountry("中国");
                                }
                                dynamicDTOTmp.setProvince(addr[1] + "省");
                                dynamicDTOTmp.setCity(addr[2] + "市");
                                if (StrUtil.isNotEmpty(dynamicDTOTmp.getCity())) {
                                    MunicipalityConstant contant = new MunicipalityConstant();
                                    List<String> municipalityList = contant.getMunicipalityList();
                                    if (municipalityList.contains(dynamicDTOTmp.getCity())) {
                                        dynamicDTOTmp.setProvince(addr[1] + "市");
                                        dynamicDTOTmp.setCity(addr[2] + "市");
                                    }
                                }
                            }
                        }
                    }
                } catch (Exception e) {
                    log.error("调用百度普通IP定位接口失败", e);
                }
            }
        } else {
            dynamicDTOTmp.setIp(locationDTO.getIp());
            dynamicDTOTmp.setCountry(locationDTO.getCountry());
            dynamicDTOTmp.setProvince(locationDTO.getProvince());
            dynamicDTOTmp.setCity(locationDTO.getCity());
        }
        return dynamicDTOTmp;
    }

    @Override
    @Transactional(readOnly = true)
    public boolean checkLocationIsUpdate(LocationDTO locationDTO, User user) {
        String ip = locationDTO.getIp();
        String country = locationDTO.getCountry();
        String province = locationDTO.getProvince();
        String city = locationDTO.getCity();
        DynamicDTO dynamicDTOTmp = DynamicDTO.builder().build();
        if (StrUtil.isAllNotEmpty(ip, country, province, city)) {
            dynamicDTOTmp.setCountry(country);
            dynamicDTOTmp.setProvince(province);
            dynamicDTOTmp.setCity(city);
        } else {
            if (StrUtil.isNotEmpty(ip)) {
                dynamicDTOTmp = this.getLocation(locationDTO, user);
            }
            if (StrUtil.isAllNotEmpty(country, province, city)) {
                dynamicDTOTmp.setCountry(country);
                dynamicDTOTmp.setProvince(province);
                dynamicDTOTmp.setCity(city);
            }
        }
        Long userIdLong = user.getId();
        // 上一次发布动态定位地址
        DynamicLocation dynamicLocation = this.dynamicMapperReader.selectByUserId(userIdLong);
        if (dynamicDTOTmp != null && dynamicLocation != null) {
            if (StrUtil.isNotEmpty(dynamicLocation.getCountry())
                    && StrUtil.isNotEmpty(dynamicDTOTmp.getCountry())
                    && StrUtil.isNotEmpty(dynamicLocation.getProvince())
                    && StrUtil.isNotEmpty(dynamicDTOTmp.getProvince())
                    && StrUtil.isNotEmpty(dynamicLocation.getCity())
                    && StrUtil.isNotEmpty(dynamicDTOTmp.getCity())) {
                return !dynamicDTOTmp.getCountry().equals(dynamicLocation.getCountry())
                        || !dynamicDTOTmp.getProvince().equals(dynamicLocation.getProvince())
                        || !dynamicDTOTmp.getCity().equals(dynamicLocation.getCity());
            } else {
                return true;
            }
        } else {
            return dynamicDTOTmp != null || dynamicLocation != null;
        }
    }

    @Override
    @Transactional(readOnly = false)
    public Integer updateLocation(LocationDTO locationDTO, DynamicDTO dynamicDTO) {
        if (locationDTO != null && dynamicDTO != null) {
            Long dynamicInfoIdLong = dynamicDTO.getDynamicInfoId();
            Long userIdLong = locationDTO.getUserId();
            DynamicInfoExample dynamicInfoExample = new DynamicInfoExample();
            dynamicInfoExample.setDistinct(true);
            dynamicInfoExample.createCriteria().andUserIdEqualTo(userIdLong).andIdEqualTo(dynamicInfoIdLong);
            List<DynamicInfo> dynamicInfoList = this.dynamicInfoMapperReader.selectByExample(dynamicInfoExample);
            if (!dynamicInfoList.isEmpty()) {
                DynamicInfo dynamicInfo = dynamicInfoList.get(0);
                if (dynamicInfo != null) {
                    Long dynamicId = dynamicInfo.getDynamicId();
                    Dynamic dynamic = this.dynamicMapperReader.selectByPrimaryKey(dynamicId);
                    if (dynamic != null) {
                        dynamic.setCountry(locationDTO.getCountry());
                        dynamic.setProvince(locationDTO.getProvince());
                        dynamic.setCity(locationDTO.getCity());
                        dynamic.setDistrict(locationDTO.getDistrict());
                        dynamic.setOther(locationDTO.getOther());
                        return this.dynamicMapperWriter.updateByPrimaryKeySelective(dynamic);
                    }
                }
            }
        }
        return 0;
    }

    @Override
    @Transactional(readOnly = true)
    public Map<String, Object> getDynamicInfoData(Long userId, DynamicInfoParam dynamicInfoParam, Integer pageNum, Integer pageSize) {
        Map<String, Object> data = new ConcurrentHashMap<>();
        log.info("dynamicInfoParam={}", dynamicInfoParam);
        final PageInfo<DynamicInfoData> listPageInfo = PageHelper.startPage(pageNum, pageSize)
                .doSelectPageInfo(() -> this.dynamicInfoMapperReader.selectDynamicInfoData(dynamicInfoParam));
        data.put("totalPage", listPageInfo.getPages());
        List<DynamicInfoData> list = listPageInfo.getList();
        List<DynamicInfoVO> list2 = new ArrayList<>();
        if (!list.isEmpty()) {
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
                if (likeRecordList != null && !likeRecordList.isEmpty()) {
                    LikeRecord likeRecord = likeRecordList.get(0);
                    if (likeRecord != null) {
                        if (likeRecord.getStatus().equals(LikeStatusEnum.YES.getType())) {
                            dynamicInfoVO.setLikeStatus(true);
                        }
                        if (likeRecord.getStatus().equals(LikeStatusEnum.NO.getType())) {
                            dynamicInfoVO.setLikeStatus(false);
                        }
                    }
                } else {
                    dynamicInfoVO.setLikeStatus(false);
                }

                if (PublicStatusEnum.NOPublic.getCode().toString().equals(dynamicInfoData.getPublishStatus())) {
                    dynamicInfoVO.setAddress(null);
                }
                if (PublicStatusEnum.Public.getCode().toString().equals(dynamicInfoData.getPublishStatus())) {
                    StringBuilder stringBuilder = new StringBuilder();
                    if (!"省".equals(dynamicInfoData.getProvince())) {
                        stringBuilder.append(dynamicInfoData.getProvince());
                    }
                    stringBuilder.append(dynamicInfoData.getCity());
                    dynamicInfoVO.setAddress(stringBuilder.toString());
                }
                String[] fileNameList01 = StrUtil.split(dynamicInfoData.getFileName(), "||");
                List<String> fileNameList02 = new ArrayList<>(Arrays.asList(fileNameList01));
                List<String> fileNameList03 = new ArrayList<>();
                for (String fileName : fileNameList02) {
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append(StrUtil.trimToNull(this.projectUrlProps.getResDomain()))
                            .append(StrUtil.trimToNull(this.projectUrlProps.getProjectName()));
                    if (StrUtil.isNotEmpty(dynamicInfoData.getAttacheFileDataType()) && AttacheInfoDataTypeEnum.Image.getCode().toString().equals(dynamicInfoData.getAttacheFileDataType())) {
                        stringBuilder.append(StrUtil.trimToNull(this.projectUrlProps.getResDynamicImageFile()));
                    }
                    if (StrUtil.isNotEmpty(dynamicInfoData.getAttacheFileDataType()) && AttacheInfoDataTypeEnum.Audio.getCode().toString().equals(dynamicInfoData.getAttacheFileDataType())) {
                        stringBuilder.append(StrUtil.trimToNull(this.projectUrlProps.getResDynamicVoiceFile()));
                    }
                    stringBuilder.append(fileName);
                    fileNameList03.add(stringBuilder.toString());
                    dynamicInfoVO.setFileName(fileNameList03);
                }
                list2.add(dynamicInfoVO);
            }
        }
        data.put("list", list2);
        return data;
    }

    /**
     * 根据用户id查询动态信息
     *
     * @param userId 用户id
     * @return 动态信息
     */
    @Override
    @Transactional(readOnly = true)
    public Dynamic findDynamicByUserId(Long userId, String country, String province, String city) {
        DynamicExample example = new DynamicExample();
        example.setDistinct(true);
        example.setOrderByClause("id ASC, create_time DESC, update_time DESC");
        if (StrUtil.isAllNotEmpty(country, province, city)) {
            example.createCriteria().andUserIdEqualTo(userId).andCountryEqualTo(country).andProvinceEqualTo(province).andCityEqualTo(city);
        } else {
            example.createCriteria().andUserIdEqualTo(userId);
        }
        List<Dynamic> dynamicList = this.dynamicMapperReader.selectByExample(example);
        Dynamic dynamic = null;
        if (!CollectionUtils.isEmpty(dynamicList)) {
            dynamic = dynamicList.get(0);
        }
        return dynamic;
    }
}
