package com.home.demos.samplebankmodule.rest.payments;

import com.home.demos.samplebankmodule.rest.payments.dto.CreatePaymentDto;
import com.home.demos.samplebankmodule.rest.payments.dto.CreatedPaymentDto;
import com.home.demos.samplebankmodule.services.payments.PaymentService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/payments")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PaymentResource {

    private final PaymentService paymentService;

    @Inject
    public PaymentResource(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @POST
    public CreatedPaymentDto create(CreatePaymentDto createPaymentDto) {
        return paymentService.create(createPaymentDto);
    }

    @GET
    @Path("/{clientId}")
    public List<CreatedPaymentDto> findAllForClient(@PathParam("clientId") Long clientId) {
        return paymentService.findAllForClient(clientId);
    }
}
