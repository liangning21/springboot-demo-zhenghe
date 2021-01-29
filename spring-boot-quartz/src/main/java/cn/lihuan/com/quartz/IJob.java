package cn.lihuan.com.quartz;


/**
 * IJOB接口
 *
 * @Author: heyuhua
 * @Date: 2021/1/11 14:54
 */
public interface IJob {

    /**
     * 开始任务
     *
     * @param name
     * @param group
     * @param cron
     * @param clazz
     */
    void start(String name, String group, String cron, Class clazz);

    /**
     * 修改任务
     *
     * @param name
     * @param group
     * @param time
     * @return
     */
    boolean modify(String name, String group, String time);


    /**
     * 暂停
     *
     * @param name
     * @param group
     */
    void stop(String name, String group);

    /**
     * 暂停所有
     */
    void stopAll();

    /**
     * 恢复任务
     *
     * @param name
     * @param group
     */
    void resume(String name, String group);


    /**
     * 恢复所有
     */
    void resumeAll();

    /**
     * 删除任务
     *
     * @param name
     * @param group
     */
    void delete(String name, String group);
}






