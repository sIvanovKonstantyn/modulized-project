package com.home.demos.samplebankmodule.rest.cards;

import com.google.gson.Gson;
import com.home.demos.samplebankmodule.infra.ModelMapper;
import com.home.demos.samplebankmodule.model.Card;
import com.home.demos.samplebankmodule.rest.cards.dto.CreateCardDto;
import com.home.demos.samplebankmodule.rest.cards.dto.CreatedCardDto;
import spark.Route;

import java.util.Collections;
import java.util.Optional;
import java.util.stream.Collectors;

public class CardResource {
    private static final Gson gson = new Gson();
    private static final ModelMapper modelMapper = new ModelMapper();

    public static Route create() {
        return (request, response) -> Optional.of(gson.fromJson(request.body(), CreateCardDto.class))
                .map(modelMapper::map)
                .map(Card::create)
                .map(CreatedCardDto::new)
                .map(gson::toJson)
                .orElse("TODO: ERROR");
    }

    public static Route findAllForClient() {
        return (request, response) ->
                gson.toJson(
                        Optional.of(request.params(":clientId"))
                                .map(Long::parseLong)
                                .map(Card::new)
                                .map(Card::findAllLikeThis)
                                .orElse(Collections.emptyList())
                                .stream()
                                .map(CreatedCardDto::new)
                                .collect(Collectors.toList())
                );
    }
}
