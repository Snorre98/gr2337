package viewutil;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * Loggin Page Controller.
 */

public class LogginPageController {

  PageHandler pageHandler;

  @FXML
  private Button button;

  @FXML
  private TextField username;

  @FXML
  private PasswordField password;

  @FXML
  void userLogin(ActionEvent event) throws IOException {
    pageHandler.loadAlbumList(username.getText());
  }
}
