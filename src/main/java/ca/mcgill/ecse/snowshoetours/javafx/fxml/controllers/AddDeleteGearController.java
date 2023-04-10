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
	private ChoiceBox<String> GearSelector; 
	
	@FXML
	private Button backButton;
	
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
  
  /**
   * @author Wasif Somji
   */
  public void addGear() {
	  // to be completed
  }
  
  /** 
   * @author Wasif Somji
   */
  
  public void removeGear() {
	  // to be completed
  }
  
}
