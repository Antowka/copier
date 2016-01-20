package ru.antowka.init.impl;

import ru.antowka.services.CopyFiles;
import ru.antowka.services.ErrorFabric;
import ru.antowka.services.Finder;
import ru.antowka.services.HelpFabric;
import ru.antowka.сontrollers.MainController;
import ru.antowka.сontrollers.impl.CliMainController;
import ru.antowka.init.Initializer;

/**
 * Created by Anton Nik on 19.01.16.
 */
public class CliInitializer implements Initializer {

    public void initilize(String[] args) {
        MainController mainController = new CliMainController(
                args,
                new ErrorFabric(),
                new HelpFabric(),
                new Finder(),
                new CopyFiles());

        mainController.initialize();
    }
}
