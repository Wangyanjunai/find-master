package com.potato369.find.order.task;

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
 * @PackageName com.potato369.find.order.task
 * @ClassName ScheduledTask
 * @Desc 定时任务管理器
 * @WebSite https://www.potato369.com
 * @Author Jack
 * @Date 2021/2/15 18:25
 * @CreateBy Intellij IDEA 2020.3 (Ultimate Edition)
 * @Copyright Copyright (c) 2016 ~ 2028 版权所有 (C) 土豆互联科技(深圳)有限公司 https://www.potato369.com All Rights Reserved。
 * </pre>
 */
@Slf4j
@Component
@EnableAsync
public class Scheduler {

    private final CloseTimeOutOrderTasker tasker;

    public Scheduler(CloseTimeOutOrderTasker tasker) {
        this.tasker = tasker;
    }

    @Autowired
    public CloseTimeOutOrderTasker getTasker() {
        return tasker;
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
     * 每小时扫描一次，扫描设定超时时间之前下的订单，如果没支付则取消该订单
     */
    @Async
    @Scheduled(cron = "0 0 0/1 * * ?")
    public synchronized void cancelUnpaidTimeoutOrder() {
        try {
            if (log.isDebugEnabled()) {
                log.debug("开始执行取消或者关闭超时未支付的订单定时任务");
            }
            this.tasker.closeOrder();
        } catch (Exception e) {
            log.error("执行取消或者关闭超时未支付的订单任务出现错误", e);
        } finally {
            if (log.isDebugEnabled()) {
                log.debug("结束执行取消或者关闭超时未支付的订单定时任务");
            }
        }
    }
}
