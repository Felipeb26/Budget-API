package com.batsworks.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDateTime;

@DynamicUpdate
@Entity
@Table(name = "user_groups")
public class UserGroup extends AbstractEntity<UserGroup> {

    public UserGroup(UserEntity user, GroupEntity group, LocalDateTime joinedAt) {
        this.user = user;
        this.group = group;
        this.joinedAt = joinedAt;
    }

    public UserGroup() {}

    @ManyToOne
    @JoinColumn(name = "userId")
    private UserEntity user;
    @ManyToOne
    @JoinColumn(name = "groupId")
    private GroupEntity group;
    private LocalDateTime joinedAt;

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public GroupEntity getGroup() {
        return group;
    }

    public void setGroup(GroupEntity group) {
        this.group = group;
    }

    public LocalDateTime getJoinedAt() {
        return joinedAt;
    }

    public void setJoinedAt(LocalDateTime joinedAt) {
        this.joinedAt = joinedAt;
    }
}
