package com.potato369.find.user.controller;

import com.potato369.find.common.api.CommonResult;
import com.potato369.find.common.vo.IndustriesVO;
import com.potato369.find.common.vo.ProfessionsVO;
import com.potato369.find.user.service.ProfessionService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Slf4j
@Api(value = "用户模块职业管理控制器类")
@RestController
@RequestMapping("/v1/professions")
@Scope(value = WebApplicationContext.SCOPE_REQUEST)
public class ProfessionController {

    private ProfessionService professionService;

    @Autowired
    public void setProfessionService(ProfessionService professionService) {
        this.professionService = professionService;
    }

    @GetMapping("/list.do")
	public CommonResult<Map<String, List<IndustriesVO>>> list() {
		try {
			if (log.isDebugEnabled()) {
				log.debug("开始获取用户注册行业和岗位信息列表");
			}
			Map<String, List<IndustriesVO>> map = new ConcurrentHashMap<>();
			List<IndustriesVO> industriesVOList = new ArrayList<>();
			this.professionService.getAllUnDeleteIndustrys().stream().map(industries -> {
				IndustriesVO industriesVO = new IndustriesVO();
				industriesVO.setId(industries.getId());
				industriesVO.setName(industries.getName());
				List<ProfessionsVO> professionsVOList = new ArrayList<>();
				this.professionService.getProfessionsByIndustrysId(industries.getId()).forEach(professions -> {
					ProfessionsVO professionsVO = new ProfessionsVO();
					professionsVO.setId(professions.getId());
					professionsVO.setName(professions.getName());
					professionsVOList.add(professionsVO);
				});
				industriesVO.setProfessions(professionsVOList);
				industriesVOList.add(industriesVO);
				return industriesVOList;
			}).collect(Collectors.toList());
			map.put("list", industriesVOList);
			return CommonResult.success(map, "获取用户注册行业和岗位信息列表成功");
		} catch (Exception e) {
			log.error("获取用户注册行业和岗位信息列表出错", e);
			return CommonResult.failed("获取用户注册行业和岗位信息列表失败");
		} finally {
			if (log.isDebugEnabled()) {
				log.debug("结束获取用户注册行业和岗位信息列表");
			}
		}
	}
}