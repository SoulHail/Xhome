package com.codebear.xhome.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * 定时任务
 */
@Slf4j
@Component
//@EnableScheduling
public class TestScheduler {

    @Scheduled(cron = "*/5 * * * * ?")
    public void test() {
        log.info("====执行定时任务，时间：{}=====", LocalDateTime.now());
    }

}
