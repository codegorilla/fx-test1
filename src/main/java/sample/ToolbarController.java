package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ToolbarController {

  @FXML ToolBar toolbar;

  MapPaneController mpc;

  @FXML private void initialize () {
    System.out.println("Toolbar loaded.");
    //toolbar.setFocusTraversable(false);
    AppContext.getInstance().setToolbarController(this);
  }

  public void disableFocusTraversable () {
    toolbar.setFocusTraversable(false);
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

  @FXML
  public void handleStartDraw (ActionEvent e) {
    mpc.startDraw();
  }


  @FXML
  public void handleStopDraw (ActionEvent e) {
    mpc.stopDraw();
  }


  void testme () {
    System.out.println("Test me! I am a toolbar controller!");
  }

  void setMapPaneController (MapPaneController mpc) {
    this.mpc = mpc;
  }

}
