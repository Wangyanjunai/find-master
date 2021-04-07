package com.potato369.find.portal.feign.fallback;

import com.potato369.find.common.api.CommonResult;
import com.potato369.find.common.dto.LocationDTO;
import com.potato369.find.portal.feign.DynamicService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@Slf4j
@Component
public class DynamicServiceFeignFallback implements DynamicService {

	@Override
	public CommonResult<Map<String, Object>> release(
			@PathVariable(name = "id", required = true) Long userIdLong,
            @RequestParam(name = "imei", required = true) String imei,
            @RequestParam(name = "attacheInfoDataType", required = true) String attacheInfoDataType,
            @RequestParam(name = "files", required = true) MultipartFile[] files,
            @RequestParam(name = "model", required = false) String model,
            @RequestParam(name = "sysName", required = false) String sysName,
            @RequestParam(name = "sysCode", required = false) String sysCode,
            @RequestParam(name = "networkMode", required = false) String networkMode,
            @RequestParam(name = "ip", required = false) String ip,
            @RequestParam(name = "country", required = false) String country,
            @RequestParam(name = "province", required = false) String province,
            @RequestParam(name = "city", required = false) String city,
            @RequestParam(name = "publicStatus", required = false) String publicStatus,
            @RequestParam(name = "content", required = false) String content) {
		log.error("进入了熔断器方法！！！");
		return CommonResult.failed("fallback; reason was: 服务忙，稍后重试！");
	}

	@Override
	public CommonResult<Map<String, Object>> checkLocation(Long userIdLong, LocationDTO locationDTO) {
		log.error("进入了熔断器方法！！！");
		return CommonResult.failed("fallback; reason was: 服务忙，稍后重试！");
	}

	@Override
	public CommonResult<Map<String, Object>> updateLocation(Long userIdLong, LocationDTO locationDTO) {
		log.error("进入了熔断器方法！！！");
		return CommonResult.failed("fallback; reason was: 服务忙，稍后重试！");
	}

	@Override
	public CommonResult<Map<String, Object>> delete(Long userId, Long dynamicInfoId) {
		log.error("进入了熔断器方法！！！");
		return CommonResult.failed("fallback; reason was: 服务忙，稍后重试！");
	}

	@Override
	public CommonResult<Map<String, Object>> likes(
			   @PathVariable(name = "id", required = true) Long userId,
               @RequestParam(name = "dynamicInfoId", required = true) Long dynamicInfoId,
               @RequestParam(name = "type", required = true) String type) {
		log.error("进入了熔断器方法！！！");
		return CommonResult.failed("fallback; reason was: 服务忙，稍后重试！");
	}
	
	@Override
	public CommonResult<Map<String, Object>> application(
			 @PathVariable(name = "id", required = true) Long userId,
             @RequestParam(name = "dynamicInfoId", required = true) Long dynamicInfoId,
             @RequestParam(name = "message", required = false) String message) {
		log.error("进入了熔断器方法！！！");
		return CommonResult.failed("fallback; reason was: 服务忙，稍后重试！");
	}

	@Override
	public CommonResult<Map<String, Object>> list(Long userId, int pageNum, int pageSize) {
		log.error("进入了熔断器方法！！！");
		return CommonResult.failed("fallback; reason was: 服务忙，稍后重试！");
	}

	@Override
	public CommonResult<Map<String, Object>> share(Long userId, Long dynamicInfoId, String mode) {
		log.error("进入了熔断器方法！！！");
		return CommonResult.failed("fallback; reason was: 服务忙，稍后重试！");
	}

	@Override
	public CommonResult<Map<String, Object>> screen(Long userId, int pageNum, int pageSize, String gender, int minAge,
			int maxAge, List<String> constellations, String dataType, List<String> provinceList, List<String> cityList) {
		log.error("进入了熔断器方法！！！");
		return CommonResult.failed("fallback; reason was: 服务忙，稍后重试！");
	}

	@Override
	public CommonResult<Map<String, Object>> mylist(Long userId, int pageNum, int pageSize) {
		log.error("进入了熔断器方法！！！");
		return CommonResult.failed("fallback; reason was: 服务忙，稍后重试！");
	}
}
