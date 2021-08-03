package com.potato369.find.dynamic.service;

import com.potato369.find.common.vo.HotTopicVO;
import com.potato369.find.mbg.model.DynamicInfo;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * <pre>
 * @PackageName com.potato369.find.dynamic.service
 * @IntellfaceName DynamicInfoService
 * @Desc 动态内容信息Service接口功能描述
 * @WebSite https://www.potato369.com
 * @Author Jack
 * @Date 2021/1/31 23:31
 * @CreateBy Intellij IDEA 2020.3 (Ultimate Edition)
 * @Copyright Copyright (c) 2016 ~ 2028 版权所有 (C) 土豆互联科技(深圳)有限公司 https://www.potato369.com All Rights Reserved。
 * </pre>
 */
@Transactional
public interface DynamicInfoService {

    /**
     * 根据动态内容id获取动态内容信息
     *
     * @param id 动态内容id
     * @return 动态内容
     */
    DynamicInfo findDynamicInfoByPrimaryKey(Long id);

    /**
     * 更新动态内容信息
     *
     * @param dynamicInfo 动态内容
     * @return 更新条数
     */
    Integer updateDynamicInfoByPrimaryKey(DynamicInfo dynamicInfo);

    /**
     * 根据用户id分页获取自己发布的所有动态内容
     *
     * @param userId   用户id
     * @param pageNum  当前页码
     * @param pageSize 每页条数
     * @return 动态内容数据
     */
    Map<String, Object> getMyDynamicInfoDataList(Long userId, Integer pageNum, Integer pageSize);

    /**
     * 根据用户id分页获取热门话题数据
     *
     * @param userId   用户id
     * @param pageNum  当前页码
     * @param pageSize 每页条数
     * @return 热门话题数据
     */
    HotTopicVO findHotTopicList(Long userId, Integer pageNum, Integer pageSize);
}
