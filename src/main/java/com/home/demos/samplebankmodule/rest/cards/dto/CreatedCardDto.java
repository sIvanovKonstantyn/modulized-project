package com.home.demos.samplebankmodule.rest.cards.dto;

import lombok.Data;

@Data
public class CreatedCardDto {
    private Long id;
    private String title;
    private Long clientId;
    private Long sum;
}
