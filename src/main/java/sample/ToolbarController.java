package sample;

import javafx.event.Event;
import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ToolbarController {

  @FXML private VBox vbox;

  MapPaneController mpc;

  @FXML private void initialize () {
    System.out.println("Toolbar loaded.");
  }

  // Should these be events or actionevents?

  @FXML
  public void handleZoomIn (Event e) {
    mpc.zoomIn();
  }

  @FXML
  public void handleZoomOut (Event e) {
    mpc.zoomOut();
  }

  void testme () {
    System.out.println("Test me! I am a toolbar controller!");
  }

  void setMapPaneController (MapPaneController mpc) {
    this.mpc = mpc;
  }

}
