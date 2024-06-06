package com.vietnamroller.ranking.controller;

import com.vietnamroller.ranking.dto.ResultDTO;
import com.vietnamroller.ranking.service.GenericService;
import com.vietnamroller.ranking.service.ResultService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/results")
@Slf4j
public class ResultController extends GenericController<ResultDTO, Long> {
    private final ResultService resultService;

    @Override
    protected GenericService<ResultDTO, Long> service() {
        return this.resultService;
    }


}
