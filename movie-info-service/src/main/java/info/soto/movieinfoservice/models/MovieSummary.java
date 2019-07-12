package info.soto.movieinfoservice.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class MovieSummary {

    private Integer id;

    @JsonProperty("original_title")
    private String title;

    @JsonProperty("overview")
    private String description;

    private List<Genre> genres;

}
