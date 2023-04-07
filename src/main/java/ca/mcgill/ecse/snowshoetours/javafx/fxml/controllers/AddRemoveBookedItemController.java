package ca.mcgill.ecse.snowshoetours.javafx.fxml.controllers;

import java.io.IOException;
import ca.mcgill.ecse.snowshoetours.javafx.fxml.SSTFxmlView;
import ca.mcgill.ecse.snowshoetours.model.Combo;
import ca.mcgill.ecse.snowshoetours.model.Gear;
import ca.mcgill.ecse.snowshoetours.model.Participant;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class AddRemoveBookedItemController {
  
  //TODO add transfer objetcs, this is NOT CORRECT
  @FXML
  private ChoiceBox<Gear> gearName;
  
  @FXML
  private ChoiceBox<Combo> comboName;
  
  @FXML
  private ChoiceBox<Participant> participantName;
  
  @FXML
  private Button add;
  
  @FXML
  private TableView<Participant> gearAndComboListForSelectedParticipant;
  
  @FXML
  public void initialize() {
    gearName.addEventHandler(SSTFxmlView.REFRESH_EVENT, e->{
      gearName.setItems(ViewUtils.getGear());
    });
    comboName.addEventHandler(SSTFxmlView.REFRESH_EVENT, e->{
      comboName.setItems(ViewUtils.getCombo());
    });
    participantName.addEventHandler(SSTFxmlView.REFRESH_EVENT, e->{
      participantName.setItems(ViewUtils.getParticipant());
    });
      SSTFxmlView.getInstance().registerRefreshEvent(gearName,participantName,comboName);
  }
  
  /**
   * @author souhail el hayani
   */
  public void add() {
    //after adding gear or combo to participant, its possible to view participant info
    
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