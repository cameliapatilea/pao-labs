package Helpers;

import java.nio.file.Path;
import java.nio.file.Paths;

public class FileHelper {
    public static String getFullPath(String filename){
        Path pathToFile = Paths.get(filename);
         return pathToFile.toAbsolutePath().toString();
    }
}
