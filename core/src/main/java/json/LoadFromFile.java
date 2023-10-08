package json;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

import core.AlbumReviewList;
import json.internal.AlbumReviewModule;

public class LoadFromFile {

  private static final String saveFile = "IT1901gr2337/AlbumReviewApp/albumreviews.json";
  static Path saveFilePath = Paths.get(System.getProperty("user.home"), saveFile);

  public static AlbumReviewList loadFromFile() throws IOException {
    ObjectMapper mapper = new ObjectMapper();
    mapper.registerModule(new AlbumReviewModule());

    // If the file-structure does not exist, this will create it
    // at user.home (e.g "C:\Users\<UserName>\IT1901gr2337\AlbumReviewApp\albumreviews.json" for
    // Windows)
    if (!Files.exists(saveFilePath.getParent())) {
      Files.createDirectories(saveFilePath.getParent());
    }
    if (!Files.exists(saveFilePath)) {
      Files.createFile(saveFilePath);
    }

    File file = saveFilePath.toFile();

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
