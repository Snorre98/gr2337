package viewutil;

import java.nio.file.Path;
import java.nio.file.Paths;
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
  private String saveFile = "IT1901gr2337/AlbumReviewApp/albumreviews.json";
  Path saveFilePath = Paths.get(System.getProperty("user.home"), saveFile);

  public void setSaveFilePath(Path saveFile) {
    this.saveFilePath = saveFile;
  }

  /**
   * Load the AlbumList.fxml and the controller
   * 
   * @param username the username that is signed in with.
   * 
   */
  public void loadAlbumList(String username, Path saveFilePath) {
    try {
      FXMLLoader loader;
      loader = new FXMLLoader(getClass().getResource("/fxml/AlbumList.fxml"));
      Parent root = (Parent) loader.load();
      Stage stage = new Stage();
      stage.setTitle("The Albums");
      stage.setScene(new Scene(root));
      stage.show();
      albumListController = loader.getController();
      albumListController.setUsername(username);
      albumListController.setPageHandler(this);
      albumListController.setSaveFilePath(saveFilePath);
      albumListController.initAlbumListView();
    } catch (Exception e) {
      e.printStackTrace();
      System.out.println("HER GIKK NOE FEIL, men knappen kjører funksjonen");
    }
  }

  /**
   * Loads the Album.fxml file and controller.
   * 
   * @param username the username that is used to login with.
   * @param selected where in the AlbumList the selected album is
   * 
   */

  public void loadAlbum(String username, int selected, Path saveFilePath) {
    try {
      FXMLLoader loader;
      loader = new FXMLLoader(getClass().getResource("/fxml/Album.fxml"));
      Parent root = (Parent) loader.load();
      Stage stage = new Stage();
      stage.setTitle("The Album");
      stage.setScene(new Scene(root));
      stage.show();
      albumController = loader.getController();
      albumController.setUsername(username);
      albumController.setSelected(selected);
      albumController.setAlbumAndArtist(albumListController);
      albumController.setSaveFilePath(saveFilePath);
      albumController.initReviewListView();
    } catch (Exception e) {
      System.out.println("HER GIKK NOE FEIL, men knappen kjører funksjonen");
    }
  }
}
