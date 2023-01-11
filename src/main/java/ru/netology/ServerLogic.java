package ru.netology;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.simple.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class ServerLogic {
//    private Map<String, String> productsCategoriesMap;
    private TSV_Parser tsv_parser;
    private int sum = 0;

    public ServerLogic() {
        tsv_parser = new TSV_Parser();
//        productsCategoriesMap = new HashMap<>();
//        File file = new File("categories.tsv");
//        try {
//            String[] products = tsv_parser.parse(file, 2);
//            String[] categories = tsv_parser.parse(file, 3);
//            for (int i = 0; i < products.length; i++) {
//                productsCategoriesMap.put(products[i], categories[i]);
//            }
//        } catch (FileNotFoundException e) {
//            throw new RuntimeException(e);
//        }
    }

    public String checkProductCategory(ProductPurchase productPurchase, File file) {
        String result = "другое";
        try {
            String[] products = tsv_parser.parse(file, 2);
            String[] categories = tsv_parser.parse(file, 3);

            System.out.println(Arrays.toString(products));                                           //d
            System.out.println(Arrays.toString(categories));                                           //d
            for (int i = 0; i < products.length; i++) {
                if (productPurchase.getTitle().equalsIgnoreCase(products[i])) {
                    result = categories[i];
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }


//        System.out.println(productPurchase.getTitle());                                             //d
//        System.out.println(productsCategoriesMap.keySet());                                         //d
//        Optional<String> product = productsCategoriesMap.keySet().stream()
//                .filter(n -> n.equalsIgnoreCase(productPurchase.getTitle())).findFirst();
//
//        if (product.isPresent()) {
//            System.out.println("тут");                                                              //d
//            return productsCategoriesMap.get(product.get());
//        } else {
//            System.out.println("не тут");                                                              //d
//            return "другое";
//        }
        return result;
    }

    public String response(String clientRequest) {
        File productDatabase = new File("categories.tsv");
//запись данных от клиента в экземпляр класса productPurchase1
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        ProductPurchase productPurchase1 = gson.fromJson(clientRequest, ProductPurchase.class);
//обработка и подготовка данных для вывода
        String category = checkProductCategory(productPurchase1, productDatabase);
        sum += productPurchase1.getSum();
//формирование json и отправка
        JSONObject jsonObject1 = new JSONObject();
        JSONObject jsonObjectTopLevel = new JSONObject();
        jsonObject1.put("category", category);
        jsonObject1.put("sum", sum);
        jsonObjectTopLevel.put("maxCategory", jsonObject1);
        String response = jsonObjectTopLevel.toJSONString();
        return response;
    }
}
