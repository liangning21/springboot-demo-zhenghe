package cn.lihuan.com.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * @Classname lihuan
 * @Description TODO
 * @Date 2021/1/29 10:23
 * @Created by Dell
 */
public class HelloJob implements Job {
    //任务执行
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("这是执行了我自己的任务调度！");
    }
}
