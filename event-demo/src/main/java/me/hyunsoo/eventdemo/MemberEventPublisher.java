package me.hyunsoo.eventdemo;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;


@RequiredArgsConstructor
@Component
public class MemberEventPublisher {

    private final ApplicationEventPublisher applicationEventPublisher;

    public void publish(Member member){
        applicationEventPublisher.publishEvent(member);
    }


}
