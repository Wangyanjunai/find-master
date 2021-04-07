package com.potato369.find.user.service;

import com.potato369.find.common.api.CommonResult;
import com.potato369.find.common.dto.DynamicDTO;
import com.potato369.find.common.dto.LocationDTO;
import com.potato369.find.mbg.model.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface DynamicService {

    DynamicInfo save(DynamicDTO dynamicDTO) throws Exception;

    CommonResult<Map<String, Object>> update(User user, DynamicDTO dynamicDTO, MultipartFile[] files, String message) throws Exception;
    
    CommonResult<Map<String, Object>> update1(User user, DynamicDTO dynamicDTO, String fileName, String message) throws Exception;

    void find(Long userId) throws Exception;

    CommonResult<Map<String, Object>> updateDynamic(LocationDTO locationDTO, DynamicDTO dynamicDTO) throws Exception;

    DynamicDTO getLocation(LocationDTO locationDTO, User user);

    DynamicDTO getLocationByIp(LocationDTO locationDTO);

    boolean checkLocationIsUpdate(LocationDTO locationDTO, User user);

    Integer updateLocation(LocationDTO locationDTO, DynamicDTO dynamicDTO);

    List<DynamicInfoData> getDynamicInfoData(DynamicInfoParam dynamicInfoParam, Integer pageNum, Integer sizeNum);
    
    Dynamic getDynamicByUserId(Long userId);
    
    Dynamic findDynamicByUserId(Long userId, String country, String province, String city);
}
