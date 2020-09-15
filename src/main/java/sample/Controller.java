package sample;

import javafx.fxml.Initializable;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.scene.layout.StackPane;

import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
//import javafx.scene.control.*;
import javafx.fxml.FXML;
import javafx.event.Event;
import javafx.stage.Stage;

public class Controller implements Initializable {

  @FXML
  private Button btnSubmit;

  @FXML
  private MenuBar menuBar;

  @FXML
  private MenuItem miExit;

  @FXML
  private StackPane sp;

  @Override
  public void initialize (URL location, ResourceBundle resources) {
    System.out.println("View is now loaded!");

    Button someButton = new Button("Some Button");
    sp.getChildren().add(someButton);
  }
  
  @FXML
  public void handleButtonAction (Event e) {
    System.out.println("Button clicked!");
  }

  @FXML
  void handleExit (Event e) {
    System.out.println("User wants to exit. Please delete your computer.");
    Stage stage = (Stage) menuBar.getScene().getWindow();
    // do what you have to do
    stage.close();
  }
}
