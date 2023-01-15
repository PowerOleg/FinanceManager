package ru.netology;

import org.json.simple.JSONObject;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.sql.Date;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.sql.Date;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;


public class ServerLogicWithDates extends ServerLogicWithSaving implements Serializable {
    protected Map<String, Integer> maxYearCategory;
    protected Map<String, Integer>  maxMonthCategory;
    protected Map<String, Integer>  maxDayCategory;

    public ServerLogicWithDates(File file, ServerLogic serverLogic) {
        super(file, serverLogic);
        maxYearCategory = new HashMap<>();
        maxMonthCategory = new HashMap<>();
        maxDayCategory = new HashMap<>();
    }

    @Override
    public String makeResponse(String category, int sum) {
        JSONObject jsonObjectTopLevel = new JSONObject();
        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.put("category", category);
        jsonObject1.put("sum", sum);
        jsonObjectTopLevel.put("maxCategory", jsonObject1);


//        Map<String, Integer> maxYearCategory =
        LocalDate date1;
        for (String[] line : saves) {
            date1 = LocalDate.parse(line[3].replace('.', '-'));
            System.out.println(date1);                                                  //d
            if (date1.getYear() == 2023) {                                              //!!!костыль. убрать 2023
                updateMapOfMaxCategories(maxYearCategory, line[2], Integer.parseInt(line[4]));
            }
        }
        System.out.println(maxYearCategory);                                                    //d
        String yearCaterory = chooseMaxCategory(maxYearCategory);
        JSONObject jsonObject2 = new JSONObject();
        jsonObject2.put("category", yearCaterory);
        jsonObject2.put("sum", maxYearCategory.get(yearCaterory));
        jsonObjectTopLevel.put("maxYearCategory", jsonObject2);






//        JSONObject jsonObject3 = new JSONObject();
//        jsonObject3.put("category", );
//        jsonObject3.put("sum", );
//        jsonObjectTopLevel.put("maxMonthCategory", );
//
//        JSONObject jsonObject4 = new JSONObject();
//        jsonObject4.put("category", );
//        jsonObject4.put("sum", );
//        jsonObjectTopLevel.put("maxDayCategory", );

        return jsonObjectTopLevel.toJSONString();
    }


    @Override
    public String response(String clientRequest) throws IOException {
       String response1 = super.response(clientRequest);              //это и что ниже - равносильно
//        String response = serverLogic.response(clientRequest);
//        ProductPurchase productPurchase = getProductPurchase(clientRequest);
////[0] это номер строчки
//        int id = 0;
//        for (String[] s : saves) {
//            if (Integer.parseInt(s[0]) > id) id = Integer.parseInt(s[0]);
//        }
////[1] это наименование товара
//        String product = productPurchase.getTitle();
////[2] это наименование категории
//        String category = checkProductCategory(productPurchase);
////[3] это дата
//        String date = productPurchase.getDate();
////[4] это сумма
//        String sum = String.valueOf(productPurchase.getSum());
//
//        String[] save = {String.valueOf(id + 1), product, category, date, sum};
//        this.addSave(save);
//        this.save();
//        return response;
        return response1;
    }





}
