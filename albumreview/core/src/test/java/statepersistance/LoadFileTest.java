package statepersistance;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import domainlogic.AlbumReview;
import domainlogic.AlbumReviewList;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.junit.jupiter.api.Test;
import statepersistence.LoadFromFile;

/**
 * Tests for loading from file.
 */
public class LoadFileTest {
  private Path saveFilePath;
  private AlbumReviewList list;

  /**
   * Setup for tests.
   */
  public void setUp(String filePath) throws URISyntaxException {
    URL resourceUrl = getClass().getClassLoader().getResource(filePath);
    saveFilePath = Paths.get(resourceUrl.toURI());
  }

  @Test
  public void testLoadPathError() {
    Exception pathError = assertThrows(NullPointerException.class, () -> {
      setUp("notPath");
    }, "TEST FAIL: path expected to be null");
  }

  @Test
  public void testLoadInvalidJson() throws URISyntaxException {
    setUp("invalid.json");

    Exception invalidJson = assertThrows(IllegalStateException.class, () -> {
      LoadFromFile.loadFromFile(saveFilePath, false);
    }, "TEST FAIL: JSON should be invalid, but was found not to be!");
  }

  @Test
  public void testLoadFile() throws IOException, URISyntaxException {
    setUp("fileTester.json");
    list = new AlbumReviewList();
    AlbumReview review = new AlbumReview("banana", 2);
    list.addAlbumReview(review);
    assertEquals(list, LoadFromFile.loadFromFile(saveFilePath, false));
  }
}
