package server;

import commands.CommandInvoker;
import bd.BDCollection;
import bd.BDUser;
import packer.Pack;
import util.Database;
import util.SHA1;
import utils.DragonFieldsReader;
import utils.IOUtils;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Scanner;

public class ClientReader {
    private CollectionManager collectionManager;
    private Scanner in;
    private DragonFieldsReader dragonFieldsReader = new DragonFieldsReader();
    private String str = "";

    void reader(ServerSocketChannel serverSocket, CollectionManager collectionManager, Database database, BDCollection BDCollection) throws IOException, ClassNotFoundException {
        this.collectionManager = collectionManager;
        Selector selector;
        selector = Selector.open();
        serverSocket.register(selector, SelectionKey.OP_ACCEPT);
        ByteBuffer buffer = ByteBuffer.allocate(Integer.MAX_VALUE / 2);

        while (true) {
            selector.select();
            Iterator<SelectionKey> keys = selector.selectedKeys().iterator();
            while (keys.hasNext()) {
                SelectionKey key = keys.next();
                keys.remove();
                if (!key.isValid()) continue;
                if (key.isAcceptable()) accept(selector, serverSocket);
                if (key.isReadable()) read(key, buffer, database, BDCollection);
            }
        }
    }

    public void accept(Selector selector, ServerSocketChannel serverSocketChannel) throws IOException {
        SocketChannel channel = serverSocketChannel.accept();
        channel.configureBlocking(false);
        channel.register(selector, SelectionKey.OP_READ);
        IOUtils.printErr("Client " + channel.getRemoteAddress());
    }

    public void read(SelectionKey key, ByteBuffer buffer, Database database, BDCollection BDCollection) throws IOException, ClassNotFoundException {
        Pack pack = new Pack();
        pack.pack("");
        Request request = new Request();
        SocketChannel client = (SocketChannel) key.channel();
        BDUser BDUser = new BDUser();
        boolean connect;
        connect = BDUser.runBDUser(database);
        CommandInvoker commandInvoker = new CommandInvoker(collectionManager, in, dragonFieldsReader, client, BDCollection);
        int num = client.read(buffer);
        if (num == -1) {
            client.close();
        } else {
            buffer.flip();
            pack = (Pack) request.deserialize(buffer);
            SHA1 sha1 = new SHA1();
            if (!pack.getReg()) {
                String password = sha1.getHash(pack.getPassword());
                if (BDUser.getAll().contains(pack.getUser())) {
                    if (password.equals(BDUser.getUserPassword(pack.getUser()))) {
                        buffer.clear();
                        pack.pack("Вы вошли!");
                        pack.setReg(true);
                        Response response = new Response();
                        buffer.put(response.serialize(pack));
                        buffer.flip();
                        client.write(buffer);
                        buffer.clear();
                    } else {
                        buffer.clear();
                        pack.pack("Неправильный пароль!");
                        pack.setReg(false);
                        Response response = new Response();
                        buffer.put(response.serialize(pack));
                        buffer.flip();
                        client.write(buffer);
                        buffer.clear();
                    }
                } else if (connect == false) {
                    buffer.clear();
                    pack.pack("Неполадки с сервером!");
                    pack.setReg(false);
                    Response response = new Response();
                    buffer.put(response.serialize(pack));
                    buffer.flip();
                    client.write(buffer);
                    buffer.clear();
                } else {
                    BDUser.add(pack.getUser(), password);
                    buffer.clear();
                    pack.pack("Вы зарегестрированы!");
                    pack.setReg(true);
                    Response response = new Response();
                    buffer.put(response.serialize(pack));
                    buffer.flip();
                    client.write(buffer);
                    buffer.clear();
                }
            } else {
                buffer.clear();
                pack = commandInvoker.execute(pack);
                if (!pack.getCommandName().equals("get_dragon")) {
                    Response response = new Response();
                    buffer.put(response.serialize(pack));
                    buffer.flip();
                    client.write(buffer);
                    buffer.clear();
                }
            }
        }
    }
}