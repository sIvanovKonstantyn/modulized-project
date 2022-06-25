package com.home.demos.samplebankmodule.model;

import com.home.demos.samplebankmodule.config.SampleBankModuleConfiguration;
import lombok.Data;

import java.util.List;

@Data
public class Card {
    private Long id;
    private String title;
    private Long clientId;
    private Long sum;

    public Card(Long clientId) {
        this.clientId = clientId;
    }

    public Card() {
    }

    public Card create() {
        return SampleBankModuleConfiguration
                .cardRepository()
                .save(this);
    }

    public List<Card> findAllLikeThis() {
        return SampleBankModuleConfiguration
                .cardRepository()
                .findAllByClientId(this.clientId);
    }
}
