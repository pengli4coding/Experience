package com.pl.schedule;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class TimingTask {

    private static final Logger log = LogManager.getLogger();

    @Scheduled(cron="0/1 * * * * *")
    public void printSomething() {
        log.info("定时打印日志");
    }
}
