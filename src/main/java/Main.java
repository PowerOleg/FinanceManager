import ru.netology.ServerLogic;
import ru.netology.ServerLogicWithSaving;
import ru.netology.ServerResponseApp;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        File productDatabase = new File("categories.tsv");
        //используется принцип декоратора для добавления функциональности классу ServerLogic. Теперь используем ServerLogicWithSaving
        Thread thread1 = new Thread(new ServerResponseApp(new ServerLogicWithSaving(productDatabase, new ServerLogic(productDatabase))));
        thread1.start();
    }
}
