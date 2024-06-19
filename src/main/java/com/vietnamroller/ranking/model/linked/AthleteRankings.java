package com.vietnamroller.ranking.model.linked;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.vietnamroller.ranking.model.Athlete;
import com.vietnamroller.ranking.model.Ranking;
import lombok.Data;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("athlete_rankings")
public class AthleteRankings {

    @JsonIgnore
    @JsonProperty("athlete_id")
    private Long athleteId;

    @JsonIgnore
    @JsonProperty("ranking_id")
    private Long rankingId;

    @Transient
    @JsonProperty("ranking")
    private Ranking ranking;

    @Transient
    @JsonProperty("athlete")
    private Athlete athlete;
}
