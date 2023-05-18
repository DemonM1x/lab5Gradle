package org.example;

import org.example.validatorClient.ValidateAbstract;
import org.example.validatorClient.ValidatorManager;

import java.util.ArrayList;
import java.util.LinkedHashMap;

/**The class from which we get data for the collection element*/

public class ObjectReading {
    public ArrayList<String> objread(String command , LinkedHashMap<String, String> fields) {
        AvailableCommands availableCommands = new AvailableCommands();
        ArrayList<String> extraArgs = new ArrayList<>();
        try {
            MessageHandler.displayToUser("Type extra data of object");
            if (!ScriptReader.getExecuteStatus()) {
                ValidatorManager validatorManager = new ValidatorManager();
                if(!availableCommands.scriptArgumentCommand.contains(command)) {
                    int iter = 1;
                    while (iter < fields.keySet().size()) {
                        String field = fields.keySet().toArray()[iter].toString();
                        ValidateAbstract<?> validator = validatorManager.getValidator(field);
                        if (validator == null) {
                            iter++;
                            continue;
                        }
                        if (!field.equals("StudyGroup.id") && !field.equals("StudyGroup.creationDate")) {
                            MessageHandler.displayToUser("Type '" + field + "'. Type of '" + field + "' is "
                                    + fields.get(field));
                        }
                        String valueOfField = InputClientReader.getInputReader().nextLine().trim();


                        /*проверяем данные которые пришли на вход*/
                        if (validator.validate(valueOfField)) {
                            extraArgs.add(valueOfField);
                            iter++;
                        } else {
                            MessageHandler.displayToUser("You've typed wrong value of field.");
                        }
                    }
                }
            } else {
                if (ScriptReader.getReadiedCommands().size() - ScriptReader
                        .getCurrentCommand() < ScriptReader.getCurrentCommand() + fields.size() - 3) {
                    return new ArrayList<>();
                }
                int startValue = ScriptReader.getCurrentCommand() + 1;
                for (int iter1 = startValue; iter1 < startValue + fields.size() - 2; iter1++) {
                    extraArgs.add(ScriptReader.getReadiedCommands().get(iter1).trim());
                    ScriptReader.setCurrentCommand(ScriptReader.getCurrentCommand() + 1);
                }
            }
        } catch (NullPointerException e) {
            MessageHandler.displayToUser("\nInterrupting input stream.\n");
            extraArgs = new ArrayList<>();
        }
        return extraArgs;
    }
}