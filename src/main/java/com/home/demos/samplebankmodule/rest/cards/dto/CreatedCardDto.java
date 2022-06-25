package com.home.demos.samplebankmodule.rest.cards.dto;

import com.home.demos.samplebankmodule.model.Card;
import lombok.Data;

@Data
public class CreatedCardDto {
    private Long id;
    private String title;
    private Long clientId;
    private Long sum;

    public CreatedCardDto(Card payment) {
        this.id = payment.getId();
        this.title = payment.getTitle();
        this.clientId = payment.getClientId();
        this.sum = payment.getSum();
    }

    public CreatedCardDto() {
    }
}
