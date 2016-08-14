/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.sun.security.ntlm.Client;
import entity.ClientHandler;
import java.util.ArrayList;
import java.util.Arrays;
import protocol.Protocol;

/**
 *
 * @author RolfMoikjær
 */
public class ClientController {
    
    private ArrayList<ClientHandler> clients;

    public ClientController() {
        clients = new ArrayList();
    }

    public boolean AddClient(ClientHandler client) {
        if (clients.contains(client)) {
            return false;
        }
        clients.add(client);
        SendUserList();
        return true;
    }

    public void RemoveClient(ClientHandler client) {
        clients.remove(client);
        SendUserList();
    }

    public void SendToAll(String msg, String sender) {
        for (ClientHandler client : clients) {
            client.send(msg, sender);
        }
    }

    public void SendToAll(String msg) {
        for (ClientHandler client : clients) {
            client.send(msg);
        }
    }

    public boolean SendToUser(String user, String msg, String sender) {
        ClientHandler endClient = null;
        for (ClientHandler client : clients) {
            if (client.getUserName().toLowerCase().equals(user.toLowerCase())) {
                endClient = client;
                break;
            }
        }

        if (endClient == null) {
            return false;
        }
        endClient.send(msg, sender);
        return true;
    }

    public void SendToUsers(String[] users, String msg, String sender) {

        for (ClientHandler client : clients) {
            if (Arrays.asList(users).contains(client.getUserName().toLowerCase())) {
                client.send(msg, sender);
            }
        }

    }

    public void SendUserList() {
        String userList = Protocol.USERLIST;
        for (ClientHandler client : clients) {
            userList += client.getUserName() + ",";
        }
        if (clients.size() > 0) {
            userList = userList.substring(0, userList.length() - 1);
        }
        SendToAll(userList);
    }

    public ArrayList<ClientHandler> getClients() {
        return clients;
    }
}
