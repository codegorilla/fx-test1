package sample;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.StackPane;

public class TMap extends StackPane {

  public TMap () {
    var loader = new FXMLLoader(getClass().getResource("tmap.fxml"));
    loader.setRoot(this);
    loader.setController(new TMapController());
    try {
      loader.load();
    } catch (IOException exception) {
      throw new RuntimeException(exception);
    }
  }
}
