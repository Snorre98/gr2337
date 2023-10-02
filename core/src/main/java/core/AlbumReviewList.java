package core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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

  public void sortName(){
    Collections.sort(albumReviews, new AlbumNameComparator());
  }

  public void sortRating(){
    Collections.sort(albumReviews, new AlbumRatingComparator());
  }

}
