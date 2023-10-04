package ui;



import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;

import core.AlbumReview;
import core.AlbumReviewList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import json.LoadFromFile;
import json.WriteToFile;


public class AlbumReviewAppController implements Initializable {

    @FXML
    private TextField albumName, albumRating;

    @FXML
    private Button sortByNameButton, sortByRatingButton, addButton, removeButton;

  
    @FXML
    private ListView<AlbumReview> albumListView;

  
    private AlbumReviewList albumList;

    private int selected;

    public void initAlbumListView(){
        try {
            albumList = LoadFromFile.loadFromFile();
        } catch (StreamReadException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (DatabindException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        ObservableList<AlbumReview> observableAlbums = FXCollections.observableArrayList(albumList.getAlbumReviews());
        albumListView.getItems().setAll(observableAlbums);
    }

    /**
     * adds album to list view in ui
     */
    @FXML
    void addRating(ActionEvent event){
        if(albumName.getText().isEmpty() || albumRating.getText().isEmpty()){
            Alert alertInfo = new Alert(Alert.AlertType.INFORMATION);
            alertInfo.setTitle("Warning");
            alertInfo.setContentText("You must enter an album and a rating");
            alertInfo.showAndWait();
        }
        else{
            try{
                AlbumReview album = new AlbumReview(albumName.getText(), Integer.parseInt(albumRating.getText()));
                albumList.addAlbumReview(album);
                albumListView.getItems().setAll(albumList.getAlbumReviews());
                albumName.setText("");
                albumRating.setText("");
                handleSave();

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
        albumList.removeAlbumReview(selected);
        handleSave();
        albumListView.getItems().setAll(albumList.getAlbumReviews());
    }

    /**
     * sorts album by name in ui
     */
    @FXML
    void sortByName(ActionEvent event){
        albumList.sortName();
        albumListView.getItems().setAll(albumList.getAlbumReviews());
    }

    /**
     * sorts album by rating in ui
     */
    @FXML
    void sortByRating(ActionEvent event){
        albumList.sortRating();
        albumListView.getItems().setAll(albumList.getAlbumReviews());
    }

    /**
     * Writes albumList to file
     */
    
    void handleSave(){
        WriteToFile.writeToFile(albumList);
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
