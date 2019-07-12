package info.soto.moviecatalogservice.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Movie {

    private String movieId;
    private String name;
    private String desc;

    private List<Genre> genres;

}
