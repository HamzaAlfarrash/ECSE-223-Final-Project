package ca.mcgill.ecse.snowshoetours.javafx.fxml.controllers;

import java.io.IOException;

import ca.mcgill.ecse.snowshoetours.controller.LodgeController;
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

public class AddDeleteLodgeController {
	
	@FXML
	private TextField lodgeNameBox; 
	
	@FXML
	private TextField addressBox; 
	
	@FXML
	private ChoiceBox<Integer> ratingChoiceBox; 
	
	@FXML
	private Button clearButton;
	
	@FXML
	private Button addLodgeButton; 
	
	@FXML
	private ChoiceBox<String> deleteLodgeChoiceBox; 
	
	@FXML
	private Button deleteLodgeButton;
	
	@FXML
	private Button GoBackButton; 
	
	
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
  
  String lodgeName = lodgeNameBox.getText();
  String address = addressBox.getText(); 
  int rating = (ratingChoiceBox.getValue()); 
  String deleteLodgeName = deleteLodgeChoiceBox.getValue(); 
  
  public void initialize() {
	    deleteLodgeChoiceBox.addEventHandler(SSTFxmlView.REFRESH_EVENT, e->{
	      deleteLodgeChoiceBox.setValue(null);
	    });
	    SSTFxmlView.getInstance().registerRefreshEvent(deleteLodgeChoiceBox);
	  }
  /**
   * @author Wasif Somji
   */
  public void addLodgeButtonClicked(ActionEvent event) {
	  // adding gear
	  if (lodgeName == null || address == null) {
		  ViewUtils.showError("Please enter both the lodge name and its address."); 
		  return; 
	  }
	  
	  if (rating < 1 || rating > 5) {
		  ViewUtils.showError("Please enter a valid rating."); 
	  }
	  
	  if (ViewUtils.callController(LodgeController.addLodge(lodgeName, address, rating))) {
		  lodgeNameBox.clear();
		  addressBox.clear(); 
		  ratingChoiceBox.setValue(null);
		  SSTFxmlView.getInstance().refresh();
	  }
  }
  /**
   * @author Wasif Somji
   */
  public void clearButtonClicked(ActionEvent event) {
	  lodgeNameBox.clear();
	  addressBox.clear();
	  ratingChoiceBox.setValue(null); 
  }
  
  /** 
   * @author Wasif Somji
   */
  
  public void deleteLodgeButtonClicked(ActionEvent event) {
	  // deleting gear
	  if (deleteLodgeName == null) {
		  ViewUtils.showError("Must select a lodge to delete."); 
	  }
	  LodgeController.deleteLodge(deleteLodgeName);
	  deleteLodgeChoiceBox.setValue(null); 
	  SSTFxmlView.getInstance().refresh();
  }
  
}




