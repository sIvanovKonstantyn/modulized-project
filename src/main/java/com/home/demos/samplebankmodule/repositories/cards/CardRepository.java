package com.home.demos.samplebankmodule.repositories.cards;

import com.home.demos.samplebankmodule.model.Card;

import java.util.List;

public interface CardRepository {
    List<Card> findAllByClientId(Long clientId);

    Card save(Card card);
}
