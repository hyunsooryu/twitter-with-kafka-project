package me.twitter.consumer;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;



@SpringBootApplication
public class ConsumerApplication {

    public static void main(String[] args) {

        var app = new SpringApplication(ConsumerApplication.class);
        app.setWebApplicationType(WebApplicationType.NONE);
        app.run(args);

    }

}
