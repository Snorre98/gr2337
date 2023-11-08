package statepersistance;

import static org.junit.jupiter.api.Assertions.assertEquals;

import domainlogic.Album;
import domainlogic.AlbumList;
import domainlogic.Review;
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
    AlbumList albums = new AlbumList();
    Album album = new Album("testArtist", "testAlbumName");
    Review review = new Review("testUser", 1);
    album.addReview(review);
    albums.addAlbum(album);
    WriteToFile.writeToFile(albums, saveFilePath);

    assertEquals(albums, LoadFromFile.loadFromFile(saveFilePath, false));
  }
}
