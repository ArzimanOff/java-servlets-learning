package org.arzimanoff.http.socket.tcp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Inet4Address;
import java.net.Socket;
import java.util.Scanner;

public class SocketRunner {
    public static void main(String[] args) throws IOException {
        // http - 80
        // https - 443
        // tcp порт
        var inetAddress = Inet4Address.getByName("localhost");
        try (var socket = new Socket(inetAddress, 7777);
             var outputStream = new DataOutputStream(socket.getOutputStream());
             var inputStream = new DataInputStream(socket.getInputStream());
             var scanner = new Scanner(System.in);
            ) {
            while (scanner.hasNextLine()){
                var request = scanner.nextLine();
                outputStream.writeUTF(request);
                var response = inputStream.readUTF();
                System.out.println("Ответ от сервера: " + response);
            }
        }
    }
}