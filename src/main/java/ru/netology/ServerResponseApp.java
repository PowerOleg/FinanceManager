package ru.netology;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * request = {"title": "булка", "date": "2022.02.08", "sum": 200}
 *
 */

public class ServerResponseApp implements Runnable {
    JSONParser jsonParser = new JSONParser();
    @Override
    public void run() {
        try (ServerSocket serverSocket = new ServerSocket(8989)) {
            System.out.println("Сервер запустился");
            while (true) {
                try (Socket socket = serverSocket.accept();
                    BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    PrintWriter out = new PrintWriter(socket.getOutputStream())) {
                    System.out.println("Клиент подключился");
                    String clientRequest = in.readLine();
                    System.out.println(clientRequest);


                    try {
                        Object o = jsonParser.parse(clientRequest);
                        JSONObject jsonObject = (JSONObject) o;
                        String titleValue = (String) jsonObject.get("title");
                        System.out.println("title: " + titleValue);


                    } catch (ParseException e) {
                        throw new RuntimeException(e);
                    }

//                        String lastName = (String) jsonObject.get("lastName");
//                        System.out.println("1. " + lastName);
//
//                        JSONObject address = (JSONObject) jsonObject.get("address");
//                        System.out.println("2. " + address);
//
//                    String streetAddress = (String) address.get("streetAddress");
//                    System.out.println("3. " + streetAddress);
//                    }



                }
            }
        } catch (IOException e) {
            System.out.println("Не могу стартовать сервер");
            e.printStackTrace();
        }
    }



}
