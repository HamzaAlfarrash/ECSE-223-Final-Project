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
	private TexField discountTextField;
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
	    deleteComboBox.setItems(ViewUtils.getCombo());
	    deleteComboChoiceBox.setValue(null);
	    deleteComboChoiceBox.addEventHandler(SSTFxmlView.REFRESH_EVENT, e -> {
	      deleteComboChoiceBox.setItems(ViewUtils.getCombo());
	      deleteComboChoiceBox.setValue(null);
	    });
	    SSTFxmlView.getInstance().registerRefreshEvent(deleteComboChoiceBox);
	  }
	
	/**
	 * @author Hamza Alfarrash
	 * @param event
	 */
	@FXML
	  public void ClearACClicked(ActionEvent event) {
		addComboNameTextField.clear();
		discountTextField.clear();
	  }
	
	/**
	 * @author Hamza Alfarrash
	 * @param event
	 */
	@FXML
	  public void ACButtonClicked(ActionEvent event) {
	    String combo = addComboNameTextField.getText();
	    String discount = discountTextField.getText();

	    if (ViewUtils.callController(GearController.addCombo(combo, discount))) {
	      addComboNameTextField.clear();
		  discountTextField.clear();
	      SSTFxmlView.getInstance().refresh();
	    }
	  }
	
	/**
	 * @author Hamza Alfarrash
	 * @param event
	 */
	@FXML
	  public void deleteComboClicked(ActionEvent event) {
	    GearController.deleteCombo(deleteComboChoiceBox.getValue());
	    deleteComboChoiceBox.setValue(null);
	    SSTFxmlView.getInstance().refresh();
	  }
  
	/**
	 * @author Hamza Alfarrash
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
