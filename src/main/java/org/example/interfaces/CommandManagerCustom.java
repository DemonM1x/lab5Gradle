package org.example.interfaces;

import org.example.Collection.City;

import org.example.MessageHandler;
import org.example.Service.InputService;
import java.util.List;

public interface CommandManagerCustom {
    /** executes given command */
    boolean executeCommand(String userInput);

    /** gets the info about each command */
    List<String> getCommandsInfo();
    /** gets input service */
    InputService getInputService();
    /** gets collection manager */
    CollectionCustom<City> getCollectionManager();
    /** gets message handler */
    MessageHandler getMessageHandler();

}
