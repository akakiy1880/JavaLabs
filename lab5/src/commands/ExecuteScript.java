package commands;

import interfaces.CommandWithArguments;
import logging.Log;

import java.io.File;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class ExecuteScript implements CommandWithArguments {

    Set<String> scriptsInProcess = new HashSet<>();
    Scanner scanner;
    CommandInvoker invoker;
    String[] arguments;

    public ExecuteScript(CommandInvoker invoker, Scanner scanner) {
        this.scanner = scanner;
        this.invoker = invoker;
    }

    @Override
    public void execute() {
        try{
            if (arguments.length == 0) {
                throw new NullPointerException();
            }
        String fileName = arguments[0];
        File file = new File(fileName);
        String absolutePath = file.getAbsolutePath();

        if (scriptsInProcess.contains(absolutePath)) {
            Log.warning("Данный скрипт уже выполняется.");
            Log.warning("Выполнение этого скрипта в данный момент невозможно.");
            return;
        }

        scriptsInProcess.add(absolutePath);
        Scanner scanner;
        try {


            
            scanner = new Scanner(file);
            System.out.printf("Попытка прочитать команды из файла \"%s\":%n", file.getName());
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                System.out.printf("Команда \"%s\":%n", line);
                invoker.execute(line);
            }
            Log.info("Все команды были успешно выполнены.");
        } catch (Exception e) {
            e.printStackTrace();
            Log.error("Непредвиденная ошибка при выполнении скрипта.");
        }
        scriptsInProcess.remove(absolutePath);
        }catch (NullPointerException e){
            Log.warning("Введите путь к скрипту");
        }
    }

    @Override
    public String getDescription() {
        String s = "считать и исполнить скрипт из указанного файла";
        return s;
    }

    @Override
    public void getArguments(String[] arguments) {
        this.arguments = arguments;
    }
}
