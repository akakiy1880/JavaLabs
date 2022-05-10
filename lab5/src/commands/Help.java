package commands;

import interfaces.Command;
import interfaces.CommandWithArguments;
import logging.Log;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class Help implements Command {

    private HashMap<String, Command> commandsWithoutArguments;
    private HashMap<String, CommandWithArguments> commandWithArguments;

    public Help(HashMap<String, Command> commandsWithoutArguments, HashMap<String, CommandWithArguments> commandWithArguments) {
        this.commandsWithoutArguments = commandsWithoutArguments;
        this.commandWithArguments = commandWithArguments;
    }

    @Override
    public void execute() {
        for (Map.Entry<String, Command> entry : commandsWithoutArguments.entrySet()) {
            Log.info(String.format("%-30s", entry.getKey()) + " " + entry.getValue().getDescription().toLowerCase(Locale.ROOT));
        }

        for (Map.Entry<String, CommandWithArguments> entry : commandWithArguments.entrySet()) {
            Log.info(String.format("%-30s", entry.getKey()) + " " + entry.getValue().getDescription().toLowerCase(Locale.ROOT));
        }
    }

    @Override
    public String getDescription() {
        return "Вывести справку по доступным командам";
    }
}
