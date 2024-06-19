package com.vietnamroller.ranking.controller;

import com.vietnamroller.ranking.model.Category;
import com.vietnamroller.ranking.model.Ranking;
import com.vietnamroller.ranking.model.Tournament;
import com.vietnamroller.ranking.service.CategoryService;
import com.vietnamroller.ranking.service.RankingService;
import com.vietnamroller.ranking.service.TournamentCategoryService;
import com.vietnamroller.ranking.service.TournamentService;
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
    private final TournamentService tournamentService;
    private final RankingService rankingService;


    @Autowired
    public CategoryController(CategoryService service,
                              TournamentCategoryService tournamentCategoryService,
                              RankingService rankingService,
                              TournamentService tournamentService) {
        super(service);
        this.tournamentCategoryService = tournamentCategoryService;
        this.tournamentService = tournamentService;
        this.rankingService = rankingService;
    }

    // TournamentCategory endpoints

    @GetMapping("/{categoryId}/tournaments")
    public Flux<Tournament> getAllTournamentsByCategoryId(@PathVariable Long categoryId) {
        return tournamentCategoryService.findAllTournamentsByCategoryId(categoryId)
                .flatMap(tournamentService::getById);
    }

    @GetMapping("/{categoryId}/rankings")
    public Flux<Ranking> getAllRankingsByCategoryId(@PathVariable Long categoryId) {
        return rankingService.getByCategoryId(categoryId);
    }
}
