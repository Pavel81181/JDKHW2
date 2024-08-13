package org.example.client;

import org.example.client.Client;

public interface ClientView {

    /**
     * Метод для отображения сообщения в GUI
     * @param message текст сообщения
     */
    void showMessage(String message);

    /**
     * Метод отключения от сервера со стороны сервера
     */
    void disconnectedFromServer();

    /**
     * Сеттер
     * @param client объект, описывающий логику программы
     */
    void setClient(Client client);
}