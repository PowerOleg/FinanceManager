import ru.netology.ServerLogic;
import ru.netology.ServerResponseApp;
public class Main {
    public static void main(String[] args) {
        Thread thread1 = new Thread(new ServerResponseApp(new ServerLogic())); //логику можем положить и другую
        thread1.start();
    }
}
