package com.divncol.nrn;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

public class DNCServer {
    
    public static void main(String[] args) {
        try {
            ServerSocket server = new ServerSocket(1407);
            ExecutorService service = Executors.newCachedThreadPool();

            while(true) {
                Socket connection = server.accept();
                service.submit(new DNCHandler(connection));
            }
        }catch(IOException e) {
            e.printStackTrace();
        }
    }
}

class DNCHandler implements Runnable {
    private final Socket connection;
    private final BufferedReader reader;
    private final PrintWriter writer;

    private LinkedBlockingQueue<String> queue;

    private String username;
    private String password;

    public DNCHandler(Socket connection) throws IOException {
        this.connection = connection;
        this.reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        this.writer = new PrintWriter(connection.getOutputStream());
        
    }

    @Override
    public void run() {
        try {
            connection.getOutputStream().write("Hello to DivNCol".getBytes());
            connection.getOutputStream().write("LogIn or SignUp? (L/S)".getBytes());
        }catch(IOException e) {
            e.printStackTrace();
        }
    }
}