package viewutil;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import domainlogic.Album;
import domainlogic.AlbumList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import statepersistence.serializer.AlbumReviewModule;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


public class RestModel {
  private final HttpClient httpClient = HttpClient.newHttpClient();
  private final String backendBaseUrl = "http://localhost:8080";

  public String getAlbumList() throws IOException, InterruptedException {
    try {
      HttpRequest request = HttpRequest.newBuilder()
          .uri(URI.create(backendBaseUrl + "/api/albumlist/getAlbumList"))
          .GET()
          .build();
      final HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
      return  response.body(); //Deserialized in albumlistcontroller
    } catch (Exception e) {
      throw new IllegalStateException("COULD NOT RETRIVE ALBUMLIST");
    }
  }


//  /addAlbum/{artist}/{name}
  public String addAlbum(String artistInput, String albumInput) throws IOException, InterruptedException {
    String endpoint = "/api/albumlist/addAlbum/";
    String postBody = "";
    System.out.println(backendBaseUrl + endpoint + artistInput + "/" + albumInput);
    try {
      HttpRequest request = HttpRequest.newBuilder()
          .uri(URI.create(backendBaseUrl + endpoint + artistInput + "/" + albumInput))
          .POST(HttpRequest.BodyPublishers.ofString(postBody))
          .build();

      final HttpResponse<String> response =
          httpClient.send(
              request,
              HttpResponse.BodyHandlers.ofString()
          );
      return response.body();
    } catch (Exception e) {
      throw new InterruptedException("Could not add Album: " + e);
    }
  }

  public String sortAlbum() throws IOException, InterruptedException {
    String endpoint = "/api/albumlist/sortAlbumsByName";
    try {
      HttpRequest request = HttpRequest.newBuilder()
          .uri(URI.create(backendBaseUrl + endpoint))
          .GET()
          .build();
      final HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
      return  response.body(); //Deserialized in albumlistcontroller
    } catch (Exception e) {
      throw new IllegalStateException("COULD NOT RETRIVE ALBUMLIST");
    }
  }

  public  String sortArtist() throws IOException, InterruptedException {
    String endpoint = "/api/albumlist/sortAlbumsByArtist";
    try {
      HttpRequest request = HttpRequest.newBuilder()
          .uri(URI.create(backendBaseUrl + endpoint))
          .GET()
          .build();
      final HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
      return  response.body(); //Deserialized in albumlistcontroller
    } catch (Exception e) {
      throw new IllegalStateException("COULD NOT RETRIVE ALBUMLIST");
    }
  }

  /**
   * updates reviewList with JSON through API call.
   *
   * @param httpResponse is response for getAlbumList()
   */
  public void updateReviewList(HttpResponse<String> httpResponse) throws IOException, InterruptedException {

    //TODO: remove httpResponse parameter
    /*
    AlbumList albumList = getAlbumListObject();
    if (listResponse.statusCode() == 200) {
      updateAlbumListView(listResponse);
    } else {
      System.out.println("Failed to fetch updated album list");
    }*/
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


}
