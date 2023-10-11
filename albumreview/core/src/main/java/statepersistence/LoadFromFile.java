package statepersistence;

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
  private static final String saveFile = "IT1901gr2337/AlbumReviewApp/albumreviews.json";
  static Path saveFilePath = Paths.get(System.getProperty("user.home"), saveFile);

  /**
   *   If the file-structure does not exist, this will create it
   *   at user.home (e.g "C:\Users\<UserName>\IT1901gr2337\AlbumReviewApp\albumreviews.json" for
   *   Windows).
   * */
  public static AlbumReviewList loadFromFile() throws IOException {
    ObjectMapper mapper = new ObjectMapper();
    mapper.registerModule(new AlbumReviewModule());

    if (!Files.exists(saveFilePath.getParent())) {
      Files.createDirectories(saveFilePath.getParent());
    }
    if (!Files.exists(saveFilePath)) {
      Files.createFile(saveFilePath);
    }

    File file = saveFilePath.toFile();

    try {
      AlbumReviewList ar = mapper.readValue(file, AlbumReviewList.class);
      if (ar != null) {
        return ar;
      }

    } catch (StreamReadException e) {

      e.printStackTrace();

    }
    /* Because StreamReader is a subclass of these
       it will catch at StreamReader if these fail
      catch (DatabindException e) {

      e.printStackTrace();

    } catch (IOException e) {

      e.printStackTrace();
    }*/
    return new AlbumReviewList();
  }
}
