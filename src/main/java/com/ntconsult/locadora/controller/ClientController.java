package com.ntconsult.locadora.controller;

import com.ntconsult.locadora.base.dto.BaseDto;
import com.ntconsult.locadora.form.ClientForm;
import com.ntconsult.locadora.service.client.CreateClientService;
import com.ntconsult.locadora.service.client.FindAllClientsService;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/client")
public class ClientController {

    private final FindAllClientsService findAllClientsService;
    private final CreateClientService createClientService;

    public ClientController(FindAllClientsService findAllClientsService, CreateClientService createClientService) {
        this.findAllClientsService = findAllClientsService;
        this.createClientService = createClientService;
    }


    @GetMapping
    public BaseDto findAll(){
        return findAllClientsService.execute();
    }

    //incluir filmes na locadora
    @PostMapping
    public BaseDto create(@RequestBody ClientForm clientForm){
        return createClientService.execute(clientForm);
    }



}
