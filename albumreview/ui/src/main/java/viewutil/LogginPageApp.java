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

public class LogginPageApp extends Application{
  public static void main(String[] args) {
    Application.launch(args);
  }

  @Override
  public void start(Stage primaryStage) throws IOException {
    primaryStage.setTitle("Login-Page");

    URL resourceUrl = getClass().getResource("/fxml/LogginPage.fxml");

    System.out.println("Resource URL: " + resourceUrl);
    primaryStage.setScene(new Scene(FXMLLoader.load(resourceUrl)));

    Parent root = FXMLLoader.load(Objects.requireNonNull(resourceUrl));
    primaryStage.setScene(new Scene(root));
    primaryStage.show();
  }
}
