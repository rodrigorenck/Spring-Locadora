package com.ntconsult.locadora.service.rental;

import com.ntconsult.locadora.base.dto.BaseDto;
import com.ntconsult.locadora.builder.ResponseSuccessBuilder;
import com.ntconsult.locadora.dto.RentalDto;
import com.ntconsult.locadora.model.Rental;
import com.ntconsult.locadora.repository.RentalRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FindAllRentalService {

    private final RentalRepository repository;


    public FindAllRentalService(RentalRepository repository) {
        this.repository = repository;
    }

    public BaseDto execute(){
        List<Rental> allRental = repository.findAll();
        List<RentalDto> rentalDtos = allRental.stream().map(Rental::convert).toList();
        ResponseSuccessBuilder<List<RentalDto>> successBuilder = new ResponseSuccessBuilder<>(HttpStatus.OK, rentalDtos);
        return successBuilder.get();
    }
}
