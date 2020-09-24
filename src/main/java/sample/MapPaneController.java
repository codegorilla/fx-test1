package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.geometry.Point2D;

import javafx.scene.input.MouseEvent; 
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import com.esri.arcgisruntime.mapping.ArcGISMap;
import com.esri.arcgisruntime.mapping.Basemap;
import com.esri.arcgisruntime.mapping.Viewpoint;
import com.esri.arcgisruntime.mapping.view.Graphic;
import com.esri.arcgisruntime.mapping.view.GraphicsOverlay;
import com.esri.arcgisruntime.mapping.view.LatitudeLongitudeGrid;
import com.esri.arcgisruntime.mapping.view.WrapAroundMode;

import com.esri.arcgisruntime.geometry.Point;
import com.esri.arcgisruntime.geometry.SpatialReference;
import com.esri.arcgisruntime.geometry.SpatialReferences;

import com.esri.arcgisruntime.geometry.GeometryEngine;

import com.esri.arcgisruntime.symbology.SimpleFillSymbol;
import com.esri.arcgisruntime.symbology.SimpleLineSymbol;
import com.esri.arcgisruntime.symbology.SimpleMarkerSymbol;
import com.esri.arcgisruntime.symbology.TextSymbol;

import com.esri.arcgisruntime.mapping.view.MapView;

public class MapPaneController {

  private int hexRed = 0xFFFF0000;
  private int hexGreen = 0xFF00FF00;
  private int hexBlue = 0xFF0000FF;

  private GraphicsOverlay graphicsOverlay;

  @FXML private VBox vbox;
  @FXML private MapView mapView;

  private Camera camera;

  @FXML private void initialize () {
    System.out.println("Map pane loaded.");
    // setupMap();
    // setupGraphicsOverlay();
    // addPointGraphic(-118.29507, 34.13501);
    // addPointGraphic(-120.5, 30.5);
  }

  public void setCamera (Camera camera) {
    this.camera = camera;
    // Probably doesn't belong here. Maybe move to some other init method
    setupMap();
    setupGraphicsOverlay();
    addPointGraphic(-118.29507, 34.13501);
    addPointGraphic(-120.5, 30.5);
  }

  private void setupMap() {
    if (mapView != null) {
      //Basemap.Type basemapType = Basemap.Type.STREETS_VECTOR;
      Basemap.Type basemapType = Basemap.Type.DARK_GRAY_CANVAS_VECTOR;
      ArcGISMap map = new ArcGISMap(
//        basemapType, 34, -118, 12);
        basemapType, camera.getLatitude(), camera.getLongitude(), camera.getZoom());
      mapView.setMap(map);
      mapView.setWrapAroundMode(WrapAroundMode.ENABLE_WHEN_SUPPORTED);

      // create a grid for showing Latitude and Longitude (Meridians and Parallels)
      LatitudeLongitudeGrid grid = new LatitudeLongitudeGrid();
      grid.setVisible(true);
      int gridLevels = grid.getLevelCount();

      // create a line symbol (a thick purple line)
      var lineSymbol = new SimpleLineSymbol(SimpleLineSymbol.Style.DOT, 0xFF5a5a5a, 1.0f);

      // create a text style with white text and blue halo.
      var textSymbol = new TextSymbol();
      textSymbol.setSize(12);
      textSymbol.setColor(0xFFFFFFFF);
      textSymbol.setHaloColor(0xFF000000);
      textSymbol.setHaloWidth(0);

      // apply new symbols to each grid level
      for (int level = 0; level < gridLevels; level++) {
        grid.setTextSymbol(level, textSymbol);
        grid.setLineSymbol(level, lineSymbol);
      }

      //display the grid on the map view
      mapView.setGrid(grid);
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
  public void handleClick (MouseEvent e) {
    var x = e.getX();
    var y = e.getY();
    System.out.println("Mouse was clicked at pixel (" + x + "," + y + ")");
    var localCoords = new Point2D(x, y);
    var p = mapView.screenToLocation(localCoords);

    // spatial reference of points stored in lat-lon coordinates: WGS84 (wkid: 4326)
    SpatialReference wgs84 = SpatialReference.create(4326);
    // project from WGS84 to the map's spatial reference
    Point pProj = (Point) GeometryEngine.project(p, wgs84);
    x = pProj.getX();
    y = pProj.getY();
    System.out.println("Mouse was clicked at coord (" + x + "," + y + ")"); 
  }

  void dispose () {
    if (mapView != null) {
      mapView.dispose();
    }
  }

  public void zoomIn () {
    Viewpoint current = mapView.getCurrentViewpoint(Viewpoint.Type.CENTER_AND_SCALE);
    Viewpoint zoomedIn = new Viewpoint((Point) current.getTargetGeometry(), current.getTargetScale() / 2.0);
    mapView.setViewpointAsync(zoomedIn);
  }

  public void zoomOut () {
   Viewpoint current = mapView.getCurrentViewpoint(Viewpoint.Type.CENTER_AND_SCALE);
   Viewpoint zoomedOut = new Viewpoint((Point) current.getTargetGeometry(), current.getTargetScale() * 2.0);
   mapView.setViewpointAsync(zoomedOut);
  }

}
