package org.example;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.logging.Logger;

public class Deliver {
    private final CommandManager commandManager;
    private final UniqueId uniqueId;
    private final Logger logger;

    public Deliver(CommandManager aCommandManager, UniqueId uniqueId, Logger aLogger) {
        if (aCommandManager == null || uniqueId == null || aLogger == null) {
            throw new NullPointerException("One or more arguments are null");
        }
        commandManager = aCommandManager;
        this.uniqueId = uniqueId;
        logger = aLogger;
    }

    public void answer(Request request, SocketChannel client){


        try {

            Response response = commandManager.execute(uniqueId.getNewId(request));
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(response);
            objectOutputStream.flush();
            byte[] b = byteArrayOutputStream.toByteArray();
            ByteBuffer buffer = ByteBuffer.wrap(b);
            client.write(buffer);
        }catch (IOException e){
            try {
                client.close();
            }catch (IOException e1){
                throw new RuntimeException(e1);
            }
        }
    }
}
