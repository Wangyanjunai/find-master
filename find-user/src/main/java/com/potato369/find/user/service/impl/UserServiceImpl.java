package com.potato369.find.user.service.impl;

import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.StrUtil;
import com.potato369.find.common.dto.DynamicDTO;
import com.potato369.find.common.enums.*;
import com.potato369.find.common.utils.CopyUtil;
import com.potato369.find.mbg.mapper.OperateRecordMapper;
import com.potato369.find.mbg.mapper.UserMapper;
import com.potato369.find.mbg.model.OperateRecord;
import com.potato369.find.mbg.model.User;
import com.potato369.find.user.config.props.ProjectUrlProps;
import com.potato369.find.user.dao.UserDaoUseJdbcTemplate;
import com.potato369.find.user.service.DynamicService;
import com.potato369.find.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private UserMapper userMapperWrite;

    private UserMapper userMapperReader;

    private UserDaoUseJdbcTemplate userDaoUseJdbcTemplate;

    private OperateRecordMapper operateRecordMapperWrite;

    private ProjectUrlProps projectUrlProps;

    private DynamicService dynamicService;

    @Autowired
    public void setUserMapperWrite(UserMapper userMapperWrite) {
        this.userMapperWrite = userMapperWrite;
    }

    @Autowired
    public void setUserMapperReader(UserMapper userMapperReader) {
        this.userMapperReader = userMapperReader;
    }

    @Autowired
    public void setUserDaoUseJdbcTemplate(UserDaoUseJdbcTemplate userDaoUseJdbcTemplate) {
        this.userDaoUseJdbcTemplate = userDaoUseJdbcTemplate;
    }

    @Autowired
    public void setOperateRecordMapperWrite(OperateRecordMapper operateRecordMapperWrite) {
        this.operateRecordMapperWrite = operateRecordMapperWrite;
    }

    @Autowired
    public void setProjectUrlProps(ProjectUrlProps projectUrlProps) {
        this.projectUrlProps = projectUrlProps;
    }

    @Autowired
    public void setDynamicService(DynamicService dynamicService) {
        this.dynamicService = dynamicService;
    }

    @Override
    @Transactional(readOnly = false)
    public int save(User user, OperateRecord operateRecord) {
        int result01 = this.userMapperWrite.insertSelective(user);
        int result02 = this.operateRecordMapperWrite.insert(operateRecord);
        if (result01 > 0 && result02 > 0) {
            return 2;
        }
        return 0;
    }

    @Override
    @Transactional(readOnly = false)
    public int update(Long id, User user, OperateRecord operateRecord) {
        User currentInstance = this.userDaoUseJdbcTemplate.getById(id);
        String[] nullPropertyNames = CopyUtil.getNullPropertyNames(user);
        BeanUtils.copyProperties(user, currentInstance, nullPropertyNames);
        int result01 = this.userMapperWrite.updateByPrimaryKey(currentInstance);
        int result02 = this.operateRecordMapperWrite.insert(operateRecord);
        if (result01 > 0 && result02 > 0) {
            return 2;
        }
        return 0;
    }

    @Override
    @Transactional(readOnly = true)
    public User find(Long id, OperateRecord operateRecord) {
        User currentInstance = this.userDaoUseJdbcTemplate.getById(id);
        this.operateRecordMapperWrite.insert(operateRecord);
        return currentInstance;
    }

    @Override
    @Transactional(readOnly = false)
    public void uploadHeadIcon(User user1, String content, MultipartFile headIconFile01, OperateRecord operateRecord) {
        if (headIconFile01 != null && !headIconFile01.isEmpty()) {
            try {
                if (StrUtil.isNotEmpty(content)) {
                    DynamicDTO dynamicDTO = new DynamicDTO();
                    dynamicDTO.setUserId(user1.getId());
                    dynamicDTO.setImei(user1.getImei());
                    dynamicDTO.setAttacheInfoDataType(AttacheInfoDataTypeEnum.Image.getCode().toString());
                    dynamicDTO.setModel(user1.getModel());
                    dynamicDTO.setSysName(user1.getSysName());
                    dynamicDTO.setSysCode(user1.getSysCode());
                    dynamicDTO.setNetworkMode(user1.getNetworkMode());
                    dynamicDTO.setIp(user1.getIp());
                    dynamicDTO.setCountry(user1.getCountry());
                    dynamicDTO.setProvince(user1.getProvince());
                    dynamicDTO.setCity(user1.getCity());
                    dynamicDTO.setPublicStatus(PublicStatusEnum.NOPublic.getCode().toString());
                    dynamicDTO.setContent(content);
                    MultipartFile[] files = {headIconFile01};
                    this.dynamicService.update(user1, dynamicDTO, files, "发布动态内容包括上传一张或者多个动态内容资源文件成功。");
                }
            } catch (Exception e) {
                log.error("上传用户头像小图到Nginx服务器出现错误", e);
            }
            if (log.isDebugEnabled()) {
                log.debug("上传用户头像小图到Nginx服务器成功，file={}", headIconFile01.getName());
            }
        }
    }

    @Override
    @Transactional(readOnly = false)
    public String uploadBackgroundIcon(User user1, MultipartFile backgroundIconFile01, OperateRecord operateRecord) {
        // 头像图片上传服务器
        String backgroundIconFileName = "bg01.png";
        StringBuffer backgroundIconFileUrlBf = new StringBuffer()
                .append(StrUtil.trimToNull(this.projectUrlProps.getResDomain()))
                .append(StrUtil.trimToNull(this.projectUrlProps.getProjectName()))
                .append(StrUtil.trimToNull(this.projectUrlProps.getResBackgroundIcon()));
        String backgroundIconFilePath = new StringBuffer()
                .append(StrUtil.trimToNull(this.projectUrlProps.getUploadRes()))
                .append(StrUtil.trimToNull(this.projectUrlProps.getProjectName()))
                .append(StrUtil.trimToNull(this.projectUrlProps.getResBackgroundIcon())).toString();
        if (backgroundIconFile01 != null && !backgroundIconFile01.isEmpty() && !backgroundIconFile01.getOriginalFilename().equals(user1.getBackgroundIcon())) {
            backgroundIconFilePath += UUID.randomUUID() + "_" + backgroundIconFile01.getOriginalFilename();
            File backgroundIconFile = new File(backgroundIconFilePath);
            File dFile = null;
            if (!backgroundIconFile.exists()) {
                backgroundIconFile.mkdirs();
            }
            if (StrUtil.isNotEmpty(user1.getHeadIcon())) {
                String nameString = user1.getHeadIcon();
                String pathString = new StringBuffer()
                        .append(StrUtil.trimToNull(this.projectUrlProps.getUploadRes()))
                        .append(StrUtil.trimToNull(this.projectUrlProps.getProjectName()))
                        .append(StrUtil.trimToNull(this.projectUrlProps.getResBackgroundIcon()))
                        .append(nameString)
                        .toString();
                dFile = new File(pathString);
            }
            backgroundIconFileName = backgroundIconFile.getName();
            try {
                operateRecord.setStatus(OperateRecordStatusEnum.Success.getCode().toString());
                if (!user1.getHeadIcon().equals(backgroundIconFileName)) {
                    if (dFile.exists()) {
                        dFile.delete();
                    }
                    user1.setBackgroundIcon(backgroundIconFileName);
                    user1.setUpdateTime(new Date());
                    operateRecord.setType(OperateRecordTypeEnum.UpdateBackgroundIcon.getCode());
                    this.userMapperWrite.updateByPrimaryKey(user1);
                }
                backgroundIconFile01.transferTo(backgroundIconFile);
                backgroundIconFileUrlBf.append(backgroundIconFileName);
            } catch (Exception e) {
                log.error("上传背景小图到Nginx服务器出现错误", e);
                backgroundIconFileUrlBf.append(user1.getHeadIcon());
            }
            if (log.isDebugEnabled()) {
                log.debug("上传背景小图到Nginx服务器成功，file={}", backgroundIconFile.getAbsolutePath() + backgroundIconFile.getName());
            }
        }
        return backgroundIconFileUrlBf.toString();
    }

    @Override
    @Transactional(readOnly = false)
    public void uploadHeadIcon1(User user, String content, String multipartFileName, OperateRecord operateRecord) {
        try {
            if (StrUtil.isNotEmpty(content)) {
                DynamicDTO dynamicDTO = new DynamicDTO();
                dynamicDTO.setUserId(user.getId());
                dynamicDTO.setImei(user.getImei());
                dynamicDTO.setAttacheInfoDataType(AttacheInfoDataTypeEnum.Image.getCode().toString());
                dynamicDTO.setModel(user.getModel());
                dynamicDTO.setSysName(user.getSysName());
                dynamicDTO.setSysCode(user.getSysCode());
                dynamicDTO.setNetworkMode(user.getNetworkMode());
                dynamicDTO.setIp(user.getIp());
                dynamicDTO.setCountry(user.getCountry());
                dynamicDTO.setProvince(user.getProvince());
                dynamicDTO.setCity(user.getCity());
                dynamicDTO.setPublicStatus(PublicStatusEnum.NOPublic.getCode().toString());
                dynamicDTO.setContent(content);
                this.dynamicService.update1(user, dynamicDTO, multipartFileName, "发布动态内容包括上传一张或者多个动态内容资源文件成功。");
            }
        } catch (Exception e) {
            log.error("上传用户头像小图到Nginx服务器出现错误", e);
        }
        if (log.isDebugEnabled()) {
            log.debug("上传用户头像小图到Nginx服务器成功，file={}", multipartFileName);
        }

    }

    /**
     * 更新用户VIP等级
     */
    @Override
    @Transactional(readOnly = false)
    public void updateUserGrade() {
        List<User> userList = this.userMapperReader.selectVIPExpireUser();
        for (User user : userList) {
            user.setGrade(UserGradeEnum.VIP0.getGrade());
            user.setUpdateTime(new Date());
            this.userMapperWrite.updateByPrimaryKeySelective(user);
        }
    }
}
