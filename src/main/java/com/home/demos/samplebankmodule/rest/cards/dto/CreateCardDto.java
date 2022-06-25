package com.home.demos.samplebankmodule.rest.cards.dto;

import lombok.Data;

@Data
public class CreateCardDto {
    private String title;
    private Long clientId;
}
