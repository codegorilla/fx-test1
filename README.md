README.md
=========

Set path to JavaFX.

```
$ set PATH_TO_FX="c:\Program Files\javafx-sdk-15\lib"
```

Compile the Main class.

```
$ javac --module-path %PATH_TO_FX% --add-modules javafx.controls,javafx.fxml src/sample/Main.java -d out
```

Compile the Controller class.

```
$ javac --module-path %PATH_TO_FX% --add-modules javafx.controls,javafx.fxml src/sample/Controller.java -d out
```

Copy src/sample/sample.fxml to out/sample/sample.fxml.

```
$ cp src/sample/sample.fxml out/sample/sample.fxml
```

Navigate to the 'out' directory. Since the classes are part of the
sample package, we need to be one directory above, i.e. we cannot be
in the sample directory when we start the application.

Run the application.

```
$ java --module-path %PATH_TO_FX% --add-modules javafx.controls,javafx.fxml --class-path out sample.Main
```

To clean the build, run the following command.

```
$ rmdir /q /s out\sample
```
