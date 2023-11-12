package com.mpaiement.beans;

import lombok.Data;

import java.util.Date;

@Data
public class OrderBean {

    private int id;

    private Integer productId;

    private Date dateCommande;

    private Integer quantite;

    private Boolean commandePayee;

    @Override
    public String toString() {
        return "OrderBean{" +
                "id=" + id +
                ", productId=" + productId +
                ", dateCommande=" + dateCommande +
                ", quantite=" + quantite +
                ", commandePayee=" + commandePayee +
                '}';
    }
}