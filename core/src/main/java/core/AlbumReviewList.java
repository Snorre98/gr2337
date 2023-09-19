package core;

import java.util.ArrayList;
import java.util.List;

public class AlbumReviewList {

  List<AlbumReview> AlbumReviews;

  public AlbumReviewList(List<AlbumReview> albumReviews) {
    AlbumReviews = new ArrayList<>();
  }
  
  public void addAlbumReview(String name, int rating){
    AlbumReview ar = new AlbumReview(name, rating);
    AlbumReviews.add(ar);
  }
  
}
