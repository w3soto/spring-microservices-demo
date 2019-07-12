package info.soto.moviecatalogservice.services;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import info.soto.moviecatalogservice.models.CatalogItem;
import info.soto.moviecatalogservice.models.Movie;
import info.soto.moviecatalogservice.models.Rating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Service
public class MovieInfoService {

    @Autowired
    @Qualifier("balancedRestTemplate")
    private RestTemplate restTemplate;

    @Value("${app.movieInfoService}")
    private String movieInfoService;

    // Circuit breaker pattern
//    @HystrixCommand(fallbackMethod = "getFallbackCatalogItem", commandProperties = {
//            @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds", value = "2000"), // kill after 2s
//            @HystrixProperty(name="circuitBreaker.requestVolumeThreshold", value = "5"), // number of requests to use for decision
//            @HystrixProperty(name="circuitBreaker.errorThresholdPercentage", value = "50"), // threshold, how many requests can fail
//            @HystrixProperty(name="circuitBreaker.sleepWindowInMilliseconds", value = "5000"), // on error, hold for some time...
//    })

    // bulkhead patter
    @HystrixCommand(
            fallbackMethod = "getFallbackCatalogItem",
            threadPoolKey = "getCatalogItemThreadPool",
            threadPoolProperties = {
                    @HystrixProperty(name = "coreSize", value = "2"), // thread pool size
                    @HystrixProperty(name = "maxQueueSize", value = "10") // max queued requests
            }
    )
    public CatalogItem getCatalogItem(Rating rating) {
        Movie movieInfo = restTemplate.getForObject("http://{service}/movies/{movieId}",
                Movie.class, movieInfoService, rating.getMoviedId());
        return new CatalogItem(movieInfo, rating.getRating());
    }

    public CatalogItem getFallbackCatalogItem(Rating rating) {
        CatalogItem c = new CatalogItem();
        c.setMovie(new Movie(rating.getMoviedId(), "N/A", "N/A", Arrays.asList()));
        c.setRating(rating.getRating());
        return c;
    }

}
