package com.vietnamroller.ranking.controller;

import com.vietnamroller.ranking.dto.AchievementDTO;
import com.vietnamroller.ranking.service.AchievementService;
import com.vietnamroller.ranking.service.GenericService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import reactor.core.publisher.Flux;

@Controller
@RequiredArgsConstructor
@RequestMapping("/achievements")
@Slf4j
public class AchievementController extends GenericController<AchievementDTO, Long> {
    private final AchievementService achievementService;

    @Override
    protected GenericService<AchievementDTO, Long> service() {
        return this.achievementService;
    }
}
