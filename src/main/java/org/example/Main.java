package org.example;

import org.example.Service.InputService;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        var mesHandler = new MessageHandler();
        var collection = new CollectionManager(new XmlFileHandler(), mesHandler);
        var inputService = new InputService(mesHandler);
        var commandManager = new CommandManager(collection, mesHandler, inputService);
        var commandMessageHandler =  commandManager.getMessageHandler();


        while (true){
            var scanner = new Scanner(System.in);
            mesHandler.displayToUser("Enter a command: ");
            try {
                commandManager.executeCommand(scanner.nextLine());
            }catch (Exception ex) {
                commandMessageHandler.displayToUser("ctrl d");
                break;
            }
        }

    }
}