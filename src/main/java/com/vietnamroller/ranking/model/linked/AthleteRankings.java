package com.vietnamroller.ranking.model.linked;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("athlete_rankings")
public class AthleteRankings {

    @JsonProperty("athlete_id")
    private Long athleteId;

    @JsonProperty("ranking_id")
    private Long rankingId;
}
