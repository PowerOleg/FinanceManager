package ru.netology;

import java.io.File;
import java.sql.Date;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.sql.Date;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
//    date2.format(DateTimeFormatter.ofPattern("d.MM.uuuu")) использования своего формата не работает в 11 версии джавы.

public class ServerLogicWithDates extends ServerLogic {
    protected ServerLogic serverLogic;
    protected Map<LocalDate, String[]> maxYearCategory;
    protected Map<LocalDate, String[]> maxMonthCategory;
    protected Map<LocalDate, String[]> maxDayCategory;

//    один Map - одна контрольная точка
//    контрольные точки:
//за последний год/месяц/день совпадающий с годом последней покупки,
//    за последний месяц
//    за последний день



    public void updateMaxDayCategory(String date, String[] categoryAndSum) {
        LocalDate date1 = LocalDate.parse(date.replace('.', '-'));
        for (LocalDate date : maxDayCategory.keySet()) {

        }
        maxDayCategory.put();
    }

    public String[] chooseMaxDayCategory() {

        return maxDayCategory.get();
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
