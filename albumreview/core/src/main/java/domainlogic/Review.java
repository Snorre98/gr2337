package domainlogic;

/**
 * Core AlbumReview object class.
 */
public class Review {
  private String user;
  private int rating;

  public Review(String user, int rating) {
    this.user = user;
    this.rating = rating;
  }

  public String getName() {
    return this.user;
  }

  public int getRating() {
    return this.rating;
  }

  @Override
  public String toString() {
    return "Username: " + user + " Rating: " + rating;
  }

}
