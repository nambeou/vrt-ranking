package com.vietnamroller.ranking.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data

@Table("category")
public class Category {
    @Id
    @JsonIgnore
    @JsonProperty("id")
    private Long id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    @JsonProperty("start_age")
    private Integer startAge;

    @JsonProperty("end_age")
    private Integer endAge;

    @JsonProperty("gender")
    private String gender;
}