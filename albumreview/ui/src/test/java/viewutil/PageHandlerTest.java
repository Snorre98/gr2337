// package viewutil;

// import static org.junit.jupiter.api.Assertions.assertTrue;

// import java.io.File;
// import java.io.IOException;
// import java.net.URISyntaxException;
// import java.net.URL;
// import java.nio.file.Path;
// import java.nio.file.Paths;
// import javafx.fxml.FXMLLoader;
// import javafx.scene.Parent;
// import javafx.scene.Scene;
// import javafx.stage.Stage;
// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.testfx.framework.junit5.ApplicationTest;


// /**
//  * Test class for PageHandler.
//  */

// public class PageHandlerTest extends ApplicationTest {
//   // private PageHandler pageHandler;
//   private Path saveFilePath;
//   private LoginPageController lpc;
//   private LoginPageControllerTest lpt;
//   private AlbumListControllerTest alc;
//   private File file;

//   /**
//   * Before Each.
//   */
//   // @BeforeEach
//   // public void setUp() throws URISyntaxException, IOException {
//   //   // URL resourceUrl = getClass().getClassLoader().getResource("testFile.json");
//   //   // System.out.println(resourceUrl);
//   //   // if (resourceUrl == null) {
//   //   //   throw new IllegalStateException(
//   //   //       "Cannot find mock JSON file. A fix might be to run 'mvn clean compile'");
//   //   // }
//   //   // saveFilePath = Paths.get(resourceUrl.toURI());
//   //   // file = new File(resourceUrl.toURI());
//   //   // lpc.setSaveFilePath(saveFilePath);
//   //   // String filePath = file.getAbsolutePath();
//   //   // AlbumListControllerTest.cleanJsonFile(filePath);
//   // }

//   @Override
//   public void start(Stage stage) throws IOException {
//     final FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/LoginPage_test.fxml"));
//     final Parent root = loader.load();
//     lpc = loader.getController();
//     stage.setScene(new Scene(root));
//     stage.show();
//   }

//   @Test
//   public void testLoadAlbumList() {
//     lpt = new LoginPageControllerTest();
//     lpt.testUserLogin();
//     assertTrue(Stage.getWindows().size() > 1);
//   }


//   @Test
//   public void testLoadAlbum() {
//     lpt = new LoginPageControllerTest();
//     lpt.testUserLogin();
//     alc = new AlbumListControllerTest();
//     alc.testAddAlbum();
//     clickOn("#openAlbum");
//     assertTrue(Stage.getWindows().size() > 1);
//   }
// }
