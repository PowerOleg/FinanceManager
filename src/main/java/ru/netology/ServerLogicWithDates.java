package ru.netology;

import org.json.simple.JSONObject;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;


public class ServerLogicWithDates extends ServerLogicWithSaving implements Serializable {
    protected Map<String, Integer> maxYearCategory;
    protected Map<String, Integer> maxMonthCategory;
    protected Map<String, Integer> maxDayCategory;
    private transient ProductPurchase lastPurchase;

    public ServerLogicWithDates(File file, ServerLogic serverLogic) {
        super(file, serverLogic);
        maxYearCategory = new HashMap<>();
        maxMonthCategory = new HashMap<>();
        maxDayCategory = new HashMap<>();
    }

    @Override
    public String response(String clientRequest) throws IOException {
        lastPurchase = getProductPurchase(clientRequest);
        return super.response(clientRequest);
    }

    @Override
    public String makeResponse(String category, int sum) {
        LocalDate dateLastPurchase = LocalDate.parse(lastPurchase.getDate().replace('.', '-'));

        JSONObject jsonObjectTopLevel = new JSONObject();
        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.put("category", category);
        jsonObject1.put("sum", sum);
        jsonObjectTopLevel.put("maxCategory", jsonObject1);

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


        LocalDate date3;
        for (String[] line : saves) {
            date3 = LocalDate.parse(line[3].replace('.', '-'));
            if (date3.getYear() == dateLastPurchase.getYear() &&
                    date3.getMonthValue() == dateLastPurchase.getMonthValue() &&
                    date3.getDayOfMonth() == dateLastPurchase.getDayOfMonth()) {
                updateMapOfMaxCategories(maxDayCategory, line[2], Integer.parseInt(line[4]));
            }
        }
        String dayCategory = chooseMaxCategory(maxDayCategory);
        JSONObject jsonObject4 = new JSONObject();
        jsonObject4.put("category", dayCategory);
        jsonObject4.put("sum", maxDayCategory.get(dayCategory));
        jsonObjectTopLevel.put("maxDayCategory", jsonObject4);
        maxDayCategory.clear();

        return jsonObjectTopLevel.toJSONString();
    }
}
