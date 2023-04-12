package ca.mcgill.ecse.snowshoetours.javafx.fxml.controllers;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;

import ca.mcgill.ecse.snowshoetours.controller.SnowShoeTourController;
import ca.mcgill.ecse.snowshoetours.javafx.fxml.SSTFxmlView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class UpdateShowShoeToursController {
	
	@FXML
	private Label CurrentStartDate;
	@FXML
	private Label CurrentNrWeeks;
	@FXML
	private Label CurrentPricePerGuide;
	@FXML
	private DatePicker NewStartDate;
	@FXML
	private TextField NewNumberOfWeeks;
	@FXML
	private TextField NewPricePerGuide;
	@FXML
	private Button UpdateButton;

	
	/**
	   * @author Philippe Marchand
	   */
	public void initialize() {
		Date startDate = ViewUtils.getStartDate();
		LocalDate localStartDate = startDate.toLocalDate();
		int NrWeeks = ViewUtils.getNbrWeeks();
		int PricePerGuide = ViewUtils.getPricePerGuide();
		
		CurrentStartDate.setText(startDate.toString());
		CurrentNrWeeks.setText(Integer.toString(NrWeeks));
		CurrentPricePerGuide.setText(Integer.toString(PricePerGuide));
		
		NewStartDate.setValue(localStartDate);
		NewNumberOfWeeks.setText(Integer.toString(NrWeeks));
		NewPricePerGuide.setText(Integer.toString(PricePerGuide));
	}
	/**
	   * @author Philippe Marchand
	   */
	public void UpdateButtonClicked(ActionEvent event) {
		LocalDate newSD = NewStartDate.getValue();
		Date newSDsql = Date.valueOf(newSD);
		String NewNrWeeks = NewNumberOfWeeks.getText();
		String PricePerGuide = NewPricePerGuide.getText();
		if (!tryParseInt(NewNrWeeks)) {
			ViewUtils.showError("Please enter a valid interger for New Number of Weeks."); 
		  	  return;
		}
		if (!tryParseInt(PricePerGuide)) {
			ViewUtils.showError("Please enter a valid interger for New Price per Guide."); 
		  	  return;
		}
		if (ViewUtils.successful(SnowShoeTourController.updateSnowShoeTour(newSDsql,Integer.parseInt(NewNrWeeks) , Integer.parseInt(PricePerGuide))));
		initialize();
		SSTFxmlView.getInstance().refresh();
	}
	
	 public static boolean tryParseInt(String value) {
	        try {
	            Integer.parseInt(value);
	            return true;
	        } catch (NumberFormatException e) {
	            return false;
	        }
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
