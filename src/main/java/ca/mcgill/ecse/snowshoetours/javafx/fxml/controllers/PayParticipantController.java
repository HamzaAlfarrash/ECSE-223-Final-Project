package ca.mcgill.ecse.snowshoetours.javafx.fxml.controllers;

import java.io.IOException;
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

public class PayParticipantController {

  @FXML
  private ChoiceBox<String> payParticipantChoiceBox;
  @FXML
  private Button payButton;
  @FXML
  private TextField codeTextField;
  
  /**
   * @author Yassine Mimet
   * @param event
   */
  public void GoBack(ActionEvent event) {
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

  /**
   * @author Martin Eskaros
   *
   */
  public void initialize() {
    payParticipantChoiceBox.setItems(ViewUtils.getParticipant());
    payParticipantChoiceBox.setValue(null);
    payParticipantChoiceBox.addEventHandler(SSTFxmlView.REFRESH_EVENT, e -> {
      payParticipantChoiceBox.setItems(ViewUtils.getParticipant());
      payParticipantChoiceBox.setValue(null);
    });
    SSTFxmlView.getInstance().registerRefreshEvent(payParticipantChoiceBox);
  }
  
  @FXML
  /**
   * @author Martin Eskaros
   * @param event
   */
  public void PayButtonClicked(ActionEvent event) {
    String participantEmail = payParticipantChoiceBox.getValue();
    String authorizationCode = codeTextField.getText();
    if(ViewUtils.successful(SnowShoeTourStateMachineController.confirmPayement(participantEmail, authorizationCode))) {
      payParticipantChoiceBox.setValue(null);
      codeTextField.clear();
      SSTFxmlView.getInstance().refresh();
    }
  }
  
  @FXML
  public void clearButtonClicked(ActionEvent event) {
    payParticipantChoiceBox.setValue(null);
    codeTextField.clear();
  }
}
