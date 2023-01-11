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

    @ParameterizedTest
    @ValueSource(strings = {"тапки", "шапка"})
    public void testCheckProductCategoryClothesPositive(String argument) {
    given(productPurchase.getTitle()).willReturn(argument);
    String expected = "одежда";
    String result = serverLogic.checkProductCategory(productPurchase);
    Assertions.assertEquals(expected, result);
    }

    @ParameterizedTest
    @ValueSource(strings = {"Asdad", "втулки"})
    public void testCheckProductCategoryOtherPositive(String argument) {
        given(productPurchase.getTitle()).willReturn(argument);
        String expected = "другое";
        String result = serverLogic.checkProductCategory(productPurchase);
        Assertions.assertEquals(expected, result);
    }
}
