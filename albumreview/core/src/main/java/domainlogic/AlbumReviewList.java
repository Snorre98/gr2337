package domainlogic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Manages a list of AlbumReviews.
 */
public class AlbumReviewList {

  private List<AlbumReview> albumReviews;

  public AlbumReviewList() {
    albumReviews = new ArrayList<>();
  }

  public void addAlbumReview(AlbumReview album) {
    albumReviews.add(album);
  }

  public void removeAlbumReview(int i) {
    albumReviews.remove(i);
  }

  public AlbumReview getAlbumReview(int i) {
    return albumReviews.get(i);
  }

  public List<AlbumReview> getAlbumReviews() {
    return new ArrayList<>(albumReviews);
  }

  public void sortName() {
    Collections.sort(albumReviews, new AlbumNameComparator());
  }

  public void sortRating() {
    Collections.sort(albumReviews, new AlbumRatingComparator());
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
    AlbumReviewList that = (AlbumReviewList) o;
    return Objects.equals(this.getAlbumReviews(), that.getAlbumReviews());
  }

  /**
   * Used to compare objects directly. If fields are added, this must be updated.
   */
  @Override
  public int hashCode() {
    return Objects.hash(this.getAlbumReviews());
  }
}
