package viewutil;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class PageHandler{

  AlbumController albumController;
  AlbumListController albumListController;
  
  public void loadAlbumList(String username){
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
    } catch (Exception e) {
      System.out.println("HER GIKK NOE FEIL, men knappen kjører funksjonen");
    }
}

public void loadAlbum(String username, int selected){
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
    } catch (Exception e) {
      System.out.println("HER GIKK NOE FEIL, men knappen kjører funksjonen");
    }
}



}
