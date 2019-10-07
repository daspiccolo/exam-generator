package com.example.examgenerator.persistence.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotEmpty;

@Entity
public class ApplicationUser extends AbstractEntity {
    @NotEmpty(message=" The field username cannot be empty")
    @Column(unique =true)
    private String username;
    @NotEmpty(message=" The field password cannot be empty")
    private String password;
    @OneToOne //é utilizada para associar duas entidades onde uma não é componente da outra
    private Professor professor;

    public ApplicationUser(ApplicationUser applicationUser) {
        this.username = applicationUser.username;
        this.password = applicationUser.password;
        this.professor = applicationUser.professor;
    }

    public ApplicationUser() {
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

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }
}

