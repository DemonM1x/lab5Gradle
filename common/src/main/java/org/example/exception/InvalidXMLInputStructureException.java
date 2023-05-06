package org.example.exception;


/**
 * Exception class offers to a major contradiction while parsing
 */
public class InvalidXMLInputStructureException extends Exception {
    /**
     * Exceptions' default constructor
     *
     * @param message - this message will pop out in System.out stream if an exception
     */
    public InvalidXMLInputStructureException(String message) {
        super(message);
    }
}