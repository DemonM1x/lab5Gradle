package org.example.exception;

import java.io.Serializable;

/**
 * Thrown if given field value does not suit Task restrictions
 */
public class InvalidInputValueException extends Exception implements Serializable {

    /**
     * Exceptions' default constructor
     *
     * @param message - this message will pop out in System.out stream if an exception
     */
    public InvalidInputValueException(String message) {
        super(message);
    }
}
