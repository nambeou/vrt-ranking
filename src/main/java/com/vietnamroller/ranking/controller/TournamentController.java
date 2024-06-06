package com.vietnamroller.ranking.controller;

import com.vietnamroller.ranking.dto.TournamentDTO;
import com.vietnamroller.ranking.service.GenericService;
import com.vietnamroller.ranking.service.TournamentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/tournaments")
@Slf4j
public class TournamentController extends GenericController<TournamentDTO, Long> {
    private final TournamentService tournamentService;


    @Override
    protected GenericService<TournamentDTO, Long> service() {
        return this.tournamentService;
    }
}
