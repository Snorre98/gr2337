package domainlogic;

import java.util.*;

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
   * gets specific album object from album list.
   *
   * @param album to be gotten from list.
   * {@return album from list}.
   * */
  public Album getAlbum(Album album) {
    System.out.println(album);
    for (Album al : albums) {
      System.out.println(al);
      if (al.getName().equals(album.getName()) && al.getArtist().equals(album.getArtist())) {
        return album;
      }
    }
    throw new IllegalStateException("This album doesnt exist in list: " + album);
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
  public Album getAlbumByIndex(int i) {
    return albums.get(i);
  }

  /**
   * gets Album by unique id.
   * @param id unique
   * @return album object
   * */
  public Album getAlbumById(UUID id) {
    for (Album album : albums) {
      if (album.getId() == id) {
        return album;
      }
    }
    throw new IllegalArgumentException("No album with this ID");
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
    Collections.sort(albums, new AlbumComperator());
  }

  public void sortArtist() {
    Collections.sort(albums, new ArtistComperator());
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AlbumList that = (AlbumList) o;
    return Objects.equals(this.getAlbums(), that.getAlbums());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getAlbums());
  }
}
