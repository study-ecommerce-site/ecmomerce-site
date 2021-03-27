package com.teckstudy.book;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Optional;
import java.util.UUID;

@EnableJpaAuditing
@SpringBootApplication
public class BookApplication {

	public static void main(String[] args) {

		SpringApplication.run(BookApplication.class, args);

	}

	@Bean
	public AuditorAware<String> auditorProvider() {
		// 람다 없이 구현
		return new AuditorAware<String>() {
			@Override
			public Optional<String> getCurrentAuditor() {
				return Optional.of(UUID.randomUUID().toString());
			}
		};
//		return () -> Optional.of(UUID.randomUUID().toString()); // 람다로 구현
	}

}
