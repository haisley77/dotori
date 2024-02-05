package com.dotori.backend.domain.book;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;

@TestConfiguration
public class TestRedisSessionConfig {
	@Bean
	public RedisConnectionFactory redisConnectionFactory() {
		return new LettuceConnectionFactory();
	}
	// @Bean
	// public SessionRepositoryFilter<MapSession> sessionRepositoryFilter(
	//     SessionRepository<MapSession> sessionRepository) {
	//     SessionRepositoryFilter<MapSession> sessionRepositoryFilter = new SessionRepositoryFilter<>(sessionRepository);
	//     return sessionRepositoryFilter;
	// }

	// @Bean
	// public SessionRepository<MapSession> sessionRepository() {
	//     MapSessionRepository sessionRepository = new MapSessionRepository(new ConcurrentHashMap<>());
	//     return sessionRepository;
	// }
}