package ru.netology;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mockito;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.stream.Collectors;

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
        final String[] productArrayTest = {"булка", "колбаса", "сухарики", "курица", "тапки", "шапка", "мыло", "акции"};
        final String[] categoriesArrayTest = {"еда", "еда", "еда", "еда", "одежда", "одежда", "быт", "финансы"};
        given(productPurchase.getTitle()).willReturn(argument);
        serverLogic = new ServerLogicImpl1(productDatabase, productArrayTest, categoriesArrayTest);

        final String expected = "еда";
        final String result = serverLogic.checkProductCategory(productPurchase);
        Assertions.assertEquals(expected, result);
    }

    @ParameterizedTest
    @ValueSource(strings = {"Asdad", "втулки"})
    public void testCheckProductCategoryOtherPositive(String argument) {
        final String[] productArrayTest = {"булка", "колбаса", "сухарики", "курица", "тапки", "шапка", "мыло", "акции"};
        final String[] categoriesArrayTest = {"еда", "еда", "еда", "еда", "одежда", "одежда", "быт", "финансы"};
        given(productPurchase.getTitle()).willReturn(argument);
        serverLogic = new ServerLogicImpl1(productDatabase, productArrayTest, categoriesArrayTest);

        final String expected = "другое";
        final String result = serverLogic.checkProductCategory(productPurchase);
        Assertions.assertEquals(expected, result);
    }


    @Test
    public void testMakeResponsePositive() {
        serverLogic = new ServerLogic(productDatabase);
        final String expected = "{\"maxCategory\":{\"sum\":200,\"category\":\"быт\"}}";
        final String result = serverLogic.makeResponse("быт", 200);
        Assertions.assertEquals(expected, result);
    }

    @ParameterizedTest
    @ValueSource(strings = {"{\"title\": \"булка\", \"date\": \"2022.02.08\", \"sum\": 200}",
            "{\"title\": \"колбаса\", \"date\": \"2022.10.08\", \"sum\": 200}"})
    public void testResponsePositive(String argument) throws IOException {
        final String[] productArrayTest = {"булка", "колбаса", "сухарики", "курица", "тапки", "шапка", "мыло", "акции"};
        final String[] categoriesArrayTest = {"еда", "еда", "еда", "еда", "одежда", "одежда", "быт", "финансы"};
        serverLogic = new ServerLogicImpl1(productDatabase, productArrayTest, categoriesArrayTest);
        final String expected = "{\"maxCategory\":{\"sum\":200,\"category\":\"еда\"}}";
        final String result = serverLogic.response(argument);
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void testUpdateMapOfMaxCategoriesPositive() {
        serverLogic = new ServerLogic(productDatabase);
        serverLogic.updateMapOfMaxCategories("финансы", 500);
        Map<String, Integer> expectedMap = Map.of("финансы", 500);
        Map<String, Integer> resultMap = serverLogic.mapMaxCategories;
        Assertions.assertEquals(new ArrayList<>(serverLogic.mapMaxCategories.entrySet()), new ArrayList<>(expectedMap.entrySet()));
    }

    @Test
    public void testChooseMaxCategory() {
        serverLogic = new ServerLogic(productDatabase);
        serverLogic.updateMapOfMaxCategories("еда", 200);
        serverLogic.updateMapOfMaxCategories("финансы", 150);
        serverLogic.updateMapOfMaxCategories("финансы", 150);
        serverLogic.updateMapOfMaxCategories("одежда", 400);
        serverLogic.updateMapOfMaxCategories("финансы", 200);
        serverLogic.updateMapOfMaxCategories("еда", 200);

        String expected = "финансы";
        String result = serverLogic.chooseMaxCategory();
        Assertions.assertEquals(expected, result);
    }
}
