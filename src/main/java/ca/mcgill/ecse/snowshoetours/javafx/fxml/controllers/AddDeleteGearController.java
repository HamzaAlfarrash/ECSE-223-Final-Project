package ca.mcgill.ecse.snowshoetours.javafx.fxml.controllers;

import java.io.IOException;

import ca.mcgill.ecse.snowshoetours.controller.GearController;
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

public class AddDeleteGearController {
	
	@FXML
	private TextField gearNameBox; 
	
	@FXML
	private TextField pricePerWeekBox; 
	
	@FXML
	private Button clearButton;
	
	@FXML
	private Button addGearButton; 
	
	@FXML
	private ChoiceBox<String> deleteGearChoiceBox; 
	
	@FXML
	private Button GoBackButton;
	
	@FXML
	private Button deleteGearButton; 
	
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
  
  String gearName = gearNameBox.getText(); 
  int PricePerWeek = Integer.parseInt(pricePerWeekBox.getText()); 
  String deleteGuideSelector = deleteGearChoiceBox.getValue(); 
  
  public void initialize() {
	    deleteGearChoiceBox.addEventHandler(SSTFxmlView.REFRESH_EVENT, e->{
	      deleteGearChoiceBox.setValue(null);
	    });
	    SSTFxmlView.getInstance().registerRefreshEvent(deleteGearChoiceBox);
	  }
  
  /**
   * @author Wasif Somji
   */
  public void addGearButtonClicked(ActionEvent event) {
	  // adding gear
	  if (gearName == null || pricePerWeekBox == null) {
		  ViewUtils.showError("Please enter both the gear name and its price per week."); 
		  return; 
	  }
	  
	  
	  if (ViewUtils.callController(GearController.addGear(gearName, PricePerWeek))) {
		  gearNameBox.clear();
		  pricePerWeekBox.clear();
		  SSTFxmlView.getInstance().refresh();
	  }
  }
  /**
   * @author Wasif Somji
   */
  public void clearButtonClicked(ActionEvent event) {
	  gearNameBox.clear();
	  pricePerWeekBox.clear(); 
  }
  
  /** 
   * @author Wasif Somji
   */
  
  public void deleteGearButtonClicked(ActionEvent event) {
	  // deleting gear
	  if (deleteGuideSelector == null) {
		  ViewUtils.showError("Must select a gear to delete"); 
	  }
	  GearController.deleteGear(deleteGuideSelector); 
	  deleteGearChoiceBox.setValue(null); 
	  SSTFxmlView.getInstance().refresh();
  }
  
}
