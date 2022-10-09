package client;

import packer.Pack;
import utils.IOUtils;

import java.io.*;
import java.net.Socket;

public class Response {
    Pack pack = new Pack();

    public void response(Socket socket) throws IOException, ClassNotFoundException {
        byte[] bytes = new byte[32768];
        socket.getInputStream().read(bytes);
        pack = deserialize(bytes);
        IOUtils.print(pack.getCommandName());
    }

    public Pack responseUser(Socket socket) throws IOException, ClassNotFoundException {
        byte[] bytes = new byte[32768];
        socket.getInputStream().read(bytes);
        pack = deserialize(bytes);
        IOUtils.println(pack.getCommandName());
        return pack;
    }

    public void responseShow(Socket socket) throws IOException, ClassNotFoundException {
        byte[] bytes = new byte[32768];
        InputStream inputStream = new DataInputStream(socket.getInputStream());

        while (inputStream.read(bytes) > -1) {
            pack = deserialize(bytes);
            IOUtils.print(pack.getCommandName());
        }
    }

    public Pack responseUpdate(Socket socket) throws IOException, ClassNotFoundException {
        byte[] bytes = new byte[32768];
        socket.getInputStream().read(bytes);
        pack = deserialize(bytes);
        if (!pack.getCommandName().equals(""))
            IOUtils.printErr(pack.getCommandName());
        return pack;
    }

    public Pack deserialize(byte[] bytes) throws IOException, ClassNotFoundException {
        ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
        ObjectInputStream objectInputStream = new ObjectInputStream(bais);
        return (Pack) objectInputStream.readObject();
    }
}
