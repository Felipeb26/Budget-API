package com.batsworks.domain.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "groups")
public class GroupEntity extends AbstractEntity<GroupEntity> {

    public GroupEntity(UUID id, LocalDateTime createdDate, LocalDateTime updateDate, String className, String name) {
        super(id, createdDate, updateDate, className);
        this.name = name;
    }

    public GroupEntity(String name) {
        this.name = name;
    }

    public GroupEntity(){}

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
