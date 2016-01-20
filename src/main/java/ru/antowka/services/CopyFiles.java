package ru.antowka.services;

import ru.antowka.entity.FileFound;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * Created by Anton Nik on 12.01.16.
 */
public class CopyFiles {

    private int countCopyFiles = 0;

    /**
     * Copy files
     *
     * @param files
     * @param targetFolder
     * @return
     */
    public int copyFilesByPath(List<FileFound> files, Path targetFolder){

        //reset counter
        countCopyFiles = 0;

        if (files.size() > 0) {

            //Create directory if not exist
            if (!Files.exists(targetFolder)) {
                try {
                    Files.createDirectory(targetFolder);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            //Copy files
            files.stream().forEach(fileFound -> {

                Path newPath = Paths.get(targetFolder.toString() + "/" + fileFound.getFileName());

                try {
                    if (!Files.exists(newPath)) {
                        Files.copy(fileFound.getFilePath(), newPath);
                        countCopyFiles++;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }

        return countCopyFiles;
    }
}
