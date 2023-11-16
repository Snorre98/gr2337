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
import java.util.List;
import java.util.ResourceBundle;
import java.util.UUID;
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
  private final HttpClient httpClient = HttpClient.newHttpClient();
  private final String backendBaseUrl = "http://localhost:8080";



  /*
  /addAlbum/{artist}/{name}
   */
  public void setSaveFilePath(Path saveFile) {
    this.saveFilePath = saveFile;
  }

  public void setUsername(String username) {
    //TODO: do this with API
    this.realusername = username;
    this.username.setText(realusername);
  }

  void setPageHandler(PageHandler pageHandler) {
    this.pageHandler = pageHandler;
  }

  /*
  void initAlbumListView() throws IOException, InterruptedException {
    //TODO: request loadAlbumList here
    albumList = LoadFromFile.loadFromFile(saveFilePath, true);
    ObservableList<Album> observableAlbums;
    observableAlbums = FXCollections.observableArrayList(albumList.getAlbums());
    albumListView.getItems().setAll(observableAlbums);
  }*/

  public void initAlbumListView() throws IOException, InterruptedException {
    getAlbumList();
  }



  @FXML
  void openAlbum(ActionEvent event) {
    //TODO: open album with data through API
    pageHandler.loadAlbum(realusername, selectedAlbumId, saveFilePath, selectedAlbum);
    // System.out.println(selectedAlbumId);
  }


  /*
  @FXML
  void sortAlbum(ActionEvent event) {
    albumList.sortAlbum();
    albumListView.getItems().setAll(albumList.getAlbums());
    handleSave();
  }*/

  public void sortAlbum(ActionEvent event) throws IOException, InterruptedException {
    HttpRequest listRequest = HttpRequest.newBuilder()
        .uri(URI.create(backendBaseUrl + "/api/albumlist/sortAlbumsByName"))
        .GET()
        .build();
    HttpResponse<String> listResponse = httpClient.send(listRequest, HttpResponse.BodyHandlers.ofString());
    if (listResponse.statusCode() == 200) {
      System.out.println("Jacob er rar");
      updateListView(listResponse);
    } else {
      System.out.println("Failed to fetch updated album list");
    }
  }

  /*
  @FXML
  void sortArtist(ActionEvent event) {
    albumList.sortArtist();
    albumListView.getItems().setAll(albumList.getAlbums());
    handleSave();
  }*/
  @FXML
  public  void sortArtist(ActionEvent event) throws IOException, InterruptedException {
    HttpRequest listRequest = HttpRequest.newBuilder()
        .uri(URI.create(backendBaseUrl + "/api/albumlist/sortAlbumsByArtist"))
        .GET()
        .build();
    HttpResponse<String> listResponse = httpClient.send(listRequest, HttpResponse.BodyHandlers.ofString());
    if (listResponse.statusCode() == 200) {
      System.out.println("Jacob er rar");
      updateListView(listResponse);
    } else {
      System.out.println("Failed to fetch updated album list");
    }
  }

/*  @FXML
  void newAlbum(ActionEvent event) {
    Album album = new Album(artistInput.getText(), albumInput.getText());
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
  */


  /**
   * Returns a copy of the AlbumList.
   *
   * @return AlbumList copy
   */

  /*
  public AlbumList getAlbumList() {
    AlbumList copy = new AlbumList();
    for (Album album : albumList.getAlbums()) {
      copy.addAlbum(album); // Create a copy of the album and add it to the new AlbumList
    }
    return copy;
  }*/

  public void updateListView(HttpResponse<String> listResponse) throws JsonProcessingException {
    String updatedAlbumList = listResponse.body();
    ObjectMapper ob = new ObjectMapper();
    ob.registerModule(new AlbumReviewModule());
    albumList = ob.readValue(updatedAlbumList, AlbumList.class);
    ObservableList<Album> observableAlbums = FXCollections.observableArrayList(albumList.getAlbums());
    albumListView.getItems().setAll(observableAlbums);
  }

  public void getAlbumList() throws IOException, InterruptedException {
    HttpRequest listRequest = HttpRequest.newBuilder()
        .uri(URI.create(backendBaseUrl + "/api/albumlist/getAlbumList"))
        .GET()
        .build();
    HttpResponse<String> listResponse = httpClient.send(listRequest, HttpResponse.BodyHandlers.ofString());

    if (listResponse.statusCode() == 200) {
      updateListView(listResponse);
    } else {
      System.out.println("Failed to fetch updated album list");
    }
  }

  public AlbumList getAlbumListObject () throws IOException, InterruptedException {
    HttpRequest listRequest = HttpRequest.newBuilder()
        .uri(URI.create(backendBaseUrl + "/api/albumlist/getAlbumList"))
        .GET()
        .build();
    HttpResponse<String> response = httpClient.send(listRequest, HttpResponse.BodyHandlers.ofString());
    if (response.statusCode() == 200) {
      System.out.println("Great success!");
      String albumListResponse = response.body();
      ObjectMapper ob = new ObjectMapper();
      ob.registerModule(new AlbumReviewModule());
      albumList = ob.readValue(albumListResponse, AlbumList.class);
      return albumList;
    } else {
      throw new IllegalStateException("Worng");
    }
  }



  @FXML
  public void newAlbum(ActionEvent event) throws IOException, InterruptedException {
    //Album album = new Album(artistInput.getText(), albumInput.getText());
    String endpoint = "/api/albumlist/addAlbum/";
    HttpRequest request = HttpRequest.newBuilder().uri(URI.create(backendBaseUrl + endpoint + artistInput.getText() + "/" + albumInput.getText())).POST(HttpRequest.BodyPublishers.noBody()).build();
    HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
    if (response.statusCode() == 200) {
      getAlbumList();
      System.out.println("Great success!");
    } else {
      System.out.println("BAD, no success!");
      }
  }

  /**
   * Writes albumList to file.
   */
  void handleSave() {
    WriteToFile.writeToFile(albumList, saveFilePath);
  }

 // public void fetchAlbumDetails(UUU)


  /**
   * sets album selected to be used in albumController.
   * 
   * @param selectedAlbum is album selected by user
   */

  public void setSelectedAlbum(Album selectedAlbum) {
    this.selectedAlbum = selectedAlbum;
    // TODO: find better solution to this??
    if (selectedAlbum != null) {
      selectedAlbumId = selectedAlbum.getId();
      albumList.getAlbumById(selectedAlbumId);
    }
  }


  /*
public void fetchSelectedAlbum(UUID albumId) throws IOException, InterruptedException {
  HttpRequest request = HttpRequest.newBuilder().uri(URI.create(backendBaseUrl + "/api/albumlist/getAlbum/" + albumId)).GET().build();
  HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
  if (response.statusCode() == 200) {
    String updatedAlbumList = response.body();
    ObjectMapper ob = new ObjectMapper();
    ob.registerModule(new AlbumReviewModule());
    Album album = ob.readValue(updatedAlbumList, Album.class);
    System.out.println("");
    this.selectedAlbum = album;
  } else {
    System.out.println("Failed to fetch album");

  }
}*/

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    albumListView.setOnMouseClicked(new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent mouseEvent) {
        //setSelectedAlbum(albumListView.getSelectionModel().getSelectedItem());

        try {
          Album selectedAlbum = albumListView.getSelectionModel().getSelectedItem();
          List<Album> albumList = getAlbumListObject().getAlbums();
          for (Album album : albumList) {
            if (album.getArtist().equals(selectedAlbum.getArtist()) && album.getName().equals(selectedAlbum.getName())) {
              setSelectedAlbum(album);
            }
          }
        } catch (IOException | InterruptedException e) {
          throw new RuntimeException(e);
        }
        //albumList = albumList.getAlbums();

        System.out.println("Was clicked!!!!");
      }
    });
  }
}
