package com.home.demos.samplebankmodule.model;

import com.home.demos.samplebankmodule.config.SampleBankModuleConfiguration;
import lombok.Data;

import java.util.List;

@Data
public class Payment {
    private Long id;
    private String title;
    private Long clientId;
    private String target;
    private Long sum;

    public Payment(Long clientId) {
        this.clientId = clientId;
    }

    public Payment() {
    }

    public Payment create() {
        return SampleBankModuleConfiguration
                .paymentRepository()
                .save(this);
    }

    public List<Payment> findAllLikeThis() {
        return SampleBankModuleConfiguration
                .paymentRepository()
                .findAllByClientId(this.clientId);
    }
}
