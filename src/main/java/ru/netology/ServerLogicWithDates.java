package ru.netology;

import java.io.File;
import java.sql.Date;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.sql.Date;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;


public class ServerLogicWithDates extends ServerLogic {
    protected ServerLogic serverLogic;
    protected Map<String, Integer> maxYearCategory;
    protected Map<String, Integer>  maxMonthCategory;
    protected Map<String, Integer>  maxDayCategory;

//    один Map - одна контрольная точка
//    контрольные точки:
//все делают последний год/месяц/день совпадающий с годом последней покупки,
//надо попытаться за последний
//    за последний год
//    за последний месяц
//    за последний день

//убрать productPurchase и передать дату
    public void updateMaxDayCategory(ProductPurchase productPurchase, String category, Integer sum) {
        LocalDate date1 = LocalDate.parse(productPurchase.getDate().replace('.', '-'));
//?????за последнй день это надо сравниват с датой покупки которая в списке
        if (date1.isEqual(/*тут должна быть дата из мапы*/)) {
            for (String category1 : maxDayCategory.keySet()) {
                if (category1.equalsIgnoreCase(category)) {
                    maxDayCategory.put(category1, maxDayCategory.get(category1) + sum);
                    return;
                }
            }
            maxDayCategory.put(category, sum);
        }
    }


//получается мапа хранит все покупки и даты за нужный период, месяц- это за 30 дней. и каждый день ползунок сдвигается
    public void updateMaxMonthCategory(ProductPurchase productPurchase, String category, Integer sum) {
        LocalDate date1 = LocalDate.parse(productPurchase.getDate().replace('.', '-'));
//за последнй месяц + надо сравниват с датой покупки которая в списке
//покупка 15.02.2023
//в мапе категория за 01.02.2023
//в мапе категория за 29.01.2023
//все суммируется потому что 15.02 - 30 = 15.01


//условие: если дата
        if (date1.getMonthValue() == ) {
            for (String category1 : maxDayCategory.keySet()) {
                if (category1.equalsIgnoreCase(category)) {
                    maxDayCategory.put(category1,  maxDayCategory.get(category1)+sum);
                    return;
                }
            }
            maxDayCategory.put(category, sum);
        }
    }







    //        LocalDate day256_2017 = LocalDate.ofYearDay(2014, 256);
//        System.out.println("256 день 2017 : " + day256_2017);          // 256 день 2017 года : 2017-09-13
//
//спер у Егора)
//private String title;
//    private Long sum;
//    private String category;
//    private String date;
//    private LocalDate parsedDate;

//    public int getPurchaseYear() {
//        this.date = date.replace('.', '-');
//        this.parsedDate = LocalDate.parse(date);
//        return parsedDate.getYear();
//    }


    public ServerLogicWithDates(File file) {
        super(file);
    }



}
