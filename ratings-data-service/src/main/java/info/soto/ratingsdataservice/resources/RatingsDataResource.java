package info.soto.ratingsdataservice.resources;

import info.soto.ratingsdataservice.models.Rating;
import info.soto.ratingsdataservice.models.UserRatings;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
@RequestMapping("ratingsdata")
public class RatingsDataResource {

    @RequestMapping("/{movieId}")
    public Rating getRating(@PathVariable("movieId") String movieId) {
        return new Rating(movieId, 4);
    }

    @GetMapping("/users/{userId}")
    public UserRatings getUserRatings(@PathVariable("userId") String userId) {
        return new UserRatings(
                userId,
                Arrays.asList(
                    new Rating("123", 1),
                    new Rating("550", 5),
                    new Rating("789", 4)
                )
        );
    }
}
