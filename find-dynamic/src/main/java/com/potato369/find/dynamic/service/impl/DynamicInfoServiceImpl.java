package com.potato369.find.dynamic.service.impl;

import cn.hutool.core.util.StrUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.potato369.find.common.enums.*;
import com.potato369.find.common.utils.DateUtil;
import com.potato369.find.common.vo.DynamicInfoVO;
import com.potato369.find.common.vo.HotTopicInfoVO;
import com.potato369.find.common.vo.HotTopicVO;
import com.potato369.find.dynamic.config.props.ProjectUrlProps;
import com.potato369.find.dynamic.service.DynamicInfoService;
import com.potato369.find.mbg.mapper.ApplicationRecordMapper;
import com.potato369.find.mbg.mapper.AttacheInfoMapper;
import com.potato369.find.mbg.mapper.DynamicInfoMapper;
import com.potato369.find.mbg.mapper.LikeRecordMapper;
import com.potato369.find.mbg.model.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <pre>
 * @PackageName com.potato369.find.dynamic.service.impl
 * @ClassName DynamicInfoServiceImpl
 * @Desc 动态内容信息Service接口实现功能描述
 * @WebSite https://www.potato369.com
 * @Author Jack
 * @Date 2021/1/31 23:37
 * @CreateBy Intellij IDEA 2020.3 (Ultimate Edition)
 * @Copyright Copyright (c) 2016 ~ 2028 版权所有 (C) 土豆互联科技(深圳)有限公司 https://www.potato369.com All Rights Reserved。
 * </pre>
 */
@Service
@Transactional
public class DynamicInfoServiceImpl implements DynamicInfoService {

    private DynamicInfoMapper dynamicInfoMapperReader;

    private DynamicInfoMapper dynamicInfoMapperWriter;

    private ApplicationRecordMapper applicationRecordMapperReader;

    private LikeRecordMapper likeRecordMapperReader;

    private ProjectUrlProps projectUrlProps;

    private AttacheInfoMapper attacheInfoMapperReader;

    @Autowired
    public void setDynamicInfoMapperReader(DynamicInfoMapper dynamicInfoMapperReader) {
        this.dynamicInfoMapperReader = dynamicInfoMapperReader;
    }

    @Autowired
    public void setDynamicInfoMapperWriter(DynamicInfoMapper dynamicInfoMapperWriter) {
        this.dynamicInfoMapperWriter = dynamicInfoMapperWriter;
    }

    @Autowired
    public void setApplicationRecordMapperReader(ApplicationRecordMapper applicationRecordMapperReader) {
        this.applicationRecordMapperReader = applicationRecordMapperReader;
    }

    @Autowired
    public void setLikeRecordMapperReader(LikeRecordMapper likeRecordMapperReader) {
        this.likeRecordMapperReader = likeRecordMapperReader;
    }

    @Autowired
    public void setProjectUrlProps(ProjectUrlProps projectUrlProps) {
        this.projectUrlProps = projectUrlProps;
    }

    @Autowired
    public void setAttacheInfoMapperReader(AttacheInfoMapper attacheInfoMapperReader) {
        this.attacheInfoMapperReader = attacheInfoMapperReader;
    }

    /**
     * 根据动态内容id获取动态内容信息
     *
     * @param id 动态内容id
     * @return 动态内容
     */
    @Override
    public DynamicInfo findDynamicInfoByPrimaryKey(Long id) {
        DynamicInfo dynamicInfo = this.dynamicInfoMapperReader.selectByPrimaryKey(id);
        if (!Objects.isNull(dynamicInfo) && !Objects.equals(DynamicInfoStatusEnum.HIDE.getStatus(), dynamicInfo.getDynamicStatus())) {
            return dynamicInfo;
        } else {
            return null;
        }
    }

    /**
     * 更新动态内容信息
     *
     * @param dynamicInfo 动态内容
     * @return 更新条数
     */
    @Override
    @Transactional
    public Integer updateDynamicInfoByPrimaryKey(DynamicInfo dynamicInfo) {
        return this.dynamicInfoMapperWriter.updateByPrimaryKeySelective(dynamicInfo);
    }

    /**
     * 根据用户id分页获取自己发布的所有动态内容
     *
     * @param userId 用户id
     * @return 动态内容数据
     */
    @Override
    @Transactional(readOnly = true)
    public Map<String, Object> getMyDynamicInfoDataList(Long userId, Integer pageNum, Integer pageSize) {
        Map<String, Object> data = new ConcurrentHashMap<>();
        final PageInfo<DynamicInfoData> listPageInfo = PageHelper.startPage(pageNum, pageSize).doSelectPageInfo(() -> this.dynamicInfoMapperReader.selectMyDynamicInfoData(userId));
        data.put("totalPage", listPageInfo.getPages());
        List<DynamicInfoData> list = listPageInfo.getList();
        List<DynamicInfoVO> list2 = new ArrayList<>();
        if (!Objects.isNull(list) && !list.isEmpty()) {
            for (DynamicInfoData dynamicInfoData : list) {
                DynamicInfoVO dynamicInfoVO = DynamicInfoVO.builder().build();
                if (StrUtil.isNotEmpty(dynamicInfoData.getHeadIcon())) {
                    dynamicInfoData.setHeadIcon(
                            StrUtil.trimToNull(this.projectUrlProps.getResDomain()
                                    + StrUtil.trimToNull(this.projectUrlProps.getProjectName()))
                                    + StrUtil.trimToNull(this.projectUrlProps.getResHeadIcon())
                                    + dynamicInfoData.getUserId()
                                    + "/"
                                    + dynamicInfoData.getHeadIcon());
                }
                dynamicInfoData.setPublishTime(DateUtil.fomateDate(dynamicInfoData.getCreateTime(), DateUtil.sdfTimeFmt));
                BeanUtils.copyProperties(dynamicInfoData, dynamicInfoVO);

                Long dynamicInfoId = dynamicInfoData.getDynamicInfoId(); // 动态内容id

                // 查询用户对该条动态是否申请加微信
                ApplicationRecordExample applicationRecordExample = new ApplicationRecordExample();
                applicationRecordExample.setDistinct(true);
                applicationRecordExample.setOrderByClause("create_time DESC, id DESC");
                applicationRecordExample.createCriteria().andUserIdEqualTo(userId).andDynamicInfoIdEqualTo(dynamicInfoId);
                List<ApplicationRecord> applicationRecordList = this.applicationRecordMapperReader.selectByExample(applicationRecordExample);
                dynamicInfoVO.setApplicationStatus(applicationRecordList != null && !applicationRecordList.isEmpty());

                // 查询用户对该条动态是否点赞
                LikeRecordExample likeRecordExample = new LikeRecordExample();
                likeRecordExample.setDistinct(true);
                likeRecordExample.setOrderByClause("create_time DESC, id DESC");
                likeRecordExample.createCriteria().andUserIdEqualTo(userId).andDynamicInfoIdEqualTo(dynamicInfoId);
                List<LikeRecord> likeRecordList = this.likeRecordMapperReader.selectByExample(likeRecordExample);
                if (!Objects.isNull(likeRecordList) && !likeRecordList.isEmpty()) {
                    LikeRecord likeRecord = likeRecordList.get(0);
                    if (!Objects.isNull(likeRecord)) {
                        if (likeRecord.getStatus().equals(LikeStatusEnum.YES.getStatus())) {
                            dynamicInfoVO.setLikeStatus(true);
                        }
                        if (likeRecord.getStatus().equals(LikeStatusEnum.NO.getStatus())) {
                            dynamicInfoVO.setLikeStatus(false);
                        }
                    }
                } else {
                    dynamicInfoVO.setLikeStatus(false);
                }

                if (PublicStatusEnum.NOPublic.getCode().toString().equals(dynamicInfoData.getPublishStatus())) {
                    dynamicInfoVO.setAddress(null);
                }
                if (PublicStatusEnum.Public.getCode().toString().equals(dynamicInfoData.getPublishStatus())) {
                    StringBuilder stringBuilder = new StringBuilder();
                    if (!"省".equals(dynamicInfoData.getProvince())) {
                        stringBuilder.append(dynamicInfoData.getProvince());
                    }
                    stringBuilder.append(dynamicInfoData.getCity());
                    dynamicInfoVO.setAddress(stringBuilder.toString());
                }
                AttacheInfo attacheInfo = this.attacheInfoMapperReader.selectByDynamicInfoId(dynamicInfoId);
                if (!Objects.isNull(attacheInfo)) {
                    String[] fileNameList01 = StrUtil.split(attacheInfo.getFileName(), "||");
                    List<String> fileNameList02 = new ArrayList<>(Arrays.asList(fileNameList01));
                    List<String> fileNameList03 = new ArrayList<>();
                    for (String fileName : fileNameList02) {
                        StringBuilder stringBuilder = new StringBuilder();
                        stringBuilder.append(StrUtil.trimToNull(this.projectUrlProps.getResDomain()))
                                .append(StrUtil.trimToNull(this.projectUrlProps.getProjectName()));
                        if (StrUtil.isNotEmpty(dynamicInfoData.getAttacheFileDataType()) && AttacheInfoDataTypeEnum.Image.getCode().toString().equals(dynamicInfoData.getAttacheFileDataType())) {
                            stringBuilder.append(StrUtil.trimToNull(this.projectUrlProps.getResDynamicImageFile()));
                        }
                        if (StrUtil.isNotEmpty(dynamicInfoData.getAttacheFileDataType()) && AttacheInfoDataTypeEnum.Audio.getCode().toString().equals(dynamicInfoData.getAttacheFileDataType())) {
                            stringBuilder.append(StrUtil.trimToNull(this.projectUrlProps.getResDynamicVoiceFile()));
                        }
                        stringBuilder.append(fileName);
                        fileNameList03.add(stringBuilder.toString());
                        dynamicInfoVO.setFileName(fileNameList03);
                    }
                }
                if (Objects.equals(IsAnonymousEnum.No.getType(), dynamicInfoData.getIsAnonymous())) {
                    dynamicInfoVO.setAnonymous(false);
                }
                if (Objects.equals(IsAnonymousEnum.Yes.getType(), dynamicInfoData.getIsAnonymous())) {
                    dynamicInfoVO.setAnonymous(true);
                    dynamicInfoVO.setHeadIcon(StrUtil.trimToNull(this.projectUrlProps.getResDomain()
                            + StrUtil.trimToNull(this.projectUrlProps.getProjectName()))
                            + StrUtil.trimToNull(this.projectUrlProps.getResHeadIcon() + "default.png"));
                    dynamicInfoVO.setNickName("匿名用户");
                }


                if (Objects.equals(IsTopicEnum.No.getType(), dynamicInfoData.getIsTopic())) {
                    dynamicInfoVO.setTopic(false);
                }
                if (Objects.equals(IsTopicEnum.Yes.getType(), dynamicInfoData.getIsTopic())) {
                    dynamicInfoVO.setTopic(true);
                    dynamicInfoVO.setTopicTitle("#" + dynamicInfoData.getTopicTitle());
                }
                if (Objects.equals(DynamicInfoToppingEnum.YES.getStatus(), dynamicInfoData.getIsTop())) {
                    dynamicInfoVO.setTop(true);
                }
                if (Objects.equals(DynamicInfoToppingEnum.NO.getStatus(), dynamicInfoData.getIsTop())) {
                    dynamicInfoVO.setTop(false);
                }
                dynamicInfoVO.setApplicationStatus(false);
                dynamicInfoVO.setComments(dynamicInfoData.getComments());
                list2.add(dynamicInfoVO);
            }
        }
        data.put("list", list2);
        return data;
    }

    /**
     * 根据用户id分页获取自己发布的所有动态内容
     *
     * @param myUserId    我的用户id
     * @param otherUserId 其他用户id
     * @return 动态内容数据
     */
    @Override
    @Transactional(readOnly = true)
    public Map<String, Object> getOtherDynamicInfoDataList(Long myUserId, Long otherUserId, Integer pageNum, Integer pageSize) {
        Map<String, Object> data = new ConcurrentHashMap<>();
        final PageInfo<DynamicInfoData> listPageInfo = PageHelper.startPage(pageNum, pageSize).doSelectPageInfo(() -> this.dynamicInfoMapperReader.selectMyDynamicInfoData(otherUserId));
        data.put("totalPage", listPageInfo.getPages());
        List<DynamicInfoData> list = listPageInfo.getList();
        List<DynamicInfoVO> list2 = new ArrayList<>();
        if (!Objects.isNull(list) && !list.isEmpty()) {
            for (DynamicInfoData dynamicInfoData : list) {
                DynamicInfoVO dynamicInfoVO = DynamicInfoVO.builder().build();
                if (StrUtil.isNotEmpty(dynamicInfoData.getHeadIcon())) {
                    dynamicInfoData.setHeadIcon(
                            StrUtil.trimToNull(this.projectUrlProps.getResDomain()
                                    + StrUtil.trimToNull(this.projectUrlProps.getProjectName()))
                                    + StrUtil.trimToNull(this.projectUrlProps.getResHeadIcon())
                                    + dynamicInfoData.getUserId()
                                    + "/"
                                    + dynamicInfoData.getHeadIcon());
                }
                dynamicInfoData.setPublishTime(DateUtil.fomateDate(dynamicInfoData.getCreateTime(), DateUtil.sdfTimeFmt));
                BeanUtils.copyProperties(dynamicInfoData, dynamicInfoVO);

                Long dynamicInfoId = dynamicInfoData.getDynamicInfoId(); // 动态内容id

                // 查询用户对该条动态是否申请加微信
                ApplicationRecordExample applicationRecordExample = new ApplicationRecordExample();
                applicationRecordExample.setDistinct(true);
                applicationRecordExample.setOrderByClause("create_time DESC, id DESC");
                applicationRecordExample.createCriteria().andUserIdEqualTo(myUserId).andDynamicInfoIdEqualTo(dynamicInfoId);
                List<ApplicationRecord> applicationRecordList = this.applicationRecordMapperReader.selectByExample(applicationRecordExample);
                dynamicInfoVO.setApplicationStatus(applicationRecordList != null && !applicationRecordList.isEmpty());

                // 查询用户对该条动态是否点赞
                LikeRecordExample likeRecordExample = new LikeRecordExample();
                likeRecordExample.setDistinct(true);
                likeRecordExample.setOrderByClause("create_time DESC, id DESC");
                likeRecordExample.createCriteria().andUserIdEqualTo(myUserId).andDynamicInfoIdEqualTo(dynamicInfoId);
                List<LikeRecord> likeRecordList = this.likeRecordMapperReader.selectByExample(likeRecordExample);
                if (!Objects.isNull(likeRecordList) && !likeRecordList.isEmpty()) {
                    LikeRecord likeRecord = likeRecordList.get(0);
                    if (!Objects.isNull(likeRecord)) {
                        if (likeRecord.getStatus().equals(LikeStatusEnum.YES.getStatus())) {
                            dynamicInfoVO.setLikeStatus(true);
                        }
                        if (likeRecord.getStatus().equals(LikeStatusEnum.NO.getStatus())) {
                            dynamicInfoVO.setLikeStatus(false);
                        }
                    }
                } else {
                    dynamicInfoVO.setLikeStatus(false);
                }

                if (PublicStatusEnum.NOPublic.getCode().toString().equals(dynamicInfoData.getPublishStatus())) {
                    dynamicInfoVO.setAddress(null);
                }
                if (PublicStatusEnum.Public.getCode().toString().equals(dynamicInfoData.getPublishStatus())) {
                    StringBuilder stringBuilder = new StringBuilder();
                    if (!"省".equals(dynamicInfoData.getProvince())) {
                        stringBuilder.append(dynamicInfoData.getProvince());
                    }
                    stringBuilder.append(dynamicInfoData.getCity());
                    dynamicInfoVO.setAddress(stringBuilder.toString());
                }
                AttacheInfo attacheInfo = this.attacheInfoMapperReader.selectByDynamicInfoId(dynamicInfoId);
                if (!Objects.isNull(attacheInfo)) {
                    String[] fileNameList01 = StrUtil.split(attacheInfo.getFileName(), "||");
                    List<String> fileNameList02 = new ArrayList<>(Arrays.asList(fileNameList01));
                    List<String> fileNameList03 = new ArrayList<>();
                    for (String fileName : fileNameList02) {
                        StringBuilder stringBuilder = new StringBuilder();
                        stringBuilder.append(StrUtil.trimToNull(this.projectUrlProps.getResDomain()))
                                .append(StrUtil.trimToNull(this.projectUrlProps.getProjectName()));
                        if (StrUtil.isNotEmpty(dynamicInfoData.getAttacheFileDataType()) && AttacheInfoDataTypeEnum.Image.getCode().toString().equals(dynamicInfoData.getAttacheFileDataType())) {
                            stringBuilder.append(StrUtil.trimToNull(this.projectUrlProps.getResDynamicImageFile()));
                        }
                        if (StrUtil.isNotEmpty(dynamicInfoData.getAttacheFileDataType()) && AttacheInfoDataTypeEnum.Audio.getCode().toString().equals(dynamicInfoData.getAttacheFileDataType())) {
                            stringBuilder.append(StrUtil.trimToNull(this.projectUrlProps.getResDynamicVoiceFile()));
                        }
                        stringBuilder.append(fileName);
                        fileNameList03.add(stringBuilder.toString());
                        dynamicInfoVO.setFileName(fileNameList03);
                    }
                }
                if (Objects.equals(IsAnonymousEnum.No.getType(), dynamicInfoData.getIsAnonymous())) {
                    dynamicInfoVO.setAnonymous(false);
                }
                if (Objects.equals(IsAnonymousEnum.Yes.getType(), dynamicInfoData.getIsAnonymous())) {
                    dynamicInfoVO.setAnonymous(true);
                    dynamicInfoVO.setHeadIcon(StrUtil.trimToNull(this.projectUrlProps.getResDomain()
                            + StrUtil.trimToNull(this.projectUrlProps.getProjectName()))
                            + StrUtil.trimToNull(this.projectUrlProps.getResHeadIcon() + "default.png"));
                    dynamicInfoVO.setNickName("匿名用户");
                }
                if (Objects.equals(IsTopicEnum.No.getType(), dynamicInfoData.getIsTopic())) {
                    dynamicInfoVO.setTopic(false);
                }
                if (Objects.equals(IsTopicEnum.Yes.getType(), dynamicInfoData.getIsTopic())) {
                    dynamicInfoVO.setTopic(true);
                    dynamicInfoVO.setTopicTitle("#" + dynamicInfoData.getTopicTitle());
                }
                dynamicInfoVO.setApplicationStatus(false);
                dynamicInfoVO.setComments(dynamicInfoData.getComments());
                list2.add(dynamicInfoVO);
            }
        }
        data.put("list", list2);
        return data;
    }

    /**
     * 根据用户id分页获取热门话题数据
     *
     * @param userId   用户id
     * @param pageNum  当前页码
     * @param pageSize 每页条数
     * @return 热门话题数据
     */
    @Override
    @Transactional(readOnly = true)
    public HotTopicVO findHotTopicList(Long userId, Integer pageNum, Integer pageSize) {
        final PageInfo<HotTopic> listPageInfo = PageHelper.startPage(pageNum, pageSize).doSelectPageInfo(() -> this.dynamicInfoMapperReader.selectHotTopic());
        HotTopicVO hotTopicVO = HotTopicVO.builder().build();
        List<HotTopicInfoVO> hotTopicInfoVOList = new ArrayList<>();
        if (!Objects.isNull(listPageInfo) && !Objects.isNull(listPageInfo.getList()) && listPageInfo.getTotal() > 0) {
            for (HotTopic hotTopic : listPageInfo.getList()) {
                HotTopicInfoVO hotTopicInfoVO = HotTopicInfoVO.builder().build();
                BeanUtils.copyProperties(hotTopic, hotTopicInfoVO);
                hotTopicInfoVO.setTopicTitle("#" + hotTopicInfoVO.getTopicTitle());
                List<DynamicInfo> dynamicInfoList = this.dynamicInfoMapperReader.selectHotDynamicInfoByTopicTitle(hotTopic.getTopicTitle());
                if (!Objects.isNull(dynamicInfoList) && !dynamicInfoList.isEmpty()) {
                    List<String> fileList = new ArrayList<>();
                    for (DynamicInfo dynamicInfo : dynamicInfoList) {
                        AttacheInfo attacheInfo = this.attacheInfoMapperReader.selectByDynamicInfoId(dynamicInfo.getId());
                        if (!Objects.isNull(attacheInfo)) {
                            String filename = attacheInfo.getFileName();
                            String[] fileNameList01 = StrUtil.split(attacheInfo.getFileName(), "||");
                            if (!Objects.isNull(fileNameList01) && fileNameList01.length > 1) {
                                filename = fileNameList01[0];
                            }
                            StringBuilder stringBuilder = new StringBuilder();
                            stringBuilder.append(StrUtil.trimToNull(this.projectUrlProps.getResDomain()))
                                    .append(StrUtil.trimToNull(this.projectUrlProps.getProjectName()));
                            if (StrUtil.isNotEmpty(attacheInfo.getDataType()) && AttacheInfoDataTypeEnum.Image.getCode().toString().equals(attacheInfo.getDataType())) {
                                stringBuilder.append(StrUtil.trimToNull(this.projectUrlProps.getResDynamicImageFile()));
                            }
                            if (StrUtil.isNotEmpty(attacheInfo.getDataType()) && AttacheInfoDataTypeEnum.Audio.getCode().toString().equals(attacheInfo.getDataType())) {
                                stringBuilder.append(StrUtil.trimToNull(this.projectUrlProps.getResDynamicVoiceFile()));
                            }
                            stringBuilder.append(filename);
                            fileList.add(stringBuilder.toString());
                        }
                    }
                    hotTopicInfoVO.setAttacheFileList(fileList);
                }
                hotTopicInfoVOList.add(hotTopicInfoVO);
            }
        }
        hotTopicVO.setTotalPage(listPageInfo.getPages());
        hotTopicVO.setTotalSize(listPageInfo.getTotal());
        hotTopicVO.setTopicTitleList(hotTopicInfoVOList);
        return hotTopicVO;
    }
}