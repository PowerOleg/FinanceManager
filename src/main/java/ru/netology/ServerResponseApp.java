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
                    System.out.println("1 " + clientRequest);

//блок в отдельный метод парсинга
                    GsonBuilder gsonBuilder = new GsonBuilder();
                    Gson gson = gsonBuilder.create();
                    ProductPurchase productPurchase1 = gson.fromJson(clientRequest, ProductPurchase.class);
                    System.out.println("2 " + productPurchase1);
//метод подсчета и обработки

//метод отправки response
                    JSONObject jsonObject1 = new JSONObject();
                    JSONObject jsonObject2 = new JSONObject();

                    jsonObject1.put("category" , "уда");                            //тут переделать
                    jsonObject1.put("sum" , "3516431");                                  //тут переделать
                    jsonObject2.put("maxCategory", jsonObject1);
                    String response = jsonObject2.toJSONString();
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
