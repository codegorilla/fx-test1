package sample;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import com.esri.arcgisruntime.mapping.view.MapView;

import com.esri.arcgisruntime.mapping.view.MapView.DefaultInteractionListener;

class MyInteractionListener extends DefaultInteractionListener {

  MapView mapView;

  public MyInteractionListener(MapView mapView) {
    super(mapView);
    this.mapView = mapView;
  }

  public void onKeyPressed (KeyEvent event) {
    System.out.println("Key pressed!");
    super.onKeyPressed(event);
    if (event.getCode() == KeyCode.ESCAPE) {
      System.out.println("ESC key pressed!");
    }
    // Event consume is one way to disable traversal
    event.consume();
  }

  public void onMouseClicked (MouseEvent event) {
    System.out.println("Mouse clicked!");
    mapView.requestFocus();
  }
}
