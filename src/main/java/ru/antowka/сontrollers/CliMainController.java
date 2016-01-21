package ru.antowka.сontrollers;

import ru.antowka.entity.Error;
import ru.antowka.entity.FileFound;
import ru.antowka.entity.Help;
import ru.antowka.services.CopyFiles;
import ru.antowka.services.ErrorFabric;
import ru.antowka.services.Finder;
import ru.antowka.services.HelpFabric;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * Created by Anton Nikanorov on 19.01.16.
 */
public class CliMainController {

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
        this.args = args;
    }

    /**
     * Method find files for display result
     *
     * @return List<FileFound>
     */
    public List<FileFound> find(){

        //check on exist all arguments for copy
        if(args.length < 3 || args[1] == null || args[1].isEmpty() || args[2] == null || args[2].isEmpty()) {

            Error error = ef.getError("cli", 0);
            error.show();
            System.exit(0);
        }

        Path pathSource = Paths.get(args[1]);

        Boolean folderIsExist = Files.exists(pathSource);

        if(!folderIsExist) {

            Error error = ef.getError("cli", 1);
            error.show();
            System.exit(0);
        }

        return finder.findByParams(pathSource, args[2]);
    }

    /**
     * Method for start find and copy process
     *
     * @return int
     */
    public int copy(){

        //check on exist all arguments for copy
        if(args.length != 4 || args[2] == null || args[2].isEmpty() || args[3] == null || args[3].isEmpty()) {

            Error error = ef.getError("cli", 0);
            error.show();
            System.exit(0);
        }

        return copyFiles.copyFilesByPath(finder.findByParams(Paths.get(args[1]), args[3]), Paths.get(args[2]));
    }

    /**
     * Method display help info
     *
     * @return Help
     */
    public Help help(){
        return hf.getHelp("cli");
    }
}
