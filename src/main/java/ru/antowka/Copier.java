package ru.antowka;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import ru.antowka.Entity.FileFound;

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
        root.setTop(grid);

        //create VBox for Table
        final VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        root.setBottom(vbox);

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


        // ********************** TABLE WITH RESULT ***************************
        //todo - http://docs.oracle.com/javase/8/javafx/user-interface-tutorial/table-view.htm#CJAGAAEE
        final Label label = new Label("Search Result");

        //create table
        final TableView table = new TableView();
        table.setEditable(false);

        //create header for table
        TableColumn fileNameCol = new TableColumn("Name");
        fileNameCol.setCellValueFactory(new PropertyValueFactory<>("fileName"));

        TableColumn fileSizeCol = new TableColumn("Size");
        fileSizeCol.setCellValueFactory(new PropertyValueFactory<>("fileSize"));

        TableColumn fileExtCol = new TableColumn("Ext");
        fileExtCol.setCellValueFactory(new PropertyValueFactory<>("FileExt"));
        table.getColumns().addAll(fileNameCol, fileSizeCol, fileExtCol);



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
            List<FileFound> foundFiles = finder.findByParams(sourceFolder, pattern);
            ObservableList<FileFound> fileListObsrv = FXCollections.observableArrayList(foundFiles);
            table.setItems(fileListObsrv);
        });


        vbox.getChildren().addAll(label, table);

        primaryStage.show();
    }
}
