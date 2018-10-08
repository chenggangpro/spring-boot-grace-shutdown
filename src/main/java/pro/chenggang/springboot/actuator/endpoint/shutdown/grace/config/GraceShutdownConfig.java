package pro.chenggang.springboot.actuator.endpoint.shutdown.grace.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.web.servlet.ServletWebServerFactoryAutoConfiguration;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import pro.chenggang.springboot.actuator.endpoint.shutdown.grace.core.TomcatShutdownHook;
import pro.chenggang.springboot.actuator.endpoint.shutdown.grace.endpoint.GraceShutdownEndpoint;
import pro.chenggang.springboot.actuator.endpoint.shutdown.grace.properties.GraceShutdownProperties;

/**
 * @author chengg
 * @date 2018/10/8.
 */
@Slf4j
@Configuration
@ConditionalOnProperty(prefix = "management.endpoint.shutdown", name = "enabled", havingValue = "true", matchIfMissing = true)
@Import(ServletWebServerFactoryAutoConfiguration.BeanPostProcessorsRegistrar.class)
public class GraceShutdownConfig {

    @Bean
    public GraceShutdownProperties graceShutdownProperties(){
        return new GraceShutdownProperties();
    }

    @Bean
    public TomcatShutdownHook tomcatShutdownHook() {
        TomcatShutdownHook tomcatShutdownHook =  new TomcatShutdownHook();
        log.info("Init TomcatShutdownHook Success");
        return tomcatShutdownHook;
    }

    @Bean
    public ServletWebServerFactory tomcatServletContainer(TomcatShutdownHook tomcatShutdownHook) {
        TomcatServletWebServerFactory servletWebServerFactory = new TomcatServletWebServerFactory();
        servletWebServerFactory.addConnectorCustomizers(tomcatShutdownHook);
        return servletWebServerFactory;
    }

    @Bean
    @ConditionalOnMissingBean
    protected GraceShutdownEndpoint graceShutdownEndpoint(TomcatShutdownHook tomcatShutdownHook,GraceShutdownProperties graceShutdownPropertie) {
        GraceShutdownEndpoint graceShutdownEndpoint =  new GraceShutdownEndpoint(tomcatShutdownHook
                ,graceShutdownPropertie.getShutdownTimeUnit()
                ,graceShutdownPropertie.getShutdownTime()
                ,graceShutdownPropertie.getHealthWaitTimeUnit()
                ,graceShutdownPropertie.getHealthWaitTime());
        log.info("Init GraceShutdownEndpoint Success");
        return graceShutdownEndpoint;
    }

}
