package com.vietnamroller.ranking.controller;

import com.vietnamroller.ranking.dto.AthleteDTO;
import com.vietnamroller.ranking.service.AthleteService;
import com.vietnamroller.ranking.service.GenericService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/athletes")
@Slf4j
public class AthleteController extends GenericController<AthleteDTO, Long> {
    private final AthleteService athleteService;

    @Override
    protected GenericService<AthleteDTO, Long> service() {
        return this.athleteService;
    }


}
