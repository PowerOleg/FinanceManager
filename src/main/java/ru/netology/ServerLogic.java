package ru.netology;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.simple.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class ServerLogic {
    private Map<String, String> productsCategoriesMap;
    private TSV_Parser tsv_parser;
    private int sum = 0;

    public ServerLogic() {
        productsCategoriesMap = new HashMap<>();
        tsv_parser = new TSV_Parser();
        File file = new File("categories.tsv");
        try {
            String[] products = tsv_parser.parse(file, 2);
            String[] categories = tsv_parser.parse(file, 3);
            for (int i = 0; i < products.length; i++) {
                productsCategoriesMap.put(products[i], categories[i]);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public String checkProductCategory(ProductPurchase productPurchase) {
        Optional<String> product = productsCategoriesMap.keySet().stream()
                .filter(n -> n.equalsIgnoreCase(productPurchase.getTitle())).findFirst();

        if (product.isPresent()) {
            return productsCategoriesMap.get(product.get());
        }
        return "другое";
    }

    public String response(String clientRequest) {
//запись данных от клиента в экземпляр класса productPurchase1
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        ProductPurchase productPurchase1 = gson.fromJson(clientRequest, ProductPurchase.class);
//обработка и подготовка данных для вывода
        String category = checkProductCategory(productPurchase1);
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
