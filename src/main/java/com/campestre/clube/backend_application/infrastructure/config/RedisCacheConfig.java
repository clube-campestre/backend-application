package com.campestre.clube.backend_application.infrastructure.config;

import java.time.Duration;
import java.util.Map;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;

@Configuration
public class RedisCacheConfig {

    @Bean
    public RedisCacheManager redisCacheManager(RedisConnectionFactory cf) {

        // Serializador que converte objetos em JSON
        var json = new GenericJackson2JsonRedisSerializer();

        // Configuração padrão para todos os caches
        var defaults = RedisCacheConfiguration.defaultCacheConfig()
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(json))
                .disableCachingNullValues()
                .prefixCacheNameWith("app::")
                .entryTtl(Duration.ofMinutes(5)); // TTL padrão de 5 minutos

        // Configurações específicas por cache
        var perCache = Map.of(
                "produtoPorId", defaults.entryTtl(Duration.ofMinutes(10)),
                "listaProdutos", defaults.entryTtl(Duration.ofSeconds(30))
        );

        return RedisCacheManager.builder(cf)
                .cacheDefaults(defaults)
                .withInitialCacheConfigurations(perCache)
                .build();
    }
}
