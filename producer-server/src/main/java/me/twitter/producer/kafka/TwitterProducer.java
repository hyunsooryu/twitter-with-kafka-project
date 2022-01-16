package me.twitter.producer.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import me.twitter.producer.Twitter;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.util.StringUtils;

import java.util.Objects;
import java.util.Properties;
import java.util.function.Consumer;

@Slf4j
@Component
public class TwitterProducer {

    private static final String TOPIC = "demo.twitter";

    private final Properties twitterProducerProperties;
    private final ObjectMapper objectMapper;

    TwitterProducer(ObjectMapper objectMapper){
        Properties properties = new Properties();
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "my-kafka:9092");
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        this.twitterProducerProperties = properties;
        this.objectMapper = objectMapper;
    }

    @Async
    public void produceTwitter(String key, Twitter twitter){

        if(!isValidKeyAndTwitter(key, twitter))return;

        String jsonTwitter = convertTwitterToJson(twitter);

        if(!StringUtils.hasLength(jsonTwitter))return;

        KafkaProducer<String, String> kafkaProducer = new KafkaProducer<String, String>(twitterProducerProperties);

        ProducerRecord<String, String> record = new ProducerRecord<>(TOPIC, key, jsonTwitter);

        kafkaProducer.send(record, (recordMetadata, e)->{
            if (e == null) { //잘 보내졌을 경우
                log.info(
                         "=================================================\n"
                        + "Topic:" + recordMetadata.topic() + "\n"
                        + "Partition : " + recordMetadata.partition() + "\n"
                        + "Offset : " + recordMetadata.offset() + "\n"
                        + "Timestamp : " + recordMetadata.timestamp()
                        + "=================================================\n");
            } else { // 익셉션이 발현했을 경우
                log.error("Error while producing", e);
            }
        });
        kafkaProducer.flush();
        kafkaProducer.close();
    }

    private static boolean isValidKeyAndTwitter(String key, Twitter twitter){

       if(!StringUtils.hasLength(key)){
           log.info("unvalid key");
           return false;
       }

       if(Objects.isNull(twitter.getId())){
           log.info("unvalid twitter id");
           return false;
       }

       if(twitter.getId() <= 0){
           log.info("twitter id can't be <= 0");
           return false;
       }

       if(!StringUtils.hasLength(twitter.getMessage())  || !StringUtils.hasLength(twitter.getAuthor())){
           log.info("twitter message and author should be not empty");
           return false;
       }

       return true;

    }

    private String convertTwitterToJson(Twitter twitter){
        String json = "";
        try {
            json = objectMapper.writeValueAsString(twitter);
        }catch (Exception e){
            log.info("twitter to json converting error");
            json = "";
        }
        return json;
    }
}
