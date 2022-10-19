package com.ntconsult.locadora.service.film;

import com.ntconsult.locadora.base.dto.BaseDto;
import com.ntconsult.locadora.builder.ResponseErrorBuilder;
import com.ntconsult.locadora.builder.ResponseSuccessBuilder;
import com.ntconsult.locadora.constants.ErrorMessages;
import com.ntconsult.locadora.form.FilmForm;
import com.ntconsult.locadora.model.Film;
import com.ntconsult.locadora.repository.FilmRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CreateFilmService {

    private final FilmRepository repository;

    public CreateFilmService(FilmRepository repository) {
        this.repository = repository;
    }

    public BaseDto execute(FilmForm filmForm) {
        //validacoes de not null e not empty eu ja verifiquei com as anotacoes javax validation
        String name = filmForm.getName();

        //validar se ja nao existe um filme com esse name
        if (!verifyName(name)) {
            ResponseErrorBuilder errorBuilder = new ResponseErrorBuilder(HttpStatus.BAD_REQUEST);
            errorBuilder.addError(name, ErrorMessages.MOVIE_NAME_ALREADY_TAKEN);
            return errorBuilder.get();
        }
        Film film = filmForm.convert();
        repository.save(film);
        ResponseSuccessBuilder successBuilder = new ResponseSuccessBuilder(HttpStatus.CREATED, true);
        return successBuilder.get();
    }

    //verifica se o nome do filme eh unico
    private boolean verifyName(String name) {
        List<String> allNames = repository.findAll()
                .stream()
                .map(Film::getName)
                .toList();
        for (String n :
                allNames) {
            if (n.equalsIgnoreCase(name)) return false;
        }
        return true;
    }

//    private boolean verifyName(String name){
//        Optional<Film> optionalFilm = repository.findByName(name);
//        return optionalFilm.isEmpty();
//    }
}
