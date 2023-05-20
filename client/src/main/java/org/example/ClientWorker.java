package org.example;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class ClientWorker {

    private SocketChannel socketChannel;
    private final ResponseHandler responseHandler;

    public ClientWorker(SocketChannel socketChannel) {
        responseHandler = ResponseHandler.getInstance();
        this.socketChannel = socketChannel;

    }

    public String sendRequest(byte[] dataToSend) {
        try {
            ByteBuffer buffer = ByteBuffer.wrap(dataToSend);
            socketChannel.write(buffer);
            return responseHandler.receive(receiveAnswer());
        } catch (IOException exception) {
            RequestHandler.getInstance().setSocketStatus(false);
            return ("\tCommand didn't send, try again!\n");

        }
    }

    public String receiveAnswer() {
        long timeStart = System.currentTimeMillis();
        ByteBuffer buffer = ByteBuffer.allocate(4096);

        try {
            socketChannel.read(buffer);
            if (buffer.position() != 0) {
                return responseHandler.receive(buffer);
            }
        } catch (IOException ignored) {
            RequestHandler.getInstance().setSocketStatus(false);
            return ("Server doesn't exist!\nWait until the server is up and running ");
        }
        return ("Server doesn't send information");
    }
}