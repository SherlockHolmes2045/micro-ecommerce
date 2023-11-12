package com.mpaiement.dao;

import com.mpaiement.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentDao extends JpaRepository<Payment, Integer>{

    Payment findById(int id);
}
