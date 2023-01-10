package ru.netology;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerResponseApp implements Runnable {
    ServerLogic serverLogic;

    public ServerResponseApp(ServerLogic serverLogic) {
        this.serverLogic = serverLogic;
    }

    @Override
    public void run() {
        try (ServerSocket serverSocket = new ServerSocket(8989)) {
            System.out.println("Сервер запустился");
            while (true) {
                try (Socket socket = serverSocket.accept();
                    BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {
                    System.out.println("Клиент подключился");
                    String clientRequest = in.readLine();
                    System.out.println("Запрос от клиента: " + clientRequest);

                    String response = serverLogic.response(clientRequest);
                    out.println(response);
                    System.out.println("Ответ сервера: " + response);
                }
            }
        } catch (IOException e) {
            System.out.println("Не могу стартовать сервер");
            e.printStackTrace();
        }
    }
}
