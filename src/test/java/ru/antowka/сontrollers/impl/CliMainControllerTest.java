package ru.antowka.сontrollers.impl;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import ru.antowka.entity.FileFound;
import ru.antowka.services.CopyFiles;
import ru.antowka.services.ErrorFabric;
import ru.antowka.services.Finder;
import ru.antowka.services.HelpFabric;
import ru.antowka.сontrollers.CliMainController;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

/**
 * Created by Anton Nik on 20.01.16.
 */
@RunWith(MockitoJUnitRunner.class)
public class CliMainControllerTest {

    CliMainController cliMainController;

    @Mock
    ErrorFabric ef;

    @Mock
    HelpFabric hf;

    @Mock
    CopyFiles copyFiles;

    @Mock
    Finder finder;

    @Before
    public void testInitialize() throws Exception {

    }

    @Test
    public void testFindFiles(){

        String[] args = {"/source/test", "/target/test", "*.txt"};
        cliMainController = new CliMainController(args, ef, hf, finder, copyFiles);

        Path sourcePath = Paths.get(args[0]);

        String pathTestFile = "/source/test/test.fb2";

        FileFound fileFound = new FileFound();
        fileFound.setChecked(true);
        fileFound.setFileExt(".fb2");
        fileFound.setFileName("test.fb2");
        fileFound.setFilePath(Paths.get(pathTestFile));
        fileFound.setFullPath(pathTestFile);

        List<FileFound> fileFounds = new ArrayList<>();
        fileFounds.add(fileFound);


        when(finder.findByParams(sourcePath, args[2])).thenReturn(fileFounds);
        List<FileFound> filesResult = cliMainController.findFiles(args[0], args[2]);
        assertTrue(filesResult.get(0).getFullPath().equals(pathTestFile));
    }
}