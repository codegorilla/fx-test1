Set path to JavaFX.

set PATH_TO_FX="c:\Program Files\javafx-sdk-15\lib"

Compile the Main class.

javac --module-path %PATH_TO_FX% --add-modules javafx.controls,javafx.fxml src/sample/Main.java

Compile the Controller class.

javac --module-path %PATH_TO_FX% --add-modules javafx.controls,javafx.fxml src/sample/Controller.java

Navigate to the 'out' directory. Since the classes are part of the
sample package, we need to be one directory above, i.e. we cannot be
in the sample directory when we start the application.

Copy src/sample/sample.fxml to out/sample/sample.fxml.

Run the application.

java --module-path %PATH_TO_FX% --add-modules javafx.controls,javafx.fxml sample.Main


