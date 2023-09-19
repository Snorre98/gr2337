package ui;

//TODO import needed custom classes

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.BinaryOperator;
import java.util.function.UnaryOperator;

import core.AlbumReview;
import core.AlbumReviewList;
import core.FileHandler;
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
    private ListView<AlbumReview> albumListView;

    //TODO create AlbumList object in core
    private AlbumReviewList albumList;

    private int selected;

    public void initAlbumListView(){
        albumList = new AlbumReviewList();
        //TODO create getAlbum methode
        ObservableList<AlbumReview> observableAlbums = FXCollections.observableArrayList(albumList.getAlbumReviews());
        albumListView.getItems().setAll(observableAlbums);
        handleLoad();
    }

    /**
     * adds album to list view in ui
     */
    @FXML
    void addRating(ActionEvent event){
        if(firstNumber.getText().isEmpty() || secondNumber.getText().isEmpty()){
            Alert alertInfo = new Alert(Alert.AlertType.INFORMATION);
            alertInfo.setTitle("Warning");
            alertInfo.setContentText("You must enter an album and a rating");
            alertInfo.showAndWait();
        }
        else{
            try{
                //TODO create addAlbum methode
                albumList.addAlbumReview(firstNumber.getText(), Integer.parseInt(secondNumber.getText()));
                albumListView.getItems().setAll(albumList.getAlbumReviews());
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
    void removeSelectedAlbum(ActionEvent event){
        //TODO create removeAlbum
        albumList.removeAlbumReview(selected);
        albumListView.getItems().setAll(albumList.getAlbumReviews());
    }

    /**
     * sorts album by name in ui
     */
    @FXML
    void sortByName(ActionEvent event){
        //TODO create sortName() methode in core
        albumList.sortName();
        albumListView.getItems().setAll(albumList.getAlbumReviews());
    }

    /**
     * sorts album by rating in ui
     */
    @FXML
    void sortByRating(ActionEvent event){
        //TODO create sortRating() methode in core
        albumList.sortRating();
        albumListView.getItems().setAll(albumList.getAlbumReviews());
    }

    /**
     * Writes albumList to file
     */
    
    void handleSave() throws IOException{
        //TODO create writeToFile methode in core
        FileHandler.writeToFile(albumList);
    }

    /**
     * Loads albumList from file and sets it to ListView ui
     */
    
    void handleLoad(){
        //TODO create loadFile methode in core
        FileHandler.loadFile(albumList);
        albumListView.getItems().setAll(albumList.getAlbumReviews());
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
