package com.home.demos.samplebankmodule.rest.payments;

import com.home.demos.samplebankmodule.rest.payments.dto.CreatePaymentDto;
import com.home.demos.samplebankmodule.rest.payments.dto.CreatedPaymentDto;
import com.home.demos.samplebankmodule.services.payments.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/payments")
public class PaymentResource {

    private final PaymentService paymentService;

    @Autowired
    public PaymentResource(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping
    public ResponseEntity<CreatedPaymentDto> create(@RequestBody CreatePaymentDto createPaymentDto) {
        return ResponseEntity.ok().body(paymentService.create(createPaymentDto));
    }

    @GetMapping("/{clientId}")
    public List<CreatedPaymentDto> findAllForClient(@PathVariable Long clientId) {
        return paymentService.findAllForClient(clientId);
    }
}
