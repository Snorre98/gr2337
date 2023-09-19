package core;

import java.util.Comparator;
//TODO create Album object


public class AlbumNameComparator  implements Comparator<Album> {
    /**
     * helper comparator methode for comparing
     * */
    @Override
    public int compare(Album arg0, Album arg1){
        return arg0.getName().compareTo(arg1.getName());
    }

}