package viewutil;

import domainlogic.AlbumReview;
import domainlogic.AlbumReviewList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import statepersistence.LoadFromFile;
import statepersistence.WriteToFile;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ResourceBundle;

/**
 * App controller for all UI.
 * */
public class AlbumReviewAppController implements Initializable {


  @SuppressWarnings("checkstyle:MultipleVariableDeclarations")
  @FXML
  private TextField albumName, albumRating;
  //Checkstyle wants these to be defined on separate lines. I don't want that.
  @SuppressWarnings("checkstyle:MultipleVariableDeclarations")
  @FXML
  private Button sortByNameButton, sortByRatingButton, addButton, removeButton, openAlbum;


  @FXML
  private ListView<AlbumReview> albumListView;


  private AlbumReviewList albumList;

  private int selected;

  private static final String saveFile = "IT1901gr2337/AlbumReviewApp/albumreviews.json";

  Path saveFilePath = Paths.get(System.getProperty("user.home"), saveFile);

  /**
   * Initializes app.
   * */
  public void initAlbumListView() throws IOException {
    albumList = LoadFromFile.loadFromFile(saveFilePath, true);
    //System.out.println(albumList);
    ObservableList<AlbumReview> observableAlbums = FXCollections.observableArrayList(albumList.getAlbumReviews());
    albumListView.getItems().setAll(observableAlbums);
  }

  /**
   * adds album to list view in ui.
   */
  @FXML
  void addRating(ActionEvent event) {
    if (albumName.getText().isEmpty() || albumRating.getText().isEmpty()) {
      Alert alertInfo = new Alert(Alert.AlertType.INFORMATION);
      alertInfo.setTitle("Warning");
      alertInfo.setContentText("You must enter an album and a rating");
      alertInfo.showAndWait();
    } else {
      try {
        AlbumReview album;
        album = new AlbumReview(albumName.getText(), Integer.parseInt(albumRating.getText()));
        albumList.addAlbumReview(album);
        albumListView.getItems().setAll(albumList.getAlbumReviews());
        albumName.setText("");
        albumRating.setText("");
        handleSave();

      } catch (NumberFormatException e) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Warning");
        alert.setContentText("You must add a rating of the album");
        alert.showAndWait();
      }
    }
  }

  /**
   * remove album from ListView in ui.
   */
  @FXML
  void removeSelectedAlbum(ActionEvent event) {
    albumList.removeAlbumReview(selected);
    handleSave();
    albumListView.getItems().setAll(albumList.getAlbumReviews());
  }

  @FXML
  void openButton(ActionEvent event) {
    try {
      FXMLLoader loader;
      loader = new FXMLLoader(getClass().getResource("/fxml/OpenAlbumReviewApp.fxml"));
      Parent root = (Parent) loader.load();
      Stage stage = new Stage();
      stage.setTitle("The Album");
      stage.setScene(new Scene(root));
      stage.show();
    } catch (Exception e) {
      System.out.println("HER GIKK NOE FEIL, men knappen kjører funksjonen");
    }
  }

  /**
   * sorts album by name in ui.
   */
  @FXML
  void sortByName(ActionEvent event) {
    albumList.sortName();
    albumListView.getItems().setAll(albumList.getAlbumReviews());
  }

  /**
   * sorts album by rating in ui.
   */
  @FXML
  void sortByRating(ActionEvent event) {
    albumList.sortRating();
    albumListView.getItems().setAll(albumList.getAlbumReviews());
  }

  /**
   * Writes albumList to file.
   */

  void handleSave() {
    WriteToFile.writeToFile(albumList, saveFilePath);
  }

  /**
   * Initializes albumListView and handles mouseClick in albumListView.
   */
  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    try {
      //System.out.println("Jacob er rar");
      initAlbumListView();
    } catch (IOException e) {
      throw new RuntimeException(e);

    }
    albumListView.setOnMouseClicked(new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent mouseEvent) {
        selected = albumListView.getSelectionModel().getSelectedIndex();
      }
    });
  }
}
