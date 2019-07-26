package com.example.poiutis.scheduled;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @ClassName HandleTimeQuartz
 * @Description TODO
 * @Author wushaopei
 * @Date 2019/7/25 20:23
 * @Version 1.0
 */
@Component
public class HandleTimeQuartz {
    // 系统日志
    //private static SystemLogHandler systemLogger = SystemLogHandler
    //         .getLogger(HandleTimeQuartz.class);

    private static Logger logger = LoggerFactory.getLogger(HandleTimeQuartz.class);

    @Scheduled(cron="0/5 * *  * * ? ")
    public void userStatusJob() {

        System.out.println("进入测试");
        logger.info(System.currentTimeMillis()+"");

    }
}