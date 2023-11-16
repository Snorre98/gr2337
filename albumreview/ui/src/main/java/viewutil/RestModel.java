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
/*
  public String addReview(Album album, String username, int rating) throws InterruptedException {

    String endpoint = "/api/albumlist/album/addReview/";
    String postBody = "";
    System.out.println(backendBaseUrl + endpoint + album + username + "/" + rating);
    try {
      HttpRequest request = HttpRequest.newBuilder()
          .uri(URI.create(backendBaseUrl + endpoint + "/" + album + username + "/" + rating))
          .POST(HttpRequest.BodyPublishers.ofString(postBody))
          .build();
      final HttpResponse<String> response =
          httpClient.send(
              request,
              HttpResponse.BodyHandlers.ofString()
          );
      return response.body();
    } catch (Exception e) {
      throw new InterruptedException("Could not add review: " + e);
    }
  }*/
}
