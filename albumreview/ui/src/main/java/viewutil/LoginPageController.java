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

public class LoginPageController {

  @FXML
  private Button button;

  @FXML
  private TextField username;

  @FXML
  private PasswordField password;

  @FXML
  void userLogin(ActionEvent event) throws IOException {
    PageHandler pageHandler = new PageHandler();
    pageHandler.loadAlbumList(username.getText());
  }
}
