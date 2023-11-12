package com.mpaiement.proxies;

import com.mpaiement.beans.OrderBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@FeignClient(name = "microservice-commandes", url = "localhost:9002")
public interface OrderMicroserviceProxy {

    @GetMapping(value = "/commandes/{id}")
    Optional<OrderBean> getOrder(@PathVariable("id") int id);

    @PutMapping(value = "/commandes")
    void updateOrder(@RequestBody OrderBean commande);
}
