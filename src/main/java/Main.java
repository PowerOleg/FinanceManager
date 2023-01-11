import ru.netology.ServerLogic;
import ru.netology.ServerResponseApp;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        File productDatabase = new File("categories.tsv");
        Thread thread1 = new Thread(new ServerResponseApp(new ServerLogic(productDatabase))); //логику можем положить и другую
        thread1.start();
    }
}
