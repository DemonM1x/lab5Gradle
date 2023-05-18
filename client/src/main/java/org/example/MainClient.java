package org.example;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.nio.channels.SocketChannel;
import java.nio.channels.UnresolvedAddressException;
import java.util.Scanner;
import java.util.regex.Pattern;

public class MainClient {

    private static boolean reconnectionMode = false;
    private static int attempts = 0;
    private static final Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        while (true) {
            try {
                if (!reconnectionMode) {
                    getRequestHandlerProperties(scanner, InetAddress.getLocalHost());

                } else {
                    Thread.sleep(7 * 1000); // 7 секунд на переподключение
                }
                SocketChannel socketChannel = SocketChannel.open();
                socketChannel.connect(RequestHandler.getInstance().getRemoteHostSocketAddress());
                RequestHandler.getInstance().setRemoteHostSocketChannel(socketChannel);
                attempts = 0;
                RequestHandler.getInstance().setSocketStatus(true);
                System.out.println(RequestHandler.getInstance().getInformation());
                InputClientReader.openStream();
                break;

            } catch (UnknownHostException e) {
                throw new RuntimeException(e);
            } catch (InterruptedException e) {
                MessageHandler.displayToUser("Соединение было прервано во время бездействия. Перезапуск клиента.");
            } catch (UnresolvedAddressException e) {
                MessageHandler.displayToUser("Сервер с этим хостом не найден. Попробуйте снова.");
                main(args);
            } catch (IOException e) {
                MessageHandler.displayToUser("Сервер недоступен. Переподключение, попытка #" + (attempts + 1));
                reconnectionMode = true;
                if (attempts == 4) {
                    MessageHandler.displayToUser("Переподключение не удалось. Попробуйте подключиться позднее.");
                    System.exit(0);
                }
                attempts++;
                main(args);
            }
        }
    }
    private static boolean requestTypeOfAddress(Scanner scanner) {
        String answer;
        do{
            System.out.println("Do you want to specify the address of the remote host?" + "[\"y\", \"n\"]:");
            answer = scanner.nextLine();
        }while (!answer.equals("y") && !answer.equals("n"));
        return answer.equals("y");
    }

    private static int getPort(Scanner scanner){
        String arg;
        Pattern remoteHostPortPattern = Pattern.compile("^\\s*(\\d{1,5})\\s*");

        do {
            System.out.print("\nPlease, enter remote host port(1-65535): ");
            arg = scanner.nextLine();
        } while (!(remoteHostPortPattern.matcher(arg).find() && (Integer.parseInt(arg.trim()) < 65536)
                && (Integer.parseInt(arg.trim()) > 0)));

        return Integer.parseInt(arg.trim());
    }
    private static void getRequestHandlerProperties(Scanner scanner, InetAddress localHost) {
        InetAddress remoteHostAddress = localHost;
        if(requestTypeOfAddress(scanner)) {
            String arg;
            Pattern hostAddress = Pattern.compile("^\\s*([\\w.]+)\\s*");

            do{
                System.out.print("Please, enter remote host address! (Example: 5.18.215.199): ");
                arg = scanner.nextLine();
            } while (!hostAddress.matcher(arg).find());
            try {
                remoteHostAddress = InetAddress.getByName(arg.trim());
            } catch (UnknownHostException e) {
                System.out.println(
                        "\nThe program could not find the server by the specified address!\n " +
                                "The default address(localhost) will be used!");;
            }

        }
        RequestHandler.getInstance().setRemoteHostSocketAddress(
                new InetSocketAddress(remoteHostAddress, getPort(scanner)));
    }
}