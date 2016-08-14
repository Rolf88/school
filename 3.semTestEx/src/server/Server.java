/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import controller.ClientController;
import entity.ClientHandler;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.util.Properties;
import utils.Utils;

/**
 *
 * @author RolfMoikjær
 */
public class Server {

    private static final Properties properties = Utils.initProperties("Server.properties");
    private static ClientController cc = new ClientController();

    public static void main(String[] args) throws IOException {
        String ip = properties.getProperty("serverIp");
        int port = Integer.parseInt(properties.getProperty("port"));

        ServerSocket ss = new ServerSocket();
        ss.bind(new InetSocketAddress(ip, port));
        while (true) {
            new ClientHandler(ss.accept(), cc).start();
        }
    }
}
