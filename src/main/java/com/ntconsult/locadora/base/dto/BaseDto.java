package com.ntconsult.locadora.base.dto;

import java.util.List;

public class BaseDto<T> {
    private T data;
    private List<BaseErrorDto> erros;
    private BaseResultDto result;

    public BaseDto(T data, List<BaseErrorDto> erros, BaseResultDto result) {
        this.data = data;
        this.erros = erros;
        this.result = result;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public List<BaseErrorDto> getErros() {
        return erros;
    }

    public void setErros(List<BaseErrorDto> erros) {
        this.erros = erros;
    }

    public BaseResultDto getResult() {
        return result;
    }

    public void setResult(BaseResultDto result) {
        this.result = result;
    }
}
