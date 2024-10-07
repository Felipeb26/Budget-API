package com.batsworks.domain.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "usuarios", uniqueConstraints = {
        @UniqueConstraint(columnNames = "email")
})
public class UserEntity extends AbstractEntity<UserEntity>{

    private String name;
    private String email;

    public UserEntity(UUID id, LocalDateTime dataInclusao, LocalDateTime dataAlteracao, String className, String name, String email) {
        super(id, dataInclusao, dataAlteracao, className);
        this.name = name;
        this.email = email;
    }

    public UserEntity() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}