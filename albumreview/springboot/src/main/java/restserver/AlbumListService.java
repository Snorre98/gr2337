package restserver;

import domainlogic.Album;
import domainlogic.AlbumList;
import org.springframework.stereotype.Service;

/**
 * AlbumList API service.
 * */
@Service
public class AlbumListService {

  //TODO: statefull bad
  private AlbumList albumList;

  /**
   * empty constructor. Used for initialization.
   * */
  public AlbumListService() {}

  /**
   * Service for adding album.
   * @param artist artist name
   * @param name album name
   * @return HTTP response status
   * */
  public String addAlbum(String artist, String name) {
    try {
      Album album = new Album(artist, name);
      albumList.addAlbum(album);
      return "200_OK";
    } catch (Exception e) {
      throw new UnsupportedOperationException(e + "could not add album");
      //return "BAD_REQUEST";
    }
  }

  /**
   * Service for removing album at index.
   * @param index album index
   * */
  public String removeAlbum(int index) {
    try {
      albumList.removeAlbum(index);
      return "200_OK";
    } catch (Exception e) {
      throw new UnsupportedOperationException(e + "could not remove album");
      //return "BAD_REQUEST";
    }
  }

  /**
   * Service for getting AlbumList.
   * */
  public AlbumList getAlbumList() {
    try {
      return albumList;
      //200_OK
    } catch (Exception e) {
      throw new UnsupportedOperationException(e + "could not get list of albums");
      //BAD_REQUEST
    }
  }

  /**
   * Service for getting album at index.
   * @param index index of album
   * */
  public Album getAlbum(int index) {
    try {
      return albumList.getAlbum(index);
      //200_OK
    } catch (Exception e) {
      throw new UnsupportedOperationException(e + "could not get album at {index}");
      //BAD_REQUEST
    }
  }

  /**
   * Service for sorting albums by album name.
   * */
  public String sortAlbumsByName() {
    try {
      albumList.sortAlbum();
      return "200_OK";
    } catch (Exception e) {
      throw new UnsupportedOperationException(e + "could not sort album by name");
    }
  }

  /**
   * Service for sorting albums by artist.
   * */
  public String sortAlbumsByArtist() {
    try {
      albumList.sortArtist();
      return "200_OK";
    } catch (Exception e) {
      throw new UnsupportedOperationException(e + "could not sort album by artist");
    }
  }
}
