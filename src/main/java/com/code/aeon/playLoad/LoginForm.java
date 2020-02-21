package com.code.aeon.playLoad;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class LoginForm {
    @NotBlank
    @Size(min = 4 , max = 60)
    private String username;

    @NotBlank
    @Size(min = 6 , max = 60)
    private String password;

    public LoginForm() {
    }

    public LoginForm(@NotBlank @Size(min = 4, max = 60) String username, @NotBlank @Size(min = 6, max = 60) String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
