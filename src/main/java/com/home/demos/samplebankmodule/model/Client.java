package com.home.demos.samplebankmodule.model;

import com.home.demos.samplebankmodule.config.SampleBankModuleConfiguration;
import lombok.Data;

import java.util.List;

@Data
public class Client {
    private Long id;
    private String name;

    public Client create() {
        return SampleBankModuleConfiguration
                .clientRepository()
                .save(this);
    }

    public List<Client> findAll() {
        return SampleBankModuleConfiguration
                .clientRepository()
                .findAll();
    }
}
