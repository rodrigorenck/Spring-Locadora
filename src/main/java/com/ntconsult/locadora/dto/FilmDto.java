package com.ntconsult.locadora.dto;

import com.ntconsult.locadora.base.dto.BaseDto;
import com.ntconsult.locadora.model.Film;
import com.ntconsult.locadora.repository.FilmRepository;
import com.ntconsult.locadora.service.film.FindFilmByNameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import java.util.Optional;

public class FilmDto {

//    @Autowired
//    private FilmRepository repository;

    private String name;

    public FilmDto(){}

    public FilmDto(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Film convert(){
        //como faço para devolver o filme do nome que o usuario passou que ja existe no banco
        //teria que chamar o repository aqui mas nao é uma boa pratica ;(
        return new Film(name);
    }
}
