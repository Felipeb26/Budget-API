package com.batsworks.domain.service.impl;

import com.batsworks.config.exceptions.BussinesApplicationException;
import com.batsworks.config.exceptions.UserException;
import com.batsworks.domain.dto.PageDTO;
import com.batsworks.domain.entity.UserEntity;
import com.batsworks.domain.repositoty.UserRepository;
import com.batsworks.domain.service.UserService;
import com.batsworks.utils.PageDTOUtility;
import io.quarkus.hibernate.reactive.panache.common.WithTransaction;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.apache.commons.beanutils.BeanUtils;
import org.jboss.resteasy.reactive.RestResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.util.UUID;

import static com.batsworks.config.enums.ErrorCodeMessage.UNKNOW_ERROR;

@ApplicationScoped
public class UserServiceImpl implements UserService {

    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
    private final UserRepository userRepository;

    @Inject
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @WithTransaction
    public Uni<PageDTO<UserEntity>> findAll(String id, String name, String email, int page, int size) {
        var pages = PageDTOUtility.create(userRepository.findAll(), page, size);
//        return userRepository.findAll().list();
        return pages;
    }

    @Override
    public Uni<UserEntity> findById(UUID id) {
        return userRepository.findById(id);
    }

    @WithTransaction
    @Override
    public Uni<UserEntity> save(UserEntity userEntity) {
        return userRepository.persist(userEntity);
    }

    @WithTransaction
    @Override
    public Uni<Void> updateUser(UUID id, UserEntity userD) {
        return userRepository.findById(id)
                .onItem().ifNull().failWith(() -> new UserException(RestResponse.Status.NOT_FOUND, UNKNOW_ERROR))
                .onItem().ifNotNull().transform(user -> {
                    try {
                        BeanUtils.copyProperties(user, userD);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    userD.setId(id);
                    return userD;
                }).onItem().ifNotNull().transform(user -> {
                    System.out.println("User persisted and flushed: " + user.getName());
                    System.out.println("User persisted and flushed: " + user.getEmail());
                    return user.persistAndFlush();
                }).replaceWithVoid()
                .onFailure().transform(failure -> {
                    if (failure instanceof BussinesApplicationException bussinesException)
                        throw new UserException(bussinesException.getResponse().getStatus(), UNKNOW_ERROR, failure.getMessage());
                    log.error(failure.getMessage(), failure);
                    throw new UserException(RestResponse.Status.BAD_REQUEST, UNKNOW_ERROR, failure.getMessage());
                });
    }

    @WithTransaction
    @Override
    public Uni<Void> deleteById(UUID id) {
        var wasDeleted = userRepository.deleteById(id);

        return wasDeleted.onItem().ifNotNull().transform(isTrue -> {
                    if (Boolean.FALSE.equals(isTrue)) throw new UserException(RestResponse.Status.BAD_REQUEST, UNKNOW_ERROR);
                    return isTrue;
                }).onItem().ifNull().failWith(() -> new UserException(RestResponse.Status.NOT_FOUND, UNKNOW_ERROR, "erro ao tentar deletar"))
                .replaceWithVoid()
                .onFailure().transform(error -> {
                    log.error("ERRO {}", error.getMessage());
                    throw new UserException(RestResponse.Status.BAD_REQUEST, UNKNOW_ERROR, error.getMessage());
                });
    }
}
