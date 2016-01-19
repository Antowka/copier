package ru.antowka.init.impl;

import ru.antowka.Controllers.MainController;
import ru.antowka.Controllers.impl.CliMainController;
import ru.antowka.init.Initializer;

/**
 * Created by Anton Nik on 19.01.16.
 */
public class CliInitializer implements Initializer {

    public void initilize(String[] args) {
        MainController mainController = new CliMainController();
    }
}
