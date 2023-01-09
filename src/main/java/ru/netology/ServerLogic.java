package ru.netology;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.simple.JSONObject;

import java.io.File;
import java.util.List;

public class ServerLogic {

    public String response(String clientRequest) {
//parsing полученных данных
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        ProductPurchase productPurchase1 = gson.fromJson(clientRequest, ProductPurchase.class);
        System.out.println("2 " + productPurchase1);

//маппинг
        TSV_Parser tsv_parser = new TSV_Parser();
        List<String[]> categoriesList = tsv_parser.parse(new File("categories.tsv"));
//        categoriesList.forEach(n -> System.out.println(Arrays.toString(n)));

//создание данных для отправки
        JSONObject jsonObject1 = new JSONObject();
        JSONObject jsonObject2 = new JSONObject();

        jsonObject1.put("category" , "уда");                            //тут переделать
        jsonObject1.put("sum" , "3516431");                                  //тут переделать
        jsonObject2.put("maxCategory", jsonObject1);
        String response = jsonObject2.toJSONString();

        return response;
    }



}
