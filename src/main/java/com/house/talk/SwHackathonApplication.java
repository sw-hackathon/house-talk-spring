package com.house.talk;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.openfeign.EnableFeignClients;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

@EnableFeignClients
@SpringBootApplication
public class SwHackathonApplication {

    static {
        System.setProperty("com.amazonaws.sdk.disableEc2Metadata", "true");
    }

    public static void main(String[] args) {
        new SpringApplicationBuilder(SwHackathonApplication.class)
                .properties("spring.config.location=classpath:application.properties")
                .run(args);
    }

    @PostConstruct
    public void started(){
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Seoul"));
    }
}
