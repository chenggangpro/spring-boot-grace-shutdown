package pro.chenggang.springboot.actuator.endpoint.shutdown.grace.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.concurrent.TimeUnit;

/**
 * @author chengg
 * @date 2018/10/8.
 */
@Getter
@Setter
@ConfigurationProperties(prefix = "management.endpoint.shutdown")
public class GraceShutdownProperties {

    private TimeUnit healthWaitTimeUnit = TimeUnit.SECONDS;
    private TimeUnit shutdownTimeUnit = TimeUnit.SECONDS;
    private long healthWaitTime =10L;
    private long shutdownTime = 60L;

}
