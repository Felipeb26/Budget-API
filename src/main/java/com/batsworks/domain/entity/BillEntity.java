package com.batsworks.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "bills")
public class BillEntity extends AbstractEntity<BillEntity> {

    @ManyToOne
    @JoinColumn(name = "user_entity_id")
    private UserEntity userEntity;
    private String name;
    private BigDecimal value;
    private LocalDate data;

    public BillEntity(UUID id, LocalDateTime createdDate, LocalDateTime updateDate, String className, UserEntity userEntity, String name, BigDecimal value, LocalDate data) {
        super(id, createdDate, updateDate, className);
        this.userEntity = userEntity;
        this.name = name;
        this.value = value;
        this.data = data;
    }

    public BillEntity(UserEntity userEntity, String name, BigDecimal value, LocalDate data) {
        this.userEntity = userEntity;
        this.name = name;
        this.value = value;
        this.data = data;
    }

    public BillEntity() {
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public static class Builder {
        private UserEntity userEntity;
        private String name;
        private BigDecimal value;
        private LocalDate data;

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setValue(BigDecimal value) {
            this.value = value;
            return this;
        }

        public Builder setData(LocalDate data) {
            this.data = data;
            return this;
        }

        public BillEntity build() {
            return new BillEntity(this.userEntity, this.name, this.value, this.data);
        }
    }
}
