package ru.job4j.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
    public static String getMsg(String line) {
        String result = line.split("msg=")[1];
        result = result.split(" ")[0];
        return result;
    }

    public static void main(String[] args) throws IOException {
        try (ServerSocket server = new ServerSocket(9000)) {
            boolean work = true;
            while (work) {
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    String line;
                    int indexOfLine = 0;
                    String answer = null;
                    while (!(line = in.readLine()).isEmpty()) {
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
                    }
                    out.write("HTTP/1.1 200 OK\r\n\\".getBytes());
                    if (answer != null) {
                        out.write((answer + "\r\n\\").getBytes());
                    }
                }
            }
        }
    }
}
