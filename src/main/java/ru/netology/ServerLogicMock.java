package ru.netology;

import java.io.File;

public class ServerLogicMock extends ServerLogic {
    String[] productsTest;
    String[] categoriesTest;

    public ServerLogicMock(File file, String[] products, String[] categories) {
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
