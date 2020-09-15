package sample;

import javafx.fxml.Initializable;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.scene.layout.StackPane;

import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
//import javafx.scene.control.*;
import javafx.fxml.FXML;
import javafx.event.Event;
import javafx.stage.Stage;

import com.esri.arcgisruntime.mapping.ArcGISMap;
import com.esri.arcgisruntime.mapping.Basemap;
import com.esri.arcgisruntime.mapping.view.MapView;


public class Controller implements Initializable {

  @FXML
  private Button btnSubmit;

  @FXML
  private MenuBar menuBar;

  @FXML
  private MenuItem miExit;

  @FXML
  private StackPane sp;

  private MapView mapView;

  @Override
  public void initialize (URL location, ResourceBundle resources) {
    System.out.println("View is now loaded!");

    //Button someButton = new Button("Some Button");
    //sp.getChildren().add(someButton);

    // create a MapView to display the map and add it to the stack pane
    mapView = new MapView();
    sp.getChildren().add(mapView);
    setupMap();
    // create an ArcGISMap with the default imagery basemap
    //ArcGISMap map = new ArcGISMap(Basemap.createImagery());

    // display the map by setting the map on the map view
    //mapView.setMap(map);
  }

  private void setupMap() {
    if (mapView != null) {
      //Basemap.Type basemapType = Basemap.Type.STREETS_VECTOR;
      Basemap.Type basemapType = Basemap.Type.DARK_GRAY_CANVAS_VECTOR;
      double latitude = 34.02700;
      double longitude = -118.80543;
      int levelOfDetail = 12;
      ArcGISMap map = new ArcGISMap(basemapType, latitude, longitude, levelOfDetail);
      mapView.setMap(map);
    }
  }

  @FXML
  public void handleButtonAction (Event e) {
    System.out.println("Button clicked!");
  }

  @FXML
  void handleExit (Event e) {
    System.out.println("User wants to exit. Please delete your computer.");
    dispose();
    Stage stage = (Stage) menuBar.getScene().getWindow();
    // do what you have to do
    stage.close();
  }

  void dispose () {
    if (mapView != null) {
      mapView.dispose();
    }
  }

}
