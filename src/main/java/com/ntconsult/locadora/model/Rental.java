package com.ntconsult.locadora.model;


import com.ntconsult.locadora.dto.FilmDto;
import com.ntconsult.locadora.dto.RentalDto;
import com.ntconsult.locadora.dto.RentalDtoId;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Rentals")
public class Rental {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long clientId;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Film> films = new ArrayList<>();
    private LocalDate startingDate;
    private LocalDate endingData;
    private BigDecimal totalValue;
    private int totalPeriod;

    public Rental(Long clientId, List<Film> films, LocalDate endingDate) {
        this.clientId = clientId;
        this.films = films;
        this.startingDate = LocalDate.now();
        this.endingData = endingDate;
        totalValue = calculateTotalValue();
        totalPeriod = period();
    }


    @Deprecated
    public Rental() {
    }

    //soh faco isso depois de verificar a lista
    public BigDecimal calculateTotalValue() {
        BigDecimal valorTotalDiario = BigDecimal.ZERO;
        for (Film filme :
                films) {
            valorTotalDiario = valorTotalDiario.add(filme.getDailyValue());
        }
        BigDecimal valorTotal = valorTotalDiario.multiply(new BigDecimal(period()));
        return valorTotal;
    }

    public int period() {
        Period period = Period.between(startingDate, endingData);
        //melhorar esse codigo -> do jeito que ta a gente ta considerando que todos os meses tem 30 dias
        int months = period.getMonths();
        return period.getDays() + (months*30);
    }

    public RentalDto convert(){
        List<FilmDto> dtos = films.stream().map(Film::convert).toList();
        return new RentalDto(id, clientId, dtos, startingDate, endingData, totalValue, totalPeriod);
    }

    public RentalDtoId convertId(){
        return new RentalDtoId(id);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Film> getFilms() {
        return films;
    }

    public void setFilms(List<Film> filmes) {
        this.films = filmes;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public BigDecimal getTotalValue() {
        return totalValue;
    }

    public LocalDate getStartingDate() {
        return startingDate;
    }

    public LocalDate getEndingData() {
        return endingData;
    }

    public int getTotalPeriod() {
        return totalPeriod;
    }
}
