Compile the Main class.

javac --module-path %PATH_TO_FX% --add-modules javafx.controls,javafx.fxml Main.java

Compile the Controller class.

javac --module-path %PATH_TO_FX% --add-modules javafx.controls,javafx.fxml Main.java

Make sure you are in the parent directory. Since the classes are part
of the sample package, we need to be one directory above, i.e. we
cannot be in the sample directory when we start the application.

Run the application.

java --module-path %PATH_TO_FX% --add-modules javafx.controls,javafx.fxml sample.Main


