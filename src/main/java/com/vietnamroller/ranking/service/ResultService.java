package com.vietnamroller.ranking.service;

import com.vietnamroller.ranking.dto.ResultDTO;
import com.vietnamroller.ranking.repository.ResultRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@RequiredArgsConstructor
public class ResultService extends GenericService<ResultDTO, Long> {

    private final ResultRepository resultRepository;
    private final TournamentService tournamentService;
    private final AthleteService athleteService;
    private final CategoryService categoryService;

    @Override
    protected R2dbcRepository<ResultDTO, Long> repository() {
        return this.resultRepository;
    }

    @Override
    Mono<ResultDTO> addDetails(ResultDTO result) {
        return Mono.zip(
                        tournamentService.findById(result.getTournamentId()),
                        categoryService.findById(result.getCategoryId()),
                        athleteService.findById(result.getAthleteId())
                )
                .map(tuple -> {
                    result.setTournament(tuple.getT1());
                    result.setCategory(tuple.getT2());
                    result.setAthlete(tuple.getT3());
                    return result;
                });
    }

}
