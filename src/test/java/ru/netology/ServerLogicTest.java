package ru.netology;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.io.File;

import static org.mockito.BDDMockito.given;

public class ServerLogicTest {
//    ProductPurchaseMock productPurchase;
//    ServerLogic serverLogic;
//    @BeforeEach
//    public void initialization() {
//        serverLogic = new ServerLogic();
//        productPurchase = new ProductPurchaseMock();/*Mockito.mock(ProductPurchase.class);*/
//    }

    @Test
    public void testCheckProductCategoryFoodPositive() {
        File productDatabase = new File("categories.tsv");
        ServerLogic serverLogic = new ServerLogic();
        ProductPurchaseMock productPurchase = new ProductPurchaseMock();
        productPurchase.setValue("булка");
//    given(productPurchase.getTitle()).willReturn("булка");
//    Mockito.when(productPurchase.getTitle()).thenReturn("булка");
    String expected1 = "еда";
    String result = serverLogic.checkProductCategory(productPurchase, productDatabase);
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
