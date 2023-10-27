package viewutil;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class PageHandler{

  AlbumListController albumListController;
  AlbumController albumController;
  
public void loadAlbumList(String username){
  albumListController.setUser(username);
    try {
      FXMLLoader loader;
      loader = new FXMLLoader(getClass().getResource("/fxml/AlbumList.fxml"));
      Parent root = (Parent) loader.load();
      Stage stage = new Stage();
      stage.setTitle("The Albums");
      stage.setScene(new Scene(root));
      stage.show();
    } catch (Exception e) {
      System.out.println("HER GIKK NOE FEIL, men knappen kjører funksjonen");
    }
}

public void loadAlbum(String username){
  albumController.setUser(username);
    try {
      FXMLLoader loader;
      loader = new FXMLLoader(getClass().getResource("/fxml/Album.fxml"));
      Parent root = (Parent) loader.load();
      Stage stage = new Stage();
      stage.setTitle("The Album");
      stage.setScene(new Scene(root));
      stage.show();
    } catch (Exception e) {
      System.out.println("HER GIKK NOE FEIL, men knappen kjører funksjonen");
    }
}



}
