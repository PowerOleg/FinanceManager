package ru.netology;

import org.json.simple.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClientApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try (Socket clientSocket = new Socket("localhost", 8989);
             BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {

            JSONObject jsonObject1 = new JSONObject();
            jsonObject1.put("title", "колбаса");
            jsonObject1.put("date", "2023.01.14");
            jsonObject1.put("sum", "300");

            out.println(jsonObject1.toJSONString());
            String serverResponse2 = in.readLine();
            System.out.println(serverResponse2);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
