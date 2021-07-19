package com.potato369.find.portal.feign.fallback;

import com.potato369.find.common.api.CommonResult;
import com.potato369.find.common.dto.BlacklistDTO;
import com.potato369.find.common.dto.ReportInfoDTO;
import com.potato369.find.common.dto.UpdateUserDTO;
import com.potato369.find.common.vo.*;
import com.potato369.find.portal.feign.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
public class UserServiceFeignFallback implements UserService {

    @Override
    public CommonResult<Map<String, Boolean>> isRegister(String phone) {
        log.error("进入了熔断器方法！！！");
        return CommonResult.failed("fallback; reason was: 服务忙，稍后重试！");
    }

    @Override
    public CommonResult<Map<String, Object>> register(
            @RequestParam(name = "phone", required = true) String phone,
            @RequestParam(name = "gender", required = false) String gender,
            @RequestParam(name = "platform", required = false) String platform,
            @RequestParam(name = "nickname", required = false) String nickname,
            @RequestParam(name = "weixinId", required = false) String weixinId,
            @RequestParam(name = "imei", required = false) String imei,
            @RequestParam(name = "model", required = false) String model,
            @RequestParam(name = "sysName", required = false) String sysName,
            @RequestParam(name = "sysCode", required = false) String sysCode,
            @RequestParam(name = "networkMode", required = false) String networkMode,
            @RequestParam(name = "year", required = false) String year,
            @RequestParam(name = "month", required = false) String month,
            @RequestParam(name = "date", required = false) String date,
            @RequestParam(name = "constellation", required = false) String constellation,
            @RequestParam(name = "ip", required = false) String ip,
            @RequestParam(name = "country", required = false) String country,
            @RequestParam(name = "province", required = false) String province,
            @RequestParam(name = "city", required = false) String city,
            @RequestParam(name = "district", required = false) String district, // district：区/县
            @RequestParam(name = "other", required = false) String other, // other：其它
            @RequestParam(name = "longitude", required = false) Double longitude, // longitude：经度
            @RequestParam(name = "latitude", required = false) Double latitude, // latitude：纬度
            @RequestParam(name = "professionId", required = false) Long professionId, // professionId：职业编号
            @RequestParam(name = "tag1", required = false) Long tag1, // tag1：标签1编号
            @RequestParam(name = "tag2", required = false) Long tag2, // tag2：标签2编号
            @RequestParam(name = "tag3", required = false) Long tag3, // tag3：标签3编号
            @RequestParam(name = "tag4", required = false) Long tag4, // tag4：标签4编号
            @RequestParam(name = "tag5", required = false) Long tag5, // tag5：标签5编号
            @RequestParam(name = "autograph", required = false) String autograph,
            @RequestPart(value = "head", required = false) MultipartFile head) {
        log.error("进入了熔断器方法！！！");
        return CommonResult.failed("fallback; reason was: 服务忙，稍后重试！");
    }

    @Override
    public CommonResult<Map<String, Object>> update(Long userId, UpdateUserDTO user) {
        log.error("进入了熔断器方法！！！");
        return CommonResult.failed("fallback; reason was: 服务忙，稍后重试！");
    }

    @Override
    public CommonResult<Map<String, Object>> query(Long userId) {
        log.error("进入了熔断器方法！！！");
        return CommonResult.failed("fallback; reason was: 服务忙，稍后重试！");
    }

    @Override
    public CommonResult<Map<String, Object>> queryWeixin(Long userId) {
        log.error("进入了熔断器方法！！！");
        return CommonResult.failed("fallback; reason was: 服务忙，稍后重试！");
    }

    @Override
    public CommonResult<Map<String, List<ReportCategoryVO>>> reportCategoryList(Long userId) {
        log.error("进入了熔断器方法！！！");
        return CommonResult.failed("fallback; reason was: 服务忙，稍后重试！");
    }

    @Override
    public CommonResult<Map<String, Object>> reportDynamic(Long userId, @Valid ReportInfoDTO reportInfoDTO) {
        log.error("进入了熔断器方法！！！");
        return CommonResult.failed("fallback; reason was: 服务忙，稍后重试！");
    }

    @Override
    public CommonResult<Map<String, Object>> blackList(Long userId) {
        log.error("进入了熔断器方法！！！");
        return CommonResult.failed("fallback; reason was: 服务忙，稍后重试！");
    }

    @Override
    public CommonResult<Map<String, String>> pushBlackList(Long userId, @Valid BlacklistDTO blacklistDTO) {
        log.error("进入了熔断器方法！！！");
        return CommonResult.failed("fallback; reason was: 服务忙，稍后重试！");
    }

    @Override
    public CommonResult<Map<String, Object>> head(Long id, MultipartFile headIconFile01) {
        log.error("进入了熔断器方法！！！");
        return CommonResult.failed("fallback; reason was: 服务忙，稍后重试！");
    }

    @Override
    public CommonResult<Map<String, Object>> background(Long id, MultipartFile backgroundIconFile02) {
        log.error("进入了熔断器方法！！！");
        return CommonResult.failed("fallback; reason was: 服务忙，稍后重试！");
    }

    @Override
    public CommonResult<Map<String, Object>> uploadRegId(Long id, String regId) {
        log.error("进入了熔断器方法！！！");
        return CommonResult.failed("fallback; reason was: 服务忙，稍后重试！");
    }

    @Override
    public CommonResult<Map<String, UserVO2>> login(String phone,
                                                    String ip,
                                                    String country,
                                                    String province,
                                                    String city,
                                                    String district, //district：区/县
                                                    String other, //other：其它
                                                    Double longitude, //longitude：经度
                                                    Double latitude) {//latitude：纬度
        log.error("进入了熔断器方法！！！");
        return CommonResult.failed("fallback; reason was: 服务忙，稍后重试！");
    }

    @Override
    public CommonResult<Map<String, List<IndustriesVO>>> professionList() {
        log.error("进入了熔断器方法！！！");
        return CommonResult.failed("fallback; reason was: 服务忙，稍后重试！");
    }

    @Override
    public CommonResult<Map<String, List<TagVO>>> tagList() {
        log.error("进入了熔断器方法！！！");
        return CommonResult.failed("fallback; reason was: 服务忙，稍后重试！");
    }

    @Override
    public CommonResult<Map<String, List<TagVO>>> tagHot() {
        log.error("进入了熔断器方法！！！");
        return CommonResult.failed("fallback; reason was: 服务忙，稍后重试！");
    }

    @Override
    public CommonResult<Map<String, List<TagVO>>> search(String key) {
        log.error("进入了熔断器方法！！！");
        return CommonResult.failed("fallback; reason was: 服务忙，稍后重试！");
    }

    @Override
    public CommonResult<Map<String, List<UserVO3>>> look(Long id, String ip, Double longitude, Double latitude, Integer count) {
        log.error("进入了熔断器方法！！！");
        return CommonResult.failed("fallback; reason was: 服务忙，稍后重试！");
    }
}
