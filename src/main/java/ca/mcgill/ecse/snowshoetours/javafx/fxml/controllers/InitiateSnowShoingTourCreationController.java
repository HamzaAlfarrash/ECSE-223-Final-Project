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
  // This method is called when the "Go Back" button is clicked and switches the scene to the main page
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

  /**
   * @author Martin Eskaros
   * This method is called when the "Initiate" button is clicked and it calls the initiate method from
   * the SnowShoeTourStateMachineController class, which updates the state of the tour creation process. If the initiation
   * was successful, this method switches the scene to the main page.
   *
   * @param event triggered by clicking the "Initiate" button
   */
  public void InitiateButtonClicked(ActionEvent event) {
    // Call the initiate method from the SnowShoeTourStateMachineController class and check if it was successful
    if(ViewUtils.successful(SnowShoeTourStateMachineController.initiate())) {
      try {
        // Load the MainPage.fxml file and create a new scene with its contents
        Parent mainPageParent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../MainPage.fxml")));
        Scene mainPageScene = new Scene(mainPageParent);
        // Get the stage from the event source
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(mainPageScene); //set its scene to the MainPage scene
        window.show(); //show the stage
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }
}