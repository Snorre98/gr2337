package restserver;

import domainlogic.AlbumList;
import domainlogic.Review;
import org.springframework.stereotype.Service;
import statepersistence.LoadFromFile;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

/**
 * Services related to review.
 * */
@Service
public class ReviewService {

  /**
   * empty constructor for initialization.
   **/
  ReviewService() {}

  private static final String saveFile = "IT1901gr2337/AlbumReviewApp/albumreviews.json";
  Path saveFilePath = Paths.get(System.getProperty("user.home"), saveFile);

  public AlbumList loadAlbumList() throws IOException {
    return LoadFromFile.loadFromFile(saveFilePath, true);
  }

  public Review loadReviewObject(UUID albumId, int index) throws IOException {
    return loadAlbumList().getAlbumById(albumId).getReview(index);
  }

  /**
   * Service for getting username on review.
   * */
  public String getUsername(UUID albumId, int index) {
    try {
      //TODO: pass albumId and index from ui
      Review review = loadReviewObject(albumId, index);
      //TODO: Is this returning right? Return void??
      return review.getUserName();
      //return "200_OK";
    } catch (Exception e) {
      throw new UnsupportedOperationException(e + "could not get username");
      //return "BAD_REQUEST";
    }
  }

  /**
   * Service for getting rating on review.
   * */
  public Integer getRating(UUID albumId, int index) {
    try {
      //TODO: pass albumId and index from ui
      Review review = loadReviewObject(albumId, index);
      return review.getRating();
      //200_OK
    } catch (Exception e) {
      throw new UnsupportedOperationException(e + "could not get rating");
      //BAD_REQUEST
    }
  }
}
