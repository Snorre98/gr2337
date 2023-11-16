package viewutil;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import domainlogic.Album;
import domainlogic.AlbumList;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.UUID;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import statepersistence.WriteToFile;
import statepersistence.serializer.AlbumReviewModule;

import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

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

  private Album selectedAlbum;

  AlbumList albumList;

  PageHandler pageHandler;

  private UUID selectedAlbumId;

  private String realusername;

  private String saveFile = "IT1901gr2337/AlbumReviewApp/albumreviews.json";

  Path saveFilePath = Paths.get(System.getProperty("user.home"), saveFile);

  private RestModel restModel = new RestModel();

  public void setSaveFilePath(Path saveFile) {
    this.saveFilePath = saveFile;
  }

  public void setUsername(String username) {
    //TODO: do this with API??
    this.realusername = username;
    this.username.setText(realusername);
  }

  void setPageHandler(PageHandler pageHandler) {
    this.pageHandler = pageHandler;
  }


  void initAlbumListView() throws IOException, InterruptedException {
    //TODO: request loadAlbumList here
    updateAlbumListView(getAlbumList());
  }


  @FXML
  void openAlbum(ActionEvent event) {
    //TODO: open album with data through API
    //pageHandler.loadAlbum(realusername, selectedAlbumId, saveFilePath, selectedAlbum);
    pageHandler.loadAlbum(realusername, saveFilePath, selectedAlbum);
  }

  @FXML
  public void sortAlbum(ActionEvent event) {
    try {
      String sortAlbumRequest = restModel.sortAlbum();
      AlbumList sortedAlbumList = albumListObjectMapper(sortAlbumRequest);
      updateAlbumListView(sortedAlbumList);
    } catch (IOException | InterruptedException e) {
      throw new RuntimeException(e);
    }
  }

  @FXML
  public void sortArtist(ActionEvent event) {
    try {
      String sortArtistRequest = restModel.sortArtist();
      AlbumList sortedAlbumList = albumListObjectMapper(sortArtistRequest);
      updateAlbumListView(sortedAlbumList);
    } catch (IOException | InterruptedException e) {
      throw new RuntimeException(e);
    }
  }

 @FXML
  public void newAlbum(ActionEvent event) {
   try {
      Album album = new Album(artistInput.getText(), albumInput.getText());
      String addAlbumRequest = restModel.addAlbum(album.getName(), album.getArtist());
      updateAlbumListView(getAlbumList());
    } catch (IOException | InterruptedException e) {
      throw new RuntimeException(e);
   }
 }


 /**
  * helper function.
  * */
 public AlbumList albumListObjectMapper(String responseBody) throws JsonProcessingException {
    ObjectMapper ob = new ObjectMapper();
    ob.registerModule(new AlbumReviewModule());
    return ob.readValue(responseBody, AlbumList.class);
 }

  public void updateAlbumListView(AlbumList albumList) throws IOException, InterruptedException {
    ObservableList<Album> observableAlbums = FXCollections.observableArrayList(albumList.getAlbums());
    this.albumListView.getItems().setAll(observableAlbums);
  }

  public AlbumList getAlbumList() throws IOException, InterruptedException {
    String getAlbumListRequest = restModel.getAlbumList();
    return albumListObjectMapper(getAlbumListRequest);
 }


  /**
   * sets album selected to be used in albumController.
   * 
   * @param selectedAlbum is album selected by user
   */
  public void setSelectedAlbum(Album selectedAlbum) {
    this.selectedAlbum = selectedAlbum;
  }

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    albumListView.setOnMouseClicked(new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent mouseEvent) {
        try {
          Album selectedAlbum = albumListView.getSelectionModel().getSelectedItem();
          List<Album> albumList = getAlbumList().getAlbums();
          for (Album album : albumList) {
            if (album.getArtist().equals(selectedAlbum.getArtist()) && album.getName().equals(selectedAlbum.getName())) {
              setSelectedAlbum(album);
            }
          }
        } catch (IOException | InterruptedException e) {
          throw new RuntimeException(e);
        }
        System.out.println("Was clicked!!!!");
      }
    });
  }
 }

