package com.mcommerce.microserviceexpedition.repository;

import com.mcommerce.microserviceexpedition.model.Expedition;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpeditionRepository extends JpaRepository<Expedition,Long> {
}
