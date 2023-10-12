package viewutil;

import domainlogic.AlbumReview;
import domainlogic.AlbumReviewList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import statepersistence.LoadFromFile;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.testfx.matcher.control.ListViewMatchers.hasItems;

public class AlbumReviewAppControllerTest extends ApplicationTest {

    private AlbumReviewAppController controller;
    ListView<AlbumReview> albumListView ;
   

    @BeforeEach
    public void setUp() {
        
    }

    @Override
    public void start(Stage stage) throws Exception {
      final FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/AlbumReviewApp_test.fxml"));
      final Parent root = loader.load();
      this.controller = loader.getController();
      stage.setScene(new Scene(root));
      stage.show();


    }

    @Test
    public void testAddRating() {
        clickOn("#albumName").write("Test Album");
        clickOn("#albumRating").write("5");
        clickOn("#addButton");

        ObservableList<AlbumReview> items = albumListView.getItems();
        System.out.println(items);
        assertEquals(1, items.size());
        assertEquals("Test Album", items.get(0).getName());
        assertEquals(5, items.get(0).getRating());
    }

    @Test
    public void testAddRatingWithEmptyFields() {
        clickOn(controller.addButton);

        // Ensure an alert is shown
        assertTrue(lookup(".alert").query() instanceof Alert);
    }

    @Test
    public void testRemoveSelectedAlbum() {
        AlbumReviewList albumList = new AlbumReviewList();
        albumList.addAlbumReview(new AlbumReview("Test Album", 5));
        controller.albumListView.setItems(FXCollections.observableArrayList(albumList.getAlbumReviews()));

        clickOn(controller.albumListView);
        clickOn(controller.removeButton);

        ObservableList<AlbumReview> items = controller.albumListView.getItems();
        assertTrue(items.isEmpty());
    }

    @Test
    public void testSortByName() {
        AlbumReviewList albumList = new AlbumReviewList();
        albumList.addAlbumReview(new AlbumReview("B Album", 5));
        albumList.addAlbumReview(new AlbumReview("C Album", 3));
        albumList.addAlbumReview(new AlbumReview("A Album", 4));
        controller.albumListView.setItems(FXCollections.observableArrayList(albumList.getAlbumReviews()));

        clickOn(controller.sortByNameButton);

        ObservableList<AlbumReview> items = controller.albumListView.getItems();
        assertEquals("A Album", items.get(0).getAlbumName());
        assertEquals("B Album", items.get(1).getAlbumName());
        assertEquals("C Album", items.get(2).getAlbumName());
    }

    @Test
    public void testSortByRating() {
        AlbumReviewList albumList = new AlbumReviewList();
        albumList.addAlbumReview(new AlbumReview("Album 1", 4));
        albumList.addAlbumReview(new AlbumReview("Album 2", 2));
        albumList.addAlbumReview(new AlbumReview("Album 3", 5));
        controller.albumListView.setItems(FXCollections.observableArrayList(albumList.getAlbumReviews()));

        clickOn(controller.sortByRatingButton);

        ObservableList<AlbumReview> items = controller.albumListView.getItems();
        assertEquals("Album 2", items.get(0).getAlbumName());
        assertEquals("Album 1", items.get(1).getAlbumName());
        assertEquals("Album 3", items.get(2).getAlbumName());
    }
}

