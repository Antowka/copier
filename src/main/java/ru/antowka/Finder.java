package ru.antowka;

import ru.antowka.Entity.FileFound;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Anton Nik on 10.01.16.
 */
public class Finder extends SimpleFileVisitor<Path> {

    private PathMatcher matcher;
    private int numMatches = 0;
    private List<FileFound> result = new ArrayList<FileFound>();

    // Compares the glob pattern against
    // the file or directory name.
    void find(Path file) {
        Path name = file.getFileName();
        if (name != null && matcher.matches(name) && !Files.isDirectory(file)) {
            numMatches++;

            FileFound fileFound = new FileFound();
            fileFound.setFilePath(file);
            fileFound.setFileName(file.getFileName().toString());
            fileFound.setFileExt(file.getFileName().toString().substring(file.getFileName().toString().indexOf(".")));
            fileFound.setFileSize(file.toFile().length());
            fileFound.setFullPath(file.toString());

            result.add(fileFound);
        }
    }

    // Prints the total number of
    // matches to standard out.
    void done() {
        System.out.println("Matched: " + numMatches);
    }

    // Invoke the pattern matching
    // method on each file.
    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
        find(file);
        return FileVisitResult.CONTINUE;
    }

    // Invoke the pattern matching
    // method on each directory.
    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) {
        find(dir);
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult visitFileFailed(Path file, IOException exc) {
        System.err.println(exc);
        return FileVisitResult.CONTINUE;
    }

    public List<FileFound> findByParams(Path startingDir, String pattern){

        //Set pattern for search
        matcher = FileSystems
                .getDefault()
                .getPathMatcher("glob:" + pattern);

        //Try find files recursive in folders
        try {
            Files.walkFileTree(startingDir, this);
        } catch (IOException e) {
            e.printStackTrace();
        }
        done();

        return result;
    }
}
