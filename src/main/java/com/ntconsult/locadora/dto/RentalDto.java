package com.ntconsult.locadora.dto;

import com.ntconsult.locadora.model.Film;

import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class RentalDto {

    private Long id;
    private Long clientId;
    @OneToMany(cascade = CascadeType.ALL)
    private List<FilmDto> films = new ArrayList<>();
    private LocalDate startingDate;
    private LocalDate endingDate;
    private BigDecimal totalValue;
    private int period;

    public RentalDto(Long id, Long clientId, List<FilmDto> films, LocalDate startingDate, LocalDate endingDate, BigDecimal totalValue, int period) {
        this.id = id;
        this.clientId = clientId;
        this.films = films;
        this.startingDate = startingDate;
        this.endingDate = endingDate;
        this.totalValue = totalValue;
        this.period = period;
    }

    public Long getId() {
        return id;
    }

    public Long getClientId() {
        return clientId;
    }

    public List<FilmDto> getFilms() {
        return films;
    }

    public LocalDate getStartingDate() {
        return startingDate;
    }

    public LocalDate getEndingDate() {
        return endingDate;
    }

    public BigDecimal getTotalValue() {
        return totalValue;
    }

    public int getPeriod() {
        return period;
    }
}
