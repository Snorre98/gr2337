package viewutil;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * Loggin Page Controller.
 */

public class LoginPageController {

  @FXML
  private Button button;

  @FXML
  private TextField username;

  @FXML
  private PasswordField password;

  private String saveFile = "IT1901gr2337/AlbumReviewApp/albumreviews.json";
  Path saveFilePath = Paths.get(System.getProperty("user.home"), saveFile);

  @FXML
  void userLogin(ActionEvent event) throws IOException {
    PageHandler pageHandler = new PageHandler();
    String un = username.getText();
    if ((un.length() < 4) || (un.length() > 20)) {
      Alert alert = new Alert(Alert.AlertType.INFORMATION);
      alert.setTitle("Warning");
      alert.setContentText("Username has to be between 4 and 20 characters");
      alert.showAndWait();
      throw new IllegalArgumentException(alert.getContentText());
    }
    pageHandler.loadAlbumList(username.getText(), saveFilePath);
  }

  public void setSaveFilePath(Path saveFile) {
    this.saveFilePath = saveFile;
  }
}
