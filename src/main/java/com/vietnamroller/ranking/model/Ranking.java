package com.vietnamroller.ranking.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table("ranking")
public class Ranking {

    @Id
    @JsonIgnore
    @JsonProperty("id")
    private Long id;

    @JsonIgnore
    @JsonProperty("category_id")
    private Long categoryId;

    @JsonProperty("description")
    private String description;

    @JsonProperty("point")
    private Integer point;

    @JsonIgnore
    @JsonProperty("best_result_id")
    private Long bestResultId;

    @Transient
    @JsonProperty("category")
    private Category category;

    @Transient
    @JsonProperty("best_result")
    private Result bestResult;
}
