package com.ntconsult.locadora.model;

import com.ntconsult.locadora.dto.ClientDto;
import com.ntconsult.locadora.dto.RentalDtoId;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Clients")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String password;
    @OneToMany
    private List<Rental> rentals = new ArrayList<>();

    public Client(String name, String passoward) {
        this.name = name;
        this.password = passoward;
    }

    public Client() {

    }

//    public boolean efetuarAluguel(Rental rental){
//        rentals.add(rental);
//        return true;
//    }

    public ClientDto convert(){
        List<RentalDtoId> rentalsId =  rentals.stream().map(Rental::convertId).toList();
        return new ClientDto(id, name, rentalsId);
    }

    //due date = data entrega
//    public boolean efetuarAluguel(List<Film> films, LocalDate dueDate){
//        Rental aluguel = new Rental(films, dueDate);
//        films.forEach(f -> f.setAvailable(false));
//        return true;
//    }
}
