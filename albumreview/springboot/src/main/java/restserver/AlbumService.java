package restserver;

import domainlogic.Album;
import domainlogic.Review;
import java.util.List;

import dto.AlbumDTO;
import org.springframework.stereotype.Service;

/**
 * Services related to album.
 * */
@Service
public class AlbumService {

  //TODO: statefull bad
  private Album album;

  /**
   * empty constructor for initialization.
   * */
  AlbumService() {}

  /**
   * Service for getting artist on album.
   * */
  public String getArtist() {
    try {
      return album.getArtist();
      //200_OK
    } catch (Exception e) {
      //BAD_REQUEST
      throw new UnsupportedOperationException(e + "could not get artist");
    }
  }

  /**
   * Service for getting name of album.
   * {@return album name}
   * */
  public String getName() {
    try {
      return album.getName();
      //200_OK
    } catch (Exception e) {
      throw new UnsupportedOperationException(e + "could not get names");
      //BAD_REQUEST
    }
  }

  /* #### review related services managed by album #### */
  /**
   * adds review to album.
   * @param username adding review
   * @param rating of album
   * {@return http response}
   * */
  public String addReview(String username, int rating) {
    try {
      Review review = new Review(username, rating);
      album.addReview(review);
      return "200_OK";
    } catch (Exception e) {
      throw new UnsupportedOperationException(e + "could not add album");
      //BAD_REQUEST
    }
  }

  /**
   * Service for removing review from album.
   * @param index of review to be removed
   * @param  username of user removing review
   * */
  public String removeReview(int index, String username) {
    try {
      album.removeReview(index, username);
      return "200_OK";
    } catch (Exception e) {
      throw new UnsupportedOperationException(e + "could not remove review");
      //BAD_REQUEST
    }
  }

  /**
   * Service for getting review by index.
   * @param index of review to be gotten
   * */
  public Review getAlbumReview(int index) {
    try {
      return album.getReview(index);
      //return "200_OK";
    } catch (Exception e) {
      throw new UnsupportedOperationException(e + "could not get review");
      //BAD_REQUEST
    }
  }

  /**
   * Service for getting review list on album.
   * */
  public List<Review> getReviewList() {
    try {
      return album.getReviews();
      //return "200_OK";
    } catch (Exception e) {
      throw new UnsupportedOperationException(e + "could not get list of reviews");
      //BAD_REQUEST
    }
  }
}
