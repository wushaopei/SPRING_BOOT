package com.example.poiutis.quartzHandler;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ClassName TestTask2
 * @Description TODO
 * @Author wushaopei
 * @Date 2019/7/26 12:28
 * @Version 1.0
 */
public class TestTask2 extends QuartzJobBean {
    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("TestQuartz02----" + sdf.format(new Date()));
    }
}
