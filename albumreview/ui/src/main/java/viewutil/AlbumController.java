package viewutil;

import domainlogic.AlbumReview;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

public class AlbumController {

  private String username;
  private int selected;

  @FXML
  private Button openReview;

  @FXML
  private Label rating;

  @FXML
  private ListView<AlbumReview> reviewList;

  @FXML
  private Label usernameLabel;

  public void setUsername(String username) {
    this.username = username;
  }

  public void setSelected(int selected){
    this.selected = selected;
  }
}
