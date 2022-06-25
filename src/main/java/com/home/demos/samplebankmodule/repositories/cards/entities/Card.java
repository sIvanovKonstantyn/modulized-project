package com.home.demos.samplebankmodule.repositories.cards.entities;

import lombok.Data;

@Data
public class Card {
    private Long id;
    private String title;
    private Long clientId;
    private Long sum;
}
