package com.clientui.controller;

import com.clientui.beans.OrderBean;
import com.clientui.beans.ExpeditionBean;
import com.clientui.beans.PaymentBean;
import com.clientui.beans.ProductBean;
import com.clientui.proxies.OrderMicroserviceProxy;
import com.clientui.proxies.ExpeditionMicroserviceProxy;
import com.clientui.proxies.PaymentMicroserviceProxy;
import com.clientui.proxies.ProductsMicroserviceProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;


@Controller
public class ClientController {

    @Autowired
    private ProductsMicroserviceProxy productProxy;

    @Autowired
    private OrderMicroserviceProxy orderProxy;

    @Autowired
    private PaymentMicroserviceProxy paymentProxy;

    @Autowired
    private ExpeditionMicroserviceProxy expeditionProxy;


    Logger log = LoggerFactory.getLogger(this.getClass());

    @RequestMapping("/")
    public String home(Model model) {


        log.info("Envoi requête vers microservice-produits");

        List<ProductBean> products = productProxy.productList();

        model.addAttribute("produits", products);


        return "Accueil";
    }


    @RequestMapping("/details-produit/{id}")
    public String ProductDetails(@PathVariable int id, Model model) {

        ProductBean product = productProxy.getProduct(id);

        model.addAttribute("produit", product);

        return "FicheProduit";
    }

    @RequestMapping(value = "/commander-produit/{idProduit}/{montant}")
    public String orderProduct(@PathVariable int idProduit, @PathVariable Double montant, Model model) {


        OrderBean order = new OrderBean();

        order.setProductId(idProduit);
        order.setQuantite(1);
        order.setDateCommande(new Date());


        OrderBean orderAdded = orderProxy.addOrder(order);

        model.addAttribute("commande", orderAdded);
        model.addAttribute("montant", montant);

        return "Paiement";
    }


    @RequestMapping(value = "/payer-commande/{idCommande}/{montantCommande}")
    public String payOrder(@PathVariable int idCommande, @PathVariable Double montantCommande, Model model) {

        PaymentBean paymentToExecute = new PaymentBean();

        paymentToExecute.setIdCommande(idCommande);
        paymentToExecute.setMontant(montantCommande);
        paymentToExecute.setNumeroCarte(numcarte()); // random card number

        ResponseEntity<PaymentBean> payment = paymentProxy.payOrder(paymentToExecute);

        boolean paymentSuccessful = payment.getStatusCode() == HttpStatus.CREATED;


        model.addAttribute("paiementOk", paymentSuccessful); // on envoi un Boolean paiementOk à la vue

        return "confirmation";
    }

    //Generate fake card number
    private Long numcarte() {

        return ThreadLocalRandom.current().nextLong(1000000000000000L, 9000000000000000L);
    }

    @RequestMapping("/suivi/{id}")
    public String expedition(@PathVariable int id, Model model) {

        ExpeditionBean expedition = expeditionProxy.expeditionState(id);

        model.addAttribute("expedition", expedition);

        return "Suivi";

    }
}
