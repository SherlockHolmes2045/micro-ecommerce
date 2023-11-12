package com.clientui.proxies;

import com.clientui.beans.OrderBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "microservice-commandes", url = "localhost:9002")
public interface OrderMicroserviceProxy {

    @PostMapping(value = "/commandes")
    OrderBean addOrder(@RequestBody OrderBean commande);
}
