package com.vietnamroller.ranking.controller;

import com.vietnamroller.ranking.model.Category;
import com.vietnamroller.ranking.model.Tournament;
import com.vietnamroller.ranking.service.CategoryService;
import com.vietnamroller.ranking.service.TournamentCategoryService;
import com.vietnamroller.ranking.service.TournamentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/api/v1/tournaments")
public class TournamentController extends GenericReactiveController<Tournament, Long> {

    private final TournamentCategoryService tournamentCategoryService;
    private final CategoryService categoryService;

    @Autowired
    public TournamentController(TournamentService service,
                                TournamentCategoryService tournamentCategoryService,
                                CategoryService categoryService) {
        super(service);
        this.tournamentCategoryService = tournamentCategoryService;
        this.categoryService = categoryService;
    }


    @GetMapping("/{tournamentId}/categories")
    public Flux<Category> getAllCategoriesByTournamentId(@PathVariable Long tournamentId) {
        return tournamentCategoryService.findAllCategoriesByTournamentId(tournamentId)
                .flatMap(categoryService::getById);
    }
}
