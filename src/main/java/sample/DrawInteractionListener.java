package sample;

import javafx.geometry.Point2D;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import com.esri.arcgisruntime.geometry.GeometryEngine;
import com.esri.arcgisruntime.geometry.Point;
import com.esri.arcgisruntime.geometry.SpatialReference;
import com.esri.arcgisruntime.geometry.SpatialReferences;

import com.esri.arcgisruntime.mapping.view.Graphic;
import com.esri.arcgisruntime.mapping.view.GraphicsOverlay;
import com.esri.arcgisruntime.mapping.view.MapView;
import com.esri.arcgisruntime.mapping.view.MapView.DefaultInteractionListener;

import com.esri.arcgisruntime.symbology.ColorUtil;
import com.esri.arcgisruntime.symbology.SimpleFillSymbol;
import com.esri.arcgisruntime.symbology.SimpleLineSymbol;
import com.esri.arcgisruntime.symbology.SimpleMarkerSymbol;


class DrawInteractionListener extends DefaultInteractionListener {

  private int hexRed = 0xFFFF0000;
  private int hexGreen = 0xFF00FF00;
  private int hexBlue = 0xFF0000FF;

  private MapView mapView;
  private MapPaneController mpc;
  private GraphicsOverlay drawOverlay;

  public DrawInteractionListener(MapPaneController mpc, MapView mapView) {
    super(mapView);
    this.mapView = mapView;

    this.mpc = mpc;

    // Setup draw overlay
    if (mapView != null) {
      drawOverlay = new GraphicsOverlay();
      mapView.getGraphicsOverlays().add(drawOverlay);
    }

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
    mapView.requestFocus();
    var x = event.getX();
    var y = event.getY();
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
    addPoint(x, y);
  }

  private void addPoint (double lon, double lat) {
    SimpleMarkerSymbol pointSymbol = new SimpleMarkerSymbol(SimpleMarkerSymbol.Style.SQUARE, hexRed, 10.0f);
    pointSymbol.setOutline(new SimpleLineSymbol(SimpleLineSymbol.Style.SOLID, hexGreen, 2.0f));
    Point point = new Point(lon, lat, SpatialReferences.getWgs84());
    Graphic pointGraphic = new Graphic(point, pointSymbol);
    drawOverlay.getGraphics().add(pointGraphic);
  }
}
