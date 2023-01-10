package ru.netology;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.simple.JSONObject;

import java.io.File;
import java.util.*;

public class ServerLogic {
    private Map<String, String> productsCategoriesMap;
    private TSV_Parser tsv_parser;
    private int sum = 0;

    public ServerLogic() {
        productsCategoriesMap = new HashMap<>();
        tsv_parser = new TSV_Parser();
        List<String[]> dataFromFile = tsv_parser.parse(new File("categories.tsv"));
        String[] line;
        for (Iterator<String[]> iterator = dataFromFile.iterator(); iterator.hasNext(); ) {
            String[] sArray = iterator.next();
            line = sArray[0].split("\t");   //что за херня, сплитим массив? переделать чтобы сразу нормальный массив приходил а не с длинной 1
            productsCategoriesMap.put(line[1], line[2]);
        }
    }

    //сделал метод checkProductCategory() здесь, а не в классе ProductPurchase, потому что логика сервера здесь.
// и у другого подобного класса ServerLogic другая логика, а class ProductPurchase нужен только как болванка.
     public String checkProductCategory(ProductPurchase productPurchase) {
        String result = "другое";
         for (String s : productsCategoriesMap.keySet()) {
             if (productPurchase.getTitle().equalsIgnoreCase(s)) {
                 result = productsCategoriesMap.get(s);
             }
         }
    return result;
    }


    public String response(String clientRequest) {
//запись данных в class productPurchase1
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        ProductPurchase productPurchase1 = gson.fromJson(clientRequest, ProductPurchase.class);
        System.out.println("2 " + productPurchase1);
//обработка и подготовка данных для вывода
        String category = checkProductCategory(productPurchase1);
        sum += productPurchase1.getSum();
//формирование json и отправка
        JSONObject jsonObject1 = new JSONObject();
        JSONObject jsonObjectTopLevel = new JSONObject();
        jsonObject1.put("category" , category);
        jsonObject1.put("sum" , sum);
        jsonObjectTopLevel.put("maxCategory", jsonObject1);
        String response = jsonObjectTopLevel.toJSONString();
        return response;
    }
}
