package com.ntconsult.locadora.controller;

import com.ntconsult.locadora.base.dto.BaseDto;
import com.ntconsult.locadora.form.RentalForm;
import com.ntconsult.locadora.service.rental.CreateRentalService;
import com.ntconsult.locadora.service.rental.FindAllRentalByClientIdService;
import com.ntconsult.locadora.service.rental.FindAllRentalService;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;

@RestController()
@RequestMapping("/rental")
public class RentalController {

    private final FindAllRentalService findAllRentalService;
    private final CreateRentalService createRentalService;
    private final FindAllRentalByClientIdService findAllRentalByClientIdService;

    public RentalController(FindAllRentalService findAllRentalService, CreateRentalService createRentalService, FindAllRentalByClientIdService findAllRentalByClientIdService) {
        this.findAllRentalService = findAllRentalService;
        this.createRentalService = createRentalService;
        this.findAllRentalByClientIdService = findAllRentalByClientIdService;
    }


    @GetMapping
    public BaseDto findAll(){
        return findAllRentalService.execute();
    }

    @GetMapping("/{clientId}")
    public BaseDto findByClientId(@PathVariable Long clientId){
        return findAllRentalByClientIdService.execute(clientId);
    }

    @PostMapping
    @Transactional
    public BaseDto create(@RequestBody RentalForm rentalForm){
        return createRentalService.execute(rentalForm);
    }
}
