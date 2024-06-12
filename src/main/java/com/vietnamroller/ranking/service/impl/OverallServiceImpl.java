package com.vietnamroller.ranking.service.impl;

import com.vietnamroller.ranking.model.Overall;
import com.vietnamroller.ranking.repository.OverAllRepository;
import com.vietnamroller.ranking.service.GenericReactiveService;
import com.vietnamroller.ranking.service.OverallService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class OverallServiceImpl extends GenericReactiveService<Overall, Long> implements OverallService {

    public OverallServiceImpl(OverAllRepository repository) {
        super(repository);
    }

    @Override
    protected void updateEntity(Overall existingEntity, Overall newEntity) {
        existingEntity.setCategoryId(newEntity.getCategoryId());
    }
}
