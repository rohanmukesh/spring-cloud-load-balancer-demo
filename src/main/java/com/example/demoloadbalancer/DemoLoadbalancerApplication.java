package com.example.demoloadbalancer;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
public class DemoLoadbalancerApplication {

    public static void main(String[] args) {

        ConfigurableApplicationContext ctx = new SpringApplicationBuilder(DemoLoadbalancerApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        WebClient loadHelloBalancedClient = ctx.getBean("hello", WebClient.Builder.class).build();

        for(int i = 1; i <= 10; i++) {
            String response =
                    loadHelloBalancedClient.get().uri("http://hello-service/api/customer")
                            .retrieve().toEntity(String.class)
                            .block().getBody();
            System.out.println(response);
        }

        WebClient loadBalancedClient = ctx.getBean("webClientBuilder", WebClient.Builder.class).build();

        for(int i = 1; i <= 10; i++) {
            String response =
                    loadBalancedClient.get().uri("http://demo-service/api/customer")
                            .retrieve().toEntity(String.class)
                            .block().getBody();
            System.out.println(response);
        }
    }

}
