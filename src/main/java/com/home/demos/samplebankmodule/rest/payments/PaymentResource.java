package com.home.demos.samplebankmodule.rest.payments;

import com.google.gson.Gson;
import com.home.demos.samplebankmodule.infra.ModelMapper;
import com.home.demos.samplebankmodule.model.Payment;
import com.home.demos.samplebankmodule.rest.payments.dto.CreatePaymentDto;
import com.home.demos.samplebankmodule.rest.payments.dto.CreatedPaymentDto;
import spark.Route;

import java.util.Collections;
import java.util.Optional;
import java.util.stream.Collectors;

public class PaymentResource {
    private static final Gson gson = new Gson();
    private static final ModelMapper modelMapper = new ModelMapper();

    public static Route create() {
        return (request, response) -> Optional.of(gson.fromJson(request.body(), CreatePaymentDto.class))
                .map(modelMapper::map)
                .map(Payment::create)
                .map(CreatedPaymentDto::new)
                .map(gson::toJson)
                .orElse("TODO: ERROR");
    }

    public static Route findAllForClient() {
        return (request, response) ->
                gson.toJson(
                        Optional.of(request.params(":clientId"))
                                .map(Long::parseLong)
                                .map(Payment::new)
                                .map(Payment::findAllLikeThis)
                                .orElse(Collections.emptyList())
                                .stream()
                                .map(CreatedPaymentDto::new)
                                .collect(Collectors.toList())
                );
    }
}
