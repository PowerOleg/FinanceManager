package ru.netology;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.mockito.BDDMockito.given;

public class ServerLogicTest {
    ProductPurchase productPurchase;
    ServerLogic serverLogic;
    @BeforeEach
    public void initialization() {
        productPurchase = Mockito.mock(ProductPurchase.class);
        serverLogic = new ServerLogic();
    }

//    @Test
//    public void testCheckProductCategoryFoodPositive() {
//    given(productPurchase.getTitle()).willReturn("булка");
//    String expected1 = "еда";
//    String result = serverLogic.checkProductCategory(productPurchase);
//    Assertions.assertEquals(expected1, result);
//    }

//    @ParameterizedTest
//    @ValueSource(strings = {"Asdad", "втулки"})
//    public void testCheckProductCategoryOtherPositive(String argument) {
//        given(productPurchase.getTitle()).willReturn(argument);
//        String expected2 = "другое";
//        String result = serverLogic.checkProductCategory(productPurchase);
//        Assertions.assertEquals(expected2, result);
//    }

    @ParameterizedTest
    @ValueSource(strings = {"тапки", "шапка"})
    public void testCheckProductCategoryPositive(String argument) {
        given(productPurchase.getTitle()).willReturn(argument);
        String expected3 = "одежда";
        String result3 = serverLogic.checkProductCategory(productPurchase);
        Assertions.assertEquals(expected3, result3);
    }
}
