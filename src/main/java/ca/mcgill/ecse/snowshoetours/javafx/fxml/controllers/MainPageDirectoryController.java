package ca.mcgill.ecse.snowshoetours.javafx.fxml.controllers;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.Parent;
import javafx.scene.Node;

public class MainPageDirectoryController {
	
	private Button GoViewSnowShoeToursDetailedButton;
	private Button GoUpdateShowShoeToursButton;
	private Button GoAddDeleteLodgeButton;
	private Button GoInitiateTheShowShoeingTourCreationButton;
	private Button GoAddDeleteComboButton;
	private Button GoAddDeleteGearButton;
	private Button GoAddDeleteGearToComboButton;
	private Button GoAddRemoveBookedItemButton;
	private Button GoRegisterDeleteGuideButton;
	private Button GoRegisterDeleteParticipantButton;
	private Button GoPayParticipantButton;
	private Button GoStartFinishCancelParticipantTripButton;
	
	public void GoViewSnowShoeToursDetailedButtonClicked(ActionEvent event) {
		try {
		   Parent ViewSnowShoeToursDetailedParent =FXMLLoader.load(getClass().getResource("ViewSnowShoeTours.fxml"));
		   Scene ViewSnowShoeToursDetailedParentScene = new Scene(ViewSnowShoeToursDetailedParent);
		   //Stage window = (Stage)((Node)event.getSource().get)
		    } catch (IOException e) {
		      e.printStackTrace();
		    }
	}
	}
