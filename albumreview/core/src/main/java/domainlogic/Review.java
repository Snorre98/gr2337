package domainlogic;

import java.util.Objects;

/**
 * Core AlbumReview object class. Manages a list of Review objects.
 */
public class Review {

  private String user;
  private int rating;

  /**
   * Constructor for Review class.
   * 
   * @param user user connected to Review object.
   * @param rating connected to Review object.
   */
  public Review(String user, int rating) {
    this.user = user;
    if (rating < 1 || rating > 10) {
      throw new IllegalArgumentException("Rating has to be a number between 1 and 10");
    }
    this.rating = rating;
  }

  public String getUserName() {
    return this.user;
  }

  public int getRating() {
    return this.rating;
  }

  @Override
  public String toString() {
    return "Username: " + user + " Rating: " + rating;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Review that = (Review) o;
    return Objects.equals(this.getUserName(), that.getUserName()) && this.rating == that.rating;
  }

  @Override
  public int hashCode() {
    return Objects.hash(getUserName(), rating);
  }

}

