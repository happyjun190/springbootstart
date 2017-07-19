package com.ysb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by wushenjun on 2017/7/19.
 */
@RestController
@EnableAutoConfiguration
public class SpringBootStartApplication {

    @RequestMapping("/")
    String home() {
        return "Hello world!";
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringBootStartApplication.class, args);
    }

}
