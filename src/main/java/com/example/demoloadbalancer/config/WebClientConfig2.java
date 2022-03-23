package com.example.demoloadbalancer.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
@LoadBalancerClient(name = "hello-service", configuration = HelloServerInstanceConfiguration.class)
class WebClientConfig2 {

    @LoadBalanced
    @Bean(name = "hello")
    WebClient.Builder webClientBuilder() {
        return WebClient.builder();
    }
}