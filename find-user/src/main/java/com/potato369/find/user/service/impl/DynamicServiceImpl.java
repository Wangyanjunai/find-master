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
import com.potato369.find.common.utils.*;
import com.potato369.find.mbg.mapper.*;
import com.potato369.find.mbg.model.*;
import com.potato369.find.user.config.props.BaiduProps;
import com.potato369.find.user.config.props.ProjectUrlProps;
import com.potato369.find.user.service.DynamicService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Service
@Slf4j
@Transactional(isolation = Isolation.SERIALIZABLE, rollbackFor = Exception.class, timeout = 30)
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

    //????????????????????????
    @Override
    public DynamicInfo save(DynamicDTO dynamicDTO) {
        OperateRecord operateRecord = new OperateRecord();
        operateRecord.setStatus(OperateRecordStatusEnum.Fail.getCode().toString());
        operateRecord.setType(OperateRecordTypeEnum.ReleaseDynamic.getCode());
        operateRecord.setUserId(dynamicDTO.getUserId());
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
            //????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????
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
        return null;
    }

    //????????????????????????
    @Override
    public void find(Long userId) {
    }

    @Override
    public CommonResult<Map<String, Object>> updateDynamic(LocationDTO locationDTO, DynamicDTO dynamicDTO) throws Exception {
        int row = this.updateLocation(locationDTO, dynamicDTO);
        if (row > 0) {
            return CommonResult.success(null, "??????????????????????????????");
        }
        return CommonResult.failed("??????????????????????????????");
    }

    //????????????????????????
    @Override
    public CommonResult<Map<String, Object>> update(User user, DynamicDTO dynamicDTO, MultipartFile[] files, String message) throws Exception {
        // ????????????????????????
        String attacheInfoDataType = dynamicDTO.getAttacheInfoDataType();

        // ??????id
        Long userIdLong = dynamicDTO.getUserId();

        // ????????????
        // ????????????id????????????????????????????????????????????????????????????????????????
        Dynamic dynamic = this.findDynamicByUserId(userIdLong, dynamicDTO.getCountry(), dynamicDTO.getProvince(), dynamicDTO.getCity());
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
        // ??????????????????
        DynamicInfo dynamicInfo = new DynamicInfo();
        dynamicInfo.setAttacheType(attacheInfoDataType);
        dynamicInfo.setAttacheNumber(files.length);
        dynamicInfo.setDynamicId(dynamic.getId());
        dynamicInfo.setContent(dynamicDTO.getContent());
        dynamicInfo.setPublicStatus(dynamicDTO.getPublicStatus());
        dynamicInfo.setUserId(userIdLong);
        // ?????????????????????
        this.dynamicInfoMapperWriter.insertSelective(dynamicInfo);

        AttacheInfo attacheInfo = new AttacheInfo();
        attacheInfo.setDynamicInfoBy(dynamicInfo.getId());// ????????????id
        attacheInfo.setDataType(attacheInfoDataType); // ????????????
        List<File> files2 = new ArrayList<>();
        StringBuilder filePath = new StringBuilder()
                .append(StrUtil.trimToNull(this.projectUrlProps.getUploadRes()))
                .append(StrUtil.trimToNull(this.projectUrlProps.getProjectName()));
        if (AttacheInfoDataTypeEnum.Image.getCode().toString().equals(attacheInfoDataType)) {
            filePath.append(StrUtil.trimToNull(this.projectUrlProps.getResDynamicImageFile()));
            for (MultipartFile multipartFile : files) {
                if (!FileTypeUtil.isImageType(multipartFile.getContentType(), Objects.requireNonNull(multipartFile.getOriginalFilename()))) {
                    return CommonResult.validateFailed("????????????????????????????????????????????????");
                }
            }
        }
        if (AttacheInfoDataTypeEnum.Audio.getCode().toString().equals(attacheInfoDataType)) {
            filePath.append(StrUtil.trimToNull(this.projectUrlProps.getResDynamicVoiceFile()));
            for (MultipartFile multipartFile : files) {
                if (!FileTypeUtil.isAudioType(multipartFile.getContentType(), Objects.requireNonNull(multipartFile.getOriginalFilename()))) {
                    return CommonResult.validateFailed("????????????????????????????????????????????????");
                }
            }
        }
        String fileString1 = userIdLong +
                "/" +
                DateUtil.getDays() +
                "/" +
                System.currentTimeMillis() +
                "/";
        // ????????????????????????
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
            //??????????????????
            multipartFile.transferTo(newHeadIconFile.getAbsoluteFile());
            files2.add(newHeadIconFile);
        }
        String fileNames = ErrorMessageUtil.fileNameBuild(files2, fileString1);
        attacheInfo.setFileName(fileNames);// ????????????
        this.attacheInfoMapperWriter.insertSelective(attacheInfo);
        Map<String, Object> data = new ConcurrentHashMap<>();
        data.put("RELEASED", "OK");
        return CommonResult.success(data, message);
    }

    //??????????????????
    @Override
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

    //???????????????ip??????????????????
    @Override
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
                                    dynamicDTOTmp.setCountry("??????");
                                }
                                dynamicDTOTmp.setProvince(addr[1]);
                                dynamicDTOTmp.setCity(addr[2]);
                            }
                        }
                    }
                } catch (Exception e) {
                    log.error("??????????????????IP??????????????????", e);
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
    public boolean checkLocationIsUpdate(LocationDTO locationDTO, User user) {
        DynamicDTO dynamicDTOTmp = this.getLocation(locationDTO, user);
        Long userIdLong = user.getId();
        // ?????????????????????????????????
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
    public List<DynamicInfoData> getDynamicInfoData(DynamicInfoParam dynamicInfoParam, Integer pageNum, Integer sizeNum) {
        return this.dynamicInfoMapperReader.selectDynamicInfoData(dynamicInfoParam);
    }

    @Override
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
     * ????????????id??????????????????
     *
     * @param userId ??????id
     * @return ????????????
     */
    @Override
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
    public int release(User user, DynamicDTO dynamicDTO, MultipartFile head) throws Exception {
        // ??????????????????????????????
        String headIconFilePath = StrUtil.trimToNull(this.projectUrlProps.getUploadRes())
                + StrUtil.trimToNull(this.projectUrlProps.getProjectName())
                + StrUtil.trimToNull(this.projectUrlProps.getResHeadIcon())
                + user.getId();
        // ???????????????????????????
        String oldHeadFileName = head.getOriginalFilename();
        assert oldHeadFileName != null;
        String newHeadFileName = cn.hutool.core.lang.UUID.randomUUID() + oldHeadFileName.substring(oldHeadFileName.lastIndexOf("."));
        File newHeadIconFile = new File(headIconFilePath, newHeadFileName);
        if (!newHeadIconFile.exists()) {
            newHeadIconFile.mkdirs();
        }
        //???????????????????????????????????????????????????????????????????????????
        head.transferTo(newHeadIconFile);
        // ????????????????????????
        String dynamicIconFilePath = StrUtil.trimToNull(this.projectUrlProps.getUploadRes()) +
                StrUtil.trimToNull(this.projectUrlProps.getProjectName()) +
                StrUtil.trimToNull(this.projectUrlProps.getResDynamicImageFile());
        String fileString = user.getId() + "/" + DateUtil.getDays() + "/" + System.currentTimeMillis() + "/";
        //??????????????????
        File newHeadIconFileDy = new File(dynamicIconFilePath + fileString);
        if (!newHeadIconFileDy.exists()) {
            newHeadIconFileDy.mkdirs();
        }
        //?????????????????????????????????
        FileUtil.copyDir(newHeadIconFile.getParent(), newHeadIconFileDy.getAbsolutePath());
        user.setHeadIcon(newHeadIconFile.getName());
        int a = this.userMapperWriter.updateByPrimaryKeySelective(user);

        //??????????????????
        Dynamic dynamic = new Dynamic();
        BeanUtils.copyProperties(dynamicDTO, dynamic);
        dynamic.setNickName(user.getNickName());
        dynamic.setUserId(user.getId());
        int b = this.dynamicMapperWriter.insertSelective(dynamic);

        //????????????????????????
        DynamicInfo dynamicInfo = new DynamicInfo();
        BeanUtils.copyProperties(dynamicDTO, dynamicInfo);
        dynamicInfo.setDynamicId(dynamic.getId());
        dynamicInfo.setAttacheType(dynamicDTO.getAttacheInfoDataType());//????????????id
        dynamicInfo.setAttacheNumber(1);//?????????????????????????????????????????????????????????????????????????????????????????????
        dynamicInfo.setUserId(user.getId());
        int c = this.dynamicInfoMapperWriter.insertSelective(dynamicInfo);

        //??????????????????????????????
        AttacheInfo attacheInfo = new AttacheInfo();
        attacheInfo.setDynamicInfoBy(dynamicInfo.getId());// ????????????id
        attacheInfo.setDataType(AttacheInfoDataTypeEnum.Image.getCodeStr()); // ????????????
        attacheInfo.setFileName(fileString + newHeadIconFile.getName());// ????????????
        int d = this.attacheInfoMapperWriter.insertSelective(attacheInfo);

        //??????????????????????????????
        OperateRecord operateRecord = new OperateRecord();
        operateRecord.setUserId(user.getId());
        operateRecord.setType(OperateRecordTypeEnum.ReleaseDynamic.getCode());
        operateRecord.setStatus(OperateRecordStatusEnum.Success.getStatus());
        int e = this.operateRecordMapperWriter.insertSelective(operateRecord);

        return a + b + c + d + e;
    }
}
