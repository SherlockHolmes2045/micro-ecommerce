package com.mcommandes.web.controller;


import com.mcommandes.dao.OrderDao;
import com.mcommandes.model.Order;
import com.mcommandes.web.exceptions.OrderNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class OrderController {

    @Autowired
    OrderDao orderDao;

    @PostMapping(value = "/commandes")
    public ResponseEntity<Order> addOrder(@RequestBody Order order) {

        return new ResponseEntity<>(orderDao.save(order), HttpStatus.CREATED);
    }

    @GetMapping(value = "/commandes/{id}")
    public Optional<Order> getOrder(@PathVariable int id) {

        Optional<Order> order = orderDao.findById(id);

        if (!order.isPresent()) throw new OrderNotFoundException("Cette commande n'existe pas");

        return order;
    }

    @PutMapping(value = "/commandes")
    public void updateOrder(@RequestBody Order order) {

        orderDao.save(order);
    }
}
