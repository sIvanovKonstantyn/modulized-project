package com.home.demos.samplebankmodule.repositories.payments;

import com.home.demos.samplebankmodule.repositories.payments.entities.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    List<Payment> findAllByClientId(Long clientId);
}
