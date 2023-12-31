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
import javafx.stage.Stage;

public class StartFinishCancelParticipantTripController {

  @FXML
  private Button backButton;

  @FXML
  private Button startTripButton;

  @FXML
  private ChoiceBox<String> selectParticipantID;

  @FXML
  private Button cancelTripButton;

  @FXML
  private Button finishTripButton;

  @FXML
  private ChoiceBox<Integer> selectStartWeek;

  @FXML
  private Button clearButton;


  /**
   * @author Yassine Mimet
   * @param event
   */
  public void GoBackButtonClicked(ActionEvent event) {
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

  @FXML
  /**
   * @author Wasif Somji || Yassine Mimet
   */
  public void initialize() {
    selectStartWeek.setItems(ViewUtils.getStartWeek());
    selectStartWeek.setValue(null);
    selectStartWeek.addEventHandler(SSTFxmlView.REFRESH_EVENT, e -> {
      selectStartWeek.setItems(ViewUtils.getStartWeek());
      selectStartWeek.setValue(null);
    });
    selectParticipantID.setItems(ViewUtils.getParticipant());
    selectParticipantID.setValue(null);
    selectParticipantID.addEventHandler(SSTFxmlView.REFRESH_EVENT, e -> {
      selectParticipantID.setItems(ViewUtils.getParticipant());
      selectParticipantID.setValue(null);
    });
    
    SSTFxmlView.getInstance().registerRefreshEvent(selectParticipantID);
  }



  @FXML
  /**
   * @author Wasif Somji || Yassine Mimet
   */// start the trip for a certain number of weeks
  public void startTripButtonClicked() {
    Integer sWeek = (selectStartWeek.getValue());
    if (ViewUtils.successful(SnowShoeTourStateMachineController.startTourForWeek(sWeek))) {
      selectParticipantID.setValue(null);
      selectStartWeek.setValue(null);
      SSTFxmlView.getInstance().refresh();
    }
  }

  @FXML
  /**
   * @author Wasif Somji || Yassine Mimet
   */// cancel the trip
  public void cancelTripButtonClicked() {
    String ParticipantName = selectParticipantID.getValue();
    if (ViewUtils.successful(SnowShoeTourStateMachineController.cancelTrip(ParticipantName))) {
      selectParticipantID.setValue(null);
      selectStartWeek.setValue(null);
      SSTFxmlView.getInstance().refresh();
    }
  }

  @FXML
  /**
   * @author Wasif Somji || Yassine Mimet
   */// finish the trip
  public void finishTripButtonClicked() {
    String ParticipantName = selectParticipantID.getValue();
    if (ViewUtils
        .successful(SnowShoeTourStateMachineController.finishTour(ParticipantName))) {
      selectParticipantID.setValue(null);
      selectStartWeek.setValue(null);
      SSTFxmlView.getInstance().refresh();
    }
  }

  /**
   * @author Yassine Mimet
   */
  public void clearButtonClicked() {
    selectParticipantID.setValue(null);
    selectStartWeek.setValue(null);
  }
}
