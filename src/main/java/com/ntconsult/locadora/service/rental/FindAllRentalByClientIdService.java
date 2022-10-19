package com.ntconsult.locadora.service.rental;

import com.ntconsult.locadora.base.dto.BaseDto;
import com.ntconsult.locadora.builder.ResponseErrorBuilder;
import com.ntconsult.locadora.builder.ResponseSuccessBuilder;
import com.ntconsult.locadora.constants.ErrorMessages;
import com.ntconsult.locadora.dto.RentalDto;
import com.ntconsult.locadora.model.Client;
import com.ntconsult.locadora.model.Rental;
import com.ntconsult.locadora.repository.ClientRepository;
import com.ntconsult.locadora.repository.RentalRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FindAllRentalByClientIdService {

    private final RentalRepository rentalRepository;
    private final ClientRepository clientRepository;

    public FindAllRentalByClientIdService(RentalRepository rentalRepository, ClientRepository clientRepository) {
        this.rentalRepository = rentalRepository;
        this.clientRepository = clientRepository;
    }


    public BaseDto execute(Long id){
        //verificar se o id eh invalido
        Optional<Client> clientOptional = clientRepository.findById(id);
        if(clientOptional.isPresent()){
            ResponseErrorBuilder errorBuilder = new ResponseErrorBuilder(HttpStatus.BAD_REQUEST);
            errorBuilder.addError("id", ErrorMessages.CLIENT_DOES_NOT_EXIST);
        }

        List<Rental> rentals =  rentalRepository.findByClientId(id);
        List<RentalDto> rentalDto = rentals.stream().map(Rental::convert).toList();

        ResponseSuccessBuilder successBuilder = new ResponseSuccessBuilder(HttpStatus.OK, rentalDto);
        return successBuilder.get();
    }
}
