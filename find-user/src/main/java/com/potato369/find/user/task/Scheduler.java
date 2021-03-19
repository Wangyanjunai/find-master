package com.potato369.find.user.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Component;

/**
 * <pre>
 * @PackageName com.potato369.find.user.task
 * @ClassName Scheduler
 * @Desc 定时任务管理器
 * @WebSite https://www.potato369.com
 * @Author Jack
 * @Date 2021/2/15 18:41
 * @CreateBy Intellij IDEA 2020.3 (Ultimate Edition)
 * @Copyright Copyright (c) 2016 ~ 2028 版权所有 (C) 土豆互联科技(深圳)有限公司 https://www.potato369.com All Rights Reserved。
 * </pre>
 */

@Slf4j
@Component
@EnableAsync
public class Scheduler {

    private UpdateVIPExpireTasker tasker;

    @Autowired
    public void setTasker(UpdateVIPExpireTasker tasker) {
        this.tasker = tasker;
    }

    @Bean
    public TaskScheduler scheduledExecutorService() {
        ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
        scheduler.setPoolSize(8);
        scheduler.setThreadNamePrefix("scheduled-thread-");
        scheduler.initialize();
        return scheduler;
    }

    /**
     * cron表达式：Seconds Minutes Hours DayofMonth Month DayofWeek [Year]
     * 每小时扫描一次，扫描用户VIP时间是否到期
     */
    @Async
    @Scheduled(cron = "0 0 0/1 * * ?")
    public synchronized void updateVIPExpire() {
        try {
            if (log.isDebugEnabled()) {
                log.debug("开始执行更新用户VIP信息定时任务");
            }
            this.tasker.updateVIPExpire();
        } catch (Exception e) {
            log.error("执行更新用户VIP信息定时任务出现错误", e);
        } finally {
            if (log.isDebugEnabled()) {
                log.debug("结束执行更新用户VIP信息定时任务");
            }
        }
    }
}
