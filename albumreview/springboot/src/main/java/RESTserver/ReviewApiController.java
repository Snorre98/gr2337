package restserver;

import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * API review controller.
 * */
@RestController
@RequestMapping("/api/review")
public class ReviewApiController {

  @Autowired
  private ReviewService reviewService;

  /**
   * empty constructor.
   * */
  public ReviewApiController() {}

  public ReviewApiController(ReviewService reviewService) {
    this.reviewService = reviewService;
  }

  @GetMapping("/getUsername/{albumId}/{index}")
  public String getUsername(@PathVariable UUID albumId, @PathVariable int index) {
    return reviewService.getUsername(albumId, index);
  }

  @GetMapping("/getRating/{albumId}/{index}")
  public Integer getRating(@PathVariable UUID albumId, @PathVariable int index) {
    return reviewService.getRating(albumId, index);
  }
}
