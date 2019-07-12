package info.soto.ratingsdataservice.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Rating {

    private String moviedId;
    private int rating;

}
