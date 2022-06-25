package com.home.demos.samplebankmodule.repositories.payments.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private String title;
    @Column
    private Long clientId;
    @Column
    private String target;
    @Column
    private Long sum;
}
