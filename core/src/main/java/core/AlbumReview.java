package core;

public class AlbumReview {

  String name;
  int rating;

  public AlbumReview(String name, int rating) {
    this.name = name;
    this.rating = rating;
  }

  public String getName(){
    return this.name;
  }

  public int getRating(){
    return this.rating;
  }

  @Override
  public String toString() {
    return name + "%%% " + rating;
  }

  

  

}
