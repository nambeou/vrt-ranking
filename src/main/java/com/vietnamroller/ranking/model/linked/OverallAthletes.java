package com.vietnamroller.ranking.model.linked;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("overall_athletes")
public class OverallAthletes {
    @Id
    @JsonProperty("id")
    private Long id;

    @JsonProperty("overall_id")
    private Long overallId;

    @JsonProperty("athlete_id")
    private Long athleteId;
}
