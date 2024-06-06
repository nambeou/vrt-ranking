package com.vietnamroller.ranking.repository;

import com.vietnamroller.ranking.dto.CategoryDTO;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends R2dbcRepository<CategoryDTO, Long> {
}