package com.home.demos.samplebankmodule.services.clients;

import com.home.demos.samplebankmodule.repositories.clients.ClientRepository;
import com.home.demos.samplebankmodule.repositories.clients.entities.Client;
import com.home.demos.samplebankmodule.rest.clients.dto.CreateClientDto;
import com.home.demos.samplebankmodule.rest.clients.dto.CreatedClientDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientService {

    private final ModelMapper mapper;
    private final ClientRepository repository;

    @Autowired
    public ClientService(ModelMapper mapper, ClientRepository repository) {
        this.mapper = mapper;
        this.repository = repository;
    }

    public CreatedClientDto create(CreateClientDto createClientDto) {
        Client client = mapper.map(createClientDto, Client.class);
        client = repository.save(client);

        return mapper.map(client, CreatedClientDto.class);
    }

    public List<CreatedClientDto> findAll() {
        return repository.findAll().stream()
                .map(client -> mapper.map(client, CreatedClientDto.class))
                .collect(Collectors.toList());
    }
}
