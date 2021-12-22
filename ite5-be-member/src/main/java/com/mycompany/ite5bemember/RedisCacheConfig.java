package com.mycompany.ite5bemember;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;


@Configuration
@EnableCaching
public class RedisCacheConfig extends CachingConfigurerSupport {
	
	private final int DEFAULT_EXPIRE_SECONDS = 600;
	
	private final String homeorderimg = "homeorderimg";
	private final int HOMEORDERIMG_EXPIRE_SECONDS = 10;
	
	
	
	@Resource
	RedisConnectionFactory redisConnectionFactory;
	
	@Bean
	@Override 
	public CacheManager cacheManager() { 
		RedisCacheConfiguration configuration = RedisCacheConfiguration.defaultCacheConfig()
					.serializeValuesWith(RedisSerializationContext.SerializationPair
							.fromSerializer(new GenericJackson2JsonRedisSerializer()))
					.entryTtl(Duration.ofSeconds(DEFAULT_EXPIRE_SECONDS)); 
		
		
		Map<String, RedisCacheConfiguration> cacheConfigurations = new HashMap<>();
		
		// HomeOrderImg
		cacheConfigurations.put(homeorderimg, RedisCacheConfiguration.defaultCacheConfig()
						.serializeValuesWith(RedisSerializationContext.SerializationPair
								.fromSerializer(new GenericJackson2JsonRedisSerializer()))
						.entryTtl(Duration.ofSeconds(HOMEORDERIMG_EXPIRE_SECONDS)));
		
		RedisCacheManager.RedisCacheManagerBuilder builder = RedisCacheManager.RedisCacheManagerBuilder.fromConnectionFactory(redisConnectionFactory); 
		builder.cacheDefaults(configuration).withInitialCacheConfigurations(cacheConfigurations); 
		return builder.build(); 
	}

	

	
}
