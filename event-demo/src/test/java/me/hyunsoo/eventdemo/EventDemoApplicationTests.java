package me.hyunsoo.eventdemo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;


class EventDemoApplicationTests {

    @Test
    void contextLoads() {
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println(localDateTime);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        System.out.println(localDateTime.format(formatter));
    }

}
