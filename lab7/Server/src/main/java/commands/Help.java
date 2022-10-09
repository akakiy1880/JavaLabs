package commands;

import interfaces.Command;
import interfaces.CommandWithArguments;
import packer.Pack;

import java.util.HashMap;
import java.util.Map;

public class Help implements Command {

    private HashMap<String, Command> commandsWithoutArguments;
    private HashMap<String, CommandWithArguments> commandWithArguments;

    public Help(HashMap<String, Command> commandsWithoutArguments, HashMap<String, CommandWithArguments> commandWithArguments) {
        this.commandsWithoutArguments = commandsWithoutArguments;
        this.commandWithArguments = commandWithArguments;
    }

    @Override
    public Pack execute(Pack pack) {
        String response = "";
        for (Map.Entry<String, Command> entry : commandsWithoutArguments.entrySet()) {
            response += entry.getKey() + " " + entry.getValue().getDescription() + "\n";
        }

        for (Map.Entry<String, CommandWithArguments> entry : commandWithArguments.entrySet()) {
            response += entry.getKey() + " " + entry.getValue().getDescription() + "\n";
        }
        pack.pack(response);
        return pack;
    }

    @Override
    public String getDescription() {
        return "Вывести справку по доступным командам";
    }
}
