package statepersistence;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import domainlogic.AlbumReviewList;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import statepersistence.serializer.AlbumReviewModule;

/**
 * Loads data from Json file stored at user.home.
 */
public class LoadFromFile {

  /**
   * Takes a path and an option to forceLoad, which creates a file at the path loaction if the file
   * does not exist.
   */
  public static AlbumReviewList loadFromFile(Path saveFilePath, Boolean forceLoad)
      throws IOException {
    ObjectMapper mapper = new ObjectMapper();
    mapper.registerModule(new AlbumReviewModule());

    if (forceLoad) {
      try {
        if (!Files.exists(saveFilePath.getParent())) {
          Files.createDirectories(saveFilePath.getParent());
        }
        if (!Files.exists(saveFilePath)) {
          Files.createFile(saveFilePath);
        }
        System.out.println("Fil was generated at :" + saveFilePath);
      } catch (IllegalArgumentException e) {
        throw new IllegalArgumentException("File was not generated!");
      }
    }

    File file = saveFilePath.toFile();

    if (file.length() == 0) {
      return new AlbumReviewList();
    }
    try {
      AlbumReviewList ar = mapper.readValue(file, AlbumReviewList.class);
      return ar;

    } catch (StreamReadException e) {
      throw new IllegalStateException("broken json file!");
    } catch (DatabindException e) {
      throw new IllegalStateException("invalid format/fields in json file!");
    }
  }
}
