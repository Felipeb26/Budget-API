package com.batsworks.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "sharing")
public class SharingEntity extends AbstractEntity<SharingEntity> {

    public SharingEntity(UUID id, LocalDateTime createdDate, LocalDateTime updateDate, String className, BillEntity bill, GroupEntity group) {
        super(id, createdDate, updateDate, className);
        this.bill = bill;
        this.group = group;
    }

    public SharingEntity() {
    }

    @ManyToOne
    @JoinColumn(name = "billId")
    private BillEntity bill;
    @ManyToOne
    @JoinColumn(name = "groupId")
    private GroupEntity group;

    public BillEntity getBill() {
        return bill;
    }

    public void setBill(BillEntity bill) {
        this.bill = bill;
    }

    public GroupEntity getGroup() {
        return group;
    }

    public void setGroup(GroupEntity group) {
        this.group = group;
    }
}
