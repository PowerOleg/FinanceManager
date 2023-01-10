package ru.netology;

public class ProductPurchase {
    private String title;
    private String date;
    private int sum;

    public ProductPurchase() {}
    public String getTitle() {
        return title;
    }
    public String getDate() {
        return date;
    }

    public int getSum() {
        return sum;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

    @Override
    public String toString() {
        return "ProductPurchase{" +
                "title='" + title + '\'' +
                ", date=" + date +
                ", sum=" + sum +
                '}';
    }
}
