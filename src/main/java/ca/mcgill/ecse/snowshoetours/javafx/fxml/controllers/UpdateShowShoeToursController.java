package ca.mcgill.ecse.snowshoetours.javafx.fxml.controllers;

import java.io.IOException;

import ca.mcgill.ecse.snowshoetours.javafx.fxml.SSTFxmlView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class UpdateShowShoeToursController {
	
	@FXML
	private Label CurrentStartDate;
	@FXML
	private Label CurrentNumberOfWeeks;
	@FXML
	private Label CurrentPricePerGuide;
	@FXML
	private TextField UpdatedStartDate;
	@FXML
	private TextField UpdatedNumberOfWeeks;
	@FXML
	private TextField UpdatedPricePerGuide;
	@FXML
	private Button UpdateButtonStartWeek;
	@FXML
	private Button UpdateButtonNumberOfWeeks;
	@FXML
	private Button UpdateButtonPricePerGuide;
	
	/**
	   * @author Philippe Marchand
	   */
	public void initialize() {
		CurrentStartDate.setText("");
		CurrentNumberOfWeeks.setText("");
		CurrentPricePerGuide.setText("");
		
	}
	
	
	
	
	
	
	
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
  
  
}
