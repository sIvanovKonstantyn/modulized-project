package com.home.demos.samplebankmodule.rest.clients;

import com.home.demos.samplebankmodule.rest.clients.dto.CreateClientDto;
import com.home.demos.samplebankmodule.rest.clients.dto.CreatedClientDto;
import com.home.demos.samplebankmodule.services.clients.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clients")
public class ClientResource {

    private final ClientService clientService;

    @Autowired
    public ClientResource(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping
    public ResponseEntity<CreatedClientDto> create(@RequestBody CreateClientDto createClientDto) {
        return ResponseEntity.ok().body(clientService.create(createClientDto));
    }

    @GetMapping
    public List<CreatedClientDto> findAll() {
        return clientService.findAll();
    }
}
