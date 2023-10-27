package viewutil;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import domainlogic.Album;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

public class AlbumListController implements Initializable{

  Album album;
  private String realusername;

  public void setUsername(String username){
    this.realusername = username;
  }

  void initAlbumListView(){
    System.out.println("det funker");
  }
  
  @FXML
  private ListView<Album> albumListView;
    
  @FXML
  private Button openAlbum;

  @FXML
  private Label username;


  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    //System.out.println("Jacob er rar");
    initAlbumListView();
  }
}
