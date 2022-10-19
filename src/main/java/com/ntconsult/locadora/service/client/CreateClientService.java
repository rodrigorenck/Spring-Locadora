package com.ntconsult.locadora.service.client;

import com.ntconsult.locadora.base.dto.BaseDto;
import com.ntconsult.locadora.builder.ResponseSuccessBuilder;
import com.ntconsult.locadora.form.ClientForm;
import com.ntconsult.locadora.model.Client;
import com.ntconsult.locadora.repository.ClientRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class CreateClientService {

    private final ClientRepository clientRepository;


    public CreateClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public BaseDto execute(ClientForm form){
        //validacoes...

        Client client = form.convert();
        clientRepository.save(client);
        ResponseSuccessBuilder<Client> successBuilder = new ResponseSuccessBuilder<>(HttpStatus.CREATED, client);
        return successBuilder.get();
    }
}
