package com.ntconsult.locadora.builder;

import com.ntconsult.locadora.base.dto.BaseDto;
import com.ntconsult.locadora.base.dto.BaseResultDto;
import org.springframework.http.HttpStatus;

import java.util.Collections;

public class ResponseSuccessBuilder<T> {

    private BaseDto<T> result;

    public ResponseSuccessBuilder(HttpStatus status, T values){
        BaseResultDto result = new BaseResultDto(status.value(), status.getReasonPhrase());
        this.result = new BaseDto<>(values, Collections.EMPTY_LIST, result);
    }

    public BaseDto<T> get(){
        return result;
    }
}
