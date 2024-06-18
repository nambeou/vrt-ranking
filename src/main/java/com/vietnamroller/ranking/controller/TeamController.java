package com.vietnamroller.ranking.controller;

import com.vietnamroller.ranking.model.Athlete;
import com.vietnamroller.ranking.model.Team;
import com.vietnamroller.ranking.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/api/v1/teams")
public class TeamController extends GenericReactiveController<Team, Long> {
    private final TeamService teamService;

    @Autowired
    public TeamController(TeamService teamService) {
        super(teamService);
        this.teamService = teamService;
    }

    @GetMapping("/{teamId}/athletes")
    public Flux<Athlete> getAllAthletesByTeamId(@PathVariable Long teamId) {
        return teamService.findAllAthletesByTeamId(teamId);
    }
}
