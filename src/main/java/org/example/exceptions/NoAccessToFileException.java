package org.example.exceptions;

import java.io.File;

/**
 * it is thrown if there is no access to the file
 */
public class NoAccessToFileException extends Exception{
    public File file;
    public NoAccessToFileException(File file){
        this.file = file;
    }
}
