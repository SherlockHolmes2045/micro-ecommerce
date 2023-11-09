package com.mcommerce.microserviceexpedition.model;


import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class Expedition {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "id_commande")
    private int idCommande;

    private int etat;
}
