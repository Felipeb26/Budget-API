package com.batsworks.domain.dto;

import java.util.List;

public record PageDTO<T>(
        int page,
        Long totalElements,
        List<T> content,
        Integer totalPages,
        Boolean hasNext,
        Boolean hasPrevious
) {


}