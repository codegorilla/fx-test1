package sample;

import javafx.fxml.Initializable;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.scene.control.Button;
import javafx.fxml.FXML;
import javafx.event.Event;

public class Controller implements Initializable {

  @FXML
  private Button btnSubmit;

  @Override
  public void initialize (URL location, ResourceBundle resources) {
    System.out.println("View is now loaded!");
  }
  
  @FXML
  public void handleButtonAction (Event e) {
    System.out.println("Button clicked!");
  }
}
