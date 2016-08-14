/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import com.sun.security.ntlm.Client;
import controller.ClientController;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import protocol.Protocol;

/**
 *
 * @author RolfMoikjær
 */
public class ClientHandler extends Thread {

    private String userName;
    private Socket socket;
    private PrintWriter output;
    private Scanner input;
    private ClientController cc;

    public ClientHandler(Socket socket, ClientController cc) throws IOException {
        this.socket = socket;
        this.cc = cc;
        output = new PrintWriter(socket.getOutputStream(), true);
        input = new Scanner(socket.getInputStream());

    }

    public void send(String msg, String sender) {
        output.println(Protocol.MSG + sender + "#" + msg);
    }

    public void send(String msg) {
        output.println(msg);
    }

    @Override
    public void run() {
        String inputStr;

        while (true) {
            try {
                inputStr = input.nextLine();
            } catch (NoSuchElementException ex) {
                Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                try {
                    socket.close();
                } catch (IOException exc) {
                    Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, exc);
                }
                cc.RemoveClient(this);
                break;
            }

            if (inputStr.contains(Protocol.USER)) {
                String name = inputStr.substring(Protocol.USER.length());

                if (userName != null && !userName.isEmpty()) {
                    try {
                        socket.close();
                    } catch (IOException ex) {
                        Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    break;
                }

                userName = name;
                if (!cc.AddClient(this)) {
                    try {
                        socket.close();
                    } catch (IOException ex) {
                        Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    break;
                }
            } else if (inputStr.contains(Protocol.MSG)) {
                if (userName == null) {
                    try {
                        socket.close();
                    } catch (IOException ex) {
                        Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    break;
                }

                String str = inputStr.substring(Protocol.MSG.length());

                if (!str.contains("#")) {
                    try {
                        socket.close();
                    } catch (IOException ex) {
                        Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    break;
                }

                String receviversStr = str.split("[#]")[0];
                String message = str.split("[#]")[1];

                if (receviversStr.equals("*")) {
                    cc.SendToAll(message, userName);
                    continue;
                }

                if (receviversStr.contains(",")) {
                    String[] recevivers = receviversStr.toLowerCase().split("[,]");
                    cc.SendToUsers(recevivers, message, userName);
                    continue;
                }

                if (!cc.SendToUser(receviversStr, message, userName)) {
                    send("Could not find user " + receviversStr, "System");
                }
            } else if (inputStr.contains(Protocol.STOP)) {
                try {
                    socket.close();
                } catch (IOException ex) {
                    Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                }
                cc.RemoveClient(this);
                break;
            } else {
                try {
                    socket.close();
                } catch (IOException ex) {
                    Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            }
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ClientHandler other = (ClientHandler) obj;
        if (!Objects.equals(this.userName.toLowerCase(), other.userName.toLowerCase())) {
            return false;
        }
        return true;
    }

    public String getUserName() {
        return userName;
    }

}
