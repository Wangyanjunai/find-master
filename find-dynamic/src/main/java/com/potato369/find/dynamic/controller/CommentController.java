package com.potato369.find.dynamic.controller;

import java.util.Date;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

import javax.validation.Valid;

import com.potato369.find.common.vo.PageCommentVOs;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.potato369.find.common.api.CommonResult;
import com.potato369.find.common.api.ResultCode;
import com.potato369.find.common.dto.CommentDTO;
import com.potato369.find.common.enums.DeleteStatusEnum;
import com.potato369.find.common.enums.LikeRecordTypeEnum;
import com.potato369.find.common.enums.LikeStatusEnum;
import com.potato369.find.dynamic.service.CommentService;
import com.potato369.find.dynamic.service.DynamicInfoService;
//import com.potato369.find.dynamic.service.JiGuangPushService;
import com.potato369.find.dynamic.service.LikeRecordService;
import com.potato369.find.dynamic.service.SensitiveWordsService;
//import com.potato369.find.dynamic.service.UserService;
import com.potato369.find.mbg.model.Comment;
import com.potato369.find.mbg.model.DynamicInfo;
import com.potato369.find.mbg.model.LikeRecord;
import com.potato369.find.mbg.model.SensitiveWords;

import lombok.extern.slf4j.Slf4j;

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
public class CommentController {

    private DynamicInfoService dynamicInfoService;

    private CommentService commentService;

    private SensitiveWordsService sensitiveWordsService;

    private LikeRecordService likeRecordService;

    // private UserService userService;

    // private JiGuangPushService jiGuangPushService;

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

//    @Autowired
//    public void setUserService(UserService userService) {
//        this.userService = userService;
//    }
//
//    @Autowired
//    public void setJiGuangPushService(JiGuangPushService jiGuangPushService) {
//        this.jiGuangPushService = jiGuangPushService;
//    }

    //发布评论
    @PostMapping("/{id}/release.do")
    public CommonResult<Map<String, Object>> release(@PathVariable(name = "id", required = true) Long userId,
                                                     @Valid CommentDTO commentDTO, BindingResult bindingResult) {
        Map<String, Object> data = new ConcurrentHashMap<>();
        try {
            if (log.isDebugEnabled()) {
                log.error("开始发布评论");
            }
            data.put("RELEASE", "ERROR");
            if (Objects.isNull(commentDTO)) {
                return CommonResult.validateFailed("发布评论，参数校验失败。");
            }
            if (bindingResult.hasErrors()) {
                return CommonResult.validateFailed(Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage());
            }
            DynamicInfo dynamicInfo = this.dynamicInfoService.findDynamicInfoByPrimaryKey(commentDTO.getDynamicInfoId());
            if (Objects.isNull(dynamicInfo)) {
                return CommonResult.validateFailed("发布评论，动态内容不存在。");
            }
            //校验发布的内容是否包含敏感词汇
            SensitiveWords sensitiveWords = this.sensitiveWordsService.checkHasSensitiveWords(commentDTO.getContent());
            if (!Objects.isNull(sensitiveWords)) {
                return CommonResult.validateFailed("发布评论，评论内容包含" + sensitiveWords.getTypeName() + "类型敏感词汇，不允许发布。");
            }
            Comment comment = new Comment();
            BeanUtils.copyProperties(commentDTO, comment);
            comment.setUserId(userId);
            int result = this.commentService.save(comment);
            if (result > 0) {
                data.put("RELEASE", "OK");
                return CommonResult.success(data, "发布评论成功");
            }
        } catch (Exception e) {
            log.error("发布评论出现错误", e);
            return CommonResult.failed(data, ResultCode.FAILED);
        } finally {
            if (log.isDebugEnabled()) {
                log.error("结束发布评论");
            }
        }
        return null;
    }

    //删除评论
    @DeleteMapping("/{id}/delete.do")
    public CommonResult<Map<String, Object>> delete(@PathVariable(name = "id", required = true) Long userId,
                                                    @RequestParam(name = "commentId", required = true) Long commentId) {
        Map<String, Object> data = new ConcurrentHashMap<>();
        try {
            if (log.isDebugEnabled()) {
                log.error("开始删除评论");
            }
            data.put("DELETE", "ERROR");
            Comment comment = this.commentService.findById(commentId);
            if (Objects.isNull(comment)) {
                return CommonResult.validateFailed("删除评论，评论不存在。");
            }
            Long commentUserId = comment.getUserId();
            if (!Objects.equals(userId, commentUserId)) {
            	return CommonResult.validateFailed("删除评论，只能删除自己发布的评论。");
			}
            comment.setDeleteStatus(DeleteStatusEnum.YES.getStatus());
            comment.setUpdatedTime(new Date());
            comment.setDeletedTime(new Date());
            int result = this.commentService.update(comment);
            if (result > 0) {
                data.put("DELETE", "OK");
                return CommonResult.success(data, "删除评论成功。");
            }
        } catch (Exception e) {
            log.error("删除评论出现错误", e);
            return CommonResult.failed(data, ResultCode.FAILED);
        } finally {
            if (log.isDebugEnabled()) {
                log.error("结束删除评论");
            }
        }
        return null;
    }

    //分页查询某条动态内容的所有评论详情数据
    @GetMapping("/{id}/query.do")
    public CommonResult<Map<String, Object>> query(
            @PathVariable(name = "id", required = true) Long userId,
            @RequestParam(name = "dynamicInfoId", required = true) Long dynamicInfoId,
            @RequestParam(name = "pageNum", required = false, defaultValue = "1") int pageNum,
            @RequestParam(name = "pageSize", required = false, defaultValue = "20") int pageSize) {
    	Map<String, Object> data = new ConcurrentHashMap<>();
    	try {
            if (log.isDebugEnabled()) {
                log.debug("开始分页查询某条动态内容的所有评论详情数据");
            }
            PageCommentVOs commentsPage = this.commentService.pageCommentsByDynamicId(userId, dynamicInfoId, pageNum, pageSize);
            data.put("totalSize", commentsPage.getTotalSize());
            data.put("totalPage", commentsPage.getTotalPage());
            data.put("list", commentsPage.getList());
            return CommonResult.success(data, "分页查询某条动态内容的所有评论详情数据成功。");
        } catch (Exception e) {
            log.error("分页查询某条动态内容的所有评论详情数据出现错误", e);
            return CommonResult.failed(data, ResultCode.FAILED);
        } finally {
            if (log.isDebugEnabled()) {
                log.debug("结束分页查询某条动态内容的所有评论详情数据");
            }
        }
    }

    //点赞/取消点赞评论（修改评论）
    @PutMapping("/{id}/likes.do")
    public CommonResult<Map<String, Object>> likes(@PathVariable(name = "id", required = true) Long userId,
                                                   @RequestParam(name = "commentId", required = true) Long commentId,
                                                   @RequestParam(name = "type", required = true) String type) {
        Map<String, Object> data = new ConcurrentHashMap<>();
        try {
            if (log.isDebugEnabled()) {
                log.debug("开始点赞/取消点赞评论（修改评论）");
            }
            Comment comment = this.commentService.findById(commentId);
            if (Objects.isNull(comment)) {
                return CommonResult.validateFailed("点赞/取消点赞评论，评论不存在。");
            }
            data.put("LIKES", "ERROR");
            LikeRecord likeRecord = this.likeRecordService.findByUserIdAndDynamicInfoId(userId, commentId, LikeRecordTypeEnum.Comment.getType());
            //取消点赞
            if (Objects.equals(LikeStatusEnum.NO.getStatus(), type)) {
                if (Objects.isNull(likeRecord)) {
                    return CommonResult.failed(data, ResultCode.LIKES_RECORD_IS_NOT_EXIST);
                }
                likeRecord.setStatus(LikeStatusEnum.NO.getStatus());
                likeRecord.setUpdateTime(new Date());
                comment.setLikes(comment.getLikes() - 1);
                comment.setUpdatedTime(new Date());
                int result = this.likeRecordService.updateComment(likeRecord, comment);
                if (result > 0) {
                    data.put("LIKES", "OK");
                    return CommonResult.success(data, "取消点赞成功。");
                }
            }
            //点赞
            if (Objects.equals(LikeStatusEnum.YES.getStatus(), type)) {
                // User user = this.userService.findUserById(userId);//评论者
                // User publishUser = this.userService.findUserById(comment.getUserId());//评论发表者
                // String content = user.getNickName() + "点赞你的评论" + comment.getContent();//消息内容
                int result = this.likeRecordService.createByUserIdAndCommentId(userId, comment, likeRecord);
                if (result > 0) {
                    data.put("LIKES", "OK");
//                    String title = "互动消息";//消息标题
//                    PushBean pushBean = new PushBean();
//                    pushBean.setAlert(content);
//                    pushBean.setTitle(title);
//                    this.jiGuangPushService.pushAndroid(pushBean, publishUser.getReserveColumn03());
                    return CommonResult.success(data, "点赞成功。");
                }
            }
        } catch (Exception e) {
            log.error("点赞/取消点赞评论（修改评论）出现错误", e);
            return CommonResult.failed(data, ResultCode.FAILED);
        } finally {
            if (log.isDebugEnabled()) {
                log.debug("结束点赞/取消点赞评论（修改评论）");
            }
        }
        return null;
    }
}
