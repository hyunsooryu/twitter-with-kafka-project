package me.twitter.producer.controller;

import lombok.RequiredArgsConstructor;
import me.twitter.producer.Twitter;
import me.twitter.producer.service.TwitterService;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;

@RequiredArgsConstructor
@RestController
public class TwitterController {

    private final TwitterService twitterService;

    @PostMapping("/twitter")
    public String putTwitterToKafka(@RequestBody Twitter twitter, Locale locale){
       twitterService.produceTwitter(locale.toString(), twitter);
       return "produced";
    }
}
