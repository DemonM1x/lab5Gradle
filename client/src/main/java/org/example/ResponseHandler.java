package org.example;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.ByteBuffer;

public class ResponseHandler {

    private static ResponseHandler instance;

    private ResponseHandler() {}


    public static ResponseHandler getInstance(){
        if(instance == null) instance = new ResponseHandler();
        return instance;
    }

    public String receive(ByteBuffer buffer) {
        try {
            ObjectInputStream inputStream = new ObjectInputStream(new ByteArrayInputStream(buffer.array()));
            Response response = (Response) inputStream.readObject();
            return response.toString();
        } catch (ClassNotFoundException | IOException e){
            return "FAILED";
        }
    }

    public String receive(String information){
        return information;
    }
}

