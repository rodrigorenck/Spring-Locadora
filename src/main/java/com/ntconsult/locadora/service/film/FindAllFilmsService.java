package com.ntconsult.locadora.service.film;

import com.ntconsult.locadora.base.dto.BaseDto;
import com.ntconsult.locadora.builder.ResponseSuccessBuilder;
import com.ntconsult.locadora.model.Film;
import com.ntconsult.locadora.repository.FilmRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FindAllFilmsService {

    private final FilmRepository repository;

    public FindAllFilmsService(FilmRepository repository) {
        this.repository = repository;
    }

    public BaseDto execute(){
        List<Film> result = repository.findAll();
        ResponseSuccessBuilder<List<Film>> success = new ResponseSuccessBuilder<>(HttpStatus.OK, result);
        return success.get();
    }
}
