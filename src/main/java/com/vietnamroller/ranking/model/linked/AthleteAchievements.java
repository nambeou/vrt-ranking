package com.vietnamroller.ranking.model.linked;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("athlete_achievements")
public class AthleteAchievements {

    @JsonIgnore
    @JsonProperty("athlete_id")
    private Long athleteId;

    @JsonIgnore
    @JsonProperty("achievement_id")
    private Long achievementId;
}
