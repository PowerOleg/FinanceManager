import ru.netology.ServerLogic;
import ru.netology.ServerLogicWithDates;
import ru.netology.ServerLogicWithSaving;
import ru.netology.ServerResponseApp;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        File productDatabase = new File("categories.tsv");
        //используется принцип декоратора для добавления функциональности классу ServerLogic.
        Thread thread1 = new Thread(new ServerResponseApp(new ServerLogicWithDates(productDatabase, new ServerLogic(productDatabase))));
        thread1.start();
    }
}
