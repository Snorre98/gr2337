package restserver;

import domainlogic.Review;
import org.springframework.stereotype.Service;

/**
 * Services related to review.
 * */
@Service
public class ReviewService {

  /**
   * empty constructor for initialization.
   **/
  ReviewService() {}

  //TODO: statefull bad
  private Review review;

  /**
   * Service for getting username on review.
   * */
  public String getUsername() {
    try {
      //TODO: Is this returning right?
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
  public Integer getRating() {
    try {
      return review.getRating();
      //200_OK
    } catch (Exception e) {
      throw new UnsupportedOperationException(e + "could not get rating");
      //BAD_REQUEST
    }
  }
}
