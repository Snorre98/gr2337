package restserver;

import domainlogic.Album;
import domainlogic.AlbumList;
import domainlogic.Review;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Service;
import statepersistence.LoadFromFile;
import statepersistence.WriteToFile;

/**
 * Services related to album.
 * */
@Service
public class AlbumService {

  //TODO: find out if loading from file to make stateless is "safe"/ok
  //private Album album;

  /**
   * empty constructor for initialization.
   * */
  AlbumService() {}
  //TODO: eventhandeling in ui controller
  //TODO: Calling the REST API: The method in the JavaFX controller that handles loading the album's details
  //  would then make a call to the REST API, passing the album's ID.
  //TODO: Displaying Album Details: Once the JavaFX application receives the album details from the REST API,
  //  it should update the UI to display these details.


  //TODO: pass saveFile from frontend
  private static final String saveFile = "IT1901gr2337/AlbumReviewApp/albumreviews.json";
  Path saveFilePath = Paths.get(System.getProperty("user.home"), saveFile);

  public AlbumList loadAlbumListObject() throws IOException {
    //TODO: pass albumID from albumController in ui, "frontend".
    AlbumList albumList = LoadFromFile.loadFromFile(saveFilePath, true);
    return albumList;
  }
  //TODO: add better error handeling and logging
  /**
   * Getts album from JSON.
   * */
  public Album loadAlbumObject(UUID albumId) throws IOException {
    //TODO: pass albumID from albumController in ui, "frontend".
    //AlbumList albumList = LoadFromFile.loadFromFile(saveFilePath, true);
    return loadAlbumListObject().getAlbumById(albumId);
  }

  /**
   * Service for getting artist on album.
   * */
  public String getArtist(UUID albumId) {
    try {
      Album album = loadAlbumObject(albumId);
      return album.getArtist();
      //200_OK
    } catch (Exception e) {
      //BAD_REQUEST
      throw new UnsupportedOperationException(e + "could not get artist");
    }
  }

  /**
   * Service for getting name of album.
   * {@return album name}
   * */
  public String getName(UUID albumId) {
    try {
      Album album = loadAlbumObject(albumId);
      return album.getName();
      //200_OK
    } catch (Exception e) {
      throw new UnsupportedOperationException(e + "could not get names");
      //BAD_REQUEST
    }
  }

  /* #### review related services managed by album #### */
  /**
   * adds review to album.
   * @param username adding review
   * @param rating of album
   * {@return http response}
   * */
  public String addReview(UUID albumId, String username, int rating) {
    //TODO: void methode, for all "returning" string???
    try {
      AlbumList albumList = loadAlbumListObject();
      Album album = loadAlbumObject(albumId);
      Review review = new Review(username, rating);
      album.addReview(review);
      WriteToFile.writeToFile(albumList, saveFilePath);
      return "200_OK";
    } catch (Exception e) {
      throw new UnsupportedOperationException(e + "could not add album");
      //BAD_REQUEST
    }
  }

  /**
   * Service for removing review from album.
   * @param index of review to be removed
   * @param  username of user removing review
   * */
  public String removeReview(UUID albumId, int index, String username) {
    //TODO: add review id system?
    try {
      AlbumList albumList = loadAlbumListObject();
      Album album = loadAlbumObject(albumId);
      album.removeReview(index, username);
      WriteToFile.writeToFile(albumList, saveFilePath);
      return "200_OK";
    } catch (Exception e) {
      throw new UnsupportedOperationException(e + "could not remove review");
      //BAD_REQUEST
    }
  }

  /**
   * Service for getting review by index.
   * @param index of review to be gotten
   * */
  public Review getAlbumReview(UUID albumId, int index) {
    //TODO: implement review id?
    try {
      Album album = loadAlbumObject(albumId);
      return album.getReview(index);
      //return "200_OK";
    } catch (Exception e) {
      throw new UnsupportedOperationException(e + "could not get review");
      //BAD_REQUEST
    }
  }

  /**
   * Service for getting review list on album.
   * */
  public List<Review> getReviewList(UUID albumId) {
    //TODO: return string????
    try {
      Album album = loadAlbumObject(albumId);
      return album.getReviews();
      //return "200_OK";
    } catch (Exception e) {
      throw new UnsupportedOperationException(e + "could not get list of reviews");
      //BAD_REQUEST
    }
  }
}
