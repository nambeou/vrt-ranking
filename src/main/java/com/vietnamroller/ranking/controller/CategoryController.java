package com.vietnamroller.ranking.controller;

import com.vietnamroller.ranking.model.Category;
import com.vietnamroller.ranking.model.linked.TournamentCategories;
import com.vietnamroller.ranking.service.CategoryService;
import com.vietnamroller.ranking.service.TournamentCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController extends GenericReactiveController<Category, Long> {

    private final TournamentCategoryService tournamentCategoryService;

    @Autowired
    public CategoryController(CategoryService service, TournamentCategoryService tournamentCategoryService) {
        super(service);
        this.tournamentCategoryService = tournamentCategoryService;
    }

    // TournamentCategory endpoints

    @GetMapping("/{categoryId}/tournaments")
    public Flux<TournamentCategories> getAllTournamentsByCategoryId(@PathVariable Long categoryId) {
        return tournamentCategoryService.findAllTournamentsByCategoryId(categoryId);
    }
}
