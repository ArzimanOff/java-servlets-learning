package org.arzimanoff.http.socket.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.Inet4Address;
import java.net.SocketException;

public class DatagramRunner {
    public static void main(String[] args) throws SocketException, IOException {
        var inetAddress = Inet4Address.getByName("localhost");

        try (var datagramSocket = new DatagramSocket();

        ) {
            // buffer
            var bytes = "Hello from ArzimanOff!".getBytes();
            DatagramPacket p = new DatagramPacket(bytes, bytes.length, inetAddress, 7777);
            datagramSocket.send(p);
        }
    }
}
