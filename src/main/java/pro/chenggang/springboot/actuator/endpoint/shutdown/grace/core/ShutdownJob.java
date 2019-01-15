package pro.chenggang.springboot.actuator.endpoint.shutdown.grace.core;

/**
 * @author chengg
 * @date 2018/10/9.
 */
public interface ShutdownJob {

    /**
     * 执行关闭tomcat线程池之前任务
     */
    void executeBeforeJob();
    /**
     * 执行关闭tomcat线程池之后任务
     */
    void executeAfterJob();
}
