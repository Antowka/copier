package ru.antowka;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ru.antowka.Controllers.MainController;

/**
 * Created by Anton Nik on 17.01.16.
 */
public class Initializer extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/main.fxml"));
        Parent root = loader.load();

        //set primary stage to MainController
        MainController mainController = loader.getController();
        mainController.setPrimaryStage(primaryStage);

        // переданный в параметре объект stage является нашим окном
        primaryStage.setTitle("GUI MainController");

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);

        primaryStage.show();
    }
}
