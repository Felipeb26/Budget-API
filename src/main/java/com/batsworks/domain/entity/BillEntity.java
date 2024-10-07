package com.batsworks.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "bills")
public class BillEntity extends AbstractEntity<BillEntity> {

    @ManyToOne
    @JoinColumn(name = "user_entity_id")
    private UserEntity userEntity;
    private String name;
    private BigDecimal value;
    private LocalDate data;

}
