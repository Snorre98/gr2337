package viewutil;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import domainlogic.AlbumReview;
import domainlogic.AlbumReviewList;
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
 * Test for albumreviewcontroller.
 */
public class AlbumReviewAppControllerTest extends ApplicationTest {

  private AlbumReviewAppController controller;
  ListView<AlbumReview> albumListView;


  @BeforeEach
  public void setUp() {
    albumListView = lookup("#albumListView").query();
  }

  @Override
  public void start(Stage stage) throws Exception {
    final FXMLLoader loader =
        new FXMLLoader(getClass().getResource("/fxml/AlbumReviewApp_test.fxml"));
    final Parent root = loader.load();
    this.controller = loader.getController();
    stage.setScene(new Scene(root));
    stage.show();
  }

  @Test
  public void testController_initial() {
    assertNotNull(this.controller);
  }


  @Test
  public void testAddRating() {
    final int sizeBefore = albumListView.getItems().size();

    clickOn("#albumName").write("Test Album");
    clickOn("#albumRating").write("5");
    clickOn("#addButton");

    ObservableList<AlbumReview> items = albumListView.getItems();

    assertEquals(sizeBefore + 1, items.size());
    assertEquals("Test Album", items.get(sizeBefore).getName());
    assertEquals(5, items.get(sizeBefore).getRating());
  }

  @Test
  public void testAddRatingWithoutInt() {
    clickOn("#albumName").write("Test Album");
    clickOn("#albumRating").write("Not int");
    clickOn("#addButton");
    assertTrue(lookup(".alert").queryAll().size() > 0);
  }

  @Test
  public void testAddRatingWithoutInputs() {
    clickOn("#addButton");
    assertTrue(lookup(".alert").queryAll().size() > 0);
  }


  @Test
  public void testRemoveSelectedAlbum() {
    AlbumReviewList albumList = new AlbumReviewList();
    albumList.addAlbumReview(new AlbumReview("Test Album", 5));

    int sizeBefore = albumListView.getItems().size();
    clickOn("#removeButton");

    int sizeAfter = albumListView.getItems().size();
    assertEquals(sizeBefore - 1, sizeAfter);
  }

  @Test
  public void testOpenButton() {
    // Click the button to trigger the openButton method
    clickOn("#openButton");
    assertTrue(Stage.getWindows().size() > 1);

  }

}

