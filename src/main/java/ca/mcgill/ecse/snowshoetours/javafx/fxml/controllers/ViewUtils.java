package ca.mcgill.ecse.snowshoetours.javafx.fxml.controllers;

import java.util.ArrayList;
import java.util.List;

import ca.mcgill.ecse.snowshoetours.application.SnowShoeToursApplication;
import ca.mcgill.ecse.snowshoetours.controller.GearController;
import ca.mcgill.ecse.snowshoetours.controller.LodgeController;
import ca.mcgill.ecse.snowshoetours.controller.GuideController;
import ca.mcgill.ecse.snowshoetours.controller.ParticipantController;
import ca.mcgill.ecse.snowshoetours.controller.SnowShoeTourController;
import ca.mcgill.ecse.snowshoetours.controller.TOCombo;
import ca.mcgill.ecse.snowshoetours.controller.TOGears;
import ca.mcgill.ecse.snowshoetours.controller.TOLodge;
import ca.mcgill.ecse.snowshoetours.controller.TOGuide;
import ca.mcgill.ecse.snowshoetours.controller.TOParticipant;
import ca.mcgill.ecse.snowshoetours.controller.TOSnowShoeTour;
import ca.mcgill.ecse.snowshoetours.javafx.fxml.SSTFxmlView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ViewUtils {

  /** Calls the controller and shows an error, if applicable. */
  public static boolean callController(String result) {
    if (result.isEmpty()) {
      SSTFxmlView.getInstance().refresh();
      return true;
    }
    showError(result);
    return false;
  }

  /** Calls the controller and returns true on success. This method is included for readability. */
  public static boolean successful(String controllerResult) {
    return callController(controllerResult);
  }

  /**
   * Creates a popup window.
   *
   * @param title: title of the popup window
   * @param message: message to display
   */
  public static void makePopupWindow(String title, String message) {
    Stage dialog = new Stage();
    dialog.initModality(Modality.APPLICATION_MODAL);
    VBox dialogPane = new VBox();

    // create UI elements
    Text text = new Text(message);
    Button okButton = new Button("OK");
    okButton.setOnAction(a -> dialog.close());

    // display the popup window
    int innerPadding = 10; // inner padding/spacing
    int outerPadding = 100; // outer padding
    dialogPane.setSpacing(innerPadding);
    dialogPane.setAlignment(Pos.CENTER);
    dialogPane.setPadding(new Insets(innerPadding, innerPadding, innerPadding, innerPadding));
    dialogPane.getChildren().addAll(text, okButton);
    Scene dialogScene = new Scene(dialogPane, outerPadding + 5 * message.length(), outerPadding);
    dialog.setScene(dialogScene);
    dialog.setTitle(title);
    dialog.show();
  }

  public static void showError(String message) {
    makePopupWindow("Error", message);
  }

  /**
   * @author souhail el hayani
   * @return
   */
  public static ObservableList<String> getGear() {
    List<TOGears> list = GearController.getGears();
    List<String> names = new ArrayList<>();
    for (TOGears gear : list) {
      names.add(gear.getName());
    }
    ObservableList<String> listNames = FXCollections.observableList(names);
    return listNames;
  }

  /**
   * @author souhail el hayani
   * @return
   */
  public static ObservableList<String> getCombo() {
    List<TOCombo> list = GearController.getCombos();
    List<String> names = new ArrayList<>();
    for (TOCombo combo : list) {
      names.add(combo.getName());
    }
    return FXCollections.observableList(names);
  }
  
  public static ObservableList<String> getGuide() {
    List<TOGuide> list = GuideController.getGuides();
    List<String> names = new ArrayList<>();
    for (TOGuide guide : list) {
      names.add(guide.getGuideAccountName());
    }
    return FXCollections.observableList(names);
  }

  /**
   * @author souhail el hayani
   * @return
   */
  public static ObservableList<String> getParticipant() {
    List<TOParticipant> list = ParticipantController.getParticipants();
    List<String> names = new ArrayList<>();
    for (TOParticipant par : list) {
      names.add(par.getParticipantAccountName());
    }
    return FXCollections.observableList(names);
  }

  /**
   * @author souhail el hayani
   * @return
   */
  public static ObservableList<TOSnowShoeTour> getTours() {
    List<TOSnowShoeTour> tours = SnowShoeTourController.getSnowShoeTours();
    return FXCollections.observableList(tours);
  }
  
  /**
   * @author Yassine Mimet
   * @return an Observable list of the names of the lodges
   */
  public static ObservableList<String> getLodges(){
    List<TOLodge> lodges = LodgeController.getLodges();
    List<String> lodgeNames = new ArrayList<>();
    for(TOLodge lodge : lodges) {
      lodgeNames.add(lodge.getLodgeName());
    }
    return FXCollections.observableList(lodgeNames);
  }
 

}
