package com.example.exam_web.model.binding;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserLoginBindingModel {

    private String username;
    private String password;

    @NotNull
    @Size(min = 3,max = 10)
    public String getUsername() {
        return username;
    }

    public UserLoginBindingModel setUsername(String username) {
        this.username = username;
        return this;
    }

    @NotNull
    @Size(min = 3)
    public String getPassword() {
        return password;
    }

    public UserLoginBindingModel setPassword(String password) {
        this.password = password;
        return this;
    }
}
