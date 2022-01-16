package me.twitter.consumer;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class JpaMainRunner implements ApplicationRunner {

    private final MemberRepository memberRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Member member1 = memberRepository.findById(150L).orElse(new Member());
        System.out.println("===============================================");
        Member member2 = memberRepository.findById(150L).orElse(new Member());
        System.out.println("===============================================");
        Member member3 = memberRepository.findById(150L).orElse(new Member());
        System.out.println("===============================================");
        Member member4 = memberRepository.findById(150L).orElse(new Member());
        System.out.println("===============================================");
        Member member5 = memberRepository.findById(150L).orElse(new Member());
    }
}
