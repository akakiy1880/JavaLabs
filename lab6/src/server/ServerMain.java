package server;

import server.logging.Log;
import server.server.Server;

// Лабораторная работа №6

// Преподаватель: Егошин Алексей Васильевич
// Выполнили:
//           Полуянов Александр 341473
//           Крол Элина 316971

// execute_script C:\Users\User\lab6\src\resouces\script2




public class ServerMain {
    public static void main(String[] args) {
        try {
            String file = System.getenv("FILE_PATH");
            Server server = new Server(file);
            server.run(7070);
        } catch (NullPointerException e ) {
            Log.warning("Укажите переменную окружения FILE_PATH!");
        }
    }
}

