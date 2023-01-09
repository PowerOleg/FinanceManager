package ru.netology;

//    JSONParser jsonParser = new JSONParser();
//                    try {
//                        Object o = jsonParser.parse(clientRequest);
//                        JSONObject jsonObject = (JSONObject) o;
//                        String titleValue = (String) jsonObject.get("title");
//                        System.out.println("title: " + titleValue);
//                    } catch (ParseException e) {
//                        throw new RuntimeException(e);
//                    }

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * request = {"title": "булка", "date": "2022.02.08", "sum": 200}
 * response =
 * { "maxCategory": {
 *     "category": "еда",
 *     "sum": 350000
 *   }
 * }
 */

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

                    String clientRequest = in.readLine();                   //есть смысл создание String выносить из цикла?
                    System.out.println("1 " + clientRequest);

                    String response = serverLogic.response(clientRequest);
                    out.println(response);
                    System.out.println("3 " + response);
                }
            }
        } catch (IOException e) {
            System.out.println("Не могу стартовать сервер");
            e.printStackTrace();
        }
    }



}
