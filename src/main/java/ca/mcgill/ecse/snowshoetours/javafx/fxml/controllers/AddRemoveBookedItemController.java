package ca.mcgill.ecse.snowshoetours.javafx.fxml.controllers;

import java.io.IOException;
import ca.mcgill.ecse.snowshoetours.controller.ParticipantController;
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

public class AddRemoveBookedItemController {
  
  //TODO add transfer objetcs, this is NOT CORRECT
  @FXML
  private ChoiceBox<String> gearName;
  
  @FXML
  private ChoiceBox<String> comboName;
  
  @FXML
  private ChoiceBox<String> participantName;
  
  @FXML
  private Button add;
  
  @FXML
  /**
   * @author souhail el hayani
   */
  public void initialize() {
    gearName.addEventHandler(SSTFxmlView.REFRESH_EVENT, e->{
      gearName.setItems(ViewUtils.getGear());
      gearName.setValue(null);
    });
    comboName.addEventHandler(SSTFxmlView.REFRESH_EVENT, e->{
      comboName.setItems(ViewUtils.getCombo());
      comboName.setValue(null);
    });
    participantName.addEventHandler(SSTFxmlView.REFRESH_EVENT, e->{
      participantName.setItems(ViewUtils.getParticipant());
      participantName.setValue(null);
    });
      SSTFxmlView.getInstance().registerRefreshEvent(gearName,participantName,comboName);
  }
  
  /**
   * @author souhail el hayani
   */
  public void add() {
    //adding gear or combo to participant
    if(!gearName.getValue().isEmpty() && !comboName.getValue().isEmpty()) {
      ViewUtils.showError("Only select one item, either a gear or a combo, but not both");
      return;
    }
    if(gearName.getValue().isEmpty() && ViewUtils.successful(ParticipantController.addBookableItemToParticipant(participantName.getValue(), comboName.getValue()))) {
      //tries to add a combo
      comboName.setValue(null);
      participantName.setValue(null);
    }
    else if(!comboName.getValue().isEmpty() && !ViewUtils.successful(ParticipantController.addBookableItemToParticipant(participantName.getValue(), gearName.getValue()))) {
      //tries to add a gear
      comboName.setValue(null);
      participantName.setValue(null);
    }
    
    
  }
  
  /**
   * @author souhail el hayani
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