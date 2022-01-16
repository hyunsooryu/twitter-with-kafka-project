package me.twitter.consumer.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.twitter.consumer.Twitter;
import me.twitter.consumer.TwitterRepository;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

@Slf4j
//@Component
@RequiredArgsConstructor
public class TwitterConsumer implements ApplicationRunner {

    private final Properties twitterProperties;

    private final TwitterRepository twitterRepository;

    private final ObjectMapper objectMapper;

    private static final String TOPIC = "demo.twitter";

    @Override
    public void run(ApplicationArguments args) throws Exception {
        KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(twitterProperties);
        consumer.subscribe(Collections.singleton(TOPIC));

        while(true){
            ConsumerRecords<String,String> records = consumer.poll(Duration.ofMillis(1000));
            for(ConsumerRecord<String, String> record : records){
                log.info("key : " + record.key() + " val : " + record.value());
                log.info("partition : " + record.partition() + " offset : " + record.offset());
                try{
                    Twitter twitter = convertStringtoTwitter(record.value());
                    twitterRepository.save(twitter);
                }catch (Exception e){
                    log.error("error making twitter object", e);
                }
            }
        }
    }

    private Twitter convertStringtoTwitter(String json) throws JsonProcessingException {
        Twitter twitter = objectMapper.readValue(json, Twitter.class);
        return twitter;
    }
}
