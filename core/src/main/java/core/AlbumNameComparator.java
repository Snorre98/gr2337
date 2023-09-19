package core;

import java.util.Comparator;
//TODO create Album object


public class AlbumNameComparator  implements Comparator<AlbumReview> {
    /**
     * helper comparator methode for comparing
     * */
    @Override
    public int compare(AlbumReview arg0, AlbumReview arg1){
        return arg0.getName().compareTo(arg1.getName());
    }

}