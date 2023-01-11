package ru.netology;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.File;
import java.io.IOException;

import static org.mockito.BDDMockito.given;

public class ServerLogicTest {
    ProductPurchase productPurchase;
    ServerLogic serverLogic;

    File productDatabase;
    @BeforeEach
    public void initialization() {
        productDatabase = new File("categories.tsv");
        productPurchase = Mockito.mock(ProductPurchase.class);
    }

    @Test
    public void testCheckProductCategoryFoodPositive() throws IOException {
        String[] productArrayTest = {"булка", "колбаса", "сухарики", "курица", "тапки", "шапка", "мыло", "акции"};
        String[] categoriesArrayTest = {"еда", "еда", "еда", "еда", "одежда", "одежда", "быт", "финансы"};
        given(productPurchase.getTitle()).willReturn("булка");
        serverLogic = new ServerLogicImpl1(productDatabase, productArrayTest, categoriesArrayTest);

        String expected = "еда";
        String result = serverLogic.checkProductCategory(productPurchase);
        System.out.println(result);
        Assertions.assertEquals(expected, result);
    }



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
