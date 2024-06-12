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
@AllArgsConstructor
@NoArgsConstructor
@Table("achievement")
public class Achievement {
    @Id
    @JsonIgnore
    @JsonProperty("id")
    private Long id;

    @JsonIgnore
    @JsonProperty("tournament_id")
    private Long tournamentId;

    @JsonIgnore
    @JsonProperty("category_id")
    private Long categoryId;

    @JsonProperty("position")
    private Integer position;

    @Transient
    @JsonProperty("category")
    private Category category;

    @Transient
    @JsonProperty("tournament")
    private Tournament tournament;
}
