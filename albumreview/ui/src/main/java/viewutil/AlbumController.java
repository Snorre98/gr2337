package viewutil;

import domainlogic.Album;
import domainlogic.AlbumList;
import domainlogic.Review;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import statepersistence.NewLoadFromFile;
import statepersistence.NewWriteToFile;

/**
 * Controller for the Album.fxml and Album classes.
 */
public class AlbumController {

  private String username;
  private int selected;
  private String album;
  private String artist;

  private AlbumList albumList;

  private static final String saveFile = "IT1901gr2337/AlbumReviewApp/albumreviews.json";
  Path saveFilePath = Paths.get(System.getProperty("user.home"), saveFile);

  @FXML
  private Button openReview;

  @FXML
  private Label albumLabel;

  @FXML
  private Label artistLabel;

  @FXML
  private TextField rating;

  @FXML
  private ListView<Review> reviews;

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
  void newReview(ActionEvent event) {
    Review review = new Review(username, Integer.parseInt(rating.getText()));
    albumList.getAlbum(selected).addReview(review);
    reviews.getItems().setAll(albumList.getAlbum(selected).getReviews());
    rating.setText("");
    handleSave();
  }

  void handleSave() {
    NewWriteToFile.writeToFile(albumList, saveFilePath);
  }

  void initReviewListView() throws IOException {
    albumList = NewLoadFromFile.loadFromFile(saveFilePath, true);
    Album album = albumList.getAlbum(selected);
    reviews.getItems().setAll(album.getReviews());
  }

  /**
   * This sets the Album name and Artist at the top of the scene.
   * 
   * @param albumListController The albumlistController, need this to get the album info.
   */
  public void setAlbumAndArtist(AlbumListController albumListController) {
    albumList = albumListController.getAlbumList();
    this.album = albumList.getAlbum(selected).getName();
    System.out.println(album + "album");
    this.artist = albumList.getAlbum(selected).getArtist();
    artistLabel.setText(artist);
    albumLabel.setText(album);
    
  }
}
