package core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AlbumReviewList {

  private List<AlbumReview> albumReviews;

  public AlbumReviewList() {
    albumReviews = new ArrayList<>();
  }

  public void addAlbumReview(String name, int rating) {
    AlbumReview ar = new AlbumReview(name, rating);
    albumReviews.add(ar);
  }

  public void removeAlbumReview(int i) {
    albumReviews.remove(i);
  }

  public String getAlbumReview(int i) {
    return albumReviews.get(i).toString();
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
