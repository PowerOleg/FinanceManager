package ru.netology;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.simple.JSONObject;

import java.io.File;
import java.io.IOException;

public class ServerLogic {
    private String[] products;
    private String[] categories;
    private TSV_Parser tsv_parser;
    private int sum = 0;

    public ServerLogic(File file) {
        tsv_parser = new TSV_Parser();
        try {
            products = tsv_parser.parse(file, 2);
            categories = tsv_parser.parse(file, 3);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static ProductPurchase getProductPurchase(String clientRequest) throws IOException {
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        return gson.fromJson(clientRequest, ProductPurchase.class);
    }

    public String checkProductCategory(ProductPurchase productPurchase) {
        String result = "другое";
        for (int i = 0; i < products.length; i++) {
            if (productPurchase.getTitle().equalsIgnoreCase(products[i])) {
                result = categories[i];
            }
        }
        return result;
    }

    public String makeResponse(String category, int sum) {
        JSONObject jsonObject1 = new JSONObject();
        JSONObject jsonObjectTopLevel = new JSONObject();
        jsonObject1.put("category", category);
        jsonObject1.put("sum", sum);
        jsonObjectTopLevel.put("maxCategory", jsonObject1);
        return jsonObjectTopLevel.toJSONString();
    }

    public String response(String clientRequest) {
//запись данных от запроса клиента в экземпляр класса productPurchase1
        ProductPurchase productPurchase1 = null;
        try {
            productPurchase1 = getProductPurchase(clientRequest);
        } catch (IOException e) {
            System.out.println("Не верный запрос от клиента");
            throw new RuntimeException(e);
        }

//обработка и подготовка данных для вывода
        String category = checkProductCategory(productPurchase1);
        sum += productPurchase1.getSum();
//формирование json и отправка
        String response = makeResponse(category, sum);
        return response;
    }
}
