package com.vietnamroller.ranking.service;

import com.vietnamroller.ranking.model.Category;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CategoryService extends ReactiveCrudService<Category, Long> {
    // Additional methods specific to CategoryService can be declared here
}
