package com.potato369.find.user.service.impl;

import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.Header;
import cn.hutool.http.HttpRequest;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.potato369.find.common.api.CommonResult;
import com.potato369.find.common.dto.DynamicDTO;
import com.potato369.find.common.dto.LocationDTO;
import com.potato369.find.common.enums.AttacheInfoDataTypeEnum;
import com.potato369.find.common.enums.OperateRecordStatusEnum;
import com.potato369.find.common.enums.OperateRecordTypeEnum;
import com.potato369.find.common.utils.CopyUtil;
import com.potato369.find.common.utils.DateUtil;
import com.potato369.find.common.utils.ErrorMessageUtil;
import com.potato369.find.common.utils.FileTypeUtil;
import com.potato369.find.mbg.mapper.*;
import com.potato369.find.mbg.model.*;
import com.potato369.find.user.config.props.BaiduProps;
import com.potato369.find.user.config.props.ProjectUrlProps;
import com.potato369.find.user.service.DynamicService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.*;

@Service
@Slf4j
@Transactional
public class DynamicServiceImpl implements DynamicService {

    private UserMapper userMapperReader;

    private DynamicMapper dynamicMapperReader;

    private DynamicMapper dynamicMapperWriter;

    private DynamicInfoMapper dynamicInfoMapperWriter;

    private DynamicInfoMapper dynamicInfoMapperReader;

    private OperateRecordMapper operateRecordMapperWriter;

    private AttacheInfoMapper attacheInfoMapperWriter;

    private BaiduProps baiduProps;

    private ProjectUrlProps projectUrlProps;

    private UserMapper userMapperWriter;

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
    public void setDynamicInfoMapperWriter(DynamicInfoMapper dynamicInfoMapperWriter) {
        this.dynamicInfoMapperWriter = dynamicInfoMapperWriter;
    }

    @Autowired
    public void setDynamicInfoMapperReader(DynamicInfoMapper dynamicInfoMapperReader) {
        this.dynamicInfoMapperReader = dynamicInfoMapperReader;
    }

    @Autowired
    public void setOperateRecordMapperWriter(OperateRecordMapper operateRecordMapperWriter) {
        this.operateRecordMapperWriter = operateRecordMapperWriter;
    }

    @Autowired
    public void setAttacheInfoMapperWriter(AttacheInfoMapper attacheInfoMapperWriter) {
        this.attacheInfoMapperWriter = attacheInfoMapperWriter;
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
    public void setUserMapperWriter(UserMapper userMapperWriter) {
        this.userMapperWriter = userMapperWriter;
    }

    //保存动态内容信息
    @Override
    @Transactional(readOnly = false)
    public DynamicInfo save(DynamicDTO dynamicDTO) {
        OperateRecord operateRecord = new OperateRecord();
        operateRecord.setStatus(OperateRecordStatusEnum.Fail.getCode().toString());
        operateRecord.setType(OperateRecordTypeEnum.ReleaseDynamic.getCode());
        operateRecord.setUserId(dynamicDTO.getUserId());
        if (dynamicDTO != null) {
            String nickname1 = null;
            Long userIdLong = dynamicDTO.getUserId();
            User user = this.userMapperReader.selectByPrimaryKey(userIdLong);
            if (user != null) {
                nickname1 = user.getNickName();
                String imei2 = dynamicDTO.getImei();
                String model2 = dynamicDTO.getModel();
                String sysName2 = dynamicDTO.getSysName();
                String sysCode2 = dynamicDTO.getSysCode();
                String networkMode2 = dynamicDTO.getNetworkMode();
                String ip2 = dynamicDTO.getIp();
                String country2 = dynamicDTO.getCountry();
                String province2 = dynamicDTO.getProvince();
                String city2 = dynamicDTO.getCity();
                //如果前端传输过来的动态信息这些字段都为空，则复制用户注册时填写的信息到动态信息表
                if (StrUtil.isAllEmpty(imei2, model2, sysName2, sysCode2, networkMode2)) {
                    dynamicDTO.setImei(user.getImei());
                    dynamicDTO.setModel(user.getModel());
                    dynamicDTO.setSysName(user.getSysName());
                    dynamicDTO.setSysCode(user.getSysCode());
                    dynamicDTO.setNetworkMode(user.getNetworkMode());
                }
                LocationDTO locationDTO = LocationDTO.builder().build();
                locationDTO.setUserId(userIdLong);
                locationDTO.setIp(ip2);
                locationDTO.setCountry(country2);
                locationDTO.setProvince(province2);
                locationDTO.setCity(city2);
                if (StrUtil.isAllEmpty(locationDTO.getCountry(), locationDTO.getProvince(), locationDTO.getCity())) {
                    DynamicDTO dynamicDTO2 = this.getLocation(locationDTO, user);
                    dynamicDTO.setCountry(dynamicDTO2.getCountry());
                    dynamicDTO.setProvince(dynamicDTO2.getProvince());
                    dynamicDTO.setCity(dynamicDTO2.getCity());
                }
            }
            Dynamic dynamic = this.getDynamicByUserId(dynamicDTO.getUserId());
            int result1;
            if (dynamic != null) {
                String[] nullPropertyNames = CopyUtil.getNullPropertyNames(dynamicDTO);
                BeanUtils.copyProperties(dynamicDTO, dynamic, nullPropertyNames);
                dynamic.setNickName(nickname1);
                dynamic.setUserId(userIdLong);
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
                Long dynamicId = dynamic.getId();
                DynamicInfo dynamicInfo = new DynamicInfo();
                dynamicInfo.setDynamicId(dynamicId);
                dynamicInfo.setUserId(userIdLong);
                String publicStatus = dynamicDTO.getPublicStatus();
                dynamicInfo.setContent(dynamicDTO.getContent());
                dynamicInfo.setPublicStatus(publicStatus);
                int result2 = this.dynamicInfoMapperWriter.insertSelective(dynamicInfo);
                if (result2 > 0) {
                    operateRecord.setStatus(OperateRecordStatusEnum.Success.getCode().toString());
                    this.operateRecordMapperWriter.insertSelective(operateRecord);
                    return dynamicInfo;
                }
            }
        }
        return null;
    }

    //筛选动态内容信息
    @Override
    @Transactional(readOnly = true)
    public void find(Long userId) {
    }

    @Override
    @Transactional(readOnly = false)
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
    public CommonResult<Map<String, Object>> update(User user, DynamicDTO dynamicDTO, MultipartFile[] files, String message) throws Exception {
        // 附件文件数据类型
        String attacheInfoDataType = dynamicDTO.getAttacheInfoDataType();

        // 用户id
        Long userIdLong = dynamicDTO.getUserId();

        // 动态信息
        Dynamic dynamic = null;
        // 根据用户id，发布定位地址（国），省份，城市获取设备动态信息
        if (dynamicDTO != null) {
            dynamic = this.findDynamicByUserId(userIdLong, dynamicDTO.getCountry(), dynamicDTO.getProvince(), dynamicDTO.getCity());
        }
        if (dynamic != null) {
            String[] nullPropertyNames = CopyUtil.getNullPropertyNames(dynamicDTO);
            BeanUtils.copyProperties(dynamicDTO, dynamic, nullPropertyNames);
            this.dynamicMapperWriter.updateByPrimaryKeySelective(dynamic);
        } else {
            dynamic = new Dynamic();
            String[] nullPropertyNames = CopyUtil.getNullPropertyNames(dynamicDTO);
            BeanUtils.copyProperties(dynamicDTO, dynamic, nullPropertyNames);
            dynamic.setNickName(user.getNickName());
            dynamic.setUserId(user.getId());
            this.dynamicMapperWriter.insertSelective(dynamic);
        }
        // 动态内容信息
        DynamicInfo dynamicInfo = new DynamicInfo();
        dynamicInfo.setAttacheType(attacheInfoDataType);
        dynamicInfo.setAttacheNumber(files.length);
        dynamicInfo.setDynamicId(dynamic.getId());
        dynamicInfo.setContent(dynamicDTO.getContent());
        dynamicInfo.setPublicStatus(dynamicDTO.getPublicStatus());
        dynamicInfo.setUserId(userIdLong);
        // 将动态信息入库
        this.dynamicInfoMapperWriter.insertSelective(dynamicInfo);

        AttacheInfo attacheInfo = new AttacheInfo();
        attacheInfo.setDynamicInfoBy(dynamicInfo.getId());// 动态内容id
        attacheInfo.setDataType(attacheInfoDataType); // 附件类型
        List<File> files2 = new ArrayList<>();
        StringBuilder filePath = new StringBuilder()
                .append(StrUtil.trimToNull(this.projectUrlProps.getUploadRes()))
                .append(StrUtil.trimToNull(this.projectUrlProps.getProjectName()));
        if (AttacheInfoDataTypeEnum.Image.getCode().toString().equals(attacheInfoDataType)) {
            filePath.append(StrUtil.trimToNull(this.projectUrlProps.getResDynamicImageFile()));
            for (MultipartFile multipartFile : files) {
                if (!FileTypeUtil.isImageType(multipartFile.getContentType(), multipartFile.getOriginalFilename())) {
                    return CommonResult.validateFailed("上传图片资源文件类型不符合要求。");
                }
            }
        }
        if (AttacheInfoDataTypeEnum.Audio.getCode().toString().equals(attacheInfoDataType)) {
            filePath.append(StrUtil.trimToNull(this.projectUrlProps.getResDynamicVoiceFile()));
            for (MultipartFile multipartFile : files) {
                if (!FileTypeUtil.isAudioType(multipartFile.getContentType(), multipartFile.getOriginalFilename())) {
                    return CommonResult.validateFailed("上传语音资源文件类型不符合要求。");
                }
            }
        }
        String fileString1 = new StringBuilder().append(userIdLong)
                .append("/")
                .append(DateUtil.getDays())
                .append("/")
                .append(System.currentTimeMillis())
                .append("/").toString();
        // 附件文件存放路径
        String fileString = filePath.append(fileString1).toString();

        for (MultipartFile multipartFile : files) {
            String oldFileName = multipartFile.getOriginalFilename();
            String newFileName = null;
            if (oldFileName != null) {
                newFileName = UUID.randomUUID() + oldFileName.substring(oldFileName.lastIndexOf("."));
            }
            assert newFileName != null;
            File newHeadIconFile = new File(fileString, newFileName);
            if (!newHeadIconFile.getParentFile().exists()) {
                newHeadIconFile.getParentFile().mkdirs();
            }
            //发布一条动态
            multipartFile.transferTo(newHeadIconFile.getAbsoluteFile());
            files2.add(newHeadIconFile);
        }
        String fileNames = ErrorMessageUtil.fileNameBuild(files2, fileString1);
        attacheInfo.setFileName(fileNames);// 附件名称
        this.attacheInfoMapperWriter.insertSelective(attacheInfo);
        Map<String, Object> data = new HashMap<>();
        data.put("RELEASED", "OK");
        return CommonResult.success(data, message);
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
                String urlString = new StringBuffer()
                        .append(StrUtil.trimToNull(this.baiduProps.getUrl()))
                        .toString();
                String result;
                try {
                    result = HttpRequest.get(urlString)
                            .header(Header.USER_AGENT, "Hutool http")
                            .charset(CharsetUtil.CHARSET_UTF_8)
                            .form(params)
                            .timeout(200)
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
                                dynamicDTOTmp.setProvince(addr[1]);
                                dynamicDTOTmp.setCity(addr[2]);
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
        DynamicDTO dynamicDTOTmp = this.getLocation(locationDTO, user);
        Long userIdLong = user.getId();
        // 上一次发布动态定位地址
        DynamicLocation dynamicLocation = this.dynamicMapperReader.selectByUserId(userIdLong);
        DynamicDTO dynamicDTO = new DynamicDTO();
        if (dynamicLocation != null) {
            dynamicDTO.setCountry(dynamicLocation.getCountry());
            dynamicDTO.setProvince(dynamicLocation.getProvince());
            dynamicDTO.setCity(dynamicLocation.getCity());
        }
        return !dynamicDTO.equals(dynamicDTOTmp);
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
    public List<DynamicInfoData> getDynamicInfoData(DynamicInfoParam dynamicInfoParam, Integer pageNum, Integer sizeNum) {
        return this.dynamicInfoMapperReader.selectDynamicInfoData(dynamicInfoParam);
    }

    @Override
    @Transactional(readOnly = true)
    public Dynamic getDynamicByUserId(Long userId) {
        DynamicExample example = new DynamicExample();
        example.setDistinct(true);
        example.setOrderByClause("id ASC, create_time DESC, update_time DESC");
        example.createCriteria().andUserIdEqualTo(userId);
        List<Dynamic> dynamicList = this.dynamicMapperReader.selectByExample(example);
        Dynamic dynamic = null;
        if (!CollectionUtils.isEmpty(dynamicList)) {
            dynamic = dynamicList.get(0);
        }
        return dynamic;
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

    @Override
    @Transactional(readOnly = false)
    public int release(User user, DynamicDTO dynamicDTO, String fileName) {
        // 附件文件数据类型
        String attacheInfoDataType = dynamicDTO.getAttacheInfoDataType();
        // 用户id
        Long userIdLong = dynamicDTO.getUserId();
        Dynamic dynamic = new Dynamic();
        String[] nullPropertyNames = CopyUtil.getNullPropertyNames(dynamicDTO);
        BeanUtils.copyProperties(dynamicDTO, dynamic, nullPropertyNames);
        dynamic.setNickName(user.getNickName());
        dynamic.setUserId(user.getId());
        //动态信息入库
        int a = this.dynamicMapperWriter.insertSelective(dynamic);
        //动态内容信息
        DynamicInfo dynamicInfo = new DynamicInfo();
        dynamicInfo.setAttacheType(attacheInfoDataType);
        dynamicInfo.setAttacheNumber(1);
        dynamicInfo.setDynamicId(dynamic.getId());
        dynamicInfo.setContent(dynamicDTO.getContent());
        dynamicInfo.setPublicStatus(dynamicDTO.getPublicStatus());
        dynamicInfo.setUserId(userIdLong);
        dynamicInfo.setCountry(dynamicDTO.getCountry());
        dynamicInfo.setProvince(dynamicDTO.getProvince());
        dynamicInfo.setCity(dynamicDTO.getCity());
        dynamicInfo.setDistrict(dynamicDTO.getDistrict());
        dynamicInfo.setOther(dynamicDTO.getOther());
        dynamicInfo.setLongitude(dynamicDTO.getLongitude());
        dynamicInfo.setLatitude(dynamicDTO.getLatitude());
        //动态内容信息入库
        int b = this.dynamicInfoMapperWriter.insertSelective(dynamicInfo);
        AttacheInfo attacheInfo = new AttacheInfo();
        attacheInfo.setDynamicInfoBy(dynamicInfo.getId());// 动态内容id
        attacheInfo.setDataType(attacheInfoDataType); // 附件类型
        attacheInfo.setFileName(fileName);// 附件名称
        //动态信息附件信息入库
        int c = this.attacheInfoMapperWriter.insertSelective(attacheInfo);
        //用户信息入库
        int d = this.userMapperWriter.updateByPrimaryKeySelective(user);
        return a + b + c + d;
    }
}
