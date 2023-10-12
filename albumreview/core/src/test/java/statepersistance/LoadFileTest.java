package statepersistance;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

import com.fasterxml.jackson.core.exc.StreamReadException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import domainlogic.AlbumReview;
import domainlogic.AlbumReviewList;
import statepersistence.LoadFromFile;

public class LoadFileTest {
  private Path saveFilePath;
  private AlbumReviewList list;



  //@BeforeEach
  public void setUp(String filePath) throws URISyntaxException {
    URL resourceURL = getClass().getClassLoader().getResource(filePath);
    saveFilePath = Paths.get(resourceURL.toURI());
  }

  @Test
  public void testLoadPathError() {
    Exception pathError = assertThrows(NullPointerException.class, () -> {
      setUp("notPath");
    }, "TEST FAIL: path expected to be null");
  }

  @Test
  public  void testLoadInvalidJson() throws URISyntaxException {
    setUp("invalid.json");

    Exception invalidJSON = assertThrows(IllegalStateException.class, () -> {
      LoadFromFile.loadFromFile(saveFilePath, false);
    }, "TEST FAIL: JSON should be invalid, but was found not to be!");
  }

  @Test
  public void testLoadFile() throws IOException, URISyntaxException {
    setUp("fileTester.json");
    list = new AlbumReviewList();
    AlbumReview review = new AlbumReview("banana", 2);
    list.addAlbumReview(review);
    assertEquals(
        list,
        LoadFromFile.loadFromFile(saveFilePath, false)
    );
  }
}
