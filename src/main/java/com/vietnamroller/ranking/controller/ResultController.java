package com.vietnamroller.ranking.controller;

import com.vietnamroller.ranking.model.Result;
import com.vietnamroller.ranking.service.ResultService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/results")
public class ResultController extends GenericReactiveController<Result, Long> {

    public ResultController(ResultService service) {
        super(service);
    }
}
