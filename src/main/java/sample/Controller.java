package sample;

import javafx.fxml.Initializable;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.scene.control.SplitPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
//import javafx.scene.control.*;
import javafx.fxml.FXML;
import javafx.event.Event;
import javafx.stage.Stage;

import com.esri.arcgisruntime.mapping.ArcGISMap;
import com.esri.arcgisruntime.mapping.Basemap;
import com.esri.arcgisruntime.mapping.Viewpoint;

import com.esri.arcgisruntime.geometry.Point;

import com.esri.arcgisruntime.mapping.view.MapView;

public class Controller implements Initializable {

  @FXML
  private Button btnSubmit;

  @FXML
  private MenuBar menuBar;

  @FXML
  private MenuItem miExit;

  @FXML
  private SplitPane sp;

  @FXML
  private VBox vb;

  @FXML
  private Button btnTest1;

  @FXML
  private MapView mapView;

  @FXML
  private Button btnZoomIn;

  @FXML
  private Button btnZoomOut;

  @Override
  public void initialize (URL location, ResourceBundle resources) {
    System.out.println("View is now loaded!");
    sp.setResizableWithParent(vb, Boolean.FALSE);
    //sp.setDividerPositions(0.25);
    setupMap();
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
  public void handleZoomIn (Event e) {
    Viewpoint current = mapView.getCurrentViewpoint(Viewpoint.Type.CENTER_AND_SCALE);
    Viewpoint zoomedIn = new Viewpoint((Point) current.getTargetGeometry(), current.getTargetScale() / 2.0);
    mapView.setViewpointAsync(zoomedIn);
  }

  @FXML
  public void handleZoomOut (Event e) {
    Viewpoint current = mapView.getCurrentViewpoint(Viewpoint.Type.CENTER_AND_SCALE);
    Viewpoint zoomedOut = new Viewpoint((Point) current.getTargetGeometry(), current.getTargetScale() * 2.0);
    mapView.setViewpointAsync(zoomedOut);
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
