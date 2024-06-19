package com.vietnamroller.ranking.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Table;

@Data

@Table("result")
public class Result {
    @Id
    @JsonProperty("id")
    private Long id;

    @JsonProperty("category_id")
    private Long categoryId;

    @JsonProperty("result")
    private String result;

    @JsonProperty("point")
    private Integer point;

    @Transient
    @JsonProperty("category")
    private Category category;
}
