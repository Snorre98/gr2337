package viewutil;

import domainlogic.AlbumList;
import domainlogic.AlbumReview;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

/**
 * Controller for the Album.fxml and Album classes.
 */
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

  @FXML
  void openReview(ActionEvent event){
    //Here we must make functions that update the labels
  }

  /**
   * This sets the Album name and Artist at the top of the scene.
   * 
   * @param albumListController The albumlistController, need this to get the album info.
   */
  public void setAlbumAndArtist(AlbumListController albumListController){
    albumList = albumListController.getAlbumList();
    this.album = albumList.getAlbum(selected).getName();
    System.out.println(album + "album");
    this.artist = albumList.getAlbum(selected).getArtist();
    artistLabel.setText(album);
    albumLabel.setText(album);
    
  }
}
