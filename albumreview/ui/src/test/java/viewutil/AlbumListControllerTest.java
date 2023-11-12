package viewutil;

import com.fasterxml.jackson.databind.ObjectMapper;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.testfx.framework.junit5.ApplicationTest;


/**
 * ALbum List Controller Test.
 */
public class AlbumListControllerTest extends ApplicationTest {
  private AlbumListController alc;
  private LoginPageController lpc;
  private Path saveFilePath;
  private LoginPageControllerTest test;
  private File file;

  // @BeforeAll
  // public static void setupHeadless() {
  // LoginPageApp.supportHeadless();
  // }

  /**
   * Since we now are testing the AlbumList controller we start by user login befor each test.
   */
  @BeforeEach
  public void userLogin() {
    test = new LoginPageControllerTest();
    test.testUserLogin();
  }

  /**
   * Before Each.
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
    file = new File(resourceUrl.toURI());
    lpc.setSaveFilePath(saveFilePath);
    // pageHandler = new PageHandler();
  }

  @Override
  public void start(Stage stage) throws IOException {
    final FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/LoginPage_test.fxml"));
    final Parent root = loader.load();
    lpc = loader.getController();
    stage.setScene(new Scene(root));
    stage.show();
  }

  @Test
  public void testController_initial() {
    assertNotNull(alc);
  }

  /**
   * album start up to add albums used for futher testing.
   */
  public void albumStartUp(int i) {
    if (i == 1) {
      clickOn("#albumInput").write("Kid A");
      clickOn("#artistInput").write("Radiohead");
      clickOn("#newAlbum");
    } else {
      clickOn("#albumInput").write("Kid A");
      clickOn("#artistInput").write("Radiohead");
      clickOn("#newAlbum");
      clickOn("#albumInput").write("The Stone Roses");
      clickOn("#artistInput").write("The Stone Roses");
      clickOn("#newAlbum");
      clickOn("#albumInput").write("Sprengkulde");
      clickOn("#artistInput").write("Cezinando");
      clickOn("#newAlbum");
    }
  }

  /**
   * Test to see if add album works.
   */
  @Test
  public void testAddAlbum() {
    albumStartUp(1);
    assertNotNull("#albumList");
    String filePath = file.getAbsolutePath();
    cleanJsonFile(filePath);
  }

  /**
   * Test too see if sort by album name works.
   */
  @Test
  public void testSortAlbum() {
    albumStartUp(2);
    clickOn("#sortAlbum");

    String filePath = file.getAbsolutePath();
    cleanJsonFile(filePath);
  }

  /**
   * Test too see if sort by artist name works.
   */
  @Test
  public void testSortArtist() {
    albumStartUp(2);
    clickOn("#sortArtist");

    String filePath = file.getAbsolutePath();
    cleanJsonFile(filePath);
  }

  /**
   * clean json file so we start on a blank sheet every test.
   */
  public static void cleanJsonFile(String filePath) {
    try {
      // Read the existing JSON file
      ObjectMapper objectMapper = new ObjectMapper();
      ObjectNode rootNode = objectMapper.createObjectNode();
      // Write the empty JSON object back to the file
      objectMapper.writeValue(new File(filePath), rootNode);

      System.out.println("JSON file cleaned successfully.");
    } catch (IOException e) {
      e.printStackTrace();
    }

  }

}
