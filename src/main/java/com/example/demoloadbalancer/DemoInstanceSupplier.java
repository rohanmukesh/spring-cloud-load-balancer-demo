package com.example.demoloadbalancer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.DefaultServiceInstance;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import reactor.core.publisher.Flux;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;

@Slf4j
public class DemoInstanceSupplier implements ServiceInstanceListSupplier {

    private final String serviceId;

    private List<String> dynamicIps;

    public DemoInstanceSupplier(String serviceId) {
        this.serviceId = serviceId;
    }

    @PostConstruct
    public void loadIPsfromAWS() {
        dynamicIps = Arrays.asList("localhost:8080", "localhost:8081");
    }

    @Override
    public String getServiceId() {
        return serviceId;
    }

    @Override
    public Flux<List<ServiceInstance>> get() {
        log.info("ServiceName:{}", serviceId);
        return Flux.just(Arrays
                .asList(new DefaultServiceInstance(serviceId + "1", serviceId, dynamicIps.get(0).split(":")[0], Integer.parseInt(dynamicIps.get(0).split(":")[1]), false),
                        new DefaultServiceInstance(serviceId + "2", serviceId, dynamicIps.get(1).split(":")[0], Integer.parseInt(dynamicIps.get(1).split(":")[1]), false)));
    }
}
