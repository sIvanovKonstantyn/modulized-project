package com.home.demos.samplebankmodule.services.payments;

import com.home.demos.samplebankmodule.repositories.payments.PaymentRepository;
import com.home.demos.samplebankmodule.repositories.payments.entities.Payment;
import com.home.demos.samplebankmodule.rest.payments.dto.CreatePaymentDto;
import com.home.demos.samplebankmodule.rest.payments.dto.CreatedPaymentDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PaymentService {

    private final ModelMapper mapper;
    private final PaymentRepository repository;

    @Autowired
    public PaymentService(ModelMapper mapper, PaymentRepository repository) {
        this.mapper = mapper;
        this.repository = repository;
    }

    public CreatedPaymentDto create(CreatePaymentDto createPaymentDto) {
        Payment payment = mapper.map(createPaymentDto, Payment.class);
        payment = repository.save(payment);

        return mapper.map(payment, CreatedPaymentDto.class);
    }

    public List<CreatedPaymentDto> findAllForClient(Long clientId) {
        return repository.findAllByClientId(clientId).stream()
                .map(payment -> mapper.map(payment, CreatedPaymentDto.class))
                .collect(Collectors.toList());

    }
}
