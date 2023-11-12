package com.mpaiement.web.controller;

import com.mpaiement.beans.OrderBean;
import com.mpaiement.dao.PaymentDao;
import com.mpaiement.model.Payment;
import com.mpaiement.proxies.OrderMicroserviceProxy;
import com.mpaiement.web.exceptions.ExistingPaymentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class PaymentController {

    @Autowired
    PaymentDao paymentDao;

    @Autowired
    OrderMicroserviceProxy orderMicroserviceProxy;


    @PostMapping(value = "/paiement")
    public ResponseEntity<Payment> payOrder(@RequestBody Payment payment) {


        Optional<Payment> existingPayment = paymentDao.findById(payment.getIdCommande());
        if (!existingPayment.isPresent()) throw new ExistingPaymentException("Cette commande est déjà payée");


        Payment newPayment = paymentDao.save(payment);


        Optional<OrderBean> orderRequest = orderMicroserviceProxy.getOrder(payment.getIdCommande());


        OrderBean order = orderRequest.get();

        order.setCommandePayee(true);

        orderMicroserviceProxy.updateOrder(order);

        return new ResponseEntity<>(newPayment, HttpStatus.CREATED);

    }


}
