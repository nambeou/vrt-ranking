package com.vietnamroller.ranking.model.linked;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("athlete_achievements")
public class AthleteResults {

    @JsonProperty("athlete_id")
    private Long athleteId;

    @JsonProperty("result_id")
    private Long resultId;
}
