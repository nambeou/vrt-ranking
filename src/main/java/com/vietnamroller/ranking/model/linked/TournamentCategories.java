package com.vietnamroller.ranking.model.linked;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("tournament_categories")
public class TournamentCategories {
    @Id
    @JsonProperty("id")
    private Long id;

    @JsonProperty("tournament_id")
    private Long tournamentId;

    @JsonProperty("category_id")
    private Long categoryId;
}
