package com.clientui.proxies;

import com.clientui.beans.ExpeditionBean;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "microservice-expedition", url = "localhost:9106")
public interface MicroserviceExpeditionProxy {

    @GetMapping("/expedition/{id}")
    ExpeditionBean etatExpedition(@PathVariable("id") int id);
}
