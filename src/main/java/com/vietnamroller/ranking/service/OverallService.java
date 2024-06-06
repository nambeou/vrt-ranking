package com.vietnamroller.ranking.service;

import com.vietnamroller.ranking.dto.OverallDTO;
import com.vietnamroller.ranking.repository.OverAllRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@RequiredArgsConstructor
public class OverallService extends GenericService<OverallDTO, Long> {

    private final OverAllRepository overAllRepository;
    private final AthleteService athleteService;
    private final CategoryService categoryService;

    @Override
    protected R2dbcRepository<OverallDTO, Long> repository() {
        return this.overAllRepository;
    }

    @Override
    Mono<OverallDTO> addDetails(OverallDTO overall) {
        log.info("overall.getCategoryId() -> {}", overall.getCategoryId());
        log.info("overall.getAthleteIds() -> {}", overall.getAthleteIds().toArray());

        return Mono.zip(
                        categoryService.findById(overall.getCategoryId()),
                        athleteService.findByIds(overall.getAthleteIds()).collectList()
                )
                .map(tuple -> {
                    overall.setCategory(tuple.getT1());
                    overall.setAthletes(tuple.getT2());
                    return overall;
                });
    }
}
