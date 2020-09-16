package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GlobeCommander extends Application {

  private final int START_WIDTH  = 960;
  private final int START_HEIGHT = 540;

  public static void main (String[] args) {
    launch(args);
  }

  @Override
  public void start (Stage primaryStage) throws Exception {
    Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
    primaryStage.setTitle("Globe Commander");
    primaryStage.setScene(new Scene(root, START_WIDTH, START_HEIGHT));
    primaryStage.show();
  }

}
