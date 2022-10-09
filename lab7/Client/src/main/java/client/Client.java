package client;

import command.CommandInvoker;
import packer.Pack;
import utils.IOUtils;

import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.Socket;
import java.util.concurrent.ForkJoinPool;

public class Client {
    private Pack pack = new Pack();
    private Response response = new Response();
    private Request request = new Request();
    private ForkJoinPool pool = new ForkJoinPool();

    public void run(int port) {
        InetAddress address = null;
        try {
            address = Inet4Address.getByName("localhost");
            Socket socket = new Socket(address, port);
            CommandInvoker commandInvoker = new CommandInvoker(socket);
            Authenticator authenticator = new Authenticator();
            while (!pack.getReg()) {
                pack.packUser(authenticator.username(), authenticator.password(), false);
                request.request(pack, socket);
                pack = response.responseUser(socket);
                if (pack.getCommandName().equals("Неполадки с сервером!"))
                    System.exit(0);
            }
            while (true) {
                IOUtils.print("Введите комманду: ");
                String str = IOUtils.input();
                if (commandInvoker.execute(str, pack) & !str.startsWith("update")) {
                    request.request(pack, socket);
                    response.response(socket);
                    if (str.trim().equalsIgnoreCase("exit"))
                        System.exit(0);
                }
            }
        } catch (IOException e) {
            IOUtils.printErr("Повторная попытка подключения через 5 секунд!");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }
            run(port);
        } catch (ClassNotFoundException e) {
            IOUtils.printErr("Сериализуемый класс не наследует класс Serializable");
        }
    }
}
