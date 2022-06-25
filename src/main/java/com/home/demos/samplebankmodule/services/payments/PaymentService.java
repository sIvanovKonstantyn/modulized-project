package com.home.demos.samplebankmodule.services.payments;

import com.home.demos.samplebankmodule.infra.ModelMapper;
import com.home.demos.samplebankmodule.repositories.payments.PaymentRepository;
import com.home.demos.samplebankmodule.repositories.payments.entities.Payment;
import com.home.demos.samplebankmodule.rest.payments.dto.CreatePaymentDto;
import com.home.demos.samplebankmodule.rest.payments.dto.CreatedPaymentDto;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class PaymentService {

    private final ModelMapper mapper;
    private final PaymentRepository repository;

    @Inject
    public PaymentService(ModelMapper mapper, PaymentRepository repository) {
        this.mapper = mapper;
        this.repository = repository;
    }

    public CreatedPaymentDto create(CreatePaymentDto createPaymentDto) {
        Payment payment = mapper.map(createPaymentDto);
        payment = repository.save(payment);

        return mapper.map(payment);
    }

    public List<CreatedPaymentDto> findAllForClient(Long clientId) {
        return repository.findAllByClientId(clientId).stream()
                .map(mapper::map)
                .collect(Collectors.toList());

    }
}
