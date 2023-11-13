package restserver;

import domainlogic.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * API controller album.
 * */
@RestController
@RequestMapping("/api/album")
public class AlbumController {
  @Autowired
  private AlbumService albumService;

  /**
   * empty constructor.
   * */
  public AlbumController() {}

  public AlbumController(AlbumService albumService) {
    this.albumService = albumService;
  }

  @GetMapping("/getArtist")
  public String getArtist() {
    return albumService.getArtist();
  }

  @GetMapping("/getName")
  public String getName() {
    return albumService.getName();
  }

  @PostMapping("/addReview/{username}/{rating}")
  public String addReview(@PathVariable String username, @PathVariable int rating) {
    return albumService.addReview(username, rating);

  }

  @DeleteMapping("/deleteReview/{index}/{username}")
  public String removeReview(@PathVariable int index, @PathVariable String username) {
    return albumService.removeReview(index, username);
  }

  @GetMapping("/getReview/{index}")
  public Review getReview(@PathVariable int index) {
    return albumService.getAlbumReview(index);
  }

  @GetMapping("getReviews")
  public List<Review> getReviewList() {
    return albumService.getReviewList();
  }
}
