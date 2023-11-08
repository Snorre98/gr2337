package viewutil;

import domainlogic.Album;
import domainlogic.AlbumList;
import domainlogic.Review;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import statepersistence.LoadFromFile;
import statepersistence.WriteToFile;

/**
 * Controller for the Album.fxml and Album classes.
 */
public class AlbumController implements Initializable {

  private String username;
  private int selected;
  private int selectedReview;
  private String album;
  private String artist;

  private AlbumList albumList;

  private static final String saveFile = "IT1901gr2337/AlbumReviewApp/albumreviews.json";
  Path saveFilePath = Paths.get(System.getProperty("user.home"), saveFile);

  @FXML
  private Button openReview;

  @FXML
  private Button removeReview;

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
    try {
      Review review = new Review(username, Integer.parseInt(rating.getText()));
      albumList.getAlbum(selected).addReview(review);
    } catch (IllegalArgumentException e) {
      Alert alert = new Alert(Alert.AlertType.INFORMATION);
      alert.setTitle("Warning");
      alert.setContentText(e.getMessage());
      alert.showAndWait();
    }

    reviews.getItems().setAll(albumList.getAlbum(selected).getReviews());
    rating.setText("");
    handleSave();
  }

  @FXML
  void removeReview(ActionEvent event) {
    try {
      albumList.getAlbum(selected).removeReview(selectedReview, username);
      reviews.getItems().setAll(albumList.getAlbum(selected).getReviews());
    } catch (IllegalStateException e) {
      Alert alert = new Alert(Alert.AlertType.INFORMATION);
      alert.setTitle("Warning");
      alert.setContentText("You can not remove a review you did not make");
      alert.showAndWait();
    }
  }

  void handleSave() {
    WriteToFile.writeToFile(albumList, saveFilePath);
  }

  void initReviewListView() throws IOException {
    albumList = LoadFromFile.loadFromFile(saveFilePath, true);
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

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    reviews.setOnMouseClicked(new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent mouseEvent) {
        selectedReview = reviews.getSelectionModel().getSelectedIndex();
        System.out.println(selected);
        System.out.println(selectedReview);

      }
    });

  }
}
