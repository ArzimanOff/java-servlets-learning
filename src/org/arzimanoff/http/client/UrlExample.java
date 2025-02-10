package org.arzimanoff.http.client;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.URL;

public class UrlExample {
    public static void main(String[] args) throws IOException {
        var url = new URL("file:C:\\Users\\ahmed\\IdeaProjects\\http-servlets-learning\\src\\main\\java\\org\\arzimanoff\\http\\socket\\tcp\\SocketRunner.java");
        var urlConnection = url.openConnection();
//        System.out.println(new String(urlConnection.getInputStream().readAllBytes()));
        urlConnection.setDoOutput(true);
        try(var outputStream = new DataOutputStream(urlConnection.getOutputStream())){
            outputStream.writeUTF("// test");
        }
    }

    private static void checkGoogle() throws IOException {
        var url = new URL("https://www.google.com");
        var urlConnection = url.openConnection();

        System.out.println();
    }
}
