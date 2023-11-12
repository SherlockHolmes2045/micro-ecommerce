package com.clientui.beans;

import lombok.Data;

@Data
public class PaymentBean {

    private int id;

    private Integer idCommande;

    private Double montant;

    private Long numeroCarte;

    @Override
    public String toString() {
        return "PaymentBean{" +
                "id=" + id +
                ", idCommande=" + idCommande +
                ", montant=" + montant +
                ", numeroCarte=" + numeroCarte +
                '}';
    }
}
