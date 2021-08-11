package com.potato369.find.user.service.impl;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.StrUtil;
import com.potato369.find.common.dto.DynamicDTO;
import com.potato369.find.common.dto.UserDTO;
import com.potato369.find.common.enums.*;
import com.potato369.find.common.utils.CopyUtil;
import com.potato369.find.common.utils.ErrorMessageUtil;
import com.potato369.find.mbg.mapper.DynamicMapper;
import com.potato369.find.mbg.mapper.FeedbackRecordMapper;
import com.potato369.find.mbg.mapper.OperateRecordMapper;
import com.potato369.find.mbg.mapper.UserMapper;
import com.potato369.find.mbg.model.Dynamic;
import com.potato369.find.mbg.model.FeedbackRecord;
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
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
@Slf4j
@Transactional
public class UserServiceImpl implements UserService {

    private UserMapper userMapperWriter;

    private UserMapper userMapperReader;

    private UserDaoUseJdbcTemplate userDaoUseJdbcTemplate;

    private OperateRecordMapper operateRecordMapperWriter;

    private ProjectUrlProps projectUrlProps;

    private DynamicService dynamicService;

    private DynamicMapper dynamicMapperWriter;

    private FeedbackRecordMapper feedbackRecordMapperWriter;

    @Autowired
    public void setUserMapperWriter(UserMapper userMapperWriter) {
        this.userMapperWriter = userMapperWriter;
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
    public void setOperateRecordMapperWriter(OperateRecordMapper operateRecordMapperWriter) {
        this.operateRecordMapperWriter = operateRecordMapperWriter;
    }

    @Autowired
    public void setProjectUrlProps(ProjectUrlProps projectUrlProps) {
        this.projectUrlProps = projectUrlProps;
    }

    @Autowired
    public void setDynamicService(DynamicService dynamicService) {
        this.dynamicService = dynamicService;
    }

    @Autowired
    public void setDynamicMapperWriter(DynamicMapper dynamicMapperWriter) {
        this.dynamicMapperWriter = dynamicMapperWriter;
    }

    @Autowired
    public void setFeedbackRecordMapperWriter(FeedbackRecordMapper feedbackRecordMapperWriter) {
        this.feedbackRecordMapperWriter = feedbackRecordMapperWriter;
    }

    @Override
    @Transactional(readOnly = false)
    public int save(User user, OperateRecord operateRecord) {
        int result01 = this.userMapperWriter.insertSelective(user);
        int result02 = this.operateRecordMapperWriter.insert(operateRecord);
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
        int result01 = this.userMapperWriter.updateByPrimaryKey(currentInstance);
        int result02 = this.operateRecordMapperWriter.insert(operateRecord);
        if (result01 > 0 && result02 > 0) {
            return 2;
        }
        return 0;
    }

    /**
     * 登录修改用户信息和动态信息
     *
     * @param user    用户信息
     * @param dynamic 动态信息
     * @return 更新用户条数
     */
    @Override
    @Transactional(readOnly = false)
    public int update(User user, Dynamic dynamic) {
        int a = this.userMapperWriter.updateByPrimaryKeySelective(user);
        int b = this.dynamicMapperWriter.updateByPrimaryKeySelective(dynamic);
        return a + b;
    }

    @Override
    @Transactional(readOnly = true)
    public User find(Long id, OperateRecord operateRecord) {
        User currentInstance = this.userDaoUseJdbcTemplate.getById(id);
        this.operateRecordMapperWriter.insert(operateRecord);
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
                    this.userMapperWriter.updateByPrimaryKey(user1);
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
    @Transactional
    public int register(User user, String content, UserDTO userDTO, MultipartFile head) throws Exception {
        //用户信息入库
        this.userMapperWriter.insertSelective(user);
        DynamicDTO dynamicDTO = DynamicDTO.builder().build();
        dynamicDTO.setImei(user.getImei());
        dynamicDTO.setAttacheInfoDataType(AttacheInfoDataTypeEnum.Image.getCodeStr());
        dynamicDTO.setModel(user.getModel());
        dynamicDTO.setSysName(user.getSysName());
        dynamicDTO.setSysCode(user.getSysCode());
        dynamicDTO.setNetworkMode(user.getNetworkMode());
        dynamicDTO.setIp(user.getIp());
        dynamicDTO.setCountry(user.getCountry());
        dynamicDTO.setProvince(user.getProvince());
        dynamicDTO.setCity(user.getCity());
        dynamicDTO.setDistrict(user.getDistrict());
        dynamicDTO.setOther(user.getOther());
        dynamicDTO.setLongitude(user.getLongitude());
        dynamicDTO.setLatitude(user.getLatitude());
        dynamicDTO.setPublicStatus(PublicStatusEnum.NOPublic.getType());
        dynamicDTO.setContent(content);
        dynamicDTO.setIsAnonymous(IsAnonymousEnum.No.getType());
        dynamicDTO.setIsTopic(IsTopicEnum.No.getType());
        return this.dynamicService.release(user, dynamicDTO, head);
    }

    /**
     * 更新用户VIP等级
     */
    @Override
    @Transactional
    public void updateUserGrade() {
        List<User> userList = this.userMapperReader.selectVIPExpireUser();
        for (User user : userList) {
            user.setGrade(UserGradeEnum.VIP0.getGrade());
            user.setUpdateTime(new Date());
            this.userMapperWriter.updateByPrimaryKeySelective(user);
        }
    }

    /**
     * 保存意见反馈信息
     *
     * @param feedbackRecord
     * @param files
     * @return 0
     */
    @Override
    @Transactional
    public int feedback(FeedbackRecord feedbackRecord, MultipartFile[] files) {
        List<File> files02 = new ArrayList<>();
        String fileString = feedbackRecord.getUserId() + "/" +
                DatePattern.PURE_DATE_FORMAT.format(new Date()) + "/" +
                System.currentTimeMillis() + "/";
        if (!Objects.isNull(files) && files.length > 0) {
            StringBuilder filePath = new StringBuilder()
                    .append(StrUtil.trimToNull(this.projectUrlProps.getUploadRes()))
                    .append(StrUtil.trimToNull(this.projectUrlProps.getProjectName()))
                    .append(StrUtil.trimToNull(this.projectUrlProps.getResFeedback()));
            try {
                for (MultipartFile multipartFile : files) {
                    StringBuilder stringBuilder = new StringBuilder().append(filePath).append("/").append(fileString);
                    File path01 = new File(stringBuilder.toString());
                    if (!path01.exists()) {
                        path01.mkdirs();
                    }
                    String fileName = UUID.randomUUID() + "." + FileUtil.getSuffix(multipartFile.getOriginalFilename());
                    Path path02 = Paths.get(stringBuilder.append(fileName).toString());
                    if (!multipartFile.isEmpty()) {
                        path02.toFile();
                        File file = new File(path02.toString());
                        files02.add(file);
                        Files.write(path02, multipartFile.getBytes());
                    }
                }
            } catch (Exception e) {
                log.error("反馈意见上传文件出现出现错误", e);
            }
        }
        String fileNames = ErrorMessageUtil.fileNameBuild(files02, fileString);
        feedbackRecord.setFilePathList(fileNames);
        return this.feedbackRecordMapperWriter.insertSelective(feedbackRecord);
    }
}
