package restserver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * API controller where all comes together.
 * */
@RestController
@RequestMapping("/api/albumreview")
public class AlbumReviewApiController {
  //TODO: make everything come together here???

  @Autowired
  private AlbumListService albumListService;

  public AlbumReviewApiController() {}

  public AlbumReviewApiController(AlbumListService albumListService) {
    this.albumListService = albumListService;
  }

}
