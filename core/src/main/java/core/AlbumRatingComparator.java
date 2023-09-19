package core;

import java.util.Comparator;

public class AlbumRatingComparator implements Comparator<AlbumReview> {
    @Override
    public int compare(AlbumReview album1, AlbumReview album2){
        if(album1.getRating() == album2.getRating()){
            return 0;
        }
        else if (album1.getRating()>album2.getRating()){
            return 1;
        }else{
            return -1;
        }
    }
}
