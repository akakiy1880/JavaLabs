package server;

import bd.BDCollection;
import dragon.Dragon;
import util.Database;
import utils.IOUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.charset.StandardCharsets;

public class Server {
    CollectionManager collectionManager;

    public Server(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    public void run(int port, Database database, BDCollection BDCollection) {
        ServerSocketChannel serverSocket;
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            File file = new File(System.getenv("FILE_PATH"));
            try (FileOutputStream outputStream = new FileOutputStream(file)){
                for (Dragon vals : collectionManager.getDragons()) {
                    outputStream.write(vals.toString().getBytes(StandardCharsets.UTF_8));
                    outputStream.write("\n".getBytes(StandardCharsets.UTF_8));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }));
        try {
            serverSocket = ServerSocketChannel.open();
            serverSocket.bind(new InetSocketAddress(port));
            serverSocket.configureBlocking(false);
            ClientReader clientReader = new ClientReader();
            clientReader.reader(serverSocket, collectionManager, database, BDCollection);
        } catch (IOException e) {
            IOUtils.println("Ошибка ввода/вывода!");
        } catch (ClassNotFoundException e) {
            IOUtils.println("Сериализуемый класс не наследует класс Serializable");
        }
    }
}