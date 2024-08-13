package org.example.server;

import org.example.server.Server;

public interface ServerView {
    void showMessage(String message);
    void setServer(Server server);
}