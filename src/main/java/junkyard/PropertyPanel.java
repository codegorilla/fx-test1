package sample;

import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;

public class PropertyPanel extends StackPane {

  @FXML private Button btn1;

  @FXML private TableView<Track> table;

  public PropertyPanel () {
    var loader = new FXMLLoader(getClass().getResource("property_panel.fxml"));
    loader.setRoot(this);
    loader.setController(this);

    try {
      loader.load();
    }
    catch (IOException exception) {
      throw new RuntimeException(exception);
    }

    var t1 = new Track(100, 92, 12.5);
    var t2 = new Track(146, 178, 8);
    var t3 = new Track(192, 34, 15);

    ObservableList<Track> tracks = FXCollections.observableArrayList();
    tracks.add(t1);
    tracks.add(t2);
    tracks.add(t3);

    var idCol = new TableColumn<Track, Integer>("ID");
    idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
    var courseCol = new TableColumn<Track, Double>("Course");
    courseCol.setCellValueFactory(new PropertyValueFactory<>("course"));
    var speedCol = new TableColumn<Track, Double>("Speed");
    speedCol.setCellValueFactory(new PropertyValueFactory<>("speed"));

    table.setItems(tracks);
    table.getColumns().add(idCol);
    table.getColumns().add(courseCol);
    table.getColumns().add(speedCol);

  }

}
