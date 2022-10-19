package com.ntconsult.locadora.service.client;

import com.ntconsult.locadora.base.dto.BaseDto;
import com.ntconsult.locadora.builder.ResponseSuccessBuilder;
import com.ntconsult.locadora.dto.ClientDto;
import com.ntconsult.locadora.model.Client;
import com.ntconsult.locadora.repository.ClientRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Stream;

@Service
public class FindAllClientsService {

    private final ClientRepository repository;

    public FindAllClientsService(ClientRepository repository) {
        this.repository = repository;
    }

    public BaseDto execute(){
        List<Client> allClients = repository.findAll();
        Stream<ClientDto> clientsDto = allClients.stream().map(Client::convert);
        ResponseSuccessBuilder successBuilder = new ResponseSuccessBuilder(HttpStatus.OK, clientsDto);
        return successBuilder.get();
    }

}
