package kr.hhplus.be.server;

import jakarta.annotation.PreDestroy;
import org.springframework.context.annotation.Configuration;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.utility.DockerImageName;

@Configuration
class TestcontainersConfiguration {
	public static final GenericContainer<?> REDIS_CONTAINER;
	public static final MySQLContainer<?> MYSQL_CONTAINER;

	static {
		MYSQL_CONTAINER = new MySQLContainer<>(DockerImageName.parse("mysql:8.0"))
			.withDatabaseName("hhplus")
			.withUsername("test")
			.withPassword("test");
		MYSQL_CONTAINER.start();

		System.setProperty("spring.datasource.url", MYSQL_CONTAINER.getJdbcUrl() + "?characterEncoding=UTF-8&serverTimezone=UTC");
		System.setProperty("spring.datasource.username", MYSQL_CONTAINER.getUsername());
		System.setProperty("spring.datasource.password", MYSQL_CONTAINER.getPassword());

		REDIS_CONTAINER = new GenericContainer<>(DockerImageName.parse("redis:7.0"))
				.withExposedPorts(6379); // Redis 기본 포트 6379 노출
		REDIS_CONTAINER.start();

		// Redis 연결 정보 환경 변수로 설정
		String redisHost = REDIS_CONTAINER.getHost();
		Integer redisPort = REDIS_CONTAINER.getMappedPort(6379);

		System.setProperty("spring.redis.host", redisHost);
		System.setProperty("spring.redis.port", String.valueOf(redisPort));
	}

	@PreDestroy
	public void preDestroy() {
		if (MYSQL_CONTAINER.isRunning()) {
			MYSQL_CONTAINER.stop();
		}
		// Redis 컨테이너 종료
		if (REDIS_CONTAINER.isRunning()) {
			REDIS_CONTAINER.stop();
		}
	}
}