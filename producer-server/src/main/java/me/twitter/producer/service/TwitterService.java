package me.twitter.producer.service;

import me.twitter.producer.Twitter;

public interface TwitterService {

    void produceTwitter(String key, Twitter twitter);
}
