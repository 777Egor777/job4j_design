package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
    private static final Logger LOG = LoggerFactory.getLogger(EchoServer.class.getName());

    public static String getMsg(String line) {
        String result = line.split("msg=")[1];
        result = result.split(" ")[0];
        return result;
    }

    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(9000)) {
            boolean work = true;
            while (work) {
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    String line = in.readLine();
                    int indexOfLine = 0;
                    String answer = null;
                    while (!line.isEmpty()) {
                        if (indexOfLine++ == 0
                           && line.contains("?msg=")) {
                            String msg = getMsg(line);
                            if (msg.equals("Hello")) {
                                answer = "Hello, my dear friend";
                            } else if (msg.equals("Exit")) {
                                work = false;
                            } else {
                                answer = msg;
                            }
                        }
                        System.out.println(line);
                        line = in.readLine();
                    }
                    out.write("HTTP/1.1 200 OK\r\n\\".getBytes());
                    if (answer != null) {
                        out.write((answer + "\r\n\\").getBytes());
                    }

                }
            }
        } catch (IOException e) {
            LOG.error("IOException", e);
        }
    }
}
