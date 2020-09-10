package com.hero.heroa.webs;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@MapperScan("com.hero.heroa.webs.dao")
@EnableDiscoveryClient
public class HeroaWebsApplication {

    public static void main(String[] args) {
        SpringApplication.run(HeroaWebsApplication.class, args);
    }

}
