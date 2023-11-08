package statepersistence;


import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import domainlogic.AlbumList;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import statepersistence.serializer.AlbumReviewModule;


/**
 * Loads data from Json file.
 */
public class LoadFromFile {

  /**
   * Loads AlbumList object from saveFilePath.
   * 
   * @param saveFilePath Path to json file.
   * @param forceLoad Boolean that decides if file should be created if missing.
   * @return AlbumList object.
   * @throws IOException If mapper can't read value or file is not generated.
   */
  public static AlbumList loadFromFile(Path saveFilePath, Boolean forceLoad) throws IOException {
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
      return new AlbumList();
    }
    try {
      AlbumList al = mapper.readValue(file, AlbumList.class);
      return al;

    } catch (StreamReadException e) {
      throw new IllegalStateException("broken json file!");
    } catch (DatabindException e) {
      throw new IllegalStateException("invalid format/fields in json file!");
    }
  }
}
