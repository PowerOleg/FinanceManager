package ru.netology;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import static org.mockito.BDDMockito.given;

public class ServerLogicTest {
    ProductPurchaseMock productPurchase;                    //вернуть без Mock
    ServerLogic serverLogic;
    TSV_Parser tsv_parser;
    File productDatabase;
    @BeforeEach
    public void initialization() {
        productDatabase = new File("categories.tsv");
        tsv_parser = Mockito.mock(TSV_Parser.class);
        productPurchase = new ProductPurchaseMock();/*Mockito.mock(ProductPurchase.class);*/
    }

    @Test
    public void testCheckProductCategoryFoodPositive() throws IOException {
        ProductPurchaseMock productPurchase = new ProductPurchaseMock();
        productPurchase.setValue("булка");

        String[] productArrayTest = {"булка", "колбаса", "сухарики", "курица", "тапки", "шапка", "мыло", "акции"};
        String[] categoriesArrayTest = {"еда", "еда", "еда", "еда", "одежда", "одежда", "быт", "финансы"};
        serverLogic = new ServerLogicMock(productDatabase, productArrayTest, categoriesArrayTest);            //какая разница между mock и spy???????????

//        given(tsv_parser.parse(productDatabase, 3)).willReturn(categoriesArrayTest);



//        Mockito.doReturn(productArrayTest).when(tsv_parser.parse(productDatabase, 2));

//    given(productPurchase.getTitle()).willReturn("булка");

    String expected1 = "еда";
    String result = serverLogic.checkProductCategory(productPurchase);
        System.out.println(result);
    Assertions.assertEquals(expected1, result);
    }
//короче нужно загрузить сюда из categories.tsv слова и их использовать


//    @ParameterizedTest
//    @ValueSource(strings = {"Asdad", "втулки"})
//    public void testCheckProductCategoryOtherPositive(String argument) {
//        given(productPurchase.getTitle()).willReturn(argument);
//        String expected2 = "другое";
//        String result = serverLogic.checkProductCategory(productPurchase);
//        Assertions.assertEquals(expected2, result);
//    }

//    @ParameterizedTest
//    @ValueSource(strings = {"тапки", "шапка"})
//    public void testCheckProductCategoryPositive(String argument) {
//        given(productPurchase.getTitle()).willReturn(argument);
//        String expected3 = "одежда";
//        String result3 = serverLogic.checkProductCategory(productPurchase);
//        Assertions.assertEquals(expected3, result3);
//    }
}
