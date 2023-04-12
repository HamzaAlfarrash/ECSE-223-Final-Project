package ca.mcgill.ecse.snowshoetours.javafx.fxml.controllers;

import java.io.IOException;
import java.util.Objects;
import ca.mcgill.ecse.snowshoetours.controller.SnowShoeTourStateMachineController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class InitiateSnowShoingTourCreationController {

  @FXML
  private Button initiateButton;
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

  public void InitiateButtonClicked(ActionEvent event) {
    if(ViewUtils.successful(SnowShoeTourStateMachineController.initiate())) {
      try {
        Parent mainPageParent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../MainPage.fxml")));
        Scene mainPageScene = new Scene(mainPageParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(mainPageScene);
        window.show();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }
}