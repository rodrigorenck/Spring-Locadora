package com.ntconsult.locadora.form;

import com.ntconsult.locadora.dto.FilmDto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class RentalForm {

    @NotNull
    @NotEmpty
    private Long clientId;
    private List<FilmDto> films = new ArrayList<>();
    @NotNull
    @NotEmpty
    private LocalDate endingDate;

    public RentalForm(Long clientId, List<FilmDto> filmsDto, LocalDate endingDate) {
        this.clientId = clientId;
        this.films = filmsDto;
        this.endingDate = endingDate;
    }
//
//    public Rental convert() {
//        List<Film> films = this.films.stream()
//                .map(FilmDto::convert)
//                .collect(Collectors.toList());
//        return new Rental(films, endingDate);
//    }


    public Long getClientId() {
        return clientId;
    }

    public LocalDate getEndingDate() {
        return endingDate;
    }

    public List<FilmDto> getFilms() {
        return films;
    }
}
