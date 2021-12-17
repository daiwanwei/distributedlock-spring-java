package com.example.distributedlockspringjava.config;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.integration.redis.util.RedisLockRegistry;

@Configuration
public class RedisConfig {
    @Bean
    public RedisLockRegistry redisLockRegistry() {
        return new RedisLockRegistry(redisConnectionFactory(), "lock");
    }

    @Bean
    @ConfigurationProperties(prefix = "spring.redis")
    public LettuceConnectionFactory redisConnectionFactory() {
        return new LettuceConnectionFactory();
    }
}
