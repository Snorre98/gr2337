package domainlogic;

import java.util.ArrayList;
import java.util.List;

/**
 * Core Album object class.
 */
public class Album {

  private List<Review> albumReviews;
  private String artist;
  private String name;

  /**
   * Constructor for Album object class.
   * 
   * @param artist artist connected to the Album.
   * @param name name connected to the Album.
   */
  public Album(String artist, String name) {
    albumReviews = new ArrayList<>();
    this.artist = artist;
    this.name = name;
  }

  /**
   * adds Review object to albumReviews list.
   * 
   * @param review Review that gets added to list.
   */
  public void addAlbumReview(Review review) {
    albumReviews.add(review);
  }

  /**
   * Removes specific album from albumReviews.
   * 
   * @param i int of Album's position.
   */
  public void removeAlbumReview(int i) {
    albumReviews.remove(i);
  }

  public Review getAlbumReview(int i) {
    return albumReviews.get(i);
  }

  public List<Review> getAlbumReviews() {
    return new ArrayList<>(albumReviews);
  }
}
