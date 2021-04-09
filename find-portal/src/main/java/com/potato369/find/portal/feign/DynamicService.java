package com.potato369.find.portal.feign;

import com.potato369.find.common.api.CommonResult;
import com.potato369.find.common.dto.LocationDTO;
import com.potato369.find.portal.config.FeignMultipartSupportConfig;
import com.potato369.find.portal.feign.fallback.DynamicServiceFeignFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@FeignClient(name = "dynamic-service", fallback = DynamicServiceFeignFallback.class, configuration = FeignMultipartSupportConfig.class)
public interface DynamicService {

    @PostMapping(value = "/find/v1/dynamic/{id}/release.do", consumes = {"multipart/form-data;charset=utf-8"}, produces = {"application/json;charset=utf-8"})
    CommonResult<Map<String, Object>> release(
            @PathVariable(name = "id") Long userIdLong,
            @RequestParam(name = "imei", required = false) String imei,
            @RequestParam(name = "attacheInfoDataType", required = false) String attacheInfoDataType,
            @RequestPart(value = "files", required = false) MultipartFile[] files,
            @RequestParam(name = "model", required = false) String model,
            @RequestParam(name = "sysName", required = false) String sysName,
            @RequestParam(name = "sysCode", required = false) String sysCode,
            @RequestParam(name = "networkMode", required = false) String networkMode,
            @RequestParam(name = "ip", required = false) String ip,
            @RequestParam(name = "country", required = false) String country,
            @RequestParam(name = "province", required = false) String province,
            @RequestParam(name = "city", required = false) String city,
            @RequestParam(name = "publicStatus", required = false) String publicStatus,
            @RequestParam(name = "content", required = false) String content);

    @PostMapping(value = "/find/v1/dynamic/{id}/checkLocation.do")
    CommonResult<Map<String, Object>> checkLocation(@PathVariable(name = "id") Long userIdLong, @RequestBody(required = false) LocationDTO locationDTO);

    @PostMapping(value = "/find/v1/dynamic/{id}/updateLocation.do")
    CommonResult<Map<String, Object>> updateLocation(@PathVariable(name = "id") Long userIdLong, @RequestBody LocationDTO locationDTO);

    @PutMapping(value = "/find/v1/dynamic/{id}/delete.do")
    CommonResult<Map<String, Object>> delete(@PathVariable(name = "id") Long userId, @RequestParam(name = "dynamicInfoId") Long dynamicInfoId);
    
    @PutMapping(value = "/find/v1/dynamic/{id}/likes.do")
    CommonResult<Map<String, Object>> likes(
    		   @PathVariable(name = "id") Long userId,
               @RequestParam(name = "dynamicInfoId") Long dynamicInfoId,
               @RequestParam(name = "type") String type);
    
    @PutMapping(value = "/find/v1/dynamic/{id}/application.do")
    CommonResult<Map<String, Object>> application(
    		 @PathVariable(name = "id") Long userId,
             @RequestParam(name = "dynamicInfoId") Long dynamicInfoId,
             @RequestParam(name = "message", required = false) String message);
    
    @PutMapping(value = "/find/v1/dynamic/{id}/share.do")
    CommonResult<Map<String, Object>> share(@PathVariable(name = "id") Long userId, @RequestParam(name = "dynamicInfoId") Long dynamicInfoId, @RequestParam(name = "mode") String mode);
    
    
    @GetMapping(value = "/find/v1/dynamic/{id}/list.do")
    CommonResult<Map<String, Object>> list(@PathVariable(name = "id") Long userId, @RequestParam(name = "pageNum", required = false, defaultValue = "1") int pageNum, @RequestParam(name = "pageSize", required = false, defaultValue = "20") int pageSize);
    
    
    @GetMapping(value = "/find/v1/dynamic/{id}/screen.do")
    public CommonResult<Map<String, Object>> screen(
            @PathVariable(name = "id") Long userId, // 用户id
            @RequestParam(name = "pageNum", required = false, defaultValue = "1") int pageNum, // 当前页数，默认：当前第1页
            @RequestParam(name = "pageSize", required = false, defaultValue = "20") int pageSize, // 每页条数，默认：每页20条
            @RequestParam(name = "gender", required = false) String gender, // 性别，0->女生，1->男生
            @RequestParam(name = "minAge", required = false, defaultValue = "16") int minAge, // 年龄范围（最小值），默认：16岁
            @RequestParam(name = "maxAge", required = false, defaultValue = "35") int maxAge, // 年龄范围（最大值），默认：35岁
            @RequestParam(name = "constellation", required = false) List<String> constellations, // 星座
            @RequestParam(name = "dataType", required = false) String dataType, // 附件类型，默认：1，图片或者图片+文字；0->文字，1->图片或者图片+文字，2->语音或者语音+文字
            @RequestParam(name = "provinceList", required = false) List<String> provinceList, // 发布动态定位（省份列表）
            @RequestParam(name = "cityList", required = false) List<String> cityList);
    
    @GetMapping(value = "/find/v1/dynamic/{id}/mylist.do")
    CommonResult<Map<String, Object>> mylist(@PathVariable(name = "id") Long userId, @RequestParam(name = "pageNum", required = false, defaultValue = "1") int pageNum, @RequestParam(name = "pageSize", required = false, defaultValue = "20") int pageSize);
}
