module albumreview.ui {
  //requires albumreview.controller;
  requires albumreview.core;

  requires javafx.fxml;
  requires javafx.graphics;
  requires javafx.controls;
  opens viewutil to javafx.graphics, javafx.fxml;
}