package sample;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.geometry.Point2D;

import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextArea;

import javafx.scene.input.MouseEvent; 

import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainController {

  @FXML
  private Button btnSubmit;

  @FXML
  private SplitPane sp;

  @FXML
  private SplitPane sp1;

  @FXML
  private SplitPane sp2;

  @FXML
  private TextArea output;

  @FXML
  private StackPane propertyPane;

  @FXML
  private VBox vb;

  @FXML
  private Button btnTest1;

  @FXML private MenubarController menubarController;
  @FXML private ToolbarController toolbarController;
  @FXML private MapPaneController mapPaneController;

  private final double START_LON = -118.75;
  private final double START_LAT = 34.0;
  private final int START_ZOOM = 12;


  @FXML private void initialize () {
    System.out.println("Main window loaded.");
    sp.setResizableWithParent(vb, false);
    sp.setDividerPositions(0.25);
    vb.setMinWidth(0);
    // Doesn't affect min width
    //vb.setVisible(false);
    //btnTest1.setVisible(false);

    //sp.getItems().remove(0);
    //sp.getItems().add(0, vb);

    sp1.setResizableWithParent(output, false);
    sp1.setDividerPositions(0.75);
    sp1.setMinHeight(100);


    sp2.setResizableWithParent(propertyPane, false);
    sp2.setDividerPositions(0.75);
    sp2.setMinWidth(0);

    menubarController.setToolbarController(toolbarController);
    toolbarController.setMapPaneController(mapPaneController);
    
    menubarController.testme();
    toolbarController.testme();

    var camera = new Camera(START_LON, START_LAT, START_ZOOM);
    mapPaneController.setCamera(camera);
  }

  @FXML
  public void handleButtonAction (Event e) {
    System.out.println("Button clicked!");
  }

  public MapPaneController getMapPaneController() {
    return mapPaneController;
  }

}
