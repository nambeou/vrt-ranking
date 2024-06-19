package com.vietnamroller.ranking.model.linked;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("tournament_results")
public class TournamentResults {

    @JsonProperty("tournament_id")
    private Long tournamentId;

    @JsonProperty("result_id")
    private Long resultId;
}
