package ru.antowka;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * Created by Anton Nik on 10.01.16.
 */
public class Copier extends Application {

    private Path sourceFolder;
    private Path targetFolder;
    private String pattern;
    private ErrorFabric ef = new ErrorFabric();

    public static void main(String[] args) {

        launch(args);

/*
        //home/anton/Desktop/test
        Path startingDir = Paths.get("/home/anton/Desktop/test");

        //home/anton/Desktop/test1
        Path copyTo = Paths.get("/home/anton/Desktop/test1");

        //*.fb2
        String pattern = "*.fb2";

        //Find files
        Finder finder = new Finder();
        List<Path> foundFiles = finder.findByParams(startingDir, pattern);

        //Copy Files
        CopyFiles copyFiles = new CopyFiles();
        copyFiles.copyFilesByPath(foundFiles, copyTo);

        Application.launch(args);
*/
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        // переданный в параметре объект stage является нашим окном
        primaryStage.setTitle("GUI Copier");

        BorderPane root = new BorderPane();
        Scene scene = new Scene(root, 720, 550, Color.LIGHTGRAY);
        primaryStage.setScene(scene);

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 30, 50, 30));
        grid.setVgap(5);
        grid.setVgap(5);
        root.setCenter(grid);

        // ********************* SOURCE FOLDER ********************************

        //Choose folder window
        DirectoryChooser chooserSource = new DirectoryChooser();
        chooserSource.setTitle("Choose folder");

        //Button an field
        Label labelSourceFolder = new Label("Source folder: ");
        TextField textFieldSourceFolder = new TextField();
        Button btnSelectSource = new Button("Choose");
        grid.add(labelSourceFolder, 1, 1);
        grid.add(textFieldSourceFolder, 2, 1);
        grid.add(btnSelectSource, 3, 1);

        //click on select source folder
        btnSelectSource.setOnAction((event) -> {
            File selectedDirectory = chooserSource.showDialog(primaryStage);
            textFieldSourceFolder.setText(selectedDirectory.getAbsolutePath());
        });

        // ********************* TARGET FOLDER ********************************

        //Choose folder window
        DirectoryChooser chooserTarget = new DirectoryChooser();
        chooserTarget.setTitle("Choose folder");

        //Button an field
        Label labelTargetFolder = new Label("Target folder: ");
        TextField textFieldTargetFolder = new TextField();
        Button btnSelectTarget = new Button("Choose");
        grid.add(labelTargetFolder, 1, 2);
        grid.add(textFieldTargetFolder, 2, 2);
        grid.add(btnSelectTarget, 3, 2);

        //click on select target folder
        btnSelectTarget.setOnAction((event) -> {
            File selectedDirectory = chooserTarget.showDialog(primaryStage);
            textFieldTargetFolder.setText(selectedDirectory.getAbsolutePath());
        });

        // ********************* PATTERN FIELD ********************************
        Label labelPattern = new Label("Search pattern: ");
        TextField textFieldPattern = new TextField();
        grid.add(labelPattern, 1, 3);
        grid.add(textFieldPattern, 2, 3);

        // ********************** BUTTONS CONTROL *****************************
        Button btnSearch = new Button("Search files");
        grid.add(btnSearch, 1, 4);

        //click on Search
        btnSearch.setOnAction((event) -> {

            //set source folder
            sourceFolder = Paths.get(textFieldSourceFolder.getText());

            //check exist source folder
            if(!Files.isDirectory(sourceFolder)) {
                //show error #1
                ef.getError(1).showAndWait();
            }

            pattern = textFieldPattern.getText();

            //check pattern on empty
            if(pattern.isEmpty()){
                //show error #3
                ef.getError(3).showAndWait();
            }

            //find files
            Finder finder = new Finder();
            List<Path> foundFiles = finder.findByParams(sourceFolder, pattern);
        });

        //Table with files list
        //todo - http://docs.oracle.com/javase/8/javafx/user-interface-tutorial/table-view.htm#CJAGAAEE

        primaryStage.show();
    }
}
