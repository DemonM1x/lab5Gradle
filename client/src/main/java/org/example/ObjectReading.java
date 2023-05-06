package org.example;

import org.example.validatorClient.ValidateAbstract;
import org.example.validatorClient.ValidatorManager;

import java.util.ArrayList;
import java.util.LinkedHashMap;

/**The class from which we get data for the collection element*/

public class ObjectReading {
    private static AvailableCommands availableCommands;
    public ArrayList<String> objread(String command , LinkedHashMap<String, String> fields) {
        availableCommands = new AvailableCommands();
        ArrayList<String> extraArgs = new ArrayList<String>();
        try {
            MessageHandler.displayToUser("Type extra data of object");
            if (!availableCommands.noArgumentCommands.contains(command)) {
                ValidatorManager validatorManager = new ValidatorManager();
                if(!availableCommands.scriptArgumentCommand.contains(command)) {
                    int iter = 1;
                    /*идем по всем полям и ищем валидаторы */
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
            }
        } catch (NullPointerException e) {
            MessageHandler.displayToUser("\nInterrupting input stream.\n");
            extraArgs = new ArrayList<String>();
        }
        return extraArgs;
    }
}