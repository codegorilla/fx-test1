package sample;

import javafx.geometry.Point2D;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import com.esri.arcgisruntime.geometry.GeometryEngine;
import com.esri.arcgisruntime.geometry.Part;
import com.esri.arcgisruntime.geometry.Point;
import com.esri.arcgisruntime.geometry.PointCollection;
import com.esri.arcgisruntime.geometry.Polyline;
import com.esri.arcgisruntime.geometry.SpatialReference;
import com.esri.arcgisruntime.geometry.SpatialReferences;
import com.esri.arcgisruntime.geometry.PolylineBuilder;

import com.esri.arcgisruntime.mapping.view.Graphic;
import com.esri.arcgisruntime.mapping.view.GraphicsOverlay;
import com.esri.arcgisruntime.mapping.view.MapView;
import com.esri.arcgisruntime.mapping.view.MapView.DefaultInteractionListener;

import com.esri.arcgisruntime.symbology.ColorUtil;
import com.esri.arcgisruntime.symbology.SimpleFillSymbol;
import com.esri.arcgisruntime.symbology.SimpleLineSymbol;
import com.esri.arcgisruntime.symbology.SimpleMarkerSymbol;


class DrawPolylineInteractionListener extends DefaultInteractionListener {

  private int hexRed = 0xFFFF0000;
  private int hexGreen = 0xFF00FF00;
  private int hexBlue = 0xFF0000FF;

  private MapView mapView;
  private MapPaneController mpc;
  private GraphicsOverlay overlay;
  private Graphic lineGraphic;

  private SpatialReference wgs84;
  private PointCollection points;

  PolylineBuilder polylineBuilder;

  private Polyline polyline;
  private SimpleLineSymbol polylineSymbol;

  //private Point point1;
  //private Point point2;

  private int counter;

  private final int BEFORE = 0;
  private final int DRAW = 1;

  private int state;

  public DrawPolylineInteractionListener (MapPaneController mpc, MapView mapView) {
    super(mapView);
    this.mapView = mapView;

    // Can we get the mapView from the mapPaneController?
    this.mpc = mpc;

    overlay = new GraphicsOverlay();
    mapView.getGraphicsOverlays().add(overlay);
    state = BEFORE;

    // Spatial reference
    wgs84 = SpatialReferences.getWgs84();

    // Polyline will have a single part that contains multiple points
    polylineBuilder = new PolylineBuilder(new Part(wgs84), wgs84);

    // Configure visual appearance
    polylineSymbol = new SimpleLineSymbol(SimpleLineSymbol.Style.DOT, hexRed, 1.0f);

    // Start the counter at 1
    counter = 1;
  }

  public void onKeyPressed (KeyEvent event) {
    System.out.println("Key pressed!");
    super.onKeyPressed(event);
    if (event.getCode() == KeyCode.ESCAPE) {
      System.out.println("ESC key pressed!");
      mpc.loadInteractionListener();
      // Need to set interaction listener to one before
    }
    // Event consume is one way to disable traversal
    event.consume();
  }

  public void onMouseClicked (MouseEvent event) {
    System.out.println("Mouse clicked here!");
    // Not sure if the requestFocus is needed
    mapView.requestFocus();
    if (state == BEFORE) {
      state = DRAW;
      // Convert from screen coordinates to map coordinates, and then to
      // geographic coordinates, e.g. latitude/longitude. Note that this is
      // actually probably converting from local coordinates, not actual screen
      // coordinates.
      var x = event.getX();
      var y = event.getY();
      var mapPoint = mapView.screenToLocation(new Point2D(x, y));
      var geoPoint = (Point)GeometryEngine.project(mapPoint, wgs84);
      var part = polylineBuilder.getParts().get(0);
      part.addPoint(geoPoint);
      part.addPoint(geoPoint);
      polyline = polylineBuilder.toGeometry();
      lineGraphic = new Graphic(polyline, polylineSymbol);
      overlay.getGraphics().add(lineGraphic);
    }
    else if (state == DRAW)  {
      // Ok now we've placed the second point. Need to move on to the third point
      var part = polylineBuilder.getParts().get(0);
      var geoPoint = part.getPoint(counter);
      part.addPoint(geoPoint);
      counter += 1;
    }

  }

  public void onMouseMoved (MouseEvent event) {
    if (state == DRAW) {
      var x = event.getX();
      var y = event.getY();
      var mapPoint = mapView.screenToLocation(new Point2D(x, y));
      var geoPoint = (Point)GeometryEngine.project(mapPoint, wgs84);
      polylineBuilder.getParts().get(0).setPoint(counter, geoPoint);
      polyline = polylineBuilder.toGeometry();
      lineGraphic.setGeometry(polyline);
    }
  }

  // private void addPoint (double lon, double lat) {
  //   SimpleMarkerSymbol pointSymbol = new SimpleMarkerSymbol(SimpleMarkerSymbol.Style.SQUARE, hexRed, 10.0f);
  //   pointSymbol.setOutline(new SimpleLineSymbol(SimpleLineSymbol.Style.SOLID, hexGreen, 2.0f));
  //   Point point = new Point(lon, lat, SpatialReferences.getWgs84());
  //   Graphic pointGraphic = new Graphic(point, pointSymbol);
  //   overlay.getGraphics().add(pointGraphic);
  // }
}
