package sample;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PropertyPaneController implements Initializable {

  @FXML private VBox vbox;
  @FXML private TableView<TrackProperty> table;

  @Override
  public void initialize (URL location, ResourceBundle resources) {
    System.out.println("Property pane is now loaded!");

    var p1 = new TrackProperty("ID", 102);
    var p2 = new TrackProperty("Course", 94);
    var p3 = new TrackProperty("Speed", 12);

    ObservableList<TrackProperty> properties = FXCollections.observableArrayList();
    properties.add(p1);
    properties.add(p2);
    properties.add(p3);

    var nameCol = new TableColumn<TrackProperty, String>("Name");
    nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));

    var valueCol = new TableColumn<TrackProperty, Integer>("Value");
    valueCol.setCellValueFactory(new PropertyValueFactory<>("value"));

    //table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

    table.setItems(properties);
    table.getColumns().add(nameCol);
    table.getColumns().add(valueCol);

    //    table.setMaxHeight(1200);

    vbox.setVgrow(table, Priority.ALWAYS);

  }

}
