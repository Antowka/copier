package ru.antowka;

import javafx.scene.control.Alert;
import org.joox.Match;
import org.xml.sax.SAXException;
import ru.antowka.Entity.Error;

import java.io.File;
import java.io.IOException;
import static org.joox.JOOX.*;

/**
 * Created by Anton Nik on 14.01.16.
 */
public class ErrorFabric {

    private Match xmlDB;

    ErrorFabric(){

        //get xml with errors
        try {
            xmlDB = $(new File("src/main/resources/xml/errors.xml"));
            String test = "";
        } catch (IOException | SAXException e) {
            e.printStackTrace();
        }
    }


    public Alert getError(Integer code){

        Error error = new Error();

        Match result = xmlDB.find("error").filter(ids(code.toString()));
        error.setHeader(result.find("header").content());
        error.setTitle(result.find("title").content());
        error.setDescription(result.find("description").content());

        return errorToAlert(error);
    }

    private Alert errorToAlert(Error error){

        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(error.getTitle());
        alert.setHeaderText(error.getHeader());
        alert.setContentText(error.getDescription());

        return alert;
    }
}
