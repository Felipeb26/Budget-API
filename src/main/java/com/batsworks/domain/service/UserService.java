package com.batsworks.domain.service;

import com.batsworks.domain.entity.UserEntity;
import io.smallrye.mutiny.Uni;

public interface UserService {

    Uni<UserEntity> findById(String id);

    Uni<UserEntity> save(UserEntity userEntity);
}
