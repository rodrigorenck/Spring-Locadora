package com.ntconsult.locadora.model;

import com.ntconsult.locadora.dto.FilmDto;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
@Table(name = "Films")
public class Film {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private BigDecimal dailyValue = BigDecimal.ZERO;
    private boolean available;
    private int numberOfCopies;

    //cadastrar um filme no banco
    public Film(@NotNull @NotEmpty String name, @NotNull BigDecimal dailyValue, int numberOfCopies) {
        this.name = name;
        this.dailyValue = dailyValue;
        //sempre que eu cadastro um filme ele inicia como disponivel
        this.available = true;
        this.numberOfCopies = numberOfCopies;
    }

    public Film(String name) {
        this.name = name;
    }

    @Deprecated
    public Film() {
    }

    public FilmDto convert(){
        return new FilmDto(name);
    }

    public void rentend() {
        numberOfCopies--;
    }


    public int getNumberOfCopies() {
        return numberOfCopies;
    }

    public Long getId() {
        return id;
    }

    public BigDecimal getDailyValue() {
        return dailyValue;
    }

    public String getName() {
        return name;
    }

    public boolean isAvailable() {
        return numberOfCopies > 0;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}
