package com.batsworks.domain.dto;

import com.batsworks.domain.entity.UserEntity;
import io.quarkus.panache.common.Page;
import io.smallrye.mutiny.Uni;

import java.util.List;

public class PageDTO<T> {

    private int page;
    private int totalPages;

    public PageDTO() {}

    public PageDTO(Uni<List<UserEntity>> list, Page page) {
        this.page = page.index;
    }
}