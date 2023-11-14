package restserver;

import domainlogic.Album;
import domainlogic.AlbumList;
import org.springframework.stereotype.Service;
import statepersistence.LoadFromFile;
import statepersistence.WriteToFile;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * AlbumList API service.
 * */
@Service
public class AlbumListService {

  //TODO: find out if loading from file to make stateless is "safe"/ok
  //private AlbumList albumList;

  private static final String saveFile = "IT1901gr2337/AlbumReviewApp/albumreviews.json";
  Path saveFilePath = Paths.get(System.getProperty("user.home"), saveFile);

  /**
   * empty constructor. Used for initialization.
   * */
  public AlbumListService() {}

  public AlbumList loadAlbumList() throws IOException {
    return LoadFromFile.loadFromFile(saveFilePath, true);
  }

  /**
   * Service for adding album.
   * @param artist artist name
   * @param name album name
   * @return HTTP response status
   * */
  public String addAlbum(String artist, String name) {
    try {
      AlbumList albumList = loadAlbumList();
      Album album = new Album(artist, name);
      albumList.addAlbum(album);
      WriteToFile.writeToFile(albumList, saveFilePath);
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
      AlbumList albumList = loadAlbumList();
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
      AlbumList albumList = loadAlbumList();
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
      AlbumList albumList = loadAlbumList();
      return albumList.getAlbumByIndex(index);
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
      AlbumList albumList = loadAlbumList();
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
      AlbumList albumList = loadAlbumList();
      albumList.sortArtist();
      return "200_OK";
    } catch (Exception e) {
      throw new UnsupportedOperationException(e + "could not sort album by artist");
    }
  }
}
