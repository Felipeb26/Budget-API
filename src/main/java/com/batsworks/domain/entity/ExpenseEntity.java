package com.batsworks.domain.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;


@Entity
@Table(name = "expenses")
public class ExpenseEntity extends AbstractEntity<ExpenseEntity> {

    public ExpenseEntity(UUID id, LocalDateTime createdDate, LocalDateTime updateDate, String className, BillEntity bill, BillEntity principalBill, String description, BigDecimal value, LocalDate expendedDate) {
        super(id, createdDate, updateDate, className);
        this.bill = bill;
        this.principalBill = principalBill;
        this.description = description;
        this.value = value;
        this.expendedDate = expendedDate;
    }

    public ExpenseEntity() {
    }

    @ManyToOne
    @JoinColumn(name = "bill_id")
    private BillEntity bill;
    @ManyToOne
    @JoinColumn(name = "principal_bill_id")
    private BillEntity principalBill;

    private String description;
    private BigDecimal value;
    @Column(name = "expended_date")
    private LocalDate expendedDate;


    public BillEntity getBill() {
        return bill;
    }

    public void setBill(BillEntity bill) {
        this.bill = bill;
    }

    public BillEntity getPrincipalBill() {
        return principalBill;
    }

    public void setPrincipalBill(BillEntity principalBill) {
        this.principalBill = principalBill;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public LocalDate getExpendedDate() {
        return expendedDate;
    }

    public void setExpendedDate(LocalDate expendedDate) {
        this.expendedDate = expendedDate;
    }
}
