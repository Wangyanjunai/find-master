package com.potato369.find.user.controller;

import com.potato369.find.common.vo.TagVO;
import com.potato369.find.user.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;

import java.util.ArrayList;
import java.util.List;

@Api(value = "用户模块标签管理控制器类")
@RestController
@RequestMapping("/v1/tag")
public class TagController {

    private TagService tagService;

    @Autowired
    public void setTagService(TagService tagService) {
        this.tagService = tagService;
    }

	@GetMapping("/list")
	public void list() {
		List<TagVO> tagVOList = new ArrayList<>();
		this.tagService.getAllUndeleteTags().stream().distinct().sorted().forEach((tag) -> {
			TagVO tagVO = new TagVO();
			tagVO.setId(tag.getId());
			tagVO.setName(tag.getName());
			tagVOList.add(tagVO);
		});
	}
}
