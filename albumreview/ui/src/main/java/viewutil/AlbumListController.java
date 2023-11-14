package viewutil;

import domainlogic.Album;
import domainlogic.AlbumList;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
 * Controller for AlbumList.
 */
public class AlbumListController implements Initializable {

  @FXML
  private ListView<Album> albumListView;

  @FXML
  private Button openAlbum;

  @FXML
  private Label username;

  @FXML
  private TextField albumInput;

  @FXML
  private TextField artistInput;

  Album album;

  AlbumList albumList;

  PageHandler pageHandler;

  private int selected;

  private String realusername;

  private String saveFile = "IT1901gr2337/AlbumReviewApp/albumreviews.json";

  Path saveFilePath = Paths.get(System.getProperty("user.home"), saveFile);

  public void setSaveFilePath(Path saveFile) {
    this.saveFilePath = saveFile;
  }

  public void setUsername(String username) {
    this.realusername = username;
    this.username.setText(realusername);
  }

  void setPageHandler(PageHandler pageHandler) {
    this.pageHandler = pageHandler;
  }

  public AlbumList getAlbumList() {
    return albumList;
  }

  void initAlbumListView() throws IOException {
    albumList = LoadFromFile.loadFromFile(saveFilePath, true);
    // System.out.println(albumList);
    ObservableList<Album> observableAlbums;
    observableAlbums = FXCollections.observableArrayList(albumList.getAlbums());
    albumListView.getItems().setAll(observableAlbums);
  }

  @FXML
  void openAlbum(ActionEvent event) {

    pageHandler.loadAlbum(realusername, selected, saveFilePath);
    System.out.println(selected);
  }

  @FXML
  void sortAlbum(ActionEvent event) {
    albumList.sortAlbum();
    albumListView.getItems().setAll(albumList.getAlbums());
    handleSave();
  }

  @FXML
  void sortArtist(ActionEvent event) {
    albumList.sortArtist();
    albumListView.getItems().setAll(albumList.getAlbums());
    handleSave();
  }

  @FXML
  void newAlbum(ActionEvent event) {
    album = new Album(artistInput.getText(), albumInput.getText());
    try {
      albumList.addAlbum(album);
    } catch (IllegalStateException e) {
      Alert alert = new Alert(Alert.AlertType.INFORMATION);
      alert.setTitle("Warning");
      alert.setContentText(e.getMessage());
      alert.showAndWait();
    }

    albumListView.getItems().setAll(albumList.getAlbums());
    albumInput.setText("");
    artistInput.setText("");
    handleSave();
  }

  /**
   * Writes albumList to file.
   */

  void handleSave() {
    WriteToFile.writeToFile(albumList, saveFilePath);
  }


  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    // System.out.println("Jacob er rar");
    // try {
    // initAlbumListView();
    // } catch (IOException e) {
    // // TODO Auto-generated catch block
    // e.printStackTrace();
    // }
    albumListView.setOnMouseClicked(new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent mouseEvent) {
        selected = albumListView.getSelectionModel().getSelectedIndex();
        System.out.println(selected);
      }
    });
  }
}
