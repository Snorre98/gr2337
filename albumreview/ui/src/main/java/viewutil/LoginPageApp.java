package viewutil;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * login page.
 */

public class LoginPageApp extends Application {
  public static void main(String[] args) {
    Application.launch(args);
  }

  @Override
  public void start(Stage primaryStage) throws IOException {
    primaryStage.setTitle("Login-Page");

    URL resourceUrl = getClass().getResource("/fxml/LoginPage.fxml");
    Parent root = FXMLLoader.load(Objects.requireNonNull(resourceUrl));
    Scene scene = new Scene(root);
    String css = this.getClass().getResource("/style.css").toExternalForm();
    scene.getStylesheets().add(css);
    primaryStage.setScene(scene);
    primaryStage.show();
  }
}
