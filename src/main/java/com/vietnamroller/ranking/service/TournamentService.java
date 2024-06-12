package com.vietnamroller.ranking.service;

import com.vietnamroller.ranking.model.Tournament;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface TournamentService extends ReactiveCrudService<Tournament, Long> {
    // Additional methods specific to TournamentService can be declared here
}
