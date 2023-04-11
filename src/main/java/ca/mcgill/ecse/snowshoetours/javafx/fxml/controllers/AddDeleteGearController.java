package ca.mcgill.ecse.snowshoetours.javafx.fxml.controllers;

import java.io.IOException;

import ca.mcgill.ecse.snowshoetours.controller.GearController;
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

public class AddDeleteGearController {

  @FXML
  private TextField gearNameBox;

  @FXML
  private TextField pricePerWeekBox;

  @FXML
  private Button clearButton;

  @FXML
  private Button addGearButton;

  @FXML
  private ChoiceBox<String> deleteGearChoiceBox;

  @FXML
  private Button GoBackButton;

  @FXML
  private Button deleteGearButton;

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

  /**
   * @author souha Wasif Somji, Souhail El Hayani
   */
  @FXML
  public void initialize() {
    deleteGearChoiceBox.setItems(ViewUtils.getGear());
    deleteGearChoiceBox.setValue(null);
    deleteGearChoiceBox.addEventHandler(SSTFxmlView.REFRESH_EVENT, e -> {
      deleteGearChoiceBox.setItems(ViewUtils.getGear());
      deleteGearChoiceBox.setValue(null);
    });
    SSTFxmlView.getInstance().registerRefreshEvent(deleteGearChoiceBox);
  }

  /**
   * @author Wasif Somji, Souhail El Hayani
   * add a gear
   */
  public void addGearButtonClicked(ActionEvent event) {
    // adding gear
    try {
      String gearName = gearNameBox.getText();
      int PricePerWeek = Integer.parseInt(pricePerWeekBox.getText());
      if (ViewUtils.successful(GearController.addGear(gearName, PricePerWeek))) {
        System.out.println("executed");
        gearNameBox.clear();
        pricePerWeekBox.clear();
      }
    } catch (NumberFormatException e) {
      ViewUtils.showError("Invalid price per week");
    }
  }

  /**
   * @author Wasif Somji, Souhail El Hayani
   * clear all fields
   */
  public void clearButtonClicked(ActionEvent event) {
    gearNameBox.clear();
    pricePerWeekBox.clear();
    deleteGearChoiceBox.setValue(null);
  }

  /**
   * @author Wasif Somji, Souhail El hayani
   * delete a gear
   */
  public void deleteGearButtonClicked(ActionEvent event) {
    // deleting gear
    if(ViewUtils.successful(GearController.deleteGear(deleteGearChoiceBox.getValue()))){
      deleteGearChoiceBox.setValue(null);
      SSTFxmlView.getInstance().refresh();
    }
  }

}
