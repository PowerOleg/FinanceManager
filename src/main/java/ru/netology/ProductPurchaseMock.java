package ru.netology;

public class ProductPurchaseMock extends ProductPurchase {
    private String value;

    @Override
    public String getTitle() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
