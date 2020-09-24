package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javafx.scene.Node;

import java.lang.Thread;


public class GlobeCommander extends Application {

  private final int START_WIDTH  = 960;
  private final int START_HEIGHT = 540;

  // private final double START_LON = -118.75;
  // private final double START_LAT = 34.0;
  // private final int START_ZOOM = 12;

  // In java 11 the main method must be ouside the class that extends the
  // application? Is that true? If so, then this can't be here.
  public static void main (String[] args) {
    launch(args);
  }

  @Override
  public void start (Stage primaryStage) throws Exception {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("main.fxml"));
    Pane root = loader.load();

    // Do some initialization
    // System.out.println("init");
    // MainController mainController = loader.getController();
    // var camera = new Camera(START_LON, START_LAT, START_ZOOM);
    // mainController.getMapPaneController().setCamera(camera);

    primaryStage.setTitle("Globe Commander");
    primaryStage.setScene(new Scene(root, START_WIDTH, START_HEIGHT));
    primaryStage.show();


  }

}
