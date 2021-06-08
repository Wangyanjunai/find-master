package com.potato369.find.user.controller;

import com.potato369.find.common.api.CommonResult;
import com.potato369.find.common.vo.TagVO;
import com.potato369.find.user.service.TagService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Api(value = "用户模块标签管理控制器类")
@RestController
@RequestMapping("/v1/tag")
public class TagController {

    private TagService tagService;

    @Autowired
    public void setTagService(TagService tagService) {
        this.tagService = tagService;
    }

	@GetMapping("/list.do")
	public CommonResult<Map<String, List<TagVO>>> list() {
		try {
			if (log.isDebugEnabled()) {
				log.debug("开始获取用户标签列表");
			}
			List<TagVO> tagVOList = new ArrayList<>();
			Map<String, List<TagVO>> map = new ConcurrentHashMap<>();
			this.tagService.getAllUndeleteTags().forEach((tag) -> {
				TagVO tagVO = new TagVO();
				tagVO.setId(tag.getId());
				tagVO.setName(tag.getName());
				tagVOList.add(tagVO);
			});
			map.put("list", tagVOList);
			return CommonResult.success(map, "获取用户标签列表成功");
		} catch (Exception e) {
			log.error("获取用户标签列表出错", e);
			return CommonResult.failed("获取用户标签列表失败");
		} finally {
			if (log.isDebugEnabled()) {
				log.debug("结束获取用户标签列表");
			}
		}
	}
}
