package com.potato369.find.user.service;

import com.potato369.find.common.dto.OperateRecordDTO;
import com.potato369.find.mbg.model.Dynamic;
import com.potato369.find.mbg.model.OperateRecord;
import com.potato369.find.mbg.model.User;
import org.springframework.web.multipart.MultipartFile;

public interface UserService {

    /**
     * 新增用户信息，用户登录或者注册，并添加用户操作记录
     *
     * @param user          用户信息
     * @param operateRecord 操作记录
     * @return 记录条数
     */
    int save(User user, OperateRecord operateRecord);

    /**
     * 修改用户个人资料
     *
     * @param id            用户id
     * @param user          用户信息
     * @param operateRecord 操作记录
     * @return 更新用户条数
     */
    int update(Long id, User user, OperateRecord operateRecord);

    /**
     * 登录修改用户信息和动态信息
     *
     * @param user    用户信息
     * @param dynamic 动态信息
     * @return 更新用户条数
     */
    int update(User user, Dynamic dynamic);

    /**
     * 查找用户个人资料
     *
     * @param id            用户id
     * @param operateRecord 操作记录
     */
    User find(Long id, OperateRecord operateRecord);

    /**
     * 用户个人上传或者修改头像图片资料
     *
     * @param user          用户信息
     * @param content       动态内容
     * @param multipartFile 头像图片文件，动态内容图片
     * @param operateRecord 操作记录
     */
    void uploadHeadIcon(User user, String content, MultipartFile multipartFile, OperateRecord operateRecord);

    /**
     * 用户个人上传或者修改背景图片资料
     *
     * @param user1         用户信息
     * @param multipartFile 附件
     * @param operateRecord 操作记录
     */
    String uploadBackgroundIcon(User user1, MultipartFile multipartFile, OperateRecord operateRecord);

    /**
     * 用户个人上传或者修改头像图片资料
     *
     * @param user              用户信息
     * @param content           动态内容
     * @param multipartFileName 头像图片文件，动态内容图片
     * @param operateRecord     操作记录
     */
    void uploadHeadIcon1(User user, String content, String multipartFileName, OperateRecordDTO operateRecord);

    /**
     * 更新用户VIP等级
     */
    void updateUserGrade();
}
