package statepersistance;

import static org.junit.jupiter.api.Assertions.assertEquals;

import domainlogic.AlbumReview;
import domainlogic.AlbumReviewList;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import statepersistence.LoadFromFile;
import statepersistence.WriteToFile;


/**
 * Tests write to file.
 */
public class WriteToFileTest {
  private Path saveFilePath;

  /**
   * Setup for tests.
   */
  @BeforeEach
  public void setUp() throws URISyntaxException, IOException {
    URL resourceUrl = getClass().getClassLoader().getResource("writeTest.json");
    System.out.println(resourceUrl);
    if (resourceUrl == null) {
      throw new IllegalStateException(
          "Cannot find mock JSON file. A fix might be to run 'mvn clean compile'");
    }
    saveFilePath = Paths.get(resourceUrl.toURI());
    Files.writeString(saveFilePath, "[]");
  }

  @Test
  public void testWriteToFile() throws IOException {
    AlbumReviewList albums = new AlbumReviewList();
    AlbumReview review = new AlbumReview("fly", 6);
    albums.addAlbumReview(review);
    WriteToFile.writeToFile(albums, saveFilePath);

    assertEquals(albums.getAlbumReview(0).toString(),
        LoadFromFile.loadFromFile(saveFilePath, false).getAlbumReview(0).toString());
  }
}
