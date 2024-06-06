package com.vietnamroller.ranking.controller;

import com.vietnamroller.ranking.dto.OverallDTO;
import com.vietnamroller.ranking.service.GenericService;
import com.vietnamroller.ranking.service.OverallService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/overalls")
@Slf4j
public class OverallController extends GenericController<OverallDTO, Long> {
    private final OverallService overallService;

    @Override
    protected GenericService<OverallDTO, Long> service() {
        return this.overallService;
    }


}
