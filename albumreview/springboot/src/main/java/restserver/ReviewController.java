package restserver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * API review controller.
 * */
@RestController
@RequestMapping("/api/review")
public class ReviewController {

  @Autowired
  private ReviewService reviewService;

  /**
   * empty constructor.
   * */
  public ReviewController() {}

  public ReviewController(ReviewService reviewService) {
    this.reviewService = reviewService;
  }

  @GetMapping("/getUsername")
  public String getUsername() {
    return reviewService.getUsername();
  }

  @GetMapping("/getRating")
  public Integer getRating() {
    return reviewService.getRating();
  }
}
