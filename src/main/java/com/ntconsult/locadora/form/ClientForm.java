package com.ntconsult.locadora.form;

import com.ntconsult.locadora.model.Client;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class ClientForm {
    @NotNull
    @NotEmpty
    private String name;

    @NotNull
    @NotEmpty
    private String password;

    public ClientForm(String name, String password) {
        this.name = name;
        this.password = password;
    }


    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public Client convert() {
        return new Client(name , password);
    }
}
