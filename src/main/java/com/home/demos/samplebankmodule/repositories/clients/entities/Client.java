package com.home.demos.samplebankmodule.repositories.clients.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private String name;
}
