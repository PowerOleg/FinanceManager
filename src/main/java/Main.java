//нужная статистика для работы сервера:
//категории на которые привязаны суммы для этого первый дерьмовый вариант создать класс category с полями
//второй вариант: сказано использовать bin. туда записывается с полей используя сериализацию класса следующее:
// все в формате стринг: List<Strings[]>, список массивов: id, дата, продукт, категория, сумма
//





// потом пробегаться по категориям и суммировать по категориям

//идеально бы было использовать уже имеющийся файл .tsv

//сумма категории за день, месяц, за год, за все время
//для этого все запросы клиента нужно записывать в лист и сортировать по дате

//дата начала для категории

import ru.netology.ServerLogic;
import ru.netology.ServerResponseApp;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        File productDatabase = new File("categories.tsv");
                                                            //используется принцип декоратора для добавления функциональности классу ServerLogic
        Thread thread1 = new Thread(new ServerResponseApp(new ServerLogic(productDatabase))); //логику можем положить и другую
        thread1.start();
    }
}
