package com.github.glavvlad.fluxreactiveclient

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import org.springframework.web.reactive.function.client.ExchangeFilterFunctions
import org.springframework.web.reactive.function.client.WebClient
import java.util.*

@SpringBootApplication
class FluxReactiveClientApplication {

    @Bean
    fun webClient() = WebClient.create("http://localhost:8080/movies")
            .mutate()
            .filter(ExchangeFilterFunctions.basicAuthentication("Vlad", "pass"))
            .build()
}

fun main(args: Array<String>) {
    SpringApplication.run(FluxReactiveClientApplication::class.java, *args)
}

data class MovieEvent(var movieId: String? = null, var date: Date? = null)

data class Movie(var id: String? = null, var title: String? = null)