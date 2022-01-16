package me.twitter.producer.service;

import lombok.RequiredArgsConstructor;
import me.twitter.producer.Twitter;
import me.twitter.producer.kafka.TwitterProducer;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class TwitterServiceImpl implements TwitterService {

    private final TwitterProducer twitterProducer;

    @Async
    @Override
    public void produceTwitter(String key, Twitter twitter) {
        twitterProducer.produceTwitter(key, twitter);
    }
}
