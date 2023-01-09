package ru.netology;

import java.util.Date;

public class ProductPurchase {
    private String title;
    private String date;
    private int sum;

    @Override
    public String toString() {
        return "ProductPurchase{" +
                "title='" + title + '\'' +
                ", date=" + date +
                ", sum=" + sum +
                '}';
    }
}
