package sample;

import java.io.IOException;
import java.net.URL;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.StackPane;

// A View object should be one per scene
// This won't be used for toolbar but keeping it
// around for future reference

public class ToolbarView {

  private FXMLLoader loader;

  ToolbarView () {
    var url = getClass().getResource("toolbar.fxml");
    load(url);
  }

  FXMLLoader load (URL location) {
    loader = new FXMLLoader(location);
    try {
      loader.load();
    }
    catch (IOException exception) {
      throw new RuntimeException(exception);
    }
    return loader;
  }

  StackPane get () {
    return loader.getRoot();
  }

}
