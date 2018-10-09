package pro.chenggang.springboot.actuator.endpoint.shutdown.grace.core;

/**
 * @author chengg
 * @date 2018/10/9.
 */
public interface ShutdownJob {
    /**
     * 执行ShutdownJob
     */
    void executeShutdownJob();
}
