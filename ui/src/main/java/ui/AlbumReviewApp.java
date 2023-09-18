package ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
//import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class AlbumReviewApp extends Application {
    public static void main(String[] args) {
        Application.launch(args);
    }
    @Override
    public void start(Stage stage) throws IOException {
        primaryStage.setTitle("Album-Review");
        primaryStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("AlbumReviewApp.fxml"))));


       /* FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("AlbumReviewApp.fxml"));
        Parent parent = fxmlLoader.load();
        stage.setScene(new Scene(parent));
        stage.show();*/
    }

    /*public static void main(String[] args) {
        launch();
    }*/
}