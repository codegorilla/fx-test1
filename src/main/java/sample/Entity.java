package sample;

import com.esri.arcgisruntime.geometry.Point;
import com.esri.arcgisruntime.geometry.SpatialReferences;
import com.esri.arcgisruntime.mapping.view.Graphic;
import com.esri.arcgisruntime.symbology.SimpleLineSymbol;
import com.esri.arcgisruntime.symbology.SimpleMarkerSymbol;


public class Entity {

  private int hexRed = 0xFFFF0000;
  private int hexGreen = 0xFF00FF00;
  private int hexBlue = 0xFF0000FF;

  Graphic graphic;
  Track track;

  public Entity () {
    var lon = -116.4;
    var lat = 33.75;
    track = new Track(100, 92, 12);
    SimpleMarkerSymbol pointSymbol = new SimpleMarkerSymbol(SimpleMarkerSymbol.Style.SQUARE, hexRed, 10.0f);
    pointSymbol.setOutline(new SimpleLineSymbol(SimpleLineSymbol.Style.SOLID, hexGreen, 2.0f));
    Point point = new Point(lon, lat, SpatialReferences.getWgs84());
    graphic = new Graphic(point, pointSymbol);
  }

  public Graphic getGraphic () {
    return graphic;
  }

  public Track getTrack () {
    return track;
  }

}
