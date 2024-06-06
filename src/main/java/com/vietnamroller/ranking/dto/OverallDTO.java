package com.vietnamroller.ranking.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Table;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table("overall")
public class OverallDTO {
    @Id
    @JsonIgnore
    @JsonProperty("id")
    private Long id;

    @JsonIgnore
    @JsonProperty("category_id")
    private Long categoryId;

    @JsonIgnore
    @JsonProperty("athlete_ids")
    private List<Long> athleteIds;

    @Transient
    @JsonProperty("athletes")
    private List<AthleteDTO> athletes;

    @Transient
    @JsonProperty("category")
    private CategoryDTO category;
}
