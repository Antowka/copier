package ru.antowka.services;

import javafx.scene.control.Alert;
import org.joox.Match;
import org.xml.sax.SAXException;
import ru.antowka.entity.Error;

import java.io.IOException;
import java.io.InputStream;

import static org.joox.JOOX.*;

/**
 * Created by Anton Nik on 14.01.16.
 */
public class ErrorFabric {

    private Match xmlDB;

    public ErrorFabric(){

        //get xml with errors
        try {
            InputStream is = getClass().getResourceAsStream("/xml/errors.xml");
            xmlDB = $(is);
            is.close();
        } catch (IOException | SAXException e) {
            e.printStackTrace();
        }
    }

    public Error getError(Integer code){

        Match result = xmlDB.find("error").filter(ids(code.toString()));
        Error error = new Error();
        error.setHeader(result.find("header").content());
        error.setTitle(result.find("title").content());
        error.setDescription(result.find("description").content());

        return error;
    }
}
