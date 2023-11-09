package com.mcommerce.microserviceexpedition;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MicroserviceExpeditionApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroserviceExpeditionApplication.class, args);
    }

}
