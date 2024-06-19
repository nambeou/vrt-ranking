package com.vietnamroller.ranking.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Table;

import java.util.List;

@Data

@Table("overall")
public class Overall {
    @Id
    @JsonProperty("id")
    private Long id;

    @JsonIgnore
    @JsonProperty("category_id")
    private Long categoryId;

    @Transient
    @JsonProperty("category")
    private Category category;

    @Transient
    @JsonProperty("rankings")
    private List<Ranking> rankings;

}
