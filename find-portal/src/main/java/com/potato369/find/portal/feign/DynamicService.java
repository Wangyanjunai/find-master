package com.potato369.find.portal.feign;

import com.potato369.find.common.api.CommonResult;
import com.potato369.find.common.dto.LocationDTO;
import com.potato369.find.common.vo.DynamicInfoVO;
import com.potato369.find.portal.config.FeignMultipartSupportConfig;
import com.potato369.find.portal.feign.fallback.DynamicServiceFeignFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@FeignClient(name = "dynamic-service", fallback = DynamicServiceFeignFallback.class, configuration = FeignMultipartSupportConfig.class)
public interface DynamicService {

    @PostMapping(value = "/find/v1/dynamic/{id}/release.do", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
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
            @RequestParam(name = "longitude", required = false) Double longitude,
            @RequestParam(name = "latitude", required = false) Double latitude,
            @RequestParam(name = "country", required = false) String country,
            @RequestParam(name = "province", required = false) String province,
            @RequestParam(name = "city", required = false) String city,
            @RequestParam(name = "district", required = false) String district,
            @RequestParam(name = "other", required = false) String other,
            @RequestParam(name = "publicStatus", required = false) String publicStatus,
            @RequestParam(name = "isTopic", required = false) String isTopic,
            @RequestParam(name = "topicTitle", required = false) String topicTitle,
            @RequestParam(name = "isAnonymous", required = false) String isAnonymous,
            @RequestParam(name = "content", required = false) String content);

    @PostMapping(value = "/find/v1/dynamic/{id}/checkLocation.do")
    CommonResult<Map<String, Object>> checkLocation(@PathVariable(name = "id") Long userIdLong, @RequestBody LocationDTO locationDTO);

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
    CommonResult<Map<String, Object>> list(@PathVariable(name = "id") Long userId,
                                           @RequestParam(name = "ip", required = false) String ip,//客户端IP
                                           @RequestParam(name = "longitude", required = false) Double longitude,//经度
                                           @RequestParam(name = "latitude", required = false) Double latitude,//纬度
                                           @RequestParam(name = "pageNum", required = false, defaultValue = "1") int pageNum,
                                           @RequestParam(name = "pageSize", required = false, defaultValue = "20") int pageSize);


    @GetMapping(value = "/find/v1/dynamic/{id}/screen.do")
    CommonResult<Map<String, Object>> screen(
            @PathVariable(name = "id") Long userId, // 用户id
            @RequestParam(name = "ip", required = false) String ip,//客户端ip
            @RequestParam(name = "longitude", required = false) Double longitude,//经度
            @RequestParam(name = "latitude", required = false) Double latitude,//纬度
            @RequestParam(name = "pageNum", required = false, defaultValue = "1") int pageNum, // 当前页数，默认：当前第1页
            @RequestParam(name = "pageSize", required = false, defaultValue = "20") int pageSize, // 每页条数，默认：每页20条
            @RequestParam(name = "gender", required = false) String gender, // 性别，0->女生，1->男生
            @RequestParam(name = "minAge", required = false, defaultValue = "16") int minAge, // 年龄范围（最小值），默认：16岁
            @RequestParam(name = "maxAge", required = false, defaultValue = "35") int maxAge, // 年龄范围（最大值），默认：35岁
            @RequestParam(name = "constellation", required = false) List<String> constellations, // 星座
            @RequestParam(name = "dataType", required = false) String dataType, // 附件类型，默认：1，图片或者图片+文字；0->文字，1->图片或者图片+文字，2->语音或者语音+文字
            @RequestParam(name = "provinceList", required = false) List<String> provinceList, // 发布动态定位（省份列表）
            @RequestParam(name = "cityList", required = false) List<String> cityList,//发布动态定位（城市列表）
            @RequestParam(name = "industryId") Long industryId, //行业Id
            @RequestParam(name = "professionId", required = false) Long profession, //职业Id
            @RequestParam(name = "tags", required = false) List<String> tagsList); //标签列表

    @GetMapping(value = "/find/v1/dynamic/{id}/mylist.do")
    CommonResult<Map<String, Object>> mylist(@PathVariable(name = "id") Long userId, @RequestParam(name = "pageNum", required = false, defaultValue = "1") int pageNum, @RequestParam(name = "pageSize", required = false, defaultValue = "20") int pageSize);

    //远程调用发表评论
    @PostMapping("/find/v1/comment/{id}/release.do")
    CommonResult<Map<String, Object>> releaseComment(@PathVariable(name = "id") Long userId,
                                                     @RequestParam(name = "dynamicInfoId") Long dynamicInfoId,
                                                     @RequestParam(name = "content") String content);

    //远程调用删除评论
    @DeleteMapping("/find/v1/comment/{id}/delete.do")
    CommonResult<Map<String, Object>> deleteComment(@PathVariable(name = "id") Long userId,
                                                    @RequestParam(name = "commentId") Long commentId);

    //远程调用分页查询某条动态内容的所有评论详情数据
    @GetMapping("/find/v1/comment/{id}/query.do")
    CommonResult<Map<String, Object>> queryComment(
            @PathVariable(name = "id") Long userId,
            @RequestParam(name = "dynamicInfoId") Long dynamicInfoId,
            @RequestParam(name = "pageNum", required = false, defaultValue = "1") int pageNum,
            @RequestParam(name = "pageSize", required = false, defaultValue = "20") int pageSize);

    //远程调用点赞/取消点赞评论（修改评论）
    @PutMapping("/find/v1/comment/{id}/likes.do")
    CommonResult<Map<String, Object>> likesComment(@PathVariable(name = "id") Long userId,
                                                   @RequestParam(name = "commentId") Long commentId,
                                                   @RequestParam(name = "type") String type);

    //远程调用获取三个热门话题接口
    @GetMapping("/find/v1/dynamic/{id}/hots.do")
    CommonResult<Map<String, Object>> hots(@PathVariable(name = "id") Long userId);

    //分页获取热门话题
    @GetMapping(value = "/find/v1/dynamic/{id}/hotTopics.do")
    CommonResult<Map<String, Object>> hotTopics(@PathVariable(name = "id") Long userId,
                                                @RequestParam(name = "pageNum", required = false, defaultValue = "1") int pageNum,
                                                @RequestParam(name = "pageSize", required = false, defaultValue = "10") int pageSize);

    //模糊搜索话题
    @GetMapping(value = "/find/v1/dynamic/{id}/search-title.do")
    CommonResult<Map<String, Object>> searchLikeByTitle(@PathVariable(name = "id") Long userId, @RequestParam(name = "keywords") String keywords);

    //根据话题名称查询所有话题列表
    @GetMapping(value = "/find/v1/dynamic/{id}/find-title.do")
    CommonResult<Map<String, Object>> findDynamicInfoByTitle(@PathVariable(name = "id") Long userId,
                                                             @RequestParam(name = "topicTitle") String topicTitle,
                                                             @RequestParam(name = "ip") String ip,
                                                             @RequestParam(name = "longitude") Double longitude,
                                                             @RequestParam(name = "latitude") Double latitude,
                                                             @RequestParam(name = "pageNum", required = false, defaultValue = "1") int pageNum,
                                                             @RequestParam(name = "pageSize", required = false, defaultValue = "10") int pageSize);

    //查询热门推荐话题列表
    @GetMapping(value = "/find/v1/dynamic/{id}/hot-topic.do")
    CommonResult<Map<String, Object>> findHotByDynamicInfoCount(@PathVariable(name = "id") Long userId);

    //根据用户id申请加微信
    @PutMapping(value = "/find/v1/dynamic/{id}/apply-to-add-wechat.do")
    CommonResult<Map<String, Object>> applyToAddWechatByUserId(@PathVariable(name = "id") Long applicantUserId,
                                                               @RequestParam(name = "userId") Long applicantsUserId,
                                                               @RequestParam(name = "message", required = false) String message);

    //检查内容是否包含敏感词汇
    @GetMapping("/find/v1/dynamic/check.do")
    CommonResult<Map<String, Object>> checkResult(@RequestParam(name = "content") String content);

    @GetMapping("/find/v1/dynamic/{id}/{id2}/otherList.do")
    CommonResult<Map<String, Object>> getDynamicByUserId(@PathVariable(name = "id") Long id,
                                                         @PathVariable(name = "id2") Long id2,
                                                         @RequestParam(name = "pageNum", required = false, defaultValue = "1") int pageNum,
                                                         @RequestParam(name = "pageSize", required = false, defaultValue = "20") int pageSize);

    // 用户置顶动态内容
    @PutMapping(value = "/find/v1/dynamic/{id}/topping.do")
    CommonResult<Map<String, Object>> topping(@PathVariable(name = "id") Long userId,
                                              @RequestParam(name = "dynamicInfoId") Long dynamicInfoId);

    //查询动态内容与当前用户的关系接口
    @GetMapping("/find/v1/dynamic/{id}/info.do")
    CommonResult<DynamicInfoVO> getDynamicInfoById(@PathVariable(name = "id") Long userId,
                                                   @RequestParam(name = "dynamicInfoId") Long dynamicInfoId,
                                                   @RequestParam(name = "ip", required = false) String clientIP,
                                                   @RequestParam(name = "longitude", required = false) Double longitude,
                                                   @RequestParam(name = "latitude", required = false) Double latitude);
}
