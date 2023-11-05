package domainlogic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Manages list of Album objects.
 */
public class AlbumList {

  List<Album> albums;

  public AlbumList() {
    this.albums = new ArrayList<>();
  }

  /**
   * adds Album object to albums list.
   * 
   * @param album Album that gets added to list.
   */
  public void addAlbum(Album album) {
    String albumName = album.getName();
    String artistName = album.getArtist();
    for (Album a : albums) {
      if (a.getArtist().equals(artistName) && a.getName().equals(albumName)) {
        throw new IllegalStateException("You can't add an album that has already been added");
      }
    }
    albums.add(album);
  }

  /**
   * Removes specific album from albums list.
   * 
   * @param i int position of Album object.
   */
  public void removeAlbum(int i) {
    albums.remove(i);
  }

  /**
   * Gets specific Album objevt from albums list.
   * 
   * @param i int position of the Album object.
   * @return Album at specified position i.
   */
  public Album getAlbum(int i) {
    return albums.get(i);
  }

  /**
   * Gets list of Albums.
   * 
   * @return ArrayList of Albums.
   */
  public List<Album> getAlbums() {
    return new ArrayList<>(albums);
  }

  public void sortAlbum() {
    Collections.sort(albums, new NewAlbumComperator());
  }

  public void sortArtist() {
    Collections.sort(albums, new NewArtistComperator());
  }


}
