package domainlogic;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class AlbumListModel implements Iterable<Album> {
  private List<Album> albums;

  public AlbumListModel() {
    this.albums = new ArrayList<>();
  }

  /**
   * Adds an Album object to the albums list if it doesn't exist already.
   *
   * @param album Album that gets added to the list.
   * @throws IllegalArgumentException if the album already exists in the list
   */
  public void addAlbum(Album album) {
    if (!isValidAlbum(album)) {
      throw new IllegalArgumentException("Album with the same name and artist already exists.");
    }
    albums.add(album);
  }

  /**
   * Removes a specific album from the albums list.
   *
   * @param album Album to be removed.
   * @return true if the album was removed, false otherwise.
   */
  public boolean removeAlbum(Album album) {
    return albums.remove(album);
  }

  /**
   * Gets a specific Album object from the albums list by index.
   *
   * @param index int position of the Album object.
   * @return Album at specified index.
   */
  public Album getAlbum(int index) {
    return albums.get(index);
  }

  /**
   * Checks if an album can be added to the list (is valid).
   *
   * @param album Album to check.
   * @return true if album is valid, false otherwise.
   */
  private boolean isValidAlbum(Album album) {
    return albums.stream()
        .noneMatch(a -> a.getArtist().equals(album.getArtist()) && a.getName().equals(album.getName()));
  }

  /**
   * Sorts albums by album name.
   */
  public void sortAlbumByName() {
    albums.sort(new AlbumComperator());
  }

  /**
   * Sorts albums by artist name.
   */
  public void sortAlbumByArtist() {
    albums.sort(new ArtistComperator());
  }

  /**
   * Gets list of albums.
   *
   * @return Unmodifiable list of Albums.
   */
  public List<Album> getAlbums() {
    return Collections.unmodifiableList(albums);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AlbumListModel that = (AlbumListModel) o;
    return Objects.equals(albums, that.albums);
  }

  @Override
  public int hashCode() {
    return Objects.hash(albums);
  }

  @Override
  public Iterator<Album> iterator() {
    return albums.iterator();
  }

  /**
   * Finds albums by a given predicate.
   *
   * @param predicate A predicate to apply to each album.
   * @return A list of albums that match the predicate.
   */
  public List<Album> findAlbums(Predicate<Album> predicate) {
    return albums.stream().filter(predicate).collect(Collectors.toList());
  }

  @Override
  public String toString() {
    return "AlbumList{" + "albums=" + albums + '}';
  }
}

