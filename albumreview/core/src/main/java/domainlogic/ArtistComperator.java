package domainlogic;

import java.io.Serializable;
import java.util.Comparator;

/**
 * Compares album by name. Used for sorting.
 */
public class ArtistComperator implements Comparator<Album>, Serializable {
  @Override
  public int compare(Album arg0, Album arg1) {
    return arg0.getArtist().compareTo(arg1.getArtist());
  }
}
