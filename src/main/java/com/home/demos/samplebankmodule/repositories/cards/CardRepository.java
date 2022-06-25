package com.home.demos.samplebankmodule.repositories.cards;

import com.home.demos.samplebankmodule.repositories.cards.entities.Card;
import com.home.demos.samplebankmodule.repositories.payments.entities.Payment;

import java.util.List;

public interface CardRepository {
    List<Card> findAllByClientId(Long clientId);

    Card save(Card card);
}
