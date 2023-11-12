package com.clientui.proxies;

import com.clientui.beans.ProductBean;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "zuul-server")
@RibbonClient(name = "microservice-produits")
public interface ProductsMicroserviceProxy {

    @GetMapping(value = "/microservice-produits/Produits")
    List<ProductBean> productList();


    @GetMapping( value = "/microservice-produits/Produits/{id}")
    ProductBean getProduct(@PathVariable("id") int id);



}
