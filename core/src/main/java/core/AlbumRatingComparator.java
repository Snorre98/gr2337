package core;

import java.util.Comparator;

public class AlbumRatingComparator implements Comparator<Album> {
    @Override
    public int compare(Album album1, Album album2){
        if(album1.getRating() == album2.getRatin/()){
            return 0;
        }
        else if (album1.getPrice()>album2.getPrice()){
            return 1;
        }else{
            return -1;
        }
    }
}
