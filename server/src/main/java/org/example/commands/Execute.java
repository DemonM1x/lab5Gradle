package org.example.commands;

import org.example.Request;
import org.example.Response;

public interface Execute {
    Response execute(Request aRequest);
}
