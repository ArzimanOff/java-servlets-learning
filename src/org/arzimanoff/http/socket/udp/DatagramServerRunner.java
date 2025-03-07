package org.arzimanoff.http.socket.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class DatagramServerRunner {
    public static void main(String[] args) throws IOException {
        try (var datagramServer = new DatagramSocket(7777);) {
            byte[] buffer = new byte[512];
            DatagramPacket p = new DatagramPacket(buffer, buffer.length);
            datagramServer.receive(p);
            System.out.println(new String(buffer));
        }
    }
}
