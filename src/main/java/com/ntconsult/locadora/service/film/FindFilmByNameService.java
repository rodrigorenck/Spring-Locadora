package com.ntconsult.locadora.service.film;

import com.ntconsult.locadora.base.dto.BaseDto;
import com.ntconsult.locadora.builder.ResponseErrorBuilder;
import com.ntconsult.locadora.builder.ResponseSuccessBuilder;
import com.ntconsult.locadora.constants.ErrorMessages;
import com.ntconsult.locadora.model.Film;
import com.ntconsult.locadora.repository.FilmRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FindFilmByNameService {
    private final FilmRepository repository;

    public FindFilmByNameService(FilmRepository repository) {
        this.repository = repository;
    }

    public BaseDto execute(String name) {
        if (name == null || name.isEmpty()) {
            ResponseErrorBuilder errorBuilder = new ResponseErrorBuilder(HttpStatus.BAD_REQUEST);
            errorBuilder.addError(name, ErrorMessages.FIELD_CANNOT_BE_EMPY);
            return errorBuilder.get();
        }
        Optional<Film> requestedFilm = repository.findByName(name);
        if (requestedFilm.isEmpty()) {
            ResponseErrorBuilder errorBuilder = new ResponseErrorBuilder(HttpStatus.BAD_REQUEST);
            errorBuilder.addError(name, ErrorMessages.FILM_DOES_NOT_EXIST);
            errorBuilder.get();
        }
        ResponseSuccessBuilder<Film> successBuilder = new ResponseSuccessBuilder<>(HttpStatus.OK, requestedFilm.get());
        return successBuilder.get();
    }

}
