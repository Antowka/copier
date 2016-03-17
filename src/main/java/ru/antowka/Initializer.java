package ru.antowka;

import ru.antowka.init.impl.CliInitializer;
import ru.antowka.init.impl.GuiInitializer;

/**
 * Created by Anton Nik on 17.01.16.
 */
public class Initializer  {

    public static void main(String[] args) {

        if(args.length == 0) {

            //GUI Version
            new GuiInitializer().initilize(args);
        } else {

            //Console Version
            new CliInitializer().initilize(args);
        }
    }
}
