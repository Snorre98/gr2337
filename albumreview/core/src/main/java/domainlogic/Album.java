package domainlogic;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Core Album object class.
 */
public class Album {
  private List<Review> reviews;
  private String artist;
  private String name;

  /**
   * Constructor for Album object class.
   * 
   * @param artist artist connected to the Album.
   * @param name name connected to the Album.
   */
  public Album(String artist, String name) {
    reviews = new ArrayList<>();
    this.artist = artist;
    this.name = name;
  }

  /**
   * adds Review object to reviews list.
   * 
   * @param review Review that gets added to list.
   */
  public void addReview(Review review) {
    reviews.add(review);
  }

  /**
   * Removes specific review from reviews list.
   * 
   * @param i int position of the review.
   */
  public void removeReview(int i, String username) {
    if (username.equals(getReview(i).getUserName())) {
      reviews.remove(i);
    } else {
      throw new IllegalStateException();
    }
  }

  /**
   * Gets specific album from reviews list.
   * 
   * @param i int position of the Review object.
   * @return Review at specified position i.
   */
  public Review getReview(int i) {
    return reviews.get(i);
  }

  /**
   * Gets list of reviews.
   * 
   * @return ArrayList of Album object's Reviews list.
   */
  public List<Review> getReviews() {
    return new ArrayList<>(reviews);
  }

  /**
   * Gets artist in Album object.
   * 
   * @return artist.
   */
  public String getArtist() {
    return artist;
  }

  /**
   * Gets name in Album object.
   * 
   * @return album name.
   */
  public String getName() {
    return name;
  }

  @Override
  public String toString() {
    return "Album: " + name + ", Artist: " + artist;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Album that = (Album) o;
    return Objects.equals(this.getArtist(), that.getArtist())
        && Objects.equals(this.getReviews(), that.getReviews())
        && Objects.equals(this.getName(), that.getName());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getReviews());
  }
}
