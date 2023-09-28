package ui;



import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import core.AlbumReview;
import core.AlbumReviewList;
import core.FileHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class AlbumReviewAppController implements Initializable {

    @FXML
    private TextField albumName, albumRating;

    @FXML
    private Button sortByNameButton, sortByRatingButton, addButton, removeButton, openAlbum;

  
    @FXML
    private ListView<AlbumReview> albumListView;

  
    private AlbumReviewList albumList;

    private int selected;

    public void initAlbumListView(){
        albumList = new AlbumReviewList();
        ObservableList<AlbumReview> observableAlbums = FXCollections.observableArrayList(albumList.getAlbumReviews());
        albumListView.getItems().setAll(observableAlbums);
        handleLoad();
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
                albumList.addAlbumReview(albumName.getText(), Integer.parseInt(albumRating.getText()));
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

    @FXML
    void openButton(ActionEvent event){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("OpenAlbumReviewApp.fxml"));
            Parent root = (Parent) loader.load();
            Stage stage = new Stage();
            stage.setTitle("The Album");
            stage.setScene(new Scene(root));
            stage.show();
        }
        catch (Exception e){
            System.out.println("Cant open album");
        }
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
        FileHandler.writeToFile(albumList);
    }

    /**
     * Loads albumList from file and sets it to ListView ui
     */
    
    void handleLoad(){
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
