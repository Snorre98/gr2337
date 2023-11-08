package statepersistence;


import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import domainlogic.AlbumList;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import statepersistence.serializer.AlbumReviewModule;

/**
 * Writes data to json file.
 */
public class WriteToFile {

  /**
   * Writes AlbumList to saveFilePath.
   * 
   * @param albums AlbumList object.
   * @param saveFilePath path to file where albums should be written.
   */
  public static void writeToFile(AlbumList albums, Path saveFilePath) {
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
