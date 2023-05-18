package org.example;

import java.util.NoSuchElementException;
import java.util.Scanner;

/**A class that reads input from the console*/
public class InputClientReader {
    private static AvailableCommands availableCommands;
    /* вызываем входящий поток */
    private static Scanner inputReader = new Scanner(System.in);
    private final MessageHandler messageHandler;

    public InputClientReader(MessageHandler messageHandler) {
        this.messageHandler = messageHandler;
    }

    public static Scanner getInputReader() {
        return inputReader;
    }

    public static DataInOutStatus openStream() {
        try {
            MessageHandler.displayToUser("Type commands");
            String inputData = inputReader.nextLine();
            while (true) {

                inputData = inputData.trim();
                DataInOutStatus checkedCommand = new CommandValidator().validate(inputData);
                if (checkedCommand != DataInOutStatus.SUCCESSFULLY) {
                    MessageHandler.displayToUser(checkedCommand.getName());
                } if (inputData.equals("exit")){
                    System.out.println("Good buy");
                    break;
                }
                if (RequestHandler.getInstance().getSocketStatus()) {
                    MessageHandler.displayToUser("Type commands");
                    ScriptReader.clearHistory();
                    ScriptReader.setExecuteStatus(false);
                    inputData = inputReader.nextLine();
                }
                else {
                    return DataInOutStatus.FAILED;
                }

                }

            return DataInOutStatus.SUCCESSFULLY;
        } catch (NullPointerException | NoSuchElementException e) {
            MessageHandler.displayToUser("You pressed ctrl + D");
            return DataInOutStatus.FAILED;
        }
    }
}
