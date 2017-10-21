package com.github.glavvlad.fluxreactiveclient;


import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class Runner {

	@Bean
	ApplicationRunner run(WebClient webClient) {
		return args -> webClient.get()
				.uri("")
				.retrieve()
				.bodyToFlux(Movie.class)
				.filter(m -> "Javatar".equalsIgnoreCase(m.getTitle()))
				.flatMap(m -> webClient.get().uri("/{id}/events", m.getId())
						.retrieve()
						.bodyToFlux(MovieEvent.class))
				.subscribe(System.out::println);
	}
}
