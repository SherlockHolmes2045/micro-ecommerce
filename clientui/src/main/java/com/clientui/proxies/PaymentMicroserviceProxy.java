package com.clientui.proxies;

import com.clientui.beans.PaymentBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "microservice-paiement", url = "localhost:9003")
public interface PaymentMicroserviceProxy {

    @PostMapping(value = "/paiement")
    ResponseEntity<PaymentBean> payOrder(@RequestBody PaymentBean paiement);

}
