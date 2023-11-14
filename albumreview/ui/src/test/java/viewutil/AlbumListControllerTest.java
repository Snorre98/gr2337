package viewutil;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import domainlogic.Album;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;


/**
 * ALbum List Controller Test.
 */
public class AlbumListControllerTest extends ApplicationTest {
  private LoginPageController lpc;
  private Path saveFilePath;
  private LoginPageControllerTest test;
  private File file;


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
    String filePath = file.getAbsolutePath();
    cleanJsonFile(filePath);
  }

  @Override
  public void start(Stage stage) throws IOException {
    final FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/LoginPage_test.fxml"));
    final Parent root = loader.load();
    lpc = loader.getController();
    stage.setScene(new Scene(root));
    stage.show();
  }

  /**
   * album start up to add albums used for futher testing.
   */
  public void albumStartUp(int i) {
    if (i == 1) {
      clickOn("#albumInput").write("Kid F");
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
  }

  /**
   * Test too see if sort by album name works.
   */
  @Test
  public void testSortAlbum() {
    albumStartUp(2);
    clickOn("#sortAlbum");

    ArrayList<String> expextedOut = new ArrayList<>();
    expextedOut.add("Kid A");
    expextedOut.add("Sprengkulde");
    expextedOut.add("The Stone Roses");

    ListView<Album> albumListView = lookup("#albumListView").query();
    ObservableList<Album> alb = albumListView.getItems();

    ArrayList<String> actualOut = new ArrayList<>();
    for (Album a : alb) {
      actualOut.add(a.getName());
    }

    assertEquals(expextedOut, actualOut);
  }

  /**
   * Test too see if sort by artist name works.
   */
  @Test
  public void testSortArtist() {
    albumStartUp(2);
    clickOn("#sortArtist");

    ArrayList<String> expextedOut = new ArrayList<>();
    expextedOut.add("Cezinando");
    expextedOut.add("Radiohead");
    expextedOut.add("The Stone Roses");

    ListView<Album> albumListView = lookup("#albumListView").query();
    ObservableList<Album> alb = albumListView.getItems();

    ArrayList<String> actualOut = new ArrayList<>();
    for (Album a : alb) {
      actualOut.add(a.getArtist());
    }

    assertEquals(expextedOut, actualOut);
  }

  /**
   * Test to se that user cannot add duplicate albums.
   */
  @Test
  public void testAddDuplicateAlbum() {
    albumStartUp(1);
    albumStartUp(1);
    assertTrue(lookup(".alert").queryAll().size() > 0);
  }

  /**
   * function for cleaning json file.
   */
  public static void cleanJsonFile(String filePath) {
    try {
      ObjectMapper objectMapper = new ObjectMapper();
      ObjectNode rootNode = objectMapper.createObjectNode();
      
      objectMapper.writeValue(new File(filePath), rootNode);

      System.out.println("JSON file cleaned successfully.");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
