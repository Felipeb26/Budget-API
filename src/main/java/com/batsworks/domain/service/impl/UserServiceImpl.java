package com.batsworks.domain.service.impl;

import com.batsworks.config.enums.ErrorCodeMessage;
import com.batsworks.config.exceptions.UsuarioException;
import com.batsworks.domain.entity.UserEntity;
import com.batsworks.domain.repositoty.UserRepository;
import com.batsworks.domain.service.UserService;
import io.quarkus.hibernate.reactive.panache.common.WithTransaction;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;
import java.util.UUID;

import static org.jboss.resteasy.reactive.RestResponse.Status.BAD_REQUEST;

@WithTransaction
@ApplicationScoped
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Inject
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @WithTransaction
    public Uni<List<UserEntity>> findAll(String id, String name, String email, int page, int size) {
        if (page == 0)
            throw new UsuarioException(BAD_REQUEST, ErrorCodeMessage.GROUP_LIMIT_MESSAGE);
        return userRepository.findAll().list();
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
