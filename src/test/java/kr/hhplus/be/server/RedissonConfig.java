package kr.hhplus.be.server;

import jakarta.annotation.PreDestroy;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.utility.DockerImageName;

@Configuration
class RedissionConfig {

    @Bean
    public RedissonClient redissonClient() {
        // Configuration for Redisson client
        // Redis 컨테이너에서 설정된 호스트와 포트를 사용
        String redisHost = System.getProperty("spring.redis.host", "localhost");
        String redisPort = System.getProperty("spring.redis.port", "6379");

        Config config = new Config();
        config.useSingleServer()
                .setAddress("redis://" + redisHost + ":" + redisPort)
                .setConnectionPoolSize(10)
                .setConnectionMinimumIdleSize(2);

        return Redisson.create(config);
    }
}

