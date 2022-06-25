package com.home.demos.samplebankmodule.infra;

import com.home.demos.samplebankmodule.model.Card;
import com.home.demos.samplebankmodule.model.Client;
import com.home.demos.samplebankmodule.model.Payment;
import com.home.demos.samplebankmodule.rest.cards.dto.CreateCardDto;
import com.home.demos.samplebankmodule.rest.cards.dto.CreatedCardDto;
import com.home.demos.samplebankmodule.rest.clients.dto.CreateClientDto;
import com.home.demos.samplebankmodule.rest.clients.dto.CreatedClientDto;
import com.home.demos.samplebankmodule.rest.payments.dto.CreatePaymentDto;
import com.home.demos.samplebankmodule.rest.payments.dto.CreatedPaymentDto;

public class ModelMapper {

    public Client map(CreateClientDto createClientDto) {
        Client client = new Client();
        client.setName(createClientDto.getName());

        return client;
    }

    public CreatedClientDto map(Client client) {
        CreatedClientDto createdClientDto = new CreatedClientDto();
        createdClientDto.setId(client.getId());
        createdClientDto.setName(client.getName());

        return createdClientDto;
    }

    public Card map(CreateCardDto createCardDto) {
        Card card = new Card();
        card.setClientId(createCardDto.getClientId());
        card.setTitle(createCardDto.getTitle());

        return card;
    }

    public CreatedCardDto map(Card card) {
        CreatedCardDto createdCardDto = new CreatedCardDto();
        createdCardDto.setId(card.getId());
        createdCardDto.setClientId(card.getClientId());
        createdCardDto.setSum(card.getSum());
        createdCardDto.setTitle(card.getTitle());

        return createdCardDto;
    }

    public Payment map(CreatePaymentDto createPaymentDto) {
        Payment payment = new Payment();
        payment.setClientId(createPaymentDto.getClientId());
        payment.setSum(createPaymentDto.getSum());
        payment.setTitle(createPaymentDto.getTitle());
        payment.setTarget(createPaymentDto.getTarget());

        return payment;
    }

    public CreatedPaymentDto map(Payment payment) {
        CreatedPaymentDto createdPaymentDto = new CreatedPaymentDto();
        createdPaymentDto.setId(payment.getId());
        createdPaymentDto.setSum(payment.getSum());
        createdPaymentDto.setTarget(payment.getTarget());
        createdPaymentDto.setClientId(payment.getClientId());
        createdPaymentDto.setTitle(payment.getTitle());

        return createdPaymentDto;
    }
}
