package cn.lihuan.com.quartz.service;
import cn.lihuan.com.quartz.IJob;

/**
 * job 接口
 *
 * @Author: heyuhua
 * @Date: 2021/1/11 14:46
 */
public interface JobService extends IJob {


    /**
     * 获取任务信息
     *
     * @param name
     * @param group
     * @return
     */
    String get(String name, String group);

}






