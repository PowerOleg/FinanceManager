package ru.netology;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.simple.JSONObject;

import java.io.File;
import java.util.List;

public class ServerLogic {
    int sum = 0;
//сделал метод checkProductCategory() здесь а не в классе ProductPurchase, потому что логика сервера здесь.
// и у другого подобного класса ServerLogic другая логика, а class ProductPurchase нужен только как болванка.
    /*abstract*/ public String checkProductCategory(ProductPurchase productPurchase) {
        TSV_Parser tsv_parser = new TSV_Parser();
        List<String[]> categoriesList = tsv_parser.parse(new File("categories.tsv"));
        productPurchase.getTitle();
        


    }
    public String response(String clientRequest) {

//        ProductPurchase productPurchase1 = new ProductPurchase() {
//            @Override
//            public String filter() {
//                TSV_Parser tsv_parser = new TSV_Parser();
//                List<String[]> categoriesList = tsv_parser.parse(new File("categories.tsv"));
//
//
//            }
//        };

//запись данных в productPurchase1
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
        jsonObject1.put("category" , category);                         //!
        jsonObject1.put("sum" , sum);                                   //!
        jsonObjectTopLevel.put("maxCategory", jsonObject1);
        String response = jsonObjectTopLevel.toJSONString();

        return response;
    }



}
