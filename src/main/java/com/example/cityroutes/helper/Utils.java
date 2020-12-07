package com.example.cityroutes.helper;

import java.io.File;
import java.net.URL;

/**
 * Utility class for common functionality
 */
public class Utils {
    /**
     * Load file from classpath.Throws an exception if not found
     * @param fileName
     * @return
     */
    public static File loadFile(String fileName) {
        ClassLoader classLoader = Utils.class.getClassLoader();
        URL resource = classLoader.getResource(fileName);
        if (resource == null) {
            throw new IllegalArgumentException("Input File is not found!");
        } else {
            File file = new File(resource.getFile());
            return file;
        }
    }
}
