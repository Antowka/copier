package ru.antowka;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * Created by Anton Nik on 10.01.16.
 */
public class Copier {

    public static void main(String[] args) {

        if(args[0].equals("--help")){
            System.out.println("******************************* HELP **************************************");
            System.out.println("*                                                                         *");
            System.out.println("* java -jar copier.jar {source path} {destination path} {pattern (*.txt)} *");
            System.out.println("*                                                                         *");
            System.out.println("******************************* END  **************************************");
        } else {

            //home/anton/Desktop/test
            Path startingDir = Paths.get(args[0]);

            //home/anton/Desktop/test1
            Path copyTo = Paths.get(args[1]);

            //*.fb2
            String pattern = args[2];

            Finder finder = new Finder(pattern);

            try {
                Files.walkFileTree(startingDir, finder);
            } catch (IOException e) {
                e.printStackTrace();
            }
            finder.done();

            List<Path> foundFiles = finder.getResult();
            if (foundFiles.size() > 0) {

                //Create directory if not exist
                if (!Files.exists(copyTo)) {
                    try {
                        Files.createDirectory(copyTo);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                //Copy files
                foundFiles.stream().forEach(path -> {

                    Path newPath = Paths.get(copyTo.toString() + "/" + path.getFileName().toString());

                    try {
                        Files.copy(path, newPath);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
            }
        }
    }
}
