/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testeksamen;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author RolfMoikjær
 */
public class NewServer {

    /**
     * Application method to run the server runs in an infinite loop listening
     * on port 9898. When a connection is requested, it spawns a new thread to
     * do the servicing and immediately returns to listening. The server keeps a
     * unique client number for each client that connects just to show
     * interesting logging messages. It is certainly not necessary to do this.
     */
    public static void main(String[] args) throws Exception {
        System.out.println("The Server is running.");
        int turnstileNumber = 1;
        int customerNumber = 0;
        ServerSocket listener = new ServerSocket(9898);
        try {
            while (true) {
                new Turnstile(listener.accept(), turnstileNumber++, customerNumber).start();
            }
        } finally {
            listener.close();
        }
    }

    /**
     * A private thread to handle capitalization requests on a particular
     * socket. The client terminates the dialogue by sending a single line
     * containing only a period.
     */
    private static class Turnstile extends Thread {

        private Socket socket;
        private int turnstileNumber;
        private int customerNumber;

        public Turnstile(Socket socket, int turnstileNumber, int customerNumber) {
            this.socket = socket;
            this.turnstileNumber = turnstileNumber;
            this.customerNumber = customerNumber;
            log("New connection with turnstile# " + turnstileNumber);
        }

        public int getTurnstileNumber() {
            return turnstileNumber;
        }

        public int getCustomerNumber() {
            return customerNumber;
        }

        /**
         * Services this thread's client by first sending the client a welcome
         * message then repeatedly reading strings and sending back the
         * capitalized version of the string.
         */
        public void run() {
            try {

                // Decorate the streams so we can send characters
                // and not just bytes.  Ensure output is flushed
                // after every newline.
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

                // Send a welcome message to the client.
                out.println("Hello, you are turnstile#" + turnstileNumber + ".");
                out.println("Enter . to quit\n");

                // Get messages from the client, line by line; return them
                // capitalized
                while (true) {
                    String input = in.readLine();
                    if (input == null || input.equals(".")) {
                        break;
                    }
                    customerNumber++;
                    out.println(customerNumber);
                }
            } catch (IOException e) {
                log("Error handling turnstile# " + turnstileNumber + ": " + e);
            } finally {
                try {

                    socket.close();
                } catch (IOException e) {
                    log("Couldn't close a socket, what's going on?");
                }
                log("Connection with turnstile# " + turnstileNumber + " closed");
                log("Number of Customers were: " + customerNumber);
            }
        }

        /**
         * Logs a simple message. In this case we just write the message to the
         * server applications standard output.
         */
        private void log(String message) {
            System.out.println(message);
        }
    }
}
