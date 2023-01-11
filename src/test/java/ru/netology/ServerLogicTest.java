package ru.netology;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mockito;

import java.io.File;
import java.io.IOException;

import static org.mockito.BDDMockito.given;
import static ru.netology.ServerLogic.getProductPurchase;

public class ServerLogicTest {
    ProductPurchase productPurchase;
    ServerLogic serverLogic;

    File productDatabase;
    @BeforeEach
    public void initialization() {
        productDatabase = new File("categories.tsv");
        productPurchase = Mockito.mock(ProductPurchase.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"{\"title\": \"булка\", \"date\": \"2022.02.08\", \"sum\": 200}",
            "{\"title\": \"колбаса\", \"date\": \"2022.10.20\", \"sum\": 1000}"
    })
    public void testGetProductPurchasePositive(String argument) throws IOException {
        ProductPurchase result = getProductPurchase(argument);
        Assertions.assertNotNull(result.getTitle());
        Assertions.assertNotNull(result.getDate());
        Assertions.assertNotNull(result.getSum());
    }


    @ParameterizedTest
    @ValueSource(strings = {"булка", "курица"})
    public void testCheckProductCategoryFoodPositive(String argument) throws IOException {
        String[] productArrayTest = {"булка", "колбаса", "сухарики", "курица", "тапки", "шапка", "мыло", "акции"};
        String[] categoriesArrayTest = {"еда", "еда", "еда", "еда", "одежда", "одежда", "быт", "финансы"};
        given(productPurchase.getTitle()).willReturn(argument);
        serverLogic = new ServerLogicImpl1(productDatabase, productArrayTest, categoriesArrayTest);

        String expected = "еда";
        String result = serverLogic.checkProductCategory(productPurchase);
        System.out.println(result);
        Assertions.assertEquals(expected, result);
    }

    @ParameterizedTest
    @ValueSource(strings = {"Asdad", "втулки"})
    public void testCheckProductCategoryOtherPositive(String argument) {
        String[] productArrayTest = {"булка", "колбаса", "сухарики", "курица", "тапки", "шапка", "мыло", "акции"};
        String[] categoriesArrayTest = {"еда", "еда", "еда", "еда", "одежда", "одежда", "быт", "финансы"};
        given(productPurchase.getTitle()).willReturn(argument);
        serverLogic = new ServerLogicImpl1(productDatabase, productArrayTest, categoriesArrayTest);

        String expected = "другое";
        String result = serverLogic.checkProductCategory(productPurchase);
        Assertions.assertEquals(expected, result);
    }
}
