package com.batsworks.domain.entity;


import io.quarkus.hibernate.reactive.panache.PanacheEntityBase;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@EntityListeners(EntityListener.class)
@MappedSuperclass
public abstract class AbstractEntity<T> extends PanacheEntityBase {

    public AbstractEntity(UUID id, LocalDateTime createdDate, LocalDateTime updateDate, String className) {
        this.id = id;
        this.createdDate = createdDate;
        this.updateDate = updateDate;
        this.className = className;
    }

    public AbstractEntity() {
        super();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(updatable = false)
    private LocalDateTime createdDate;
    private LocalDateTime updateDate;
    @Column(name = "class_name", updatable = false)
    private String className;

    public UUID getId() {
        return id;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDateTime getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(LocalDateTime updateDate) {
        this.updateDate = updateDate;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getClassName() {
        return className;
    }
}
