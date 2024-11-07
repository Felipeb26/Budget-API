package com.batsworks.utils;

import com.batsworks.domain.dto.PageDTO;
import io.quarkus.hibernate.reactive.panache.PanacheQuery;
import io.smallrye.mutiny.Uni;

import java.util.List;

public class PageDTOUtility {

    public static <T> Uni<PageDTO<T>> create(PanacheQuery<T> query, int page, int pageSize) {
        // ou crie uma nova consulta se necessário
        Uni<Long> totalElements = query.count();

        // Criação de uma consulta para listar os itens da página
        PanacheQuery<T> pageQuery = query.page(page, pageSize);
        Uni<List<T>> content = pageQuery.list();

        // Calcular total de páginas
        Uni<Integer> totalPages = totalElements
                .map(total -> (int) Math.ceil((double) total / pageSize));

        // Verifica se há uma próxima página
        Uni<Boolean> hasNext = totalPages.map(totalPagesValue -> page < totalPagesValue - 1);

        // Verifica se há uma página anterior
        Uni<Boolean> hasPrevious = Uni.createFrom().item(page > 0);

        // Combina todos os Unis reativos
        return Uni.combine().all().unis(totalElements, content, totalPages, hasNext, hasPrevious)
                .asTuple()
                .map(tuple -> {
                    Long totalEl = tuple.getItem1();
                    List<T> cont = tuple.getItem2();
                    Integer totalPg = tuple.getItem3();
                    Boolean next = tuple.getItem4();
                    Boolean prev = tuple.getItem5();
                    return new PageDTO<>(page, totalEl, cont, totalPg, next, prev);
                });
    }
}
