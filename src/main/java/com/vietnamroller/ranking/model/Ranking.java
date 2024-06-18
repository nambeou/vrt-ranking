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
@Table("ranking")
public class Ranking {

    @Id
    @JsonProperty("id")
    private Long id;

    @JsonProperty("description")
    private String description;

    @JsonProperty("rank")
    private Integer rank;

    @JsonProperty("best_result_id")
    private Long bestResultId;

    @JsonProperty("category_id")
    private Long categoryId;

    @Transient
    @JsonProperty("best_result")
    private Result bestResult;

    @Transient
    @JsonProperty("category")
    private Category category;
}
