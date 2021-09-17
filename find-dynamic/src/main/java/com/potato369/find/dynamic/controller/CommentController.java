package com.potato369.find.dynamic.controller;

import cn.hutool.core.util.StrUtil;
import com.potato369.find.common.api.CommonResult;
import com.potato369.find.common.api.ResultCode;
import com.potato369.find.common.dto.CommentDTO;
import com.potato369.find.common.enums.*;
import com.potato369.find.common.vo.PageCommentVOs;
import com.potato369.find.dynamic.config.bean.PushBean;
import com.potato369.find.dynamic.service.*;
import com.potato369.find.mbg.mapper.OperateRecordMapper;
import com.potato369.find.mbg.mapper.UserMapper;
import com.potato369.find.mbg.model.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.WebApplicationContext;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <pre>
 * @PackageName com.potato369.find.dynamic.controller
 * @ClassName CommentController
 * @Desc 评论管理控制器类
 * @WebSite https://www.potato369.com
 * @Author admin
 * @Date 2021/07/12 10:58
 * @CreateBy IntelliJ IDEA 2020.3 (Ultimate Edition)
 * @Copyright Copyright (c) 2016 ~ 2028 版权所有 (C) 土豆互联科技(深圳)有限公司 https://www.potato369.com All Rights Reserved。
 * </pre>
 */
@Slf4j
@RestController
@RequestMapping("/v1/comment")
@Scope(value = WebApplicationContext.SCOPE_REQUEST)
public class CommentController {

    private DynamicInfoService dynamicInfoService;

    private CommentService commentService;

    private SensitiveWordsService sensitiveWordsService;

    private LikeRecordService likeRecordService;

    private OperateRecordMapper operateRecordMapperWriter;

    private UserMapper userMapperReader;

    private JiGuangPushService jiGuangPushService;

    @Autowired
    public void setDynamicInfoService(DynamicInfoService dynamicInfoService) {
        this.dynamicInfoService = dynamicInfoService;
    }

    @Autowired
    public void setCommentService(CommentService commentService) {
        this.commentService = commentService;
    }

    @Autowired
    public void setSensitiveWordsService(SensitiveWordsService sensitiveWordsService) {
        this.sensitiveWordsService = sensitiveWordsService;
    }

    @Autowired
    public void setLikeRecordService(LikeRecordService likeRecordService) {
        this.likeRecordService = likeRecordService;
    }

    @Autowired
    public void setOperateRecordMapperWriter(OperateRecordMapper operateRecordMapperWriter) {
        this.operateRecordMapperWriter = operateRecordMapperWriter;
    }

    @Autowired
    public void setUserMapperReader(UserMapper userMapperReader) {
        this.userMapperReader = userMapperReader;
    }

    @Autowired
    public void setJiGuangPushService(JiGuangPushService jiGuangPushService) {
        this.jiGuangPushService = jiGuangPushService;
    }

    // 发布评论
    @PostMapping("/{id}/release.do")
    public CommonResult<Map<String, Object>> release(
            @PathVariable(name = "id") Long userId,
            @Valid CommentDTO commentDTO,
            BindingResult bindingResult) {
        Map<String, Object> data = new ConcurrentHashMap<>();
        OperateRecord operateRecord = new OperateRecord();
        operateRecord.setUserId(userId);
        operateRecord.setStatus(OperateRecordStatusEnum.Fail.getStatus());
        operateRecord.setType(OperateRecordTypeEnum.ReleaseComment.getCode());
        data.put("RELEASE", "ERROR");
        try {
            if (log.isDebugEnabled()) {
                log.error("开始发布评论");
            }
            if (bindingResult.hasErrors()) {
                return CommonResult.validateFailed("参数校验不通过，" + Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage());
            }
            User user = this.userMapperReader.selectByPrimaryKey(userId);
            if (Objects.isNull(user)) {
                return CommonResult.validateFailed("参数校验不通过，用户信息不存在。");
            }
            DynamicInfo dynamicInfo = this.dynamicInfoService.findDynamicInfoByPrimaryKey(commentDTO.getDynamicInfoId());
            if (Objects.isNull(dynamicInfo)) {
                return CommonResult.validateFailed("参数校验不通过，动态内容信息不存在。");
            }
            Long publishUserId = dynamicInfo.getUserId();
            User publishUser = this.userMapperReader.selectByPrimaryKey(publishUserId);
            if (Objects.isNull(publishUser)) {
                return CommonResult.validateFailed("参数校验不通过，用户信息不存在。");
            }
            // 校验发布的内容是否包含敏感词汇
            if (StrUtil.isNotEmpty(commentDTO.getContent())) {
                SensitiveWords sensitiveWords = this.sensitiveWordsService.checkHasSensitiveWords(commentDTO.getContent());
                if (!Objects.isNull(sensitiveWords)) {
                    return CommonResult.validateFailed("参数校验不通过，评论内容包含" + sensitiveWords.getTypeName() + "类型敏感词汇，禁止发布。");
                }
            }
            //每个用户对一条动态只能评论一次
            List<Comment> commentList = this.commentService.findByDynamicIdAndUserId(commentDTO.getDynamicInfoId(), userId);
            if (!Objects.isNull(commentList) && !commentList.isEmpty()) {
                return CommonResult.validateFailed("参数校验不通过，只能对这条动态评论一次。");
            }
            //评论信息
            Comment comment = new Comment();
            BeanUtils.copyProperties(commentDTO, comment);
            comment.setUserId(userId);
            //动态信息
            dynamicInfo.setUpdateTime(new Date());
            dynamicInfo.setComments(dynamicInfo.getComments() + 1);
            String content = user.getNickName() + " 评论您的动态 " + dynamicInfo.getContent();//消息内容
            //评论记录信息
            CommentRecord commentRecord = new CommentRecord();
            commentRecord.setUserId(userId);
            commentRecord.setBeUserId(publishUserId);
            commentRecord.setDynamicInfoId(commentDTO.getDynamicInfoId());
            int result = this.commentService.save(content, comment, commentRecord, dynamicInfo);
            if (result > 0) {
                operateRecord.setStatus(OperateRecordStatusEnum.Success.getStatus());
                this.operateRecordMapperWriter.insertSelective(operateRecord);
                String title = "互动消息";  //消息标题
                PushBean pushBean = new PushBean();
                pushBean.setAlert(content);
                pushBean.setTitle(title);
                String regId = publishUser.getReserveColumn03();
                if (!Objects.isNull(regId) && !Objects.equals(userId, publishUserId)) {
                    this.jiGuangPushService.pushAndroid(pushBean, regId);
                }
                data.put("RELEASE", "OK");
                return CommonResult.success(data, "发布评论成功。");
            } else {
                this.operateRecordMapperWriter.insertSelective(operateRecord);
                return CommonResult.failed(data, ResultCode.FAILED);
            }
        } catch (Exception e) {
            log.error("发布评论出现错误", e);
            this.operateRecordMapperWriter.insertSelective(operateRecord);
            return CommonResult.failed(data, ResultCode.FAILED);
        } finally {
            if (log.isDebugEnabled()) {
                log.error("结束发布评论");
            }
        }
    }

    // 删除评论
    @DeleteMapping("/{id}/delete.do")
    public CommonResult<Map<String, Object>> delete(
            @PathVariable(name = "id") Long userId, @RequestParam(name = "commentId") Long commentId) {
        Map<String, Object> data = new ConcurrentHashMap<>();
        OperateRecord operateRecord = new OperateRecord();
        operateRecord.setUserId(userId);
        operateRecord.setStatus(OperateRecordStatusEnum.Fail.getStatus());
        operateRecord.setType(OperateRecordTypeEnum.DeleteComment.getCode());
        data.put("DELETE", "ERROR");
        try {
            if (log.isDebugEnabled()) {
                log.error("开始删除评论");
            }
            Comment comment = this.commentService.findById(commentId);
            if (Objects.isNull(comment)) {
                return CommonResult.validateFailed("参数校验不通过，评论不存在。");
            }
            Long commentUserId = comment.getUserId();
            if (!Objects.equals(userId, commentUserId)) {
                return CommonResult.validateFailed("参数校验不通过，只能删除自己发布的评论。");
            }
            comment.setDeleteStatus(DeleteStatusEnum.YES.getStatus());
            comment.setUpdatedTime(new Date());
            comment.setDeletedTime(new Date());
            int result = this.commentService.update(comment);
            if (result > 0) {
                data.put("DELETE", "OK");
                operateRecord.setStatus(OperateRecordStatusEnum.Success.getStatus());
                this.operateRecordMapperWriter.insertSelective(operateRecord);
                return CommonResult.success(data, "删除评论成功。");
            } else {
                this.operateRecordMapperWriter.insertSelective(operateRecord);
                return CommonResult.failed(data, ResultCode.FAILED);
            }
        } catch (Exception e) {
            log.error("删除评论出现错误", e);
            this.operateRecordMapperWriter.insertSelective(operateRecord);
            return CommonResult.failed(data, ResultCode.FAILED);
        } finally {
            if (log.isDebugEnabled()) {
                log.error("结束删除评论");
            }
        }
    }

    // 分页查询某条动态内容的所有评论详情数据
    @GetMapping("/{id}/query.do")
    public CommonResult<Map<String, Object>> query(
            @PathVariable(name = "id") Long userId,
            @RequestParam(name = "dynamicInfoId") Long dynamicInfoId,
            @RequestParam(name = "pageNum", required = false, defaultValue = "1") int pageNum,
            @RequestParam(name = "pageSize", required = false, defaultValue = "20") int pageSize) {
        Map<String, Object> data = new ConcurrentHashMap<>();
        OperateRecord operateRecord = new OperateRecord();
        operateRecord.setUserId(userId);
        operateRecord.setType(OperateRecordTypeEnum.QueryComment.getCode());
        operateRecord.setStatus(OperateRecordStatusEnum.Fail.getStatus());
        try {
            if (log.isDebugEnabled()) {
                log.debug("开始分页查询某条动态内容的所有评论详情数据");
            }
            PageCommentVOs commentsPage =
                    this.commentService.pageCommentsByDynamicId(userId, dynamicInfoId, pageNum, pageSize);
            if (commentsPage.getTotalSize() > 0) {
                data.put("totalSize", commentsPage.getTotalSize());
                data.put("totalPage", commentsPage.getTotalPage());
                data.put("list", commentsPage.getList());
            }
            this.operateRecordMapperWriter.insertSelective(operateRecord);
            return CommonResult.success(data, "分页查询某条动态内容的所有评论详情数据成功。");
        } catch (Exception e) {
            log.error("分页查询某条动态内容的所有评论详情数据出现错误", e);
            this.operateRecordMapperWriter.insertSelective(operateRecord);
            return CommonResult.failed(data, ResultCode.FAILED);
        } finally {
            if (log.isDebugEnabled()) {
                log.debug("结束分页查询某条动态内容的所有评论详情数据");
            }
        }
    }

    // 点赞/取消点赞评论（修改评论）
    @PutMapping("/{id}/likes.do")
    public CommonResult<Map<String, Object>> likes(
            @PathVariable(name = "id") Long userId,
            @RequestParam(name = "commentId") Long commentId,
            @RequestParam(name = "type") String type) {
        Map<String, Object> data = new ConcurrentHashMap<>();
        OperateRecord operateRecord = new OperateRecord();
        operateRecord.setUserId(userId);
        operateRecord.setType(OperateRecordTypeEnum.LikesComment.getCode());
        operateRecord.setStatus(OperateRecordStatusEnum.Fail.getStatus());
        try {
            if (log.isDebugEnabled()) {
                log.debug("开始点赞/取消点赞评论（修改评论）");
            }
            User user = this.userMapperReader.selectByPrimaryKey(userId);
            if (Objects.isNull(user)) {
                return CommonResult.validateFailed("点赞/取消点赞评论，用户信息不存在。");
            }
            Comment comment = this.commentService.findById(commentId);
            if (Objects.isNull(comment) || Objects.equals(DeleteStatusEnum.YES.getStatus(), comment.getDeleteStatus())) {
                return CommonResult.validateFailed("点赞/取消点赞评论，评论不存在。");
            }
            Long publishUserId = comment.getUserId();
            User publishUser = this.userMapperReader.selectByPrimaryKey(publishUserId);
            if (Objects.isNull(publishUser)) {
                return CommonResult.validateFailed("点赞/取消点赞评论，用户信息不存在。");
            }
            data.put("LIKES", "ERROR");
            LikeRecord likeRecord = this.likeRecordService.findByUserIdAndDynamicInfoId(userId, commentId, LikeRecordTypeEnum.Comment.getType());
            // 取消点赞
            if (Objects.equals(LikeStatusEnum.NO.getStatus(), type)) {
                if (Objects.isNull(likeRecord)) {
                    return CommonResult.failed(data, ResultCode.LIKES_RECORD_IS_NOT_EXIST);
                }
//                likeRecord.setStatus(LikeStatusEnum.NO.getStatus());
                likeRecord.setUpdateTime(new Date());
//                comment.setLikes(comment.getLikes() - 1);
                comment.setUpdatedTime(new Date());
                int result = this.likeRecordService.updateComment(likeRecord, comment);
                if (result > 0) {
                    data.put("LIKES", "OK");
                    operateRecord.setStatus(OperateRecordStatusEnum.Success.getStatus());
                    return CommonResult.success(data, "无法取消点赞。");
                }
            }
            // 点赞
            if (Objects.equals(LikeStatusEnum.YES.getStatus(), type)) {
                String content = user.getNickName() + " 点赞您的评论 " + comment.getContent();//消息内容
                comment.setLikes(comment.getLikes() + 1);
                comment.setUpdatedTime(new Date());
                int result = this.likeRecordService.createByUserIdAndCommentId(content, userId, comment, likeRecord);
                if (result > 0) {
                    data.put("LIKES", "OK");
                    String title = "互动消息";//消息标题
                    PushBean pushBean = new PushBean();
                    pushBean.setAlert(content);
                    pushBean.setTitle(title);
                    String regId = publishUser.getReserveColumn03();
                    if (!Objects.isNull(regId) && !Objects.equals(userId, publishUserId)) {
                        this.jiGuangPushService.pushAndroid(pushBean, regId);
                    }
                    operateRecord.setStatus(OperateRecordStatusEnum.Success.getStatus());
                    return CommonResult.success(data, "点赞成功。");
                } else {
                    return CommonResult.failed(data, ResultCode.FAILED);
                }
            }
            this.operateRecordMapperWriter.insertSelective(operateRecord);
            return CommonResult.failed(data, ResultCode.FAILED);
        } catch (Exception e) {
            log.error("点赞/取消点赞评论（修改评论）出现错误", e);
            this.operateRecordMapperWriter.insertSelective(operateRecord);
            return CommonResult.failed(data, ResultCode.FAILED);
        } finally {
            if (log.isDebugEnabled()) {
                log.debug("结束点赞/取消点赞评论（修改评论）");
            }
        }
    }
}