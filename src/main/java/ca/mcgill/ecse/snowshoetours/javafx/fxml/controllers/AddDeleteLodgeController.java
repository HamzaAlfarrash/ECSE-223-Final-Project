package ca.mcgill.ecse.snowshoetours.javafx.fxml.controllers;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import ca.mcgill.ecse.snowshoetours.controller.LodgeController;
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
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddDeleteLodgeController {
	
	@FXML
	private TextField lodgeNameBox; 
	
	@FXML
	private TextField addressBox; 
	
	@FXML
    private ChoiceBox<Integer> ratingChoiceBox; 
	
	@FXML
	private Button clearButton;
	   
	@FXML
	private Button clearButton1;
	
	@FXML
	private Button addLodgeButton; 
	
	@FXML
	private ChoiceBox<String> deleteLodgeChoiceBox; 
	
	@FXML
	private Button deleteLodgeButton;
	
	@FXML
	private Button GoBackButton; 
	
	
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
   * @author Yassine Mimet
   */
  public void initialize() {
    deleteLodgeChoiceBox.setItems(ViewUtils.getLodges());
    deleteLodgeChoiceBox.setValue(null);
	deleteLodgeChoiceBox.addEventHandler(SSTFxmlView.REFRESH_EVENT, e->{
	  deleteLodgeChoiceBox.setItems(ViewUtils.getLodges());
	  deleteLodgeChoiceBox.setValue(null);
	});
	List<Integer> ratings = Arrays.asList(1, 2, 3, 4, 5);
	ObservableList<Integer> ratingsObs = FXCollections.observableList(ratings);
	ratingChoiceBox.setItems(ratingsObs);
	ratingChoiceBox.setValue(null);
	ratingChoiceBox.addEventHandler(SSTFxmlView.REFRESH_EVENT, e->{
	  ratingChoiceBox.setItems(ratingsObs);
	  ratingChoiceBox.setValue(null);
    });
	SSTFxmlView.getInstance().registerRefreshEvent(deleteLodgeChoiceBox, ratingChoiceBox);
  }
  
  /**
   * @author Wasif Somji || Yassine Mimet
   */
  public void addLodgeButtonClicked(ActionEvent event) {
    String lodgeName = lodgeNameBox.getText();
    String address = addressBox.getText(); 
    Integer rating = (ratingChoiceBox.getValue()); 
	// adding gear
	if (lodgeName == null || address == null || lodgeName.equals("") || address.equals("")) {
	  ViewUtils.showError("Please enter both the lodge name and its address."); 
	  return; 
	}
	if (rating < 1 || rating > 5) {
	  ViewUtils.showError("Please enter a valid rating."); 
	}
	if (ViewUtils.successful(LodgeController.addLodge(lodgeName, address, rating))) {
	  lodgeNameBox.clear();
	  addressBox.clear(); 
	  ratingChoiceBox.setValue(null);
	  SSTFxmlView.getInstance().refresh();
	}
  }
  
  /**
   * @author Wasif Somji || Yassine Mimet
   */
  public void clearButtonClicked(ActionEvent event) {
	  lodgeNameBox.clear();
	  addressBox.clear();
	  ratingChoiceBox.setValue(null); 
  }
  /**
   * @author Yassine Mimet
   * @param event
   */
  public void clearDeleteChoiceBox(ActionEvent event) {
    deleteLodgeChoiceBox.setValue(null);
  }
  
  /** 
   * @author Wasif Somji || Yassine Mimet
   */
  public void deleteLodgeButtonClicked(ActionEvent event) {
      String deleteLodgeName = deleteLodgeChoiceBox.getValue();
	  if(ViewUtils.successful(LodgeController.deleteLodge(deleteLodgeName))) {
	    deleteLodgeChoiceBox.setValue(null);
	    SSTFxmlView.getInstance().refresh();
	  }
  }
  
}




