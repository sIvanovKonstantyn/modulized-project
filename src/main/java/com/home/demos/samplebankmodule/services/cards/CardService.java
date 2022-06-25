package com.home.demos.samplebankmodule.services.cards;

import com.home.demos.samplebankmodule.repositories.cards.CardRepository;
import com.home.demos.samplebankmodule.repositories.cards.entities.Card;
import com.home.demos.samplebankmodule.rest.cards.dto.CreateCardDto;
import com.home.demos.samplebankmodule.rest.cards.dto.CreatedCardDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CardService {

    private final ModelMapper mapper;
    private final CardRepository repository;

    @Autowired
    public CardService(ModelMapper mapper, CardRepository repository) {
        this.mapper = mapper;
        this.repository = repository;
    }

    public CreatedCardDto create(CreateCardDto createCardDto) {
        Card card = mapper.map(createCardDto, Card.class);
        card = repository.save(card);

        return mapper.map(card, CreatedCardDto.class);
    }

    public List<CreatedCardDto> findAllForClient(Long clientId) {
        return repository.findAllByClientId(clientId).stream()
                .map(card -> mapper.map(card, CreatedCardDto.class))
                .collect(Collectors.toList());

    }
}
