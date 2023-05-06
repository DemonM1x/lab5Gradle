package org.example;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class ClientWorker {

    private final InetSocketAddress socketAddress;
    private SocketChannel socketChannel;
    private final ResponseHandler responseHandler;

    public ClientWorker(InetSocketAddress aSocketAddress) {
        responseHandler = ResponseHandler.getInstance();
        socketAddress = aSocketAddress;
        try {
            socketChannel = SocketChannel.open();
            socketChannel.connect(socketAddress);
        } catch (IOException e) {
            System.out.println("Some problem's with network!");
        }
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

        while (true) {
            if ((System.currentTimeMillis() - timeStart) < 5000) {
                try {
                    socketChannel.read(buffer);
                    if (buffer.position() != 0) {
                        return responseHandler.receive(buffer);
                    }
                } catch (IOException ignored) {
                    return ("Server doesn't exist!\nWait until the server is up and running ");
                }
            } else {
                return ("Server isn't available at the moment! " +
                        "Please, select another remote host!\n");
            }
        }
    }
}