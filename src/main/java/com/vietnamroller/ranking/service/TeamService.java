package com.vietnamroller.ranking.service;

import com.vietnamroller.ranking.model.Athlete;
import com.vietnamroller.ranking.model.Team;
import reactor.core.publisher.Flux;

public interface TeamService extends ReactiveCrudService<Team, Long> {
    // Additional methods specific to TeamService can be declared here
    Flux<Athlete> findAllAthletesByTeamId(Long teamId);

}
