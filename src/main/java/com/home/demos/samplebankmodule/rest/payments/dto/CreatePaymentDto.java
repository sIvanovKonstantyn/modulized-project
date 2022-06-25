package com.home.demos.samplebankmodule.rest.payments.dto;

import lombok.Data;

@Data
public class CreatePaymentDto {
    private String title;
    private Long clientId;
    private String target;
    private Long sum;
}
