package sample;

import javafx.fxml.FXML;
import javafx.event.Event;

import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.CheckBoxTreeItem;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;

import javafx.scene.control.cell.CheckBoxTreeCell;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.stage.Stage;

public class LayerPanelController {

  @FXML
  private Button btnHello;

  @FXML
  private TreeView treeView;

  @FXML private void initialize () {
    System.out.println("View is now loaded!");

    Image image = new Image(getClass().getResourceAsStream("layers16.png"));

    // Create check box
    var check = new CheckBox();

    // // Create tree items
    // var rootItem = new CheckBoxTreeItem<>("Tutorials", new ImageView(image));
    // var webItem = new CheckBoxTreeItem<>("Web Tutorials", new ImageView(image));
    // webItem.getChildren().add(new CheckBoxTreeItem<>("HTML5 Tutorial", new ImageView(image)));
    // webItem.getChildren().add(new CheckBoxTreeItem<>("CSS Tutorial", new ImageView(image)));
    // rootItem.getChildren().add(webItem);
    // //treeView.setCellFactory(CheckBoxTreeCell.forTreeView());
    // //treeView.setCellFactory(p -> new CustomCheckBoxTreeCell<>());
    // treeView.setRoot(rootItem);


    // Create tree items
    var rootItem = new TreeItem<String>("Tutorials", new ImageView(image));
    var webItem = new TreeItem<String>("Web Tutorials", new ImageView(image));
    webItem.getChildren().add(new TreeItem<String>("HTML5 Tutorial", check));
    webItem.getChildren().add(new TreeItem<String>("CSS Tutorial", new ImageView(image)));
    rootItem.getChildren().add(webItem);
    //treeView.setCellFactory(CheckBoxTreeCell.forTreeView());
    //treeView.setCellFactory(p -> new CustomCheckBoxTreeCell<>());
    treeView.setRoot(rootItem);


  }

  @FXML
  public void handleButtonAction (Event e) {
    System.out.println("Hello Button clicked!");
  }

}
