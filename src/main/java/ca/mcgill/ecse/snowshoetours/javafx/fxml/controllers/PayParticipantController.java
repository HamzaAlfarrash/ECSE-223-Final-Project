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


  // This field represents a JavaFX ChoiceBox element with String items,
  // which allows the user to select a participant to pay.
  @FXML
  private ChoiceBox<String> payParticipantChoiceBox;

  // This field represents a JavaFX Button element, which allows the user
  // to initiate the payment process.
  @FXML
  private Button payButton;

  // This field represents a JavaFX TextField element, which allows the
  // user to enter an authorization code for payment.
  @FXML
  private TextField codeTextField;
  
  /**
   * @author Yassine Mimet
   * This method is associated with a button click event and navigates the user
   * back to the main page of the application.
   *
   * @param event back button triggers this method
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
   * This method sets up the choice box with a list of participants
   * and registers an event handler to refresh the list when necessary.
   */
  public void initialize() {
    // Set the choice box items to a list of participants
    payParticipantChoiceBox.setItems(ViewUtils.getParticipant());
    // Clear the choice box selection
    payParticipantChoiceBox.setValue(null);
    // Register an event handler to refresh the list when necessary
    payParticipantChoiceBox.addEventHandler(SSTFxmlView.REFRESH_EVENT, e -> {
      payParticipantChoiceBox.setItems(ViewUtils.getParticipant());
      payParticipantChoiceBox.setValue(null);
    });
    // Register the choice box with the view to receive refresh events
    SSTFxmlView.getInstance().registerRefreshEvent(payParticipantChoiceBox);
  }
  
  @FXML
  /**
   * @author Martin Eskaros
   * This method  performs the payment process when the pay button is clicked. It retrieves the selected
   * participant and authorization code from the corresponding fields, calls
   * the appropriate controller method to confirm payment, and clears the fields
   * if payment is successful.
   *
   * @param event pay button triggers this method
   */
  public void PayButtonClicked(ActionEvent event) {
    // Get the selected participant from the choice box
    String participantEmail = payParticipantChoiceBox.getValue();
    // Get the authorization code from the text field
    String authorizationCode = codeTextField.getText();
    if(ViewUtils.successful(SnowShoeTourStateMachineController.confirmPayement(participantEmail, authorizationCode))) {
      // Clear the choice box selection and the authorization code text field
      payParticipantChoiceBox.setValue(null);
      codeTextField.clear();
      SSTFxmlView.getInstance().refresh();
    }
  }
  
  @FXML
  /** @author Martin Eskaros
   * This method clears the selected
   * participant and authorization code fields.
   *
   * @param event clear button triggers this method
   */
  public void clearButtonClicked(ActionEvent event) {
    payParticipantChoiceBox.setValue(null);
    codeTextField.clear();
  }
}
