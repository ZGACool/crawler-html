package com.crawler.webmagic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author misterWei
 * @create 2018年10月07号:17点36分
 * @mailbox mynameisweiyan@gmail.com
 */
@SpringBootApplication
@EnableScheduling
public class StartApplication {
    public static void main(String[] args) {
        SpringApplication.run(StartApplication.class,args);
    }
}
