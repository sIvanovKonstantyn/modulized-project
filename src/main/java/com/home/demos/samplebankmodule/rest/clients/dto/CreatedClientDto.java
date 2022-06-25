package com.home.demos.samplebankmodule.rest.clients.dto;

import com.home.demos.samplebankmodule.model.Client;
import lombok.Data;

@Data
public class CreatedClientDto {
    private Long id;
    private String name;

    public CreatedClientDto(Client client) {
        this.id = client.getId();
        this.name = client.getName();
    }

    public CreatedClientDto() {
    }
}
