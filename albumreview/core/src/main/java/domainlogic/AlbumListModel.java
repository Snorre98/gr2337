package domainlogic;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;

/**
 * Modell for app data.
 */
public class AlbumListModel implements Iterable<AbstractAlbumList> {
  private List<AbstractAlbumList> albumList = new ArrayList<>();

  private int indexOfAlbum(String albumListName) {
    for (int i = 0; i < albumList.size(); i++) {
      if (albumListName.equals(albumList.get(i).getAbstractAlbumListName())) {
        return i;
      }
    }
    return -1;
  }

  /**
   * Can only add album that does not exist.
   */
  public boolean hasAlbumList(String albumListName) {
    return indexOfAlbum(albumListName) < 0;
  }

  public boolean isValidAlbumListName(String albumListName) {
    return albumListName.strip().length() > 0;
  }

  /**
   * adds album to an abstract album list.
   */
  public void addAlbumList(AbstractAlbumList albumListToAdd) {
    if (!isValidAlbumListName(albumListToAdd.getAbstractAlbumListName())) {
      throw new IllegalArgumentException(
          albumListToAdd.getAbstractAlbumListName() + " is not a valid name");
    }

    if (!albumList.contains(albumListToAdd)) {
      albumList.add(albumListToAdd);
    }
  }

  public void removeAlbumList(AbstractAlbumList albumListToRemove) {
    albumList.remove(albumListToRemove);
  }

  public Iterator<AbstractAlbumList> iterator() {
    return albumList.iterator();
  }

  /**
   * gets abstract album from albumList.
   */
  public AbstractAlbumList getAlbumList(String albumListName) {
    for (var albumListToGet : albumList) {
      if (albumListName.equals(albumListToGet.getAbstractAlbumListName())) {
        return albumListToGet;
      }
    }
    return null;
  }



  // TODO: Sjekk om dette trengs:

  // public AbstractAlbumList putAlbumList(AbstractAlbumList albumList) {
  // int pos = indexOfAlbum(albumList.getAbstractAlbumListName());
  // }


  private static Collection<Album> albumProvider(AlbumList albumList,
      Function<AlbumList, Collection<Album>> albumProvider1,
      Function<AlbumList, Collection<Album>> albumProvider2) {

    // TODO: find out if this is needed
    Collection<Album> albums = new ArrayList<>(albumProvider1.apply(albumList));
    albums.addAll(albumProvider2.apply(albumList));
    return albums;
  }

}
