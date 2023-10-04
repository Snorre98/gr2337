package json;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

import core.AlbumReviewList;
import json.internal.AlbumReviewModule;

public class LoadFromFile {

  public static AlbumReviewList loadFromFile(){
    ObjectMapper mapper = new ObjectMapper();
    mapper.registerModule(new AlbumReviewModule());
    File file = new File("core/src/main/resources/core/albumreviews.json");
    try {
      AlbumReviewList ar = mapper.readValue(file, AlbumReviewList.class);
      return ar;
    } catch (StreamReadException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (DatabindException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return new AlbumReviewList();
  }
}
