package org.example;

import org.example.service.InputService;

import java.util.Scanner;

/**
 * Main class creates manager objects and
 * reads the entered data
 */
public class Main {
    /**
     * the method reads the entered data and calls executeCommand from CommandManager
     * @param args
     */
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