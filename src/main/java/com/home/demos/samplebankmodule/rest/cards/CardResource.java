package com.home.demos.samplebankmodule.rest.cards;

import com.home.demos.samplebankmodule.rest.cards.dto.CreateCardDto;
import com.home.demos.samplebankmodule.rest.cards.dto.CreatedCardDto;
import com.home.demos.samplebankmodule.services.cards.CardService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/cards")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CardResource {

    private final CardService cardService;

    @Inject
    public CardResource(CardService paymentService) {
        this.cardService = paymentService;
    }

    @POST
    public CreatedCardDto create(CreateCardDto createPaymentDto) {
        return cardService.create(createPaymentDto);
    }

    @GET
    @Path("/{clientId}")
    public List<CreatedCardDto> findAllForClient(@PathParam("clientId") Long clientId) {
        return cardService.findAllForClient(clientId);
    }
}
