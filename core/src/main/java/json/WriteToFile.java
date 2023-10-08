package json;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

import core.AlbumReviewList;
import json.internal.AlbumReviewModule;

public class WriteToFile {
  private static final String saveFile = "IT1901gr2337/AlbumReviewApp/albumreviews.json";
  static Path saveFilePath = Paths.get(System.getProperty("user.home"), saveFile);

  public static void writeToFile(AlbumReviewList albums){
    ObjectMapper mapper = new ObjectMapper();
    mapper.registerModule(new AlbumReviewModule());

    File file = saveFilePath.toFile();

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
