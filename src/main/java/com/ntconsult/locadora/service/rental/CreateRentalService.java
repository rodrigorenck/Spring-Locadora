package com.ntconsult.locadora.service.rental;

import com.ntconsult.locadora.base.dto.BaseDto;
import com.ntconsult.locadora.builder.ResponseErrorBuilder;
import com.ntconsult.locadora.builder.ResponseSuccessBuilder;
import com.ntconsult.locadora.constants.ErrorMessages;
import com.ntconsult.locadora.dto.FilmDto;
import com.ntconsult.locadora.form.RentalForm;
import com.ntconsult.locadora.model.Client;
import com.ntconsult.locadora.model.Film;
import com.ntconsult.locadora.model.Rental;
import com.ntconsult.locadora.repository.ClientRepository;
import com.ntconsult.locadora.repository.FilmRepository;
import com.ntconsult.locadora.repository.RentalRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CreateRentalService {

    private final RentalRepository rentalRepository;
    private final FilmRepository filmRepository;
    private final ClientRepository clientRepository;


    public CreateRentalService(RentalRepository repository, FilmRepository filmRepository, ClientRepository clientRepository) {
        this.rentalRepository = repository;
        this.filmRepository = filmRepository;
        this.clientRepository = clientRepository;
    }

    public BaseDto execute(RentalForm form){
        //verificacao de null ou empty

        //verificar se existe um client come esse id
        if(!clientExists(form)){
            ResponseErrorBuilder errorBuilder = new ResponseErrorBuilder(HttpStatus.BAD_REQUEST);
            errorBuilder.addError("client id", ErrorMessages.CLIENT_DOES_NOT_EXIST);
            return errorBuilder.get();
        }

        //verificar se a data nao eh menor que a data atual
        if(!dateValid(form)){
            ResponseErrorBuilder errorBuilder = new ResponseErrorBuilder(HttpStatus.BAD_REQUEST);
            errorBuilder.addError("ending date", ErrorMessages.STARTING_DATE_MUST_BE_AFTER_ENDING_DATE);
            return errorBuilder.get();
        }

        //verificar se existem filmes que nao estao no banco
        if(!areFilmsInTheDb(form)){
            ResponseErrorBuilder errorBuilder = new ResponseErrorBuilder(HttpStatus.BAD_REQUEST);
            errorBuilder.addError("films", ErrorMessages.FILMS_DESIRED_DOES_NOT_EXIST);
            return errorBuilder.get();
        }

        //aqui nao vamos tomar nullpointer pq sabemos que todos filmes existem no banco!
        List<Film> filmsToBeRented = filmsToBeRented(form.getFilms());

        //aqui ja sabemos que todos filmes existem no banco, agora temos que verificar se estao disponiveis
        //verificar se os filmes estao disponiveis
        if(!areFilmsAvailable(filmsToBeRented)){
            ResponseErrorBuilder errorBuilder = new ResponseErrorBuilder(HttpStatus.BAD_REQUEST);
            errorBuilder.addError("films", ErrorMessages.FILMS_DESIRED_ARE_NOT_AVAILABLE);
            return errorBuilder.get();
        }

        //se chegamos aqui entao sabemos que todos os filmes existem no banco e estao disponiveis
        rentFilms(filmsToBeRented);

        //tivemos que fazer uma conversao manual -> de RentalForm para Rental
        Rental rental = new Rental(form.getClientId(), filmsToBeRented, form.getEndingDate());
        rentalRepository.save(rental);
        ResponseSuccessBuilder<Rental> successBuilder = new ResponseSuccessBuilder<>(HttpStatus.CREATED, rental);
        return  successBuilder.get();
    }

    private boolean clientExists(RentalForm form) {
        Optional<Client> clientOptional = clientRepository.findById(form.getClientId());
        if(clientOptional.isEmpty()){
           return false;
        }
        return true;
    }

    private boolean dateValid(RentalForm form) {
        LocalDate endingDate = form.getEndingDate();
        if(endingDate.isBefore(LocalDate.now())){
            return false;
        }
        return true;
    }

    //metodo para diminuir o numero de copias
    private void rentFilms(List<Film> films) {
        films.forEach(Film::rentend);
    }

    //se pelo menos um dos filmes nao tiver disponivel a gente da erro -> futuramente melhorar
    private boolean areFilmsAvailable(List<Film> filmsToBeRented) {
        for (Film film :
                filmsToBeRented) {
            if (!film.isAvailable()){
                return false;
            }
        }
        return true;
    }

    //poderia criar um validator dessa regra?
    //metodo para verificar se o usuario nao esta tentado alugar um filme que nao existe no banco
    private boolean areFilmsInTheDb(RentalForm form){
        List<String> filmsAvailable = filmRepository.findByAvailableIsTrue().stream().map(Film::getName).toList();
        List<String> filmsDesired = form.getFilms().stream().map(FilmDto::getName).toList();
        for (String f:
                filmsDesired) {
            if(!filmsAvailable.contains(f)) return false;
        }
        return true;
    }


    //transforma os dto em model
    private List<Film> filmsToBeRented(List<FilmDto> filmsDto){
        List<Film> films = new ArrayList<>();
        for (FilmDto dto:
             filmsDto) {
            String name = dto.getName();
            Optional<Film> film = filmRepository.findByName(name);
            films.add(film.get());
        }
        return films;
    }
}
