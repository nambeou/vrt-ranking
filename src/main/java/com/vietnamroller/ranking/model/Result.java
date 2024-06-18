package com.vietnamroller.ranking.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Table;

@Data

@Table("result")
public class Result {
    @Id
    @JsonIgnore
    @JsonProperty("id")
    private Long id;

    @JsonIgnore
    @JsonProperty("category_id")
    private Long categoryId;

    @JsonIgnore
    @JsonProperty("tournament_id")
    private Long tournamentId;

    @JsonIgnore
    @JsonProperty("athlete_id")
    private Long athleteId;

    @JsonProperty("result")
    private String result;

    @JsonProperty("point")
    private Integer point;

    @Transient
    @JsonProperty("category")
    private Category category;

    @Transient
    @JsonProperty("tournament")
    private Tournament tournament;

    @Transient
    @JsonProperty("athlete")
    private Athlete athlete;
}
