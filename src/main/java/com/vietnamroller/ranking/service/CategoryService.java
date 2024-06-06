package com.vietnamroller.ranking.service;

import com.vietnamroller.ranking.dto.CategoryDTO;
import com.vietnamroller.ranking.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@RequiredArgsConstructor
public class CategoryService extends GenericService<CategoryDTO, Long> {

    private final CategoryRepository categoryRepository;

    @Override
    protected R2dbcRepository<CategoryDTO, Long> repository() {
        return this.categoryRepository;
    }

    @Override
    Mono<CategoryDTO> addDetails(CategoryDTO category) {
        return Mono.just(category);
    }
}
