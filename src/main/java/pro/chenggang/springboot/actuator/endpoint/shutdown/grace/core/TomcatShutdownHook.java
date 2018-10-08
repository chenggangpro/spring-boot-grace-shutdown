package pro.chenggang.springboot.actuator.endpoint.shutdown.grace.core;

import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.connector.Connector;
import org.apache.tomcat.util.threads.ThreadPoolExecutor;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.web.embedded.tomcat.TomcatConnectorCustomizer;

import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

/**
 * TomcatShutdown
 * @author chengg
 * @date 2018/10/7.
 */
@Slf4j
public class TomcatShutdownHook implements ShutdownHook,TomcatConnectorCustomizer {

    private volatile Connector connector;
    private static volatile boolean startFlag=false;

    @Override
    public void customize(final Connector connector) {
        this.connector = connector;
    }

    @Override
    public void startGraceShutdown() {
        startFlag = true;
    }

    @Override
    public boolean isStartGraceShutdown() {
        return startFlag;
    }

    @Override
    public void pauseRequest() throws InterruptedException {
        final Executor executor = this.connector.getProtocolHandler().getExecutor();
        this.connector.pause();
        log.debug("Tomcat Pause Request Success");
    }

    @Override
    public void shutdown(long delayTime, TimeUnit delayTimeUnit) throws InterruptedException {
        final Executor executor = connector.getProtocolHandler().getExecutor();
        if (executor instanceof ThreadPoolExecutor) {
            final ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) executor;
            threadPoolExecutor.shutdown();

            if(!threadPoolExecutor.awaitTermination(delayTime, delayTimeUnit)) {
                log.warn("Tomcat can not shutdown gracefully . Proceeding with force shutdown.DelayTime:{},DelayTimeUnit:{}",delayTime,delayTimeUnit);
            } else {
                log.debug("Tomcat ThreadPool is empty");
            }
        }else{
            log.warn("Executor is not ThreadPoolExecutor instance...");
        }
    }

    @Override
    public Health health() {
        if(this.isStartGraceShutdown()) {
            return Health.outOfService().build();
        } else {
            return Health.up().build();
        }
    }
}
