package ru.antowka.сontrollers.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import ru.antowka.entity.Error;
import ru.antowka.сontrollers.MainController;
import ru.antowka.services.CopyFiles;
import ru.antowka.entity.FileFound;
import ru.antowka.services.ErrorFabric;
import ru.antowka.services.Finder;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Anton Nik on 10.01.16.
 */
public class GuiMainController implements MainController{

    private Path sourceFolder;
    private Path targetFolder;
    private String pattern;
    private ErrorFabric ef = new ErrorFabric();
    private DirectoryChooser chooserSource = new DirectoryChooser();
    private DirectoryChooser chooserTarget = new DirectoryChooser();
    private Stage primaryStage;
    private List<FileFound> foundFiles;
    private Finder finder = new Finder();
    private CopyFiles copyFiles = new CopyFiles();
    private ObservableList<FileFound> fileListObsrv;

    @FXML
    private TableView<FileFound> tableFiles;

    @FXML
    private TableColumn<FileFound, String> fileName;

    @FXML
    private TableColumn<FileFound, Long> fileSize;

    @FXML
    private TableColumn<FileFound, String> fileExt;

    @FXML
    private TableColumn<FileFound, String> fullPath;

    @FXML
    private TableColumn<FileFound, Boolean> checked;

    @FXML
    private Button btnSelectSource;

    @FXML
    private Button btnSelectTarget;

    @FXML
    private Button btnSearch;

    @FXML
    private Button btnCopy;

    @FXML
    private Button btnClearResult;

    @FXML
    private TextField textFieldSourceFolder;

    @FXML
    private TextField textFieldTargetFolder;

    @FXML
    private TextField textFieldPattern;

    @FXML
    public void initialize() {

        // ********************** TABLE WITH RESULT ***************************
        //create header for table
        fileName.setCellValueFactory(new PropertyValueFactory<>("fileName"));
        fileSize.setCellValueFactory(new PropertyValueFactory<>("fileSize"));
        fileExt.setCellValueFactory(new PropertyValueFactory<>("fileExt"));
        fullPath.setCellValueFactory(new PropertyValueFactory<>("fullPath"));

        checked.setCellValueFactory(
                param -> param.getValue().isChecked()
        );

        checked.setCellFactory(CheckBoxTableCell.forTableColumn(checked));

        // ********************* SOURCE FOLDER ********************************

        //click on select source folder
        btnSelectSource.setOnAction((event) -> chooseSourceFolder());

        // ********************* TARGET FOLDER ********************************

        //click on select target folder
        btnSelectTarget.setOnAction((event) -> chooseTargetFolder());

        // ********************** CLICK ON START SEARCH ************************
        btnSearch.setOnAction((event) -> clickOnSearchButton());

        // ********************** CLICK ON COPY BUTTON *************************
        btnCopy.setOnAction((event) -> copyFiles());

        // ********************** CLICK ON COPY BUTTON *************************
        btnCopy.setOnAction((event) -> copyFiles());

        // ********************** CLICK ON CLEAR RESULT ************************
        btnClearResult.setOnAction((event) -> clearResult());
    }

    /**
     * Method start on click Search Button
     */
    private void clickOnSearchButton(){

        //set source folder
        sourceFolder = Paths.get(textFieldSourceFolder.getText());

        //check exist source folder
        if(!Files.isDirectory(sourceFolder)) {
            //show error #1
            showError(ef.getError(1));
        }

        pattern = textFieldPattern.getText();

        //check pattern on empty
        if(pattern.isEmpty()){
            //show error #3
            showError(ef.getError(3));
        }

        //find files
        foundFiles = finder.findByParams(sourceFolder, pattern);
        fileListObsrv = FXCollections.observableArrayList(foundFiles);
        tableFiles.setItems(fileListObsrv);
        tableFiles.autosize();
    }

    /**
     * Method set choose SOURCE folder
     */
    private void chooseSourceFolder(){
        File selectedDirectory = chooserSource.showDialog(primaryStage);
        textFieldSourceFolder.setText(selectedDirectory.getAbsolutePath());
    }

    /**
     * Method set choose TARGET folder
     */
    private void chooseTargetFolder(){
        File selectedDirectory = chooserTarget.showDialog(primaryStage);
        textFieldTargetFolder.setText(selectedDirectory.getAbsolutePath());
    }

    /**
     * Method copy found files to target folder
     */
    private void copyFiles(){

        List<FileFound> selectedFiles = new ArrayList<>();

        //select only checked files
        foundFiles.stream().forEach(file -> {
            if(file.isChecked().get()) {
                selectedFiles.add(file);
            }
        });

        //set source folder
        targetFolder = Paths.get(textFieldTargetFolder.getText());

        //Copy Files
        copyFiles.copyFilesByPath(selectedFiles ,targetFolder);
    }

    /**
     * Method clear prev result
     */
    private void clearResult(){
        fileListObsrv.removeAll(foundFiles);
        tableFiles.refresh();
    }

    /**
     * Show error
     *
     * @param error
     * @return
     */
    private void showError(Error error){

        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(error.getTitle());
        alert.setHeaderText(error.getHeader());
        alert.setContentText(error.getDescription());
        alert.showAndWait();
    }


    /**
     * *********************************** Getters and Setters *************************************
     */

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }
}
