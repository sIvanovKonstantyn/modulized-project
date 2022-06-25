package com.home.demos.samplebankmodule.rest.payments.dto;

import com.home.demos.samplebankmodule.model.Payment;
import lombok.Data;

@Data
public class CreatedPaymentDto {
    private Long id;
    private String title;
    private Long clientId;
    private String target;
    private Long sum;

    public CreatedPaymentDto(Payment payment) {
        this.id = payment.getId();
        this.title = payment.getTitle();
        this.clientId = payment.getClientId();
        this.target = payment.getTarget();
        this.sum = payment.getSum();
    }

    public CreatedPaymentDto() {
    }
}
