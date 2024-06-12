package com.vietnamroller.ranking.controller;

import com.vietnamroller.ranking.model.Category;
import com.vietnamroller.ranking.service.CategoryService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/categories")
public class CategoryController extends GenericReactiveController<Category, Long> {

    public CategoryController(CategoryService service) {
        super(service);
    }
}
