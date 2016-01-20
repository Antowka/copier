package ru.antowka.services;

import org.joox.Match;
import org.xml.sax.SAXException;
import ru.antowka.entity.Help;
import ru.antowka.entity.HelpCli;
import ru.antowka.entity.HelpGui;

import java.io.IOException;
import java.io.InputStream;

import static org.joox.JOOX.$;
import static org.joox.JOOX.ids;

/**
 * Created by Anton Nik on 20.01.16.
 */
public class HelpFabric {

    private Match xmlDB;
    private Help help;

    public HelpFabric(){

        //get xml with helps
        try {
            InputStream is = getClass().getResourceAsStream("/xml/helps.xml");
            xmlDB = $(is);
            is.close();
        } catch (IOException | SAXException e) {
            e.printStackTrace();
        }
    }

    /**
     * Return Help** Object by type
     *
     * @param type
     * @return
     */
    public Help getHelp(String type){

        switch(type){

            //create CLI Help
            case "cli":
                help = new HelpCli();
            break;

            //create GUI Help
            case "gui":
                help = new HelpGui();
            break;
        }

        Match result = xmlDB.find("error").filter(ids(type));
        help.setTitle(result.find("title").content());
        help.setDescription(result.find("description").content());

        return help;
    }
}
