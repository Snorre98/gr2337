package restserver;

import domainlogic.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * API controller album.
 * */
@RestController
@RequestMapping("/api/album")
public class AlbumApiController {
  @Autowired
  private AlbumService albumService;

  /**
   * empty constructor.
   * */
  public AlbumApiController() {}

  public AlbumApiController(AlbumService albumService) {
    this.albumService = albumService;
  }

  @GetMapping("/getArtist/{albumId}")
  public String getArtist(@PathVariable UUID albumId) {
    return albumService.getArtist(albumId);
  }

  @GetMapping("/getName/{albumId}")
  public String getName(@PathVariable UUID albumId) {
    return albumService.getName(albumId);
  }

  @PostMapping("/addReview/{albumId}/{username}/{rating}")
  public String addReview(@PathVariable UUID albumId,
                          @PathVariable String username,
                          @PathVariable int rating) {
    return albumService.addReview(albumId, username, rating);
  }

  @DeleteMapping("/deleteReview/{albumId}/{index}/{username}")
  public String removeReview(@PathVariable UUID albumId,
                             @PathVariable int index,
                             @PathVariable String username) {
    return albumService.removeReview(albumId, index, username);
  }

  @GetMapping("/getReview/{albumId}/{index}")
  public Review getReview(@PathVariable UUID albumId, @PathVariable int index) {
    return albumService.getAlbumReview(albumId, index);
  }

  @GetMapping("getReviews/{albumId}")
  public List<Review> getReviewList(@PathVariable UUID albumId) {
    return albumService.getReviewList(albumId);
  }
}
