package me.hyunsoo.eventdemo;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberController {

    final MemberEventPublisher memberEventPublisher;

    @GetMapping("/member")
    public Member getMember(Member member){
        System.out.println("========MVC CALL=============");
        System.out.println(Thread.currentThread().getName());
        memberEventPublisher.publish(member);
        System.out.println("============================");
        return member;
    }
}
