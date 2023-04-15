package org.example.exceptions;

/**
 * it is thrown if an error occurred with the download from the file
 */
public class FileLoadingException extends Exception{
    public FileLoadingException(String message){
        super(message);
    }
}
