package cn.lihuan.com.htllo;

import cn.lihuan.com.job.HelloJob;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

public class HelloQuartzScheduling {

    public static void main(String[] args) throws Exception {
        //创建调度器
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = schedulerFactory.getScheduler();
        //这里可以构建基于cron的表大事

        //创建JobDetail实例，并与HelloJob类绑定
        JobDetail build = JobBuilder.newJob(HelloJob.class).withIdentity("heollo", "group").build();
        // 3、构建Trigger实例,每隔1s执行一次
        Trigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger1", "triggerGroup1")
                .startNow()//立即生效
                .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                        .withIntervalInSeconds(1)//每隔1s执行一次
                        .repeatForever()).build();//一直执行
        //4、执行
        scheduler.scheduleJob(build, trigger);
        System.out.println("--------scheduler start ! ------------");
        scheduler.start();
    }

}