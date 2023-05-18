package org.example;
import java.util.ArrayList;

public class ScriptReader {
    private static ArrayList<String> historyOfFiles = new ArrayList<>();
    private static ArrayList<String> readedCommands = new ArrayList<String>();
    private static Integer currentCommand;
    private static String file;
    private static boolean executeStatus = false;


    public static void setFile(String file){
        ScriptReader.file = file;
    }
    public static void setCurrentCommand(Integer currentCommand){
        ScriptReader.currentCommand = currentCommand;
    }

    public static Integer getCurrentCommand() {
        return currentCommand;
    }
    public static ArrayList<String> getReadedCommands() {
        return readedCommands;
    }
    public static void clearHistory(){
        historyOfFiles = new ArrayList<String>();
    }
    public static void setExecuteStatus(boolean executeStatus1){
        executeStatus = executeStatus1;
    }

    public static boolean getExecuteStatus() {
        return executeStatus;
    }

    public static void execute() {
        StringBuilder execution = new StringBuilder();
        if (historyOfFiles.contains(file)) {
            historyOfFiles = new ArrayList<>();
            MessageHandler.displayToUser(execution.append("Recursion was detected in your files").toString());

        } else {
            historyOfFiles.add(file);
            currentCommand = 0;
            readedCommands = new FileReader().readFile(file);
            int iter = 0;

            if (readedCommands.size() != 0) {
                setExecuteStatus(true);
                while (iter < readedCommands.size()) {
                    String commandLine = readedCommands.get(iter);
                    if (new CommandValidator().validate(commandLine) != DataInOutStatus.SUCCESSFULLY) {
                        MessageHandler.displayToUser(execution.append("Check correctness of commands in your script '" + file
                                + "'. Failed.\nSome commands can be completed.").toString());
                    }
                    currentCommand++;
                    iter = currentCommand;
                }
                setExecuteStatus(false);
            } else {
                MessageHandler.displayToUser(execution.append("There are some errors with file '" + file + "'. Try again.").toString());
            }
        }
    }
}
