package com.home.demos.samplebankmodule.rest.clients;

import com.google.gson.Gson;
import com.home.demos.samplebankmodule.infra.ModelMapper;
import com.home.demos.samplebankmodule.model.Client;
import com.home.demos.samplebankmodule.rest.clients.dto.CreateClientDto;
import com.home.demos.samplebankmodule.rest.clients.dto.CreatedClientDto;
import spark.Route;

import java.util.Collections;
import java.util.Optional;
import java.util.stream.Collectors;

public class ClientResource {

    private static final Gson gson = new Gson();
    private static final ModelMapper modelMapper = new ModelMapper();

    public static Route create() {
        return (request, response) -> Optional.of(gson.fromJson(request.body(), CreateClientDto.class))
                .map(modelMapper::map)
                .map(Client::create)
                .map(CreatedClientDto::new)
                .map(gson::toJson)
                .orElse("TODO: ERROR");
    }

    public static Route findAll() {
        return (request, response) ->
                gson.toJson(
                        Optional.of(new Client())
                                .map(Client::findAll)
                                .orElse(Collections.emptyList())
                                .stream()
                                .map(CreatedClientDto::new)
                                .collect(Collectors.toList())
                );
    }
}
