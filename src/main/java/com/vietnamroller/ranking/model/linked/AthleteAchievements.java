package com.vietnamroller.ranking.model.linked;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("athlete_achievements")
public class AthleteAchievements {

    @JsonProperty("athlete_id")
    private Long athleteId;

    @JsonProperty("achievement_id")
    private Long achievementId;
}
