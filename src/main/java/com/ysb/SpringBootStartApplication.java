package com.ysb;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by wushenjun on 2017/7/19.
 */
@EnableCaching
@RestController
@EnableAutoConfiguration
public class SpringBootStartApplication {

    private static final Logger logger = LoggerFactory.getLogger(SpringBootStartApplication.class);

    public static void main(String[] args) {
        logger.info("Start  SpringBootStartApplication.");
        SpringApplication.run(SpringBootStartApplication.class, args);
    }

}
