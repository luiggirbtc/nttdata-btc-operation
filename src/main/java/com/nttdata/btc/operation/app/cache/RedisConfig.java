package com.nttdata.btc.operation.app.cache;

import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * Class configuration redis.
 *
 * @author lrs
 */
@Configuration
@EnableConfigurationProperties(RedisProperties.class)
public class RedisConfig {
    @Bean
    public RedisTemplate<String, RedisOperation> redisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<String, RedisOperation> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);
        return template;
    }
}