package ca.mcgill.ecse.snowshoetours.javafx.fxml.controllers;

import java.io.IOException;
import java.util.List;

import ca.mcgill.ecse.snowshoetours.controller.ParticipantController;
import ca.mcgill.ecse.snowshoetours.controller.SnowShoeTourStateMachineController;
import ca.mcgill.ecse.snowshoetours.controller.TOParticipant;
import ca.mcgill.ecse.snowshoetours.javafx.fxml.SSTFxmlView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;

public class PayParticipantController {

  @FXML
  private ChoiceBox<String> payParticipantChoiceBox;
  @FXML
  private Button payButton;

  public void GoBack(ActionEvent event) {
    try {
      Parent ViewSnowShoeToursDetailedParent =
              FXMLLoader.load(getClass().getResource("../MainPage.fxml"));
      Scene ViewSnowShoeToursDetailedParentScene = new Scene(ViewSnowShoeToursDetailedParent);
      Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
      window.setScene(ViewSnowShoeToursDetailedParentScene);
      window.show();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

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
  public void PayButtonClicked(ActionEvent event) {
    String participantEmail = payParticipantChoiceBox.getValue();
    List<TOParticipant> list1 = ParticipantController.getParticipants();
    if (participantEmail != null) {
      for (TOParticipant par : list1){
        if (par.getParticipantAccountName().equals(participantEmail)){
          SnowShoeTourStateMachineController.confirmPayement(participantEmail, par.getAuthorizationCode());
        }
      }
    }
  }
}

