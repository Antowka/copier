package ru.antowka.init.impl;

import ru.antowka.entity.FileFound;
import ru.antowka.entity.Help;
import ru.antowka.services.CopyFiles;
import ru.antowka.services.ErrorFabric;
import ru.antowka.services.Finder;
import ru.antowka.services.HelpFabric;
import ru.antowka.сontrollers.CliMainController;
import ru.antowka.init.Initializer;

import java.util.List;

/**
 * Created by Anton Nik on 19.01.16.
 */
public class CliInitializer implements Initializer {

    public void initilize(String[] args) {
        CliMainController cliController = new CliMainController(
                args,
                new ErrorFabric(),
                new HelpFabric(),
                new Finder(),
                new CopyFiles());


        switch (args[0]){

            //display found files with path
            case "-find":

                List<FileFound> filesFound = cliController.find();
                System.out.println("-------------------------------------");
                System.out.println("| File Name | File Size | File Path |");
                System.out.println("-------------------------------------");

                filesFound.stream().forEach(file ->
                    System.out.println("| " + file.getFileName() + " | "  + file.getFileSize() + " | " + file.getFullPath() + " |")
                );

                System.out.println("-------------------------------------");
            break;

            //copy files
            case "-copy":
                System.out.println("Copied: " + cliController.copy());
            break;

            //show help
            default:
                Help help = cliController.help();
                help.show();
            break;
        }
    }
}
