package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ToolbarController {

  MapPaneController mpc;

  @FXML private void initialize () {
    System.out.println("Toolbar loaded.");
  }

  @FXML
  public void handleZoomIn (ActionEvent e) {
    mpc.zoomIn();
  }

  @FXML
  public void handleZoomOut (ActionEvent e) {
    mpc.zoomOut();
  }

  @FXML
  public void handleToggleGrid (ActionEvent e) {
    mpc.toggleGrid();
  }

  @FXML
  public void handleDrawPoint (ActionEvent e) {
    mpc.drawPoint();
  }

  void testme () {
    System.out.println("Test me! I am a toolbar controller!");
  }

  void setMapPaneController (MapPaneController mpc) {
    this.mpc = mpc;
  }

}
