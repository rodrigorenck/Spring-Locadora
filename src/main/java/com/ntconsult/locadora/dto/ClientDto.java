package com.ntconsult.locadora.dto;

import java.util.ArrayList;
import java.util.List;

public class ClientDto {

    private Long id;
    private String name;
    private List<RentalDtoId> rentals = new ArrayList<>();

    public ClientDto(Long id, String name, List<RentalDtoId> rentals) {
        this.id = id;
        this.name = name;
        this.rentals = rentals;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<RentalDtoId> getRentals() {
        return rentals;
    }
}
