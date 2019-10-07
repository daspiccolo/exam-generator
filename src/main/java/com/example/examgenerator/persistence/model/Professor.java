package com.example.examgenerator.persistence.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Entity // serve para gerar uma tabela no banco de dados
public class Professor extends AbstractEntity{
    @NotEmpty(message = " The field name cannot be empty")
    private String name;
    @Email(message = "This email is not valid")
    @NotEmpty(message = "The field email cannot be empty")
    @Column(unique = true) //utilizado para especificar colunas a serem marcadas como possuindo valores únicos. O valor default é false. Alguns valores não são chave-primária, no entanto, precisam possuir valores únicos, que causam problemas caso sejam duplicados.
    private String email;

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}
