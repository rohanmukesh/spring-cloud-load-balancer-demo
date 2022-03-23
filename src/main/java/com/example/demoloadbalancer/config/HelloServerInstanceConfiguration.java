package com.example.demoloadbalancer.config;

import com.example.demoloadbalancer.DemoInstanceSupplier;
import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class HelloServerInstanceConfiguration {

    @Bean(name = "hellolist")
    ServiceInstanceListSupplier helloServiceInstanceListSupplier() {
        return new DemoInstanceSupplier("hello-service");

    }
}
