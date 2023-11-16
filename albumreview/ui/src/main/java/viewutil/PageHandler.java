package viewutil;

import domainlogic.Album;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * A class to open new javafx scenes and keep track of the controllers.
 */
public class PageHandler {

  AlbumController albumController;
  AlbumListController albumListController;

  /**
   * Load the AlbumList.fxml and the controller
   * 
   * @param username the username that is signed in with.
   * 
   */
  public void loadAlbumList(String username, Path saveFilePath) {
    //TODO: possibly not beeing used
    try {
      FXMLLoader loader;
      loader = new FXMLLoader(getClass().getResource("/fxml/AlbumList.fxml"));
      Parent root = (Parent) loader.load();
      Stage stage = new Stage();
      stage.setTitle("The Albums");
      Scene scene = new Scene(root);
      String css = this.getClass().getResource("/style.css").toExternalForm();
      scene.getStylesheets().add(css);
      stage.setScene(scene);
      stage.show();
      albumListController = loader.getController();
      albumListController.setUsername(username);
      albumListController.setPageHandler(this);
      albumListController.setSaveFilePath(saveFilePath);
      albumListController.initAlbumListView();
    } catch (Exception e) {
      e.printStackTrace();
      System.out.println("HER GIKK NOE FEIL I loadAlbumLIST" + e);
    }
  }

  /**
   * Loads the Album.fxml file and controller.
   * 
   * @param username the username that is used to login with.
   * 
   */
  public void loadAlbum(String username, Path saveFilePath, Album album) {
    try {
      FXMLLoader loader;
      loader = new FXMLLoader(getClass().getResource("/fxml/Album.fxml"));
      Parent root = (Parent) loader.load();
      Stage stage = new Stage();
      stage.setTitle("The Album");
      Scene scene = new Scene(root);
      String css = this.getClass().getResource("/style.css").toExternalForm();
      scene.getStylesheets().add(css);
      stage.setScene(scene);
      stage.show();
      albumController = loader.getController();
      albumController.setUsername(username);
      albumController.setAlbum(album);
      albumController.setSaveFilePath(saveFilePath);
      albumController.initReviewListView();
    } catch (Exception e) {
      System.out.println("HER GIKK NOE FEIL i loadAlbum" + e);
    }
  }
}
