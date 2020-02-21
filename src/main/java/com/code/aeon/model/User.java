package com.code.aeon.model;

import lombok.Data;
import org.hibernate.annotations.NaturalId;
import org.springframework.data.annotation.TypeAlias;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users" , uniqueConstraints = {
        @UniqueConstraint(columnNames = {
                "username"
        }),
        @UniqueConstraint( columnNames = {
                "email"
        })
})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    @Size(min = 5 , max = 60)
    private String name;

    @NotBlank
    @Size(min = 6 , max = 60)
    private String username;

    @NotBlank
    @Size(min = 6 , max =  40)
    private String password;

    @NaturalId
    @NotBlank
    @Size(min = 8 , max =  70)
    @Email
    private String email;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable( name = "user_roles" ,
         joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role>roles = new HashSet<>();

    public User() {
    }

    public User(@NotBlank @Size(min = 5, max = 60) String name, @NotBlank @Size(min = 6, max = 60) String username, @NotBlank @Size(min = 6, max = 40) String password, @NotBlank @Size(min = 8, max = 70) @Email String email, Set<Role> roles) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.email = email;
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
