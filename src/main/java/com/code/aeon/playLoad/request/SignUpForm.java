package com.code.aeon.playLoad.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

public class SignUpForm {
    @NotBlank
    @Size(min = 3, max = 50)
    private String name;

    @NotBlank
    @Size(min = 5 , max = 60)
    private String username;

    @NotBlank
    @Size(min = 6 , max = 60)
    @Email
    private String Email;

    private Set<String>role;

    @NotBlank
    @Size(min = 6, max = 60)
    private String password;

    public SignUpForm() {
    }

    public SignUpForm(@NotBlank @Size(min = 3, max = 50) String name, @NotBlank @Size(min = 5, max = 60) String username, @NotBlank @Size(min = 6, max = 60) @Email String Email, Set<String> role, @NotBlank @Size(min = 6, max = 60) String password) {
        this.name = name;
        this.username = username;
        this.Email = Email;
        this.role = role;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return Email;
    }

    public void setMail(String Email) {
        this.Email = Email;
    }

    public Set<String> getRole() {
        return role;
    }

    public void setRole(Set<String> role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
