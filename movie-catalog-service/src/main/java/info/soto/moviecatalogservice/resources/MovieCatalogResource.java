package info.soto.moviecatalogservice.resources;

import info.soto.moviecatalogservice.models.CatalogItem;
import info.soto.moviecatalogservice.models.UserRatings;
import info.soto.moviecatalogservice.services.MovieInfoService;
import info.soto.moviecatalogservice.services.UserRatingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {

    @Autowired
    private MovieInfoService movieInfoService;

    @Autowired
    private UserRatingsService userRatingsService;

    @GetMapping("/{userId}")
    public List<CatalogItem> getCatalog(@PathVariable("userId") String userId) {
        UserRatings userRatings = userRatingsService.getUserRatings(userId);
        return userRatings.getUserRatings().stream()
                .map(rating -> movieInfoService.getCatalogItem(rating))
                .collect(Collectors.toList());
    }

}
