package com.ntconsult.locadora.controller;

import com.ntconsult.locadora.base.dto.BaseDto;
import com.ntconsult.locadora.form.FilmForm;
import com.ntconsult.locadora.service.film.FindAllFilmsService;
import com.ntconsult.locadora.service.film.CreateFilmService;
import com.ntconsult.locadora.service.film.FindAvailableFilmsService;
import com.ntconsult.locadora.service.film.FindUnavailableFilmsService;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/films")
public class FilmController {

    private final FindAllFilmsService findAllFilmsService;
    private final CreateFilmService createFilmService;
    private final FindAvailableFilmsService findAvailableFilmsService;
    private final FindUnavailableFilmsService findUnavailableFilmsService;

    public FilmController(FindAllFilmsService findAllFilmsService, CreateFilmService createFilmeService, FindAvailableFilmsService findAvailableFilmsService, FindUnavailableFilmsService findUnavailableFilmsService) {
        this.findAllFilmsService = findAllFilmsService;
        this.createFilmService = createFilmeService;
        this.findAvailableFilmsService = findAvailableFilmsService;
        this.findUnavailableFilmsService = findUnavailableFilmsService;
    }

    //adicionar paginacao
    @GetMapping
    public BaseDto findAll(){
        return findAllFilmsService.execute();
    }

    //incluir filmes na locadora
    @PostMapping
    public BaseDto create(@RequestBody FilmForm filmeForm){
        return createFilmService.execute(filmeForm);
    }

    //adicionar paginacao
    @GetMapping("/available")
    public BaseDto findAvailable(){
        return findAvailableFilmsService.execute();
    }

    @GetMapping("/unavailable")
    public BaseDto findUnavailable(){
        return findUnavailableFilmsService.execute();
    }

}
