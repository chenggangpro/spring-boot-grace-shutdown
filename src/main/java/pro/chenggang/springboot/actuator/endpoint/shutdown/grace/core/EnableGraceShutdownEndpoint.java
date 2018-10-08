package pro.chenggang.springboot.actuator.endpoint.shutdown.grace.core;

import org.springframework.context.annotation.Import;
import pro.chenggang.springboot.actuator.endpoint.shutdown.grace.config.GraceShutdownConfig;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author chengg
 * @date 2018/10/8.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Import(GraceShutdownConfig.class)
public @interface EnableGraceShutdownEndpoint {
}
