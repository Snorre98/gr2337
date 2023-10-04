module albumreview.ui {
    requires com.fasterxml.jackson.databind;

    requires albumreview.core;
    requires javafx.controls;
    requires javafx.fxml;

    opens ui to javafx.graphics, javafx.fxml;
}
