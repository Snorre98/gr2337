package core;

import java.util.ArrayList;
import java.util.List;

public class AlbumReviewList {

  List<AlbumReview> albumReviews;

  public AlbumReviewList(List<AlbumReview> albumReviews) {
    albumReviews = new ArrayList<>();
  }
  
  public void addAlbumReview(String name, int rating){
    AlbumReview ar = new AlbumReview(name, rating);
    albumReviews.add(ar);
  }

  public void removeAlbumReview(int i){
    albumReviews.remove(i);
  }

  
  
}
