package me.hyunsoo.eventdemo;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class MemberEventListener{

    @Async
    @EventListener(Member.class)
    public void onApplicationEvent(Member event) {
        try {
            Thread.sleep(10000);
        }catch (Exception e){}

        System.out.println("===============================");
        System.out.println(Thread.currentThread().getName());
        System.out.println(event.getId());
        System.out.println(event.getName());
        System.out.println("===============================");


    }
}
