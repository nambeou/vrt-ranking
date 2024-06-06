package com.vietnamroller.ranking.service;

import com.vietnamroller.ranking.dto.TournamentDTO;
import com.vietnamroller.ranking.repository.TournamentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@RequiredArgsConstructor
public class TournamentService extends GenericService<TournamentDTO, Long> {

    private final TournamentRepository tournamentRepository;
    private final CategoryService categoryService;

    @Override
    protected R2dbcRepository<TournamentDTO, Long> repository() {
        return this.tournamentRepository;
    }

    @Override
    Mono<TournamentDTO> addDetails(TournamentDTO tournament) {
        return categoryService.findByIds(tournament.getCategoryIds())
                .collectList()
                .map(categories -> {
                    tournament.setCategories(categories);
                    return tournament;
                });
    }
}
