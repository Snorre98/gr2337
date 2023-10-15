package domainlogic;

import java.util.Objects;

/**
 * Core AlbumReview object class.
 */
public class AlbumReview {

  private String name;
  private int rating;

  public AlbumReview(String name, int rating) {
    this.name = name;
    this.rating = rating;
  }

  public String getName() {
    return this.name;
  }

  public int getRating() {
    return this.rating;
  }

  @Override
  public String toString() {
    return "Name: " + name + " Rating: " + rating;
  }


  /**
   * Used to compare objects directly. If fields are added, this must be updated.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AlbumReview that = (AlbumReview) o;
    return Objects.equals(this.getName(), that.getName()) && rating == that.rating; // rating is int
  }

  /**
   * Used to compare objects directly. If fields are added, this must be updated.
   */
  @Override
  public int hashCode() {
    return Objects.hash(this.getName(), rating);
  }

}
