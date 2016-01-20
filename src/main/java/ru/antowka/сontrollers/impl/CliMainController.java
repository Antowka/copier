package ru.antowka.сontrollers.impl;

import ru.antowka.entity.FileFound;
import ru.antowka.services.CopyFiles;
import ru.antowka.services.ErrorFabric;
import ru.antowka.services.Finder;
import ru.antowka.services.HelpFabric;
import ru.antowka.сontrollers.MainController;

import java.util.List;

/**
 * Created by Anton Nik on 19.01.16.
 */
public class CliMainController implements MainController {

    private ErrorFabric ef;
    private HelpFabric hf;
    private Finder finder;
    private CopyFiles copyFiles;
    private String[] args;

    public CliMainController(String[] args, ErrorFabric ef, HelpFabric hf, Finder finder, CopyFiles copyFiles){

        this.ef = ef;
        this.hf = hf;
        this.finder = finder;
        this.copyFiles = copyFiles;
    }

    @Override
    public void initialize(){

    }

    /**
     * Method find files by params
     *
     * @return
     */
    public List<FileFound> findFiles(String sourcePath, String pattern){

        //todo - make mathod
        return null;
    }

    private int copyFiles() {
        return 0;
    }

    private String showHelp(){
        return null;
    }
}
