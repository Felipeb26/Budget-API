package com.batsworks.domain.service;

import com.batsworks.domain.dto.PageDTO;
import com.batsworks.domain.entity.UserEntity;
import io.smallrye.mutiny.Uni;

import java.util.UUID;

public interface UserService {

    Uni<UserEntity> findById(UUID id);

    Uni<UserEntity> save(UserEntity userEntity);

    Uni<PageDTO<UserEntity>> findAll(String id, String name, String email, int page, int size);

    Uni<Void> updateUser(UUID id, UserEntity user);

    Uni<Void> deleteById(UUID id);
}
