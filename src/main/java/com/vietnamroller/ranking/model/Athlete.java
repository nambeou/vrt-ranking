package com.vietnamroller.ranking.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;

@Data
@Table("athlete")
public class Athlete {
    @Id
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

    @JsonProperty("profile_photo_url")
    private String profilePhotoUrl;

    @JsonProperty("team_id")
    private Long teamId;
}
