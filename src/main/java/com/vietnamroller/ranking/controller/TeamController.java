package com.vietnamroller.ranking.controller;

import com.vietnamroller.ranking.model.Team;
import com.vietnamroller.ranking.service.TeamService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/teams")
public class TeamController extends GenericReactiveController<Team, Long> {

    public TeamController(TeamService service) {
        super(service);
    }
}
