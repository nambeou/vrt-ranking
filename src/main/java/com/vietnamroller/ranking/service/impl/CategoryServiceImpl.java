package com.vietnamroller.ranking.service.impl;

import com.vietnamroller.ranking.model.Category;
import com.vietnamroller.ranking.repository.CategoryRepository;
import com.vietnamroller.ranking.service.CategoryService;
import com.vietnamroller.ranking.service.GenericReactiveService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CategoryServiceImpl extends GenericReactiveService<Category, Long> implements CategoryService {

    public CategoryServiceImpl(CategoryRepository repository) {
        super(repository);
    }

    @Override
    protected void updateEntity(Category existingEntity, Category newEntity) {
        existingEntity.setName(newEntity.getName());
        existingEntity.setDescription(newEntity.getDescription());
        existingEntity.setGender(newEntity.getGender());
        existingEntity.setStartAge(newEntity.getStartAge());
        existingEntity.setEndAge(newEntity.getEndAge());
    }

}
