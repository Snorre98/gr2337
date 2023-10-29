package viewutil;

import domainlogic.AlbumList;
import domainlogic.AlbumReview;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

public class AlbumController {

  private String username;
  private int selected;
  private String album;
  private String artist;

  private AlbumList albumList;

  @FXML
  private Button openReview;

  @FXML
  private Label albumLabel;

  @FXML
  private Label artistLabel;

  @FXML
  private ListView<AlbumReview> reviewList;

  @FXML
  private Label usernameLabel;

  public void setUsername(String username) {
    this.username = username;
    usernameLabel.setText(username);
  }

  public void setSelected(int selected) {
    this.selected = selected;
  }

  public void setAlbumAndArtist(AlbumListController albumListController){
    albumList = albumListController.getAlbumList();
    this.album = albumList.getAlbum(selected).getName();
    System.out.println(album + "album");
    this.artist = albumList.getAlbum(selected).getArtist();
    artistLabel.setText(album);
    albumLabel.setText(album);
    
  }
}
