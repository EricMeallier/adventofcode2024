package fr.meallier.adventofcode;

import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Utils {

    @SuppressWarnings("resource")
    static String[] readFile(String filepath) throws Exception {
        return Files.lines(Paths.get(filepath)).toArray(String[]::new);
    }

    @SuppressWarnings("resource")
    static String[] readFile(URI filepath) throws Exception {
        return Files.lines(Paths.get(filepath)).toArray(String[]::new);
    }
}
