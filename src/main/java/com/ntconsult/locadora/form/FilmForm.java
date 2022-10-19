package com.ntconsult.locadora.form;

import com.ntconsult.locadora.model.Film;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class FilmForm {

    @NotNull
    @NotEmpty
    private String name;
    @NotNull
    private BigDecimal dailyValue;
    private final int numberOfCopies;

    public FilmForm(String name, BigDecimal dailyValue, int numberOfCopies) {
        this.name = name;
        this.dailyValue = dailyValue;
        this.numberOfCopies = numberOfCopies;
    }

    public Film convert(){
        return new Film(name, dailyValue, numberOfCopies);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDailyValue(BigDecimal dailyValue) {
        this.dailyValue = dailyValue;
    }

}
