package json;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

import core.AlbumReviewList;
import json.internal.AlbumReviewModule;

public class LoadFromFile {

  public static AlbumReviewList loadFromFile() throws StreamReadException, DatabindException, IOException{
    ObjectMapper mapper = new ObjectMapper();
    mapper.registerModule(new AlbumReviewModule());
    File file = new File("core/src/main/resources/albumreviews.json");
    AlbumReviewList ar = mapper.readValue(file, AlbumReviewList.class);
    return ar;
  }

  public static void main(String[] args) {
    try {
      AlbumReviewList albumReviews = loadFromFile();
      System.out.println(albumReviews.getAlbumReviews());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
