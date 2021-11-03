package com.example.restapi.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.Duration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.cache.RedisCacheManagerBuilderCustomizer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@EnableCaching
public class Config {

    @Autowired
    private ObjectMapper objectMapper;

    void customizeObjectMapper() {
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }

    @Bean
    public Docket swaggerApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .paths(PathSelectors.regex("^(?!/(error).*$).*$"))
                .build();
    }

    @Bean
    public RedisCacheConfiguration cacheConfiguration() {
        return RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(Duration.ofMinutes(60))
                .disableCachingNullValues()
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer()));
    }

    @Bean
    public RedisCacheManagerBuilderCustomizer redisCacheManagerBuilderCustomizer() {
        return (builder) -> builder
                .withCacheConfiguration("owners",
                        RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofMinutes(2)))
                .withCacheConfiguration("singleOwner",
                        RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofMinutes(2)))
                .withCacheConfiguration("editOwner",
                        RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofMinutes(2)))
                .withCacheConfiguration("deleteOwner",
                        RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofMinutes(2)))
                .withCacheConfiguration("games",
                        RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofMinutes(2)))
                .withCacheConfiguration("singleGame",
                        RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofMinutes(2)))
                .withCacheConfiguration("editGame",
                        RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofMinutes(2)))
                .withCacheConfiguration("deleteGame",
                        RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofMinutes(2)))
                .withCacheConfiguration("interventions",
                        RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofMinutes(2)))
                .withCacheConfiguration("singleIntervention",
                        RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofMinutes(2)))
                .withCacheConfiguration("editIntervention",
                        RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofMinutes(2)))
                .withCacheConfiguration("deleteIntervention",
                        RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofMinutes(2)))
                .withCacheConfiguration("foods",
                        RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofMinutes(2)))
                .withCacheConfiguration("singleFood",
                        RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofMinutes(2)))
                .withCacheConfiguration("editFood",
                        RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofMinutes(2)))
                .withCacheConfiguration("deleteFood",
                        RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofMinutes(2)))
                .withCacheConfiguration("animals",
                        RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofMinutes(2)))
                .withCacheConfiguration("singleAnimal",
                        RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofMinutes(2)))
                .withCacheConfiguration("editAnimal",
                        RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofMinutes(2)))
                .withCacheConfiguration("deleteAnimal",
                        RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofMinutes(2)))
                .withCacheConfiguration("animalTypes",
                        RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofMinutes(2)))
                .withCacheConfiguration("singleAnimalType",
                        RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofMinutes(2)))
                .withCacheConfiguration("editAnimalType",
                        RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofMinutes(2)))
                .withCacheConfiguration("deleteAnimalType",
                        RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofMinutes(2)));
    }

}
