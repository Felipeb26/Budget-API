package com.batsworks.domain.service.impl;

import com.batsworks.domain.entity.UserEntity;
import com.batsworks.domain.repositoty.UserRepository;
import com.batsworks.domain.service.UserService;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.UUID;

@ApplicationScoped
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Inject
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Uni<UserEntity> findById(String id) {
        return userRepository.findById(UUID.fromString(id));
    }

    @Override
    public Uni<UserEntity> save(UserEntity userEntity) {
        return userRepository.persist(userEntity);
    }
}
