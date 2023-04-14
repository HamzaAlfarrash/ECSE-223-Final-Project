package ca.mcgill.ecse.snowshoetours.javafx.fxml.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import java.io.IOException;

import ca.mcgill.ecse.snowshoetours.controller.GearController;
import ca.mcgill.ecse.snowshoetours.javafx.fxml.SSTFxmlView;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class AddDeleteComboController {

  @FXML
  private TextField addComboNameTextField;
  @FXML
  private TextField discountTextField;
  @FXML
  private Button clearACSectionButton;
  @FXML
  private Button ACButton;
  @FXML
  private Button back;
  @FXML
  private ChoiceBox<String> deleteComboChoiceBox;
  @FXML
  private Button deleteComboButton;


  /**
   * @author Hamza Alfarrash
   */
  @FXML
  public void initialize() {
    deleteComboChoiceBox.setItems(ViewUtils.getCombo());
    deleteComboChoiceBox.setValue(null);
    deleteComboChoiceBox.addEventHandler(SSTFxmlView.REFRESH_EVENT, e -> {
      deleteComboChoiceBox.setItems(ViewUtils.getCombo());
      deleteComboChoiceBox.setValue(null);
    });
    SSTFxmlView.getInstance().registerRefreshEvent(deleteComboChoiceBox);
  }

  /**
   * @author Hamza Alfarrash
   * Clears the input fields for adding a new combo.
   *
   * @param event clearACSectionButton triggers this method
   */
  @FXML
  public void ClearACClicked(ActionEvent event) {
    addComboNameTextField.clear();
    discountTextField.clear();
  }

  /**
   * @author Hamza Alfarrash, souhail
   * Adds a new combo with the provided input data.
   *
   * @param event AC Button triggers this method
   */
  @FXML
  public void ACButtonClicked(ActionEvent event) {
    try {
      String combo = addComboNameTextField.getText();
      int discount = Integer.parseInt(discountTextField.getText());

      if (ViewUtils.callController(GearController.addCombo(combo, discount))) {
        addComboNameTextField.clear();
        discountTextField.clear();
        SSTFxmlView.getInstance().refresh();    
      }
    } catch (NumberFormatException e) {
      ViewUtils.showError("Invalid discount format");
    }

  }

  /**
   * @author Hamza Alfarrash
   * Deletes the selected combo from the list.
   *
   * @param event Delete Combo Button triggeres this method
   */
  @FXML
  public void deleteComboClicked(ActionEvent event) {
    GearController.deleteCombo(deleteComboChoiceBox.getValue());
    deleteComboChoiceBox.setValue(null);
    SSTFxmlView.getInstance().refresh();
  }

  /**
   * @author Yassine Mimet
   * Switches to the main page view when the "back" button is clicked.
   *
   * @param event Go Back Button triggers this method
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


}
