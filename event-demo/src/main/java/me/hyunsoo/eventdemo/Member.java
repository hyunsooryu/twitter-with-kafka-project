package me.hyunsoo.eventdemo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.context.ApplicationEvent;

@Getter
@Setter
@Builder
@ToString
public class Member{

    private Long id;
    private String name;
}
