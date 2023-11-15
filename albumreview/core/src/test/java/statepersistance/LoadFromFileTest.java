package statepersistance;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import domainlogic.Album;
import domainlogic.AlbumList;
import domainlogic.Review;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.junit.jupiter.api.Test;
import statepersistence.LoadFromFile;

/**
 * Tests for loading from file.
 */
public class LoadFromFileTest {
  private Path saveFilePath;
  private AlbumList albums;

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
  public void createFile() throws IOException, URISyntaxException {
    String s = "src/test/resources/testFile.json";
    Path resourcePath = Paths.get(s);

    LoadFromFile.loadFromFile(resourcePath, true);
    assertTrue(Files.exists(resourcePath));
    Files.deleteIfExists(resourcePath);
  }

  @Test
  public void testLoadFile() throws IOException, URISyntaxException {
    setUp("fileTester.json");
    AlbumList jsonAlbumList = LoadFromFile.loadFromFile(saveFilePath, false);
    assertEquals(jsonAlbumList.getAlbumByIndex(0).getArtist(), "testArtist");
    assertEquals(jsonAlbumList.getAlbumByIndex(0).getName(), "testAlbumName");
    assertEquals(jsonAlbumList.getAlbumByIndex(0).getReview(0).getUserName(), "testUser");
    assertEquals(jsonAlbumList.getAlbumByIndex(0).getReview(0).getRating(), 1);

    /*
    albums = new AlbumList();
    Album album = new Album("testArtist", "testAlbumName");
    Review review = new Review("testUser", 1);
    album.addReview(review);
    albums.addAlbum(album);
    assertEquals(albums, LoadFromFile.loadFromFile(saveFilePath, false));*/
  }
}
