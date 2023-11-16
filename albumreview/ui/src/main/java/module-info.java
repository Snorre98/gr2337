module albumreview.ui {

  requires albumreview.core;

  requires javafx.fxml;
  requires javafx.graphics;
  requires javafx.controls;
  requires java.net.http;

  opens viewutil to javafx.graphics, javafx.fxml;
}
