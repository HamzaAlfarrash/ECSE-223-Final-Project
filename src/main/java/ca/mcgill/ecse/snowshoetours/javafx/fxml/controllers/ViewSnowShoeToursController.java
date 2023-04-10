package ca.mcgill.ecse.snowshoetours.javafx.fxml.controllers;

import java.io.IOException;
import java.util.List;
import ca.mcgill.ecse.snowshoetours.controller.TOSnowShoeTour;
import ca.mcgill.ecse.snowshoetours.controller.TOParticipantCost;
import ca.mcgill.ecse.snowshoetours.javafx.fxml.SSTFxmlView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * 
 * @author souhail el hayani
 *
 */
public class ViewSnowShoeToursController {

  @FXML
  private TableView<TOSnowShoeTour> tourInfo;

  /**
   * @author souhail el hayani
   */
  @FXML
  public void initialize() {
    // createTableColumn(COLUMN_NAME, ATTRIBUTE_NAME)
    tourInfo.getColumns().add(createTableColumn("Tour Id", "id"));
    tourInfo.getColumns().add(createTableColumn("From week", "startWeek"));
    tourInfo.getColumns().add(createTableColumn("To week", "endWeek"));
    tourInfo.getColumns().add(createTableColumn("Guide", "guideEmail"));
    tourInfo.getColumns().add(createParticipantTableColumn());

    tourInfo.setItems(ViewUtils.getTours());
    // overview table if a refreshable element
    tourInfo.addEventHandler(SSTFxmlView.REFRESH_EVENT,
        e -> tourInfo.setItems(ViewUtils.getTours()));

    // register refreshable nodes
    SSTFxmlView.getInstance().registerRefreshEvent(tourInfo);
  }

  // the table column will automatically display the string value of the property for each instance
  // in the table
  /**
   * @author souhail el hayani
   * @param header
   * @param propertyName
   * @return column with a header and values
   */
  public static TableColumn<TOSnowShoeTour, String> createTableColumn(String header,
      String propertyName) {
    TableColumn<TOSnowShoeTour, String> column = new TableColumn<>(header);
    column.setCellValueFactory(new PropertyValueFactory<>(propertyName));
    return column;
  }
  
  //TODO prolly wont work , will need a String :(
  /*public static TableColumn<TOSnowShoeTour, List<TOParticipantCost>> createParticipantTableColumn(String header,
      String propertyName) {
    TableColumn<TOSnowShoeTour, List<TOParticipantCost>> column = new TableColumn<>(header);
    column.setCellValueFactory(new PropertyValueFactory<>(propertyName));
    return column;
  }*/
  
  /**
   * @author souhail el hayani
   * @return column with header "participant" and values of participants of a tour
   */
  public static TableColumn<TOSnowShoeTour,String> createParticipantTableColumn() {
    TableColumn<TOSnowShoeTour, String> column = new TableColumn<>("Participant");
    column.setCellValueFactory(new PropertyValueFactory<>("firstParticipant"));
    return column;
  }

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
