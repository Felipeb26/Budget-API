package com.batsworks.domain.entity;

import com.batsworks.domain.enums.BillType;
import com.batsworks.domain.enums.ExpenseType;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "bills")
public class BillEntity extends AbstractEntity<BillEntity> {

    public BillEntity(UUID id, LocalDateTime createdDate, LocalDateTime updateDate, String className, UserEntity userEntity, ExpenseEntity expenseEntity, String name, BigDecimal value, LocalDate expiryDate, BillType type, Boolean regular) {
        super(id, createdDate, updateDate, className);
        this.userEntity = userEntity;
        this.expenseEntity = expenseEntity;
        this.name = name;
        this.value = value;
        this.expiryDate = expiryDate;
        this.type = type;
        this.regular = regular;
    }

    public BillEntity(){}

    @ManyToOne
    @JoinColumn(name = "userId")
    private UserEntity userEntity;
    @ManyToOne
    @JoinColumn(name = "expenseId")
    private ExpenseEntity expenseEntity;

    private String name;
    private BigDecimal value;
    private LocalDate expiryDate;
    @Enumerated(EnumType.STRING)
    private BillType type;
    private Boolean regular;
    @Enumerated(EnumType.STRING)
    private ExpenseType expenseType;

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public ExpenseEntity getExpenseEntity() {
        return expenseEntity;
    }

    public void setExpenseEntity(ExpenseEntity expenseEntity) {
        this.expenseEntity = expenseEntity;
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

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    public BillType getType() {
        return type;
    }

    public void setType(BillType type) {
        this.type = type;
    }

    public Boolean getRegular() {
        return regular;
    }

    public void setRegular(Boolean regular) {
        this.regular = regular;
    }
}
