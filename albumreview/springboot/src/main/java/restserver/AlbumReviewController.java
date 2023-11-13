package restserver;

import domainlogic.Album;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * API controller where all comes together.
 * */
@RestController
@RequestMapping("/api/albumreview")
public class AlbumReviewController {
  //TODO: make everything come together here???

  @Autowired
  private AlbumListService albumListService;

  public AlbumReviewController() {}

  public AlbumReviewController(AlbumListService albumListService) {
    this.albumListService = albumListService;
  }

}
