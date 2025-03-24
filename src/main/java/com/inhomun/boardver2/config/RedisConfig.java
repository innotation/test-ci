package com.inhomun.boardver2.config;

import com.inhomun.boardver2.dto.MailDTO;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
public class RedisConfig {

    @Bean
    public RedisTemplate<String, MailDTO> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, MailDTO> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);
        return template;
    }
}
