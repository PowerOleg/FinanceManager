package ru.netology;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mockito;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;

public class ServerLogicWithDatesTest {
    @Test
    public void testMakeResponsePositive() {
        File productDatabase = new File("categories.tsv");
        ServerLogicWithDates serverLogicWithDates = new ServerLogicWithDates(productDatabase, new ServerLogic(productDatabase));
        String[] line1 = {"1", "мыло", "быт", "2023.01.12", "200"};
        serverLogicWithDates.saves.add(line1);
        ProductPurchase productPurchase = new ProductPurchase();
        productPurchase.setTitle("мыло");
        productPurchase.setDate("2023.01.12");
        productPurchase.setSum(200);
        serverLogicWithDates.lastPurchase = productPurchase;

        final String expected = "{\"maxMonthCategory\":{\"sum\":200,\"category\":\"быт\"},\"maxDayCategory\":{\"sum\":200,\"category\":\"быт\"},\"maxCategory\":{\"sum\":200,\"category\":\"быт\"},\"maxYearCategory\":{\"sum\":200,\"category\":\"быт\"}}";
        final String result = serverLogicWithDates.makeResponse("быт", 200);
        Assertions.assertEquals(expected, result);
    }


@ParameterizedTest
@ValueSource(strings = {"{\"title\": \"булка\", \"date\": \"2022.02.08\", \"sum\": 200}"})
public void testResponsePositive(String argument) throws IOException {
    File productDatabase = new File("categories.tsv");
    final String[] productArrayTest = {"булка", "колбаса", "сухарики", "курица", "тапки", "шапка", "мыло", "акции"};
    final String[] categoriesArrayTest = {"еда", "еда", "еда", "еда", "одежда", "одежда", "быт", "финансы"};
    ServerLogicWithDates serverLogic = new ServerLogicWithDates(productDatabase, new ServerLogicImpl1(productDatabase, productArrayTest, categoriesArrayTest));
    serverLogic.setProducts(productArrayTest);
    serverLogic.setCategories(categoriesArrayTest);
    final String expected = "{\"maxMonthCategory\":{\"sum\":200,\"category\":\"еда\"},\"maxDayCategory\":{\"sum\":200,\"category\":\"еда\"},\"maxCategory\":{\"sum\":200,\"category\":\"еда\"},\"maxYearCategory\":{\"sum\":200,\"category\":\"еда\"}}";
    final String result = serverLogic.response(argument);
    Assertions.assertEquals(expected, result);
}
}
