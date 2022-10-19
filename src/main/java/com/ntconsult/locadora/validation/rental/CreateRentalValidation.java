package com.ntconsult.locadora.validation.rental;

import com.ntconsult.locadora.base.dto.BaseErrorDto;
import com.ntconsult.locadora.builder.ResponseErrorBuilder;
import com.ntconsult.locadora.constants.ErrorMessages;
import com.ntconsult.locadora.dto.FilmDto;
import com.ntconsult.locadora.form.RentalForm;
import com.ntconsult.locadora.model.Client;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//valida payload -> form
public class CreateRentalValidation {

    public static List<BaseErrorDto> validate(RentalForm form){
        List<BaseErrorDto> result = new ArrayList<>();

        if(form.getEndingDate().isBefore(LocalDate.now())){
            result.add(new BaseErrorDto("date", ErrorMessages.STARTING_DATE_MUST_BE_AFTER_ENDING_DATE));
        }
        for (FilmDto film : form.getFilms()) {
            if(film.getName().isBlank()){
                result.add(new BaseErrorDto("film name", "Film name can not be blank"));
            }
        }
        return result;
    }


    private boolean dateValid(RentalForm form) {
        LocalDate endingDate = form.getEndingDate();
        if(endingDate.isBefore(LocalDate.now())){
            return false;
        }
        return true;
    }

}
