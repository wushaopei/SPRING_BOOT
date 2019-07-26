package com.example.poiutis.timer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.Trigger;

import java.sql.Time;
import java.util.Timer;

/**
 * @ClassName MyTimer
 * @Description TODO
 * @Author wushaopei
 * @Date 2019/7/26 15:57
 * @Version 1.0
 */
@Configuration
public class MyTimer {
//    public static void main(String[] args) {
    @Bean
    public void testQuartzTrigger1() {
        //1.创建一个timer实例
        Timer timer = new Timer();
        //2.创建一个MyTimerTask实例
        MyTimeTask myTimeTask = new MyTimeTask("No.1");

        //3.通过timer定时定频率调用myTimerTask的业务逻辑
        // 即 第一次执行是在当前时间的两秒之后，之后每隔一秒钟执行一次\
        timer.schedule(myTimeTask,2000L,1000L);

    }
}
