package com.potato369.find.user.task;

import com.potato369.find.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <pre>
 * @PackageName com.potato369.find.user.task
 * @ClassName UpdateVIPExpireTasker
 * @Desc 更新VIP过期的用户新定时任务
 * @WebSite https://www.potato369.com
 * @Author Jack
 * @Date 2021/2/15 19:12
 * @CreateBy Intellij IDEA 2020.3 (Ultimate Edition)
 * @Copyright Copyright (c) 2016 ~ 2028 版权所有 (C) 土豆互联科技(深圳)有限公司 https://www.potato369.com All Rights Reserved。
 * </pre>
 */
@Component
public class UpdateVIPExpireTasker {

    private final UserService userService;

    public UpdateVIPExpireTasker(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public UserService getUserService() {
        return userService;
    }

    public void updateVIPExpire() {
        this.userService.updateUserGrade();
    }
}
