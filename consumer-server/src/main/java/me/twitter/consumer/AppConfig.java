package me.twitter.consumer;

import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

@Configuration
public class AppConfig {

    @Value("${twitter.bootstrap-servers-config}")
    private String bootstrap_servers_config;
    @Value("${twitter.group-id-config}")
    private String groupIdConfig;
    @Value("${twitter.auto-offset-reset-config}")
    private String autoOffsetResetConfig;

    @Bean
    public Properties twitterProperties(){
        Properties properties = new Properties();
        properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrap_servers_config);
        properties.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, autoOffsetResetConfig);
        properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, groupIdConfig);
        properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        return properties;
    }
}
