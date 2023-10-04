package json;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

import core.AlbumReviewList;
import json.internal.AlbumReviewModule;

public class WriteToFile {
  
  public static void writeToFile(AlbumReviewList albums){
    ObjectMapper mapper = new ObjectMapper();
    mapper.registerModule(new AlbumReviewModule());
    File file = new File("core/src/main/resources/albumreviews.json");
    try {
      mapper.writeValue(file, albums);
    } catch (StreamWriteException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (DatabindException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
}
