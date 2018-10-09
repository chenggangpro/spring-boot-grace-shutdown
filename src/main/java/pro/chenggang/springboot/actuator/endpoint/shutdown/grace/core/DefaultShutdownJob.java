package pro.chenggang.springboot.actuator.endpoint.shutdown.grace.core;

import lombok.extern.slf4j.Slf4j;

/**
 * @author chengg
 * @date 2018/10/9.
 */
@Slf4j
public class DefaultShutdownJob implements ShutdownJob {

    @Override
    public void executeShutdownJob() {
        log.info("Execute Default Shutdown Job,There Is nothing To do...");
    }
}
