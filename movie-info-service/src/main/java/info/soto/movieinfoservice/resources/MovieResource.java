package info.soto.movieinfoservice.resources;

import info.soto.movieinfoservice.models.Genre;
import info.soto.movieinfoservice.models.Movie;
import info.soto.movieinfoservice.models.MovieSummary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;

@RestController
@RequestMapping("/movies")
public class MovieResource {

    @Value("${app.apiKey}")
    private String apiKey;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/{movieId}")
    public Movie getMovieInfo(@PathVariable("movieId") String movieId) {

//        try {
//            Thread.sleep(5000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        MovieSummary movieSummary = restTemplate.getForObject(
                "https://api.themoviedb.org/3/movie/{movieId}?api_key={apiKey}",
                MovieSummary.class,
                movieId, apiKey
        );

        ArrayList<Genre> genres = new ArrayList<Genre>();

        return new Movie(movieId, movieSummary.getTitle(), movieSummary.getDescription(), movieSummary.getGenres());

//        return new Movie(movieId, "Avatar", "Super movie", Arrays.asList(
//                new Genre("John Travolta"),
//                new Genre("Robert Redford"),
//                new Genre("Harrison Ford")
//        ));
    }
}
