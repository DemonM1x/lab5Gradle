package org.example;

import org.example.commands.ExitSaver;
import org.example.exception.FileLoadingException;
import org.example.exception.NoAccessToFileException;
import org.example.xmlUtils.XmlFileHandler;
import org.example.сollection.City;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.SocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.util.logging.Logger;

public class MainServer {
    private static final Logger logger = Logger.getLogger(MainServer.class.getCanonicalName());



    public static void main(String[] args) {
        logger.info("Entering server!");

        try (ServerSocketChannel serverSocket = ServerSocketChannel.open()) {
            int Port = 56432;
            SocketAddress address = new InetSocketAddress(Port);
            serverSocket.bind(address);
            logger.info("Server listening port "+ Port);
            XmlFileHandler xmlFileHandler = new XmlFileHandler(logger);
            xmlFileHandler.load();
            UniqueId id = new UniqueId();
            if (xmlFileHandler.get() != null) {
                for (City city : xmlFileHandler.get()) {
                    if (UniqueId.getCurrentID() < city.getId()) {
                        id.setId(city.getId());
                    }
                }
            }
            LocalDateBase localDateBase = new LocalDateBase(xmlFileHandler.get());
            Receiver receiver = new Receiver(localDateBase);
            CommandManager commandManager = new CommandManager(receiver);
            Deliver deliver = new Deliver(commandManager, id, logger);
            AcceptingConnections connect = new AcceptingConnections(serverSocket,logger, deliver);
            Runtime.getRuntime().addShutdownHook(new Thread(new ExitSaver(xmlFileHandler,receiver)));
            connect.start(xmlFileHandler, receiver);

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (FileLoadingException e) {
            throw new RuntimeException(e);
        } catch (NoAccessToFileException e) {
            throw new RuntimeException(e);
        }
    }
}