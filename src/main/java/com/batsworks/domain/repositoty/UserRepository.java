package com.batsworks.domain.repositoty;

import com.batsworks.domain.dto.PageDTO;
import com.batsworks.domain.entity.UserEntity;
import com.batsworks.utils.PageDTOUtility;
import io.quarkus.hibernate.reactive.panache.PanacheQuery;
import io.quarkus.hibernate.reactive.panache.PanacheRepositoryBase;
import io.quarkus.hibernate.reactive.panache.common.WithTransaction;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
@ApplicationScoped
public class UserRepository implements PanacheRepositoryBase<UserEntity, UUID> {

    private static final String SAFE_WHERE = " WHERE 1=1 ";

    public PageDTO<UserEntity> findAll(int pageIndex, int size) {
        PanacheQuery<UserEntity> query = findAll();
        var page = query.page(pageIndex, size);
//        return new PageDTO<>(page);
        return null;
    }

    public Uni<List<UserEntity>> findAllByParameters(String name, String email) {
        StringBuilder query = new StringBuilder(SAFE_WHERE);
        Map<String, Object> params = new HashMap<>();

        if (name != null) {
            query.append(" AND name =:name ");
            params.put("name", name);
        }

        if (email != null) {
            query.append(" AND email=:email ");
            params.put("email", email);
        }

        return find(query.toString(), params).list();
    }

    public Uni<PageDTO<UserEntity>> findAllByParameters(String name, String email, int pageIndex, int size) {
        StringBuilder query = new StringBuilder(SAFE_WHERE);
        Map<String, Object> params = new HashMap<>();

        if (name != null) {
            query.append(" AND name =:name ");
            params.put("name", name);
        }

        if (email != null) {
            query.append(" AND email=:email ");
            params.put("email", email);
        }

        var page=  find(query.toString(), params).page(pageIndex, size);
        return PageDTOUtility.create(page, pageIndex, size);
    }
}
