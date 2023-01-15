//d
//        for (String[] s : saves) {
//            System.out.println("тут");
//            System.out.println(Arrays.toString(s));
//        }
package ru.netology;

import org.json.simple.JSONObject;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.sql.Date;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.sql.Date;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;


public class ServerLogicWithDates extends ServerLogicWithSaving implements Serializable {
    private transient ProductPurchase lastPurchase;
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
    public String response(String clientRequest) throws IOException {
        lastPurchase = getProductPurchase(clientRequest);
        String response1 = super.response(clientRequest);
        return response1;
    }

    @Override
    public String makeResponse(String category, int sum) {
        LocalDate dateLastPurchase = LocalDate.parse(lastPurchase.getDate().replace('.', '-'));

        JSONObject jsonObjectTopLevel = new JSONObject();
        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.put("category", category);
        jsonObject1.put("sum", sum);
        jsonObjectTopLevel.put("maxCategory", jsonObject1);
        System.out.println("6 mapMax Categories DatesClass" + mapMaxCategories);                  //d


        LocalDate date1;
        for (String[] line : saves) {
            date1 = LocalDate.parse(line[3].replace('.', '-'));

            if (date1.getYear() == dateLastPurchase.getYear()) {
                updateMapOfMaxCategories(maxYearCategory, line[2], Integer.parseInt(line[4]));
            }
        }
        String yearCategory = chooseMaxCategory(maxYearCategory);
        JSONObject jsonObject2 = new JSONObject();
        jsonObject2.put("category", yearCategory);
        jsonObject2.put("sum", maxYearCategory.get(yearCategory));
        jsonObjectTopLevel.put("maxYearCategory", jsonObject2);
        maxYearCategory.clear();

        LocalDate date2;
        for (String[] line : saves) {
            date2 = LocalDate.parse(line[3].replace('.', '-'));
            if (date2.getYear() == dateLastPurchase.getYear() && date2.getMonthValue() == dateLastPurchase.getMonthValue()) {
                updateMapOfMaxCategories(maxMonthCategory, line[2], Integer.parseInt(line[4]));
            }
        }
        String monthCategory = chooseMaxCategory(maxMonthCategory);

        JSONObject jsonObject3 = new JSONObject();
        jsonObject3.put("category", monthCategory);
        jsonObject3.put("sum", maxMonthCategory.get(monthCategory));
        jsonObjectTopLevel.put("maxMonthCategory", jsonObject3);
        maxMonthCategory.clear();
////        JSONObject jsonObject4 = new JSONObject();
////        jsonObject4.put("category", );
////        jsonObject4.put("sum", );
////        jsonObjectTopLevel.put("maxDayCategory", );
//
        return jsonObjectTopLevel.toJSONString();
    }







}
