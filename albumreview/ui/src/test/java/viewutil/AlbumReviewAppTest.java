package viewutil;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

/**
 * Testing app launch.
 */
public class AlbumReviewAppTest extends ApplicationTest {


  @Override
  public void start(Stage stage) throws Exception {
    new AlbumReviewApp().start(stage);
  }

  @Test
  public void testUiElementsExist() {
    // Check if UI elements exist
    assertNotNull(lookup("#albumListView").queryAs(ListView.class));
    assertNotNull(lookup("#addButton").queryAs(Button.class));
    assertNotNull(lookup("#removeButton").queryAs(Button.class));
    assertNotNull(lookup("#albumName").queryAs(TextField.class));
    assertNotNull(lookup("#albumRating").queryAs(TextField.class));
    assertNotNull(lookup("#sortByNameButton").queryAs(Button.class));
    assertNotNull(lookup("#sortByRatingButton").queryAs(Button.class));
    assertNotNull(lookup("#openButton").queryAs(Button.class));
  }



}
