package com.home.demos.samplebankmodule.repositories.payments;

import com.home.demos.samplebankmodule.repositories.payments.entities.Payment;

import java.util.List;

public interface PaymentRepository {
    List<Payment> findAllByClientId(Long clientId);

    Payment save(Payment payment);
}
