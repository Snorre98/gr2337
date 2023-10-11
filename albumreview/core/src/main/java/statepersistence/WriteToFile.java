package statepersistence;

import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import domainlogic.AlbumReviewList;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import statepersistence.serializer.AlbumReviewModule;

/**
 * Writes to JSON file at user.home location.
 * */
public class WriteToFile {
  private static final String saveFile = "IT1901gr2337/AlbumReviewApp/albumreviews.json";
  static Path saveFilePath = Paths.get(System.getProperty("user.home"), saveFile);

  public static void writeToFile(AlbumReviewList albums) {
    ObjectMapper mapper = new ObjectMapper();
    mapper.registerModule(new AlbumReviewModule());

    File file = saveFilePath.toFile();

    try {
      mapper.writeValue(file, albums);

    } catch (StreamWriteException e) {
      e.printStackTrace();

    } catch (DatabindException e) {
      e.printStackTrace();

    } catch (IOException e) {
      e.printStackTrace();

    }
  }
}
