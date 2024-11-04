package com.batsworks.domain.repositoty;

import com.batsworks.domain.dto.PageDTO;
import com.batsworks.domain.entity.UserEntity;
import io.quarkus.hibernate.reactive.panache.PanacheQuery;
import io.quarkus.hibernate.reactive.panache.PanacheRepositoryBase;
import io.quarkus.panache.common.Page;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.UUID;

@ApplicationScoped
public class UserRepository implements PanacheRepositoryBase<UserEntity, UUID> {

    public PageDTO<UserEntity> findAll(int pageIndex, int size) {
        PanacheQuery<UserEntity> query = findAll();
        var page = query.page(pageIndex, size);
        return new PageDTO<>(page.list(), page.page());
    }
}
