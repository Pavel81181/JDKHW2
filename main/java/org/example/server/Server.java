package org.example.server;

import org.example.client.Client;
import org.example.server.Repository;
import org.example.server.ServerView;

import java.util.ArrayList;
import java.util.List;

public class Server {
    private boolean work;
    private ServerView serverView;
    private List<Client> clientList;
    private Repository<String> repository;

    public Server(ServerView serverView, Repository<String> repository) {
        this.serverView = serverView;
        this.repository = repository;
        clientList = new ArrayList<>();
        serverView.setServer(this);
    }

    public void start(){
        if (work){
            showOnWindow("Сервер уже был запущен");
        } else {
            work = true;
            showOnWindow("Сервер запущен!");
        }
    }

    public void stop(){
        if (!work){
            showOnWindow("Сервер уже был остановлен");
        } else {
            work = false;
            while (!clientList.isEmpty()){
                disconnectUser(clientList.get(clientList.size() - 1));
            }
            showOnWindow("Сервер остановлен!");
        }
    }

    public void disconnectUser(Client client){
        clientList.remove(client);
        if (client != null){
            client.disconnectFromServer();
            showOnWindow(client.getName() + " отключился от беседы");
        }
    }

    public boolean connectUser(Client clientController){
        if (!work){
            return false;
        }
        clientList.add(clientController);
        showOnWindow(clientController.getName() + " подключился к беседе");
        return true;
    }

    public void message(String text){
        if (!work){
            return;
        }
        showOnWindow(text);
        answerAll(text);
        saveInHistory(text);
    }

    public String getHistory() {
        return repository.load();
    }

    private void answerAll(String text){
        for (Client clientController : clientList){
            clientController.answerFromServer(text);
        }
    }

    private void showOnWindow(String text){
        serverView.showMessage(text + "\n");
    }

    private void saveInHistory(String text){
        repository.save(text);
    }
}