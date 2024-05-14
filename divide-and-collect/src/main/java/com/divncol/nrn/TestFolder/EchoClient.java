package com.TestFolder;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

// connects to the server with the ip 134.60.156.67 and port 15636
public class EchoClient {
    
    public static void main (String[] args){
        try(Socket connection = new Socket("134.60.156.67", 15636)) {
            InputStream reader = connection.getInputStream();
            OutputStream writer = connection.getOutputStream();

            writer.write("Hello, World!".getBytes());
            connection.shutdownOutput();

            int c;
            String input = "";
            while((c = reader.read()) != -1) {
                input += (char) c;
            }
            System.out.println(input);

        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
