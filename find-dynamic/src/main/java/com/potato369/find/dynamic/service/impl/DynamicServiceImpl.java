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
import com.potato369.find.common.utils.*;
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

    private AttacheInfoMapper attacheInfoMapperReader;

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

    @Autowired
    public void setAttacheInfoMapperReader(AttacheInfoMapper attacheInfoMapperReader) {
        this.attacheInfoMapperReader = attacheInfoMapperReader;
    }

    //保存动态内容信息
    @Override
    @Transactional
    public int save(User user, DynamicDTO dynamicDTO, MultipartFile[] files) throws Exception {
        OperateRecord operateRecord = new OperateRecord();
        operateRecord.setUserId(user.getId());
        operateRecord.setStatus(OperateRecordStatusEnum.Fail.getCode().toString());
        operateRecord.setType(OperateRecordTypeEnum.ReleaseDynamic.getCode());
        Long userId2 = user.getId();
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
        int result1 = 0;
        int result2 = 0;
        Dynamic dynamic = this.findDynamicByUserId(userId2);
        if (!Objects.isNull(dynamic)) {
            DynamicInfo dynamicInfo = new DynamicInfo();
            String[] nullPropertyNames = CopyUtil.getNullPropertyNames(dynamicDTO);
            BeanUtils.copyProperties(dynamicDTO, dynamicInfo, nullPropertyNames);
            dynamicInfo.setDynamicId(dynamic.getId());
            dynamicInfo.setUserId(userId2);
            if (!Objects.isNull(files) && files.length > 0) {
                dynamicInfo.setAttacheNumber(files.length);
            } else {
                dynamicInfo.setAttacheNumber(0);
            }
            dynamicInfo.setAttacheType(dynamicDTO.getAttacheInfoDataType());
            result1 = this.dynamicInfoMapperWriter.insertSelective(dynamicInfo);
            if (!Objects.isNull(files) && files.length > 0) {
                String attacheInfoDataType = dynamicDTO.getAttacheInfoDataType();
                StringBuilder filePath = new StringBuilder().append(StrUtil.trimToNull(this.projectUrlProps.getUploadRes())).append(StrUtil.trimToNull(this.projectUrlProps.getProjectName()));
                if (Objects.equals(AttacheInfoDataTypeEnum.Image.getCodeStr(), attacheInfoDataType)) {
                    filePath.append(StrUtil.trimToNull(this.projectUrlProps.getResDynamicImageFile()));
                }
                if (Objects.equals(AttacheInfoDataTypeEnum.Audio.getCodeStr(), attacheInfoDataType)) {
                    filePath.append(StrUtil.trimToNull(this.projectUrlProps.getResDynamicVoiceFile()));
                }
                AttacheInfo attacheInfo = new AttacheInfo();
                attacheInfo.setDynamicInfoBy(dynamicInfo.getId());// 动态内容id
                attacheInfo.setDataType(attacheInfoDataType); // 附件类型
                List<File> files02 = new ArrayList<>();
                String fileString = userId2 + "/" +
                        DatePattern.PURE_DATE_FORMAT.format(new Date()) + "/" +
                        System.currentTimeMillis() + "/";
                for (MultipartFile multipartFile : files) {
                    StringBuilder stringBuffer = new StringBuilder().append(filePath).append("/").append(fileString);
                    File path01 = new File(stringBuffer.toString());
                    if (!path01.exists()) {
                        path01.mkdirs();
                    }
                    String fileName = UUID.randomUUID() + "." + FileUtil.getSuffix(multipartFile.getOriginalFilename());
                    Path path02 = Paths.get(stringBuffer.append(fileName).toString());
                    if (!multipartFile.isEmpty()) {
                        path02.toFile();
                        File file = new File(path02.toString());
                        files02.add(file);
                        Files.write(path02, multipartFile.getBytes());
                    }
                }
                String fileNames = ErrorMessageUtil.fileNameBuild(files02, fileString);
                attacheInfo.setFileName(fileNames);// 附件名称
                result2 = this.attacheInfoMapperWriter.insertSelective(attacheInfo);
            }
            if (result2 > 0) {
                operateRecord.setStatus(OperateRecordStatusEnum.Success.getCode().toString());
            }
        }
        int result3 = this.operateRecordMapperWriter.insertSelective(operateRecord);
        return result1 + result2 + result3;
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
    @Transactional
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
        Dynamic dynamic = this.findDynamicByUserId(userIdLong);
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
    public DynamicDTO getLocation(LocationDTO locationDTO) {
        DynamicDTO dynamicDTOTmp = new DynamicDTO();
        LocationDTO locationDTO02 = this.getLocationByAliyunIP(locationDTO.getIp());
        BeanUtils.copyProperties(locationDTO02, dynamicDTOTmp);
        return dynamicDTOTmp;
    }

    @Override
    public LocationDTO getLocationByAliyunIP(String ip) {
        return IPLocationUtil.getLocationByAliyunIP(StrUtil.trimToEmpty(this.aliyunProps.getAppcode()), StrUtil.trimToEmpty(this.aliyunProps.getUrl()), ip);
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
        String district = locationDTO.getDistrict();
        String other = locationDTO.getOther();
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
        DynamicDTO dynamicDTOTmp = DynamicDTO.builder().build();
        if (StrUtil.isAllNotEmpty(country, province, city, longitudeString, latitudeString)) {
            dynamicDTOTmp.setCountry(country);
            dynamicDTOTmp.setProvince(province);
            dynamicDTOTmp.setCity(city);
            dynamicDTOTmp.setDistrict(district);
            dynamicDTOTmp.setOther(other);
            dynamicDTOTmp.setLongitude(longitude);
            dynamicDTOTmp.setLatitude(latitude);
        } else {
            if (StrUtil.isNotEmpty(ip)) {
                dynamicDTOTmp = this.getLocation(locationDTO);
            }
        }
        DynamicLocation newDynamicLocation = DynamicLocation.builder().build();
        BeanUtils.copyProperties(dynamicDTOTmp, newDynamicLocation);

        DynamicLocation oldDynamicLocation = new DynamicLocation();
        // 获取上一次发布动态定位地址
        DynamicInfoExample dynamicInfoExample = new DynamicInfoExample();
        dynamicInfoExample.setOrderByClause("id DESC, create_time DESC");
        dynamicInfoExample.createCriteria().andUserIdEqualTo(user.getId());
        List<DynamicInfo> dynamicInfoList = this.dynamicInfoMapperReader.selectByExample(dynamicInfoExample);
        if (!Objects.isNull(dynamicInfoList) && !dynamicInfoList.isEmpty()) {
            DynamicInfo dynamicInfo = dynamicInfoList.get(0);
            BeanUtils.copyProperties(dynamicInfo, oldDynamicLocation);
        }
        return !Objects.deepEquals(newDynamicLocation, oldDynamicLocation);
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
//        log.info("dynamicInfoParam={}", dynamicInfoParam);
        final PageInfo<DynamicInfoData> listPageInfo = PageHelper.startPage(pageNum, pageSize).doSelectPageInfo(() -> this.dynamicInfoMapperReader.selectDynamicInfoData(dynamicInfoParam));
        data.put("totalPage", listPageInfo.getPages());
        List<DynamicInfoData> list = listPageInfo.getList();
        List<DynamicInfoVO> list2 = new ArrayList<>();
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
                    dynamicInfoVO.setNickName("匿名");
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
                    dynamicInfoVO.setDistance(DistanceUtil.getDistance(dynamicInfoData.getLongitude(), dynamicInfoData.getLatitude(), dynamicInfoParam.getLongitude(), dynamicInfoParam.getLatitude()));
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
    public Dynamic findDynamicByUserId(Long userId) {
        DynamicExample example = new DynamicExample();
        example.createCriteria().andUserIdEqualTo(userId);
        List<Dynamic> dynamicList = this.dynamicMapperReader.selectByExample(example);
        return dynamicList.get(0);
    }

    @Override
    @Transactional(readOnly = true)
    public List<HotTopic> findHotTopicList() {
        return this.dynamicInfoMapperReader.selectHotTopicTitle();
    }
}
