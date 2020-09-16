package sample;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitPane;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.event.Event;
import javafx.stage.Stage;


import com.esri.arcgisruntime.mapping.ArcGISMap;
import com.esri.arcgisruntime.mapping.Basemap;
import com.esri.arcgisruntime.mapping.Viewpoint;
import com.esri.arcgisruntime.mapping.view.Graphic;
import com.esri.arcgisruntime.mapping.view.GraphicsOverlay;

import com.esri.arcgisruntime.geometry.Point;
import com.esri.arcgisruntime.geometry.SpatialReferences;

import com.esri.arcgisruntime.symbology.SimpleFillSymbol;
import com.esri.arcgisruntime.symbology.SimpleLineSymbol;
import com.esri.arcgisruntime.symbology.SimpleMarkerSymbol;

import com.esri.arcgisruntime.mapping.view.MapView;

public class Controller implements Initializable {

  private int hexRed = 0xFFFF0000;
  private int hexGreen = 0xFF00FF00;
  private int hexBlue = 0xFF0000FF;

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

  private GraphicsOverlay graphicsOverlay;

  @Override
  public void initialize (URL location, ResourceBundle resources) {
    System.out.println("View is now loaded!");
    sp.setResizableWithParent(vb, Boolean.FALSE);
    //sp.setDividerPositions(0.25);
    setupMap();
    setupGraphicsOverlay();
    addPointGraphic(-118.29507, 34.13501);
    addPointGraphic(-120.5, 30.5);
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

  private void setupGraphicsOverlay() {
    if (mapView != null) {
      graphicsOverlay = new GraphicsOverlay();
      mapView.getGraphicsOverlays().add(graphicsOverlay);
    }
  }

  private void addPointGraphic(double lon, double lat) {
    if (graphicsOverlay != null) {
      SimpleMarkerSymbol pointSymbol = new SimpleMarkerSymbol(SimpleMarkerSymbol.Style.SQUARE, hexRed, 10.0f);
      pointSymbol.setOutline(new SimpleLineSymbol(SimpleLineSymbol.Style.SOLID, hexGreen, 2.0f));
      Point point = new Point(lon, lat, SpatialReferences.getWgs84());
      Graphic pointGraphic = new Graphic(point, pointSymbol);
      graphicsOverlay.getGraphics().add(pointGraphic);
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
