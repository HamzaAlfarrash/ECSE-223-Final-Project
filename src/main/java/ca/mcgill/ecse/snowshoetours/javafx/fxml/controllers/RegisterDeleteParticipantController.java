package ca.mcgill.ecse.snowshoetours.javafx.fxml.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import ca.mcgill.ecse.snowshoetours.controller.ParticipantController;
import ca.mcgill.ecse.snowshoetours.controller.TOParticipantCost;
import ca.mcgill.ecse.snowshoetours.javafx.fxml.SSTFxmlView;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

public class RegisterDeleteParticipantController {
  @FXML
  private TextField addParticipantNameTextField;
  @FXML
  private TextField ECITextField;
  @FXML
  private Button clearRPSectionButton;
  @FXML
  private Button RPButton;
  @FXML
  private TextField EmailRPTextField1;
  @FXML
  private TextField PasswordRPTextField11;
  @FXML
  private TextField WAFRPTextField1;
  @FXML
  private TextField WAURPTextField11;
  @FXML
  private CheckBox LGRPCheckBox;
  @FXML
  private TextField NrWeeksRPTextField; 
  
  @FXML
  private ChoiceBox<TOParticipantCost> deleteParticipantChoiceBox;
  @FXML
  private Button deleteParticipantButton;
  
  @FXML
  public void initialize() {
    deleteParticipantChoiceBox.addEventHandler(SSTFxmlView.REFRESH_EVENT, e->{
      //deleteParticipantChoiceBox.setItems(ViewUtils.getParticipant());
      deleteParticipantChoiceBox.setValue(null);
    });
      SSTFxmlView.getInstance().registerRefreshEvent(deleteParticipantChoiceBox);
  }
  
  @FXML
  public void ClearRPClicked(ActionEvent event) {
    addParticipantNameTextField.clear();
    ECITextField.clear();
    EmailRPTextField1.clear();
    PasswordRPTextField11.clear();
    WAFRPTextField1.clear();
    WAURPTextField11.clear();
    LGRPCheckBox.setSelected(false);
    NrWeeksRPTextField.clear();
  }
  
  @FXML
  public void RPButtonClicked(ActionEvent event) {
    String name = addParticipantNameTextField.getText();
    String eci = ECITextField.getText();
    String email = EmailRPTextField1.getText();
    String password = PasswordRPTextField11.getText();
    int waf = Integer.parseInt(WAFRPTextField1.getText());
    int wau = Integer.parseInt(WAURPTextField11.getText());
    int nrWeeks = Integer.parseInt(NrWeeksRPTextField.getText());
    boolean lodge = LGRPCheckBox.isSelected();
    
    /*
    if (ViewUtils.callController(BikeTourPlusFeatureSet3Controller.registerParticipant(email, password, name, eci, nrWeeks, waf, wau, lodge))) {
      addParticipantNameTextField.clear();
      ECITextField.clear();
      EmailRPTextField1.clear();
      PasswordRPTextField11.clear();
      WAFRPTextField1.clear();
      WAURPTextField11.clear();
      LGRPCheckBox.setSelected(false);
      NrWeeksRPTextField.clear();
      BikeTourPlusFxmlView.getInstance().refresh();
    }*/
  }
    
  @FXML
  public void deleteParticipantClicked(ActionEvent event) {
      ParticipantController.deleteParticipant(deleteParticipantChoiceBox.getValue().getParticipantEmail());
      deleteParticipantChoiceBox.setValue(null);
      SSTFxmlView.getInstance().refresh();
  }
  
}
