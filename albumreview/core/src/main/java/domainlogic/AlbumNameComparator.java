package domainlogic;

import java.util.Comparator;

/**
 * Compares album by name. Used for sorting.
 */
public class AlbumNameComparator implements Comparator<AlbumReview> {
  @Override
  public int compare(AlbumReview arg0, AlbumReview arg1) {
    return arg0.getName().compareTo(arg1.getName());
  }
}
