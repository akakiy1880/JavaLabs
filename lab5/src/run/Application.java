package run;

import collectionworker.*;
import commands.CommandInvoker;
import file.FileWorker;
import logging.Log;
import utils.HumanBeingFieldReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.rmi.AccessException;
import java.security.AccessControlException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Application {
    Scanner in = new Scanner(System.in);
    CollectionManager collectionManager = new CollectionManager();
    FileWorker fileWorker = new FileWorker(collectionManager);
    CommandInvoker commandInvoker;

    public void start(String inputFile) {
        Scanner in = new Scanner(System.in);
        HumanBeingFieldReader humanBeingFieldReader = new HumanBeingFieldReader();
        this.commandInvoker = new CommandInvoker(collectionManager, in, humanBeingFieldReader);
        try {
            File file = new File(inputFile);
            if (!file.canRead()) throw new RuntimeException();
            if ( !file.isFile() || file.isDirectory()) throw new FileNotFoundException();
            fileWorker.fromCSVtoObj();
            if (collectionManager.getSize() == 0) {
                Log.info("Добавьте объекты с помощью команды add, после чего введите команды save для сохранения в csv!");
            } else  Log.info("Объекты из файла загружены!");

        } catch (FileNotFoundException e) {
            Log.warning("Такого файла не существует");
        } catch (RuntimeException e ){
            Log.error("Отсутствует доступ на чтение файла.");
        }


        try {
            process();
        } catch (NoSuchElementException e) {
            Log.error("Закрыт поток ввода");
            Log.info("Завершение выполнения программы");
        } catch (RuntimeException e){
            Log.error("Закрыт поток ввода");
            Log.info("Завершение выполнения программы");
        }

    }

    public void process() {
        Log.info("Программа запущена");
        while (true) {
            Log.info("\nВведите название команды");
            String command = in.nextLine();
            commandInvoker.execute(command);
        }
    }
}

