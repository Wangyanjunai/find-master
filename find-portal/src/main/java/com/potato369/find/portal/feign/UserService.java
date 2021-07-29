package com.potato369.find.portal.feign;

import com.potato369.find.common.api.CommonResult;
import com.potato369.find.common.dto.BlacklistDTO;
import com.potato369.find.common.dto.ReportInfoDTO;
import com.potato369.find.common.dto.UpdateUserDTO;
import com.potato369.find.common.vo.*;
import com.potato369.find.portal.config.FeignMultipartSupportConfig;
import com.potato369.find.portal.feign.fallback.UserServiceFeignFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

// 用户中心微服务远程调用feignClient
@FeignClient(name = "user-service", fallback = UserServiceFeignFallback.class, configuration = FeignMultipartSupportConfig.class)
public interface UserService {

    // 远程调用上报或者更新极光推送唯一设备的标识接口
    @PutMapping(value = "/find/v1/user/{id}/uploadRegId.do")
    CommonResult<Map<String, Object>> uploadRegId(@PathVariable(name = "id") Long id, @RequestParam(name = "regId") String regId);

    // 远程调用修改头像接口
    @PutMapping(value = "/find/v1/user/{id}/head.do", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    CommonResult<Map<String, Object>> head(@PathVariable(name = "id") Long id, @RequestPart(value = "headIconFile") MultipartFile headIconFile);

    // 远程调用修改背景图接口
    @PutMapping(value = "/find/v1/user/{id}/background.do", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    CommonResult<Map<String, Object>> background(@PathVariable(name = "id") Long id, @RequestPart(value = "backgroundIconFile") MultipartFile backgroundIconFile);

    // 远程调用判断用户是否已经注册接口
    @GetMapping(value = "/find/v1/user/is-reg.do")
    CommonResult<Map<String, Boolean>> isRegister(@RequestParam(name = "phone") String phone);

    // 远程调用登录或者注册接口
    @PostMapping(value = "/find/v1/user/reg.do", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    CommonResult<Map<String, Object>> register(
            @RequestParam(name = "phone") String phone, // phone：手机号
            @RequestParam(name = "gender", required = false) String gender, // gender：性别
            @RequestParam(name = "platform", required = false) String platform, // platform：平台
            @RequestParam(name = "nickname", required = false) String nickname, // nickname：昵称
            @RequestParam(name = "weixinId", required = false) String weixinId, // weixinId：微信号
            @RequestParam(name = "imei", required = false) String imei, // imei：设备串码
            @RequestParam(name = "model", required = false) String model, // model：设备型号 
            @RequestParam(name = "sysName", required = false) String sysName, // sysName：系统名称
            @RequestParam(name = "sysCode", required = false) String sysCode, // sysCode：系统版本
            @RequestParam(name = "networkMode", required = false) String networkMode, // networkMode：网络方式
            @RequestParam(name = "year", required = false) String year, // year：出生年份
            @RequestParam(name = "month", required = false) String month, // month：出生月份
            @RequestParam(name = "date", required = false) String date, // date：出生日期
            @RequestParam(name = "constellation", required = false) String constellation, // constellation：星座
            @RequestParam(name = "ip", required = false) String ip, // ip：客户端IP
            @RequestParam(name = "country", required = false) String country, // country：国家
            @RequestParam(name = "province", required = false) String province, // province：省份
            @RequestParam(name = "city", required = false) String city, // city：城市
            @RequestParam(name = "district", required = false) String district, // district：区/县
            @RequestParam(name = "other", required = false) String other, // other：其它
            @RequestParam(name = "longitude", required = false) Double longitude, // longitude：经度
            @RequestParam(name = "latitude", required = false) Double latitude, // latitude：纬度
            @RequestParam(name = "professionId", required = false) Long professionId, // professionId：职业编号
            @RequestParam(name = "tag1", required = false) String tag1, // tag1：标签1
            @RequestParam(name = "tag2", required = false) String tag2, // tag2：标签2
            @RequestParam(name = "tag3", required = false) String tag3, // tag3：标签3
            @RequestParam(name = "tag4", required = false) String tag4, // tag4：标签4
            @RequestParam(name = "tag5", required = false) String tag5, // tag5：标签5
            @RequestParam(name = "autograph", required = false) String autograph, // autograph：签名/动态内容
            @RequestPart(value = "head") MultipartFile head); // head：头像图片文件

    @PutMapping(value = "/find/v1/user/login.do")
    CommonResult<Map<String, UserVO2>> login(
            @RequestParam(name = "phone") String phone, // phone：手机号码
            @RequestParam(name = "ip", required = false) String ip, // ip：客户端IP
            @RequestParam(name = "country", required = false) String country, // country：国家
            @RequestParam(name = "province", required = false) String province, // province：省份
            @RequestParam(name = "city", required = false) String city, //city：城市
            @RequestParam(name = "district", required = false) String district, //district：区/县
            @RequestParam(name = "other", required = false) String other, //other：其它
            @RequestParam(name = "longitude", required = false) Double longitude, //longitude：经度
            @RequestParam(name = "latitude", required = false) Double latitude); //latitude：纬度

    // 远程调用修改或者更新用户资料接口
    @PutMapping(value = "/find/v1/user/{id}/update.do", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    CommonResult<Map<String, Object>> update(@PathVariable(name = "id") Long userId, @RequestBody UpdateUserDTO user);

    // 远程调用查看用户个人资料接口
    @GetMapping(value = "/find/v1/user/{id}/query.do")
    CommonResult<Map<String, Object>> query(@PathVariable(name = "id") Long userId);

    // 远程调用查看用户微信接口
    @GetMapping(value = "/find/v1/user/{id}/query-weChat.do")
    CommonResult<Map<String, Object>> queryWeChat(@PathVariable(name = "id") Long userId);

    @GetMapping(value = "/find/v1/user/{id}/report-categories.do")
    CommonResult<Map<String, List<ReportCategoryVO>>> reportCategoryList(@PathVariable(name = "id") Long userId);

    // 远程调用记录用户举报内容接口
    @PostMapping(value = "/find/v1/user/{id}/report.do", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    CommonResult<Map<String, Object>> reportDynamic(@PathVariable(name = "id") Long userId, @RequestBody @Valid ReportInfoDTO reportInfoDTO);

    // 远程调用获取用户黑名单列表接口
    @GetMapping(value = "/find/v1/user/{id}/black-list.do")
    CommonResult<Map<String, Object>> blackList(@PathVariable(name = "id") Long userId);

    // 远程调用拉入推出用户黑名单列表接口
    @PostMapping(value = "/find/v1/user/{id}/pushOrPull-blacklist.do", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    CommonResult<Map<String, String>> pushBlackList(@PathVariable(name = "id") Long userId, @RequestBody @Valid BlacklistDTO blacklistDTO);

    // 远程调用行业和职业列表接口
    @GetMapping(value = "/find/v1/professions/list.do")
    CommonResult<Map<String, List<IndustriesVO>>> professionList();

    // 远程调用用户注册标签列表接口
    @GetMapping(value = "/find/v1/tag/list.do")
    CommonResult<Map<String, List<TagVO>>> tagList();

    // 远程调用获取热门标签列表接口
    @GetMapping(value = "/find/v1/tag/hot.do")
    CommonResult<Map<String, List<TagVO>>> tagHot();

    // 远程调用获取热门标签列表接口
    @GetMapping(value = "/find/v1/tag/search.do")
    CommonResult<Map<String, List<TagVO>>> search(@RequestParam(name = "keywords") String key);

    //远程调用鹿可模块推荐用户数据接口
    @GetMapping("/find/v1/user/{id}/look.do")
    CommonResult<Map<String, List<UserVO3>>> look(@PathVariable(name = "id") Long id,
                                                  @RequestParam(name = "ip") String ip,
                                                  @RequestParam(name = "longitude") Double longitude,
                                                  @RequestParam(name = "latitude") Double latitude,
                                                  @RequestParam(name = "count", required = false, defaultValue = "10") Integer count);

    //远程调用鹿可模块推荐用户详情数据接口
    @GetMapping("/find/v1/user/{id}/look-details.do")
    CommonResult<UserVO4> lookDetails(@PathVariable(name = "id") Long id, @RequestParam(name = "detailsUserId") Long detailsUserId);
}
