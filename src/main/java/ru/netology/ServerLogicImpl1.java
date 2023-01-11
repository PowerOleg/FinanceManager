package ru.netology;

import java.io.File;

public class ServerLogicImpl1 extends ServerLogic {
    String[] productsTest;
    String[] categoriesTest;

    public ServerLogicImpl1(File file, String[] products, String[] categories) {
        super(file);
        productsTest = products;
        categoriesTest = categories;
    }

    @Override
    public String checkProductCategory(ProductPurchase productPurchase) {
        String result = "другое";
        for (int i = 0; i < productsTest.length; i++) {
            if (productPurchase.getTitle().equalsIgnoreCase(productsTest[i])) {
                result = categoriesTest[i];
            }
        }
        return result;
    }
}
