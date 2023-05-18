package org.example;


import org.example.сollection.City;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;

public class RequestHandler {
    private static RequestHandler instance;
    private InetSocketAddress socketAddress;
    private SocketChannel socketChannel;

    private boolean socketStatus;

    public static RequestHandler getInstance(){
        if(instance == null) instance = new RequestHandler();
        return instance;
    }

    private RequestHandler() {

    }

    public String send(Request request) {
        try {
            ClientWorker clientWorker = new ClientWorker(socketChannel);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(4096);
            ObjectOutputStream outputStream = new ObjectOutputStream(byteArrayOutputStream);
            outputStream.writeObject(request);
            return clientWorker.sendRequest(byteArrayOutputStream.toByteArray());
        }  catch (IOException e) {
            return "Request can not be serialized, call programmer";
        }
    }

    public String send(Request request , City city){
        if(city != null) {
            request.addCity(city);
            return send(request);
        }
        return "City isn't exist";
    }

    public void setRemoteHostSocketAddress(InetSocketAddress aSocketAddress){
        socketAddress = aSocketAddress;
    }
    public InetSocketAddress getRemoteHostSocketAddress(){
        return socketAddress;
    }
    public void setRemoteHostSocketChannel(SocketChannel socketChannel){
        this.socketChannel = socketChannel;
    }
    public String getInformation(){
        return "Connection\n" +  "remote host address: " + socketAddress;
    }

    public void setSocketStatus(boolean b) {
        socketStatus = b;
    }

    public boolean getSocketStatus(){
        return socketStatus;
    }
}
