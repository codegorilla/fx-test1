package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.geometry.Point2D;

import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import com.esri.arcgisruntime.geometry.GeometryEngine;
import com.esri.arcgisruntime.geometry.Point;
import com.esri.arcgisruntime.geometry.SpatialReference;
import com.esri.arcgisruntime.geometry.SpatialReferences;

import com.esri.arcgisruntime.mapping.ArcGISMap;
import com.esri.arcgisruntime.mapping.Basemap;
import com.esri.arcgisruntime.mapping.SelectionProperties;
import com.esri.arcgisruntime.mapping.Viewpoint;
import com.esri.arcgisruntime.mapping.view.Graphic;
import com.esri.arcgisruntime.mapping.view.GraphicsOverlay;
import com.esri.arcgisruntime.mapping.view.InteractionListener;
import com.esri.arcgisruntime.mapping.view.LatitudeLongitudeGrid;
import com.esri.arcgisruntime.mapping.view.WrapAroundMode;
import com.esri.arcgisruntime.mapping.view.SketchCreationMode;
import com.esri.arcgisruntime.mapping.view.SketchEditConfiguration;
import com.esri.arcgisruntime.mapping.view.SketchEditor;
import com.esri.arcgisruntime.mapping.view.SketchStyle;

import com.esri.arcgisruntime.symbology.ColorUtil;
import com.esri.arcgisruntime.symbology.SimpleFillSymbol;
import com.esri.arcgisruntime.symbology.SimpleLineSymbol;
import com.esri.arcgisruntime.symbology.SimpleMarkerSymbol;
import com.esri.arcgisruntime.symbology.TextSymbol;

import com.esri.arcgisruntime.mapping.view.MapView;
import javafx.scene.control.TextArea;

public class MapPaneController {

  private int hexRed = 0xFFFF0000;
  private int hexGreen = 0xFF00FF00;
  private int hexBlue = 0xFF0000FF;

  private int mode;
  
  // Move mode allows you to zoom, pan, etc. Draw mode allows you to draw
  // graphics (points, lines, etc.) on the map.
  private final int MOVE = 0;
  private final int DRAW = 1;

  private LatitudeLongitudeGrid grid;
  private GraphicsOverlay graphicsOverlay;
  
  private SketchEditor sketchEditor;
  private SketchStyle sketchStyle;

  private InteractionListener savedIntListener;
  private InteractionListener intListener;
  private DrawInteractionListener drawIntListener = null;

  @FXML private MapView mapView;

  @FXML private void initialize () {
    setupMap();
    setupGrid();

    // Create interaction listeners
    intListener = new MyInteractionListener(mapView);
    mapView.setInteractionListener(intListener);

    drawIntListener = new DrawInteractionListener(this, mapView);


    setupGraphicsOverlay();
    setupSketchEditor();

    addPointGraphic(-118.29507, 34.13501);
    addPointGraphic(-120.5, 30.5);
    mode = MOVE;
    System.out.println("Map pane loaded.");


    // Set the map pane controller into the app context
    // We need to access map pane controller later because for some reason
    // in order for it to have focus we need to set it after the scene is
    // loaded.
    AppContext.getInstance().setMapPaneController(this);

    // var x = mapView.getInteractionListener();
    // x.onKeyPressed(event -> {
    //   System.out.println("HI!");
    // });

    // mapView.setOnKeyPressed(event -> {
    //   System.out.println("HI!");
    // });

    // mapView.setOnKeyReleased(event -> {
    //   System.out.println("HI!");
    // });

    // mapView.setOnKeyTyped(event -> {
    //   System.out.println("HI!");
    // });

  }

  public void setFocus () {
    mapView.requestFocus();
    // Should not be traversable
    //mapView.setFocusTraversable(false);
  }

  private void setupMap () {
    if (mapView != null) {
      var camera = AppContext.getInstance().getCamera();
      // Try using other basemap types, e.g. Basemap.Type.STREETS_VECTOR
      Basemap.Type basemapType = Basemap.Type.DARK_GRAY_CANVAS_VECTOR;
      ArcGISMap map = new ArcGISMap(
        basemapType, camera.getLatitude(), camera.getLongitude(), camera.getZoom());
      mapView.setMap(map);
      mapView.setWrapAroundMode(WrapAroundMode.ENABLE_WHEN_SUPPORTED);
    }
  }

  private void setupGrid () {
    // create a grid for showing Latitude and Longitude (Meridians and Parallels)
    grid = new LatitudeLongitudeGrid();
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

  private void setupGraphicsOverlay() {
    if (mapView != null) {
      graphicsOverlay = new GraphicsOverlay();
      mapView.getGraphicsOverlays().add(graphicsOverlay);
    }
  }

  private void setupSketchEditor () {
    // For now get the existing sketch style and modify it instead of creating
    // a new one. Creating a new one caused a null pointer exception.
    sketchEditor = new SketchEditor();
    var style = sketchEditor.getSketchStyle();
    var pointSymbol = new SimpleMarkerSymbol(SimpleMarkerSymbol.Style.SQUARE, ColorUtil.colorToArgb(Color.BLUE), 10.0f);
    pointSymbol.setOutline(
      new SimpleLineSymbol(SimpleLineSymbol.Style.SOLID, ColorUtil.colorToArgb(Color.RED), 2.0f));
    style.setVertexSymbol(pointSymbol);
    style.setSelectedVertexSymbol(pointSymbol);
    sketchEditor.setSketchStyle(style);
    mapView.setSketchEditor(sketchEditor);
    // Configure selection effect to have reduced halo. Note that this isn't
    // really part of the sketch editor anymore.
    var properties = mapView.getSelectionProperties();
    properties.setColor(0x8000FFFF);
    // Configure sketching
    // setAllowPartSelection doesn't seem to have any effect
    sketchEditor.getSketchEditConfiguration().setAllowPartSelection(true);
    // It set to SELECT_ONLY, then nothing appears when drawing
    sketchEditor.getSketchEditConfiguration().setVertexEditMode(
      SketchEditConfiguration.SketchVertexEditMode.INTERACTION_EDIT);
    sketchEditor.getSketchEditConfiguration().setContextMenuEnabled(true);
    sketchEditor.getSketchEditConfiguration().setRequireSelectionBeforeDrag(true);
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

  // @FXML
  // public void handleClick1 (MouseEvent e) {
  //   System.out.println("HERE! ***");
  // }

  // @FXML
  // public void handleClick2 (MouseEvent e) {
  //   System.out.println("THERE! ***");
  // }

  @FXML
  public void handleClick (MouseEvent e) {
    mapView.requestFocus();
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
    if (mode == DRAW) {
      SimpleMarkerSymbol pointSymbol = new SimpleMarkerSymbol(SimpleMarkerSymbol.Style.SQUARE, 0xFF222222, 10.0f);
      pointSymbol.setOutline(new SimpleLineSymbol(SimpleLineSymbol.Style.SOLID, 0xFF88DDFF, 2.0f));
      Point point = new Point(x, y, SpatialReferences.getWgs84());
      Graphic pointGraphic = new Graphic(point, pointSymbol);
      graphicsOverlay.getGraphics().add(pointGraphic);
    }
  }

  @FXML
  public void handleKeyPress (KeyEvent e) {
    System.out.println("Pressed a key now!");
    e.consume();
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

  public void toggleGrid () {
    var gridState = !grid.isVisible();
    grid.setVisible(gridState);
  }

  public void drawPoint () {
    //mode = DRAW;
    graphicsOverlay.clearSelection();
    sketchEditor.stop();

    sketchEditor.start(SketchCreationMode.POLYGON);
    // Need to get interaction listener and make it listen for ESC key press
    //InteractionListener someIntListener = mapView.getInteractionListener();
  }

  public void startDraw () {
    // Switch to a new interaction listener, but preserve the old one
    saveInteractionListener();
    //intListener = drawIntListener;
    mapView.setInteractionListener(drawIntListener);
  }

  public void saveInteractionListener () {
    savedIntListener = intListener;
  }

  public void loadInteractionListener () {
    mapView.setInteractionListener(intListener);
  }

  public void stopDraw () {
    //mode = DRAW;
    graphicsOverlay.clearSelection();
    sketchEditor.stop();
    // Below doesn't work to give map focus after stopping sketchEditor
    mapView.requestFocus();
  }


  public void createEntity () {
    Entity x = new Entity();
    var g = x.getGraphic();
    graphicsOverlay.getGraphics().add(g);
  }

}
