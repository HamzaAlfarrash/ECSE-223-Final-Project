package ca.mcgill.ecse.snowshoetours.javafx.fxml.controllers;

import java.io.IOException;

import ca.mcgill.ecse.snowshoetours.controller.LodgeController;
import ca.mcgill.ecse.snowshoetours.controller.SnowShoeTourStateMachineController;
import ca.mcgill.ecse.snowshoetours.javafx.fxml.SSTFxmlView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
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
	
	@FXML
	private ChoiceBox<Integer> startWeek; // need to implement this on UI, won't work right now
	
	@FXML
	private Button clearButton; // need to implement this on UI, won't work right now
	
	
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
    	selectParticipantID.setItems(ViewUtils.getParticipant());
        selectParticipantID.setValue(null);
        selectParticipantID.addEventHandler(SSTFxmlView.REFRESH_EVENT, e->{
          selectParticipantID.setItems(ViewUtils.getParticipant());
          selectParticipantID.setValue(null);
        });
          SSTFxmlView.getInstance().registerRefreshEvent(selectParticipantID);
      }
    
    
    
    @FXML
    /**
     * @author Wasif Somji
     */// start the trip for a certain number of weeks
    public void startTripButtonClicked() {
  	  int numWeeks =(startWeek.getValue()); 
  	if(ViewUtils.successful(SnowShoeTourStateMachineController.startTourForWeek(numWeeks))) {
  	    selectParticipantID.setValue(null);
  	    SSTFxmlView.getInstance().refresh();
  	  }
    }
    
    @FXML
    /**
     * @author Wasif Somji
     */// cancel the trip
    public void cancelTripButtonClicked() {
  	  String deleteParticipantName = selectParticipantID.getValue(); 
  	if(ViewUtils.successful(SnowShoeTourStateMachineController.cancelTrip(deleteParticipantName))) {
  	    selectParticipantID.setValue(null);
  	    SSTFxmlView.getInstance().refresh();
  	  }
    }
    
    @FXML
    /**
     * @author Wasif Somji
     */// finish the trip
    public void finishTrip() {
  	  String finishParticipantName = selectParticipantID.getValue(); 
  	if(ViewUtils.successful(SnowShoeTourStateMachineController.finishTour(finishParticipantName))) {
  	    selectParticipantID.setValue(null);
  	    SSTFxmlView.getInstance().refresh();
  	  }
    }
}
