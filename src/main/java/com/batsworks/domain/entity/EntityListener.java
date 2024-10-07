package com.batsworks.domain.entity;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

import java.time.LocalDateTime;

@ApplicationScoped
public class EntityListener {


    @PreUpdate
    public <T> void preUpdateEntity(AbstractEntity<T> abstractEntity) {
        abstractEntity.setUpdateDate(LocalDateTime.now());
    }

    @PrePersist
    public <T> void prePersistEntity(AbstractEntity<T> abstractEntity) {
        abstractEntity.setCreatedDate(LocalDateTime.now());
        abstractEntity.setClassName(abstractEntity.getClass().getName());
    }

}
