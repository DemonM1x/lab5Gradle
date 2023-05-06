package org.example;

import org.example.xmlUtils.XmlFileHandler;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

public class AcceptingConnections {

    private final Deliver deliver;
    private XmlFileHandler xmlFileHandler;
    private Receiver receiver;

    private final ServerSocketChannel serverSocket;
    private Selector selector;
    private Map<SocketChannel, ByteBuffer> buffers;
    private Map<SocketChannel, InetSocketAddress> addresses;
    private final int bufferSize = 4096;

    private final Logger logger;


    public AcceptingConnections(ServerSocketChannel aServerSocket, Logger logger, Deliver aDeliver) throws IOException {
        serverSocket = aServerSocket;
        deliver = aDeliver;
        this.logger = logger;
        selector = Selector.open();
        buffers = new HashMap<>();
        addresses = new HashMap<>();
        serverSocket.configureBlocking(false);
        serverSocket.register(selector, SelectionKey.OP_ACCEPT);
    }

    public void start(XmlFileHandler xmlFileHandler, Receiver receiver) {
        this.xmlFileHandler = xmlFileHandler;
        this.receiver = receiver;

        try {
            while (true) {
                selector.select();
                Set<SelectionKey> keys = selector.selectedKeys();
                Iterator<SelectionKey> iter = keys.iterator();
                while (iter.hasNext()) {
                    SelectionKey key = iter.next();
                    if (key.isAcceptable()) {
                        SocketChannel socketChannel = serverSocket.accept();
                        logger.info("Сервер соединился с" + socketChannel.getLocalAddress());
                        socketChannel.configureBlocking(false);
                        socketChannel.register(selector, SelectionKey.OP_READ);
                        buffers.put(socketChannel, ByteBuffer.allocate(bufferSize));
                    } else if (key.isReadable()) {
                        SocketChannel socketChannel = (SocketChannel) key.channel();
                        ByteBuffer buffer = buffers.get(socketChannel);
                        int bytesRead = socketChannel.read(buffer);
                        if (bytesRead == -1) {
                            addresses.remove(socketChannel);
                            buffers.remove(socketChannel);
                            socketChannel.close();
                            continue;
                        }
                        if (addresses.get(socketChannel) == null) {
                            InetSocketAddress address = (InetSocketAddress) socketChannel.getRemoteAddress();
                            addresses.put(socketChannel, address);
                        }
                        ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(buffer.array()));
                        Request request = (Request) ois.readObject();
                        buffer.clear();
                        logger.info("Server received command: " + request.toString());
                        deliver.answer(request, socketChannel);

                    }
                    iter.remove();
                }
            }
        } catch (IOException e) {
            logger.info("Some problem's with network!");
        } catch (ClassNotFoundException e) {
            logger.info("Client sent outdated request!");
        }
//        catch (InterruptedException e) {
//            ExitSaver exitSaver = new ExitSaver(xmlFileHandler, receiver);
//            exitSaver.run();
//        }

    }
}