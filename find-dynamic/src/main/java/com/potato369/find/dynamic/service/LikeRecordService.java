package com.potato369.find.dynamic.service;

import com.potato369.find.mbg.model.DynamicInfo;
import com.potato369.find.mbg.model.LikeRecord;

/**
 * <pre>
 * @PackageName com.potato369.find.dynamic.service
 * @InterfaceName LikeRecordService
 * @Desc 点赞记录Service接口实现的功能描述
 * @WebSite https://www.potato369.com
 * @Author admin
 * @Date 2021/02/01 09:11
 * @CreateBy Intellij IDEA 2020.3 (Ultimate Edition)
 * @Copyright Copyright (c) 2016 ~ 2028 版权所有 (C) 土豆互联科技(深圳)有限公司 https://www.potato369.com All Rights Reserved。
 * </pre>
 */
public interface LikeRecordService {

    /**
     * 根据用户id和动态内容id查询用户对该条动态内容的点赞记录
     *
     * @param userId        用户id
     * @param dynamicInfoId 动态内容id
     * @return 点赞记录
     */
    LikeRecord findByUserIdAndDynamicInfoId(Long userId, Long dynamicInfoId);

    /**
     * 根据用户id和动态内容id删除或者取消用户对该条动态内容的点赞记录
     *
     * @param userId      用户id
     * @param dynamicInfo 动态内容
     * @return 点赞记录条数
     */
    int deleteByUserIdAndDynamicInfoId(Long userId, DynamicInfo dynamicInfo);

    /**
     * 根据用户id和动态内容id点赞用户对该条动态内容
     *
     * @param content	  消息内容
     * @param userId      用户id
     * @param dynamicInfo 动态内容
     * @return 点赞记录条数
     */
    int createByUserIdAndDynamicInfoId(String content, Long userId, DynamicInfo dynamicInfo);
}
