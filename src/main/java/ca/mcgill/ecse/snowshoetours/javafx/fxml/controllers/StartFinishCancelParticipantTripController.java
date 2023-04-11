package ca.mcgill.ecse.snowshoetours.javafx.fxml.controllers;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;

public class StartFinishCancelParticipantTripController {
	@FXML
	private Button backButton; 
	
	@FXML
	private Button startTripButton;
	
	@FXML
	private ChoiceBox<String> selectParticipantID; 
	
	@FXML
	private Button cancelTripButton;
	
	@FXML
	private Button finishTripButton; 
	
	/**
	 * @author Yassine Mimet
	 * @param event
	 */
    public void GoBackButtonClicked(ActionEvent event) {
      try {
        Parent ViewSnowShoeToursDetailedParent = FXMLLoader.load(getClass().getResource("../MainPage.fxml"));
        Scene ViewSnowShoeToursDetailedParentScene = new Scene(ViewSnowShoeToursDetailedParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(ViewSnowShoeToursDetailedParentScene);
        window.show();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    
    @FXML
    /**
     * @author Wasif Somji
     */
    public void initialize() {
  	  // to be completed 
    }
    
    @FXML
    /**
     * @author Wasif Somji
     */
    public void startTrip() {
  	  // to be completed
    }
    
    @FXML
    /**
     * @author Wasif Somji
     */
    public void cancelTrip() {
  	  // to be completed
    }
    
    @FXML
    /**
     * @author Wasif Somji
     */
    public void finishTrip() {
  	  // to be completed
    }
}
