package com.TestFolder;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EchoServer {
    
    public static void main(String[] args) {
        try {
            ServerSocket server = new ServerSocket(15636);
            ExecutorService service = Executors.newCachedThreadPool();

            while(true) {
                Socket connection = server.accept();
                service.submit(new EchoHandler(connection));
            }
        }catch(Exception e) {
            e.printStackTrace();
        }
    }
}

class EchoHandler implements Runnable{
    private final Socket connection;

    public EchoHandler(Socket connection) {
        this.connection = connection;
    }

    @Override
    public void run() {
        try {
            connection.getOutputStream().write("Hello to DivNCol".getBytes());
        }catch(Exception e) {
            e.printStackTrace();
        }
    }
}