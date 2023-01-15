package ru.netology;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.simple.JSONObject;

import java.io.*;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class ServerLogic implements Serializable {
    protected transient String[] products;
    protected transient String[] categories;
    protected transient TSV_Parser tsv_parser;
    protected Map<String, Integer> mapMaxCategories;

    public ServerLogic(File file) {
        tsv_parser = new TSV_Parser();
        try {
            products = tsv_parser.parse(file, 2);
            categories = tsv_parser.parse(file, 3);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        mapMaxCategories = new HashMap<>();
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

    public void updateMapOfMaxCategories(Map<String, Integer> map, String category, int sum) {
//логика чтобы суммы для категории накапливалась
        for (String categoryInMap : map.keySet()) {
            if(categoryInMap.equalsIgnoreCase(category)) {
                map.put(category, map.get(category)+sum);
                return;
            }
        }
        mapMaxCategories.put(category, sum);
    }

    public String chooseMaxCategory(Map<String, Integer> map) {
//логика определения категории с максимальной суммой (посредством сортировки)
       Optional<Map.Entry<String, Integer>> o = map.entrySet().stream().max(Comparator.comparingInt(n -> n.getValue()));

       if (o.isPresent()) {
            return o.get().getKey();
        } else {
            System.out.println("Статистики запросов от клиента - нет");
            return null;
        }
    }

    public String makeResponse(String category, int sum) {
        JSONObject jsonObject1 = new JSONObject();
        JSONObject jsonObjectTopLevel = new JSONObject();
        jsonObject1.put("category", category);
        jsonObject1.put("sum", sum);
        jsonObjectTopLevel.put("maxCategory", jsonObject1);
        return jsonObjectTopLevel.toJSONString();
    }

    public String response(String clientRequest) throws IOException {
//запись данных запроса клиента в экземпляр класса productPurchase1
        ProductPurchase productPurchase1 = null;
        try {
            productPurchase1 = getProductPurchase(clientRequest);
        } catch (IOException e) {
            System.out.println("Не верный запрос от клиента");
            throw new RuntimeException(e);
        }

//обновляем статистику по категориям и накопленным суммам. Определяем максимальную категорию
        String category = checkProductCategory(productPurchase1);
        updateMapOfMaxCategories(mapMaxCategories, category, productPurchase1.getSum());
        String maxCategory = chooseMaxCategory(mapMaxCategories);
//формирование json ответа и отправка
        String response = makeResponse(maxCategory, mapMaxCategories.get(maxCategory));
        return response;
    }

    public void setProducts(String[] products) {
        this.products = products;
    }

    public void setCategories(String[] categories) {
        this.categories = categories;
    }

    public void setTsv_parser(TSV_Parser tsv_parser) {
        this.tsv_parser = tsv_parser;
    }
}
