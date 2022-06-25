package com.home.demos.samplebankmodule.repositories.cards;

import com.home.demos.samplebankmodule.repositories.cards.entities.Card;
import com.home.demos.samplebankmodule.repositories.payments.entities.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CardRepository extends JpaRepository<Card, Long> {
    List<Payment> findAllByClientId(Long clientId);
}
