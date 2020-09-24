package sample;

public class Camera {

  private double longitude;
  private double latitude;
  private int zoom;

  public Camera (double longitude, double latitude, int zoom) {
    this.longitude = longitude;
    this.latitude = latitude;
    this.zoom = zoom;
  }

  public double getLongitude () {
    return longitude;
  }

  public double getLatitude () {
    return latitude;
  }

  public int getZoom () {
    return zoom;
  }
}
