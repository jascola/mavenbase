package com.jascola.job;

import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class SchedulJob {
    private static final Logger LOGGER = Logger.getLogger(SchedulJob.class);
    @Scheduled(cron = "0 0 23 ? * *")
    public void clearFiles(){
        LOGGER.info("=============定时任务开始了");
        try {

        }catch (Exception e){
            LOGGER.info("=============文件清理失败");
            LOGGER.info(e.getLocalizedMessage(),e);
        }
    }
}
