package ca.mcgill.ecse.snowshoetours.javafx.fxml.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import java.io.IOException;
import ca.mcgill.ecse.snowshoetours.controller.ParticipantController;
import ca.mcgill.ecse.snowshoetours.javafx.fxml.SSTFxmlView;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class RegisterDeleteParticipantController {
  @FXML
  private TextField addParticipantNameTextField;
  @FXML
  private TextField addEmailTextField;
  @FXML
  private TextField addPasswordTextField;
  @FXML
  private TextField addEmergencyTextField;
  @FXML
  private TextField addWeekFromTextField;
  @FXML
  private TextField addWeekUntilTextField;
  @FXML
  private TextField addNumberOfWeeksTextField;
  @FXML
  private Button clearButton;
  @FXML
  private Button registerParticipantButton;
  @FXML
  private CheckBox lodgeCheckBox;
  @FXML
  private ChoiceBox<String> deleteParticipantChoiceBox;
  @FXML
  private Button deleteParticipantButton;
  
  /**
   * @author Yassine Mimet
   */
  @FXML
  public void initialize() {
    deleteParticipantChoiceBox.setItems(ViewUtils.getParticipant());
    deleteParticipantChoiceBox.setValue(null);
    deleteParticipantChoiceBox.addEventHandler(SSTFxmlView.REFRESH_EVENT, e->{
      deleteParticipantChoiceBox.setItems(ViewUtils.getParticipant());
      deleteParticipantChoiceBox.setValue(null);
    });
    SSTFxmlView.getInstance().registerRefreshEvent(deleteParticipantChoiceBox);
  }
  
  /**
   * @author Yassine Mimet
   * @param event
   */
  @FXML
  public void clearClicked(ActionEvent event) {
    addParticipantNameTextField.clear();
    addEmailTextField.clear();
    addPasswordTextField.clear();
    addEmergencyTextField.clear();
    addWeekFromTextField.clear();
    addWeekUntilTextField.clear();
    lodgeCheckBox.setSelected(false);
    addNumberOfWeeksTextField.clear();
  }
  
  /**
   * @author Yassine Mimet
   * @param event
   */
  @FXML
  public void clearDeleteChoiceBox(ActionEvent event) {
    deleteParticipantChoiceBox.setValue(null);
  }
  
  /**
   * @author Yassine Mimet
   * @param event
   */
  @FXML
  public void RegisterButtonClicked(ActionEvent event) {
    String name = addParticipantNameTextField.getText();
    String emergencyContact = addEmergencyTextField.getText();
    String email = addEmailTextField.getText();
    String password = addPasswordTextField.getText();
    String waf = addWeekFromTextField.getText();
    String wau = addWeekUntilTextField.getText();
    String nrw = addNumberOfWeeksTextField.getText();
    int weekAvailableFrom = -1;
    int weekAvailableUntil = -1;
    int nrWeeks = -1;
    try {
      weekAvailableFrom = Integer.parseInt(waf);
    } catch (NumberFormatException e) {
      ViewUtils.showError("Enter an Integer");
      return;
    }
    try {
      weekAvailableUntil = Integer.parseInt(wau);
    } catch (NumberFormatException e) {
      ViewUtils.showError("Enter an Integer");
      return;
    }
    try {
      nrWeeks = Integer.parseInt(nrw);
    } catch (NumberFormatException e) {
      ViewUtils.showError("Enter an Integer");
      return;
    }
    boolean lodge = lodgeCheckBox.isSelected();
    
    if (ViewUtils.callController(ParticipantController.registerParticipant(email, password, name, emergencyContact, nrWeeks, weekAvailableFrom, weekAvailableUntil, lodge))) {
      addParticipantNameTextField.clear();
      addEmailTextField.clear();
      addPasswordTextField.clear();
      addEmergencyTextField.clear();
      addWeekFromTextField.clear();
      addWeekUntilTextField.clear();
      lodgeCheckBox.setSelected(false);
      addNumberOfWeeksTextField.clear();
      SSTFxmlView.getInstance().refresh();
    }
  }
  
  /**
   * @author Yassine Mimet
   * @param event
   */
  @FXML
  public void deleteParticipantClicked(ActionEvent event) {
      if(ViewUtils.successful(ParticipantController.deleteParticipant(deleteParticipantChoiceBox.getValue()))) {
        deleteParticipantChoiceBox.setValue(null);
        SSTFxmlView.getInstance().refresh();
      }
  }
  
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
  
}
