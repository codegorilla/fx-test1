package sample;

import javafx.scene.Node;

import javafx.scene.control.CheckBox;

import javafx.scene.control.cell.CheckBoxTreeCell;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class CustomCheckBoxTreeCell<T> extends CheckBoxTreeCell<T> {
  @Override
  public void updateItem (T item, boolean empty) {
    Image image = new Image(getClass().getResourceAsStream("layers16.png"));
    var iv = new ImageView(image);
    super.updateItem(item, empty);
    Node n = getGraphic();
    if (n instanceof CheckBox) {
      CheckBox box = (CheckBox)getGraphic();
      box.setGraphic(iv);
    }
  }

}
