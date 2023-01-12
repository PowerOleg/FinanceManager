//нужно проверить как сохраняется объект ServerLogicWithSaving, и посмотреть его параметры
//подключить в main вместо сервера



//нужная статистика для работы сервера:
//категории на которые привязаны суммы для этого первый дерьмовый вариант создать класс category с полями
//второй вариант: сказано использовать bin. туда записывается с полей используя сериализацию класса следующее:
// все в формате стринг: List<Strings[]>, список массивов: id, дата, продукт, категория, сумма


import ru.netology.ServerLogic;
import ru.netology.ServerLogicWithSaving;
import ru.netology.ServerResponseApp;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        File productDatabase = new File("categories.tsv");
                                                            //используется принцип декоратора для добавления функциональности классу ServerLogic
        Thread thread1 = new Thread(new ServerResponseApp(new ServerLogic(productDatabase))); //логику можем положить и другую
//        thread1.start();

        ServerLogicWithSaving serverLogicWithSaving = new ServerLogicWithSaving(productDatabase, new ServerLogic(productDatabase));
//        String[] line1 = {"1", "булка", "еда", "2022.02.08", "200"};
//        String[] line2 = {"2", "тапки", "одежда", "2023.01.10", "400"};
//        serverLogicWithSaving.add(line1);
//        serverLogicWithSaving.add(line2);
//        serverLogicWithSaving.save();
        serverLogicWithSaving.load();
    }
}
