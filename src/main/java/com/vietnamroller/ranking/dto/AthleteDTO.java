package com.vietnamroller.ranking.dto;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table("athlete")
public class AthleteDTO {
    @Id
    @JsonIgnore
    @JsonProperty("id")
    private Long id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("date_of_birth")
    private LocalDate dateOfBirth;

    @JsonProperty("gender")
    private String gender;

    @JsonProperty("join_date")
    private LocalDate joinDate;

    @JsonIgnore
    @JsonProperty("ranking_ids")
    private List<Long> rankingIds;

    @JsonIgnore
    @JsonProperty("achievement_ids")
    private List<Long> achievementIds;

    @Transient
    @JsonProperty("rankings")
    private List<RankingDTO> rankings;

    @Transient
    @JsonProperty("achievements")
    private List<AchievementDTO> achievements;
}
