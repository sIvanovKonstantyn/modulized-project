package com.home.demos.samplebankmodule.repositories.cards.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private String title;
    @Column
    private Long clientId;
    @Column
    private Long sum;
}
