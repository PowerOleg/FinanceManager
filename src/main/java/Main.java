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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        File productDatabase = new File("categories.tsv");
                                                            //используется принцип декоратора для добавления функциональности классу ServerLogic
        Thread thread1 = new Thread(new ServerResponseApp(new ServerLogic(productDatabase))); //логику можем положить и другую
//        thread1.start();

        ServerLogicWithSaving serverLogicWithSaving = ServerLogicWithSaving.load();
//                new ServerLogicWithSaving(productDatabase, new ServerLogic(productDatabase));
//        String[] line1 = {"1", "булка", "еда", "2022.02.08", "200"};
//        String[] line2 = {"2", "тапки", "одежда", "2023.01.10", "400"};
//        serverLogicWithSaving.add(line1);
//        serverLogicWithSaving.add(line2);

        for (String[] s : serverLogicWithSaving.getSaves()) {
            System.out.println(Arrays.toString(s));
        }
//        serverLogicWithSaving.save();

//
//        List<String[]> saveList = new ArrayList<>();
//        saveList = serverLogicWithSaving.getSaves();
//        System.out.println(saveList);
//        System.out.println("-");
//
//        System.out.println(serverLogicWithSaving.load());
//        saveList = serverLogicWithSaving.getSaves();
//        System.out.println(saveList);
//                .forEach(n -> System.out.println(Arrays.deepToString(n)));
    }
}
