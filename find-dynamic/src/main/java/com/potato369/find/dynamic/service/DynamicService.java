package com.potato369.find.dynamic.service;

import com.potato369.find.common.api.CommonResult;
import com.potato369.find.common.dto.DynamicDTO;
import com.potato369.find.common.dto.LocationDTO;
import com.potato369.find.mbg.model.Dynamic;
import com.potato369.find.mbg.model.DynamicInfoParam;
import com.potato369.find.mbg.model.User;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

public interface DynamicService {

	int save(User user, DynamicDTO dynamicDTO, MultipartFile[] files) throws Exception;

	int update(User user, DynamicDTO dynamicDTO, MultipartFile[] files) throws Exception;

    void find(Long userId) throws Exception;

    CommonResult<Map<String, Object>> updateDynamic(LocationDTO locationDTO, DynamicDTO dynamicDTO) throws Exception;

    DynamicDTO getLocation(LocationDTO locationDTO, User user);

    DynamicDTO getLocationByIp(LocationDTO locationDTO);

    boolean checkLocationIsUpdate(LocationDTO locationDTO, User user);

    Integer updateLocation(LocationDTO locationDTO, DynamicDTO dynamicDTO);

    Map<String, Object> getDynamicInfoData(Long userId, DynamicInfoParam dynamicInfoParam, Integer pageNum, Integer sizeNum);

    /**
     * 根据用户id查询动态信息
     *
     * @param userId 用户id
     * @return 动态信息
     */
    Dynamic findDynamicByUserId(Long userId, String country, String province, String city);
}
