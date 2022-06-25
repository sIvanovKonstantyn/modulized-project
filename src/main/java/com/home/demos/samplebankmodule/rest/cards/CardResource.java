package com.home.demos.samplebankmodule.rest.cards;

import com.home.demos.samplebankmodule.rest.cards.dto.CreateCardDto;
import com.home.demos.samplebankmodule.rest.cards.dto.CreatedCardDto;
import com.home.demos.samplebankmodule.services.cards.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cards")
public class CardResource {

    private final CardService cardService;

    @Autowired
    public CardResource(CardService paymentService) {
        this.cardService = paymentService;
    }

    @PostMapping
    public ResponseEntity<CreatedCardDto> create(@RequestBody CreateCardDto createPaymentDto) {
        return ResponseEntity.ok().body(cardService.create(createPaymentDto));
    }

    @GetMapping("/{clientId}")
    public List<CreatedCardDto> findAllForClient(@PathVariable Long clientId) {
        return cardService.findAllForClient(clientId);
    }
}
