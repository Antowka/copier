package ru.antowka.init.impl;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ru.antowka.сontrollers.GuiMainController;
import ru.antowka.init.Initializer;

/**
 * Created by Anton Nik on 19.01.16.
 */
public class GuiInitializer extends Application implements Initializer {

    @Override
    public void initilize(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/main.fxml"));
        Parent root = loader.load();

        //set primary stage to GuiMainController
        GuiMainController guiMainController = loader.getController();
        guiMainController.setPrimaryStage(primaryStage);

        // переданный в параметре объект stage является нашим окном
        primaryStage.setTitle("GUI GuiMainController");

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);

        primaryStage.show();
    }
}
