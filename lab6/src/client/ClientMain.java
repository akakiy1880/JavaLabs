package client;

import client.client.Client;

import java.io.IOException;

public class ClientMain {
    public static void main(String[] args) throws IOException {
        Client client = new Client();
        client.run(7070);
    }
}
