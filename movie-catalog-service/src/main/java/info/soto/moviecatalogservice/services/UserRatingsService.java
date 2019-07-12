package info.soto.moviecatalogservice.services;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import info.soto.moviecatalogservice.models.Rating;
import info.soto.moviecatalogservice.models.UserRatings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Service
public class UserRatingsService {

    @Autowired
    @Qualifier("balancedRestTemplate")
    private RestTemplate restTemplate;

    @Value("${app.ratingsDataService}")
    private String ratingsDataService;

    @HystrixCommand(fallbackMethod = "getFallbackUserRatings")
    public UserRatings getUserRatings(String userId) {
        return restTemplate.getForObject("http://{service}/ratingsdata/users/{userId}",
                UserRatings.class, ratingsDataService, userId);
    }

    public UserRatings getFallbackUserRatings(String userId) {
        UserRatings r = new UserRatings();
        r.setUserId(userId);
        r.setUserRatings(Arrays.asList(new Rating("N/A", 0)));
        return r;
    }
}
