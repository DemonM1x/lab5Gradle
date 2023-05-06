package org.example.commands;

import org.example.Request;
import org.example.Response;

import java.util.ArrayList;

public class ExecuteScriptCommand extends AbstractCommand implements Execute {

    public ExecuteScriptCommand() {
        super("execute_script", "read and execute the script from the specified file." +
                " The script contains commands in the same form in which they are entered by the user" +
                " in interactive mode", "{file_name}");
    }


    @Override
    public Response execute(Request request) {
       return null;
    }
}
