package org.example.Exceptions;

import java.io.File;

public class NoAccessToFileException extends Exception{
    public File file;
    public NoAccessToFileException(File file){
        this.file = file;
    }
}
