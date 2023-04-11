package ca.mcgill.ecse.snowshoetours.javafx.fxml.controllers;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import ca.mcgill.ecse.snowshoetours.controller.GearController;
import ca.mcgill.ecse.snowshoetours.javafx.fxml.SSTFxmlView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;

public class AddRemoveGearToComboController {
  
  @FXML
  private ChoiceBox<String> comboNameChoiceBox;
  
  @FXML
  private ChoiceBox<String> gearNameChoiceBox;
  
  @FXML
  private ChoiceBox<String> comboNameChoiceBox1;
  
  @FXML
  private ChoiceBox<String> gearNameChoiceBox1;
  
  @FXML
  private Button addGearButton;
  
  @FXML
  private Button removeGearButton;
  
  @FXML
  private Button addClearButton;
  
  @FXML
  private Button deleteClearButton;
  
  /**
   * @author Yassine Mimet
   */
  public void initialize() {
    comboNameChoiceBox.setItems(ViewUtils.getCombo());
    comboNameChoiceBox.setValue(null);
    comboNameChoiceBox.addEventHandler(SSTFxmlView.REFRESH_EVENT, e->{
      comboNameChoiceBox.setItems(ViewUtils.getCombo());
      comboNameChoiceBox.setValue(null);
    });
    comboNameChoiceBox1.setItems(ViewUtils.getCombo());
    comboNameChoiceBox1.setValue(null);
    comboNameChoiceBox1.addEventHandler(SSTFxmlView.REFRESH_EVENT, e->{
      comboNameChoiceBox1.setItems(ViewUtils.getCombo());
      comboNameChoiceBox1.setValue(null);
    });
    gearNameChoiceBox.setItems(ViewUtils.getGear());
    gearNameChoiceBox.setValue(null);
    gearNameChoiceBox.addEventHandler(SSTFxmlView.REFRESH_EVENT, e->{
      gearNameChoiceBox.setItems(ViewUtils.getGear());
      gearNameChoiceBox.setValue(null);
    });
    gearNameChoiceBox1.setItems(ViewUtils.getGear());
    gearNameChoiceBox1.setValue(null);
    gearNameChoiceBox1.addEventHandler(SSTFxmlView.REFRESH_EVENT, e->{
      gearNameChoiceBox1.setItems(ViewUtils.getGear());
      gearNameChoiceBox1.setValue(null);
    });
  SSTFxmlView.getInstance().registerRefreshEvent(comboNameChoiceBox, comboNameChoiceBox1, gearNameChoiceBox, gearNameChoiceBox1);
  }
  
  /**
   * @author Yassine Mimet
   * @param event
   */
  public void addButtonClicked(ActionEvent event) {
    String comboName = comboNameChoiceBox.getValue();
    String gearName = gearNameChoiceBox.getValue();
    if(ViewUtils.successful(GearController.addGearToCombo(gearName, comboName))) {
      comboNameChoiceBox.setValue(null);
      gearNameChoiceBox.setValue(null);
      SSTFxmlView.getInstance().refresh();
    }
  }
  
 /**
  * @author Yassine Mimet
  * @param event
  */
  public void deleteButtonClicked(ActionEvent event) {
    String comboName = comboNameChoiceBox1.getValue();
    String gearName = gearNameChoiceBox1.getValue();
    if(ViewUtils.successful(GearController.removeGearFromCombo(gearName, comboName))) {
      comboNameChoiceBox1.setValue(null);
      gearNameChoiceBox1.setValue(null);
      SSTFxmlView.getInstance().refresh();
    }
  }
  
  /**
   * @author Yassine Mimet
   * @param event
   */
  public void clearAddButtonClicked(ActionEvent event) {
    comboNameChoiceBox.setValue(null);
    gearNameChoiceBox.setValue(null);
  }
  
  /**
   * @author Yassine Mimet
   * @param event
   */
  public void clearRemoveButtonClicked(ActionEvent event) {
    comboNameChoiceBox1.setValue(null);
    gearNameChoiceBox1.setValue(null);
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
