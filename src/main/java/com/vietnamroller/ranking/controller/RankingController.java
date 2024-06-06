package com.vietnamroller.ranking.controller;

import com.vietnamroller.ranking.dto.RankingDTO;
import com.vietnamroller.ranking.service.GenericService;
import com.vietnamroller.ranking.service.RankingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/rankings")
@Slf4j
public class RankingController extends GenericController<RankingDTO, Long> {
    private final RankingService rankingService;


    @Override
    protected GenericService<RankingDTO, Long> service() {
        return this.rankingService;
    }
}
