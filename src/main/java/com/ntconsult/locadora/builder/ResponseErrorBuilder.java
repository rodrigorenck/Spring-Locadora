package com.ntconsult.locadora.builder;

import com.ntconsult.locadora.base.dto.BaseDto;
import com.ntconsult.locadora.base.dto.BaseErrorDto;
import com.ntconsult.locadora.base.dto.BaseResultDto;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

public class ResponseErrorBuilder {

    private BaseDto<Void> result;

    public ResponseErrorBuilder(HttpStatus status){
        BaseResultDto result = new BaseResultDto(status.value(), status.getReasonPhrase());
        this.result = new BaseDto<>(null, new ArrayList<>(), result);
    }

    public ResponseErrorBuilder(HttpStatus status, List<BaseErrorDto> errors){
        BaseResultDto result = new BaseResultDto(status.value(), status.getReasonPhrase());
        this.result = new BaseDto<>(null, errors, result);
    }

    public void addError(String field, String message){
        BaseErrorDto error = new BaseErrorDto(field, message);
        result.getErros().add(error);
    }

    public BaseDto<Void> get(){
        return result;
    }
}
