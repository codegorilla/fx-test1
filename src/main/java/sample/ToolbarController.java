package sample;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ToolbarController {

  @FXML private VBox vbox;

  @FXML private void initialize () {
    System.out.println("Toolbar is now loaded!");
  }

  @FXML
  public void handleZoomIn (Event e) {
    System.out.println("Hello!");
//    Viewpoint current = mapView.getCurrentViewpoint(Viewpoint.Type.CENTER_AND_SCALE);
//    Viewpoint zoomedIn = new Viewpoint((Point) current.getTargetGeometry(), current.getTargetScale() / 2.0);
//    mapView.setViewpointAsync(zoomedIn);
  }

  @FXML
  public void handleZoomOut (Event e) {
//    Viewpoint current = mapView.getCurrentViewpoint(Viewpoint.Type.CENTER_AND_SCALE);
//    Viewpoint zoomedOut = new Viewpoint((Point) current.getTargetGeometry(), current.getTargetScale() * 2.0);
//    mapView.setViewpointAsync(zoomedOut);
  }


  // @FXML
  // public void handleButtonAction (Event e) {
  //   System.out.println("Hello Button clicked!");
  // }

}
