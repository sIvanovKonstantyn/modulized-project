package com.home.demos.samplebankmodule.rest.clients;

import com.home.demos.samplebankmodule.rest.clients.dto.CreateClientDto;
import com.home.demos.samplebankmodule.rest.clients.dto.CreatedClientDto;
import com.home.demos.samplebankmodule.services.clients.ClientService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/clients")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ClientResource {

    private final ClientService clientService;

    @Inject
    public ClientResource(ClientService clientService) {
        this.clientService = clientService;
    }

    @POST
    public CreatedClientDto create(CreateClientDto createClientDto) {
        return clientService.create(createClientDto);
    }

    @GET
    public List<CreatedClientDto> findAll() {
        return clientService.findAll();
    }
}
