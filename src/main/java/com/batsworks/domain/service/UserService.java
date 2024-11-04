package com.batsworks.domain.service;

import com.batsworks.domain.entity.UserEntity;
import io.smallrye.mutiny.Uni;

import java.util.List;

public interface UserService {

    Uni<UserEntity> findById(String id);

    Uni<UserEntity> save(UserEntity userEntity);

    Uni<List<UserEntity>> findAll(String id, String name, String email);
}
