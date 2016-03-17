package ru.antowka.entity;

import javafx.scene.control.Alert;

/**
 * Created by Anton Nik on 20.01.16.
 */
public class ErrorGui extends Error{

    /**
     * Show alert-error on GUI
     */
    public void show(){

        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(description);
        alert.showAndWait();
    }
}
