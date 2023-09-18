package ui;

//TODO import needed custom classes

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.BinaryOperator;
import java.util.function.UnaryOperator;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class AlbumReviewAppController implements Initializable {
    @FXML
    private AnchorPane background;

    @FXML
    private TextField firstNumber;

    @FXML
    private TextField secondNumber;

    //TODO create Album object in core
    @FXML
    private ListView<Album> albumListView;

    //TODO create AlbumList object in core
    private AlbumList albumList;

    private int selected;

    public void initAlbumListView(){
        albumList = new AlbumList();
        //TODO create getAlbum methode
        ObservableList<Album> observableAlbums = FXCollections.observableArrayList(albumList.getAlbums());
        albumListView.getItems().setAll(observableAlbums);
    }

    /**
     * adds album to list view in ui
     */
    @FXML
    void addAlbumToListView(ActionEvent event){
        if(firstNumber.getText().isEmpty() || secondNumber.getText().isEmpty()){
            Alert alertInfo = new Alert(Alert.AlertType.INFORMATION);
            alertInfo.setTitle("Warning");
            alertInfo.setContentText("You must enter an album and a rating");
            alertInfo.showAndWait();
        }
        else{
            try{
                //TODO create addAlbum methode
                albumList.addAlbum(firstNumber.getText(), Integer.parseInt(secondNumber.getText()));
                albumListView.getItems().setAll(albumList.getAlbums());
                firstNumber.setText("");
                secondNumber.setText("");

        } catch (NumberFormatException e) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Warning");
                alert.setContentText("You must add a rating of the album");
                alert.showAndWait();
            }
        }
    }

    /**
     * remove album from ListView in ui
     */
    @FXML
    void removeAlbumFromListView(ActionEvent event){
        //TODO create removeAlbum
        albumList.removeAlbum(selected);
        albumListView.getItems().setAll(albumList.getAlbums());
    }

    /**
     * sorts album by name in ui
     */
    @FXML
    void sortByName(ActionEvent event){
        //TODO create sortName() methode in core
        albumList.sortName();
        albumListView.getItems().setAll(albumList.getAlbums());
    }

    /**
     * sorts album by rating in ui
     */
    @FXML
    void sortByRating(ActionEvent event){
        //TODO create sortRating() methode in core
        albumList.sortRating();
        albumListView.getItems().setAll(albumList.getAlbums());
    }

    /**
     * Writes albumList to file
     */
    @FXML
    void handleSave() throws IOException{
        //TODO create writeToFile methode in core
        albumList.writeToFile();
    }

    /**
     * Loads albumList from file and sets it to ListView ui
     */
    @FXML
    void handleLoad(){
        //TODO create loadFile methode in core
        albumList.loadFile();
        albumListView.getItems().setAll(albumList.getAlbums());
    }

    /**
     * Initializes albumListView and handles mouseClick in albumListView
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initAlbumListView();
        albumListView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                selected = albumListView.getSelectionModel().getSelectedIndex();
            }
        });
    }
}
