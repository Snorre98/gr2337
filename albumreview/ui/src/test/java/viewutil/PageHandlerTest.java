package viewutil;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.apache.logging.log4j.core.util.Loader;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.testfx.framework.junit5.ApplicationTest;
import domainlogic.Album;
import domainlogic.AlbumList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Test class for PageHandler.
 */

public class PageHandlerTest extends ApplicationTest {
  private PageHandler pageHandler;
  private Path saveFilePath;
  private LoginPageController lpc;
  private AlbumListController alc;

  /**
   * Setup for test.
   */

  @BeforeEach
  public void setUp() throws URISyntaxException, IOException {
    URL resourceUrl = getClass().getClassLoader().getResource("testFile.json");
    System.out.println(resourceUrl);
    if (resourceUrl == null) {
      throw new IllegalStateException(
          "Cannot find mock JSON file. A fix might be to run 'mvn clean compile'");
    }
    saveFilePath = Paths.get(resourceUrl.toURI());
    alc.setSaveFilePath(saveFilePath);
    pageHandler = new PageHandler();
  }

  // @Override
  // public void start(Stage stage) throws IOException {
  // final FXMLLoader loader =
  // new FXMLLoader(getClass().getResource("/fxml/LoginPage_test.fxml"));
  // final Parent root = loader.load();
  // lpc = loader.getController();
  // stage.setScene(new Scene(root));
  // stage.show();
  // }

  @Override
  public void start(Stage stage) throws IOException {
    final FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/AlbumList_test.fxml"));
    final Parent root = loader.load();
    alc = loader.getController();
    stage.setScene(new Scene(root));
    stage.show();
  }

  @Test
  public void testLoadAlbumList() {
    // test for load album list
    clickOn("#username").write("tretruser");
    clickOn("#button");
    // assertNotNull(lookup("#The Albums").query());
    assertTrue(Stage.getWindows().size() > 1);
  }

  /**
   * test for å teste testen.
   */

  // public void addTestAlbum() {
  // AlbumList testAlbums;
  // testAlbums = new AlbumList();
  // Album testAlbum;
  // testAlbum = new Album("Peggy", "SCARING THE HOES");

  // testAlbums.addAlbum(testAlbum);
  // }

  @Test
  public void testLoadAlbum() {
    // test for load album
    try {
      // test code
      clickOn("#albumInput").write("Songs for the Deaf");
      clickOn("#artistInput").write("Queens of the Stone Age");
      clickOn("#newAlbum");
      clickOn("#albumListView");
      clickOn("#openAlbum");
      assertTrue(Stage.getWindows().size() > 1);
    } catch (RuntimeException e) {
      Throwable rootCause = e.getCause();
      if (rootCause != null) {
        rootCause.printStackTrace();
      }

      // assertNotNull(lookup("#Album").query());
    }
  }
}
