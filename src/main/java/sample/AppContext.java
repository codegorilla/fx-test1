package sample;

public class AppContext {

  private static AppContext context = null;

  private final double START_LON = -118.75;
  private final double START_LAT = 34.0;
  private final int START_ZOOM = 12;

  private Camera camera = null;

  private MenubarController menubarController;
  private ToolbarController toolbarController;

  private AppContext () {
    camera = new Camera(START_LON, START_LAT, START_ZOOM);
  }

  public static AppContext getInstance () {
    if (context == null)
      context = new AppContext();
    return context;
  }

  public Camera getCamera () {
    return camera;
  }

  private void setMenubarController (MenubarController m) {
    menubarController = m;
  }

  private void setToolbarController (ToolbarController t) {
    toolbarController = t;
  }



}
