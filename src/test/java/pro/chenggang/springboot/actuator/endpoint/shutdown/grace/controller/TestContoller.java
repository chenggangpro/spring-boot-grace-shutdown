package pro.chenggang.springboot.actuator.endpoint.shutdown.grace.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @author chengg
 * @date 2018/10/8.
 */
@RestController
public class TestContoller {


    @GetMapping("/test")
    public String test() throws InterruptedException {
        TimeUnit.SECONDS.sleep(10);
        return "Test Complete";
    }
}
