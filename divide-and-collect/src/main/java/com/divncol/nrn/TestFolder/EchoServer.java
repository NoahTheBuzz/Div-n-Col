package com.TestFolder;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
    
    public static void main(String[] args) {
        try(ServerSocket server = new ServerSocket(15636);) {
            Socket connection = server.accept();
            System.out.println("[SERVER] Connection established");

            InputStream reader = connection.getInputStream();
            OutputStream writer = connection.getOutputStream();

            int c;
            while ((c = reader.read()) != -1) {
                writer.write(c);
                writer.flush();
            }

            System.out.println("[SERVER] Echoed everything");

            connection.close();

            System.out.println("[SERVER] Connection closed");

        }catch(Exception e) {
            e.printStackTrace();
        }
    }
}