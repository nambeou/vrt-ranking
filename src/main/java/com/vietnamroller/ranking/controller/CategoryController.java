package com.vietnamroller.ranking.controller;

import com.vietnamroller.ranking.dto.CategoryDTO;
import com.vietnamroller.ranking.service.CategoryService;
import com.vietnamroller.ranking.service.GenericService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/categories")
@Slf4j
public class CategoryController extends GenericController<CategoryDTO, Long> {
    private final CategoryService categoryService;

    @Override
    protected GenericService<CategoryDTO, Long> service() {
        return this.categoryService;
    }


}
