package statepersistence;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import domainlogic.AlbumReviewList;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import statepersistence.serializer.AlbumReviewModule;

/**
 * Loads data from Json file stored at user.home.
 * */
public class LoadFromFile {
  //private static final String saveFile = "IT1901gr2337/AlbumReviewApp/albumreviews.json";
  //static Path saveFilePath = Paths.get(System.getProperty("user.home"), saveFile);

  /**
   *   If the file-structure for datafile does not exist and forceLoad is true, this will create it
   *   at user.home (e.g "C:\Users\<UserName>\IT1901gr2337\AlbumReviewApp\albumreviews.json" for
   *   Windows).
   * */
  public static AlbumReviewList loadFromFile(Path saveFilePath, Boolean forceLoad) throws IOException {
    ObjectMapper mapper = new ObjectMapper();
    mapper.registerModule(new AlbumReviewModule());

    if(forceLoad){
      try{
        if (!Files.exists(saveFilePath.getParent())) {
          Files.createDirectories(saveFilePath.getParent());
        }
        if (!Files.exists(saveFilePath)) {
          Files.createFile(saveFilePath);
        }
        System.out.println("Fil was generated at :" + saveFilePath);
      }catch(IllegalArgumentException e ){
        throw new IllegalArgumentException("File was not generated!");
      }
    }

    File file = saveFilePath.toFile();

    if(file.length() == 0){
      return new AlbumReviewList();
    }
  //TODO: Check if we should use Log4j: https://logging.apache.org/log4j/2.x/
    try {
      AlbumReviewList ar = mapper.readValue(file, AlbumReviewList.class);
      return ar;

    } catch (StreamReadException e) {
      throw new IllegalStateException("broken json file!");
    }
      catch (DatabindException e) {
        throw new IllegalStateException("invalid format/fields in json file!");
    }
  }
}
