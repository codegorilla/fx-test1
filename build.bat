set PATH_TO_FX="c:\Program Files\javafx-sdk-15\lib"

javac --module-path %PATH_TO_FX% --add-modules javafx.controls,javafx.fxml src/sample/Main.java -d out

javac --module-path %PATH_TO_FX% --add-modules javafx.controls,javafx.fxml src/sample/Controller.java -d out

javac --module-path %PATH_TO_FX% --add-modules javafx.controls,javafx.fxml src/sample/TestController.java -d out

copy src\sample\sample.fxml out\sample\sample.fxml

copy src\sample\test.fxml out\sample\test.fxml

