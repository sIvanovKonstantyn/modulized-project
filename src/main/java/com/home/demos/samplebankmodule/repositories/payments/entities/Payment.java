package com.home.demos.samplebankmodule.repositories.payments.entities;

import lombok.Data;

@Data
public class Payment {
    private Long id;
    private String title;
    private Long clientId;
    private String target;
    private Long sum;
}
