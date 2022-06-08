package run;

import logging.Log;

// Лабораторная работа №5

// Преподаватель: Егошин Алексей Васильевич
// Выполнили:
//           Полуянов Александр 341473
//           Крол Элина 316971


public class Main {
    public static void main(String[] args) {
        try {
             String inputFile = System.getenv("FILE_PATH");
             Application application = new Application();
             application.start(inputFile);

        } catch (NullPointerException e) {
            Log.warning("Укажите переменную окружения FILE_PATH!");
        }
    }
}
