package domainlogic;

import java.io.Serializable;
import java.util.Comparator;

/**
 * Compares album by name. Used for sorting.
 */
public class AlbumComperator implements Comparator<Album>, Serializable {
  @Override
  public int compare(Album arg0, Album arg1) {
    return arg0.getName().compareTo(arg1.getName());
  }
}
