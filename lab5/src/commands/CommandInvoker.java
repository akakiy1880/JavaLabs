package commands;

import collectionworker.CollectionManager;
import interfaces.Command;
import interfaces.CommandWithArguments;
import logging.Log;
import utils.HumanBeingFieldReader;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Locale;
import java.util.Scanner;

public class CommandInvoker {
    Scanner in;
    private HashMap<String, Command> commandWithoutArguments;
    private HashMap<String, CommandWithArguments> commandWithArguments;
    private CollectionManager collectionManager;
    HumanBeingFieldReader humanBeingFieldReader;

    public CommandInvoker(CollectionManager collectionManager, Scanner in, HumanBeingFieldReader humanBeingFieldReader) {
        this.collectionManager = collectionManager;
        this.in = in;
        this.humanBeingFieldReader = humanBeingFieldReader;
        commandWithoutArguments = new HashMap<>();
        commandWithArguments = new HashMap<>();
        this.registerCommands();
    }


    private void registerCommands() {
        register("help", new Help(commandWithoutArguments, commandWithArguments));
        register("info", new Info(collectionManager));
        register("show", new Show(collectionManager));
        register("add", new Add(collectionManager));
        registerWithArgument("update", new Update(collectionManager, in));
        registerWithArgument("remove_by_id", new RemoveById(collectionManager, in));
        register("clear", new Clear(collectionManager));
        register("save", new Save(collectionManager));
        register("remove_head", new RemoveHead(collectionManager));
        registerWithArgument("execute_script", new ExecuteScript(this, in));
        register("exit", new Exit());
        register("head", new ShowHead(collectionManager));
        registerWithArgument("count_by_mood", new CountByMood(collectionManager, in));
        register("average_of_impact_speed", new AvarageOfImpactSpeed(collectionManager));
        register("remove_first", new RemoveFirst(collectionManager));
        register("print_field_ascending_mood", new PrintFieldAscendingMood(collectionManager));
//        register("remove_lower", new RemoveLower(collectionManager));
//        register("average_of_weight", new AverageOfWeight(collectionManager));
//        register("group_by_age", new GroupCountingByAge(collectionManager));
//        registerWithArgument("filter_by_weight", new FilterByWeight(collectionManager, in));
    }

    private void register(String name, Command command) {
        commandWithoutArguments.put(name, command);
    }
    private void registerWithArgument(String name, CommandWithArguments command) {
        commandWithArguments.put(name, command);
    }

    public void execute(String firstLineCommand) {
        String[] words = firstLineCommand.trim().split("\\s+");
        String[] args = Arrays.copyOfRange(words, 1, words.length);

        if (commandWithArguments.containsKey(words[0].toLowerCase(Locale.ROOT))) {
            CommandWithArguments command;
            command = commandWithArguments.get(words[0].toLowerCase(Locale.ROOT));
            command.getArguments(args);
            command.execute();
        } else if (commandWithoutArguments.containsKey(words[0].toLowerCase(Locale.ROOT))) {
            Command command;
            command = commandWithoutArguments.get(words[0].toLowerCase(Locale.ROOT));
            command.execute();
        } else {
            Log.info("Команда " + words[0] + " не распознана, введите корректную команду!");
        }

    }
}
