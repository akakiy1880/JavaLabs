package server.server;

import lib.dragon.Dragon;
import lib.utils.IOUtils;
import server.CollectionManager;
import server.logging.Log;

import java.io.*;
import java.net.BindException;
import java.net.InetSocketAddress;
import java.net.SocketException;
import java.nio.channels.ServerSocketChannel;
import java.nio.charset.StandardCharsets;

public class Server {
    private String file;
    CollectionManager collectionManager = new CollectionManager();
    ServerSocketChannel serverSocket;

    public Server(String file) {
        this.file = file;
    }

    public void run(int port) {
        try {
            FileWorker fileWorker = new FileWorker(collectionManager);
            ClientReader clientReader = new ClientReader();
            try {
                String inputFile = System.getenv("FILE_PATH");
                File file = new File(inputFile);
                if (!file.canWrite() || !file.isFile() || file.isDirectory()) throw new IOException();
                fileWorker.fromCSVtoObj();
                if (collectionManager.getSize() == 0) {
                    Log.info("Добавьте объекты с помощью команды add, после чего введите команду save для сохранения в csv!");
                } else Log.info("Объекты из файла загружены!");

            }
            catch (IOException e) {
                Log.info(("Такого файла нет"));
                System.exit(0);
            }
            serverSocket = ServerSocketChannel.open();
            serverSocket.bind(new InetSocketAddress(port));
            serverSocket.configureBlocking(false);
            clientReader.reader(serverSocket, collectionManager);
        } catch (BindException e){
            Log.info("Запуск 2 серверов на 1 порту запрещен. Прекращение работы.");
        }
        catch (SocketException e){

            Log.info("Клиент отключился");
//            e.printStackTrace();
//               try{

//            serverSocket.close();
//
//            }
//            catch (Exception err){
//
//            } finally {
//                try {
//                    String file = System.getenv("FILE_PATH");
//                    Server server = new Server(file);
//                    server.run(7070);
//                } catch (NullPointerException er ) {
//                    Log.warning("Укажите переменную окружения FILE_PATH!");
//                }
//            }
        }

        catch (IOException e) {
            Log.error(e.toString());
            IOUtils.println("Ошибка ввода/вывода!"); // И всякая хрень
        } catch (ClassNotFoundException e) {
            IOUtils.println("Сериализуемый класс не наследует класс Serializable");
        }
    }
}