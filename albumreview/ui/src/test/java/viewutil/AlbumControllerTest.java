package viewutil;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import domainlogic.Album;
import domainlogic.Review;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Set;

import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

/**
 * Album Controller Test.
 */

public class AlbumControllerTest extends ApplicationTest {
  private AlbumListControllerTest act;
  private LoginPageController lpc;
  private Path saveFilePath;
  private LoginPageControllerTest test;
  private File file;

  /**
   * Before Each.
   */
  @BeforeEach
  public void userLoginAndAddAlbum() {
    test = new LoginPageControllerTest();
    test.testUserLogin();
    act = new AlbumListControllerTest();
    act.testAddAlbum();

    ListView<Album> listView = lookup("#albumListView").query();
    listView.scrollTo(0);
    Set<Node> cells = listView.lookupAll(".list-cell");
    Node firstItemNode = cells.stream().findFirst().orElse(null);

    if (firstItemNode != null) {
      clickOn(firstItemNode);
    }

    clickOn("#openAlbum");
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
    AlbumListControllerTest.cleanJsonFile(filePath);
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
  public void testAddRating() {
    clickOn("#rating").write("5");
    clickOn("#newReview");
    ArrayList<Integer> expextedOut = new ArrayList<>();
    expextedOut.add(5);
    ListView<Review> reviewsListView = lookup("#reviews").query();
    ObservableList<Review> rlv = reviewsListView.getItems();
    ArrayList<Integer> actualOut = new ArrayList<>();
    for (Review r : rlv) {
      actualOut.add(r.getRating());
    }
    assertEquals(expextedOut, actualOut);
  }

  @Test
  public void testAddRatingWithoutInt() {
    clickOn("#rating").write("This aint no int");
    clickOn("#newReview");
    assertTrue(lookup(".alert").queryAll().size() > 0);
  }

  @Test
  public void testAddRatingWithoutInputs() {
    clickOn("#newReview");
    assertTrue(lookup(".alert").queryAll().size() > 0);
  }

  @Test
  public void testRemoveRating() {
    testAddRating();
    clickOn("#removeReview");
    ListView<Review> reviewsListView = lookup("#reviews").query();
    ObservableList<Review> rlv = reviewsListView.getItems();
    assertTrue(rlv.isEmpty());
  }
}
