package com.batsworks.utils;

import com.batsworks.domain.dto.PageDTO;
import io.quarkus.hibernate.reactive.panache.PanacheQuery;
import io.smallrye.mutiny.Uni;

import java.util.List;

public class PageDTOUtility {

    public static <T> Uni<PageDTO<T>> create(PanacheQuery<T> query, int page, int pageSize) {
        query.page(page, pageSize);

        Uni<List<T>> content = query.list();

        Uni<Long> totalElements = query.count();
        Uni<Integer> totalPages = totalElements
                .map(total -> (int) Math.ceil((double) total / pageSize));

        Uni<Boolean> hasNext = totalPages.map(totalPagesValue -> page < totalPagesValue - 1);

        Uni<Boolean> hasPrevious = Uni.createFrom().item(page > 0);

        return Uni.combine().all().unis(totalElements, content, totalPages, hasNext, hasPrevious)
                .with((totalEl, cont, totalPg, next, prev) -> new PageDTO<>(page, totalEl, cont, totalPg, next, prev));
    }
}
