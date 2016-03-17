package ru.antowka.services;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.antowka.entity.FileFound;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.junit.Assert.*;


/**
 * Created by Anton Nik on 20.01.16.
 */
public class CopierTest {

    Finder finder;
    CopyFiles copyFiles;
    List<Path> files = new ArrayList<>();
    Path tempDir = Paths.get(System.getProperty("java.io.tmpdir"));
    String pattern = "fb2";
    List<FileFound> filesFounds;
    List<FileFound> filesFoundsCopy;
    Path targetDir = Paths.get(tempDir + "/test7271734");

    @Before
    public void setUp() {

        //create source folder
        for(int i = 5; i > 0; i--){
            try {
                files.add(Files.createTempFile(null, "." + pattern));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //create Finder
        finder = new Finder();
        copyFiles = new CopyFiles();
    }

    @After
    public void setDown() {

        //remove test files
        if(filesFounds != null) {
            filesFounds.stream().forEach(file -> {
                try {
                    Files.delete(file.getFilePath());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }

        if(filesFoundsCopy != null) {
            try {
                filesFoundsCopy.stream().forEach(file -> {
                    try {
                        Files.delete(file.getFilePath());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
                Files.delete(targetDir);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void testFind(){

        filesFounds = finder.findByParams(tempDir, "*." + pattern);
        List<Boolean> test = new ArrayList<>();
        files.stream().forEach(file -> {
            if(!filesFounds.stream().anyMatch(f -> Objects.equals(f.getFilePath(), file))) {
                test.add(false);
            }
        });

        assertTrue(test.isEmpty());
    }

    @Test
    public void testCopy(){

        filesFoundsCopy = finder.findByParams(tempDir, "*." + pattern);
        copyFiles.copyFilesByPath(filesFoundsCopy, targetDir);

        filesFoundsCopy = finder.findByParams(targetDir, "*." + pattern);
        List<Boolean> test = new ArrayList<>();
        files.stream().forEach(file -> {
            if(!filesFoundsCopy.stream().anyMatch(f -> Objects.equals(f.getFilePath(), file))) {
                test.add(false);
            }
        });

        assertTrue(test.isEmpty());
    }
}