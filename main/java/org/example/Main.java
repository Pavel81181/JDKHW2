package org.example;

import org.example.client.Client;
import org.example.client.ClientGUI;
import org.example.server.Server;
import org.example.server.FileStorage;
import org.example.server.ServerWindow;

public class Main {
    public static void main(String[] args) {
        Server server = new Server(new ServerWindow(), new FileStorage());

        new Client(new ClientGUI(), server);
        new Client(new ClientGUI(), server);
    }
}