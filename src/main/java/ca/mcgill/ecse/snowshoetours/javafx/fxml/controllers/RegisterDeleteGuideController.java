package ca.mcgill.ecse.snowshoetours.javafx.fxml.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import java.io.IOException;

import ca.mcgill.ecse.snowshoetours.controller.GuideController;
import ca.mcgill.ecse.snowshoetours.controller.ParticipantController;
import ca.mcgill.ecse.snowshoetours.controller.TOParticipantCost;
import ca.mcgill.ecse.snowshoetours.javafx.fxml.SSTFxmlView;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class RegisterDeleteGuideController {
	
	  @FXML
	  private TextField addGuideNameTextField;
	  @FXML
	  private TextField ECITextField;
	  @FXML
	  private Button clearRGSectionButton;
	  @FXML
	  private Button RGButton;
	  @FXML
	  private TextField EmailRGTextField1;
	  @FXML
	  private TextField PasswordRGTextField11;  
	  @FXML
	  private ChoiceBox<TOGuide> deleteGuideChoiceBox;
	  @FXML
	  private Button deleteGuideButton; 
	  
	  @FXML
	  public void initialize() {
	    deleteGuideChoiceBox.addEventHandler(SSTFxmlView.REFRESH_EVENT, e->{
	      deleteGuideChoiceBox.setValue(null);
	    });
	      SSTFxmlView.getInstance().registerRefreshEvent(deleteGuideChoiceBox);
	  }
	  
	  @FXML
	  public void ClearRGClicked(ActionEvent event) {
	    addGuideNameTextField.clear();
	    ECITextField.clear();
	    EmailRGTextField1.clear();
	    PasswordRGTextField11.clear();
	  }
	  
	  @FXML
	  public void RGButtonClicked(ActionEvent event) {
	    String name = addGuideNameTextField.getText();
	    String eci = ECITextField.getText();
	    String email = EmailRGTextField1.getText();
	    String password = PasswordRGTextField11.getText();
	  }
	  
	  @FXML
	  public void deleteGuideClicked(ActionEvent event) {
	      GuideController.deleteGuide(deleteGuideChoiceBox.getValue().getGuideEmail());
	      deleteGuideChoiceBox.setValue(null);
	      SSTFxmlView.getInstance().refresh();
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
