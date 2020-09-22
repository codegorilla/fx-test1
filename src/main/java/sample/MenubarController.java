package sample;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;


public class MenubarController implements Initializable {

  @FXML private MenuBar menuBar;
  @FXML private MenuItem miExit;

  @Override
  public void initialize (URL location, ResourceBundle resources) {
    System.out.println("Menubar is now loaded!");
  }

  @FXML
  void handleExit (Event e) {
    System.out.println("User wants to exit. Please delete your computer.");
    dispose();
    Stage stage = (Stage) menuBar.getScene().getWindow();
    // do what you have to do
    stage.close();
  }

  void dispose () {
    // if (mapView != null) {
    //   mapView.dispose();
    // }
  }

}
