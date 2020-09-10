package com.hero.heroa.dreport;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@MapperScan("com.hero.heroa.dreport.dao")
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients("com.hero.heroa.dreport.feign")
public class HeroaDreportApplication {

    public static void main(String[] args) {
        SpringApplication.run(HeroaDreportApplication.class, args);
    }

}
